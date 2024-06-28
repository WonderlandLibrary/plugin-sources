package me.vagdedes.spartan.checks.combat.a;

import me.vagdedes.spartan.checks.combat.a.d.CACache;
import me.vagdedes.spartan.checks.combat.HitReach;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.checks.combat.a.d.CAHistory;
import me.vagdedes.spartan.checks.combat.a.c.CAResultEvent;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.checks.combat.a.a.CAConfig;
import me.vagdedes.spartan.checks.combat.a.a.CATraining;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.checks.combat.a.d.CAResult;
import java.util.concurrent.ConcurrentHashMap;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.event.Listener;

public class CombatAnalysis implements Listener
{
    public static final Enums.HackType a;
    private static final double c = 50.0;
    private static final double d = 20.0;
    private static final double distance = 18.0;
    public static final int time = 600;
    public static final int c;
    public static final int d = 3;
    public static final int ping = 200;
    public static final String d = "default";
    public static final String e = "average";
    private static boolean d;
    private static final ConcurrentHashMap<String, CAResult> a;
    
    public CombatAnalysis() {
        super();
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer) {
        return !spartanPlayer.isOnline() || VL.b(spartanPlayer, CombatAnalysis.a, true) || CATraining.d(spartanPlayer) || CAConfig.getDataSize() < 100 || CombatAnalysis.d || (CombatAnalysis.d = NoHitDelay.E(spartanPlayer));
    }
    
    public static void a(final boolean b, final boolean b2) {
        CombatAnalysis.a.clear();
        CombatAnalysis.d = false;
        for (final SpartanPlayer spartanPlayer : NPC.a()) {
            CooldownUtils.n(spartanPlayer, "combat-analysis=");
            AttemptUtils.n(spartanPlayer, "combat-analysis=");
            MillisUtils.n(spartanPlayer, "combat-analysis=");
            DoubleUtils.n(spartanPlayer, "combat-analysis=");
        }
        if (b) {
            CAConfig.load();
        }
        else if (b2) {
            CAConfig.clear();
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        CAResult.c(spartanPlayer, "default");
        CAResult.c(spartanPlayer, "average");
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        for (final String key : CombatAnalysis.a.keySet()) {
            if (key.contains(spartanPlayer.getName() + "=")) {
                CombatAnalysis.a.remove(key);
            }
        }
    }
    
    @EventHandler
    private void a(final CAResultEvent caResultEvent) {
        final SpartanPlayer spartanPlayer = new SpartanPlayer(caResultEvent.getPlayer());
        final CAEventListeners.a a = caResultEvent.a();
        CAResult a2 = a(spartanPlayer, a, caResultEvent.b(), "default");
        CAResult a3 = a(spartanPlayer, a, caResultEvent.a(), "average");
        if (a2 != null) {
            final String string = spartanPlayer.getName() + "=" + "default";
            if (!CombatAnalysis.a.containsKey(string)) {
                CombatAnalysis.a.put(string, a2);
            }
            else {
                a2 = CombatAnalysis.a.get(string);
            }
        }
        if (a3 != null) {
            final String string2 = spartanPlayer.getName() + "=" + "average";
            if (!CombatAnalysis.a.containsKey(string2)) {
                CombatAnalysis.a.put(string2, a3);
            }
            else {
                a3 = CombatAnalysis.a.get(string2);
            }
        }
        if (a2 != null && a3 != null) {
            CAHistory.a(spartanPlayer, (a2.c() + a3.c()) / 2.0);
            CAHistory.a(spartanPlayer, Math.max(a2.getScore(), a3.getScore()));
            for (final Entity entity : spartanPlayer.getNearbyEntities(18.0, 18.0, 18.0)) {
                if (entity instanceof Player) {
                    a(spartanPlayer, SpartanBukkit.a(entity.getUniqueId()));
                }
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        if (CAHistory.f(spartanPlayer2) && CAHistory.a(spartanPlayer, spartanPlayer2) && (PlayerData.au(spartanPlayer2) || PlayerData.av(spartanPlayer2)) && PlayerData.c(spartanPlayer, spartanPlayer2)) {
            final int b = CAHistory.b(spartanPlayer);
            final int b2 = CAHistory.b(spartanPlayer2);
            final int abs = Math.abs(b - b2);
            final SpartanPlayer spartanPlayer3 = (b >= b2) ? spartanPlayer : spartanPlayer2;
            if (abs >= Math.round((double)CombatAnalysis.c / 2.0)) {
                final int c = CAHistory.c(spartanPlayer3);
                final double n = (double)LatencyUtils.u(spartanPlayer3);
                final double n2 = (CAHistory.h(spartanPlayer3) && abs >= CombatAnalysis.c) ? 2.0 : 2.5;
                double n3 = CAHistory.a(spartanPlayer3) - CombatUtils.j(spartanPlayer3) - MathUtils.percentage(Math.min(b, b2), Math.max(b, b2)) / n2 - (CombatHeuristics.m(spartanPlayer) ? HitReach.a(spartanPlayer3, null) : 0.0) + ((n > 50.0) ? (n / 50.0 * n2) : 0.0);
                if (n3 > 50.0 && n3 < 51.0) {
                    n3 = (double)Math.round(n3);
                }
                if (n3 <= 50.0) {
                    if (c <= 3) {
                        VL.c(spartanPlayer3, CombatAnalysis.a, "p: " + n3 + ", d: " + abs + ", f: " + c + ", s: " + CAConfig.getDataSize() + ", h: " + CombatAnalysis.c);
                    }
                    CAHistory.l(spartanPlayer3);
                }
                else {
                    CAHistory.k(spartanPlayer3);
                }
            }
            CAHistory.c(spartanPlayer, spartanPlayer2);
        }
    }
    
    private static CAResult a(final SpartanPlayer spartanPlayer, final CAEventListeners.a a, final double n, final String str) {
        final CAResult a2 = CAResult.a(spartanPlayer, str, CombatAnalysis.c);
        if (a2 != null) {
            return a2;
        }
        final double a3 = CACache.a(spartanPlayer, a, n, str);
        if (a3 >= 0.0) {
            final double a4 = CACache.a(spartanPlayer, a, a3, str + "=1");
            if (a4 >= 0.0) {
                double abs = -1.0;
                if (a4 <= 20.0) {
                    final double a5 = CACache.a(spartanPlayer, a.toString(), a4, ((a == CAEventListeners.a.b) ? CAEventListeners.a.c : a).toString());
                    if (a5 >= 0.0) {
                        if (a5 <= 10.0) {
                            abs = Math.abs(a4 - a5);
                        }
                        else {
                            abs = 20.0;
                        }
                    }
                }
                else {
                    abs = 20.0;
                }
                if (abs >= 0.0) {
                    CAResult.a(spartanPlayer, 600, abs > 5.0, str);
                }
            }
        }
        return null;
    }
    
    static {
        a = Enums.HackType.CombatAnalysis;
        c = (int)Math.round(15.0);
        CombatAnalysis.d = false;
        a = new ConcurrentHashMap<String, CAResult>();
    }
}
