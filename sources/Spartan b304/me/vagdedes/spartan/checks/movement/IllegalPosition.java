package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class IllegalPosition
{
    private static final Enums.HackType a;
    
    public IllegalPosition() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (VL.b(spartanPlayer, IllegalPosition.a, true) || Teleport.E(spartanPlayer)) {
            return;
        }
        final float pitch = spartanPlayer.a().getPitch();
        AttemptUtils.b(spartanPlayer, IllegalPosition.a.toString() + "=rotate", 5);
        final int a = AttemptUtils.a(spartanPlayer, IllegalPosition.a.toString() + "=rotate");
        final int a2 = LagManagement.a(spartanPlayer, 100);
        if (a >= a2) {
            EventsHandler1.a(spartanPlayer, IllegalPosition.a, spartanLocation, "t: rotations, r: " + a + ", l: " + a2, true, true);
        }
        else if (Math.abs(pitch) >= 90.1f && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0)) {
            EventsHandler1.a(spartanPlayer, IllegalPosition.a, "t: rotations, r: " + a + ", l: " + a2);
        }
    }
    
    static {
        a = Enums.HackType.IllegalPosition;
    }
}
