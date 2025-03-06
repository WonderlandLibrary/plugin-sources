package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class PredictionD extends PositionCheck {
    public PredictionD(PlayerData playerData) {
        super(playerData, "No Slow B", "Checks for no slow", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle() {

        if (playerData.getPlayer().getAllowFlight() || playerData.getPlayer().isFlying() || playerData.getPlayer().isInsideVehicle()) return;

        double level = playerData.getPlayer().getFoodLevel();

        if (level < 6 && playerData.getPlayer().isSprinting()) {
            if (increaseBuffer() > 2) handleViolation(new DetailedPlayerViolation(this, "L " + level + " D " + positionTracker.getDeltaXZ() + " S true"));
        }
    }
}
