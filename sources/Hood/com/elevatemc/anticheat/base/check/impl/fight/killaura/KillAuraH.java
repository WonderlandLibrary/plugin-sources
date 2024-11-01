package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class KillAuraH extends PositionCheck {
    double lastDeltaXZ = 0;

    public KillAuraH(PlayerData playerData) {
        super(playerData, "Aura H", "Invalid acceleration", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5 && actionTracker.isSprinting()) {
            double x = positionTracker.getLastDeltaX(), z = positionTracker.getLastDeltaZ();
            double predictedX = x * 0.6;
            double predictedZ = z * 0.6;
            double deltaX = Math.abs(predictedX - x);
            double deltaZ = Math.abs(predictedZ - z);
            double deltaXZ = deltaX + deltaZ;
            double acceleration = Math.abs(deltaXZ - lastDeltaXZ);
            if (acceleration < 0.01) {
                if (deltaX > 0.089 || deltaZ > 0.089) {
                    if (increaseBuffer() > 15.0) {
                        handleViolation(new DetailedPlayerViolation(this,"DX " + deltaX + " DZ " + deltaZ));
                    }
                } else {
                    decreaseBufferBy(2.0D);
                }
            } else {
                decreaseBufferBy(1.5D);
            }
            this.lastDeltaXZ = deltaXZ;
        }
    }
}
