package com.elevatemc.anticheat.base.check.impl.movement.water;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class WaterSpeedC extends PositionCheck {

    public WaterSpeedC(PlayerData playerData) {
        super(playerData, "Water Speed Type C", "Walking on water", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.WATERSPEED,0);
    }

    @Override
    public void handle() {
        if (playerData.getPlayer().getAllowFlight()) return;
        double deltaX = positionTracker.getDeltaX();
        double deltaY = positionTracker.getDeltaY();
        double deltaZ = positionTracker.getDeltaZ();

        boolean onGround = collisionTracker.isClientGround() || collisionTracker.isServerGround();
        boolean inLiquid = collisionTracker.isInWater();

        boolean stationary = deltaX % 1.0 == 0.0 && deltaZ % 1.0 == 0.0;
        boolean invalid = deltaY > 0.0 && !onGround && inLiquid && stationary;

        if (invalid) {
            double deltaXZ = positionTracker.getDeltaXZ();
            if (deltaXZ > 0.1) {
                handleViolation(new DetailedPlayerViolation(this,""));
            }
        }
    }
}
