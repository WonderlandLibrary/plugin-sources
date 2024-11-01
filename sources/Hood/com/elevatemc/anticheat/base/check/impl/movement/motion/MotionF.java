package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionF extends PositionCheck {
    public MotionF(PlayerData playerData) {
        super(playerData, "Motion F", "Invalid deltas", new ViolationHandler(10, 300000L), Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        boolean exempt = collisionTracker.isVerticallyColliding() || collisionTracker.isOnSlime();
        boolean inLiquid = collisionTracker.isInWater() || collisionTracker.isInLava();

        int airTicks = collisionTracker.getClientAirTicks();
        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        if (collisionTracker.isVerticallyColliding() || collisionTracker.isLastVerticallyColliding()) {
            resetBuffer();
            return;
        }

        if (airTicks == 1 && !exempt && !inLiquid && deltaY > 0) {
            double limit = 0.41999998688697815;

            limit += (float) attributeTracker.getJumpBoost() * 0.1F;

            limit -= velocityTracker.peekVelocity() == null ? 0 : (float) velocityTracker.peekVelocity().getY();


            if (deltaY < limit && deltaY != lastDeltaY) {
                if (increaseBuffer() > 20){
                    handleViolation(new DetailedPlayerViolation(this,"dY=" + deltaY));
                }
            } else if (deltaY >= limit) {
                multiplyBuffer(0.98);
            } else {
                decreaseBuffer();
            }
        }
    }
}
