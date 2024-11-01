package com.elevatemc.anticheat.base.check.impl.fight.backtrack;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMove;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityRelativeMoveAndRotation;

import java.util.ArrayDeque;
import java.util.Queue;

public class BackTrackC extends PacketCheck {
    private static final int MOVE_SAMPLES = 15;
    private int trackedId = -1;
    private long clientTicks = 0;
    private final Queue<Long> closeTimes = new ArrayDeque<>(MOVE_SAMPLES);
    private final Queue<Long> awayTimes = new ArrayDeque<>(MOVE_SAMPLES);
    public BackTrackC(PlayerData playerData) {
        super(playerData, "Back Track C", "Uses client ticks to detect backtrack", new ViolationHandler(25, 3000L), Category.COMBAT, SubCategory.BACK_TRACK, 10);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                trackedId = wrapper.getEntityId();
            }
        }


        if (PacketUtil.isFlying(event.getPacketType()) && trackedId != -1) {
            clientTicks++;
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE) {
            WrapperPlayServerEntityRelativeMove wrapper = new WrapperPlayServerEntityRelativeMove(event);

            if (playerData.getPlayer().getAllowFlight()) return;

            if (wrapper.getEntityId() == trackedId) {
                processMove(wrapper.getDeltaX(), wrapper.getDeltaY(), wrapper.getDeltaZ());
            }

        } else if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION) {
            WrapperPlayServerEntityRelativeMoveAndRotation wrapper = new WrapperPlayServerEntityRelativeMoveAndRotation(event);

            if (wrapper.getEntityId() == trackedId) {
                processMove(wrapper.getDeltaX(), wrapper.getDeltaY(), wrapper.getDeltaZ());
            }
        }
    }

    public void processMove(double deltaX, double deltaY, double deltaZ) {

        long startTick = clientTicks;

        playerData.getTransactionTracker().confirmPre(() -> {
            long diff = clientTicks - startTick;

            PlayerReachEntity tracked = playerData.getEntityTracker().getTrackedPlayer(trackedId);

            if (tracked == null) return;

            Vector3d playerPos = new Vector3d(playerData.getPositionTracker().getX(), playerData.getPositionTracker().getY(), playerData.getPositionTracker().getZ());
            double oldDistance = tracked.serverPos.distanceSquared(playerPos);
            double newDistance = tracked.serverPos.add(deltaX, deltaY, deltaZ).distanceSquared(playerPos);

            boolean away = newDistance > oldDistance;

            if (oldDistance == newDistance) {
                return;
            }

            if (actionTracker.getLastAttack() > 100) return;

            if (away) {
                if (awayTimes.size() < MOVE_SAMPLES) {
                    awayTimes.add(diff);
                }
            } else {
                if (closeTimes.size() < MOVE_SAMPLES) {
                    closeTimes.add(diff);
                }
            }

            if (awayTimes.size() == MOVE_SAMPLES && closeTimes.size() == MOVE_SAMPLES) {
                int averageTicksAway = (int) MathUtil.getAverage(awayTimes);
                int averageTicksClose = (int) MathUtil.getAverage(closeTimes);

                if (averageTicksAway - averageTicksClose > 2) {
                    if (increaseBuffer() > 1) handleViolation(new DetailedPlayerViolation(this, "DIFF " + (averageTicksAway - averageTicksClose)));
                }

                if (averageTicksClose - averageTicksAway > 4) {
                    handleViolation(new DetailedPlayerViolation(this, "CLOSE " + (averageTicksClose - averageTicksAway)));
                }

                awayTimes.clear();
                closeTimes.clear();

                debug("Away : " + averageTicksAway + " close " + averageTicksClose);
            }
        });
    }
}
