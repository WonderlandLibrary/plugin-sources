package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.checks.combat.HitReach;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Precision
{
    private static final Enums.HackType a;
    private static final int g = 8;
    
    public Precision() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_precision") || n < 1.0 || !(entity instanceof Player) || CombatUtils.c(spartanPlayer, entity) || NoHitDelay.E(spartanPlayer)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
        if ((!PlayerData.au(spartanPlayer) && !PlayerData.au(a) && !PlayerData.av(a)) || VL.a(a, Enums.HackType.Velocity) > 0) {
            return;
        }
        final String string = Precision.a.toString() + "=precision=result";
        final double h = CombatUtils.h(spartanPlayer, entity);
        final double n3 = DoubleUtils.a(spartanPlayer, string + "=storage") + ((h >= 0.9999) ? 1 : ((h >= 0.999) ? 2 : ((h >= 0.99) ? 3 : ((h >= 0.9) ? 4 : ((h >= 0.0) ? 5 : 6)))));
        DoubleUtils.a(spartanPlayer, string + "=storage", n3);
        final double n4 = DoubleUtils.a(spartanPlayer, string + "=distance") + n;
        DoubleUtils.a(spartanPlayer, string + "=distance", n4);
        final int n5 = AttemptUtils.a(spartanPlayer, string + "=loops") + 1;
        AttemptUtils.c(spartanPlayer, string + "=loops", n5);
        if (n5 >= 8) {
            AttemptUtils.m(spartanPlayer, string + "=loops");
            DoubleUtils.j(spartanPlayer, string + "=storage");
            DoubleUtils.j(spartanPlayer, string + "=distance");
            final double d = n3 / n5;
            final double d2 = n4 / n5;
            final int i = (d2 <= 1.5 && (d < 3.0 || d > 4.0)) ? 1 : ((d2 <= 2.5 && (d < 2.5 || d > 4.5)) ? 2 : ((d2 <= 3.0 && (d < 3.0 || d > 4.375)) ? 3 : ((d2 > 3.0 && (d < 3.5 || d >= 5.5)) ? 4 : 0)));
            if (i != 0 && d2 <= HitReach.a(spartanPlayer, entity)) {
                KillAuraOverall.o(spartanPlayer);
                if (AttemptUtils.b(spartanPlayer, string + "=attempts", 264) >= 2) {
                    new HackPrevention(spartanPlayer, Precision.a, "t: precision, s: " + d + ", d: " + d2 + ", c: " + i);
                }
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
