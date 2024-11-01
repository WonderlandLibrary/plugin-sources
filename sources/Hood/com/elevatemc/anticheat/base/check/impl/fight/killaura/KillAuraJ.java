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

public class KillAuraJ extends RotationCheck {

    public KillAuraJ(PlayerData playerData) {
        super(playerData, "Aura J", "", new ViolationHandler(6,3000L),
                Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5 && !rotationTracker.isZooming()) {
            float deltaPitch = MathUtil.getDistanceBetweenAngles(rotationTracker.getPitch(), rotationTracker.getLastPitch());
            float deltaYaw = MathUtil.getDistanceBetweenAngles(rotationTracker.getYaw(), rotationTracker.getLastYaw());

            double modulo = deltaYaw % deltaPitch;

            boolean invalid = (Double.isNaN(modulo) && deltaYaw > 1.65) || (Double.isNaN(modulo) && deltaYaw < 0.065 && deltaYaw > 0.0f);

            if (rotationTracker.getDeltaYaw() > 20.0F && rotationTracker.getDeltaPitch() > 20.0F) return;

            if (invalid) {
                if (increaseBuffer() > 12.0) {
                    handleViolation(new DetailedPlayerViolation(this,  "Y " + format(deltaYaw) + " P " + format(deltaPitch)));
                    multiplyBuffer(.5);
                    if (vl == 2) {
                        if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                    }
                }
            } else {
                decreaseBufferBy(1.5);
            }
        }
    }
}
