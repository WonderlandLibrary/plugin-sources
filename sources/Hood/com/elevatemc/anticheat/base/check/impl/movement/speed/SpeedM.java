package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;

public class SpeedM extends PositionCheck {

    public SpeedM(PlayerData playerData) {
        super(playerData, "Speed M", "Invalid acceleration", new ViolationHandler(30,300), Category.MOVEMENT, SubCategory.SPEED,5);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();
        double lastDeltaXZ = positionTracker.getLastDeltaXZ();

        if (collisionTracker.isVerticallyColliding() || collisionTracker.isLastVerticallyColliding()) {
            resetBuffer();
            return;
        }

        double limit = PlayerUtil.getBaseSpeed(playerData) + 0.1 + (velocityTracker.isOnFirstTick() ? (Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ()) + 0.15) : 0.0);
        double acceleration = deltaXZ - lastDeltaXZ;

        boolean exempt = playerData.getPlayer().getAllowFlight() || collisionTracker.isVerticallyColliding() || collisionTracker.isOnClimbable() || collisionTracker.isOnSlime()
                || teleportTracker.isTeleport() || teleportTracker.getSinceTeleportTicks() <= 1 || playerData.getPlayer().isInsideVehicle();

        boolean teleport = teleportTracker.isTeleport() || teleportTracker.getSinceTeleportTicks() < 10;

        boolean invalid = acceleration > limit;
        if (invalid && !exempt && !teleport) {
            if (!positionTracker.isSentMotion()) return;
            if (increaseBuffer() > 6.0) {
                handleViolation(new DetailedPlayerViolation(this,"A " + acceleration));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(.15);
        }
    }
}
