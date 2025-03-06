package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionJ extends PositionCheck {

    public MotionJ(PlayerData playerData) {
        super(playerData, "Motion J", "Invalid jump motion", new ViolationHandler(10, 3000), Category.MOVEMENT, SubCategory.MOTION, 1);
    }

    @Override
    public void handle() {
        double deltaY = positionTracker.getDeltaY();
        double jumpMotion = 0.42F + attributeTracker.getJumpBoost() * 0.1F;

        boolean hasJumped = Math.abs(deltaY - jumpMotion) < 0.001D;
        boolean currentlyTakingKB = velocityTracker.isOnFirstTick();

        if (hasJumped) {
            double expectedY = deltaY - 0.42;

            if (Math.abs(expectedY) < 1.7E-14) {
                handleViolation(new DetailedPlayerViolation(this,"Y " + deltaY + " K " + currentlyTakingKB));
            }
        }
    }
}
