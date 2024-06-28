package me.vagdedes.spartan.j;

import me.vagdedes.spartan.k.f.TPS;
import org.bukkit.OfflinePlayer;
import java.util.Iterator;
import me.vagdedes.ultimatestatistics.system.Enums;
import me.vagdedes.ultimatestatistics.api.UltimateStatisticsAPI;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.features.c.CpsCounter;
import java.util.UUID;
import java.util.HashMap;

public class UltimateStatistics
{
    private static boolean enabled;
    private static double av;
    private static double aw;
    private static int S;
    private static final HashMap<UUID, Boolean> q;
    
    public UltimateStatistics() {
        super();
    }
    
    public static boolean isEnabled() {
        return UltimateStatistics.enabled;
    }
    
    public static int l() {
        return UltimateStatistics.S;
    }
    
    public static void d(final boolean b) {
        UltimateStatistics.q.clear();
        UltimateStatistics.av = 0.0;
        UltimateStatistics.aw = 0.0;
        UltimateStatistics.S = 0;
        UltimateStatistics.enabled = false;
        CpsCounter.refresh();
        if (b) {
            final Compatibility compatibility = new Compatibility("UltimateStatistics");
            if (compatibility.isEnabled() && ((UltimateStatistics.enabled = PluginUtils.exists("UltimateStatistics")) || compatibility.c())) {
                final Iterator<UUID> iterator;
                UUID uuid;
                final Integer n;
                int n2 = 0;
                boolean b2 = false;
                final Integer n3;
                int n4 = 0;
                Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> {
                    try {
                        UltimateStatisticsAPI.getStats().iterator();
                        while (iterator.hasNext()) {
                            uuid = iterator.next();
                            if (UltimateStatisticsAPI.hasData(uuid, Enums.EventType.Times_Kicked)) {
                                UltimateStatisticsAPI.getStats(uuid, Enums.EventType.Times_Kicked, "amount");
                                UltimateStatistics.av += ((n instanceof Integer) ? ((double)Integer.valueOf(n)) : 0.0);
                                ++n2;
                                b2 = true;
                            }
                            if (UltimateStatisticsAPI.hasData(uuid, Enums.EventType.Distance_Travelled)) {
                                UltimateStatisticsAPI.getStats(uuid, Enums.EventType.Distance_Travelled, "amount");
                                UltimateStatistics.aw += ((n3 instanceof Integer) ? ((double)Integer.valueOf(n3)) : 0.0);
                                ++n4;
                                b2 = true;
                            }
                            if (b2) {
                                ++UltimateStatistics.S;
                            }
                        }
                        if (n2 > 0) {
                            UltimateStatistics.av /= n2;
                        }
                        if (n4 > 0) {
                            UltimateStatistics.aw /= n4;
                        }
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        UltimateStatistics.enabled = false;
                    }
                });
            }
        }
    }
    
    public static boolean a(final OfflinePlayer offlinePlayer) {
        if (!UltimateStatistics.enabled || TPS.get() < 18.0) {
            return false;
        }
        final UUID uniqueId = offlinePlayer.getUniqueId();
        if (UltimateStatistics.q.containsKey(uniqueId)) {
            return Boolean.valueOf(UltimateStatistics.q.get((Object)uniqueId));
        }
        final boolean b = a(offlinePlayer) >= 3.0 || b(offlinePlayer) >= 50.0 || c(offlinePlayer) >= 10000.0;
        UltimateStatistics.q.put(uniqueId, Boolean.valueOf(b));
        return b;
    }
    
    public static double a(final OfflinePlayer offlinePlayer) {
        final UUID uniqueId = offlinePlayer.getUniqueId();
        int n = 0;
        int n2 = 0;
        final Object stats = UltimateStatisticsAPI.getStats(uniqueId, Enums.EventType.Players_Killed, "amount");
        if (stats != null && !stats.equals("None")) {
            n = ((stats instanceof Integer) ? ((int)stats) : 0);
        }
        final Object stats2 = UltimateStatisticsAPI.getStats(uniqueId, Enums.EventType.Deaths_By_Player, "amount");
        if (stats2 != null && !stats2.equals("None")) {
            n2 = ((stats2 instanceof Integer) ? ((int)stats2) : 0);
        }
        if (n > 0 && n2 > 0) {
            return n / (double)n2;
        }
        return 0.0;
    }
    
    public static double b(final OfflinePlayer offlinePlayer) {
        if (UltimateStatistics.av > 0.0) {
            final Object stats = UltimateStatisticsAPI.getStats(offlinePlayer.getUniqueId(), Enums.EventType.Times_Kicked, "amount");
            if (stats != null && !stats.equals("None")) {
                final int n = (stats instanceof Integer) ? ((int)stats) : 0;
                if (n > UltimateStatistics.av) {
                    return n - UltimateStatistics.av;
                }
            }
        }
        return 0.0;
    }
    
    public static double c(final OfflinePlayer offlinePlayer) {
        if (UltimateStatistics.aw > 0.0) {
            final Object stats = UltimateStatisticsAPI.getStats(offlinePlayer.getUniqueId(), Enums.EventType.Distance_Travelled, "amount");
            if (stats != null && !stats.equals("None")) {
                final int n = (stats instanceof Integer) ? ((int)stats) : 0;
                if (n > UltimateStatistics.aw) {
                    return n - UltimateStatistics.aw;
                }
            }
        }
        return 0.0;
    }
    
    private static /* synthetic */ void h() {
        int n = 0;
        int n2 = 0;
        try {
            for (final UUID uuid : UltimateStatisticsAPI.getStats()) {
                boolean b = false;
                if (UltimateStatisticsAPI.hasData(uuid, Enums.EventType.Times_Kicked)) {
                    final Object stats = UltimateStatisticsAPI.getStats(uuid, Enums.EventType.Times_Kicked, "amount");
                    UltimateStatistics.av += ((stats instanceof Integer) ? ((double)(int)stats) : 0.0);
                    ++n;
                    b = true;
                }
                if (UltimateStatisticsAPI.hasData(uuid, Enums.EventType.Distance_Travelled)) {
                    final Object stats2 = UltimateStatisticsAPI.getStats(uuid, Enums.EventType.Distance_Travelled, "amount");
                    UltimateStatistics.aw += ((stats2 instanceof Integer) ? ((double)(int)stats2) : 0.0);
                    ++n2;
                    b = true;
                }
                if (b) {
                    ++UltimateStatistics.S;
                }
            }
            if (n > 0) {
                UltimateStatistics.av /= n;
            }
            if (n2 > 0) {
                UltimateStatistics.aw /= n2;
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {
            UltimateStatistics.enabled = false;
        }
    }
    
    static {
        UltimateStatistics.enabled = false;
        UltimateStatistics.av = 0.0;
        UltimateStatistics.aw = 0.0;
        UltimateStatistics.S = 0;
        q = new HashMap<UUID, Boolean>();
    }
}
