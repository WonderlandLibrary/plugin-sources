package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsJ extends RotationCheck {

    private final EvictingList<Float> yawAcceleration = new EvictingList<>(60);
    private final EvictingList<Float> pitchAcceleration = new EvictingList<>(60);

    public AimAnalyticsJ(PlayerData playerData) {
        super(playerData, "Analytics J", "Invalid acceleration", new ViolationHandler(5, 3000L), Category.COMBAT, SubCategory.KILL_AURA, 2);
    }

    @Override
    public void handle() {
        if (!rotationTracker.isZooming()) {

            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            float joltY = rotationTracker.getJoltYaw();
            float joltP = rotationTracker.getJoltPitch();

            if (deltaYaw != 0.0f && deltaPitch != 0.0f && deltaYaw < 20.0f && deltaPitch < 20.0f) {

                yawAcceleration.add(joltY);
                pitchAcceleration.add(joltP);

                if (yawAcceleration.isFull() && pitchAcceleration.isFull()) {

                    double averageYaw = MathUtil.getAverage(yawAcceleration);
                    double averagePitch = MathUtil.getAverage(pitchAcceleration);

                    if (averageYaw > 3.5 && averagePitch > 6.5) {
                        if (increaseBuffer() > 6.0) {
                            handleViolation(new DetailedPlayerViolation(this,"Y " + averageYaw + " P " + averagePitch));
                        }
                    } else {
                        decreaseBufferBy(.15);
                    }

                    yawAcceleration.clear();
                    pitchAcceleration.clear();
                }
            }
        }
    }
}


