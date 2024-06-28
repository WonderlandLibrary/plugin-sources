package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.system.VL;
import org.bukkit.event.block.Action;
import java.util.Iterator;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class HitTime
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, UUID> v;
    
    public HitTime() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        HitTime.v.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        HitTime.v.clear();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!Checks.getBoolean("KillAura.check_hit_time") || LatencyUtils.e(spartanPlayer, 500)) {
            return;
        }
        for (int i = 0; i <= 2; ++i) {
            if (!a(entity, 1.0, i, 1.0)) {
                a(spartanPlayer);
                return;
            }
        }
        MillisUtils.o(spartanPlayer, HitTime.a.toString() + "=time_between_hits");
        AttemptUtils.m(spartanPlayer, HitTime.a.toString() + "=hit_time");
        HitTime.v.put(spartanPlayer.getUniqueId(), entity.getUniqueId());
    }
    
    private static boolean a(final Entity entity, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(new SpartanLocation(entity.getLocation()), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (BlockUtils.f(null, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static int i(final SpartanPlayer spartanPlayer) {
        if (spartanPlayer == null) {
            return 0;
        }
        int a = 0;
        final long a2 = MillisUtils.a(spartanPlayer, HitTime.a.toString() + "=time_between_hits");
        if (MillisUtils.hasTimer(a2)) {
            if (a2 <= 450L) {
                AttemptUtils.b(spartanPlayer, HitTime.a.toString() + "=hit_time", 10);
            }
            a = AttemptUtils.a(spartanPlayer, HitTime.a.toString() + "=hit_time");
        }
        return a;
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Action action) {
        if (VL.b(spartanPlayer, HitTime.a, true) || !HitTime.v.containsKey(spartanPlayer.getUniqueId()) || action != Action.LEFT_CLICK_AIR || VL.a(spartanPlayer, Enums.HackType.FastClicks) == 0 || LatencyUtils.e(spartanPlayer, 500)) {
            return;
        }
        for (final Entity entity : spartanPlayer.getNearbyEntities(3.8, 3.8, 3.8)) {
            if (HitTime.v.get(spartanPlayer.getUniqueId()) == entity.getUniqueId() && MathUtils.b(spartanPlayer.a(), new SpartanLocation(entity.getLocation())) >= 1.5 && CombatUtils.e(spartanPlayer, entity) <= 27.5 && CombatUtils.f(spartanPlayer, entity) <= 1.5) {
                final int i = i(spartanPlayer);
                if (i < 5 || AttemptUtils.b(spartanPlayer, HitTime.a.toString() + "=hit_time=attempts", 100) != 22) {
                    continue;
                }
                KillAuraOverall.o(spartanPlayer);
                a(spartanPlayer);
                AttemptUtils.m(spartanPlayer, HitTime.a.toString() + "=hit_time=attempts");
                new HackPrevention(spartanPlayer, HitTime.a, "t; hit time, tm: " + i + ", e: " + entity.getType().toString().toLowerCase());
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
        v = new HashMap<UUID, UUID>();
    }
}
