package com.elevatemc.anticheat.base.check.impl.movement.flight;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class FlyA extends PositionCheck {

    private double lastOffset;
    public FlyA(PlayerData playerData) {
        super(playerData, "Fly A", "Vertical Axis Prediction", new ViolationHandler(30, 300L), Category.MOVEMENT, SubCategory.FLIGHT, 10);
    }

    @Override
    public void handle() {
        if (!positionTracker.isInLoadedChunk() || teleportTracker.isTeleport()) return;
        if (collisionTracker.isClientGround()
                || collisionTracker.isLastClientGround()
                || collisionTracker.isInLava()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isOnTrapdoor()
                || collisionTracker.isInWater()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastLastInWater()
                || collisionTracker.isLastLastInLava()
                || collisionTracker.isInWeb()
                || collisionTracker.isOnSlime()
                || playerData.getPlayer().getAllowFlight()
                || teleportTracker.isTeleport()
                || velocityTracker.isOnFirstTick()) {
            return;
        }

        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        if (teleportTracker.isJoined()) return;

        if (teleportTracker.getSinceTeleportTicks() < 20 && positionTracker.getDeltaY() == positionTracker.getLastDeltaY() && (positionTracker.isMoved() || positionTracker.isJumped())) return;

        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        boolean underBlock = collisionTracker.isVerticallyColliding();
        boolean wasUnderBlock = collisionTracker.isLastVerticallyColliding();
        boolean isNextToBlock = collisionTracker.isNearWall();
        double prediction = (lastDeltaY - 0.08) * 0.9800000190734863D;

        if (Math.abs(prediction) < 0.005) {
            prediction = 0;
        }

        double offset = Math.abs(deltaY - prediction);

        boolean valid = offset < 1E-8 || ((underBlock || wasUnderBlock || isNextToBlock) && prediction > deltaY);

        double closestPrediction = prediction;

        if (!valid) {
            prediction -= 0.08;
            prediction *= 0.9800000190734863D;

            if (Math.abs(prediction) < 0.005) {
                prediction = 0;
            }

            offset = Math.min(offset, Math.abs(deltaY - prediction));

            valid = offset < 1E-8;

            if (valid) {
                closestPrediction = prediction;
            }
        }

        if (!valid && offset != lastOffset) {
            if (increaseBuffer() > 8) {
                handleViolation(new DetailedPlayerViolation(this, "O " + format(offset) + " PRED " + format(closestPrediction)));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(0.25);
        }
        lastOffset = offset;
    }
}