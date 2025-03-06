package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimBadI extends RotationCheck {

    public AimBadI(PlayerData playerData) {
        super(playerData, "Aim Bad I", "Invalid rotation constant", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (playerData.getTicksExisted() < 4) return;
        if (actionTracker.getLastAttack() > 5) return;
        if (playerData.getPlayer().isInsideVehicle()) return;

        double deltaYaw = MathUtil.getDistanceBetweenAngles(rotationTracker.getLastYaw(), rotationTracker.getYaw());
        double deltaPitch = MathUtil.getDistanceBetweenAngles(rotationTracker.getLastPitch(), rotationTracker.getPitch());

        if (deltaYaw == 0.0 && deltaPitch == 0.0) {
            if (increaseBuffer() > 7) {
                handleViolation(new DetailedPlayerViolation(this, "T " + getBuffer()));
            }
        } else {
            resetBuffer();
        }
    }
}
