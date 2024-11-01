package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistR extends RotationCheck {

    public AimAssistR(PlayerData playerData) {
        super(playerData, "Aim Assist R", "", new ViolationHandler(10, 3000L),
                Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 4 && !rotationTracker.isZooming()) {
            final boolean validSensitivity = rotationTracker.getSensitivity() != -1;

            if (validSensitivity) {

                double mcpSensitivity = rotationTracker.getMcpSensitivity();

                float f = (float) mcpSensitivity * 0.6f + 0.2f;
                float gcd = f * f * f * 1.2f;

                float deltaYaw = rotationTracker.getDeltaYaw();
                float deltaPitch = rotationTracker.getDeltaPitch();

                float moduloX = deltaYaw % gcd;
                float moduloY = deltaPitch % gcd;

                if (MathUtil.isScientificNotation(moduloX) && moduloY == 0.0) {
                    if (increaseBuffer() > 10) {
                        handleViolation(new DetailedPlayerViolation(this, "Y 0 X " + moduloX));
                        setBuffer(5);
                    }
                } else {
                    decreaseBuffer();
                }

            }
        }
    }
}
