package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.checks.combat.b.HitRatio;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Combined
{
    private static final Enums.HackType a;
    
    public Combined() {
        super();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, Combined.a.toString() + "=dif");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n) {
        if (!Checks.getBoolean("KillAura.check_combined") || n < 1.0 || CombatUtils.c(spartanPlayer, entity)) {
            return;
        }
        final double f = CombatUtils.f(spartanPlayer, entity);
        final double e = CombatUtils.e(spartanPlayer, entity);
        final double h = CombatUtils.h(spartanPlayer, entity);
        final long a = TimeBetweenClicks.a(spartanPlayer, false);
        final double a2 = DoubleUtils.a(spartanPlayer, Combined.a.toString() + "=dif");
        if (DoubleUtils.h(a2) && a != 0L) {
            final SpartanLocation a3 = CombatUtils.a(spartanPlayer, entity);
            final double d = (a3 == null) ? 0.0 : Math.abs(a3.getX());
            final double d2 = (a3 == null) ? 0.0 : Math.abs(a3.getZ());
            final boolean b = entity instanceof Player;
            final double n2 = b ? HitRatio.c(spartanPlayer, SpartanBukkit.a(entity.getUniqueId())) : 0.0;
            final double n3 = (VL.a(spartanPlayer, Combined.a) >= 2 || n2 >= 35.0) ? 6.0 : 7.0;
            if (d >= n3 || d2 >= n3) {
                final double abs = Math.abs(f - a2);
                if (abs <= 0.2 && h >= 0.99 && e <= 12.0 && a <= 350L) {
                    KillAuraOverall.o(spartanPlayer);
                    int i = 0;
                    if (CombatHeuristics.i(spartanPlayer)) {
                        i = 1;
                    }
                    else if (PlayerData.au(spartanPlayer) && (!b || PlayerData.av(SpartanBukkit.a(entity.getUniqueId())))) {
                        i = 2;
                    }
                    else if (CombatHeuristics.m(spartanPlayer)) {
                        i = 3;
                    }
                    else if (b && PlayerData.au(SpartanBukkit.a(entity.getUniqueId())) && PlayerData.av(SpartanBukkit.a(entity.getUniqueId()))) {
                        i = 4;
                    }
                    else if (CombatHeuristics.j(spartanPlayer)) {
                        i = 5;
                    }
                    else if (CombatHeuristics.k(spartanPlayer)) {
                        i = 6;
                    }
                    else if (CombatHeuristics.l(spartanPlayer)) {
                        i = 7;
                    }
                    else if (!b && CombatHeuristics.a(entity)) {
                        i = 8;
                    }
                    else if (n2 >= 35.0) {
                        i = 9;
                    }
                    if (i != 0 && (i == 3 || AttemptUtils.b(spartanPlayer, Combined.a.toString() + "=combined", 100) >= 2)) {
                        new HackPrevention(spartanPlayer, Combined.a, "t: combined, diff: " + abs + ", a: " + h + ", r: " + e + ", x: " + d + ", z: " + d2 + ", t_b_c: " + a + ", c: " + i);
                    }
                }
            }
            DoubleUtils.a(spartanPlayer, Combined.a.toString() + "=dif", f);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
