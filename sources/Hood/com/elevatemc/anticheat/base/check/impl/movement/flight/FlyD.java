package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class FlyD extends PositionCheck {

    double lastLastDeltaY;
    public FlyD(PlayerData playerData) {
        super(playerData, "Fly D", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.FLIGHT, 2);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport()
                || playerData.getPlayer().getAllowFlight()|| collisionTracker.isOnSlime()
                || collisionTracker.isOnIce()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isNearPiston()
                || collisionTracker.isOnFence()
                || collisionTracker.isLastOnFence();
        if (teleportTracker.isJoined()) return;

        if (!exempt) {
            if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

            double deltaY = positionTracker.getDeltaY(), lastDeltaY = positionTracker.getLastDeltaY();

            int airTicks = collisionTracker.getClientAirTicks();

            if (airTicks >= 20 && (!collisionTracker.isClientGround() || !collisionTracker.isServerGround())) {
                if (increaseBuffer() > 15.0) {
                    if (deltaY > 0.7f && deltaY < 1 && lastDeltaY < 0.42f && lastLastDeltaY != lastDeltaY) {
                        handleViolation(new DetailedPlayerViolation(this,"Y " + format(deltaY) + " LY " + format(lastDeltaY)));
                        resetBuffer();
                    }
                }
            } else {
                decreaseBuffer();
            }

            lastLastDeltaY = lastDeltaY;
        }
    }
}
