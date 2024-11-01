package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistJ2 extends RotationCheck {

    private float lastCombined;
    public AimAssistJ2(PlayerData playerData) {
        super(playerData, "Aim Assist J2", "Subtle aim assist modifications", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 10 && !rotationTracker.isZooming()) {
            final boolean validSensitivity = rotationTracker.getSensitivity() > 0 && rotationTracker.getSensitivity() < 200;
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

                boolean exempt = teleportTracker.isTeleport();

                float combined = yawDifference + pitchDifference;
                float diff = combined - lastCombined;

                if (combined < 0.005 && (diff == 0.0 || MathUtil.isScientificNotation(diff)) && !exempt) {
                    if (increaseBuffer() > 7) {
                        handleViolation(new DetailedPlayerViolation(this, "C " + combined + " D " + format(diff)));
                    }
                } else {
                    decreaseBufferBy(2.5);
                }
                debug("combined " + combined + " diff " + diff);

                lastCombined = combined;
            }
        }
    }
}