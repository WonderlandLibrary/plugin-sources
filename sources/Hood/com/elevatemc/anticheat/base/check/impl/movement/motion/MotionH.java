package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import org.bukkit.util.Vector;

public class MotionH extends PositionCheck {

    public MotionH(PlayerData playerData) {
        super(playerData, "Motion H", "Omni-directional movement", new ViolationHandler(15, 300), Category.MOVEMENT, SubCategory.MOTION, 15);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport() || Hood.get().isLagging()
                || playerData.getPlayer().getAllowFlight()
                || velocityTracker.isOnFirstTick();

        if (!exempt && !actionTracker.isSprinting() && (!collisionTracker.isClientGround() || !collisionTracker.isServerGround())) {

            float deltaYaw = rotationTracker.getDeltaYaw(), deltaPitch = rotationTracker.getDeltaPitch();

            if (deltaPitch > 30.0f || deltaYaw > 30.0f) resetBuffer();

            Vector movement = new Vector(positionTracker.getDeltaX(), 0.0, positionTracker.getDeltaZ());
            Vector direction = new Vector(-Math.sin(playerData.getPlayer().getPlayer().getEyeLocation().getYaw() * 3.1415927f / 180.0f) * 1.0 * 0.5, 0.0, Math.cos(playerData.getPlayer().getPlayer().getEyeLocation().getYaw() * 3.1415927f / 180.0f) * 1.0 * 0.5);

            double delta = movement.distanceSquared(direction);
            double maxDelta = 0.294;

            if (actionTracker.getLastAttack() < 4) {
                maxDelta += 0.12;
            }
            if (attributeTracker.getWalkSpeed() > 0.2f) {
                maxDelta += attributeTracker.getWalkSpeed() * 0.28634357f * 4.0f;
            }
            if (collisionTracker.isOnIce()) {
                maxDelta += 0.42;
            }

            boolean invalid = delta > maxDelta + 0.7 && positionTracker.getDeltaXZ() > 0.2095 && actionTracker.isSprinting();
            boolean move = Math.abs(predictionTracker.getMoveForward()) == 0.98 || predictionTracker.getMoveForward() == 0.0 && predictionTracker.getMoveStrafe() == -0.98;

            if (invalid && move) {
                if (increaseBuffer() > 15.0) {
                    handleViolation(new DetailedPlayerViolation(this,"D " + delta + " M " + maxDelta));
                }
            } else {
                decreaseBufferBy(2);
            }
        }
    }
}
