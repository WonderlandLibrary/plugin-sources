package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsL extends RotationCheck {

    private EvictingList<Double> yawSamples = new EvictingList<>(50), pitchSamples = new EvictingList<>(50);

    public AimAnalyticsL(PlayerData playerData) {
        super(playerData, "Analytics L", "", new ViolationHandler(10,3000L), Category.COMBAT, SubCategory.KILL_AURA, 1);
    }

    @Override
    public void handle() {
        if (rotationTracker.getZoomTicks() >= 2 || actionTracker.getLastAttack() > 10 || !positionTracker.isMoved() || velocityTracker.getTicksSinceVelocity() > 20) return;
        double yawDelta = rotationTracker.getDeltaYaw();
        double pitchDelta = rotationTracker.getDeltaPitch();

        yawSamples.add(yawDelta);
        pitchSamples.add(pitchDelta);

        if (pitchSamples.isFull() && yawSamples.isFull()) {
            double varianceYaw = MathUtil.getVariance(yawSamples);
            double variancePitch = MathUtil.getVariance(pitchSamples);

            int distinctYaw = (int) yawSamples.stream().distinct().count();

            if (distinctYaw > 45) {
                if (varianceYaw > 150 && variancePitch < 7.5) {
                    if (increaseBuffer() > 2) {
                        handleViolation(new DetailedPlayerViolation(this, "VY " + varianceYaw + " VP " + variancePitch + " D " + distinctYaw));
                    }
                } else {
                    decreaseBufferBy(0.0075);
                }
            }
            pitchSamples.clear();
            yawSamples.clear();
        }
    }
}
