package com.elevatemc.anticheat.base.check.impl.fight.killaura;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.elevatemc.anticheat.util.math.MathUtil;

public class KillAuraC extends RotationCheck {

    public KillAuraC(PlayerData playerData) {
        super(playerData, "Aura C", "Invalid rotation modulo", new ViolationHandler(3, 3000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 20 || rotationTracker.isZooming()) {
            return;
        }
        double deltaYaw = rotationTracker.getDeltaYaw();
        double deltaPitch = rotationTracker.getDeltaPitch();
        double lastDeltaPitch = rotationTracker.getDeltaPitch();

        double divisorPitch = (double) MathUtil.getGcd((long)(deltaPitch * MathUtil.EXPANDER), (long)(lastDeltaPitch * MathUtil.EXPANDER));
        double constantPitch = divisorPitch / MathUtil.EXPANDER;

        double currentY = deltaPitch / constantPitch;
        double previousY = lastDeltaPitch / constantPitch;

        if (deltaYaw > 0.0F && deltaPitch > 0.0F && deltaYaw < 20.0F && deltaPitch < 20.0F) {
            double moduloY = currentY % previousY;
            double floorModuloY = Math.abs(Math.floor(moduloY) - moduloY);
            if (Double.isNaN(floorModuloY) || floorModuloY != 0.0) {
                if (increaseBuffer() > 4.0) {
                    multiplyBuffer(.5);
                    handleViolation(new DetailedPlayerViolation(this,"FMY " + floorModuloY));
                    if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                }
            } else {
                decreaseBufferBy(.25);
            }
        }
    }
}

