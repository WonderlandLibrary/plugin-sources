package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.c.SmashHit;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Angle
{
    private static final Enums.HackType a;
    
    public Angle() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 5 || !Checks.getBoolean("KillAura.check_angle") || NoHitDelay.E(spartanPlayer) || n < 1.5 || LatencyUtils.e(spartanPlayer, 250) || (PlayerData.aw(spartanPlayer) && MoveUtils.k(spartanPlayer) >= 0.4) || SmashHit.isEnabled() || PlayerData.ax(spartanPlayer) || !PlayerData.au(spartanPlayer)) {
            return;
        }
        final double h = CombatUtils.h(spartanPlayer, entity);
        if (h == 0.0) {
            return;
        }
        final double i = CombatUtils.i(spartanPlayer, entity);
        int j = 0;
        if (!MathUtils.b(h, 0.4 / n)) {
            j = 1;
        }
        else if (!MathUtils.b(h, 0.2)) {
            j = 2;
        }
        else if (i > 0.6) {
            j = 3;
        }
        if (j != 0) {
            KillAuraOverall.o(spartanPlayer);
            boolean b = false;
            if (CombatHeuristics.m(spartanPlayer)) {
                b = true;
            }
            else {
                final int a = LagManagement.a(spartanPlayer, 4);
                if (AttemptUtils.b(spartanPlayer, Angle.a.toString() + "=angle", 100) >= ((a > 8 || NoHitDelay.E(spartanPlayer) || PlayerData.aw(spartanPlayer)) ? 8 : a)) {
                    b = true;
                }
            }
            if (b) {
                new HackPrevention(spartanPlayer, Angle.a, "t: angle, c: " + j + ", a1: " + h + ", a2: " + i);
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
