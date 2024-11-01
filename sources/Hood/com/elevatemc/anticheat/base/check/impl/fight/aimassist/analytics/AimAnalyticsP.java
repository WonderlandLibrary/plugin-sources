package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsP extends RotationCheck {

    private final EvictingList<Double> yawSamples = new EvictingList<>(20);
    private final EvictingList<Double> pitchSamples = new EvictingList<>(20);
    public AimAnalyticsP(PlayerData playerData) {
        super(playerData, "Analytics P", "Improper rotations", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 4 && !rotationTracker.isZooming()) {
            // Get the deltas from the rotation update
            final float deltaYaw = rotationTracker.getDeltaYaw();
            final float deltaPitch = rotationTracker.getDeltaPitch();

            final float lastDeltaYaw = rotationTracker.getLastDeltaYaw();
            final float lastDeltaPitch = rotationTracker.getLastDeltaPitch();

            // Grab the gcd using an expander.
            final double divisorYaw = MathUtil.getGcd((long) (deltaYaw * MathUtil.EXPANDER), (long) (lastDeltaYaw * MathUtil.EXPANDER));
            final double divisorPitch = MathUtil.getGcd((long) (deltaPitch * MathUtil.EXPANDER), (long) (lastDeltaPitch * MathUtil.EXPANDER));

            // Get the constant for both rotation updates by dividing by the expander
            final double constantYaw = divisorYaw / MathUtil.EXPANDER;
            final double constantPitch = divisorPitch / MathUtil.EXPANDER;

            // Get the estimated mouse delta from the constant
            final double currentX = deltaYaw / constantYaw;
            final double currentY = deltaPitch / constantPitch;

            // Get the estimated mouse delta from the old rotations using the new constant
            final double previousX = lastDeltaYaw / constantYaw;
            final double previousY = lastDeltaPitch / constantPitch;

            // Make sure the rotation is not very large and not equal to zero and get the modulo of the xys
            if (deltaYaw > 0.0 && deltaPitch > 0.0 && deltaYaw < 20.f && deltaPitch < 20.f) {
                final double moduloX = currentX % previousX;
                final double moduloY = currentY % previousY;

                // Get the floor delta of the the moduloes
                final double floorModuloX = Math.abs(Math.floor(moduloX) - moduloX);
                final double floorModuloY = Math.abs(Math.floor(moduloY) - moduloY);

                yawSamples.add(floorModuloX);
                pitchSamples.add(floorModuloY);

                if (yawSamples.isFull() && pitchSamples.isFull()) {

                    int duplicates = (int) (yawSamples.size() - yawSamples.parallelStream().distinct().count());
                    int pitchDuplicates = (int) (pitchSamples.size() - pitchSamples.parallelStream().distinct().count());

                    int combinedDuplicates = duplicates + pitchDuplicates;

                    debug("Combined " + combinedDuplicates + " Yaw " + duplicates + " Pitch " + pitchDuplicates);

                    if (combinedDuplicates >= 25 || pitchDuplicates > 20 && duplicates == 0) {
                        if (increaseBuffer() > 15) {
                            handleViolation(new DetailedPlayerViolation(this, "Combined " + combinedDuplicates + " Yaw " + duplicates + " Pitch " + pitchDuplicates));
                            setBuffer(10);
                        }
                    } else {
                        decreaseBufferBy(0.75);
                    }
                    yawSamples.clear();
                    pitchSamples.clear();
                }
            }
        }
    }
}
