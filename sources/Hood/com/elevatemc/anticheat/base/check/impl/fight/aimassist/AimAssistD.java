package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistD extends RotationCheck {
    public AimAssistD(PlayerData playerData) {
        super(playerData, "Aim Assist D", "Invalid Yaw and Pitch Changes Check",
                new ViolationHandler(15, 300L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 20) {
            return;
        }

        float var3 = 0.512F;
        float var4 = var3 * var3 * var3 * 8.0F;

        float expectedYaw = rotationTracker.getDeltaYaw() * var4 + (float) (rotationTracker.getDeltaYaw() + 0.15);
        float expectedPitch = rotationTracker.getDeltaPitch() * var4 + (float) (rotationTracker.getDeltaPitch() - 0.15);

        double pitchDiff = Math.abs(rotationTracker.getDeltaPitch() - expectedPitch);
        double pitch = Math.abs(rotationTracker.getDeltaPitch() - pitchDiff);

        float yawDiff = Math.abs(rotationTracker.getDeltaYaw() - expectedYaw);
        float yaw = Math.abs(rotationTracker.getDeltaYaw() - yawDiff);

        if (yaw > 1 && pitch > 2) {
            if (increaseBuffer() > 6) {
                handleViolation(new DetailedPlayerViolation(this, ""));
            }
        } else {
            decreaseBufferBy(0.05);
        }
    }
}