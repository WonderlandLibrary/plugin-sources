package me.vagdedes.spartan.k.f;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.checks.b.PingSpoof;
import me.vagdedes.spartan.k.b.ReflectionUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.CheckLatency;
import java.util.HashMap;

public class LatencyUtils
{
    private static int Z;
    public static final int limit = 25;
    private static final HashMap<String, CheckLatency> q;
    
    public LatencyUtils() {
        super();
    }
    
    public static void clear() {
        LatencyUtils.q.clear();
    }
    
    public static boolean i(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        return LatencyUtils.q.containsKey(spartanPlayer.getName() + "." + obj);
    }
    
    public static CheckLatency a(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        return LatencyUtils.q.get(spartanPlayer.getName() + "." + obj);
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        LatencyUtils.q.put(spartanPlayer.getName() + "." + obj, new CheckLatency(spartanPlayer));
    }
    
    public static int c(final Player player) {
        if (LatencyUtils.Z == -1) {
            return 0;
        }
        try {
            if (LatencyUtils.Z != 0) {
                final int intValue = (int)ReflectionUtils.getCraftPlayer(player, "ping");
                return (intValue < 0) ? 0 : intValue;
            }
            final Object craftPlayer = ReflectionUtils.getCraftPlayer(player, "ping");
            if (craftPlayer != null) {
                LatencyUtils.Z = 1;
                final int intValue2 = (int)craftPlayer;
                return (intValue2 < 0) ? 0 : intValue2;
            }
            LatencyUtils.Z = -1;
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public static int u(final SpartanPlayer spartanPlayer) {
        return (PingSpoof.o(spartanPlayer) || VL.a(spartanPlayer, Enums.HackType.Exploits) > 0) ? 0 : spartanPlayer.getPing();
    }
    
    public static boolean e(final SpartanPlayer spartanPlayer, final int n) {
        final int ping = spartanPlayer.getPing();
        return Register.arePlibPacketsEnabled() && !PingSpoof.o(spartanPlayer) && VL.a(spartanPlayer, Enums.HackType.Exploits) == 0 && VL.o(spartanPlayer) <= 3 && ping >= n && ping <= 2500;
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (!Settings.canDo("use_ping_protection")) {
            return;
        }
        final boolean b = !PingSpoof.o(spartanPlayer) && !bi(spartanPlayer) && CooldownUtils.g(spartanPlayer, "ping=protection=join");
        final int ping = spartanPlayer.getPing();
        if (b && AttemptUtils.f(spartanPlayer, "ping")) {
            final int a = AttemptUtils.a(spartanPlayer, "ping");
            if (a > 0 && ping >= a + 25) {
                CooldownUtils.d(spartanPlayer, "ping=protection", 10);
            }
        }
        AttemptUtils.c(spartanPlayer, "ping", ping);
    }
    
    public static boolean bi(final SpartanPlayer spartanPlayer) {
        return Settings.canDo("use_ping_protection") && !CooldownUtils.g(spartanPlayer, "ping=protection");
    }
    
    public static void Q(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "ping=protection=join", 200);
    }
    
    static {
        LatencyUtils.Z = 0;
        q = new HashMap<String, CheckLatency>(Enums.HackType.values().length);
    }
}
