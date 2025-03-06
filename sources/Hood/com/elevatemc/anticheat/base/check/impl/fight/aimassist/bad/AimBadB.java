package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimBadB extends RotationCheck {
    public AimBadB(PlayerData playerData) {
        super(playerData, "Aim Bad B", "Bad bad bad bad bad", new ViolationHandler(2, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }
    @Override
    public void handle() {
        if (!rotationTracker.isZooming()) {

            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            boolean invalid = deltaYaw < .0001 && deltaYaw > 0 && deltaPitch > .5F;

            if (invalid) {
                if (increaseBuffer() > 6.0) {
                    handleViolation(new DetailedPlayerViolation(this,"P " + deltaPitch + " Y " + deltaYaw));
                    if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                    multiplyBuffer(.25);
                }
            } else {
                decreaseBufferBy(.25);
            }
        }
    }
}