package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.box.SimpleCollisionBox;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.google.common.collect.Lists;
import net.minecraft.server.v1_8_R3.MathHelper;

import java.util.Deque;
public class AimAnalyticsR extends PacketCheck {

    private final Deque<Float> offsets = Lists.newLinkedList(), deltas = Lists.newLinkedList();

    private int id = -1;
    public AimAnalyticsR(PlayerData playerData) {
        super(playerData, "Analytics R", "Improper rotations", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }
    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity entity = new WrapperPlayClientInteractEntity(event);

            this.id = entity.getEntityId();
        } else if (PacketUtil.isFlying(event.getPacketType())) {
            PlayerReachEntity target = entityTracker.getTrackedPlayer(id);
            if (target != null) {
                // Clamped yaw.
                final float yaw = MathHelper.g(rotationTracker.getYaw());

                SimpleCollisionBox targetBox = target.getPossibleCollisionBoxes();
                double entityPosX = (targetBox.copy().maxX + targetBox.copy().minX) / 2.0;
                double entityPosZ = (targetBox.copy().maxZ + targetBox.copy().minZ) / 2.0;

                double distanceX = entityPosX - positionTracker.getLastX();
                double distanceZ = entityPosZ - positionTracker.getLastZ();

                final float generated =  MathHelper.g((float)(Math.toDegrees(Math.atan2(distanceZ, distanceX)) - 90.0));

                offsets.add(Math.abs(yaw - generated));
                deltas.add(rotationTracker.getDeltaYaw());

                if (offsets.size() + deltas.size() == 60) {
                    double averageDelta = MathUtil.getMean(deltas);
                    double averageOffset = MathUtil.getMean(offsets);

                    double deltasDeviation = MathUtil.getStandardDeviation(deltas);
                    double offsetsDeviation = MathUtil.getStandardDeviation(offsets);

                    debug("offset D (" + averageDelta +") O (" + averageOffset + ") D (" + deltasDeviation + "| " + offsetsDeviation + ")") ;

                    if (averageDelta > 2.5 && averageOffset < 3.0 && deltasDeviation > 1.0 && offsetsDeviation < 3.0) {
                        if (increaseBuffer() > 3) {
                            debug("offset failed");
                            handleViolation(new DetailedPlayerViolation(this, ""));
                        }
                    } else {
                        decreaseBufferBy(0.25D);
                    }
                    offsets.clear();
                    deltas.clear();
                }
            }
        }
    }
}
