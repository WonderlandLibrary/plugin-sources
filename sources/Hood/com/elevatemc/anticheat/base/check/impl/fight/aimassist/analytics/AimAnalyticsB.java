package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
public class AimAnalyticsB extends RotationCheck {

    private EvictingList<Double> yawSamples = new EvictingList<>(50), pitchSamples = new EvictingList<>(50);

    public AimAnalyticsB(PlayerData playerData) {
        super(playerData, "Analytics B", "Slinky aim assist.", new ViolationHandler(20, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (rotationTracker.getZoomTicks() >= 2 || actionTracker.getLastAttack() > 20 || actionTracker.isSneaking() || velocityTracker.getTicksSinceVelocity() > 60)
            return;
        double yawDelta = rotationTracker.getDeltaYaw();
        double pitchDelta = rotationTracker.getDeltaPitch();

        yawSamples.add(yawDelta);
        pitchSamples.add(pitchDelta);

        if (pitchSamples.isFull() && yawSamples.isFull()) {

            int distinctYaw = (int) yawSamples.stream().distinct().count();
            int distinctPitch = (int) pitchSamples.stream().distinct().count();

            if (distinctYaw > 40 && distinctPitch <= distinctYaw / 2 && distinctPitch > 10) {
                if (increaseBuffer() > 3) {
                    handleViolation(new DetailedPlayerViolation(this, "Y " + distinctYaw + " P " + distinctPitch));
                }
            } else {
                decreaseBufferBy(0.75);
            }
            pitchSamples.clear();
            yawSamples.clear();
        }
    }
}
