package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;

public class AimAnalyticsM extends RotationCheck {

    private EvictingList<Double> yawSamples = new EvictingList<>(50), pitchSamples = new EvictingList<>(50);
    private int lastYaw, lastPitch;
    public AimAnalyticsM(PlayerData playerData) {
        super(playerData, "Analytics M", "", new ViolationHandler(10,3000L), Category.COMBAT, SubCategory.KILL_AURA, 1);
    }

    @Override
    public void handle() {
        if (rotationTracker.getZoomTicks() >= 2 || actionTracker.getLastAttack() > 10 || !positionTracker.isMoved() || velocityTracker.getTicksSinceVelocity() > 60) return;
        double yawDelta = rotationTracker.getDeltaYaw();
        double pitchDelta = rotationTracker.getDeltaPitch();

        yawSamples.add(yawDelta);
        pitchSamples.add(pitchDelta);

        if (pitchSamples.isFull() && yawSamples.isFull()) {

            int distinctYaw = (int) yawSamples.stream().distinct().count();
            int distinctPitch = (int) pitchSamples.stream().distinct().count();

            int differenceYaw = Math.abs(distinctYaw - lastYaw);
            int differencePitch = Math.abs(distinctPitch - lastPitch);

            if (distinctYaw > 45 && distinctPitch <= 30 && differenceYaw == differencePitch) {
                if (increaseBuffer() > 2) {
                    handleViolation(new DetailedPlayerViolation(this, "S " + differencePitch));
                }
            } else {
                decreaseBufferBy(0.0075);
            }


            lastYaw = distinctYaw;
            lastPitch = distinctPitch;
            pitchSamples.clear();
            yawSamples.clear();
        }
    }
}

