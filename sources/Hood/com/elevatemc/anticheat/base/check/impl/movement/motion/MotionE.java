package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionE extends PositionCheck {

    public MotionE(PlayerData playerData) {
        super(playerData, "Motion E", "Fast ladder", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        boolean exempt = playerData.getPlayer().getAllowFlight()
                || Hood.get().isLagging()
                || playerData.getPlayer().isInsideVehicle();
        boolean valid = collisionTracker.isOnLadder();
        boolean invalidDeltas = positionTracker.getDeltaY() == positionTracker.getLastDeltaY();

        if (!exempt && !invalidDeltas && valid) {
            double deltaY = positionTracker.getDeltaY();

            if ((float) deltaY > 0.1178) {
                if (increaseBuffer() > 5.0) {
                    handleViolation(new DetailedPlayerViolation(this,"Y " + format(deltaY)));
                    multiplyBuffer(.5);
                }
            } else {
                decreaseBufferBy(.25);
            }
        }
    }
}