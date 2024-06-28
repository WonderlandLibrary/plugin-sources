package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Rotations
{
    private static final Enums.HackType a;
    
    public Rotations() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_rotations") || n < 1.0) {
            return;
        }
        final double f = CombatUtils.f(spartanPlayer, entity);
        final double h = CombatUtils.h(spartanPlayer, entity);
        final double e = CombatUtils.e(spartanPlayer, entity);
        if (CooldownUtils.g(spartanPlayer, Rotations.a.toString() + "=rotations=hit")) {
            if (f >= 1.25) {
                CooldownUtils.d(spartanPlayer, Rotations.a.toString() + "=rotations=hit", 14);
            }
        }
        else if (f < 0.1 && h >= 0.999 && e <= 3.0) {
            KillAuraOverall.o(spartanPlayer);
            new HackPrevention(spartanPlayer, Rotations.a, "t: rotations, d: " + f + ", r: " + e + ", a: " + h);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
