package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class AimAccuracy
{
    private static final int limit = 10;
    private static final Enums.HackType a;
    
    public AimAccuracy() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_aim_accuracy") || n < 1.0 || !(entity instanceof Player) || VL.a(SpartanBukkit.a(entity.getUniqueId()), Enums.HackType.Velocity) > 0 || CombatUtils.c(spartanPlayer, entity)) {
            return;
        }
        final long a = TimeBetweenClicks.a(spartanPlayer, false);
        if (a > 0L && a <= 400L) {
            final double abs = Math.abs(CombatUtils.k(spartanPlayer, entity));
            int a2 = AttemptUtils.a(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=hit");
            int a3 = AttemptUtils.a(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=miss");
            if (abs <= 3.0) {
                ++a2;
                AttemptUtils.c(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=hit", a2);
            }
            else {
                ++a3;
                AttemptUtils.c(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=miss", a3);
            }
            final int n3 = a2 + a3;
            if (n3 >= 10) {
                AttemptUtils.m(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=hit");
                AttemptUtils.m(spartanPlayer, AimAccuracy.a.toString() + "aim_accuracy=miss");
                final double d = Double.valueOf(a2) / Double.valueOf(n3) * 100.0;
                if (d >= 90.0) {
                    KillAuraOverall.o(spartanPlayer);
                    new HackPrevention(spartanPlayer, AimAccuracy.a, "t: aim-accuracy, p: " + d);
                }
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
