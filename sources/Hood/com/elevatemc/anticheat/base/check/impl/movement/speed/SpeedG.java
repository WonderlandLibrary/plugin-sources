package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class SpeedG extends PositionCheck {

    public SpeedG(PlayerData playerData) {
        super(playerData, "Speed G", "Invalid deltas", new ViolationHandler(15, 3000L), Category.MOVEMENT, SubCategory.SPEED,8);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport()
                || playerData.getPlayer().isFlying()
                || collisionTracker.isOnIce()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater()
                || playerData.getPlayer().getAllowFlight()
                || Hood.get().isLagging();

        if (exempt) return;

        double maxDelta = getMaxDelta();

        double deltaXZ = positionTracker.getDeltaXZ();

        if (deltaXZ > 2) return;
        if (!positionTracker.isSentMotion()) return;

        if (deltaXZ > maxDelta && collisionTracker.getClientGroundTicks() > 2) {
            if (increaseBuffer() > 10) {
                handleViolation(new DetailedPlayerViolation(this, format(positionTracker.getDeltaXZ()) + " > " + maxDelta));
            }
        }
    }

    private double getMaxDelta() {
        double maxDelta = 0.432;
        if (attributeTracker.getWalkSpeed() > 0.2f) {
            maxDelta += attributeTracker.getWalkSpeed() * 0.28634357f * 4.0f;
        }
        maxDelta += attributeTracker.getSpeedBoost() * 0.0625;
        if (collisionTracker.isOnStairs()) {
            maxDelta += 0.5;
        }
        if (collisionTracker.isVerticallyColliding()) {
            maxDelta += 0.35;
        }
        if (actionTracker.getLastAttack() <= 1) {
            maxDelta += 0.6;
        }
        if (keepAliveTracker.getKeepAlivePing() > 500 && velocityTracker.getTicksSinceVelocity() <= 3) {
            maxDelta += 10.5 * 1;
        }
        if (collisionTracker.isOnIce() || collisionTracker.isLastOnIce()) {
            maxDelta += 0.25;
        }
        if (!collisionTracker.isOnIce()) {
            maxDelta += 0.15;
        }
        return maxDelta;
    }
}
