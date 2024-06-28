package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.checks.combat.b.YawRate;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class YawMovement
{
    private static final Enums.HackType a;
    
    public YawMovement() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (!Checks.getBoolean("KillAura.check_yaw_movement") || n2 > 5 || n < 1.0 || !PlayerData.au(spartanPlayer) || PlayerData.aw(spartanPlayer)) {
            return;
        }
        final double h = CombatUtils.h(spartanPlayer, entity);
        final double e = CombatUtils.e(spartanPlayer, entity);
        final float a = YawRate.a(spartanPlayer);
        boolean b = false;
        if (h >= 0.999 && e <= 3.0 && a >= 75.0f) {
            new HackPrevention(spartanPlayer, YawMovement.a, "t: yaw movement(normal), a: " + h + ", r: " + e + ", y: " + a);
            b = true;
        }
        else if (CombatHeuristics.j(spartanPlayer) && a >= 75.0f && CombatHeuristics.a(spartanPlayer, 6.0) <= 3 && AttemptUtils.b(spartanPlayer, YawMovement.a.toString() + "=yaw_movement=constant", 25) >= 2) {
            new HackPrevention(spartanPlayer, YawMovement.a, "t: yaw movement(constant), a: " + h + ", r: " + e + ", y: " + a);
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
