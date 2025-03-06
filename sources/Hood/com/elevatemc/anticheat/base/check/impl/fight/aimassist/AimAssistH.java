package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimAssistH extends RotationCheck {
    public AimAssistH(PlayerData playerData) {
        super(playerData, "Aim Assist H", "Subtle aim assist modifications", new ViolationHandler(2,
                3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 4 || !rotationTracker.isZooming()) {
            final boolean validSensitivity = rotationTracker.getSensitivity() > 0 && rotationTracker.getSensitivity() < 200;
            if (validSensitivity) {

                double mcpSensitivity = rotationTracker.getMcpSensitivity();

                float f = (float) mcpSensitivity * 0.6f + 0.2f;
                float gcd = f * f * f * 1.2f;

                float deltaYaw = rotationTracker.getDeltaYaw();
                float deltaPitch = rotationTracker.getDeltaPitch();

                double deltaX = deltaYaw / gcd;
                double deltaY = deltaPitch / gcd;

                double floorDivisorX = Math.abs(Math.round(deltaX) - deltaX);
                double floorDivisorY = Math.abs(Math.round(deltaY) - deltaY);

                if (floorDivisorY > 0.03 && floorDivisorX < 1.0E-4) {
                    if (increaseBuffer() > 25.0) {
                        multiplyBuffer(0.25);
                        handleViolation(new DetailedPlayerViolation(this, String.format("Y %s X %s", format(floorDivisorY), format(floorDivisorX))));
                        if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                    }
                } else {
                    decreaseBufferBy(1.25);
                }
            }
        }
    }
}
