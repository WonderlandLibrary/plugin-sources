package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistE extends RotationCheck {
    public AimAssistE(PlayerData playerData) {
        super(playerData, "Aim Assist E", "Invalid Yaw and Pitch Changes Check",
                new ViolationHandler(10, 300L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 4) {
            return;
        }
        if (rotationTracker.isZooming()) return;

        float deltaPitch = rotationTracker.getDeltaPitch();
        float deltaYaw = rotationTracker.getDeltaYaw();

        float gcd = (float) (rotationTracker.getGcdPitch() * MathUtil.EXPANDER);

        boolean validAngles = deltaYaw > .1F && deltaPitch > .1F && deltaPitch < 20F && deltaYaw < 20F;
        boolean invalid = gcd < 131072L;

        if (invalid && validAngles) {
            if (increaseBuffer() > 11) {
                handleViolation(new DetailedPlayerViolation(this, "GCD " + format(gcd) + " Y " + format(deltaYaw) + " P " + format(deltaPitch)));
                if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                multiplyBuffer(.35);
            }
        } else {
            decreaseBufferBy(1.25);
        }

    }
}

