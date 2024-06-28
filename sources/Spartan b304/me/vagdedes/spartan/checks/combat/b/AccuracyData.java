package me.vagdedes.spartan.checks.combat.b;

import java.util.Iterator;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class AccuracyData
{
    private static final int limit = 20;
    
    public AccuracyData() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, "accuracy=percentage");
        AttemptUtils.m(spartanPlayer, "accuracy=hits");
        AttemptUtils.m(spartanPlayer, "accuracy=miss");
        AttemptUtils.m(spartanPlayer, "accuracy=count");
    }
    
    public static double d(final SpartanPlayer spartanPlayer) {
        final double a = DoubleUtils.a(spartanPlayer, "accuracy=percentage");
        return DoubleUtils.h(a) ? a : 0.0;
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer) {
        return DoubleUtils.h(DoubleUtils.a(spartanPlayer, "accuracy=percentage"));
    }
    
    public static int e(final SpartanPlayer spartanPlayer) {
        return a(spartanPlayer, "hits");
    }
    
    public static int f(final SpartanPlayer spartanPlayer) {
        return a(spartanPlayer, "mis");
    }
    
    private static void d(final SpartanPlayer spartanPlayer, final String s) {
        AttemptUtils.c(spartanPlayer, "accuracy=" + s, AttemptUtils.a(spartanPlayer, "accuracy=" + s) + 1);
        final int n = AttemptUtils.a(spartanPlayer, "accuracy=count") + 1;
        AttemptUtils.c(spartanPlayer, "accuracy=count", n);
        if (n >= 20) {
            final int a = a(spartanPlayer, "hits");
            DoubleUtils.a(spartanPlayer, "accuracy=percentage", MathUtils.percentage((double)a, (double)(a + a(spartanPlayer, "miss"))));
            AttemptUtils.m(spartanPlayer, "accuracy=hits");
            AttemptUtils.m(spartanPlayer, "accuracy=miss");
            AttemptUtils.m(spartanPlayer, "accuracy=count");
        }
    }
    
    private static int a(final SpartanPlayer spartanPlayer, final String str) {
        return AttemptUtils.a(spartanPlayer, "accuracy=" + str);
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (CombatUtils.ah(spartanPlayer)) {
                return;
            }
            boolean b = true;
            for (int i = 0; i <= 2; ++i) {
                if (!BlockUtils.c(spartanPlayer, true, 1.0, i, 1.0) || !BlockUtils.c(a, true, 1.0, i, 1.0)) {
                    b = false;
                    break;
                }
            }
            if (b && MoveUtils.a(spartanPlayer.a(), a.a()) >= 3.0) {
                d(spartanPlayer, "hits");
            }
            MillisUtils.o(spartanPlayer, "accuracy");
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        final long a = MillisUtils.a(spartanPlayer, "accuracy");
        if (action != Action.LEFT_CLICK_AIR || CombatUtils.ah(spartanPlayer) || g(spartanPlayer) == 0 || !MillisUtils.hasTimer(a) || a <= 500L) {
            return;
        }
        d(spartanPlayer, "miss");
    }
    
    public static int g(final SpartanPlayer spartanPlayer) {
        int n = 0;
        final Iterator<Entity> iterator = spartanPlayer.getNearbyEntities(6.0, 6.0, 6.0).iterator();
        while (iterator.hasNext()) {
            if (((Entity)iterator.next()) instanceof Player) {
                ++n;
            }
        }
        return n;
    }
}
