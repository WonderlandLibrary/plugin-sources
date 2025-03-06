package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class SpeedK extends PositionCheck {

    public SpeedK(PlayerData playerData) {
        super(playerData, "Speed K", "Invalid air movement", new ViolationHandler(10, 300), Category.MOVEMENT, SubCategory.SPEED,1);
    }

    @Override
    public void handle() {
        if (attributeTracker.getWalkSpeed() < 0.2f) return;

        double deltaXZ = positionTracker.getDeltaXZ();
        double lastDeltaXZ = positionTracker.getLastDeltaXZ();
        boolean sprinting = actionTracker.isSprinting();

        double prediction = lastDeltaXZ * 0.91f + (sprinting ? 0.026 : 0.02);
        double difference = deltaXZ - prediction;

        int airTicks = collisionTracker.getClientAirTicks();

        boolean exempt = teleportTracker.isTeleport() || Hood.get().isLagging()
                || playerData.getPlayer().isDead()
                || playerData.getPlayer().getAllowFlight()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava();
        boolean hasJump = attributeTracker.getJumpBoost() > 0;
        if (!positionTracker.isSentMotion()) return;

        if (airTicks > 3 && !exempt && !hasJump) {
            if (difference > 1.0E-12 && deltaXZ > 0.2) {
                if (increaseBuffer() > 6.0) {
                    handleViolation(new DetailedPlayerViolation(this,"D " + format(difference) + " T " + airTicks));
                    staffAlert(new PlayerViolation(this));
                    multiplyBuffer(.5);
                }
            } else {
                decreaseBufferBy(.35);
            }
        }
    }
}
