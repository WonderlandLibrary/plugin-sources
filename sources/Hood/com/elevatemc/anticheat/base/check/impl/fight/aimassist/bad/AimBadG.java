package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimBadG extends RotationCheck {

    public AimBadG(PlayerData playerData) {
        super(playerData, "Aim Bad G", "0 Pitch in combat", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (teleportTracker.isTeleport()) return;

        float pitch = rotationTracker.getPitch();

        if (actionTracker.getLastAttack() < 10 && pitch == 0) {
            handleViolation(new DetailedPlayerViolation(this, "P 0.0"));
        }
    }
}
