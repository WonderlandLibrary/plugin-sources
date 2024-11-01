package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionS extends PositionCheck {

    public MotionS(PlayerData playerData) {
        super(playerData, "Motion S", "No Slow", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 1);
    }
    @Override
    public void handle() {
        boolean sprinting = actionTracker.isSprinting();
        boolean blocking = actionTracker.isBlocking() && playerData.getPlayer().isBlocking();
        boolean exempt = teleportTracker.isTeleport() || collisionTracker.isInAir() ||
                playerData.getPlayer().getAllowFlight() || playerData.getPlayer().isInsideVehicle();

        if (exempt) return;

        boolean invalid = blocking && sprinting;
        if (invalid) {
            if (increaseBuffer() > 4.0) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(2.5);
        }
    }
}

