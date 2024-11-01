package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class MotionH1 extends PositionCheck {

    public MotionH1(PlayerData playerData) {
        super(playerData, "Motion H1", "Omni Directional Sprint", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.MOTION, 0);
    }

    @Override
    public void handle() {
        boolean exempt = collisionTracker.isInWater() || collisionTracker.isInWeb()
                || velocityTracker.isOnFirstTick()
                || !collisionTracker.isClientGround();
        if (exempt) return;

        final Vector moveDeltaXZ = new Vector(positionTracker.getDeltaX(), 0, positionTracker.getDeltaZ());
        final Vector direction = new Vector(-Math.sin(playerData.getPlayer().getEyeLocation().getYaw() * Math.PI / 180.0F) * (float) 1 * 0.5F, 0, Math.cos(playerData.getPlayer().getEyeLocation().getYaw() * Math.PI / 180.0F) * (float) 1 * 0.5F);

        double delta = moveDeltaXZ.distance(direction);
        double maxDelta = playerData.getPlayer().getWalkSpeed() > 0.2f ? .23 * 1 + ((playerData.getPlayer().getWalkSpeed() / 0.2f) * 0.36) : 0.23 + (attributeTracker.getSpeedBoost() * 0.062f);
        double deltaXZ = positionTracker.getDeltaXZ();

        boolean sprinting = actionTracker.isSprinting();
        boolean invalid = delta >= maxDelta && deltaXZ > 0.1 && sprinting;

        if (invalid) {
           // if (increaseBuffer() > 7) handleViolation(new DetailedPlayerViolation(this, "P " + delta + " M " + maxDelta + " C " + collisionTracker.isClientGround() + " T " + teleportTracker.getSinceTeleportTicks() + " V " + velocityTracker.isOnFirstTick() + " F " + actionTracker.getLastAttack()));
        }
    }
}
