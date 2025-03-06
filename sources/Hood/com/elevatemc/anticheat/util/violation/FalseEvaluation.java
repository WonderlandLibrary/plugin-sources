package com.elevatemc.anticheat.util.violation;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;

public class FalseEvaluation {

    public static Probability isHacking(PlayerData data) {
        double overallFalsePositiveProbability = 1.0;
        double totalViolationLevel = 0;

        // Loop through all checks in the player data
        for (Check check : data.getCheckData().getChecks()) {
            if (check.getViolationHandler().getMaxViolations() == Integer.MAX_VALUE) continue;
            double checkFalsePositiveProbability = check.getFalseProbability() / 100.0; // Convert to 0-1 scale
            overallFalsePositiveProbability *= (1.0 - checkFalsePositiveProbability);
            totalViolationLevel += check.getVl();
        }

        // Calculate the final overall false positive probability
        overallFalsePositiveProbability = 1.0 - overallFalsePositiveProbability;

        // Calculate the cheating probability
        double cheatingProbability = (1.0 - overallFalsePositiveProbability) * (totalViolationLevel / (totalViolationLevel + 1.0));

        // Determine the cheating level based on thresholds
        if (cheatingProbability < 0.05) {
            return Probability.NOT;
        } else if (cheatingProbability < 0.15) {
            return Probability.FALSE;
        } else if (cheatingProbability < 0.3) {
            return Probability.LOW;
        } else if (cheatingProbability < 0.5) {
            return Probability.PROBABLE;
        } else if (cheatingProbability < 0.75) {
            return Probability.SECURE;
        } else if (cheatingProbability < 0.9) {
            return Probability.HIGH;
        } else {
            return Probability.BLATANT;
        }
    }
}
