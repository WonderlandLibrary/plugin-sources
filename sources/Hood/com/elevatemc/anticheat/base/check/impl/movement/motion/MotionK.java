package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionK extends PositionCheck {


    public MotionK(PlayerData playerData) {
        super(playerData, "Motion K", "Invalid received velocity.", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 50);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport() || playerData.getPlayer().getAllowFlight() || collisionTracker.isVerticallyColliding()
                || collisionTracker.isOnStairs() || collisionTracker.isInWeb() || collisionTracker.isOnClimbable() || collisionTracker.isOnSlabs();

        if (exempt) return;
        if (positionTracker.isPossiblyZeroThree()) return;

        if (velocityTracker.isOnFirstTick() && !(attributeTracker.getJumpBoost() > 0)) {
            double velocityX = velocityTracker.peekVelocity().getX();
            double velocityZ = velocityTracker.peekVelocity().getZ();
            double expectedMotion = Math.sqrt((velocityX * velocityX) + (velocityZ * velocityZ));

            if (Math.abs(velocityX) < 0.005 || Math.abs(velocityZ) < 0.005) return;

            double deltaXZ = positionTracker.getDeltaXZ();

            if ((Math.round(deltaXZ * 100D) / 100D) > Math.round(expectedMotion * 100D) / 100D) {
                if (increaseBuffer() > 15) {
                   handleViolation(new DetailedPlayerViolation(this, "E " + format(Math.round(expectedMotion * 100D) / 100D) + " R " + format(Math.round(deltaXZ * 100D) / 100D)));
                }
            } else {
                decreaseBufferBy(2.5);
            }
        }
    }
}
