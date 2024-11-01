package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
public class AimAssistS extends RotationCheck {

    public AimAssistS(PlayerData playerData) {
        super(playerData, "Aim Assist S", "Invalid acceleration change in combat.", new ViolationHandler(20,
                30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (rotationTracker.getTicksSinceRotation() < 40 && actionTracker.getLastAttack() < 10) {
            double mcpSens = rotationTracker.getMcpSensitivity();
            int sensitivity = rotationTracker.getSensitivity();

            boolean validRotation = sensitivity != -1 && mcpSens > 0.0 && mcpSens < 1.0;

            if (validRotation) {
                double pitchAccel = rotationTracker.getPitchAccel();
                double yawAccel = rotationTracker.getYawAccel();
                double pitch = rotationTracker.getPitch();

                boolean areAccelerationsAlike = Math.abs(pitchAccel - yawAccel) < 0;
                boolean pitchCondition = pitch != rotationTracker.getLastPitch() && Math.abs(pitch) < 5;

                if (areAccelerationsAlike && pitchCondition) {
                    if (increaseBuffer() > 2) {
                        handleViolation(new DetailedPlayerViolation(this, "D " + Math.abs(pitchAccel - yawAccel)));
                    }
                } else {
                    decreaseBufferBy(0.005);
                }
            }
        }
    }
}
