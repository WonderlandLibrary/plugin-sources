package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class AimAssistK extends RotationCheck {

    public AimAssistK(PlayerData playerData) {
        super(playerData, "Aim Assist K", "Flawed divisor within rotation", new ViolationHandler(20,
                30000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport();

        float deltaYaw = rotationTracker.getDeltaYaw(), deltaPitch = rotationTracker.getDeltaPitch();

        if (deltaYaw < 1.0F && deltaPitch < 1.0F) return;
        if (rotationTracker.isZooming() || rotationTracker.getSensitivity() == -1) return;
        if (actionTracker.getLastAttack() > 4) return;

        if (deltaYaw > 0.1F && deltaPitch > 0.1F && deltaYaw < 20.0F && deltaPitch < 20.0F) {

            double divisorX = rotationTracker.getDivisorX();
            double divisorY = rotationTracker.getDivisorY();

            double maxDivisor = Math.max(divisorX, divisorY);

            // 131072 / 16777216
            boolean invalid = maxDivisor < 0.0078125F;

            if (invalid && !exempt) {
                if (increaseBuffer() > 24) handleViolation(new DetailedPlayerViolation(this, "D " + maxDivisor));
            } else {
                decreaseBufferBy(0.3D);
            }
        }
    }
}
