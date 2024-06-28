package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.Location;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class YawRate
{
    public YawRate() {
        super();
    }
    
    public static void n(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, "yaw-rate=last");
        DoubleUtils.j(spartanPlayer, "yaw-rate=old");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Location location) {
        final double n = (double)location.getYaw();
        final double a = DoubleUtils.a(spartanPlayer, "yaw-rate=old");
        if (DoubleUtils.h(a)) {
            DoubleUtils.a(spartanPlayer, "yaw-rate=last", CombatUtils.a(a, n));
            CooldownUtils.d(spartanPlayer, "yaw-rate=last", 4);
        }
        DoubleUtils.a(spartanPlayer, "yaw-rate=old", n);
    }
    
    public static float a(final SpartanPlayer spartanPlayer) {
        return CooldownUtils.g(spartanPlayer, "yaw-rate=last") ? 0.0f : ((float)DoubleUtils.a(spartanPlayer, "yaw-rate=last"));
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "yaw-rate=last");
    }
}
