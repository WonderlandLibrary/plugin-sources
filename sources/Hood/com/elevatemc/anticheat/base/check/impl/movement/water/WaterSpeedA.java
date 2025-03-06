package com.elevatemc.anticheat.base.check.impl.movement.water;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class WaterSpeedA extends PositionCheck {

    int ticksInWater;
    public WaterSpeedA(PlayerData playerData) {
        super(playerData, "Water Speed Type A", "Checks for waterspeed", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.WATERSPEED,0);
    }

    @Override
    public void handle() {
        Player player = playerData.getPlayer();

        if (!collisionTracker.isInWater()) { // Its a water speed check [!] [!] [!]
            ticksInWater = 0; // Needed
            return;
        }
        // We'll account for velocity later it's no worries.
        if (velocityTracker.getTicksSinceVelocity() < 40 || player.getAllowFlight()) return;

        double deltaXZ = positionTracker.getDeltaXZ(); // We'll use this to measure for speed in liquid
        double deltaY = positionTracker.getDeltaY();
        double lastDeltaY = positionTracker.getLastDeltaY(); // we can use both current and last to check if they are holding space.

        double max = 0.10; // maximum default speed in liquid if their deltaY is 0.0 (they're on a block in liquid)

        /*
        This was retarded don't mind :>
        if (deltaY == 0.0 && isWearingDepthStrider(player) && !actionTracker.isSprinting()) {
            max += 0.07;
        } else if (deltaY == 0.0 && isWearingDepthStrider(player) && actionTracker.isSprinting()) {
            max += 0.096;
        }
         */

        if (deltaY != 0.0) {
            max += 0.05; // they're going up and down
        }

        /*
            If they jump into the water, their deltaXZ goes to roughly 0.28
            It takes about 13 ticks for the delta to reset, but we'll put 15 to be safe.
         */
        if (ticksInWater < 15) {
            max = deltaXZ;
        }

        if (isWearingDepthStrider(player)) {
            switch (playerData.getPlayer().getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER)) {
                case 1: {
                    max += 0.07; // speed increase.
                    break;
                }
                case 2: {
                    max += 0.15;
                    break;
                }
                case 3: {
                    // acts as if the player is on land as is normal player basespeed (0.28)
                    max += 0.19;
                    break;
                }
            }
        }


        /*
        debug("&7&m------&astart&7&m-------");
        debug("1: " + deltaXZ);
        debug("2: " + deltaY);
        debug("3: " + lastDeltaY);
        debug("5: " + max);
        debug("&7&m------&cend&7&m-------");
         */
        if (deltaXZ > max) {
            if (increaseBuffer() > 4) {
              //  handleViolation(new DetailedPlayerViolation(this, "XZ " + format(deltaXZ) + " Y " + format(lastDeltaY)));
            }
        } else {
            decreaseBuffer();
        }

        ticksInWater++;
    }

    public boolean isWearingDepthStrider(final Player player) {
        return player.getInventory().getBoots() != null && player.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) > 0;
    }
}
