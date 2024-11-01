package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistM extends RotationCheck {

    double lastDeltaY = -1;

    public AimAssistM(PlayerData playerData) {
        super(playerData, "Aim Assist M", "Not constant rotations", new ViolationHandler(8, 3000L),
                Category.COMBAT, SubCategory.AIM_ASSIST, 1);
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

        if (!Double.isNaN(angle) && deltaX > 0.0 && deltaX < 0.2 && deltaY == lastDeltaY && actionTracker.getLastAttack() <= 1) {
            if (increaseBuffer() > 4) {
                handleViolation(new DetailedPlayerViolation(this, "X " + format(deltaX) + " Y " + format(deltaY)));
                decreaseBufferBy(2);
            }
        } else {
            decreaseBuffer();
        }
        lastDeltaY = deltaY;
    }
}
