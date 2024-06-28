package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.ArrayList;
import me.vagdedes.spartan.system.Enums;

public class KillAuraOverall
{
    private static final Enums.HackType a;
    private static final int max = 1200;
    private static int time;
    
    public KillAuraOverall() {
        super();
    }
    
    public static void a(final ArrayList<SpartanPlayer> list) {
        if (Checks.getBoolean("KillAura.check_overall")) {
            if (KillAuraOverall.time == 0) {
                KillAuraOverall.time = 1200;
                for (final SpartanPlayer spartanPlayer : list) {
                    final int a = AttemptUtils.a(spartanPlayer, KillAuraOverall.a.toString() + "=overall=violations");
                    AttemptUtils.m(spartanPlayer, KillAuraOverall.a.toString() + "=overall=violations");
                    if (a >= 25) {
                        new HackPrevention(spartanPlayer, KillAuraOverall.a, "t: overall, vls: " + a, true);
                    }
                }
            }
            else {
                --KillAuraOverall.time;
            }
        }
        else if (KillAuraOverall.time != 1200) {
            KillAuraOverall.time = 1200;
        }
    }
    
    public static void o(final SpartanPlayer spartanPlayer) {
        AttemptUtils.c(spartanPlayer, KillAuraOverall.a.toString() + "=overall=violations", AttemptUtils.a(spartanPlayer, KillAuraOverall.a.toString() + "=overall=violations") + 1);
    }
    
    public static int j(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, KillAuraOverall.a.toString() + "=overall=cached");
    }
    
    static {
        a = Enums.HackType.KillAura;
        KillAuraOverall.time = 1200;
    }
}
