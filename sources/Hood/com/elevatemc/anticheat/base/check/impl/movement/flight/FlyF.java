package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class FlyF extends PositionCheck {

    private double lastAcceleration, lastDelta;
    private int accelerationBuffer;
    public FlyF(PlayerData playerData) {
        super(playerData, "Fly F", "Mid-Air Acceleration", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.FLIGHT, 5);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport() || Hood.get().isLagging()
                || collisionTracker.isOnClimbable() || collisionTracker.isLastOnClimbable() || collisionTracker.isOnIce() || collisionTracker.isLastOnIce()
                || collisionTracker.isOnSlime() || collisionTracker.isLastOnSlime() || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava() || playerData.getPlayer().isInsideVehicle() || collisionTracker.isInWater()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastLastInWater()
                || collisionTracker.isLastLastInLava()
                || playerData.getPlayer().getAllowFlight()
                || teleportTracker.isTeleport()
                || collisionTracker.isInLava()
                || velocityTracker.isOnFirstTick()
                || collisionTracker.isVerticallyColliding()
                || collisionTracker.isOnFence()
                || collisionTracker.isInWeb()
                || positionTracker.getY() < 4.0;
        if (exempt) {
            resetBuffer();
            return;
        }
        if (teleportTracker.isJoined()) return;
        if (!positionTracker.isInLoadedChunk() || teleportTracker.isTeleport()) return;
        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        if (velocityTracker.isOnFirstTick()) {
            deltaY -= velocityTracker.peekVelocity().getY();
        }

        double acceleration = Math.abs(deltaY - lastDeltaY);
        double deltaAcceleration = acceleration - this.lastAcceleration;

        boolean onGround = collisionTracker.isClientGround();

        int airTicks = collisionTracker.getClientAirTicks();
        if (!onGround) {
            if (airTicks > 5 && deltaY == lastDeltaY) {
                if (increaseBuffer() > 3) {
                    handleViolation(new DetailedPlayerViolation(this, "D " + format(deltaY) + " T " + airTicks));
                }
            } else {
                resetBuffer();
            }
            if ((airTicks > 5 && deltaAcceleration == 0.0 && deltaAcceleration != lastDelta) || airTicks > 6 && deltaAcceleration > 0.018) {
                if (++this.accelerationBuffer > 4) {
                    handleViolation(new DetailedPlayerViolation(this, "A " + format(deltaAcceleration) + " T " + airTicks));
                }
            }
            else {
                this.accelerationBuffer = 0;
            }
        }
        this.lastDelta = deltaAcceleration;
        this.lastAcceleration = acceleration;
    }
}
