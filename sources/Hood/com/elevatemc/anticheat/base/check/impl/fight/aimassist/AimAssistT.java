package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistT extends RotationCheck {

    public AimAssistT(PlayerData playerData) {
        super(playerData, "Aim Assist T", "Resulted and combined are equal to 0", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10) return;

        float deltaYaw = rotationTracker.getDeltaYaw();

        if (deltaYaw < 200 && !teleportTracker.isTeleport()) {

            double ySensitivity = rotationTracker.getMcpSensitivity();

            float f = (float) ySensitivity * 0.6f + 0.2f;
            float gcd = f * f * f * 1.2f;

            float yaw = rotationTracker.getYaw();

            yaw -= yaw % gcd;

            float result = Math.abs(yaw % gcd);
            float delta = Math.abs(result - gcd);

            float combined = result + delta;

            if (combined <= 0.005 && !teleportTracker.isTeleport()) {
                if (increaseBuffer() > 5) {
                    handleViolation(new DetailedPlayerViolation(this, "C " + combined));
                    resetBuffer();
                }
            } else {
                decreaseBuffer();
            }
        }
    }
}
