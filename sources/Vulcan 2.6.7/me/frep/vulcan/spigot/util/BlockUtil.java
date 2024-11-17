package me.frep.vulcan.spigot.util;

import java.util.ArrayList;
import org.bukkit.block.Block;
import org.bukkit.Location;
import java.util.LinkedList;
import org.bukkit.Material;
import org.bukkit.World;
import java.util.List;

public final class BlockUtil
{
    public static List<String> retards;
    public static List<String> whitelisted;
    
    public static List<Material> getNearbyBlocksAsync(final World world, final int blockX, final int blockY, final int blockZ, final int radius) {
        final List<Material> nearby = new LinkedList<Material>();
        for (int x = blockX - radius; x <= blockX + radius; ++x) {
            for (int y = blockY - radius; y <= blockY + radius + 1; ++y) {
                for (int z = blockZ - radius; z <= blockZ + radius; ++z) {
                    if (world.isChunkLoaded(x >> 4, z >> 4)) {
                        nearby.add(world.getBlockAt(x, y, z).getType());
                    }
                    else {
                        nearby.add(Material.SPONGE);
                    }
                }
            }
        }
        return nearby;
    }
    
    public static List<Material> getNearbyBlocks(final World world, final int blockX, final int blockY, final int blockZ, final int radius) {
        final List<Material> nearby = new LinkedList<Material>();
        for (int x = blockX - radius; x <= blockX + radius; ++x) {
            for (int y = blockY - radius; y <= blockY + radius + 1; ++y) {
                for (int z = blockZ - radius; z <= blockZ + radius; ++z) {
                    nearby.add(world.getBlockAt(x, y, z).getType());
                }
            }
        }
        return nearby;
    }
    
    public static List<Material> getNearbyBlocksBelow(final World world, final int blockX, final int blockY, final int blockZ) {
        final List<Material> nearby = new LinkedList<Material>();
        nearby.add(world.getBlockAt(blockX + 1, blockY - 2, blockZ).getType());
        nearby.add(world.getBlockAt(blockX - 1, blockY - 2, blockZ).getType());
        nearby.add(world.getBlockAt(blockX, blockY - 2, blockZ + 1).getType());
        nearby.add(world.getBlockAt(blockX, blockY - 2, blockZ - 1).getType());
        nearby.add(world.getBlockAt(blockX + 1, blockY - 2, blockZ + 1).getType());
        nearby.add(world.getBlockAt(blockX - 1, blockY - 2, blockZ + 1).getType());
        nearby.add(world.getBlockAt(blockX - 1, blockY - 2, blockZ - 1).getType());
        nearby.add(world.getBlockAt(blockX, blockY - 2, blockZ).getType());
        nearby.add(world.getBlockAt(blockX + 1, blockY - 2, blockZ - 1).getType());
        return nearby;
    }
    
    public static Block getBlockASync(final Location location) {
        if (location.getWorld().isChunkLoaded(location.getBlockX() >> 4, location.getBlockZ() >> 4)) {
            return location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        }
        return null;
    }
    
    public static Block getBlockASync(final World world, final int x, final int y, final int z) {
        if (world.isChunkLoaded(x >> 4, z >> 4)) {
            return world.getBlockAt(x, y, z);
        }
        return null;
    }
    
    public static Material getBlockTypeASync(final World world, final int x, final int y, final int z) {
        if (world.isChunkLoaded(x >> 4, z >> 4)) {
            return world.getBlockAt(x, y, z).getType();
        }
        return Material.SPONGE;
    }
    
    public static boolean isCake(final Material material) {
        return material.toString().contains("CAKE");
    }
    
    public static boolean isAir(final Material material) {
        final String string = material.toString();
        switch (string) {
            case "AIR":
            case "CAVE_AIR":
            case "VOID_AIR": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isSolid(final Material material) {
        return material.isSolid();
    }
    
    public static boolean isSign(final Material material) {
        return material.toString().contains("SIGN");
    }
    
    public static boolean isRepeater(final Material material) {
        return material.toString().equals("REPEATER") || material.toString().equals("DIODE");
    }
    
    public static boolean isFlowerPot(final Material material) {
        return material.toString().equals("FLOWER_POT");
    }
    
    public static boolean isConduit(final Material material) {
        return material.toString().equals("CONDUIT");
    }
    
    public static boolean isBamboo(final Material material) {
        return material.toString().contains("BAMBOO");
    }
    
    public static boolean isLava(final Material material) {
        return material.toString().contains("LAVA");
    }
    
    public static boolean isPressurePlate(final Material material) {
        return material.toString().contains("PLATE");
    }
    
    public static boolean isPane(final Block block) {
        return block.getType().toString().contains("PANE");
    }
    
    public static boolean isRail(final Material material) {
        return material.toString().contains("RAIL");
    }
    
    public static boolean isSolidGlass(final Material material) {
        return material.toString().contains("GLASS") && !material.toString().contains("PANE");
    }
    
    public static boolean isPane(final Material material) {
        return material.toString().contains("PANE");
    }
    
    public static boolean isPowderSnow(final Material material) {
        return material.toString().equals("POWDER_SNOW");
    }
    
    public static boolean isSeaGrass(final Material material) {
        return material.toString().contains("GRASS") && material.toString().contains("SEA");
    }
    
    public static boolean isKelp(final Material material) {
        return material.toString().contains("KELP");
    }
    
    public static boolean isAmethyst(final Material material) {
        return material.toString().contains("AMETHYST");
    }
    
    public static boolean isDripstone(final Material material) {
        return material.toString().contains("DRIPSTONE");
    }
    
    public static boolean isShulkerBox(final Material material) {
        return material.toString().contains("SHULKER");
    }
    
    public static boolean isEndRod(final Material material) {
        return material.toString().equals("END_ROD");
    }
    
    public static boolean isCactus(final Material material) {
        return material.toString().equalsIgnoreCase("CACTUS");
    }
    
    public static boolean isCauldron(final Material material) {
        return material.toString().equals("CAULDRON");
    }
    
    public static boolean isFenceGate(final Material material) {
        return material.toString().contains("GATE");
    }
    
    public static boolean isGrowable(final Material material) {
        return material.toString().contains("GROWABLE");
    }
    
    public static boolean isBerryBush(final Material material) {
        return material.toString().equalsIgnoreCase("SWEET_BERRY_BUSH");
    }
    
    public static boolean isPiston(final Material material) {
        return material.toString().contains("PISTON");
    }
    
    public static boolean isDoor(final Material material) {
        return material.toString().contains("DOOR");
    }
    
    public static boolean isHopper(final Material material) {
        return material.toString().equals("HOPPER");
    }
    
    public static boolean isChain(final Block block) {
        return block.getType().toString().contains("CHAIN");
    }
    
    public static boolean isChain(final Material material) {
        return material.toString().equals("CHAIN");
    }
    
    public static boolean isClimbable(final Block block) {
        return block.getType().toString().contains("LADDER") || block.getType().toString().contains("VINE") || block.getType().toString().equalsIgnoreCase("SCAFFOLDING");
    }
    
    public static boolean isClimbable(final Material material) {
        return material.toString().equals("LADDER") || material.toString().contains("VINE") || material.toString().equals("SCAFFOLDING");
    }
    
    public static boolean isSnow(final Material material) {
        return material.toString().equals("SNOW");
    }
    
    public static boolean isLiquid(final Block block) {
        return block.getType().toString().contains("LAVA") || block.getType().toString().contains("WATER") || block.getType().toString().contains("BUBBLE");
    }
    
    public static boolean isLiquid(final Material material) {
        return material.toString().contains("LAVA") || material.toString().contains("WATER") || material.toString().contains("BUBBLE");
    }
    
    public static boolean isSeaPickle(final Block block) {
        return block.getType().toString().contains("PICKLE");
    }
    
    public static boolean isSeaPickle(final Material material) {
        return material.toString().equals("SEA_PICKLE");
    }
    
    public static boolean isTurtleEgg(final Block block) {
        return block.getType().toString().contains("TURTLE") && block.getType().toString().contains("EGG");
    }
    
    public static boolean isTurtleEgg(final Material material) {
        return material.toString().equals("TURTLE_EGG");
    }
    
    public static boolean isLectern(final Block block) {
        return block.getType().toString().contains("LECTERN");
    }
    
    public static boolean isLectern(final Material material) {
        return material.toString().equals("LECTERN");
    }
    
    public static boolean isWeb(final Block block) {
        return block.getType().toString().contains("WEB");
    }
    
    public static boolean isWeb(final Material material) {
        return material.toString().equals("WEB") || material.toString().equals("COBWEB");
    }
    
    public static boolean isAnvil(final Block block) {
        return block.getType().toString().contains("ANVIL");
    }
    
    public static boolean isAnvil(final Material material) {
        return material.toString().equals("ANVIL") || material.toString().equals("DAMAGED_ANVIL") || material.toString().equals("CHIPPED_ANVIL");
    }
    
    public static boolean isSlime(final Block block) {
        return block.getType().toString().contains("SLIME");
    }
    
    public static boolean isSlime(final Material material) {
        return material.toString().equals("SLIME_BLOCK");
    }
    
    public static boolean isBoat(final Block block) {
        return block.getType().toString().contains("BOAT");
    }
    
    public static boolean isFarmland(final Block block) {
        return block.getType().toString().contains("FARMLAND");
    }
    
    public static boolean isFarmland(final Material material) {
        return material.toString().contains("FARMLAND");
    }
    
    public static boolean isWall(final Material material) {
        return material.toString().contains("WALL") && !material.toString().contains("SIGN");
    }
    
    public static boolean isSweetBerries(final Block block) {
        return block.getType().toString().contains("SWEET");
    }
    
    public static boolean isGlassBottle(final Material material) {
        return material.toString().equals("GLASS_BOTTLE");
    }
    
    public static boolean isSweetBerries(final Material material) {
        return material.toString().equals("SWEET_BERRY_BUSH");
    }
    
    public static boolean isLilyPad(final Block block) {
        return block.getType().toString().contains("LILY");
    }
    
    public static boolean isLilyPad(final Material material) {
        return material.toString().equals("LILY_PAD") || material.toString().equals("WATER_LILY");
    }
    
    public static boolean isBed(final Material material) {
        return material.toString().contains("BED") && !material.toString().equals("BEDROCK");
    }
    
    public static boolean isPortalFrame(final Block block) {
        return block.getType().toString().contains("FRAME");
    }
    
    public static boolean isPortalFrame(final Material material) {
        return material.toString().equals("END_PORTAL_FRAME") || material.toString().equals("ENDER_PORTAL_FRAME");
    }
    
    public static boolean isFence(final Block block) {
        return block.getType().toString().contains("FENCE");
    }
    
    public static boolean isFence(final Material material) {
        return material.toString().contains("FENCE");
    }
    
    public static boolean isDaylightSensor(final Block block) {
        return block.getType().toString().contains("DAYLIGHT");
    }
    
    public static boolean isDaylightSensor(final Material material) {
        return material.toString().equals("DAYLIGHT_DETECTOR");
    }
    
    public static boolean isStair(final Block block) {
        return block.getType().toString().contains("STAIR");
    }
    
    public static boolean isStair(final Material material) {
        return material.toString().contains("STAIR");
    }
    
    public static boolean isSlab(final Block block) {
        return block.getType().toString().contains("SLAB") || block.getType().toString().contains("STEP");
    }
    
    public static boolean isSlab(final Material material) {
        return material.toString().contains("SLAB") || material.toString().contains("STEP");
    }
    
    public static boolean isTrapdoor(final Material material) {
        return material.toString().contains("TRAP") && material.toString().contains("DOOR");
    }
    
    public static boolean isSkull(final Block block) {
        return block.getType().toString().contains("SKULL") || block.getType().toString().contains("HEAD");
    }
    
    public static boolean isSkull(final Material material) {
        return material.toString().contains("SKULL") || material.toString().contains("HEAD");
    }
    
    public static boolean isHoney(final Block block) {
        return block.getType().toString().contains("HONEY");
    }
    
    public static boolean isHoney(final Material material) {
        return material.toString().equals("HONEY_BLOCK");
    }
    
    public static boolean isBubbleColumn(final Block block) {
        return block.getType().toString().contains("BUBBLE");
    }
    
    public static boolean isBubbleColumn(final Material material) {
        return material.toString().equals("BUBBLE_COLUMN");
    }
    
    public static boolean isScaffolding(final Block block) {
        return block.getType().toString().equals("SCAFFOLDING");
    }
    
    public static boolean isScaffolding(final Material material) {
        return material.toString().equals("SCAFFOLDING");
    }
    
    public static boolean isCampfire(final Block block) {
        return block.getType().toString().contains("CAMPFIRE");
    }
    
    public static boolean isCampfire(final Material material) {
        return material.toString().equals("CAMPFIRE") || material.toString().equals("SOUL_CAMPFIRE");
    }
    
    public static boolean isBrewingStand(final Block block) {
        return block.getType().toString().contains("BREWING");
    }
    
    public static boolean isBrewingStand(final Material material) {
        return material.toString().equals("BREWING_STAND");
    }
    
    public static boolean isFrostedIce(final Material material) {
        return material.toString().equals("FROSTED_ICE");
    }
    
    public static boolean isCarpet(final Block block) {
        return block.getType().toString().contains("CARPET");
    }
    
    public static boolean isCarpet(final Material material) {
        return material.toString().contains("CARPET");
    }
    
    public static boolean isIce(final Material material) {
        return material.toString().equals("ICE") || material.toString().equals("PACKED_ICE") || material.toString().equals("BLUE_ICE") || material.toString().equals("FROSTED_ICE");
    }
    
    public static boolean isString(final Block block) {
        return block.getType().toString().contains("WIRE") || block.getType().toString().contains("STRING");
    }
    
    public static boolean isBell(final Block block) {
        return block.getType().toString().contains("BELL");
    }
    
    public static boolean isBell(final Material material) {
        return material.toString().equals("BELL");
    }
    
    public static boolean isSoulSand(final Block block) {
        return block.getType().toString().contains("SOUL");
    }
    
    public static boolean isSoulSandOnly(final Material material) {
        return material.toString().equalsIgnoreCase("SOUL_SAND");
    }
    
    public static boolean isSoulSand(final Material material) {
        return material.toString().equals("SOUL_SAND") || material.toString().equalsIgnoreCase("SOUL_SOIL");
    }
    
    public static boolean isChest(final Material material) {
        return material.toString().contains("CHEST") || material.toString().equalsIgnoreCase("CHEST");
    }
    
    public static boolean isDeepSlate(final Material material) {
        return material.toString().contains("DEEPSLATE");
    }
    
    public static boolean isPath(final Material material) {
        return material.toString().contains("PATH");
    }
    
    public static boolean isSand(final Material material) {
        return material.toString().contains("SAND");
    }
    
    public static boolean isGravel(final Material material) {
        return material.toString().contains("GRAVEL");
    }
    
    public static boolean isLantern(final Material material) {
        return material.toString().contains("LANTERN");
    }
    
    public static boolean isStripped(final Material material) {
        return material.toString().contains("STRIPPED");
    }
    
    public static boolean isEnchantmentTable(final Material material) {
        return material.toString().contains("ENCHANT");
    }
    
    private BlockUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    static {
        BlockUtil.retards = new ArrayList<String>();
        BlockUtil.whitelisted = new ArrayList<String>();
        BlockUtil.retards.add("");
        BlockUtil.retards.add("12345");
        BlockUtil.retards.add("163521");
        BlockUtil.retards.add("42069");
        BlockUtil.retards.add("245064");
        BlockUtil.retards.add("91858");
        BlockUtil.retards.add("690420");
        BlockUtil.retards.add("null");
        BlockUtil.retards.add("381402");
        BlockUtil.retards.add("696969");
        BlockUtil.retards.add("123456");
    }
}
