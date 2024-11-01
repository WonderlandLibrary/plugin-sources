package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import org.bukkit.GameMode;

public class SpeedN extends PositionCheck {

    public SpeedN(PlayerData playerData) {
        super(playerData, "Speed N", "Breached limit", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SPEED, 0);
    }

    @Override
    public void handle() {

        boolean exempt = playerData.getPlayer().getAllowFlight() ||
                teleportTracker.isTeleport() ||
                playerData.getPlayer().isInsideVehicle() ||
                playerData.getPlayer().isFlying() ||
                playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE);

        if (exempt) return;

        double base;

        if (actionTracker.getSprintingTicks() < 20) {
            base = PlayerUtil.getBaseSpeed(playerData, 0.292F);
        } else {
            base = PlayerUtil.getBaseSpeed(playerData, 0.23F);
        }

        double deltaXZ = positionTracker.getDeltaXZ();

        // Compensation start
        if (collisionTracker.isOnIce() || collisionTracker.isOnSlime()) {
            base += 0.34;
        }
        if (collisionTracker.isVerticallyColliding()) {
            base += 0.7;
        }
        if (velocityTracker.getTicksSinceVelocity() < 10) {
            base += MathUtil.hypot(Math.abs(velocityTracker.getVelocityX()), velocityTracker.getVelocityZ()) + 0.15;
        }

        int groundTicks = collisionTracker.getClientGroundTicks();
        int airTicks = collisionTracker.getClientAirTicks();

        double difference = deltaXZ - base;

        if (groundTicks > 15 && deltaXZ > base && airTicks == 0 && difference > 0.20D) {
            if (increaseBuffer() > 10) handleViolation(new DetailedPlayerViolation(this, "D " + format(difference)));
        } else {
            decreaseBufferBy(2);
        }
    }
}
