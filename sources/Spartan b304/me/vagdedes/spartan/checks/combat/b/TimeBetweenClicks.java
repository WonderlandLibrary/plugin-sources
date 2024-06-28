package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;

public class TimeBetweenClicks
{
    private static final int f = 7;
    private static final HashMap<UUID, Long> t;
    private static final HashMap<UUID, Long> s;
    private static final HashMap<UUID, Long> u;
    
    public TimeBetweenClicks() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        TimeBetweenClicks.s.remove(spartanPlayer.getUniqueId());
        TimeBetweenClicks.t.remove(spartanPlayer.getUniqueId());
        TimeBetweenClicks.u.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        TimeBetweenClicks.s.clear();
        TimeBetweenClicks.t.clear();
        TimeBetweenClicks.u.clear();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Action action) {
        if (action == Action.LEFT_CLICK_AIR) {
            MillisUtils.o(spartanPlayer, "heuristics=time-between-clicks=action");
            final long a = MillisUtils.a(spartanPlayer, "heuristics=time-between-clicks");
            if (MillisUtils.hasTimer(a)) {
                TimeBetweenClicks.u.put(spartanPlayer.getUniqueId(), Long.valueOf(a));
                if (!TimeBetweenClicks.t.containsKey(spartanPlayer.getUniqueId())) {
                    TimeBetweenClicks.t.put(spartanPlayer.getUniqueId(), Long.valueOf(a));
                }
                else {
                    TimeBetweenClicks.t.put(spartanPlayer.getUniqueId(), Long.valueOf((long)Long.valueOf(TimeBetweenClicks.t.get((Object)spartanPlayer.getUniqueId())) + a));
                }
                final int n = AttemptUtils.a(spartanPlayer, "heuristics=time-between-clicks") + 1;
                AttemptUtils.c(spartanPlayer, "heuristics=time-between-clicks", n);
                if (n >= 7) {
                    AttemptUtils.m(spartanPlayer, "heuristics=time-between-clicks");
                    if (TimeBetweenClicks.t.containsKey(spartanPlayer.getUniqueId())) {
                        TimeBetweenClicks.s.put(spartanPlayer.getUniqueId(), Long.valueOf((long)Long.valueOf(TimeBetweenClicks.t.get((Object)spartanPlayer.getUniqueId())) / 7L));
                        TimeBetweenClicks.t.remove(spartanPlayer.getUniqueId());
                    }
                }
            }
            MillisUtils.o(spartanPlayer, "heuristics=time-between-clicks");
        }
    }
    
    public static long a(final SpartanPlayer spartanPlayer) {
        return MillisUtils.a(spartanPlayer, "heuristics=time-between-clicks=action");
    }
    
    public static long a(final SpartanPlayer spartanPlayer, final boolean b) {
        return (!b && Register.v1_9) ? 1L : (TimeBetweenClicks.s.containsKey(spartanPlayer.getUniqueId()) ? ((long)Long.valueOf(TimeBetweenClicks.s.get((Object)spartanPlayer.getUniqueId()))) : 0L);
    }
    
    public static long b(final SpartanPlayer spartanPlayer) {
        return TimeBetweenClicks.u.containsKey(spartanPlayer.getUniqueId()) ? ((long)Long.valueOf(TimeBetweenClicks.u.get((Object)spartanPlayer.getUniqueId()))) : 0L;
    }
    
    public static boolean n(final SpartanPlayer spartanPlayer) {
        return TimeBetweenClicks.u.containsKey(spartanPlayer.getUniqueId());
    }
    
    static {
        t = new HashMap<UUID, Long>();
        s = new HashMap<UUID, Long>();
        u = new HashMap<UUID, Long>();
    }
}
