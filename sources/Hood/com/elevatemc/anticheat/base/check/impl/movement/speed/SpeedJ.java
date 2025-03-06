package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;

public class SpeedJ extends PositionCheck {

    private double lastAirLimit = -1;
    public SpeedJ(PlayerData playerData) {
        super(playerData, "Speed J", "", new ViolationHandler(10, 300), Category.MOVEMENT, SubCategory.SPEED,2);
    }

    @Override
    public void handle() {
        boolean sprinting = actionTracker.isSprinting();

        double lastDeltaX = positionTracker.getLastDeltaX();
        double lastDeltaZ = positionTracker.getLastDeltaZ();

        double deltaXZ = positionTracker.getDeltaXZ();
        double deltaY = positionTracker.getDeltaY();

        int airTicks = collisionTracker.getClientAirTicks();

        float jumpMotion = 0.42f + attributeTracker.getJumpBoost() * 0.1F;

        double airLimit = PlayerUtil.getBaseSpeed(playerData);

        if (Math.abs(deltaY - jumpMotion) < 1.0E-4 && airTicks == 1 && sprinting) {
            float f = rotationTracker.getYaw() * 0.017453292f;

            double x = lastDeltaX - Math.sin(f) * 0.2D;
            double z = lastDeltaZ + Math.cos(f) * 0.2D;
            airLimit += Math.hypot(x, z);
        }
        if (collisionTracker.isOnIce() || collisionTracker.isOnSlime()) {
            airLimit += 0.34D;
        }
        if (collisionTracker.isNearWall()) {
            airLimit += 0.91F;
        }
        if (attributeTracker.getWalkSpeed() > 0.3 && collisionTracker.isClientGround()) {
            return;
        }
        if (velocityTracker.isOnFirstTick()) {
            airLimit += Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ()) + 0.15;
        }
        boolean exempt = playerData.getPlayer().isInsideVehicle()
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isDead()
                || collisionTracker.isInLava()
                || collisionTracker.isInWater()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isOnIce()
                || collisionTracker.isLastOnIce()
                || collisionTracker.isVerticallyColliding()
                || collisionTracker.isLastVerticallyColliding()
                || Hood.get().isLagging();
        boolean falling = playerData.getPlayer().getFallDistance() > 9.0F;
        if (!positionTracker.isSentMotion()) return;

        if (!exempt && !falling) {
            if (airTicks > 10) {
                if (deltaXZ > airLimit && (airLimit != 0.34D) && airLimit != lastAirLimit) {
                    if (increaseBuffer() > 15.0) {
                        handleViolation(new DetailedPlayerViolation(this,"L " + airLimit));
                    }
                } else {
                    decreaseBufferBy(1.15);
                }
            }
        }
        lastAirLimit = airLimit;

    }
}
