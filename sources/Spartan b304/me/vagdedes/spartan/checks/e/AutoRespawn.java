package me.vagdedes.spartan.checks.e;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import me.vagdedes.spartan.system.Enums;

public class AutoRespawn
{
    private static final Enums.HackType a;
    private static final long d = 750L;
    private static final ConcurrentHashMap<UUID, Long> a;
    
    public AutoRespawn() {
        super();
    }
    
    public static void clear() {
        AutoRespawn.a.clear();
    }
    
    public static void j(final SpartanPlayer spartanPlayer) {
        if (VL.b(spartanPlayer, AutoRespawn.a, false) || PluginUtils.contains("respawn")) {
            return;
        }
        AutoRespawn.a.put(spartanPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
    }
    
    public static void run() {
        for (final UUID uuid : AutoRespawn.a.keySet()) {
            final long lng = System.currentTimeMillis() - Long.valueOf(AutoRespawn.a.get((Object)uuid));
            if (lng <= 750L) {
                final SpartanPlayer a = SpartanBukkit.a(uuid);
                if (a != null && !PlayerData.ba(a)) {
                    new HackPrevention(a, AutoRespawn.a, "ms: " + lng);
                }
            }
            AutoRespawn.a.remove(uuid);
        }
    }
    
    static {
        a = Enums.HackType.AutoRespawn;
        a = new ConcurrentHashMap<UUID, Long>();
    }
}
