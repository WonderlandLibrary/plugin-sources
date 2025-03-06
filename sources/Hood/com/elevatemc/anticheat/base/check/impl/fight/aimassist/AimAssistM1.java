package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistM1 extends RotationCheck {

    double lastDeltaY = -1;
    double otherBuffer = 0;

    public AimAssistM1(PlayerData playerData) {
        super(playerData, "Aim Assist M1", "Not constant rotations", new ViolationHandler(10,
                3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
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
        double angle = rotationTracker.getAngle();

        boolean critting = actionTracker.getLastAttack() < 4 && positionTracker.isJumped();

        if (critting) return;

        if (!Double.isNaN(angle) && (MathUtil.isScientificNotation(deltaX) && deltaY == lastDeltaY)) {
            if (increaseBuffer() > 2.5) {
                handleViolation(new DetailedPlayerViolation(this, "X " + deltaX + "  Y " + deltaY));
            }
        } else {
            decreaseBufferBy(0.025);
        }
        lastDeltaY = deltaY;
    }
}
