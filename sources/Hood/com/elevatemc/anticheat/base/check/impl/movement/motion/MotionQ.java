package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionQ extends PositionCheck {

    public MotionQ(PlayerData playerData) {
        super(playerData, "Motion Q", "No Slow", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 1);
    }
    @Override
    public void handle() {
        boolean sprinting = actionTracker.getSprintingTicks() > 10;
        boolean eating = actionTracker.isEating();

        boolean exempt = teleportTracker.isTeleport() || collisionTracker.isInAir() ||
                playerData.getPlayer().getAllowFlight() || playerData.getPlayer().isInsideVehicle();

        if (exempt) return;

        boolean invalid = sprinting && eating;

        if (invalid) {
            if (increaseBuffer() > 12) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(3);
        }
    }
}
