package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionR extends PositionCheck {

    public MotionR(PlayerData playerData) {
        super(playerData, "Motion R", "No Slow", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 1);
    }
    @Override
    public void handle() {
        int groundTicks = collisionTracker.getClientGroundTicks();
        int sprintingTicks = actionTracker.getSprintingTicks();
        int sneakingTicks = actionTracker.getSneakingTicks();

        boolean exempt = groundTicks < 10;

        boolean invalid = sprintingTicks > 10 && sneakingTicks > 10;
        if (invalid && !exempt && positionTracker.isInLoadedChunk()) {
            if (increaseBuffer() > 13) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            resetBuffer();
        }
    }
}
