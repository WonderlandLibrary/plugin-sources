package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionO extends PositionCheck {

    public MotionO(PlayerData playerData) {
        super(playerData, "Motion O", "Invalid flight deltas", new ViolationHandler(8, 3000L), Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        // They see me floating....they hating...patrolling and tryna catch me riding dirty.
        boolean invalid = collisionTracker.isInWater() || collisionTracker.isClientGround() || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isInsideVehicle();
        handle_check: {
            if (invalid) break handle_check;
            // they see me floatin....they hating...
            boolean floating = positionTracker.getDeltaY() == 0 && playerData.getPlayer().getVelocity().getY() < -0.4 && positionTracker.getLastDeltaY() == 0 && playerData.getPlayer().getVelocity().getY() < 0.4;

            if (floating) {
                if (increaseBuffer() > 2) {
                    handleViolation(new DetailedPlayerViolation(this, ""));
                    staffAlert(new PlayerViolation(this));
                }
            } else {
                resetBuffer();
            }
        }
    }
}