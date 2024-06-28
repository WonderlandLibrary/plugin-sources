package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.system.VL;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ImpossibleHits
{
    private static final Enums.HackType a;
    private static final boolean c;
    
    public ImpossibleHits() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (VL.b(spartanPlayer, ImpossibleHits.a, true) || !Checks.getBoolean("KillAura.check_impossible_hits") || LatencyUtils.e(spartanPlayer, 200)) {
            return;
        }
        boolean b = false;
        if (PlayerData.ba(spartanPlayer)) {
            if (AttemptUtils.b(spartanPlayer, ImpossibleHits.a.toString() + "=impossible-hits=dead", 100) >= 3) {
                new HackPrevention(spartanPlayer, ImpossibleHits.a, "t: impossible hits, r: death");
            }
            b = true;
        }
        else if (spartanPlayer.isSleeping()) {
            new HackPrevention(spartanPlayer, ImpossibleHits.a, "t: impossible hits, r: sleeping");
            b = true;
        }
        else if (PlayerData.aI(spartanPlayer)) {
            new HackPrevention(spartanPlayer, ImpossibleHits.a, "t: impossible hits, r: cursor usage");
            b = true;
        }
        else if (PlayerData.d(spartanPlayer, 8)) {
            new HackPrevention(spartanPlayer, ImpossibleHits.a, "t: impossible hits, r: inventory usage");
            b = true;
        }
        else if (Checks.getBoolean("KillAura.check_item_usage") && ImpossibleHits.c && !CombatUtils.c(spartanPlayer, entity) && spartanPlayer.isBlocking()) {
            new HackPrevention(spartanPlayer, ImpossibleHits.a, "t: impossible hits, r: item usage");
            b = true;
        }
        if (b) {
            KillAuraOverall.o(spartanPlayer);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
        c = (VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.c);
    }
}
