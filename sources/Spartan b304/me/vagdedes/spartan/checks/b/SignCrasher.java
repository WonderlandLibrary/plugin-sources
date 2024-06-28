package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class SignCrasher
{
    private static final Enums.HackType a;
    
    public SignCrasher() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final String[] array) {
        for (int i = 0; i <= 3; ++i) {
            final int length = array[i].length();
            if (length >= 384 && !VL.b(spartanPlayer, SignCrasher.a, false)) {
                new HackPrevention(spartanPlayer, SignCrasher.a, "t: illegal sign character length, l: " + length);
            }
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
    }
}
