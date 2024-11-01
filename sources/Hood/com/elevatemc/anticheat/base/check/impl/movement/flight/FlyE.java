package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class FlyE extends PositionCheck {

    public FlyE(PlayerData playerData) {
        super(playerData, "Fly E", "", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.FLIGHT, 5);
    }

    @Override
    public void handle() {
        if (teleportTracker.getSinceTeleportTicks() <= 1) return;

        int serverAirTicks = collisionTracker.getServerAirTicks();
        int clientAirTicks = collisionTracker.getClientAirTicks();

        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        double acceleration = deltaY - lastDeltaY;

        boolean exempt = teleportTracker.isTeleport()
                || collisionTracker.isInLava()
                || collisionTracker.isInWater()
                || collisionTracker.isOnSlime()
                || playerData.getPlayer().isInsideVehicle()
                || teleportTracker.isTeleport()
                || collisionTracker.isInWeb()
                || playerData.getPlayer().getAllowFlight()
                || collisionTracker.isOnFence()
                || collisionTracker.isOnClimbable();

        double limit = 0.0;
        if (teleportTracker.isJoined()) return;
        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        if (deltaY == lastDeltaY || clientAirTicks > 30 && serverAirTicks == 0 || collisionTracker.isInWeb()) {
            return;
        }

        if (exempt) resetBuffer();

        if (velocityTracker.getTicksSinceVelocity() <= 10) {
            limit += Math.abs(Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ()));
        } else {
            limit = 0.0;
        }

        boolean invalid = acceleration > limit && clientAirTicks > 20;

        if (invalid && !exempt) {
            if (increaseBuffer() > 15.0) {
                handleViolation(new DetailedPlayerViolation(this,"T " + clientAirTicks + " ST " + serverAirTicks + " Y " + format(deltaY)));
            }
        }
        else {
            decreaseBufferBy(0.1);
        }
    }
}
