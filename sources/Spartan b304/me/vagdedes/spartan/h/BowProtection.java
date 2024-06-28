package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class BowProtection
{
    public BowProtection() {
        super();
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "bow=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "bow=protection", n);
    }
}
