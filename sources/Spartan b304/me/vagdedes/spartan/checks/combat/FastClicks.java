package me.vagdedes.spartan.checks.combat;

import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.checks.f.Nuker;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.h.BlockBreak;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.system.VL;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastClicks
{
    private static final Enums.HackType a;
    private static final int a = 80;
    
    public FastClicks() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Action action) {
        if (VL.b(spartanPlayer, FastClicks.a, true) || spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) || BlockBreak.E(spartanPlayer) || BlockBreak.Q(spartanPlayer) || action != Action.LEFT_CLICK_AIR || spartanPlayer.getVehicle() != null || (Checks.getBoolean("FastClicks.combat_only_mode") && !PlayerData.au(spartanPlayer) && !PlayerData.av(spartanPlayer)) || Nuker.a(spartanPlayer.getItemInHand().getType())) {
            CooldownUtils.d(spartanPlayer, FastClicks.a.toString() + "=cancelled", 10);
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, FastClicks.a.toString() + "=cancelled")) {
            return;
        }
        f(spartanPlayer);
        g(spartanPlayer);
        h(spartanPlayer);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String s) {
        if (AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=cancel", 320) >= 2) {
            new HackPrevention(spartanPlayer, FastClicks.a, s);
        }
    }
    
    private static long a(final SpartanPlayer spartanPlayer, final int n) {
        final long a = MillisUtils.a(spartanPlayer, FastClicks.a.toString() + "=time_between_clicks=" + n);
        MillisUtils.o(spartanPlayer, FastClicks.a.toString() + "=time_between_clicks=" + n);
        return MillisUtils.hasTimer(a) ? a : 0L;
    }
    
    public static int a(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, FastClicks.a.toString() + "=cps=clicks") / 4;
    }
    
    private static void f(final SpartanPlayer spartanPlayer) {
        if (!Checks.getBoolean("FastClicks.check_cps")) {
            return;
        }
        final long a = a(spartanPlayer, 1);
        final int integer = Checks.getInteger("FastClicks.cps_limit");
        final int b = AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=cps=clicks", 80);
        final int o = CpsCounter.o(spartanPlayer);
        if (b == 1) {
            AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=cps=time");
        }
        if (a <= 100L) {
            AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=cps=time", 80);
        }
        final int a2 = AttemptUtils.a(spartanPlayer, FastClicks.a.toString() + "=cps=time");
        if (o >= LagManagement.a(spartanPlayer, integer) && a2 >= 30) {
            AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=cps=clicks");
            AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=cps=time");
            a(spartanPlayer, "t: cps, l: " + integer + ", tm: " + a2);
        }
    }
    
    private static void g(final SpartanPlayer spartanPlayer) {
        if (!Checks.getBoolean("FastClicks.check_click_time") || CpsCounter.o(spartanPlayer) <= 8) {
            return;
        }
        final long a = a(spartanPlayer, 2);
        final int o = CpsCounter.o(spartanPlayer);
        if (a <= 50L) {
            final int b = AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=click-time=50", 80);
            final int a2 = AttemptUtils.a(spartanPlayer, FastClicks.a.toString() + "=click-time=50=division");
            int n;
            if (b == 1) {
                n = (int)a;
            }
            else {
                n = a2 + (int)a;
            }
            AttemptUtils.c(spartanPlayer, FastClicks.a.toString() + "=click-time=50=division", n);
            if (b == 30 && o > 6) {
                AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=click-time=50");
                AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=click-time=50=division");
                a(spartanPlayer, "t: click-time, ms: 50, o: " + n / b + ", cps: " + o);
                return;
            }
        }
        if (a <= 100L) {
            final int b2 = AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=click-time=100", 80);
            final int a3 = AttemptUtils.a(spartanPlayer, FastClicks.a.toString() + "=click-time=100=division");
            int n2;
            if (b2 == 1) {
                n2 = (int)a;
            }
            else {
                n2 = a3 + (int)a;
            }
            AttemptUtils.c(spartanPlayer, FastClicks.a.toString() + "=click-time=100=division", n2);
            if (b2 == 50 && o > 6) {
                AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=click-time=100");
                AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=click-time=100=division");
                a(spartanPlayer, "t: click-time, ms: 100, o: " + n2 / b2 + ", cps: " + o);
            }
        }
    }
    
    private static void h(final SpartanPlayer spartanPlayer) {
        if (!Checks.getBoolean("FastClicks.check_click_consistency") || PlayerData.c(spartanPlayer, 6.0) > 5 || CpsCounter.o(spartanPlayer) <= 8) {
            return;
        }
        final long a = a(spartanPlayer, 3);
        final double a2 = DoubleUtils.a(spartanPlayer, FastClicks.a.toString() + "=click-consistency=previous");
        if (DoubleUtils.h(a2)) {
            final long lng = (long)a2;
            if (a <= 75L && lng <= 75L) {
                final long abs = Math.abs(a - lng);
                if (abs != 0L && abs != 50L && AttemptUtils.b(spartanPlayer, FastClicks.a.toString() + "=click-consistency=attempts", 80) >= 10) {
                    AttemptUtils.m(spartanPlayer, FastClicks.a.toString() + "=click-consistency=attempts");
                    a(spartanPlayer, "t: click-consistency, c: " + a + ", p: " + lng + ", m: " + abs);
                }
            }
        }
        DoubleUtils.a(spartanPlayer, FastClicks.a.toString() + "=click-consistency=previous", (double)a);
    }
    
    static {
        a = Enums.HackType.FastClicks;
    }
}
