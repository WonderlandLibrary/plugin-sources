package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class SpeedI extends PositionCheck {

    int delay;
    double last;

    public SpeedI(PlayerData playerData) {
        super(playerData, "Speed I", "Invalid movement", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SPEED,9);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport()
                || velocityTracker.isOnFirstTick()
                || playerData.getPlayer().getAllowFlight();

        if (exempt) resetBuffer();

        boolean onGround = collisionTracker.isClientGround()
                || collisionTracker.isServerGround();

        double deltaX = positionTracker.getDeltaX(), deltaZ = positionTracker.getDeltaZ();
        double horizontalOffset = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

        double maxDelta = onGround ? 0.34 : 0.36;

        int speedLevel = attributeTracker.getSpeedBoost();

        if (speedLevel > 0) {
            delay = 0;
        } else if (++delay > keepAliveTracker.getPingTicks() * 5) {
            speedLevel = 0;
        }

        maxDelta += (onGround ? 0.06 : 0.02) * speedLevel + 0.12;

        if (collisionTracker.isOnStairs()) {
            maxDelta = 0.45;
        } else if (collisionTracker.isOnIce() || collisionTracker.isLastOnIce()) {
            maxDelta = collisionTracker.isVerticallyColliding() ? 1.3 : 0.8;
        } else if (collisionTracker.isVerticallyColliding()) {
            maxDelta = 0.7;
        }


        if (velocityTracker.isOnFirstTick()){
            maxDelta += Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), Math.abs(velocityTracker.peekVelocity().getZ())) + 0.15;
        }

        if (!positionTracker.isMoved() || !positionTracker.isLastMoved()) {
            maxDelta += 0.03;
        }

        double walkSpeed = attributeTracker.getWalkSpeed();

        if (walkSpeed > 0.2F) maxDelta += walkSpeed * 10F * 0.33F;

        if (horizontalOffset > 1.5) return;
        if (!positionTracker.isSentMotion()) return;

        if (horizontalOffset > maxDelta && !exempt && horizontalOffset != last) {
            if (increaseBuffer() > 22) {
                handleViolation(new DetailedPlayerViolation(this,"D " + format(horizontalOffset) + " MAX " + maxDelta));
                decreaseBufferBy(2.0);
            }
        } else {
            decreaseBufferBy(1.25);
        }
        last = horizontalOffset;
    }
}
