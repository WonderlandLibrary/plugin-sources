package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.util.math.EvictingList;

import java.util.List;

public class AimAnalyticsH extends RotationCheck {

    private final EvictingList<Double> samples = new EvictingList<>(30);

    public AimAnalyticsH(PlayerData playerData) {
        super(playerData, "Analytics H", "Invalid head movement", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        boolean valid = actionTracker.getLastAttack() < 5 && !rotationTracker.isZooming();

        if (valid) {
            double deviation = getDeviation();
            // Add deviation to samples for further analysis
            samples.add(deviation);

            if (samples.isFull()) {
                // Get the mean and stDev.
                double mean = calculateMean(samples);
                double stDev = calculateStandardDeviation(samples);

                debug("M " + format(mean) + " DEV " + format(stDev));
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

        // Clamp!.
        double clampedYaw = clamp180(yawDerivation);
        double clampedPitch = clamp180(pitchDerivation);

        // Calculate symmetric deviation
        return (clampedYaw + clampedPitch) / 2;
    }

    double calculateMean(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Input list is null or empty");
        }

        double sum = 0.0;
        for (double value : values) {
            sum += value;
        }

        return sum / values.size();
    }

    double calculateStandardDeviation(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("Input list is null or empty");
        }

        double mean = calculateMean(values);
        double sumSquaredDeviations = 0.0;

        for (double value : values) {
            double deviation = value - mean;
            sumSquaredDeviations += deviation * deviation;
        }

        double variance = sumSquaredDeviations / values.size();
        return Math.sqrt(variance);
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

