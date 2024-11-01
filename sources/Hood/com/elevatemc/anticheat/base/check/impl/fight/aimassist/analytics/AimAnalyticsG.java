package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

import java.util.List;

public class AimAnalyticsG extends RotationCheck {

    private EvictingList<Double> samples = new EvictingList<>(30);
    private double last;

    public AimAnalyticsG(PlayerData playerData) {
        super(playerData, "Analytics G", "Invalid head movement", new ViolationHandler(10, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        boolean valid = actionTracker.getLastAttack() < 20 && !rotationTracker.isZooming();

        if (valid) {
            double deviation = getDeviation();
            // Add deviation to samples for further analysis
            samples.add(deviation);

            if (samples.isFull()) {
                double stDev = MathUtil.getStandardDeviation(samples);
                double difference = Math.abs(stDev - last);
                double mean = calculateMean(samples);

                boolean smooth = stDev < 0.5 && difference < 0.05 && mean < 0.01;
                if (smooth) {
                    if (increaseBuffer() > 6) {
                        handleViolation(new DetailedPlayerViolation(this, "S " + format(stDev) + " M " + format(mean)));
                    }
                }
                last = stDev;
                samples.clear();
            }
        }
    }

    private double getDeviation() {
        float currentYaw = rotationTracker.getDeltaYaw();
        float currentPitch = rotationTracker.getDeltaPitch();

        float previousYaw = rotationTracker.getLastDeltaYaw(); // Get the previous yaw angle
        float previousPitch = rotationTracker.getLastDeltaPitch(); // Get the previous pitch angle

        float yawDerivation = currentYaw - previousYaw;
        float pitchDerivation = currentPitch - previousPitch;

        // Clamp!
        double clampedYaw = clamp180(yawDerivation);
        double clampedPitch = clamp180(pitchDerivation);

        // Calculate symmetric deviation
        return (clampedYaw + clampedPitch) / 2;
    }

    private double calculateMean(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Input list is null or empty");
        }

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }

        return sum / values.size();
    }

    public static double clamp180(double theta) {
        theta %= 360.0;
        if (theta >= 180.0) {
            theta -= 360.0;
        }
        if (theta < -180.0) {
            theta += 360.0;
        }
        return theta;
    }
}

