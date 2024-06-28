package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Velocity
{
    public Velocity() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final boolean b) {
        if (b) {
            MoveUtils.L(spartanPlayer);
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, "velocity=protection=disallow")) {
            return;
        }
        g(spartanPlayer, 80);
    }
    
    public static void q(final SpartanPlayer spartanPlayer) {
        i(spartanPlayer, 2);
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "velocity=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "velocity=protection", n);
    }
    
    public static void i(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "velocity=protection=disallow", n);
    }
}
