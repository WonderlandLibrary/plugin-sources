package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimBadC extends RotationCheck {
    public AimBadC(PlayerData playerData) {
        super(playerData, "Aim Bad C", "Bad bad bad bad bad", new ViolationHandler(2, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (!rotationTracker.isZooming() && actionTracker.getLastAttack() < 10) {
            float deltaYaw = rotationTracker.getDeltaYaw();

            if (deltaYaw == 0.0f) {
                return;
            }

            boolean invalid = deltaYaw % 0.25 == 0.0;
            boolean exempt = teleportTracker.getSinceTeleportTicks() < 10;

            if (invalid && !exempt) {
                if (increaseBuffer() > 8.0) {
                    handleViolation(new DetailedPlayerViolation(this,"YAW " + deltaYaw + " LAST " + rotationTracker.getLastDeltaYaw()));
                    multiplyBuffer(.5);
                    if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                }
            } else {
                decreaseBufferBy(.75);
            }
        }
    }
}
