package me.vagdedes.spartan.checks.combat.a.d;

import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CACache
{
    private static final String name = "combat-analysis=cache";
    
    public CACache() {
        super();
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final CAEventListeners.a a, final double n, final String str) {
        final String string = "combat-analysis=cache=difference=" + a.toString() + "=" + str;
        final double a2 = DoubleUtils.a(spartanPlayer, string);
        final double n2 = DoubleUtils.h(a2) ? Math.abs(a2 - n) : -1.0;
        DoubleUtils.a(spartanPlayer, string, n);
        return n2;
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final String str, final double n, final String str2) {
        final double a = DoubleUtils.a(spartanPlayer, "combat-analysis=cache=memory=" + str2);
        final double n2 = DoubleUtils.h(a) ? a : -1.0;
        DoubleUtils.a(spartanPlayer, "combat-analysis=cache=memory=" + str, n);
        return n2;
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final String s) {
        DoubleUtils.j(spartanPlayer, "combat-analysis=cache=difference=" + s);
        DoubleUtils.j(spartanPlayer, "combat-analysis=cache=memory=" + s);
    }
}
