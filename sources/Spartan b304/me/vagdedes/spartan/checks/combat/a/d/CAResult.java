package me.vagdedes.spartan.checks.combat.a.d;

import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CAResult
{
    private int e;
    private double e;
    private static final String name = "combat-analysis=result";
    
    public CAResult(final int e, final double e2) {
        super();
        this.e = e;
        this.e = e2;
    }
    
    public int getScore() {
        return this.e;
    }
    
    public double c() {
        return this.e;
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final String str) {
        final String string = "combat-analysis=result=" + str;
        if (!CooldownUtils.g(spartanPlayer, string) && !CAEventListeners.g(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, string, CooldownUtils.a(spartanPlayer, string) + 1);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final int n, final boolean b, final String str) {
        final String string = "combat-analysis=result=" + str;
        if (b) {
            AttemptUtils.c(spartanPlayer, string + "=finite", AttemptUtils.a(spartanPlayer, string + "=finite") + 1);
        }
        AttemptUtils.c(spartanPlayer, string + "=infinite", AttemptUtils.a(spartanPlayer, string + "=infinite") + 1);
        if (CooldownUtils.g(spartanPlayer, string)) {
            CooldownUtils.d(spartanPlayer, string, n);
        }
    }
    
    public static CAResult a(final SpartanPlayer spartanPlayer, final String str, final int n) {
        final String string = "combat-analysis=result=" + str;
        if (CooldownUtils.f(spartanPlayer, string)) {
            final int a = AttemptUtils.a(spartanPlayer, string + "=infinite");
            final int a2 = AttemptUtils.a(spartanPlayer, string + "=finite");
            if (CooldownUtils.g(spartanPlayer, string)) {
                CooldownUtils.j(spartanPlayer, string);
                AttemptUtils.m(spartanPlayer, string + "=infinite");
                AttemptUtils.m(spartanPlayer, string + "=finite");
                if (a >= n) {
                    return new CAResult(a, MathUtils.percentage(a2, a));
                }
            }
        }
        return null;
    }
    
    public static int b(final SpartanPlayer spartanPlayer) {
        final String s = "combat-analysis=result=";
        return Math.max(AttemptUtils.a(spartanPlayer, s + "default" + "=infinite"), AttemptUtils.a(spartanPlayer, s + "average" + "=infinite"));
    }
    
    public static double b(final SpartanPlayer spartanPlayer) {
        final String s = "combat-analysis=result=";
        return (MathUtils.percentage(AttemptUtils.a(spartanPlayer, s + "default" + "=finite"), AttemptUtils.a(spartanPlayer, s + "default" + "=infinite")) + MathUtils.percentage(AttemptUtils.a(spartanPlayer, s + "average" + "=finite"), AttemptUtils.a(spartanPlayer, s + "average" + "=infinite"))) / 2.0;
    }
    
    public static double c(final SpartanPlayer spartanPlayer) {
        final double n = 30.0;
        final double n2 = n / (n - d(spartanPlayer) / 20);
        return (n2 < 1.0) ? 1.0 : n2;
    }
    
    public static int d(final SpartanPlayer spartanPlayer) {
        final String s = "combat-analysis=result=";
        return (CooldownUtils.a(spartanPlayer, s + "default") + CooldownUtils.a(spartanPlayer, s + "average")) / 2;
    }
}
