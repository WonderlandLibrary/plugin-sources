package me.vagdedes.spartan.h;

import java.util.Iterator;
import me.vagdedes.spartan.k.a.BlockUtils;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;

public class EnderPearl
{
    private static final HashMap<UUID, SpartanLocation> q;
    
    public EnderPearl() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        EnderPearl.q.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        EnderPearl.q.clear();
    }
    
    public static void r(final SpartanPlayer spartanPlayer) {
        if (EnderPearl.q.containsKey(spartanPlayer.getUniqueId())) {
            final SpartanLocation a = spartanPlayer.a();
            final SpartanLocation spartanLocation = (SpartanLocation)EnderPearl.q.get(spartanPlayer.getUniqueId());
            if (a.getWorld() != spartanLocation.getWorld() || a.a(spartanLocation) >= 2.5) {
                EnderPearl.q.remove(spartanPlayer.getUniqueId());
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation value, final PlayerTeleportEvent.TeleportCause teleportCause) {
        if (teleportCause == PlayerTeleportEvent.TeleportCause.ENDER_PEARL && !h(spartanPlayer, value)) {
            EnderPearl.q.put(spartanPlayer.getUniqueId(), value);
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return spartanPlayer != null && EnderPearl.q.containsKey(spartanPlayer.getUniqueId());
    }
    
    private static boolean h(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        for (int i = 0; i <= 1; ++i) {
            if (!b(spartanPlayer, spartanLocation, 0.3, i, 0.3)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanLocation, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (BlockUtils.f(spartanPlayer, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    static {
        q = new HashMap<UUID, SpartanLocation>();
    }
}
