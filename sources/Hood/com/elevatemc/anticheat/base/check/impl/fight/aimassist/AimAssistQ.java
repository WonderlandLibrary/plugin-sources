package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistQ extends RotationCheck {

    int lastSensitivity;

    public AimAssistQ(PlayerData playerData) {
        super(playerData, "Aim Assist Q", "HMM", new ViolationHandler(10, 30000L),
                Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5) {
            int sensitivity = rotationTracker.getSensitivity();

            float yawAccel = rotationTracker.getYawAccel();
            float deltaPitch = rotationTracker.getDeltaPitch();
            float deltaYaw = rotationTracker.getDeltaYaw();

            if (deltaPitch != 0.0f && sensitivity != lastSensitivity && yawAccel <= 30 && deltaYaw > 2.f) {
                if (increaseBuffer() > 6) {
                    handleViolation(new DetailedPlayerViolation(this, "P " + format(deltaPitch) + " S|L " + sensitivity + " , " + lastSensitivity));
                }
            } else {
                decreaseBufferBy(2);
            }
            lastSensitivity = sensitivity;
        }
    }
}