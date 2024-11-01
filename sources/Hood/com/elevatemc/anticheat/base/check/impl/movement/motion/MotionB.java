package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionB extends PositionCheck {
    public MotionB(PlayerData playerData) {
        super(playerData, "Motion B", "Chunk Exploit Check", new ViolationHandler(2, 60L), Category.MOVEMENT, SubCategory.MOTION, 0);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();
        double deltaY = positionTracker.getDeltaY();

        if (deltaXZ > 1.0E10 && Math.abs(deltaY) > 1.0E10) {
            handleViolation(new DetailedPlayerViolation(this,"dXZ=" + format(deltaXZ) + " dY=" + format(Math.abs(deltaY))));
            staffAlert(new PlayerViolation(this));
        }
    }
}