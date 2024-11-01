package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class MotionM extends PositionCheck {
    public MotionM(PlayerData playerData) {
        super(playerData, "Motion M", "Large horizontal", new ViolationHandler(15, 3000), Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();


        boolean exempt = teleportTracker.isTeleport() || System.currentTimeMillis() - playerData.getLastJoin() < 3000L
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater();
        if (exempt) {
            resetBuffer();
            return;
        }

        boolean teleport = teleportTracker.isTeleport();
        boolean invalid = deltaXZ > 10.0;

        if (invalid && !teleport) {
            if (increaseBuffer() > 10) {
                handleViolation(new DetailedPlayerViolation(this, "DXZ " + deltaXZ));
                resetBuffer();
            }
        } else {
            vl -= vl > 0 ? 1 : 0;
        }
    }
}
