package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Knockback
{
    public Knockback() {
        super();
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "knockback=protection", n);
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "knockback=protection");
    }
}
