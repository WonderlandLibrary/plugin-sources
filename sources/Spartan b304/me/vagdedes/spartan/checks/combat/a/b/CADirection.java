package me.vagdedes.spartan.checks.combat.a.b;

import me.vagdedes.spartan.checks.combat.a.a.CAExecution;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;

public class CADirection
{
    private static final CAEventListeners.a a;
    
    public CADirection() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2, final CAEventListeners.b b) {
        final String string = "combat-analysis=" + CADirection.a.toString();
        double e;
        final double n = e = CombatUtils.e(spartanPlayer, spartanPlayer2);
        final double a = DoubleUtils.a(spartanPlayer, string);
        if (DoubleUtils.h(a)) {
            e += a;
            if (CAExecution.a(spartanPlayer, b, CADirection.a, n, e, 3)) {
                DoubleUtils.j(spartanPlayer, string);
                return;
            }
        }
        DoubleUtils.a(spartanPlayer, string, e);
    }
    
    static {
        a = CAEventListeners.a.c;
    }
}
