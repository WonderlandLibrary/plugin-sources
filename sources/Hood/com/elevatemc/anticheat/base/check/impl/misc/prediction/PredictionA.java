package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class PredictionA extends PositionCheck {

    public PredictionA(PlayerData playerData) {
        super(playerData, "No Slow A", "Checks for no slow", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle() {

        boolean usingItem = predictionTracker.isUsingItem();
        boolean sprinting = actionTracker.isSprinting();
        boolean blocking = actionTracker.isBlocking();
        boolean player = playerData.getPlayer().isBlocking();

        float moveForward = predictionTracker.getMoveForward();
        float moveStrafe = predictionTracker.getMoveStrafe();

        double deltaXZ = positionTracker.getDeltaXZ();

        if (usingItem && player && !blocking && (Math.abs(moveStrafe) > 0.2 || Math.abs(moveForward) != 0.0 && Math.abs(moveStrafe) != 0.0) && deltaXZ > 0.15) {
            if (increaseBuffer() > 5) {
                handleViolation(new DetailedPlayerViolation(this, ""));
            }
        } else {
            resetBuffer();
        }
        debug("Using " + usingItem + " sprinting " + sprinting + " deltaXZ " + format(deltaXZ) + " blocking (A|P) " + blocking + player + " (S|F) (" + moveStrafe + " | " + moveForward + ")");
    }
}
