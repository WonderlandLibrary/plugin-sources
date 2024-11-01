package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionD extends PositionCheck {

    double velocityY;
    public MotionD(PlayerData playerData) {
        super(playerData, "Motion D", "Fast Y axis acceleration", new ViolationHandler(15, 300L), Category.MOVEMENT, SubCategory.MOTION, 0);
    }

    @Override
    public void handle() {

        boolean exempt = playerData.getPlayer().getAllowFlight()
                ||collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isClientGround()
                || teleportTracker.isTeleport();

        if (!exempt) {
            double max = 0.7 + attributeTracker.getJumpBoost() * 0.1;

            double deltaY = positionTracker.getDeltaY();
            if (velocityTracker.isOnFirstTick()) {
                velocityY = velocityTracker.peekVelocity().getY();
            }


            if (deltaY != 1.1199999749660492) return;

            if (velocityTracker.getTicksSinceVelocity() <= 5) {
                max += Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ());
            }

            if (deltaY > max && velocityY < -0.075) {
                handleViolation(new DetailedPlayerViolation(this,"Y " + format(deltaY)));
            }
        }
    }
}