package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimBadF extends RotationCheck {

    public AimBadF(PlayerData playerData) {
        super(playerData, "Aim Bad F", "", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10 || teleportTracker.isTeleport() || playerData.getPlayer().isDead()) return;

        float pitch = rotationTracker.getPitch();
        float deltaYaw = rotationTracker.getDeltaYaw();
        float deltaPitch = rotationTracker.getDeltaPitch();

        if (deltaYaw > 0 && deltaPitch < 0.001 && pitch == 0) {
            handleViolation(new DetailedPlayerViolation(this, "Y " + format(deltaYaw) + " DP " + format(deltaPitch) + " P 0"));
        }
    }
}
