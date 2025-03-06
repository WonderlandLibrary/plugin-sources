package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
public class AimAssistJ extends RotationCheck {

    public AimAssistJ(PlayerData playerData) {
        super(playerData, "Aim Assist J", "GCD Bypass flaw detected", new ViolationHandler(3,
                5000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
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

                if ((yawDifference == 0.0f || pitchDifference == 0.0f) && !exempt) {
                    if (increaseBuffer() > 12.0) {
                        multiplyBuffer(0.25);
                        handleViolation(new DetailedPlayerViolation(this, "Y " + format(yawDifference) + " P " + pitchDifference));
                    }
                } else {
                    decreaseBufferBy(.2);
                }
            }
        }
    }
}
