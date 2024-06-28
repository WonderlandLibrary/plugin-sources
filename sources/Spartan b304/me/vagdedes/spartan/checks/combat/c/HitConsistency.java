package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class HitConsistency
{
    private static final Enums.HackType a;
    
    public HitConsistency() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        final long a = MillisUtils.a(spartanPlayer, HitConsistency.a.toString() + "=hit-consistency");
        if (a >= 450L && a <= 500L) {
            final long b = TimeBetweenClicks.b(spartanPlayer);
            final long abs = Math.abs(a - b);
            if (abs <= 3L && AttemptUtils.b(spartanPlayer, HitConsistency.a.toString() + "=hit-consistency", 100) >= 3) {
                KillAuraOverall.o(spartanPlayer);
                new HackPrevention(spartanPlayer, HitConsistency.a, "t: hit-consistency, ms: " + a + ", cac: " + b + ", diff: " + abs);
            }
        }
        MillisUtils.o(spartanPlayer, HitConsistency.a.toString() + "=hit-consistency");
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
