package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;

public class MotionP extends PacketCheck {

    private double motionX, motionZ;
    public MotionP(PlayerData playerData) {
        super(playerData, "Motion P", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 1);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {

            if (velocityTracker.getTicksSinceVelocity() < 10) return;

            final boolean onGround = collisionTracker.isClientGround();
            final boolean lastOnGround = collisionTracker.isLastClientGround();

            final double deltaX = positionTracker.getDeltaX();
            final double deltaZ = positionTracker.getDeltaZ();

            final double deltaXZ = positionTracker.getDeltaXZ();

            float friction = 0.91F;
            if (lastOnGround) friction *= 0.6F;

            double movementSpeed = attributeTracker.getMoveSpeed(true);

            if (lastOnGround) {
                movementSpeed *= 0.16277136F / (friction * friction * friction);

                if (!onGround && positionTracker.isJumped()) {
                    movementSpeed += 0.2D;
                }
            } else {
                movementSpeed = (float) ((double) 0.02F + (double) 0.02F * 0.3D);
            }

            final double acceleration = (deltaXZ - (MathUtil.hypot(this.motionX, this.motionZ))) / movementSpeed;

            final boolean exempt = teleportTracker.isTeleport()
            || collisionTracker.isNearPiston() || collisionTracker.isInWater()
            || collisionTracker.isInWeb() || playerData.getPlayer().getAllowFlight()
            || playerData.getPlayer().isInsideVehicle()
            || !positionTracker.isInLoadedChunk();

            final boolean invalid = acceleration > 1.0D + 1.0E-6 && deltaXZ > 0.2D;

            if (invalid && !exempt) {
                final boolean certain = velocityTracker.getTicksSinceVelocity() > 5;
                final double increment = Math.min(certain ? 10.0D : 8.0D, Math.max(2.5D, acceleration));

                if (increaseBufferBy(increment) > 25) {
                    handleViolation(new DetailedPlayerViolation(this, "A " + format(acceleration)));
                }
            } else {
               decreaseBufferBy(0.5D);
            }

            // Update motion values
            this.motionX = deltaX * friction;
            this.motionZ = deltaZ * friction;

            // Low motion
            if (Math.abs(this.motionX) < 0.005D) this.motionX = 0.0D;
            if (Math.abs(this.motionZ) < 0.005D) this.motionZ = 0.0D;
        }
    }
}