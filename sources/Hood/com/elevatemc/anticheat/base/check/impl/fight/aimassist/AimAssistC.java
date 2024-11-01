package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;

public class AimAssistC extends RotationCheck {

    private double lastDelta, lastLastDelta;

    private float lastLastDeltaYaw;

    public AimAssistC(PlayerData playerData) {
        super(playerData, "Aim Assist C", "Checks for common killaura rotation patterns",
                new ViolationHandler(7, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 20) {
            return;
        }

        PlayerReachEntity entity = entityTracker.getTrackedPlayer(actionTracker.getEntityId());

        if (entity == null) {
            return;
        }

        float deltaYaw = rotationTracker.getDeltaYaw();
        float lastDeltaYaw = rotationTracker.getLastDeltaYaw();

        double angle = rotationTracker.getAngle();

        double lastAngle = rotationTracker.getLastAngle();

        double delta = Double.NaN;

        if (!Double.isNaN(angle) && !Double.isNaN(lastAngle)) {
            delta = angle - lastAngle;

            if (!Double.isNaN(lastDelta) && !Double.isNaN(lastLastDelta)) {
                // They moved their mouse and didn't spam
                if (deltaYaw > 5F && lastDeltaYaw > 5F && lastLastDeltaYaw > 5F
                        && deltaYaw < 7F && lastDeltaYaw < 7F && lastLastDeltaYaw < 7F) {
                    if (Math.abs(delta - lastDeltaYaw) > 0.1 && Math.abs(delta - lastLastDelta) < 0.005 && delta > lastDelta) {
                        if (increaseBuffer() > 2) {
                            handleViolation(new DetailedPlayerViolation(this, ""));
                        }
                    } else {
                        decreaseBufferBy(0.025);
                    }
                }
            }
        }

        lastLastDelta = lastDelta;
        lastDelta = delta;
        lastLastDeltaYaw = lastDeltaYaw;
    }
}
