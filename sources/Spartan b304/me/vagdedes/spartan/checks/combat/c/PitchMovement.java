package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.checks.combat.b.PitchRate;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class PitchMovement
{
    private static final Enums.HackType a;
    
    public PitchMovement() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || !Checks.getBoolean("KillAura.check_pitch_movement") || n < 1.5 || !PlayerData.au(spartanPlayer)) {
            return;
        }
        final double h = CombatUtils.h(spartanPlayer, entity);
        final double e = CombatUtils.e(spartanPlayer, entity);
        final float a = PitchRate.a(spartanPlayer);
        boolean b = false;
        if (h >= 0.999 && e <= 6.0 && CombatHeuristics.k(spartanPlayer)) {
            new HackPrevention(spartanPlayer, PitchMovement.a, "t: pitch movement(normal), a: " + h + ", r: " + e + ", p: " + a);
            b = true;
        }
        else if (h >= 0.999 && e <= 6.0 && a >= 30.0f) {
            new HackPrevention(spartanPlayer, PitchMovement.a, "t: pitch movement(instant), a: " + h + ", r: " + e + ", p: " + a);
            b = true;
        }
        if (b) {
            KillAuraOverall.o(spartanPlayer);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
