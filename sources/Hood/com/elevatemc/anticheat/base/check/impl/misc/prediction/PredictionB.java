package com.elevatemc.anticheat.base.check.impl.misc.prediction;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class PredictionB extends PositionCheck {

    public PredictionB(PlayerData playerData) {
        super(playerData, "Difference A", "", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.SIMULATION, 0);
    }

    @Override
    public void handle() {
        if (teleportTracker.isTeleport() || playerData.getTicksExisted() < 100 || actionTracker.getRespawnTicks() < 10 || playerData.getPlayer().getAllowFlight()) return;

        double deltaXZ = positionTracker.getDeltaXZ();

        double motionX = predictionTracker.getMotionX();
        double motionZ = predictionTracker.getMotionZ();

        double motionXZ = Math.sqrt(motionX * motionX + motionZ * motionZ);
        double motionDifference = deltaXZ - motionXZ;

        if (predictionTracker.getDistance() != Double.MAX_VALUE) {
            if (Math.abs(motionDifference) > 1.5 && predictionTracker.getDistance() > 1) handleViolation(new DetailedPlayerViolation(this, "M " + motionDifference + " D " + predictionTracker.getDistance()));
        }
    }
}
