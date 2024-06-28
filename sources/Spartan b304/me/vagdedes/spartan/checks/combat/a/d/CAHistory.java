package me.vagdedes.spartan.checks.combat.a.d;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CAHistory
{
    private static final String name = "combat-analysis=history";
    
    public CAHistory() {
        super();
    }
    
    public static double a(final SpartanPlayer spartanPlayer) {
        final double a = DoubleUtils.a(spartanPlayer, "combat-analysis=history=percentage");
        return DoubleUtils.h(a) ? a : (CAResult.b(spartanPlayer) * CAResult.c(spartanPlayer));
    }
    
    public static int b(final SpartanPlayer spartanPlayer) {
        final String s = "combat-analysis=history=score";
        if (AttemptUtils.f(spartanPlayer, s)) {
            return AttemptUtils.a(spartanPlayer, s);
        }
        final int b = CAResult.b(spartanPlayer);
        return (b >= CombatAnalysis.c) ? (b * (int)CAResult.c(spartanPlayer)) : -1;
    }
    
    public static int c(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "combat-analysis=history=failure");
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        CooldownUtils.d(spartanPlayer, "combat-analysis=history=check=" + spartanPlayer2.getUniqueId(), 600);
        CooldownUtils.d(spartanPlayer2, "combat-analysis=history=check=" + spartanPlayer.getUniqueId(), 600);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final double n) {
        DoubleUtils.a(spartanPlayer, "combat-analysis=history=percentage", n);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final int n) {
        AttemptUtils.c(spartanPlayer, "combat-analysis=history=score", n);
    }
    
    public static void k(final SpartanPlayer spartanPlayer) {
        AttemptUtils.c(spartanPlayer, "combat-analysis=history=failure", AttemptUtils.a(spartanPlayer, "combat-analysis=history=failure") + 1);
    }
    
    public static void l(final SpartanPlayer spartanPlayer) {
        AttemptUtils.m(spartanPlayer, "combat-analysis=history=failure");
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer) {
        return h(spartanPlayer) || (a(spartanPlayer) >= 0.0 && b(spartanPlayer) >= 0 && CAResult.d(spartanPlayer) <= 100);
    }
    
    public static boolean h(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.f(spartanPlayer, "combat-analysis=history=score") && DoubleUtils.h(DoubleUtils.a(spartanPlayer, "combat-analysis=history=percentage"));
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        return CooldownUtils.g(spartanPlayer, "combat-analysis=history=check=" + spartanPlayer2.getUniqueId()) && CooldownUtils.g(spartanPlayer2, "combat-analysis=history=check=" + spartanPlayer.getUniqueId());
    }
}
