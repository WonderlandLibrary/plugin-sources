package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistN extends RotationCheck {

    double lastDeltaPitch = 0;

    public AimAssistN(PlayerData playerData) {
        super(playerData, "Aim Assist N", "Preemptive aim rotation", new ViolationHandler(8, 10000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5 && !rotationTracker.isZooming()) {

            float deltaYaw = distanceBetweenAngles(rotationTracker.getLastYaw(), rotationTracker.getYaw());
            float deltaPitch = distanceBetweenAngles(rotationTracker.getLastPitch(), rotationTracker.getPitch());

            double pitchAcceleration = Math.abs(this.lastDeltaPitch - deltaPitch);
            boolean preemptive = deltaYaw > 1.975f;

            if (preemptive) {
                boolean flag = deltaPitch < this.lastDeltaPitch && deltaPitch < 0.0700001f && deltaPitch > 0.0015f;
                if (flag) {
                    if (increaseBuffer() > 5.0) {
                        handleViolation(new DetailedPlayerViolation(this, "P " + deltaPitch + " LP " + lastDeltaPitch + " SUP " + (deltaPitch + pitchAcceleration)));
                        resetBuffer();
                    }
                } else {
                    decreaseBufferBy(0.25);
                }
            }
            this.lastDeltaPitch = deltaPitch;
        }
    }

    public static float distanceBetweenAngles(final float alpha, final float beta) {
        final float alphax = alpha % 360.0f;
        final float betax = beta % 360.0f;
        final float delta = Math.abs(alphax - betax);
        return (float) Math.abs(Math.min(360.0 - delta, delta));
    }
}
