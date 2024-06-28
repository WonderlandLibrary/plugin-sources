package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class FishingHook
{
    public FishingHook() {
        super();
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final SpartanPlayer obj) {
        if (!spartanPlayer.equals(obj)) {
            Damage.g(spartanPlayer, 20);
            g(spartanPlayer, 40);
        }
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "hook=protection=has", n);
        CooldownUtils.d(spartanPlayer, "hook=protection=had", n * 2);
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "hook=protection=has");
    }
    
    public static boolean Y(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "hook=protection=had");
    }
}
