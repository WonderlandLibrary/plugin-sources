package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class MotionA extends PositionCheck {
    public MotionA(PlayerData playerData) {
        super(playerData, "Motion A", "Y-Port Motion Check", new ViolationHandler(15, 300L), Category.MOVEMENT, SubCategory.MOTION, 3);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport() || collisionTracker.isOnSlime() || collisionTracker.isInWeb() || collisionTracker.isOnIce() || Hood.get().isLagging();
        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY();

        boolean invalid = deltaY == -lastDeltaY && deltaY != 0.0;

        if (invalid && !exempt) {
            if (increaseBuffer() > 4.0) {
                handleViolation(new DetailedPlayerViolation(this,"M " + format(deltaY)));
                staffAlert(new PlayerViolation(this));
            }
        } else {
            resetBuffer();
        }
    }
}