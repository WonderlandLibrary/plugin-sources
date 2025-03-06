package com.elevatemc.anticheat.base.check.impl.fight.hitselect;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityStatus;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Queue;

public class HitSelectA extends PacketCheck {
    private static final int SAMPLES = 14;
    private boolean checkDelay = false;
    private int lastHurt = 0;
    private long lastAttack;
    private int targetEntity = -1;
    private int clientTicks = 0;
    private boolean movingAway = false;
    private int zeros = 0;
    private int lastTicks = -1;
    private final Queue<Integer> delayTimes = new ArrayDeque<>(SAMPLES);
    public HitSelectA(PlayerData playerData) {
        super(playerData, "HitSelect A", "Checks for hit select", new ViolationHandler(10, 3000L), Category.COMBAT, SubCategory.BACK_TRACK, 3);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            Entity entity = SpigotReflectionUtil.getEntityById(wrapper.getEntityId());
            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK && entity instanceof Player) {
                targetEntity = wrapper.getEntityId();
                lastAttack = clientTicks;

                if (checkDelay) {
                    checkDelay = false;

                    int diff = clientTicks - lastHurt;

                    if (movingAway) {
                        //debug("bro was moving away :(");
                        return;
                    }

                    if (diff == 0) {
                        //debug("zero hit => " + movingAway + " " + wasMovingAway);
                        zeros++;
                        if (zeros >= 3) {
                            // If the player was hit selecting they would never have 3x 0 delays
                            delayTimes.clear();
                            zeros = 0;
                        }
                        return;
                    } else if (diff > 60) {
                        // Player has not attacked for 3 seconds -> useless sample
                        return;
                    } else {
                        delayTimes.add(diff);
                    }

                   // debug("diff=" + diff);
                    //debug("delays=" + delayTimes.size());

                    if (delayTimes.size() == SAMPLES) {
                        Int2IntMap repeated = new Int2IntOpenHashMap();

                        for (int delay : delayTimes) {
                            repeated.put(delay, repeated.getOrDefault(delay, 0) + 1);
                        }

                        double stDev = MathUtil.getStandardDeviation(delayTimes);
                        double average = MathUtil.getAverage(delayTimes);

                       // debug("stDev=" + stDev + " avg=" + average);
                       // debug("Map=" + repeated);

                        for (var entry : repeated.int2IntEntrySet()) {
                            // If one delay is the same for half the sample size -> flag
                            if (entry.getIntValue() >= SAMPLES / 2) {
                                int ticks = entry.getIntKey();

                                if (this.lastTicks == ticks) {
                                    int pause = Math.max((ticks * 50) - 50, 50);
                                    handleViolation(new DetailedPlayerViolation(this,"PAUSE ~" + pause + "ms"));
                                }

                                this.lastTicks = ticks;

                                break;
                            }
                        }

                        delayTimes.clear();
                        zeros = 0;
                    }
                }
            }
        }

        if (PacketUtil.isFlying(event.getPacketType())) {
            clientTicks++;

            // 30 seconds
            if ((clientTicks - lastAttack) > 20L * 15) {
                return;
            }

            PlayerReachEntity tracked = playerData.getEntityTracker().getTrackedPlayer(targetEntity);
            if (tracked != null) {
                Vector3d oldPlayerPos = new Vector3d(playerData.getPositionTracker().getLastX(), playerData.getPositionTracker().getLastY(), playerData.getPositionTracker().getLastZ());
                Vector3d playerPos = new Vector3d(playerData.getPositionTracker().getX(), playerData.getPositionTracker().getY(), playerData.getPositionTracker().getZ());

                Vector3d trackedPos = new Vector3d(tracked.serverPos.getX(), tracked.serverPos.getY(), tracked.serverPos.getZ());
                double oldDistance = trackedPos.distanceSquared(oldPlayerPos);
                double newDistance = trackedPos.distanceSquared(playerPos);

                // Player is too close (-> causes hitselect to not work)
                if (newDistance < 1.1) {
                    movingAway = true;
                    return;
                }

                if (newDistance > oldDistance) {
                    movingAway = true;
                    return;
                }
            }

            movingAway = false;
            }
        }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_STATUS) {
            WrapperPlayServerEntityStatus wrapper = new WrapperPlayServerEntityStatus(event);
            if (wrapper.getEntityId() == targetEntity && wrapper.getStatus() == 2) {
                event.getTasksAfterSend().add(() -> playerData.getTransactionTracker().sendTransaction());

                playerData.getTransactionTracker().scheduleTrans(1, () -> {
                    lastHurt = clientTicks;
                    checkDelay = true;
                });
            }
        }
    }
}
