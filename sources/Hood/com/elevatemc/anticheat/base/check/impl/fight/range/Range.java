package com.elevatemc.anticheat.base.check.impl.fight.range;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.reach.box.SimpleCollisionBox;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.elevatemc.anticheat.util.reach.data.ReachUtils;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Range extends PacketCheck {
    private final Map<Integer, Vector3d> playerAttackQueue = new LinkedHashMap<>();
    private int sinceNoMove = 0;

    private static final double[] eyeHeights = new double[] {(double) (1.62f - 0.08f), (double) (1.62f)};

    public Range(PlayerData playerData) {
        super(playerData, "Range", "Modification Of Attack Distance Check", new ViolationHandler(25, 30000L), Category.COMBAT, SubCategory.REACH, 2);
    }


    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            final WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            Entity target = SpigotReflectionUtil.getEntityById(wrapper.getEntityId());

            if (playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE) || playerData.getPlayer().isInsideVehicle() || target instanceof Player && target.isInsideVehicle()) {
                return;
            }

            if (wrapper.getAction().equals(WrapperPlayClientInteractEntity.InteractAction.ATTACK)) {
                int entityId = wrapper.getEntityId();
                if (entityTracker.playerMap.containsKey(entityId)) {
                    playerAttackQueue.put(entityId, new Vector3d(positionTracker.getX(), positionTracker.getY(), positionTracker.getZ())); // Queue for next tick for very precise check
                }
            }
        } else if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            boolean isNotMoving = event.getPacketType() == PacketType.Play.Client.PLAYER_ROTATION || event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING;
            if (isNotMoving) {
                sinceNoMove = 0;
            } else {
                sinceNoMove++;
            }

            if (!teleportTracker.isTeleport()) {
                this.tickFlying();
            } else {
                playerAttackQueue.clear();
            }
        }
    }

    public void tickFlying() {
        for (Map.Entry<Integer, Vector3d> attack : playerAttackQueue.entrySet()) {
            final PlayerReachEntity reachEntity = entityTracker.playerMap.get(attack.getKey());
            final SimpleCollisionBox targetBox = reachEntity.getPossibleCollisionBoxes();

            targetBox.expand(0.10249999910593033); // 1.8 / 1.7 hitbox is 0.1 bigger

            if (!positionTracker.isMoved()) {
                targetBox.expand(0.03);
            }

            final Vector3d from = attack.getValue();
            double minDistance = Double.MAX_VALUE;
            final List<Vector> possibleLookDirs = new ArrayList<>();

            possibleLookDirs.add(ReachUtils.getLook(rotationTracker.getYaw(), rotationTracker.getPitch(), false, playerData.getClientVersion()));
            possibleLookDirs.add(ReachUtils.getLook(rotationTracker.getYaw(), rotationTracker.getPitch(), true, playerData.getClientVersion()));
            possibleLookDirs.add(ReachUtils.getLook(rotationTracker.getLastYaw(), rotationTracker.getPitch(), false, playerData.getClientVersion()));
            possibleLookDirs.add(ReachUtils.getLook(rotationTracker.getLastYaw(), rotationTracker.getPitch(), true, playerData.getClientVersion()));

            for (final Vector lookVec : possibleLookDirs) {
                for (final double eye : eyeHeights) {
                    Vector eyePos = new Vector(from.getX(), from.getY() + eye, from.getZ());
                    Vector endReachPos = eyePos.clone().add(new Vector(lookVec.getX() * 6.0D, lookVec.getY() * 6.0D, lookVec.getZ() * 6.0D));

                    Vector intercept = ReachUtils.calculateIntercept(targetBox, eyePos, endReachPos).getX();

                    if (ReachUtils.isVecInside(targetBox, eyePos)) {
                        minDistance = 0;
                        break;
                    }

                    if (intercept != null) {
                        minDistance = Math.min(eyePos.distance(intercept), minDistance);
                    }
                }
            }

            if (minDistance == Double.MAX_VALUE) {
            } else if (minDistance > 3.005) {
                handleViolation(new DetailedPlayerViolation(this,"D " + format(minDistance) + " T " + sinceNoMove));
                staffAlert(new PlayerViolation(this));
            }
        }
        playerAttackQueue.clear();
    }
}