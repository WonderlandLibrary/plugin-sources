package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionC extends PositionCheck {
    public MotionC(PlayerData playerData) {
        super(playerData, "Motion C", "Large Y Check", new ViolationHandler(1, 5000L), Category.MOVEMENT, SubCategory.MOTION, 0);
    }

    @Override
    public void handle() {
        if (Math.abs(positionTracker.getY()) > 1.0E9) {
            handleViolation(new DetailedPlayerViolation(this, "Y " + Math.abs(positionTracker.getY())));
        }
    }
}