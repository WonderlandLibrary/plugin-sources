package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class SpeedE extends PositionCheck {

    public SpeedE(PlayerData playerData) {
        super(playerData, "Speed E", "Invalid air friction", new ViolationHandler(5, 3000L), Category.MOVEMENT, SubCategory.SPEED,3);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();
        double lastDeltaXZ = positionTracker.getLastDeltaXZ();
        boolean sprinting = actionTracker.isSprinting();

        double prediction = lastDeltaXZ * 0.91f + (sprinting ? 0.026 : 0.02);
        double difference = deltaXZ - prediction;

        int airTicks = collisionTracker.getClientAirTicks();

        boolean velocity = velocityTracker.getTicksSinceVelocity() < 10;
        boolean depthStrider = collisionTracker.isInWater() && isWearingDepthStrider(getPlayerData().getPlayer().getPlayer());
        if (!positionTracker.isSentMotion()) return;
        boolean exempt =  Hood.get().isLagging()
                || teleportTracker.isTeleport()
                || collisionTracker.isOnClimbable()
                || collisionTracker.isLastOnClimbable()
                || collisionTracker.isOnIce()
                || collisionTracker.isLastOnIce()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isOnSlime()
                || collisionTracker.isLastOnSlime()
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isInsideVehicle()
                || depthStrider;

        if ((airTicks > 2) && !velocity && !exempt) {
            if (difference > 1e-5) {
                if (increaseBuffer() > 7) {
                    handleViolation(new DetailedPlayerViolation(this,"DIFF " + format(difference) + " T " + airTicks));
                    multiplyBuffer(.5);
                }
            } else {
                decreaseBufferBy(.35);
            }
        }
    }

    public boolean isWearingDepthStrider(final Player player) {
        return player.getInventory().getBoots() != null && player.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) > 0;
    }
}
