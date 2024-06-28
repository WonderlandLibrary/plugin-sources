package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.c.SmashHit;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class RapidHits
{
    private static final Enums.HackType a;
    
    public RapidHits() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n) {
        if (CombatUtils.a(entity, n) || mcMMO.K(spartanPlayer) || !Checks.getBoolean("KillAura.check_rapid_hits") || NoHitDelay.E(spartanPlayer) || SmashHit.isEnabled() || Register.v1_9 || LatencyUtils.e(spartanPlayer, 200)) {
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, RapidHits.a.toString() + "=rapid_hits=cooldown")) {
            if (AttemptUtils.b(spartanPlayer, RapidHits.a.toString() + "=rapid_hits", 100) >= 6) {
                KillAuraOverall.o(spartanPlayer);
                new HackPrevention(spartanPlayer, RapidHits.a, "t: rapid hits");
            }
        }
        else {
            CooldownUtils.d(spartanPlayer, RapidHits.a.toString() + "=rapid_hits=cooldown", 2);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
