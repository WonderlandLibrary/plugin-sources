package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.b.HitRatio;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class Comparison
{
    private static final Enums.HackType a;
    private static final double g = 0.1;
    private static final double h = 0.3;
    private static final double i = 0.45;
    private static final HashMap<UUID, HashMap<UUID, Double>> q;
    
    public Comparison() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        Comparison.q.remove(spartanPlayer.getUniqueId());
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final boolean b) {
        if ((!b || MoveUtils.b(spartanPlayer)) && !VL.b(spartanPlayer, Comparison.a, true)) {
            for (final Entity entity : spartanPlayer.getNearbyEntities(6.0, 6.0, 6.0)) {
                if (CombatHeuristics.a(spartanPlayer, null, entity)) {
                    if (MathUtils.b(spartanPlayer.a(), new SpartanLocation(entity.getLocation())) >= 1.0) {
                        c(spartanPlayer, entity);
                    }
                    else {
                        d(spartanPlayer, entity);
                    }
                }
            }
        }
        else {
            a(spartanPlayer);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n) {
        if (!Checks.getBoolean("KillAura.check_comparison") || n < 1.0) {
            d(spartanPlayer, entity);
            return;
        }
        a(spartanPlayer, true);
        final boolean b = entity instanceof Player;
        final boolean c = CombatUtils.c(spartanPlayer, entity);
        final double n2 = ((b && !c) || PlayerData.c(spartanPlayer, 6.0) > 5) ? 0.45 : (b ? 0.3 : 0.1);
        final double f = CombatUtils.f(spartanPlayer, entity);
        final double b2 = b(spartanPlayer, entity);
        final double abs = Math.abs(f - b2);
        final long a = TimeBetweenClicks.a(spartanPlayer, false);
        if (b2 != 0.0 && abs != 0.0 && a > 0L && a <= 550L) {
            boolean b3 = false;
            final SpartanLocation a2 = CombatUtils.a(spartanPlayer, entity);
            final double n3 = (a2 == null) ? 0.0 : Math.abs(a2.getX());
            final double n4 = (a2 == null) ? 0.0 : Math.abs(a2.getZ());
            final int a3 = VL.a(spartanPlayer, Comparison.a);
            if (abs >= 2.0) {
                new HackPrevention(spartanPlayer, Comparison.a, "t: comparison(instant), r: " + abs);
                b3 = true;
            }
            else {
                final double n5 = (entity instanceof Player) ? HitRatio.c(spartanPlayer, SpartanBukkit.a(entity.getUniqueId())) : 0.0;
                if (n <= 3.5 || !MoveUtils.ar(spartanPlayer)) {
                    if (abs >= 0.01) {
                        final double d = (a3 >= 2 || n5 >= 35.0) ? 6.0 : 7.0;
                        if ((n3 >= d || n4 >= d) && AttemptUtils.b(spartanPlayer, Comparison.a.toString() + "=comparison=sensitive", 100) >= ((n5 >= 35.0) ? 2 : 3)) {
                            new HackPrevention(spartanPlayer, Comparison.a, "t: comparison(sensitive), r: " + abs + ", l: " + d);
                            b3 = true;
                        }
                    }
                    if (abs >= 0.1 && (n3 >= 6.0 || n4 >= 6.0) && AttemptUtils.b(spartanPlayer, Comparison.a.toString() + "=comparison=hard", 100) >= 2) {
                        new HackPrevention(spartanPlayer, Comparison.a, "t: comparison(hard), r: " + abs);
                        b3 = true;
                    }
                }
                if (abs >= n2 && AttemptUtils.b(spartanPlayer, Comparison.a.toString() + "=comparison=normal", 100) >= 2) {
                    new HackPrevention(spartanPlayer, Comparison.a, "t: comparison(normal), r: " + abs + ", m: " + n2);
                    b3 = true;
                }
                if (abs > 0.0 && c && AttemptUtils.b(spartanPlayer, Comparison.a.toString() + "=comparison=constant", 100) >= ((n5 >= 35.0) ? 6 : 8)) {
                    new HackPrevention(spartanPlayer, Comparison.a, "t: comparison(constant), r: " + abs + ", m: " + n2);
                    b3 = true;
                }
            }
            if (b3) {
                KillAuraOverall.o(spartanPlayer);
            }
        }
    }
    
    private static double b(final SpartanPlayer spartanPlayer, final Entity entity) {
        return (Comparison.q.containsKey(spartanPlayer.getUniqueId()) && ((HashMap<UUID, Double>)Comparison.q.get(spartanPlayer.getUniqueId())).containsKey(entity.getUniqueId())) ? ((double)Double.valueOf(((HashMap<UUID, Double>)Comparison.q.get(spartanPlayer.getUniqueId())).get((Object)entity.getUniqueId()))) : 0.0;
    }
    
    private static void c(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!Comparison.q.containsKey(spartanPlayer.getUniqueId())) {
            Comparison.q.put(spartanPlayer.getUniqueId(), new HashMap<UUID, Double>());
        }
        ((HashMap<UUID, Double>)Comparison.q.get(spartanPlayer.getUniqueId())).put(entity.getUniqueId(), Double.valueOf(CombatUtils.f(spartanPlayer, entity)));
    }
    
    private static void d(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (Comparison.q.containsKey(spartanPlayer.getUniqueId())) {
            ((HashMap<UUID, Double>)Comparison.q.get(spartanPlayer.getUniqueId())).remove(entity.getUniqueId());
        }
    }
    
    public static void clear() {
        Comparison.q.clear();
    }
    
    static {
        a = Enums.HackType.KillAura;
        q = new HashMap<UUID, HashMap<UUID, Double>>();
    }
}
