package com.elevatemc.anticheat.base.check.impl.fight.velocity;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.util.Vector3d;

import java.util.List;

public class VelocityB extends PositionCheck {

    public VelocityB(PlayerData playerData) {
        super(playerData, "Velocity B", "Vertical Velocity Modifications", new ViolationHandler(5, 3000L), Category.COMBAT, SubCategory.VELOCITY, 0);
    }

    @Override
    public void handle() {
        // if during the velocity, the vertical movement could possibly be altered, we exempt.
        if (!velocityTracker.isOnFirstTick()
                || collisionTracker.isVerticallyColliding()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isInWeb()
                || positionTracker.getDeltaY() >= 0.42F) {
            decreaseBufferBy(0.01D);
            return;
        }

        final double delta = positionTracker.getDeltaY();

        final List<Double> velocities = velocityTracker.getPossibleVelocities().stream().mapToDouble(Vector3d::getY).
                boxed().toList();

        double offset = velocities.stream().mapToDouble(velocity -> {
            double difference = Math.abs(delta - velocity);

            // account for possible 0.03
            if (difference > 1E-6D) {
                double fixed = (velocity - 0.08D) * 0.98F;

                if (Math.abs(fixed - delta) < difference) {
                    difference = Math.abs(fixed - delta);
                }
            }

            return difference;
        }).min().orElse(0.0D);

        if (delta > 0.0D && velocities.stream().anyMatch(velocity -> velocity >= 0.003D) && offset > 1E-10D) {
            if (increaseBuffer() > 3) {
                setBuffer(2);
                handleViolation(new DetailedPlayerViolation(this, "offset=" + offset + " delta=" + delta));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(0.1D);
        }
    }
}
