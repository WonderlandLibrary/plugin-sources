package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsD extends RotationCheck {
    private EvictingList<Float> yawSamples = new EvictingList<>(20);
    private EvictingList<Double> deviationAverage = new EvictingList<>(5);

    public AimAnalyticsD(PlayerData playerData) {
        super(playerData, "Analytics D", "Invalid deviation average", new ViolationHandler(5, 3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (rotationTracker.getSensitivity() != -1 && actionTracker.getLastAttack() < 5) {
            float deltaYaw = rotationTracker.getDeltaYaw();

            if (deltaYaw == 0.0 || deltaYaw > 20.0F) return;

            yawSamples.add(deltaYaw);

            if (yawSamples.isFull()) {
                double deviation = MathUtil.getStandardDeviation(yawSamples);

                deviationAverage.add(deviation);

                if (deviationAverage.isFull()) {
                    double avg = MathUtil.getAverage(deviationAverage);
                    if (avg < 1) {
                        if (increaseBuffer() > 2) {
                            handleViolation(new DetailedPlayerViolation(this, "AVG " + format(avg)));
                        }
                    } else {
                        decreaseBuffer();
                    }
                    deviationAverage.clear();
                }
                yawSamples.clear();
            }
        }
    }
}

