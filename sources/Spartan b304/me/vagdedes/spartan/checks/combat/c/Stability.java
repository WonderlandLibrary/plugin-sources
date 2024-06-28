package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Stability
{
    private static final Enums.HackType a;
    
    public Stability() {
        super();
    }
    
    public static void n(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, Stability.a.toString() + "=stability=yaw");
        DoubleUtils.j(spartanPlayer, Stability.a.toString() + "=stability=pitch");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_stability") || n < 1.0) {
            return;
        }
        final float pitch = spartanPlayer.a().getPitch();
        final float yaw = spartanPlayer.a().getYaw();
        final long a = MillisUtils.a(spartanPlayer, Stability.a.toString() + "=stability=delay");
        if (MillisUtils.hasTimer(a)) {
            final double abs = Math.abs((double)pitch - DoubleUtils.a(spartanPlayer, "stability=pitch"));
            final double abs2 = Math.abs((double)yaw - DoubleUtils.a(spartanPlayer, "stability=yaw"));
            final double k = MoveUtils.k(spartanPlayer);
            if (a <= 655L && abs2 >= 10.0 && k >= 0.18 && abs == 0.0) {
                final int b = AttemptUtils.b(spartanPlayer, Stability.a.toString() + "=stability=attempts", 99);
                if (b >= 2) {
                    KillAuraOverall.o(spartanPlayer);
                    if (b >= 4) {
                        new HackPrevention(spartanPlayer, Stability.a, "t: stability, yaw: " + abs2 + ", pitch: " + abs + ", speed: " + k + ", ms: " + a);
                    }
                }
            }
            MillisUtils.o(spartanPlayer, Stability.a.toString() + "=stability=delay");
            DoubleUtils.a(spartanPlayer, "stability=pitch", pitch);
            DoubleUtils.a(spartanPlayer, "stability=yaw", yaw);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
