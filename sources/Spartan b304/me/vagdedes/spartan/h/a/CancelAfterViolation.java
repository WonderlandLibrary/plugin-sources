package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CancelAfterViolation
{
    public CancelAfterViolation() {
        super();
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "cancel-after-violation=protection");
    }
    
    public static void F(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "cancel-after-violation=protection", 2);
    }
}
