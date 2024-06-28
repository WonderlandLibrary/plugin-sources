package me.vagdedes.spartan.checks.combat;

import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastBow
{
    private static final Enums.HackType a;
    
    public FastBow() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final float n) {
        if (VL.b(spartanPlayer, FastBow.a, true) || spartanPlayer.getItemInHand().getType() != Material.BOW) {
            return;
        }
        e(spartanPlayer);
        b(spartanPlayer, n);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if (!VL.b(spartanPlayer, FastBow.a, true) && action == Action.RIGHT_CLICK_AIR && spartanPlayer.getItemInHand().getType() == Material.BOW && spartanPlayer.a().contains(Material.ARROW)) {
            MillisUtils.o(spartanPlayer, FastBow.a.toString() + "=interact");
        }
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final float n) {
        final long a = MillisUtils.a(spartanPlayer, FastBow.a.toString() + "=interact");
        if (!Checks.getBoolean("FastBow.check_bow_force") || n != 1.0f || a > 500L) {
            return;
        }
        if (AttemptUtils.b(spartanPlayer, FastBow.a.toString() + "=bow-force", 20) >= 2) {
            new HackPrevention(spartanPlayer, FastBow.a, "t: bow force, ms: " + a);
        }
    }
    
    private static void e(final SpartanPlayer spartanPlayer) {
        if (!Checks.getBoolean("FastBow.check_bow_shots")) {
            return;
        }
        String str = null;
        AttemptUtils.b(spartanPlayer, FastBow.a.toString() + "=fast", 10);
        AttemptUtils.b(spartanPlayer, FastBow.a.toString() + "=medium", 20);
        AttemptUtils.b(spartanPlayer, FastBow.a.toString() + "=slow", 30);
        if (AttemptUtils.a(spartanPlayer, FastBow.a.toString() + "=fast") == LagManagement.a(spartanPlayer, 6)) {
            AttemptUtils.m(spartanPlayer, FastBow.a.toString() + "=fast");
            str = "fast";
        }
        else if (AttemptUtils.a(spartanPlayer, FastBow.a.toString() + "=medium") == LagManagement.a(spartanPlayer, 7)) {
            AttemptUtils.m(spartanPlayer, FastBow.a.toString() + "=medium");
            str = "medium";
        }
        else if (AttemptUtils.a(spartanPlayer, FastBow.a.toString() + "=slow") == LagManagement.a(spartanPlayer, 9)) {
            AttemptUtils.m(spartanPlayer, FastBow.a.toString() + "=slow");
            str = "slow";
        }
        if (str != null) {
            new HackPrevention(spartanPlayer, FastBow.a, "t: bow shots, r: " + str);
        }
    }
    
    static {
        a = Enums.HackType.FastBow;
    }
}
