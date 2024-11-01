package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class SpeedD extends PositionCheck {
    public SpeedD(PlayerData playerData) {
        super(playerData, "Speed D", "Invalid speed", new ViolationHandler(15, 3000L), Category.MOVEMENT, SubCategory.SPEED,3);
    }

    @Override
    public void handle() {

        boolean exempt = teleportTracker.isTeleport() || Hood.get().isLagging()
                || collisionTracker.isOnClimbable() || collisionTracker.isLastOnClimbable() || collisionTracker.isOnIce() || collisionTracker.isLastOnIce()
                || collisionTracker.isOnSlime() || collisionTracker.isLastOnSlime()
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isInsideVehicle();
        if (!positionTracker.isSentMotion()) return;
        if (exempt) return;
        boolean underBlock = collisionTracker.isVerticallyColliding();
        boolean velocity = velocityTracker.getTicksSinceVelocity() < 20;
        boolean onGround = collisionTracker.isClientGround() || collisionTracker.isServerGround();

        double deltaXZ = positionTracker.getDeltaXZ();

        double speedLimit = getBaseSpeed(playerData);

        int airTicks = collisionTracker.getClientAirTicks();

        if (underBlock) {
            speedLimit += 0.91;
        }

        if (velocity) {
            speedLimit += Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ()) + 0.15;
        }

        if (!onGround) {
            speedLimit += 0.91F;
        }

        if (collisionTracker.isInWater() || collisionTracker.isInLava()) {
            speedLimit += 0.01;
        }

        if (airTicks > 5 && deltaXZ > speedLimit) {
            if (increaseBuffer() > 6.0) {
                handleViolation(new DetailedPlayerViolation(this,"D " + format(deltaXZ)));
                multiplyBuffer(.75);
            }
        } else {
            decreaseBuffer();
        }
    }
    public static float getBaseSpeed(final PlayerData data) {
        return (float) (0.34f + (data.getAttributeTracker().getSpeedBoost() * 0.062f) + (data.getAttributeTracker().getWalkSpeed() - 0.2f) * 1.6f);
    }
}
