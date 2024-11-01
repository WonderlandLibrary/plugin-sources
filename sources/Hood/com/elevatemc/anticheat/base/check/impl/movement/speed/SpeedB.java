package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

import java.util.List;
import java.util.stream.Collectors;

public class SpeedB extends PositionCheck {
    public SpeedB(PlayerData playerData) {
        super(playerData, "Speed B", "Movement Emulation Check", new ViolationHandler(20, 30000L), Category.MOVEMENT, SubCategory.SPEED,3);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();
        double lastDeltaXZ = positionTracker.getLastDeltaXZ();

        boolean ground = collisionTracker.isClientGround();
        boolean lastGround = collisionTracker.isLastClientGround();
        boolean lastLastGround = collisionTracker.isLastLastClientGround();

        int desyncTicks = positionTracker.getDelayedFlyingTicks();

        float friction = 0.91F;

        if (lastGround) {
            friction *= collisionTracker.getLastFriction();
        }

        float lastFriction = 0.91F;

        if (lastLastGround) {
            lastFriction *= collisionTracker.getLastLastFriction();
        }

        float f = 0.16277136F / (friction * friction * friction);

        float movementSpeed;

        if (lastGround) {
            double aiMoveSpeed = attributeTracker.getWalkSpeed();

            aiMoveSpeed += aiMoveSpeed * 0.30000001192092896D;

            int speed = attributeTracker.getSpeedBoost();
            int slow = attributeTracker.getSlowness();

            aiMoveSpeed += aiMoveSpeed * (speed) * 0.20000000298023224D;

            aiMoveSpeed += aiMoveSpeed * (slow) * -0.15000000596046448D;

            movementSpeed = (float) aiMoveSpeed;

            movementSpeed *= f;
        } else {
            // you can thank mojang for this line <3
            movementSpeed = (float) ((double) 0.02F + (double) 0.02F * 0.3D);
        }

        List<Double> movements = getVelocityTracker().getPossibleVelocities().stream().map(velocity
                -> Math.hypot(velocity.getX(), velocity.getZ())).collect(Collectors.toList());

        movements.add(lastDeltaXZ * lastFriction);

        double minOffset = Double.MAX_VALUE;

        for (double movement : movements) {
            if (!ground && lastGround) {
                movement += 0.2F;
            }

            movement += movementSpeed;

            double offset = deltaXZ - movement;

            minOffset = Math.min(minOffset, offset);

            // Found legit movement
            if (minOffset < 0)
                break;
        }

        boolean exempt = collisionTracker.isNearPiston()
                || teleportTracker.isTeleport()
                || collisionTracker.isLastLastInWater()
                || playerData.getPlayer().getAllowFlight()
                || collisionTracker.isLastLastInLava();
        if (!positionTracker.isSentMotion()) return;

        if (minOffset > (desyncTicks > 0 ? 0.03 + 1E-5 : 1E-5) && !exempt && deltaXZ >= 0.2D) {
            if (increaseBuffer() > 10) {

                handleViolation(new DetailedPlayerViolation(this, "XZ " + format(deltaXZ) + " O " + format(minOffset)));
            }
        } else {
            decreaseBufferBy(0.25);
        }
    }
}