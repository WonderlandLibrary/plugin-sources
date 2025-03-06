package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class PredictionE extends PositionCheck {

    public PredictionE(PlayerData playerData) {
        super(playerData, "Aura Emulation Slowdown", "", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 1) {
            return;
        }
        boolean exempt = playerData.getPlayer().isInsideVehicle() || playerData.getPlayer().getAllowFlight()
                || collisionTracker.isNearPiston() || collisionTracker.isOnSlime() || positionTracker.isPossiblyZeroThree()
                || collisionTracker.isInWater() || teleportTracker.isTeleport();

        boolean invalid = predictionTracker.isSlowdown() && predictionTracker.isSprinting() && collisionTracker.isClientGround();
        if (invalid && !exempt) {
            if (increaseBuffer() > 12) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(1.2);
        }
    }
}