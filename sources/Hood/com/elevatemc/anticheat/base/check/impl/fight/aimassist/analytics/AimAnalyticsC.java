package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsC extends RotationCheck {

    private EvictingList<Float> yawSamples = new EvictingList<>(20);
    private double lastDeviation;

    public AimAnalyticsC(PlayerData playerData) {
        super(playerData, "Analytics C", "Invalid deviation differences", new ViolationHandler(10, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 2);
    }

    @Override
    public void handle() {
        if (rotationTracker.getSensitivity() != -1 && actionTracker.getLastAttack() < 5) {
            float deltaYaw = rotationTracker.getDeltaYaw();

            if (deltaYaw == 0.0 || deltaYaw > 20.0F) return;

            yawSamples.add(deltaYaw);

            if (yawSamples.isFull()) {
                double deviation = MathUtil.getStandardDeviation(yawSamples);
                double average = MathUtil.getAverage(yawSamples);
                double difference = Math.abs(deviation - lastDeviation);

                if (average > 3.5 && difference < 0.025) {
                    if (increaseBuffer() > 6) {
                        handleViolation(new DetailedPlayerViolation(this, "A " + format(average) + " DIFF " + format(difference)));
                        decreaseBufferBy(2);
                    }
                }

                lastDeviation = deviation;
                yawSamples.clear();
            }
        }
    }
}

