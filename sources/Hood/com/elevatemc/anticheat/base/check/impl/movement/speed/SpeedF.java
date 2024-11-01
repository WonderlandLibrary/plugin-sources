package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import org.bukkit.GameMode;

public class SpeedF extends PositionCheck {

    public SpeedF(PlayerData playerData) {
        super(playerData, "Speed F", "Invalid deltas", new ViolationHandler(15, 300L), Category.MOVEMENT, SubCategory.SPEED,5);
    }

    @Override
    public void handle() {

        double maxDelta = 0.41;
        double deltaY = positionTracker.getDeltaY(), lastDeltaY = positionTracker.getLastDeltaY();

        if (deltaY == 0.009999990463242625 || deltaY == 0.41999998688697815 || deltaY == 0.35999999999999943
                || deltaY == 0.07840000152587834 || deltaY == 0.019499999999993634 || deltaY == 0.0019999980926428407
                || deltaY == 0.33319999363422337 || deltaY == 0.039851049416299134 || deltaY == 0.039345972337798685
                || deltaY == lastDeltaY || deltaY == 0.33319999363422426) return;
        boolean exempt = teleportTracker.isTeleport()
                || !playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL)
                || playerData.getPlayer().getAllowFlight()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava();
        if (!positionTracker.isSentMotion()) return;

        maxDelta += attributeTracker.getJumpBoost() * 0.2;
        maxDelta += attributeTracker.getSpeedBoost() * 0.0625;

        if (deltaY == 0.07840000152587923) {
            maxDelta += 0.125;
        }
        if (deltaY == 0.41999998688697815) {
            maxDelta += 0.1;
        }

        if (collisionTracker.isOnStairs()) {
            maxDelta += 0.125;
        }
        if (deltaY == 0.5) {
            maxDelta += 0.1;
        }
        if (collisionTracker.isOnSlabs()) {
            maxDelta += 0.1;
        }
        if (keepAliveTracker.getKeepAlivePing() > 500) {
            maxDelta += 10.0;
        }
        if (velocityTracker.getTicksSinceVelocity() < 15) {
            maxDelta += 1;
        }
        if (collisionTracker.isOnSlime()) {
            maxDelta += 0.05;
        }
        if (deltaY == 0.0) {
            maxDelta += 0.2;
        }
        if (velocityTracker.isOnFirstTick()) {
            maxDelta += 0.8;
        }
        if ((collisionTracker.isOnIce() || collisionTracker.isLastOnIce()) && collisionTracker.isOnSlabs()) {
            maxDelta += 0.25;
        }
        if ((deltaY == 0.07840000152587834 && collisionTracker.isOnSlabs() || (deltaY == 0.07840000152587923 && collisionTracker.isOnSlabs()))) {
            maxDelta += 0.03;
        }
        if (positionTracker.getDeltaY() == 0.5 && collisionTracker.isOnSlabs()) {
            maxDelta += 0.5;
        }
        if (collisionTracker.isOnIce()) {
            maxDelta += 0.55;
        }
        if (attributeTracker.getWalkSpeed() > 0.2f) {
            maxDelta += attributeTracker.getWalkSpeed() * 0.28634357f * 10.0f;
        }
        if (collisionTracker.isVerticallyColliding()) {
            maxDelta += 0.55;
        }
        if (collisionTracker.isNearWall() && positionTracker.getDeltaY() < 0.2) {
            maxDelta += 0.35;
        }
        double deltaXZ = positionTracker.getDeltaXZ();

        if (deltaXZ > maxDelta + 1.0 && !exempt) {
            if (increaseBuffer() > 1)
                handleViolation(new DetailedPlayerViolation(this,"XZ " + format(positionTracker.getDeltaXZ()) + " M " + maxDelta));
        } else {
            decreaseBuffer();
        }
    }
}
