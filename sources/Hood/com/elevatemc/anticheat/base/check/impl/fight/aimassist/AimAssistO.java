package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistO extends RotationCheck {

    public AimAssistO(PlayerData playerData) {
        super(playerData, "Aim Assist O", "Invalid resolution change [GCD]", new ViolationHandler(3, 3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 8 && !rotationTracker.isZooming()) {
            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            float lastDeltaYaw = rotationTracker.getLastDeltaYaw();
            float lastDeltaPitch = rotationTracker.getLastDeltaPitch();

            float yAccel = Math.abs(deltaYaw - lastDeltaYaw);
            float pAccel = Math.abs(deltaPitch - lastDeltaPitch);

            if (yAccel < 0.1 || pAccel < 0.1) return;

            if (deltaYaw < 0.35 || deltaPitch < 0.35 || deltaYaw > 12.5 || deltaPitch > 12.5) return;

            double gcd = MathUtil.getGcd(deltaPitch, lastDeltaPitch);

            final boolean exempt = teleportTracker.isTeleport();
            final boolean jumped = positionTracker.isJumped();

            if (gcd < 0.007 && !exempt && !jumped) {
                if (increaseBuffer() > 13.0) {
                    handleViolation(new DetailedPlayerViolation(this, "DY " + deltaYaw + "  DP" + deltaPitch));
                }
            } else {
                decreaseBufferBy(1.25);
            }
        }
    }
}
