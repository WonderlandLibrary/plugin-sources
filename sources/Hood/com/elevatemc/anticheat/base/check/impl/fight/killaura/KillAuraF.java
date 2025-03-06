package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class KillAuraF extends RotationCheck {

    public KillAuraF(PlayerData playerData) {
        super(playerData, "Aura F", "Invalid rotation acceleration",
                new ViolationHandler(3, 30000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5) {
            float yAcceleration = rotationTracker.getYawAccel();
            float pAcceleration = rotationTracker.getPitchAccel();

            float deltaPitch = rotationTracker.getDeltaPitch();
            float deltaYaw = rotationTracker.getDeltaYaw();

            if ((MathUtil.isScientificNotation(yAcceleration) || MathUtil.isScientificNotation(pAcceleration)) && (deltaPitch > 0.5 && deltaYaw < 0.003 || deltaYaw > 0.5 && deltaPitch < 0.003)) {
                if (increaseBuffer() > 5.0) {
                    handleViolation(new DetailedPlayerViolation(this, ""));
                }
            } else {
                decreaseBufferBy(.025);
            }
        }
    }
}
