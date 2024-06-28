package me.vagdedes.spartan.features.d;

import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.features.b.FileLogs;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.a.a.Settings;
import java.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class PerformanceOptimizer
{
    private static final ArrayList<UUID> b;
    private static final HashMap<UUID, Integer> M;
    private static final ConcurrentHashMap<UUID, Integer> c;
    private static final int t = 7;
    private static final int max = 1200;
    private static int time;
    public static final String message = "has been considered a legitimate player and is exempted from all checks.";
    
    public PerformanceOptimizer() {
        super();
    }
    
    public static boolean d() {
        return Settings.canDo("enable_performance_optimizer") && SearchEngine.d() && NPC.a().length > 5 && TPS.get() >= 18.0;
    }
    
    public static void c(final SpartanPlayer[] array) {
        if (d()) {
            if (PerformanceOptimizer.time == 0) {
                PerformanceOptimizer.time = 1200;
                for (final SpartanPlayer spartanPlayer : array) {
                    final UUID uniqueId = spartanPlayer.getUniqueId();
                    if (PerformanceOptimizer.b.contains(uniqueId)) {
                        PerformanceOptimizer.b.remove(uniqueId);
                        if (!PerformanceOptimizer.M.containsKey(uniqueId)) {
                            PerformanceOptimizer.M.put(uniqueId, Integer.valueOf(1));
                        }
                        else {
                            final int j = Integer.valueOf(PerformanceOptimizer.M.get((Object)uniqueId)) + 1;
                            PerformanceOptimizer.M.put(uniqueId, Integer.valueOf(j));
                            if (j >= 7) {
                                FileLogs.a("[Spartan " + Register.plugin.getDescription().getVersion() + "] " + spartanPlayer.getName() + " " + "has been considered a legitimate player and is exempted from all checks.", true);
                                SearchEngine.c(spartanPlayer.getUniqueId());
                            }
                        }
                    }
                    else if (g(spartanPlayer)) {
                        PerformanceOptimizer.b.add(uniqueId);
                    }
                }
            }
            else {
                --PerformanceOptimizer.time;
            }
            for (int length2 = array.length, k = 0; k < length2; ++k) {
                final UUID uniqueId2 = array[k].getUniqueId();
                if (PerformanceOptimizer.c.containsKey(uniqueId2)) {
                    final int l = Integer.valueOf(PerformanceOptimizer.c.get((Object)uniqueId2)) - 1;
                    if (l == 0) {
                        PerformanceOptimizer.c.remove(uniqueId2);
                    }
                    else {
                        PerformanceOptimizer.c.put(uniqueId2, Integer.valueOf(l));
                    }
                }
            }
        }
        else {
            clear();
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final int n) {
        if (d()) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            if (n == 5.0 && !PerformanceOptimizer.c.containsKey(uniqueId)) {
                PerformanceOptimizer.c.put(uniqueId, Integer.valueOf(4800));
                if (PerformanceOptimizer.M.containsKey(uniqueId)) {
                    final int intValue = (int)Integer.valueOf(PerformanceOptimizer.M.get((Object)uniqueId));
                    if (intValue == 1) {
                        PerformanceOptimizer.M.remove(uniqueId);
                    }
                    else {
                        PerformanceOptimizer.M.put(uniqueId, Integer.valueOf(intValue - 1));
                    }
                }
            }
        }
    }
    
    public static void clear() {
        PerformanceOptimizer.b.clear();
        PerformanceOptimizer.c.clear();
        PerformanceOptimizer.M.clear();
        PerformanceOptimizer.time = 1200;
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        PerformanceOptimizer.b.remove(uniqueId);
        PerformanceOptimizer.c.remove(uniqueId);
        PerformanceOptimizer.M.remove(uniqueId);
    }
    
    public static boolean O(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return (((PerformanceOptimizer.M.containsKey(uniqueId) && Integer.valueOf(PerformanceOptimizer.M.get((Object)uniqueId)) >= 7) || PerformanceOptimizer.b.contains(uniqueId)) && !PerformanceOptimizer.c.containsKey(uniqueId)) || SearchEngine.c(spartanPlayer.getUniqueId());
    }
    
    private static boolean g(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return !PerformanceOptimizer.c.containsKey(uniqueId) && (!PerformanceOptimizer.M.containsKey(uniqueId) || Integer.valueOf(PerformanceOptimizer.M.get((Object)uniqueId)) < 7) && !SearchEngine.a((OfflinePlayer)Bukkit.getPlayer(spartanPlayer.getUniqueId()));
    }
    
    public static boolean M(final SpartanPlayer spartanPlayer) {
        return PerformanceOptimizer.c.containsKey(spartanPlayer.getUniqueId());
    }
    
    static {
        b = new ArrayList<UUID>();
        M = new HashMap<UUID, Integer>();
        c = new ConcurrentHashMap<UUID, Integer>();
        PerformanceOptimizer.time = 0;
    }
}
