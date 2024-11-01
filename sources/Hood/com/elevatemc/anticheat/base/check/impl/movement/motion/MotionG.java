package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionG extends PositionCheck {

    public MotionG(PlayerData playerData) {
        super(playerData, "Motion G", "Invalid motion",ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 0);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport()
                || collisionTracker.isVerticallyColliding()
                || velocityTracker.isOnFirstTick()
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isInsideVehicle()
                || teleportTracker.isTeleport()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || playerData.getPlayer().isDead();

        if (!exempt) {
            double deltaY = positionTracker.getDeltaY(), lastDeltaY = positionTracker.getLastDeltaY();

            double yDifference = Math.abs(deltaY - lastDeltaY);

            //debug("dY=" + deltaY);
            //debug("diff=" + yDifference);

            boolean invalid = deltaY == yDifference && deltaY > 1.0 && attributeTracker.getJumpBoost() < 2;

            if (invalid) {
                if (increaseBuffer() > 4.0) {
                    handleViolation(new DetailedPlayerViolation(this,"Y " + deltaY + " DIFF " + yDifference));
                    resetBuffer();
                }
            }
        }
    }
}
