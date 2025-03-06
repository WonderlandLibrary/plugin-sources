package com.elevatemc.anticheat.util.block;

import com.elevatemc.anticheat.base.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
public class BlockUtil {

    public static List<Material> getBlocksAbove(final PlayerData data) {
        final List<Material> blocksAbove = new ArrayList<>();
        final List<Material> nearbyBlocks = data.getCollisionTracker().getNearbyBlocks();
        if (nearbyBlocks == null) {
            return blocksAbove;
        }
        blocksAbove.add(nearbyBlocks.get(9));
        blocksAbove.add(nearbyBlocks.get(10));
        blocksAbove.add(nearbyBlocks.get(11));
        blocksAbove.add(nearbyBlocks.get(21));
        blocksAbove.add(nearbyBlocks.get(22));
        blocksAbove.add(nearbyBlocks.get(23));
        blocksAbove.add(nearbyBlocks.get(33));
        blocksAbove.add(nearbyBlocks.get(34));
        blocksAbove.add(nearbyBlocks.get(35));
        return blocksAbove;
    }

    public static Block getBlockAsync(final World world, final int x, final int y, final int z) {
        if (!world.isChunkLoaded(x >> 4, z >> 4)) {
            return null;
        }
        return world.getBlockAt(x, y, z);
    }
    public static List<Material> getBlocksSide(final PlayerData data) {
        final List<Material> blocksAbove = new ArrayList<>();
        final List<Material> nearbyBlocks = data.getCollisionTracker().getNearbyBlocks();
        if (nearbyBlocks == null) {
            return blocksAbove;
        }
        blocksAbove.add(nearbyBlocks.get(3));
        blocksAbove.add(nearbyBlocks.get(4));
        blocksAbove.add(nearbyBlocks.get(5));
        blocksAbove.add(nearbyBlocks.get(6));
        blocksAbove.add(nearbyBlocks.get(7));
        blocksAbove.add(nearbyBlocks.get(8));
        blocksAbove.add(nearbyBlocks.get(9));
        blocksAbove.add(nearbyBlocks.get(10));
        blocksAbove.add(nearbyBlocks.get(11));
        blocksAbove.add(nearbyBlocks.get(15));
        blocksAbove.add(nearbyBlocks.get(17));
        blocksAbove.add(nearbyBlocks.get(18));
        blocksAbove.add(nearbyBlocks.get(20));
        blocksAbove.add(nearbyBlocks.get(21));
        blocksAbove.add(nearbyBlocks.get(22));
        blocksAbove.add(nearbyBlocks.get(23));
        blocksAbove.add(nearbyBlocks.get(27));
        blocksAbove.add(nearbyBlocks.get(28));
        blocksAbove.add(nearbyBlocks.get(29));
        blocksAbove.add(nearbyBlocks.get(30));
        blocksAbove.add(nearbyBlocks.get(31));
        blocksAbove.add(nearbyBlocks.get(32));
        blocksAbove.add(nearbyBlocks.get(33));
        blocksAbove.add(nearbyBlocks.get(34));
        blocksAbove.add(nearbyBlocks.get(35));

        return blocksAbove;
    }


    public static List<Material> getNearbyBlocksAsync(final Location location, final int radius) {
        final List<Material> nearby = new ArrayList<>();
        final int blockX = location.getBlockX();
        final int blockY = location.getBlockY();
        final int blockZ = location.getBlockZ();
        for (int x = blockX - radius; x <= blockX + radius; ++x) {
            for (int y = blockY - radius; y <= blockY + radius + 1; ++y) {
                for (int z = blockZ - radius; z <= blockZ + radius; ++z) {
                    nearby.add(getBlockTypeASync(location.getWorld(), x, y, z));
                }
            }
        }
        return nearby;
    }

    public static Material getBlockTypeASync(final World world, final int x, final int y, final int z) {
        if (y > 256) {
            return Material.AIR;
        }

        if (world.isChunkLoaded(x >> 4, z >> 4)) {
            return world.getBlockAt(x, y, z).getType();
        }
        return Material.AIR;
    }

    public static boolean isClimbable(final PlayerData data) {
        if (!data.getPositionTracker().isInLoadedChunk())return false;
        Location loc = data.getPlayer().getLocation();
        if (loc == null) return false;
        final double y = loc.getY();
        for (double x = -0.5; x <= 0.5; x += 0.2) {
            for (double z = -0.5; z <= 0.5; z += 0.2) {
                final Location check = new Location(loc.getWorld(), loc.getX() + x, y, loc.getZ() + z);
                if (check.getBlock().getType().equals(Material.VINE) || check.getBlock().getType().equals(Material.LADDER) || check.getBlock().getType().equals(Material.SNOW) || check.getBlock().getType().equals(Material.WEB)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLadder(final PlayerData data) {
        if (!data.getPositionTracker().isInLoadedChunk())return false;
        Location loc = data.getPlayer().getLocation();
        if (loc == null) return false;
        final double y = loc.getY();
        for (double x = -0.5; x <= 0.5; x += 0.2) {
            for (double z = -0.5; z <= 0.5; z += 0.2) {
                final Location check = new Location(loc.getWorld(), loc.getX() + x, y, loc.getZ() + z);
                if (check.getBlock().getType().equals(Material.LADDER)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isFence(final PlayerData data) {
        if (!data.getPositionTracker().isInLoadedChunk())return false;
        Location loc = data.getPlayer().getLocation();
        final double y = loc.getY();
        for (double x = -0.5; x <= 0.5; x += 0.2) {
            for (double z = -0.5; z <= 0.5; z += 0.2) {
                final Location check = new Location(loc.getWorld(), loc.getX() + x, y, loc.getZ() + z);
                if (check.getBlock().getType().toString().contains("FENCE") || check.getBlock().getType().toString().contains("fence")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isWeb(PlayerData data) {
        if (!data.getWorldTracker().isChunkLoaded(data.getPositionTracker().getX(), data.getPositionTracker().getZ())) return false;
        Player player = data.getPlayer();
        return player.getLocation().getBlock().getType() == Material.WEB || player.getLocation().clone().add(0, 1, 0).getBlock().getType() == Material.WEB;
    }

    public static boolean isIce(PlayerData data) {
        Player player = data.getPlayer();
        return player.getLocation().getBlock().getType() == Material.ICE || player.getLocation().clone().add(0, 1, 0).getBlock().getType() == Material.PACKED_ICE;
    }

    public static boolean isStair(PlayerData data) {
        if (!data.getWorldTracker().isChunkLoaded(data.getPositionTracker().getX(), data.getPositionTracker().getZ())) return false;
        Player player = data.getPlayer();
        double expand = 0.3;
        for (double x = -expand; x <= expand; x += expand) {
            for (double z = -expand; z <= expand; z += expand) {
                if (player.getLocation().clone().add(z, 0, x).getBlock().getType().toString().toLowerCase().contains("stairs")) {
                    return true;
                }
                if (player.getLocation().clone().add(z, player.getEyeLocation().getY(), x).getBlock().getType().toString().contains("STAIRS")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNearWeb(final Location loc) {

        final double y = loc.getY() - 0.1;
        for (double x = -0.5; x <= 0.5; x += 0.2) {
            for (double z = -0.5; z <= 0.5; z += 0.2) {
                final Location check = new Location(loc.getWorld(), loc.getX() + x, y, loc.getZ() + z);
                if (check.getBlock().getType().equals(Material.WEB)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isTrapdoor(final Material material) {
        return material == Material.TRAP_DOOR || material == Material.IRON_TRAPDOOR;
    }

    public static boolean isStair(final Material material) {
        return material.toString().contains("STAIR");
    }

    public static boolean isSlab(final Material material) {
        return material.toString().contains("STEP");
    }

    public static boolean isIce(final Material material) {
        return material == Material.ICE || material == Material.PACKED_ICE;
    }

    public static boolean isSlime(final Material material) {
        return material == Material.SLIME_BLOCK;
    }

    public static boolean isSlime(PlayerData data) {
        Player player = data.getPlayer();
        if (!data.getWorldTracker().isChunkLoaded(data.getPositionTracker().getX(), data.getPositionTracker().getZ())) return false;
        double expand = 0.3;
        for (double x = -expand; x <= expand; x += expand) {
            for (double z = -expand; z <= expand; z += expand) {
                if (player.getLocation().clone().add(z, 0, x).getBlock().getType().toString().toLowerCase().contains("slime")) {
                    return true;
                }
                if (player.getLocation().clone().add(z, player.getEyeLocation().getY(), x).getBlock().getType().toString().toLowerCase().contains("slime")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAir(final Material material) {
        return material == Material.AIR;
    }

    public static boolean isWeb(final Material material) {
        return material == Material.WEB;
    }

    public static boolean isLiquid(final Material material) {
        return material == Material.WATER || material == Material.LAVA || material == Material.STATIONARY_LAVA || material == Material.STATIONARY_WATER;
    }

    public static boolean isPiston(final Material material) {
        return material == Material.PISTON_BASE || material == Material.PISTON_MOVING_PIECE || material == Material.PISTON_STICKY_BASE || material == Material.PISTON_EXTENSION;
    }

    public static boolean isSoulSand(Material material) {
        return material == Material.SOUL_SAND;
    }
}

