package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionL extends PositionCheck {

    public MotionL(PlayerData playerData) {
        super(playerData, "Motion L", "Large Y movement", new ViolationHandler(15, 3000), Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        double deltaY = Math.abs(positionTracker.getDeltaY());
        boolean exempt = teleportTracker.isTeleport() || System.currentTimeMillis() - playerData.getLastJoin() < 3000L || positionTracker.getLastY() < 4.0;
        if (exempt) {
            resetBuffer();
            return;
        }
        boolean invalid = deltaY > 10.0;
        if (invalid) {
            if (increaseBuffer() > 10) {
                handleViolation(new DetailedPlayerViolation(this,"Y " + deltaY));
                resetBuffer();
            }
        } else {
            vl -= vl > 0 ? 1 : 0;
        }
    }
}
