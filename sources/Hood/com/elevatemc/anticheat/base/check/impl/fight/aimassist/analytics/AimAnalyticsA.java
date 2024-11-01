package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;



import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class AimAnalyticsA extends RotationCheck {

    private List<Double> pitchChanges = new ArrayList<>();
    private List<Double> yawChanges = new ArrayList<>();
    private List<Long> timestamps = new ArrayList<>();

    public AimAnalyticsA(PlayerData playerData) {
        super(playerData, "Analytics A", "Vape aimassist", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 2);
    }

    @Override
    public void handle() {

        if (rotationTracker.getZoomTicks() >= 2) {
            return;
        }

        if (actionTracker.getLastAttack() > 10) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        double deltaYaw = rotationTracker.getDeltaYaw();
        double deltaPitch = rotationTracker.getDeltaPitch();

        pitchChanges.add(deltaPitch);
        yawChanges.add(deltaYaw);
        timestamps.add(currentTime);

        if (youSmellIkeYouFarted()) {
           if (increaseBuffer() > 2) handleViolation(new PlayerViolation(this));
        }
    }

    /*
        Alright, this is an actual competent check that had time put into it
        so adding on to that time, we'll add an explanation. Because I enjoyed making this check.
     */
    private boolean youSmellIkeYouFarted() {
        // listo no fillo returno falso.
        if (pitchChanges.size() < 20 || yawChanges.size() < 20) {
            return false;
        }

        // calculate mean of yaw, pitch changes.
        double avgPitchChange = MathUtil.getAverage(pitchChanges);
        double avgYawChange = MathUtil.getAverage(yawChanges);
        // grab the deviation.
        double stdDevPitch = MathUtil.getStandardDeviation(pitchChanges);
        double stdDevYaw = MathUtil.getStandardDeviation(yawChanges);
        // check time between aim changes.
        double timeBetweenChanges = getAverageTimeBetweenChanges(timestamps);

        // I have made these thresholds and let me explain why.
        /*
            So, for the smoothness, it's known that minecraft rotations are linear, and that
            players don't typically have "perfect smooth aim" unless they're losers who sit on
            their ass and actually practice aim, not in our community, everyone cheats.
            minecraft aim changes will have natural variance because of hand-eye coordination,
            the human reaction time etc etc.
            I debugged a bit, and it turned out that the average change (both yaw and pitch) that
            is below 0.3 degrees makes me realise that there's an insane level of smoothness that
            you can't just achieve manually, which what makes me think that they're using aim assist.
         */
        float smoothnessThreshold = 0.3f;
        /*
            For standard deviation.....so
            stdev in this case im using it to measure the variability in the players' rotation changes.
            a stdev below 0.15 is really, and I mean really low, usually meaning that their aim is like
            really consistent. More natural aim shows more variance, especially in 1v1's and teamfights.
         */
        float stdDevThreshold = 0.15f;
        /*
            80 milliseconds is an average time between aim changes, this reflects how quickly
            a data adjusts their aim, a low average time (less than our threshold) is really
            unfitting if we take in account actual human reaction time and decision-making.
         */
        float timeThreshold = 80.0f;

        debug("Smoothness (" + format(timeBetweenChanges / 1000) + ") Y|P (" + format(avgYawChange) + " | " + format(avgPitchChange)
                + ") SDY|SDP (" + format(stdDevYaw) + " | " + format(stdDevPitch));

        pitchChanges.clear();
        yawChanges.clear();

        /*
            Overall combining all three thresholds ensures no false positives.
            hopefully, you never know what our retarded community does.
         */
        return (avgPitchChange < smoothnessThreshold && avgYawChange < smoothnessThreshold &&
                stdDevPitch < stdDevThreshold && stdDevYaw < stdDevThreshold &&
                timeBetweenChanges < timeThreshold);
    }


    private float getAverageTimeBetweenChanges(List<Long> timestamps) {
        if (timestamps.size() < 2) {
            return Float.MAX_VALUE;
        }
        List<Long> intervals = new ArrayList<>();
        for (int i = 1; i < timestamps.size(); i++) {
            intervals.add(timestamps.get(i) - timestamps.get(i - 1));
        }
        timestamps.clear();
        return (float) intervals.stream().mapToLong(Long::longValue).average().orElse(0.0);
    }
}

