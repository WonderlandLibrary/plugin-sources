package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;

public class AimBadA extends RotationCheck {
    public AimBadA(PlayerData playerData) {
        super(playerData, "Aim Bad A", "Bad bad bad bad bad", new ViolationHandler(2, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (!rotationTracker.isZooming()) {
                /*
                    Bored so let's do an explanation to this check because it's easy

                    This checks for when the deltaPitch is exponentially small in comparison to the deltaYaw

                    We grab our deltaYaw/Pitch from our processor

                    and proceed to make our check based on the following explanation:

                    - The players' rotations should be linear, moving your mouse an exponentially small amount
                    - on 1 axis and a large amount on the other is basically impossible

                    - the deltaYaw will be a large rotation where the deltaPitch will be in shambles.

                    Explanation done, Saif out.

                 */
            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            boolean invalid = deltaPitch < .0001 && deltaPitch > 0 && deltaYaw > .5F;

            if (invalid) {
                if (increaseBuffer() > 6.0) {
                    handleViolation(new DetailedPlayerViolation(this, "P " + deltaPitch + " Y " + deltaYaw));
                    if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                    multiplyBuffer(.25);
                }
            } else {
                decreaseBufferBy(.25);
            }
        }
    }
}
