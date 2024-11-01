package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class FlyB extends PositionCheck {
    public FlyB(PlayerData playerData) {
        super(playerData, "Fly B", "Vertical axis prediction", new ViolationHandler(30, 300L), Category.MOVEMENT, SubCategory.FLIGHT, 5);
    }

    @Override
    public void handle() {
        if (!positionTracker.isInLoadedChunk()) return;
        int airTicks = collisionTracker.getServerAirTicks();

        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        double prediction = (lastDeltaY - 0.08) * 0.9800000190734863;
        double difference = Math.abs(deltaY - prediction);

        boolean invalid = airTicks > 5 && difference > 1e-10;
        boolean exempt = teleportTracker.isTeleport()
                || Hood.get().isLagging()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isLastOnClimbable()
                || collisionTracker.isOnIce()
                || collisionTracker.isLastOnIce()
                || collisionTracker.isOnSlime()
                || collisionTracker.isLastOnSlime()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || playerData.getPlayer().isInsideVehicle()
                || collisionTracker.isInWater()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastLastInWater()
                || collisionTracker.isLastLastInLava()
                || playerData.getPlayer().isFlying()
                || playerData.getPlayer().getAllowFlight()
                || teleportTracker.isTeleport()
                || collisionTracker.isInWater()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater()
                || collisionTracker.isInLava()
                || velocityTracker.isOnFirstTick()
                || positionTracker.getY() < 0.0;

        if (teleportTracker.isJoined()) return;
        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        if (invalid && !exempt) {
            if (increaseBuffer() > 8) {
                handleViolation(new DetailedPlayerViolation(this,"T " + airTicks + " Y " + format(deltaY)));
                multiplyBuffer(.5);
            }
        } else {
            decreaseBufferBy(.125);
        }
    }
}
