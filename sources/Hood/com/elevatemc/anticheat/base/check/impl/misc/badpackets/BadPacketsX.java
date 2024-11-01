package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class BadPacketsX extends PositionCheck {

    public BadPacketsX(PlayerData playerData) {
        super(playerData, "Bad Packets X", "Sprinting whilst in inventory", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.BAD_PACKETS, 3);
    }
    @Override
    public void handle() {

        boolean exempt = playerData.getTicksExisted() < 4 || teleportTracker.teleport || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().getAllowFlight() || collisionTracker.isOnSlime()
                || collisionTracker.isInWater() || collisionTracker.isInWeb() || collisionTracker.isNearWall()
                || collisionTracker.isNearPiston();

        if (predictionTracker.getDistance() > (1.0E-14 * 0.03D) && !exempt) {
            if (increaseBuffer() > 30) {
                handleViolation(new DetailedPlayerViolation(this, "D " + predictionTracker.getDistance()));
            }
        } else {
            decreaseBufferBy(1.5);
        }

    }
}
