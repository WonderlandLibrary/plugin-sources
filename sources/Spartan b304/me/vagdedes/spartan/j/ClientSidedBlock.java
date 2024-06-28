package me.vagdedes.spartan.j;

import org.bukkit.block.Block;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.Location;
import me.vagdedes.spartan.k.e.ServerWorld;
import org.bukkit.Bukkit;
import java.util.Map;
import org.bukkit.Material;
import me.vagdedes.spartan.f.Position;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.HashMap;

public class ClientSidedBlock
{
    private static final HashMap<UUID, ConcurrentHashMap<Position, Material>> q;
    private static final HashMap<UUID, ConcurrentHashMap<Position, Byte>> T;
    
    public ClientSidedBlock() {
        super();
    }
    
    public static void run() {
        for (final Map.Entry<UUID, ConcurrentHashMap<Position, Material>> entry : ClientSidedBlock.q.entrySet()) {
            final UUID key = (UUID)entry.getKey();
            final Player player = Bukkit.getPlayer(key);
            final Location location = player.getLocation();
            for (final Position position : ((ConcurrentHashMap<Position, Material>)entry.getValue()).keySet()) {
                final Location location2 = new Location(ServerWorld.a(position.getID()), position.getX(), position.getY(), position.getZ());
                if (location2.getWorld() == location.getWorld() && location.distance(location2) <= 256.0) {
                    player.sendBlockChange(location2, (Material)((ConcurrentHashMap<Position, Material>)entry.getValue()).get(position), (byte)Byte.valueOf(((ConcurrentHashMap<Position, Byte>)ClientSidedBlock.T.get(key)).get((Object)position)));
                }
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material value, final byte b) {
        if (value == Material.AIR || ((value == MaterialUtils.a("water") || value == MaterialUtils.a("lava")) && BlockUtils.f(null, spartanLocation))) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!ClientSidedBlock.q.containsKey(uniqueId)) {
            ClientSidedBlock.q.put(uniqueId, new ConcurrentHashMap<Position, Material>());
        }
        if (!ClientSidedBlock.T.containsKey(uniqueId)) {
            ClientSidedBlock.T.put(uniqueId, new ConcurrentHashMap<Position, Byte>());
        }
        final Position position = new Position(spartanLocation);
        ((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(uniqueId)).put(position, value);
        ((ConcurrentHashMap<Position, Byte>)ClientSidedBlock.T.get(uniqueId)).put(position, Byte.valueOf(b));
        if (spartanLocation.getWorld() == spartanPlayer.getWorld()) {
            Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendBlockChange(new Location(spartanLocation.getWorld(), spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ()), value, b);
        }
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Position a = a(spartanPlayer, spartanLocation);
        if (a == null) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final SpartanBlock a2 = spartanLocation.a();
        ((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(uniqueId)).remove(a);
        ((ConcurrentHashMap<Position, Byte>)ClientSidedBlock.T.get(uniqueId)).remove(a);
        if (spartanLocation.getWorld() == spartanPlayer.getWorld()) {
            player.sendBlockChange(new Location(spartanLocation.getWorld(), spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ()), a2.getType(), a2.getData());
        }
    }
    
    public static Position a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final ConcurrentHashMap<Position, Material> concurrentHashMap = (ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(spartanPlayer.getUniqueId());
        if (concurrentHashMap != null) {
            final int a = ServerWorld.a(spartanLocation.getWorld());
            for (final Position position : concurrentHashMap.keySet()) {
                if (position.getID() == a && position.getX() == spartanLocation.getBlockX() && position.getY() == spartanLocation.getBlockY() && position.getZ() == spartanLocation.getBlockZ()) {
                    return position;
                }
            }
        }
        return null;
    }
    
    public static Material a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Position a = a(spartanPlayer, spartanLocation);
        return (a == null) ? null : ((Material)((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(spartanPlayer.getUniqueId())).get(a));
    }
    
    public static byte a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Position a = a(spartanPlayer, spartanLocation);
        return (byte)((a == null) ? 0 : ((byte)Byte.valueOf(((ConcurrentHashMap<Position, Byte>)ClientSidedBlock.T.get(spartanPlayer.getUniqueId())).get((Object)a))));
    }
    
    public static void f(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (spartanLocation.getWorld() != spartanPlayer.getWorld()) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final SpartanBlock a = spartanLocation.a();
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final Location location = new Location(spartanLocation.getWorld(), spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ());
        final Position a2 = a(spartanPlayer, spartanLocation);
        if (a2 != null) {
            player.sendBlockChange(location, (Material)((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(uniqueId)).get(a2), (byte)Byte.valueOf(((ConcurrentHashMap<Position, Byte>)ClientSidedBlock.T.get(uniqueId)).get((Object)a2)));
        }
        else if (spartanPlayer != null && spartanLocation != null && BlockUtils.F(a.getType())) {
            player.sendBlockChange(location, a.getType(), a.getData());
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!ClientSidedBlock.q.containsKey(uniqueId) || !ClientSidedBlock.T.containsKey(uniqueId)) {
            return;
        }
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        for (final Position position : ((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(uniqueId)).keySet()) {
            final Location location = new Location(ServerWorld.a(position.getID()), position.getX(), position.getY(), position.getZ());
            if (location.getWorld() == player.getWorld()) {
                final Block block = location.getBlock();
                player.sendBlockChange(location, block.getType(), block.getData());
            }
        }
        ClientSidedBlock.q.remove(uniqueId);
        ClientSidedBlock.T.remove(uniqueId);
    }
    
    public static void remove() {
        for (final Map.Entry<UUID, ConcurrentHashMap<Position, Material>> entry : ClientSidedBlock.q.entrySet()) {
            final UUID uuid = (UUID)entry.getKey();
            final Player player = Bukkit.getPlayer(uuid);
            for (final Position position : ((ConcurrentHashMap<Position, Material>)entry.getValue()).keySet()) {
                final Location location = new Location(ServerWorld.a(position.getID()), position.getX(), position.getY(), position.getZ());
                if (location.getWorld() == player.getWorld()) {
                    final Block block = location.getBlock();
                    player.sendBlockChange(location, block.getType(), block.getData());
                }
            }
            ClientSidedBlock.q.remove(uuid);
            ClientSidedBlock.T.remove(uuid);
        }
    }
    
    public static Material b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (spartanLocation == null || spartanLocation.isEmpty()) {
            return Material.AIR;
        }
        final SpartanBlock a = spartanLocation.a();
        if (spartanPlayer == null || ClientSidedBlock.q.isEmpty() || ClientSidedBlock.T.isEmpty()) {
            return a.getType();
        }
        final Position a2 = a(spartanPlayer, spartanLocation);
        if (a2 == null) {
            return a.getType();
        }
        return ((ConcurrentHashMap<Position, Material>)ClientSidedBlock.q.get(spartanPlayer.getUniqueId())).get(a2);
    }
    
    static {
        q = new HashMap<UUID, ConcurrentHashMap<Position, Material>>();
        T = new HashMap<UUID, ConcurrentHashMap<Position, Byte>>();
    }
}
