package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
public class AimBadE extends RotationCheck {

    public AimBadE(PlayerData playerData) {
        super(playerData, "Aim Bad E", "", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        float deltaYaw = rotationTracker.getDeltaYaw();
        float deltaPitch = rotationTracker.getDeltaPitch();
        boolean teleporting = teleportTracker.isTeleport();

        if (teleporting) return;

        if (deltaYaw + deltaPitch == 0.0 && actionTracker.getLastAttack() < 5) {
            handleViolation(new DetailedPlayerViolation(this, "Y 0.0 P 0.0"));
        }
    }
}
