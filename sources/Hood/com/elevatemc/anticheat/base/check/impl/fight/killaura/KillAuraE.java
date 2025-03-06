package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class KillAuraE extends RotationCheck {
    public KillAuraE(PlayerData playerData) {
        super(playerData, "Aura E", "GCD bypass flaw detected", new ViolationHandler(3, 3000L),
                Category.COMBAT, SubCategory.KILL_AURA,0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 8) {
            boolean validSensitivity = rotationTracker.getSensitivity() > 0 && rotationTracker.getSensitivity() < 200;

            if (validSensitivity) {
                double mcpSensitivity = rotationTracker.getMcpSensitivity();

                float f = (float) mcpSensitivity * 0.6f + 0.2f;

                float gcd = f * f * f * 1.2f;
                float yaw = rotationTracker.getYaw();
                float pitch = rotationTracker.getPitch();

                float adjustedYaw = yaw - yaw % gcd;
                float adjustedPitch = pitch - pitch % gcd;

                float yawDifference = Math.abs(yaw - adjustedYaw);
                float pitchDifference = Math.abs(pitch - adjustedPitch);

                float deltaYaw = rotationTracker.getDeltaYaw();
                float deltaPitch = rotationTracker.getDeltaPitch();

                float combined = deltaYaw + deltaPitch;

                if (MathUtil.isScientificNotation(yawDifference) && pitchDifference == 0.0f) {
                    if (increaseBuffer() > 6.0) {
                        multiplyBuffer(0.25);
                        handleViolation(new DetailedPlayerViolation(this, "Y " + format(yawDifference) + " P " + format(pitchDifference) + " C " + format(combined)));
                    }
                } else {
                    decreaseBufferBy(.2);
                }
            }
        }
    }
}