package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.checks.combat.b.AccuracyData;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.c.NoHitDelay;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Accuracy
{
    private static final Enums.HackType a;
    
    public Accuracy() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof Player && !NoHitDelay.E(spartanPlayer)) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (!CombatHeuristics.a(spartanPlayer, Accuracy.a, entity) || !Checks.getBoolean("KillAura.check_accuracy") || !PlayerData.c(spartanPlayer, a) || VL.a(a, Enums.HackType.Velocity) > 0) {
                return;
            }
            final long a2 = TimeBetweenClicks.a(spartanPlayer, false);
            final long a3 = TimeBetweenClicks.a(a, false);
            final double d = AccuracyData.d(spartanPlayer);
            if (d >= 90.0 && a2 > 0L && a2 <= 550L && a3 > 0L && a3 <= 550L && (PlayerData.au(spartanPlayer) || (PlayerData.au(a) && PlayerData.av(spartanPlayer)))) {
                KillAuraOverall.o(spartanPlayer);
                AccuracyData.a(spartanPlayer);
                new HackPrevention(spartanPlayer, Accuracy.a, "t: accuracy, v: " + d);
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
