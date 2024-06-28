package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.HackPrevention;
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

public class AimConsistency
{
    private static final Enums.HackType a;
    
    public AimConsistency() {
        super();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, AimConsistency.a.toString() + "=dir");
        DoubleUtils.j(spartanPlayer, AimConsistency.a.toString() + "=rot");
        DoubleUtils.j(spartanPlayer, AimConsistency.a.toString() + "=ang");
        DoubleUtils.j(spartanPlayer, AimConsistency.a.toString() + "=pit");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_aim_consistency") || n < 1.0 || CombatUtils.c(spartanPlayer, entity)) {
            return;
        }
        final double f = CombatUtils.f(spartanPlayer, entity);
        final double e = CombatUtils.e(spartanPlayer, entity);
        final double h = CombatUtils.h(spartanPlayer, entity);
        final double n3 = (double)Math.abs(spartanPlayer.a().getPitch());
        final long a = TimeBetweenClicks.a(spartanPlayer, false);
        final double a2 = DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=dir");
        final double a3 = DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=rot");
        final double a4 = DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=ang");
        final double a5 = DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=pit");
        if (DoubleUtils.h(a2) && DoubleUtils.h(a3) && DoubleUtils.h(a4) && DoubleUtils.h(a5)) {
            final double abs = Math.abs(a2 - f);
            final double abs2 = Math.abs(a3 - e);
            final double abs3 = Math.abs(a4 - h);
            final double abs4 = Math.abs(a5 - n3);
            if (abs < 0.2 && f <= 0.5 && abs2 <= 4.0 && e <= 25.0 && abs3 < 0.01 && h >= 0.999 && abs4 > 0.0 && abs4 < 8.0 && a > 0L && a <= 550L) {
                final SpartanLocation a6 = CombatUtils.a(spartanPlayer, entity);
                final double d = (a6 == null) ? 0.0 : Math.abs(a6.getX());
                final double d2 = (a6 == null) ? 0.0 : Math.abs(a6.getZ());
                final boolean b = entity instanceof Player;
                final double n4 = b ? HitRatio.c(spartanPlayer, SpartanBukkit.a(entity.getUniqueId())) : 0.0;
                final double n5 = (VL.a(spartanPlayer, AimConsistency.a) >= 2 || n4 >= 35.0) ? 6.0 : 7.0;
                if (d >= n5 || d2 >= n5) {
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
                    else if (n4 >= 35.0) {
                        i = 9;
                    }
                    if (i != 0) {
                        new HackPrevention(spartanPlayer, AimConsistency.a, "t: aim-consistency, dir: " + abs + ", rot: " + abs2 + ", ang: " + abs3 + ", x: " + d + ", z: " + d2 + ", t_b_c: " + a + ", pit: " + abs4 + ", c: " + i);
                    }
                }
            }
            DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=dir", f);
            DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=rot", e);
            DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=ang", h);
            DoubleUtils.a(spartanPlayer, AimConsistency.a.toString() + "=pit", n3);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
