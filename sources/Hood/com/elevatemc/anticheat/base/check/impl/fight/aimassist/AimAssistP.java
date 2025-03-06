package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistP extends RotationCheck {

    double lastDeltaY = -1, lastDeltaX = -1;

    public AimAssistP(PlayerData playerData) {
        super(playerData, "Aim Assist P", "Not constant rotations", new ViolationHandler(5,
                30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 4 || rotationTracker.isZooming()) {
            return;
        }

        float currentYaw = rotationTracker.getYaw();
        float currentPitch = rotationTracker.getPitch();

        float f = rotationTracker.getSensitivity() / 142.0F;

        double adjustedYaw = currentYaw - (currentYaw % f);
        double adjustedPitch = currentPitch - (currentPitch % f);

        double deltaX = Math.abs(adjustedYaw - currentYaw);
        double deltaY = Math.abs(adjustedPitch - currentPitch);

        double differenceX = Math.abs(deltaX - lastDeltaX);
        double differenceY = Math.abs(deltaY - lastDeltaY);

        if (MathUtil.isScientificNotation(differenceY) && differenceX > 0.001) {
            if (increaseBuffer() > 8) {
                handleViolation(new DetailedPlayerViolation(this, "X " + differenceX + " Y " + differenceY));
            }
        } else {
            decreaseBufferBy(1.5);
        }
        lastDeltaX = deltaX;
        lastDeltaY = deltaY;
    }
}

