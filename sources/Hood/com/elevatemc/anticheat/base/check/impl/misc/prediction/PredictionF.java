package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class PredictionF extends PositionCheck {

    public PredictionF(PlayerData playerData) {
        super(playerData, "", "", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 1) {
            return;
        }

    }
}