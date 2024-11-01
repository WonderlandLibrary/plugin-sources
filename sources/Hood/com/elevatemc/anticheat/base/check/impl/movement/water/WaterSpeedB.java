package com.elevatemc.anticheat.base.check.impl.movement.water;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class WaterSpeedB extends PositionCheck {

    public WaterSpeedB(PlayerData playerData) {
        super(playerData, "Water Speed Type B", "Checks if your walking on water", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.WATERSPEED,0);
    }

    @Override
    public void handle() {
        boolean onLiquid = collisionTracker.isInWater();

        boolean clientGround = collisionTracker.isClientGround();
        boolean serverGround = collisionTracker.isServerGround();

        boolean exempt = playerData.getPlayer().isInsideVehicle()
                || playerData.getPlayer().getAllowFlight()
                || !positionTracker.isInLoadedChunk();
        boolean invalid = (clientGround || serverGround) && onLiquid && positionTracker.getDeltaY() != 0.0;

        if (invalid && !exempt) {
            if (increaseBuffer() > 5) {
                handleViolation(new DetailedPlayerViolation(this, "L " + onLiquid + " C " + clientGround));
            }
        } else {
            decreaseBufferBy(0.50);
        }
    }
}
