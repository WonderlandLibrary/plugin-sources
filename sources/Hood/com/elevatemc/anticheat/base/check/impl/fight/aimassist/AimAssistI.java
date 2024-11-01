package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistI extends RotationCheck {
    public AimAssistI(PlayerData playerData) {
        super(playerData, "Aim Assist I", "Subtle aim assist modifications", new ViolationHandler(8,
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

                float deltaYaw = rotationTracker.getDeltaYaw();
                float deltaPitch = rotationTracker.getDeltaPitch();

                double deltaX = deltaYaw / gcd;
                double deltaY = deltaPitch / gcd;

                double floorDivisorX = Math.abs(Math.round(deltaX) - deltaX);
                double floorDivisorY = Math.abs(Math.round(deltaY) - deltaY);

                if (floorDivisorX > 0.03 && floorDivisorY < 1.0E-4) {
                    if (increaseBuffer() > 25.0) {
                        multiplyBuffer(0.25);
                        handleViolation(new DetailedPlayerViolation(this, String.format("X %s Y %s", format(floorDivisorX), format(floorDivisorY))));
                    }
                } else {
                    decreaseBufferBy(1.25);
                }
            }
        }
    }
}
