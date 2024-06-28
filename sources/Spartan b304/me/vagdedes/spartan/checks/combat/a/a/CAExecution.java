package me.vagdedes.spartan.checks.combat.a.a;

import java.util.Iterator;
import java.util.ArrayList;
import me.vagdedes.spartan.api.CombatAnalysisEvent;
import org.bukkit.event.Event;
import me.vagdedes.spartan.checks.combat.a.c.CAResultEvent;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CAExecution
{
    private static final String name = "combat-analysis=execution";
    
    public CAExecution() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final CAEventListeners.b b, final CAEventListeners.a a, final double n, double n2, final int n3) {
        final String string = "combat-analysis=execution=" + b.toString() + "=" + a.toString();
        final boolean b2 = b == CAEventListeners.b.b;
        final boolean d = CATraining.d(spartanPlayer);
        if (!b2) {
            AttemptUtils.c(spartanPlayer, string, AttemptUtils.a(spartanPlayer, string) + 1);
        }
        if (!d) {
            a(spartanPlayer, new CAQueue(b, a, n));
        }
        final int a2 = AttemptUtils.a(spartanPlayer, string);
        if (a2 >= n3 || b2) {
            AttemptUtils.m(spartanPlayer, string);
            n2 = ((a2 > 1) ? (n2 / a2) : n2);
            if (d || CATraining.e(spartanPlayer)) {
                CAConfig.a(b, a, n2, true);
            }
            return true;
        }
        return false;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final CAQueue caQueue) {
        if (!CombatAnalysis.c(spartanPlayer)) {
            final CAEventListeners.a a = caQueue.a();
            final CAEventListeners.b a2 = caQueue.a();
            final ArrayList<Double> a3 = CAConfig.a(a2, a);
            final double a4 = CAConfig.a(a2, a);
            final double value = caQueue.getValue();
            double n = 0.0;
            double n2 = 0.0;
            final int size = a3.size();
            final Iterator<Double> iterator = a3.iterator();
            while (iterator.hasNext()) {
                final double doubleValue = (double)Double.valueOf(iterator.next());
                if ((doubleValue + value) / 2.0 <= a4) {
                    ++n;
                }
                else {
                    n += 0.25;
                }
                if (value <= doubleValue) {
                    ++n2;
                }
                else {
                    n2 += 0.5;
                }
            }
            final double percentage = MathUtils.percentage(n, size);
            final double percentage2 = MathUtils.percentage(n2, size);
            Bukkit.getPluginManager().callEvent((Event)new CAResultEvent(Bukkit.getPlayer(spartanPlayer.getUniqueId()), a, a2, percentage, percentage2));
            Bukkit.getPluginManager().callEvent((Event)new CombatAnalysisEvent(Bukkit.getPlayer(spartanPlayer.getUniqueId()), a2, percentage, percentage2));
        }
    }
}
