package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class KillAuraG extends RotationCheck {

    public KillAuraG(PlayerData playerData) {
        super(playerData, "Aura G", "Acceleration does not match rotations", new ViolationHandler(5,
                3000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        float deltaYaw = rotationTracker.getDeltaYaw();
        float deltaPitch = rotationTracker.getDeltaPitch();

        double acceleration = positionTracker.getAcceleration();

        boolean invalid = MathUtil.isScientificNotation(acceleration) && deltaYaw > 10.0f && deltaPitch > 24.5D;

        if (invalid) {
            if (increaseBuffer() > 2) {
                handleViolation(new DetailedPlayerViolation(this,"Y " + format(deltaYaw) + " P " + format(deltaPitch)));
                resetBuffer();
            }
        }
    }
}
