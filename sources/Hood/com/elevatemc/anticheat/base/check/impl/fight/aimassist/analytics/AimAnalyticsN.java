package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsN extends RotationCheck {

    private final EvictingList<Double> yaw = new EvictingList<>(50), pitch = new EvictingList<>(50);

    private int lastPoints, lastLastPoints;
    /*
        Most new aim assist features such as Slinky's aim assist have the vertical acceleration
        set to 0 by default, meaning when the player is attacking most of the time they're just rotating horizontally
        which gives an idea that if we keep track of the players' past 50 rotations vertically and horizontally
        from their combat data, we can find unusual rotational behaviour.

        of course, we'll check the average, and the distinct count from each list.
        pretty easy check to understand.

     */
    public AimAnalyticsN(PlayerData playerData) {
        super(playerData, "Analytics N", "", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 5) return; // we want combat statistics only.

        double yawAcceleration = rotationTracker.getYawAccel();
        double pitchAcceleration = rotationTracker.getPitchAccel();

        if (rotationTracker.getDeltaYaw() < 0.1F && rotationTracker.getDeltaPitch() < 0.1F) return;

        yaw.add(yawAcceleration);
        pitch.add(pitchAcceleration);

        if (yaw.isFull() && pitch.isFull()) {
            double averageYaw = MathUtil.getAverage(yaw);
            double averagePitch = MathUtil.getAverage(pitch);

            int distinctYaw = (int) yaw.stream().distinct().count();
            int distinctPitch = (int) pitch.stream().distinct().count();
            int distinct = distinctYaw - distinctPitch;

            double deviationYaw = MathUtil.getStandardDeviation(yaw);
            double deviationPitch = MathUtil.getStandardDeviation(pitch);

            debug("Analytics Y (" + averageYaw + " | " + distinctYaw + " | " + deviationYaw + ") P (" + averagePitch + " | " + distinctPitch + " | " + deviationPitch + ") D " + distinct);

            int points = calculateFlagValue(averageYaw, averagePitch,distinctPitch, deviationYaw, deviationPitch, distinct);

            debug("points : " + points + " " + lastPoints + " " + lastLastPoints);

            /*
            if (points == lastPoints && lastPoints == lastLastPoints) {
                handleViolation(new DetailedPlayerViolation(this, "Consistent pattern: " + points));
            }
             */

            if (points >= 3) {
                handleViolation(new DetailedPlayerViolation(this, "Bad randomization: " + points));
            }

            lastLastPoints = lastPoints;
            lastPoints = points;

            yaw.clear();
            pitch.clear();
        }
    }


    public int calculateFlagValue(double averageYaw, double averagePitch, int distinctPitch, double deviationYaw, double deviationPitch, int distinct) {
        int points = 0;

        // Positive Flags
        if (distinctPitch < 10) {
            points += 5;
        } else if (distinctPitch < 15) {
            points += 3;
        }

        if (distinct > 35) {
            points += 4;
        } else if (distinct > 25) {
            points += 2;
        }

        if (averagePitch < 0.05) {
            points += 3;
        } else if (averagePitch < 0.1) {
            points += 1;
        }

        if (averageYaw < 0.2) {
            points += 3;
        } else if (averageYaw < 0.3) {
            points += 1;
        }

        if (deviationYaw < 0.2 || deviationPitch < 0.2) {
            points += 2;
        }
        if (deviationYaw < 0.2 && deviationPitch < 0.2) {
            points += 4;
        }

        // Negative Flags
        if (distinctPitch > 30) {
            points -= 3;
        } else if (distinctPitch > 25) {
            points -= 2;
        }

        if (distinct < 10) {
            points -= 3;
        } else if (distinct < 15) {
            points -= 2;
        }

        if (averagePitch > 0.2) {
            points -= 2;
        } else if (averagePitch > 0.15) {
            points -= 1;
        }

        if (averageYaw > 0.4) {
            points -= 2;
        } else if (averageYaw > 0.35) {
            points -= 1;
        }

        if (deviationYaw > 0.5 || deviationPitch > 0.5) {
            points -= 3;
        }
        if (deviationYaw > 0.4 && deviationPitch > 0.4) {
            points -= 2;
        }
        return points;
    }

}
