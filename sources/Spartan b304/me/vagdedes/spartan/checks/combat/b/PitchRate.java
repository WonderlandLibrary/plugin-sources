package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.Location;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class PitchRate
{
    public PitchRate() {
        super();
    }
    
    public static void n(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, "pitch-rate=last");
        DoubleUtils.j(spartanPlayer, "pitch-rate=old");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Location location) {
        final double n = (double)location.getPitch();
        final double a = DoubleUtils.a(spartanPlayer, "pitch-rate=old");
        if (DoubleUtils.h(a)) {
            DoubleUtils.a(spartanPlayer, "pitch-rate=last", Math.abs(a - n));
            CooldownUtils.d(spartanPlayer, "heuristics=pitch-rate", 4);
        }
        DoubleUtils.a(spartanPlayer, "pitch-rate=old", n);
    }
    
    public static float a(final SpartanPlayer spartanPlayer) {
        return CooldownUtils.g(spartanPlayer, "heuristics=pitch-rate") ? 0.0f : ((float)DoubleUtils.a(spartanPlayer, "pitch-rate=last"));
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "heuristics=pitch-rate");
    }
}
