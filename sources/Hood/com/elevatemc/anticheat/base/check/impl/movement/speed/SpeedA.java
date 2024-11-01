package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.tag.TagsBuilder;

public class SpeedA extends PositionCheck {
    private double lastDeltaXZ;
    public SpeedA(PlayerData playerData) {
        super(playerData, "Speed A", "Friction Check", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SPEED,5);
    }

    @Override
    public void handle() {
        if (!velocityTracker.canVerifyVelocity()) return;
        if (collisionTracker.isOnIce()
                || collisionTracker.isOnStairs()
                || collisionTracker.isLastOnIce()
                || collisionTracker.isInWater()
                || teleportTracker.isTeleport()
                || playerData.getPlayer().getAllowFlight()) {
            return;
        }

        if (collisionTracker.isVerticallyColliding() || collisionTracker.isLastVerticallyColliding()) {
            resetBuffer();
            return;
        }
        if (!positionTracker.isSentMotion()) return;

        TagsBuilder tags = new TagsBuilder();

        double deltaXZ = positionTracker.getDeltaXZ();
        double deltaY = positionTracker.getDeltaY();

        if (!velocityTracker.getPossibleVelocities().isEmpty()) {
            // Do this so on split if they get a slower velocity they wont false
            // Theres not really any advantage that can be given here either since we can check
            // In a velocity check

            // Compensate for player velocity
            lastDeltaXZ = Math.max(
                    lastDeltaXZ,
                    velocityTracker.getPossibleVelocities().stream().mapToDouble(vector
                            -> Math.hypot(Math.abs(vector.x), vector.z) + 0.15).max().getAsDouble()
            );
        }

        // Max offset limit
        double baseMovementSpeed = attributeTracker.getWalkSpeed();

        // We apply math for sprinting which we assume is always true
        baseMovementSpeed *= 1.3F;

        int speed = attributeTracker.getSpeedBoost();
        int slow = attributeTracker.getSlowness();

        baseMovementSpeed += baseMovementSpeed * speed * 0.2F;
        baseMovementSpeed += baseMovementSpeed * slow * -0.15F;

        float movementSpeed = (float) baseMovementSpeed;

        float friction = 0.91F;

        if (collisionTracker.isLastClientGround()) {
            friction *= playerData.getCollisionTracker().getLastFriction();

            tags.addTag("Last Ground");

            // Apply math for on ground
            movementSpeed *= 0.16277136F / (friction * friction * friction);
        } else {
            tags.addTag("Last Air");
            // Apply math for in air
            movementSpeed = (float) ((double) 0.02F + (double) 0.02F * 0.3D);
        }

        boolean jumpPossible = !collisionTracker.isClientGround()
                && collisionTracker.isLastClientGround()
                && (collisionTracker.isVerticallyColliding()
                || collisionTracker.isLastVerticallyColliding()
                || deltaY >= 0.42F);

        if (jumpPossible) {
            tags.addTag("Jump");

            // Compensate for player jump
            lastDeltaXZ += 0.2D;
        }

        lastDeltaXZ += movementSpeed;

        double offset = deltaXZ - lastDeltaXZ;

        if (positionTracker.isPossiblyZeroThree()) {
            tags.addTag("Desync");

            offset -= 0.03;
        }

        if (offset > 0.001 && (collisionTracker.getServerAirTicks() > 3 || collisionTracker.getClientAirTicks() > 3)) {
            if (deltaXZ > 0.2) {
                if (increaseBuffer() > 15) {
                    handleViolation(new DetailedPlayerViolation(this, "Delta " + format(offset) + " XZ " + format(deltaXZ) + " T [" + tags.build() + "]"));
                    staffAlert(new PlayerViolation(this));
                }
            } else {
                decreaseBufferBy(.05);
            }
        } else {
            decreaseBufferBy(.05);
        }

        lastDeltaXZ = deltaXZ * friction;
    }
}