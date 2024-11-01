package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class FlyC extends PositionCheck {

    public FlyC(PlayerData playerData) {
        super(playerData, "Fly C", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.FLIGHT, 3);

    }

    @Override
    public void handle() {
        boolean onGround = collisionTracker.isClientGround() || collisionTracker.isServerGround();

        double deltaY = positionTracker.getDeltaY();

        double lastDeltaY = positionTracker.getLastDeltaY();
        double difference = Math.abs(deltaY - lastDeltaY);
        boolean exempt = teleportTracker.isTeleport()
                || collisionTracker.isInLava()
                || collisionTracker.isInWater()
                || collisionTracker.isInWeb()
                || collisionTracker.isOnClimbable()
                || Math.abs(deltaY) > 3.0
                || Math.abs(lastDeltaY) > 3.0
                || collisionTracker.isOnSlime()
                || positionTracker.getY() < 4.0
                || velocityTracker.isOnFirstTick()
                || playerData.getPlayer().getAllowFlight()
                || teleportTracker.isTeleport()
                || positionTracker.getDeltaY() < 0.09;

        if (teleportTracker.isJoined()) return;
        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        double limit = 0.01;

        boolean invalid = difference < limit && !onGround;

        if (invalid && !exempt) {
            if (increaseBuffer() > 4.0) {
                handleViolation(new DetailedPlayerViolation(this,"DY " + format(deltaY) + " DIFF " + format(difference)));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(0.25);
        }
    }
}
