package com.elevatemc.anticheat.base.check.impl.fight.aimassist.bad;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class AimBadH extends RotationCheck {

    public AimBadH(PlayerData playerData) {
        super(playerData, "Aim Bad H", "Invalid rotation constant", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }
    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 5 || teleportTracker.isTeleport()) return;

        float yaw = rotationTracker.getYaw(), pitch = rotationTracker.getPitch();
        float constant = rotationTracker.getSensitivity() / 142.0F;

        float moduloPitch = Math.abs(pitch % constant);
        float moduloYaw = Math.abs(yaw % constant);
        if (moduloPitch == 0.0 && moduloYaw == 0.0) {
            if (increaseBuffer() > 30) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(0.05);
        }
    }
}
