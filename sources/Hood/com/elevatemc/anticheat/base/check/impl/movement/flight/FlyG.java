package com.elevatemc.anticheat.base.check.impl.movement.flight;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class FlyG extends PositionCheck {

    public FlyG(PlayerData playerData) {
        super(playerData, "Fly G", "Capping", new ViolationHandler(15, 30000L), Category.MOVEMENT, SubCategory.FLIGHT, 5);
    }
    @Override
    public void handle() {
        if (teleportTracker.isTeleport() || teleportTracker.getSinceTeleportTicks() < 3 || playerData.getPlayer().getAllowFlight()) return;
        if (!positionTracker.isSentMotion() || actionTracker.getRespawnTicks() < 100) return;

        int airTicks = collisionTracker.getClientAirTicks();
        int serverTicks = collisionTracker.getServerAirTicks();

        boolean ground = collisionTracker.isClientGround() || collisionTracker.isServerGround();
        boolean capping = (airTicks > 15 || serverTicks > 15) && ground;
        if (capping) {
            if (increaseBuffer() > 3) {
                handleViolation(new DetailedPlayerViolation(this, "T " + airTicks));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            resetBuffer();
        }
    }
}
