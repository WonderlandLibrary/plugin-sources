package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistB extends RotationCheck {
    public AimAssistB(PlayerData playerData) {
        super(playerData, "Aim Assist B", "", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10) return;

        int sensitivity = rotationTracker.getSensitivity();

        float yawAccel = rotationTracker.getYawAccel();
        float deltaPitch = rotationTracker.getDeltaPitch();
        float deltaYaw = rotationTracker.getDeltaYaw();

        debug("Acceleration " + yawAccel + " P " + deltaPitch + " Y " + deltaYaw + " S " + sensitivity);

        boolean aura = yawAccel < 0.1F && deltaPitch > 0.5F && deltaYaw > 0.5F && sensitivity == -1;

        if (aura && !teleportTracker.isTeleport()) {
            if (increaseBuffer() > 5) {
                handleViolation(new DetailedPlayerViolation(this, "Accel " + format(yawAccel) + " Y " + deltaYaw + " P " + deltaPitch));
            }
        } else {
            decreaseBuffer();
        }
    }
}