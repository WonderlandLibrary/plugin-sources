package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class HitsPerSec
{
    private static final Enums.HackType a;
    
    public HitsPerSec() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (!Checks.getBoolean("KillAura.check_hits_per_second") || NoHitDelay.E(spartanPlayer)) {
            return;
        }
        final int b = AttemptUtils.b(spartanPlayer, HitsPerSec.a.toString() + "=max_hits_per_second", 20);
        if (b > 20) {
            KillAuraOverall.o(spartanPlayer);
            new HackPrevention(spartanPlayer, HitsPerSec.a, "t: hits-per-second, h: " + b + ", c: " + CpsCounter.o(spartanPlayer));
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
