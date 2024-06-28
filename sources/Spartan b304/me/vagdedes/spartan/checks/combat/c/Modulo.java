package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Modulo
{
    private static final Enums.HackType a;
    
    public Modulo() {
        super();
    }
    
    public static void n(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, Modulo.a.toString() + "=modulo=cached");
        AttemptUtils.c(spartanPlayer, Modulo.a.toString() + "=modulo=teleport", 1);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (MoveUtils.b(spartanLocation, spartanLocation2)) {
            AttemptUtils.m(spartanPlayer, Modulo.a.toString() + "=modulo=teleport");
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!Checks.getBoolean("KillAura.check_modulo") || AttemptUtils.a(spartanPlayer, Modulo.a.toString() + "=modulo=teleport") != 0 || CombatUtils.c(spartanPlayer, entity)) {
            return;
        }
        final SpartanLocation a = spartanPlayer.a();
        final double n = (double)Math.abs(a.getPitch());
        final double d = (double)Math.abs(a.getYaw());
        boolean b = false;
        final double d2 = d % 0.5;
        if (d2 == 0.0 && VL.a(spartanPlayer, Modulo.a) > 0) {
            new HackPrevention(spartanPlayer, Modulo.a, "t: modulo(normal), yd: " + d2 + ", y: " + d);
            b = true;
        }
        final double a2 = DoubleUtils.a(spartanPlayer, Modulo.a.toString() + "=modulo=cached");
        if (!b && DoubleUtils.h(a2)) {
            final double abs = Math.abs(n - a2);
            final double d3 = abs % 0.5;
            if (abs >= 15.0 && d3 == 0.0) {
                new HackPrevention(spartanPlayer, Modulo.a, "t: modulo(cached), pd: " + d3 + ", p: " + abs);
            }
        }
        DoubleUtils.a(spartanPlayer, Modulo.a.toString() + "=modulo=cached", n);
        if (b) {
            KillAuraOverall.o(spartanPlayer);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
