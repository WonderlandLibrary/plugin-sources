package com.elevatemc.anticheat.util.anticheat;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.PacketEvents;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

@UtilityClass
public class BotUtils {

    public void removeBotEntity(PlayerData user) {
        if (user.getPredictionTracker().isHasBot()) {
            user.getPredictionTracker().setHasBot(false);
            user.getPredictionTracker().setEntityHitTime(0);

            user.getPredictionTracker().entityPlayer.setPosition(user.getPlayer().getLocation().getX() * -9999, user.getPlayer().getLocation().getY() * -9999, user.getPlayer().getLocation().getZ() * -9999);
            sendPacket(user, new PacketPlayOutEntityTeleport(user.getPredictionTracker().entityPlayer), user.getPredictionTracker().getForcedUser());

            user.getPredictionTracker().entityPlayer = null;
        }
    }

    public void sendPacket(PlayerData user, Packet<?> packet) {
        PacketEvents.getAPI().getPlayerManager().sendPacket(user.getPlayer(), packet);
    }

    public void sendPacket(PlayerData user, net.minecraft.server.v1_8_R3.Packet packet, PlayerData forcedUser) {
        PacketEvents.getAPI().getPlayerManager().sendPacket(user.getPlayer(), packet);

        if (forcedUser != null) {
            PacketEvents.getAPI().getPlayerManager().sendPacket(user.getPlayer(), packet);
        }
    }

    private Player getRandomPlayer(PlayerData user) {
        Player randomPlayer;
        if (Bukkit.getServer().getOnlinePlayers().size() > 1) {
            List<Player> onlinePlayers = new ArrayList<>();
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (!online.getUniqueId().toString().equalsIgnoreCase(user.getPlayer().getUniqueId().toString()))
                    onlinePlayers.add(online);
            }
            randomPlayer = onlinePlayers.get(new Random().nextInt(onlinePlayers.size()));
        } else {
            randomPlayer = user.getPlayer();
        }
        return randomPlayer;
    }

    public Location getBehind(Player player, double multi) {
        Location location;
        location = player.getLocation().add(player.getEyeLocation().getDirection().multiply(multi));
        BlockFace facing = getCardinalFace(player);
        if (facing == BlockFace.WEST
                || facing == BlockFace.EAST) {
        }
        return location;
    }


    private String getCardinalDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 180) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }

    private String getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return "North";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northeast";
        } else if (67.5 <= rot && rot < 112.5) {
            return "East";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Southeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "South";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southwest";
        } else if (247.5 <= rot && rot < 292.5) {
            return "West";
        } else if (292.5 <= rot && rot < 0) {
            return "Northwest";
        } else if (310.5 <= rot && rot < 360) {
            return "North";
        } else {
            return "North";
        }
    }
    private BlockFace getCardinalFace(Player player) {
        String direction = getCardinalDirection(player);
        if (direction.equalsIgnoreCase("North"))
            return BlockFace.NORTH;
        if (direction.equalsIgnoreCase("Northeast"))
            return BlockFace.NORTH_EAST;
        if (direction.equalsIgnoreCase("East"))
            return BlockFace.EAST;
        if (direction.equalsIgnoreCase("Southeast"))
            return BlockFace.SOUTH_EAST;
        if (direction.equalsIgnoreCase("South"))
            return BlockFace.SOUTH;
        if (direction.equalsIgnoreCase("Southwest"))
            return BlockFace.SOUTH_WEST;
        if (direction.equalsIgnoreCase("West"))
            return BlockFace.WEST;
        if (direction.equalsIgnoreCase("Northwest"))
            return BlockFace.NORTH_WEST;
        return null;
    }


    public void spawnBotEntity(PlayerData user) {
        spawnBotEntity(user, null, PredictionTracker.BotTypes.NORMAL);
    }

    public void spawnBotEntity(PlayerData user, PredictionTracker.BotTypes botType) {
        spawnBotEntity(user, null, botType);
    }

    public void spawnBotEntity(PlayerData user, PlayerData forcedFrom, PredictionTracker.BotTypes botType) {
        if (!user.getPredictionTracker().isHasBot() && user.getActionTracker().getLastAttack() < 20) {
            user.getPredictionTracker().setEntityAFollowDistance(-10.00);
            user.getPredictionTracker().setEntityAReportedFlags(0);
            user.getPredictionTracker().setEntityAMovementOffset(0.0f);
            user.getPredictionTracker().setEntityAStartYaw(user.getRotationTracker().getYaw());
            user.getPredictionTracker().setHasBot(true);
            user.getPredictionTracker().setEntityHitTime(0);

            user.getPredictionTracker().setBotType(botType);

            if (forcedFrom != null) user.getPredictionTracker().setForcedUser(forcedFrom);


            Player randomPlayer = getRandomPlayer(user);

            UUID uuid = randomPlayer.getUniqueId();
            String name = randomPlayer.getName();

            MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
            WorldServer worldServer = ((CraftWorld) user.getPlayer().getWorld()).getHandle();
            EntityPlayer entityPlayer = new EntityPlayer(minecraftServer, worldServer, new GameProfile(UUID.fromString(String.valueOf(uuid)), name), new PlayerInteractManager(worldServer));
            entityPlayer.onGround = true;
            entityPlayer.playerInteractManager.b(WorldSettings.EnumGamemode.CREATIVE);
            entityPlayer.setInvisible(false);
            entityPlayer.setHealth((float) getRandomDouble(getRandomDouble(1.20, 5.32), 20.0));
            entityPlayer.ping = ((CraftPlayer) randomPlayer).getHandle().ping;
            user.getPredictionTracker().setBotID(entityPlayer.getId());

            user.getPredictionTracker().lastEntitySpawn = System.currentTimeMillis();

            user.getPredictionTracker().entityPlayer = entityPlayer;

            sendPacket(user, new PacketPlayOutNamedEntitySpawn(user.getPredictionTracker().entityPlayer), forcedFrom);

            entityPlayer.setLocation(user.getPlayer().getLocation().getX(), user.getPlayer().getLocation().getY() + 0.42f, user.getPlayer().getLocation().getZ(), user.getPlayer().getLocation().getYaw(), (float) getRandomDouble(-90.0f, 90.0f));
            sendPacket(user, new PacketPlayOutEntityTeleport(entityPlayer), forcedFrom);

            sendPacket(user, new PacketPlayOutUpdateAttributes(), forcedFrom);

            if (randomPlayer.getItemInHand() != null) sendPacket(user, new PacketPlayOutEntityEquipment(entityPlayer.getId(), 0, CraftItemStack.asNMSCopy(randomPlayer.getItemInHand())), forcedFrom);
            sendPacket(user, new PacketPlayOutEntityEquipment(entityPlayer.getId(), 1, CraftItemStack.asNMSCopy(randomPlayer.getInventory().getBoots())), forcedFrom);
            sendPacket(user, new PacketPlayOutEntityEquipment(entityPlayer.getId(), 2, CraftItemStack.asNMSCopy(randomPlayer.getInventory().getLeggings())), forcedFrom);
            sendPacket(user, new PacketPlayOutEntityEquipment(entityPlayer.getId(), 3, CraftItemStack.asNMSCopy(randomPlayer.getInventory().getChestplate())), forcedFrom);
            sendPacket(user, new PacketPlayOutEntityEquipment(entityPlayer.getId(), 4, CraftItemStack.asNMSCopy(randomPlayer.getInventory().getHelmet())), forcedFrom);
            sendPacket(user, new PacketPlayOutUpdateAttributes(), forcedFrom);
        }
    }

    public double getRandomDouble(double number1, double number2) {
        return number1 + (number2 - number1) * new Random().nextDouble();
    }
}
