package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimAssistJ1 extends RotationCheck {
    public AimAssistJ1(PlayerData playerData) {
        super(playerData, "Aim Assist J1", "Subtle aim assist modifications", new ViolationHandler(8,
                3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5 && !rotationTracker.isZooming()) {
            final boolean validSensitivity = rotationTracker.getSensitivity() != -1;
            if (validSensitivity) {

                double mcpSensitivity = rotationTracker.getMcpSensitivity();

                float f = (float) mcpSensitivity * 0.6f + 0.2f;
                float gcd = f * f * f * 1.2f;

                float currentYaw = rotationTracker.getYaw() * 180.0f;
                float pitch = rotationTracker.getPitch();

                float adjustedYaw = currentYaw - currentYaw % gcd;
                float adjustedPitch = pitch - pitch % gcd;

                float yawDifference = Math.abs(currentYaw - adjustedYaw);
                float pitchDifference = Math.abs(pitch - adjustedPitch);

                float gcdYawDifference = Math.abs(gcd - yawDifference);
                float gcdPitchDifference = Math.abs(gcd - pitchDifference);

                double moduloX = gcdYawDifference % 1.0E-3;
                double moduloY = gcdPitchDifference % 1.0E-3;

                if (moduloX < 1.0E-6 && moduloY < 1.0E-6) {
                    if (increaseBuffer() > 10) {
                        handleViolation(new DetailedPlayerViolation(this, "X " + moduloX + " Y " + moduloY));
                        if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                        multiplyBuffer(0.5);
                    }
                } else {
                    decreaseBufferBy(0.175);
                }
            }
        }
    }
}