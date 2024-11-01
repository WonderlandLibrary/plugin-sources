package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimAssistA extends RotationCheck {
    private float suspiciousYaw;

    public AimAssistA(PlayerData playerData) {
        super(playerData, "Aim Assist A", "Rounded Yaw Check", new ViolationHandler(2, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 20) {
            return;
        }

        float yawChange = rotationTracker.getDeltaYaw();

        if (yawChange > 1F && Math.round(yawChange) == yawChange && yawChange % 1.5F != 0F) {
            if (yawChange == suspiciousYaw) {
                handleViolation(new DetailedPlayerViolation(this, "Y " + yawChange));
                if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
            }

            suspiciousYaw = Math.round(yawChange);
        } else {
            suspiciousYaw = 0F;
        }
    }
}