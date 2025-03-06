package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimBadD extends RotationCheck {
    public AimBadD(PlayerData playerData) {
        super(playerData, "Aim Bad D", "Bad bad bad bad bad", new ViolationHandler(5, 30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 8) {
            if (rotationTracker.getSensitivity() == -1) return;

            float deltaYaw = rotationTracker.getDeltaYaw();
            float lastDeltaYaw = rotationTracker.getLastDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            boolean invalid = MathUtil.isScientificNotation(deltaYaw) && MathUtil.isScientificNotation(lastDeltaYaw) && deltaPitch > 0.5;

            if (invalid) {
                if (increaseBuffer() > 4.0) {
                    handleViolation(new DetailedPlayerViolation(this,"YAW " + deltaYaw + " PITCH " + deltaPitch));
                }
            }
        }
    }
}
