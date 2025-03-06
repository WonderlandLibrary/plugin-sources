package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class DisablerJ extends PositionCheck {


    public DisablerJ(PlayerData playerData) {
        super(playerData, "Disabler J", "Invalid position values", new ViolationHandler(1, 3000L), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle() {
        double x = positionTracker.getX();
        double y = positionTracker.getY();
        double z = positionTracker.getZ();

        if (Math.max(x, Math.max(y,z)) == Double.MAX_VALUE) {
            handleViolation(new PlayerViolation(this, 2));
            staffAlert(new PlayerViolation(this));
        }
    }
}
