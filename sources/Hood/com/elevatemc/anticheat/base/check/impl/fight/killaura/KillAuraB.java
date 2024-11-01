package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
public class KillAuraB extends PositionCheck {


    public KillAuraB(PlayerData playerData) {
        super(playerData, "Aura B", "Acceleration does not match rotation", new ViolationHandler(2, 30000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 4) {

            double acceleration = positionTracker.getAcceleration();
            double deltaXZ = positionTracker.getDeltaXZ();

            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            boolean invalid = acceleration < .001 && deltaYaw > 10 && deltaPitch > 26.5D && deltaXZ > 0;

            if (invalid) {
                if (increaseBuffer() > 3) {
                    multiplyBuffer(.25);
                    handleViolation(new DetailedPlayerViolation(this, acceleration));
                }
            } else {
                decreaseBufferBy(.35);
            }
        }
    }
}
