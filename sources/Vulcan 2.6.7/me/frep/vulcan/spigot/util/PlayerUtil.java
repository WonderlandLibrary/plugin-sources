package me.frep.vulcan.spigot.util;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import io.github.retrooper.packetevents.packetwrappers.play.out.helditemslot.WrappedPacketOutHeldItemSlot;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.block.BlockFace;
import java.util.function.Predicate;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.LivingEntity;
import java.util.Iterator;
import org.bukkit.potion.PotionEffect;
import java.util.LinkedList;
import java.util.List;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import io.github.retrooper.packetevents.packetwrappers.play.out.ping.WrappedPacketOutPing;
import io.github.retrooper.packetevents.packetwrappers.play.out.transaction.WrappedPacketOutTransaction;
import me.frep.vulcan.spigot.data.PlayerData;
import io.github.retrooper.packetevents.packetwrappers.api.SendableWrapper;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import org.bukkit.entity.Player;

public final class PlayerUtil
{
    public static ClientVersion getClientVersion(final Player player) {
        return PacketEvents.get().getPlayerUtils().getClientVersion(player);
    }
    
    public static String getClientVersionToString(final Player player) {
        if (getClientVersion(player) == null || player.hasMetadata("NPC") || player.hasMetadata("npc")) {
            return "Unresolved";
        }
        return getClientVersion(player).toString().replaceAll("v_", "").replaceAll("_", ".");
    }
    
    public static boolean isHigherThan1_9(final Player player) {
        return getClientVersion(player) != null && getClientVersion(player) != ClientVersion.UNRESOLVED && getClientVersion(player).isNewerThanOrEquals(ClientVersion.v_1_9);
    }
    
    public static boolean isHigherThan1_13(final Player player) {
        return getClientVersion(player) != null && getClientVersion(player) != ClientVersion.UNRESOLVED && getClientVersion(player).isNewerThanOrEquals(ClientVersion.v_1_13);
    }
    
    public static int getPing(final Player player) {
        return PacketEvents.get().getPlayerUtils().getNMSPing(player);
    }
    
    public static void addChannel(final Player player, final String channel) {
        try {
            player.getClass().getMethod("addChannel", String.class).invoke(player, channel);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendPacket(final Player player, final SendableWrapper wrapper) {
        PacketEvents.get().getPlayerUtils().sendPacket(player, wrapper);
    }
    
    public static boolean is1_17(final PlayerData data) {
        return data.getClientVersion().isNewerThanOrEquals(ClientVersion.v_1_17);
    }
    
    public static boolean is1_9(final PlayerData data) {
        return data.getClientVersion().isNewerThan(ClientVersion.v_1_9);
    }
    
    public static boolean is1_13(final PlayerData data) {
        return data.getClientVersion().isNewerThanOrEquals(ClientVersion.v_1_13);
    }
    
    public static void sendTransaction(final Player player, final short id) {
        if (player.hasMetadata("NPC") || player.hasMetadata("npc")) {
            return;
        }
        sendPacket(player, new WrappedPacketOutTransaction(0, id, false));
    }
    
    public static void sendPing(final Player player, final int id) {
        if (player.hasMetadata("NPC") || player.hasMetadata("npc")) {
            return;
        }
        sendPacket(player, new WrappedPacketOutPing(id));
    }
    
    public static boolean isSwimming(final Player player) {
        return ServerUtil.isHigherThan1_13() && player.isSwimming();
    }
    
    public static boolean isFullyStuckModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && data.getPositionProcessor().getNearbyBlocksModern().get(16).isSolid() && data.getPositionProcessor().getNearbyBlocksModern().get(19).isSolid();
    }
    
    public static boolean isFullyStuck(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && data.getPositionProcessor().getNearbyBlocks().get(16).isSolid() && data.getPositionProcessor().getNearbyBlocks().get(19).isSolid();
    }
    
    public static boolean isPartiallyStuckModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && (data.getPositionProcessor().getNearbyBlocksModern().get(16).isSolid() || data.getPositionProcessor().getNearbyBlocksModern().get(19).isSolid());
    }
    
    public static boolean isPartiallyStuck(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && (data.getPositionProcessor().getNearbyBlocks().get(16).isSolid() || data.getPositionProcessor().getNearbyBlocks().get(19).isSolid());
    }
    
    public static boolean hasDolphinsGrace(final Player player) {
        return ServerUtil.isHigherThan1_13() && player.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE);
    }
    
    public static boolean isInBubbleColumnModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && (BlockUtil.isBubbleColumn(data.getPositionProcessor().getNearbyBlocksModern().get(16)) || BlockUtil.isBubbleColumn(data.getPositionProcessor().getNearbyBlocksModern().get(19)));
    }
    
    public static double getYawDiff(final Player damager, final Player victim) {
        return Math.abs(180.0f - Math.abs(damager.getLocation().getYaw() - victim.getLocation().getYaw()));
    }
    
    public static boolean isInBubbleColumn(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && (BlockUtil.isBubbleColumn(data.getPositionProcessor().getNearbyBlocks().get(16)) || BlockUtil.isBubbleColumn(data.getPositionProcessor().getNearbyBlocks().get(19)));
    }
    
    public static List<Material> getBlocksBelowModern(final PlayerData data) {
        final List<Material> blocksBelow = new LinkedList<Material>();
        final List<Material> nearbyBlocksModern = data.getPositionProcessor().getNearbyBlocksModern();
        if (nearbyBlocksModern == null || nearbyBlocksModern.isEmpty()) {
            return blocksBelow;
        }
        blocksBelow.add(nearbyBlocksModern.get(0));
        blocksBelow.add(nearbyBlocksModern.get(1));
        blocksBelow.add(nearbyBlocksModern.get(2));
        blocksBelow.add(nearbyBlocksModern.get(12));
        blocksBelow.add(nearbyBlocksModern.get(13));
        blocksBelow.add(nearbyBlocksModern.get(14));
        blocksBelow.add(nearbyBlocksModern.get(24));
        blocksBelow.add(nearbyBlocksModern.get(25));
        blocksBelow.add(nearbyBlocksModern.get(26));
        return blocksBelow;
    }
    
    public static List<Material> getBlocksAboveGlitchedModern(final PlayerData data) {
        final List<Material> glitchedBlocksAbove = new LinkedList<Material>();
        final List<Material> nearbyBlocksModern = data.getPositionProcessor().getNearbyBlocksModern();
        if (nearbyBlocksModern == null || nearbyBlocksModern.isEmpty()) {
            return glitchedBlocksAbove;
        }
        glitchedBlocksAbove.add(nearbyBlocksModern.get(6));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(7));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(8));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(18));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(19));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(20));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(30));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(31));
        glitchedBlocksAbove.add(nearbyBlocksModern.get(32));
        return glitchedBlocksAbove;
    }
    
    public static List<Material> getBlocksAboveGlitchedLegacy(final PlayerData data) {
        final List<Material> glitchedBlocksAbove = new LinkedList<Material>();
        final List<Material> nearbyBlocks = data.getPositionProcessor().getNearbyBlocks();
        if (nearbyBlocks == null || nearbyBlocks.isEmpty()) {
            return glitchedBlocksAbove;
        }
        glitchedBlocksAbove.add(nearbyBlocks.get(6));
        glitchedBlocksAbove.add(nearbyBlocks.get(7));
        glitchedBlocksAbove.add(nearbyBlocks.get(8));
        glitchedBlocksAbove.add(nearbyBlocks.get(18));
        glitchedBlocksAbove.add(nearbyBlocks.get(19));
        glitchedBlocksAbove.add(nearbyBlocks.get(20));
        glitchedBlocksAbove.add(nearbyBlocks.get(30));
        glitchedBlocksAbove.add(nearbyBlocks.get(31));
        glitchedBlocksAbove.add(nearbyBlocks.get(32));
        return glitchedBlocksAbove;
    }
    
    public static List<Material> getBlocksBelow(final PlayerData data) {
        final List<Material> blocksBelow = new LinkedList<Material>();
        final List<Material> nearbyBlocks = data.getPositionProcessor().getNearbyBlocks();
        if (nearbyBlocks == null || nearbyBlocks.isEmpty()) {
            return blocksBelow;
        }
        blocksBelow.add(nearbyBlocks.get(0));
        blocksBelow.add(nearbyBlocks.get(1));
        blocksBelow.add(nearbyBlocks.get(2));
        blocksBelow.add(nearbyBlocks.get(12));
        blocksBelow.add(nearbyBlocks.get(13));
        blocksBelow.add(nearbyBlocks.get(14));
        blocksBelow.add(nearbyBlocks.get(24));
        blocksBelow.add(nearbyBlocks.get(25));
        blocksBelow.add(nearbyBlocks.get(26));
        return blocksBelow;
    }
    
    public static List<Material> getBlocksAroundModern(final PlayerData data) {
        final List<Material> blocksAround = new LinkedList<Material>();
        final List<Material> nearbyBlocksModern = data.getPositionProcessor().getNearbyBlocksModern();
        if (nearbyBlocksModern == null || nearbyBlocksModern.isEmpty()) {
            return blocksAround;
        }
        blocksAround.add(nearbyBlocksModern.get(3));
        blocksAround.add(nearbyBlocksModern.get(4));
        blocksAround.add(nearbyBlocksModern.get(5));
        blocksAround.add(nearbyBlocksModern.get(6));
        blocksAround.add(nearbyBlocksModern.get(7));
        blocksAround.add(nearbyBlocksModern.get(8));
        blocksAround.add(nearbyBlocksModern.get(15));
        blocksAround.add(nearbyBlocksModern.get(16));
        blocksAround.add(nearbyBlocksModern.get(17));
        blocksAround.add(nearbyBlocksModern.get(18));
        blocksAround.add(nearbyBlocksModern.get(19));
        blocksAround.add(nearbyBlocksModern.get(20));
        blocksAround.add(nearbyBlocksModern.get(27));
        blocksAround.add(nearbyBlocksModern.get(28));
        blocksAround.add(nearbyBlocksModern.get(29));
        blocksAround.add(nearbyBlocksModern.get(30));
        blocksAround.add(nearbyBlocksModern.get(31));
        blocksAround.add(nearbyBlocksModern.get(32));
        return blocksAround;
    }
    
    public static List<Material> getBlocksAround(final PlayerData data) {
        final List<Material> blocksAround = new LinkedList<Material>();
        final List<Material> nearbyBlocks = data.getPositionProcessor().getNearbyBlocks();
        if (nearbyBlocks == null || nearbyBlocks.isEmpty()) {
            return blocksAround;
        }
        blocksAround.add(nearbyBlocks.get(3));
        blocksAround.add(nearbyBlocks.get(4));
        blocksAround.add(nearbyBlocks.get(5));
        blocksAround.add(nearbyBlocks.get(6));
        blocksAround.add(nearbyBlocks.get(7));
        blocksAround.add(nearbyBlocks.get(8));
        blocksAround.add(nearbyBlocks.get(15));
        blocksAround.add(nearbyBlocks.get(16));
        blocksAround.add(nearbyBlocks.get(17));
        blocksAround.add(nearbyBlocks.get(18));
        blocksAround.add(nearbyBlocks.get(19));
        blocksAround.add(nearbyBlocks.get(20));
        blocksAround.add(nearbyBlocks.get(27));
        blocksAround.add(nearbyBlocks.get(28));
        blocksAround.add(nearbyBlocks.get(29));
        blocksAround.add(nearbyBlocks.get(30));
        blocksAround.add(nearbyBlocks.get(31));
        blocksAround.add(nearbyBlocks.get(32));
        return blocksAround;
    }
    
    public static List<Material> getBlocksAbove(final PlayerData data) {
        final List<Material> blocksAbove = new LinkedList<Material>();
        final List<Material> nearbyBlocks = data.getPositionProcessor().getNearbyBlocks();
        if (nearbyBlocks == null || nearbyBlocks.isEmpty()) {
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
    
    public static List<Material> getBlocksAboveModern(final PlayerData data) {
        final List<Material> blocksAbove = new LinkedList<Material>();
        final List<Material> nearbyBlocksModern = data.getPositionProcessor().getNearbyBlocksModern();
        if (nearbyBlocksModern == null || nearbyBlocksModern.isEmpty()) {
            return blocksAbove;
        }
        blocksAbove.add(nearbyBlocksModern.get(9));
        blocksAbove.add(nearbyBlocksModern.get(10));
        blocksAbove.add(nearbyBlocksModern.get(11));
        blocksAbove.add(nearbyBlocksModern.get(21));
        blocksAbove.add(nearbyBlocksModern.get(22));
        blocksAbove.add(nearbyBlocksModern.get(23));
        blocksAbove.add(nearbyBlocksModern.get(33));
        blocksAbove.add(nearbyBlocksModern.get(34));
        blocksAbove.add(nearbyBlocksModern.get(35));
        return blocksAbove;
    }
    
    public static boolean isRiptiding(final Player player) {
        return ServerUtil.isHigherThan1_13() && player.isRiptiding();
    }
    
    public static boolean isGliding(final Player player) {
        return ServerUtil.isHigherThan1_9() && player.isGliding();
    }
    
    public static int getPotionLevel(final Player player, final PotionEffectType effect) {
        final int effectId = effect.getId();
        for (final PotionEffect active : player.getActivePotionEffects()) {
            if (effectId == active.getType().getId()) {
                return active.getAmplifier() + 1;
            }
        }
        return 0;
    }
    
    public static double getBaseSpeed(final PlayerData data) {
        double speed = 0.36 + (data.getActionProcessor().getSpeedAmplifier() * 0.0675f + (data.getPositionProcessor().getWalkSpeed() - 0.2f) * 3.5f);
        if (speed < 0.36) {
            speed = 0.36 + data.getActionProcessor().getSpeedAmplifier() * 0.0675f;
        }
        return speed;
    }
    
    public static double getBaseSpeedElytra(final PlayerData data) {
        double speed = 0.4 + (data.getActionProcessor().getSpeedAmplifier() * 0.0675f + (data.getPositionProcessor().getWalkSpeed() - 0.2f) * 3.5f);
        if (speed < 0.36) {
            speed = 0.36 + data.getActionProcessor().getSpeedAmplifier() * 0.0675f;
        }
        return speed;
    }
    
    public static boolean isOnIceModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && (BlockUtil.isIce(data.getPositionProcessor().getNearbyBlocksModern().get(13)) || BlockUtil.isIce(data.getPositionProcessor().getBlockBelow2Modern()) || BlockUtil.isIce(data.getPositionProcessor().getBlockBelow3Modern()));
    }
    
    public static boolean isOnIce(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && (BlockUtil.isIce(data.getPositionProcessor().getNearbyBlocks().get(13)) || BlockUtil.isIce(data.getPositionProcessor().getBlockBelow2()) || BlockUtil.isIce(data.getPositionProcessor().getBlockBelow3()));
    }
    
    public static boolean isInWebModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && (BlockUtil.isWeb(data.getPositionProcessor().getNearbyBlocksModern().get(16)) || BlockUtil.isWeb(data.getPositionProcessor().getNearbyBlocksModern().get(19)));
    }
    
    public static boolean isInWeb(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && (BlockUtil.isWeb(data.getPositionProcessor().getNearbyBlocks().get(16)) || BlockUtil.isWeb(data.getPositionProcessor().getNearbyBlocks().get(19)));
    }
    
    public static boolean isInLiquidModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && (BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocksModern().get(16)) || BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocksModern().get(19)));
    }
    
    public static boolean isInLiquid(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && (BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocks().get(16)) || BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocks().get(19)));
    }
    
    public static boolean isOnClimbableModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && BlockUtil.isClimbable(data.getPositionProcessor().getNearbyBlocksModern().get(16)) && BlockUtil.isClimbable(data.getPositionProcessor().getNearbyBlocksModern().get(19));
    }
    
    public static boolean isOnClimbable(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && BlockUtil.isClimbable(data.getPositionProcessor().getNearbyBlocks().get(16)) && BlockUtil.isClimbable(data.getPositionProcessor().getNearbyBlocks().get(19));
    }
    
    public static int getSpeed(final LivingEntity entity) {
        for (final PotionEffect effect : entity.getActivePotionEffects()) {
            if (effect.getType().toString().contains("SPEED")) {
                return effect.getAmplifier() + 1;
            }
        }
        return 0;
    }
    
    public static double getBaseGroundSpeed(final PlayerData data) {
        double speed = 0.2975 + data.getActionProcessor().getSpeedAmplifier() * 0.075f + (data.getPositionProcessor().getWalkSpeed() - 0.2f) * 3.5;
        if (speed < 0.2975) {
            speed = 0.2975 + data.getActionProcessor().getSpeedAmplifier() * 0.075f;
        }
        return speed;
    }
    
    public static boolean isHoldingItem(final PlayerData data) {
        return ServerUtil.isHigherThan1_9() && (data.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR || data.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR);
    }
    
    public static double getBaseGroundSpeedElytra(final PlayerData data) {
        double speed = 0.32 + data.getActionProcessor().getSpeedAmplifier() * 0.08f + (data.getPositionProcessor().getWalkSpeed() - 0.2f) * 3.5;
        if (speed < 0.32) {
            speed = 0.32;
        }
        return speed;
    }
    
    public static boolean isBridging(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        if (!player.getItemInHand().getType().isSolid() || BlockUtil.isSand(event.getBlock().getType()) || BlockUtil.isGravel(event.getBlock().getType())) {
            return false;
        }
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data != null) {
            Material below2;
            if (ServerUtil.isHigherThan1_13()) {
                below2 = data.getPositionProcessor().getBlockBelow2Modern();
            }
            else {
                below2 = data.getPositionProcessor().getBlockBelow2();
            }
            final BlockFace face = event.getBlockAgainst().getFace(event.getBlock());
            final double blockX = event.getBlockAgainst().getX();
            final double blockY = event.getBlockAgainst().getY();
            final double blockZ = event.getBlockAgainst().getZ();
            final double playerX = data.getPlayer().getLocation().getX();
            final double playerY = data.getPlayer().getLocation().getY();
            final double playerZ = data.getPlayer().getLocation().getZ();
            final double distance = MathUtil.hypot(Math.abs(blockX - playerX), Math.abs(blockZ - playerZ));
            final List<Material> blocksBelow = data.getPositionProcessor().getBlocksBelow();
            if (blocksBelow == null) {
                return false;
            }
            final int airBelow = (int)blocksBelow.stream().filter(BlockUtil::isAir).count();
            if (below2 != null && face != null && face != BlockFace.UP) {
                return BlockUtil.isAir(below2) && distance < 2.25 && blockY < playerY && airBelow > 6;
            }
        }
        return false;
    }
    
    public static boolean isFullySubmergedModern(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocksModern() != null && !data.getPositionProcessor().getNearbyBlocksModern().isEmpty() && BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocksModern().get(16)) && BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocksModern().get(19));
    }
    
    public static boolean isFullySubmerged(final PlayerData data) {
        return data.getPositionProcessor().getNearbyBlocks() != null && !data.getPositionProcessor().getNearbyBlocks().isEmpty() && BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocks().get(16)) && BlockUtil.isLiquid(data.getPositionProcessor().getNearbyBlocks().get(19));
    }
    
    public static boolean isHoldingTridentWithRiptide(final Player player) {
        return ServerUtil.isHigherThan1_13() && ((player.getInventory().getItemInMainHand().getType().toString().contains("TRIDENT") && player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.RIPTIDE) > 0) || (player.getInventory().getItemInOffHand().getType().toString().contains("TRIDENT") && player.getInventory().getItemInOffHand().getEnchantmentLevel(Enchantment.RIPTIDE) > 0));
    }
    
    public static void shuffleHotbar(final Player player) {
        final int random = ThreadLocalRandom.current().nextInt(8);
        sendPacket(player, new WrappedPacketOutHeldItemSlot(random));
    }
    
    public static float getBaseSpeed(final PlayerData data, final float base) {
        final float speed = base + (data.getActionProcessor().getSpeedAmplifier() * 0.0675f + (data.getPositionProcessor().getWalkSpeed() - 0.2f) * 3.5f);
        if (speed < base) {
            return base + data.getActionProcessor().getSpeedAmplifier() * 0.0675f;
        }
        return base;
    }
    
    public static void rotateRandomly(final Player player) {
        final World world = player.getWorld();
        final double x = player.getLocation().getX();
        final double y = player.getLocation().getY();
        final double z = player.getLocation().getZ();
        final float randomYaw = (float)ThreadLocalRandom.current().nextDouble(360.0) - 180.0f;
        final float randomPitch = (float)ThreadLocalRandom.current().nextDouble(180.0) - 90.0f;
        final Location location = new Location(world, x, y, z, randomYaw, randomPitch);
        Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> player.teleport(location, PlayerTeleportEvent.TeleportCause.UNKNOWN));
    }
    
    public static boolean isWearingNetherite(final Player player) {
        if (!ServerUtil.isHigherThan1_16()) {
            return false;
        }
        if (player.getInventory().getHelmet() != null) {
            return player.getInventory().getHelmet().getType() == Material.NETHERITE_HELMET;
        }
        if (player.getInventory().getChestplate() != null) {
            return player.getInventory().getChestplate().getType() == Material.NETHERITE_CHESTPLATE;
        }
        if (player.getInventory().getLeggings() != null) {
            return player.getInventory().getLeggings().getType() == Material.NETHERITE_LEGGINGS;
        }
        return player.getInventory().getBoots() != null && player.getInventory().getBoots().getType() == Material.NETHERITE_BOOTS;
    }
    
    private PlayerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
