package me.vagdedes.spartan.j;

import java.util.Iterator;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.c.ProtocolLib;
import org.bukkit.entity.Player;
import java.util.UUID;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.concurrent.CopyOnWriteArrayList;

public class NPC
{
    private static final CopyOnWriteArrayList<SpartanPlayer> b;
    private static final CopyOnWriteArrayList<UUID> c;
    public static final long e = 3000L;
    
    public NPC() {
        super();
    }
    
    public static void clear() {
        NPC.b.clear();
        NPC.c.clear();
    }
    
    public static boolean D(final SpartanPlayer spartanPlayer) {
        return ac(spartanPlayer) || ((System.currentTimeMillis() - spartanPlayer.getLastPlayed() <= 3000L) ? (spartanPlayer.getAddress() == null) : (!NPC.c.contains(spartanPlayer.getUniqueId())));
    }
    
    public static boolean is(final Player player) {
        return e(player) || ((System.currentTimeMillis() - player.getLastPlayed() <= 3000L) ? (player.getAddress() == null) : (!NPC.c.contains(player.getUniqueId())));
    }
    
    public static boolean e(final Player player) {
        return player == null || ProtocolLib.b(player);
    }
    
    public static boolean ac(final SpartanPlayer spartanPlayer) {
        return spartanPlayer == null || spartanPlayer.k();
    }
    
    public static void cache() {
        if (VersionUtils.a() != VersionUtils.a.c) {
            for (final Player player : Bukkit.getOnlinePlayers()) {
                if (player.getAddress() != null) {
                    I(SpartanBukkit.b(player));
                }
            }
        }
    }
    
    public static void I(final SpartanPlayer e) {
        NPC.b.add(e);
        NPC.c.add(e.getUniqueId());
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        for (final SpartanPlayer o : a()) {
            if (spartanPlayer.getUniqueId().equals(o.getUniqueId())) {
                NPC.b.remove(o);
                break;
            }
        }
        NPC.c.remove(spartanPlayer.getUniqueId());
    }
    
    public static SpartanPlayer[] a() {
        final Iterator<SpartanPlayer> iterator = NPC.b.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                NPC.b.remove(index);
            }
            ++index;
        }
        return NPC.b.<SpartanPlayer>toArray(new SpartanPlayer[0]);
    }
    
    static {
        b = new CopyOnWriteArrayList<SpartanPlayer>();
        c = new CopyOnWriteArrayList<UUID>();
    }
}
