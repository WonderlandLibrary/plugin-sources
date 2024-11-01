package com.elevatemc.anticheat.util.mcp;

import com.elevatemc.anticheat.base.PlayerData;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@UtilityClass
public class PlayerUtil {

    public AxisAlignedBB getBoundingBox(double x, double y, double z) {
        float width = 0.6F / 2.0F;

        return new AxisAlignedBB(x - width, y, z - width, x + width, y + 1.8F, z + width);
    }
    public static double getBaseGroundSpeed(final PlayerData data) {
        double speed = 0.288 + data.getAttributeTracker().getSpeedBoost() * 0.062f + (data.getPlayer().getWalkSpeed() - 0.2f) * 3.5;
        if (speed < 0.288) {
            speed = 0.288;
        }
        return speed;
    }


    public static boolean isLiquid(final Material material) {
        return material == Material.WATER || material == Material.LAVA || material == Material.STATIONARY_LAVA || material == Material.STATIONARY_WATER;
    }

    public boolean isHoldingSword(final Player player) {
        return player.getItemInHand().getType().toString().contains("SWORD");
    }

    public static boolean isLiquid(PlayerData data) {
        if (!data.getPositionTracker().isInLoadedChunk()) return false;
        Player player = data.getPlayer();
        double expand = 0.3;
        for (double x = -expand; x <= expand; x += expand) {
            for (double z = -expand; z <= expand; z += expand) {
                if (player.getLocation().clone().add(z, 0, x).getBlock().isLiquid()) {
                    return true;
                }
                if (player.getLocation().clone().add(z, player.getEyeLocation().getY(), x).getBlock().isLiquid()) {
                    return true;
                }
            }
        }
        return false;
    }


    public static float getBaseSpeed(final PlayerData data) {
        return 0.34f + (data.getAttributeTracker().getSpeedBoost() * 0.062f) + (data.getPlayer().getWalkSpeed() - 0.2f) * 1.6f;
    }

    public float getBaseSpeed(final PlayerData data, final float base) {
        return base + (data.getAttributeTracker().getSpeedBoost() * 0.062f) + ((data.getPlayer().getWalkSpeed() - 0.2f) * 1.6f);
    }
}
