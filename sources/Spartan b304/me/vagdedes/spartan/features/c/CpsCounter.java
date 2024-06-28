package me.vagdedes.spartan.features.c;

import me.vagdedes.spartan.checks.combat.FastClicks;
import me.vagdedes.spartan.j.UltimateStatistics;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.b.ReflectionUtils;

public class CpsCounter
{
    private static final int a = 80;
    private static final String f = "cps-counter";
    private static boolean h;
    
    public CpsCounter() {
        super();
    }
    
    public static void refresh() {
        CpsCounter.h = ReflectionUtils.e("me.vagdedes.ultimatestatistics.handlers.CpsCounter");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if (action == Action.LEFT_CLICK_AIR && CooldownUtils.g(spartanPlayer, "cps-counter=cooldown")) {
            AttemptUtils.b(spartanPlayer, "cps-counter", 80);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (AttemptUtils.b(spartanPlayer, "cps-counter") == 1) {
            AttemptUtils.c(spartanPlayer, "cps-counter=result", AttemptUtils.a(spartanPlayer, "cps-counter"));
            CooldownUtils.d(spartanPlayer, "cps-counter=cooldown", 80);
        }
    }
    
    public static int o(final SpartanPlayer spartanPlayer) {
        return Math.max((UltimateStatistics.isEnabled() && CpsCounter.h) ? me.vagdedes.ultimatestatistics.handlers.CpsCounter.get(spartanPlayer.getPlayer()) : 0, Math.max(FastClicks.a(spartanPlayer), AttemptUtils.a(spartanPlayer, "cps-counter=result") / 4));
    }
    
    static {
        CpsCounter.h = false;
    }
}
