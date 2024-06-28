package me.vagdedes.spartan.f;

import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Settings;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class HackPrevention
{
    private Enums.HackType b;
    private String j;
    private int E;
    private SpartanLocation a;
    private int F;
    private boolean n;
    private double damage;
    private static final HashMap<UUID, CopyOnWriteArrayList<HackPrevention>> q;
    private static final HashMap<UUID, ConcurrentHashMap<Enums.HackType, Integer>> Q;
    
    public static void run() {
        for (final Map.Entry<UUID, ConcurrentHashMap<Enums.HackType, Integer>> entry : HackPrevention.Q.entrySet()) {
            for (final Map.Entry<Enums.HackType, Integer> entry2 : ((ConcurrentHashMap<Enums.HackType, Integer>)entry.getValue()).entrySet()) {
                final int intValue = (int)Integer.valueOf(entry2.getValue());
                if (intValue > 0) {
                    entry2.setValue(Integer.valueOf(intValue - 1));
                }
                else {
                    ((ConcurrentHashMap<Enums.HackType, Integer>)HackPrevention.Q.get(entry.getKey())).remove(entry2.getKey());
                }
            }
        }
    }
    
    public static void clear() {
        HackPrevention.q.clear();
        HackPrevention.Q.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        HackPrevention.q.remove(uniqueId);
        HackPrevention.Q.remove(uniqueId);
    }
    
    private void D(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final CopyOnWriteArrayList<HackPrevention> list = (CopyOnWriteArrayList<HackPrevention>)HackPrevention.q.get(uniqueId);
        if (list != null) {
            list.add(this);
        }
        else {
            final CopyOnWriteArrayList<HackPrevention> value = new CopyOnWriteArrayList<HackPrevention>();
            value.add(this);
            HackPrevention.q.put(uniqueId, value);
        }
    }
    
    private void f(final SpartanPlayer spartanPlayer, final int n) {
        if (spartanPlayer != null && this.b != null) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            final ConcurrentHashMap<Enums.HackType, Integer> concurrentHashMap = (ConcurrentHashMap<Enums.HackType, Integer>)HackPrevention.Q.get(uniqueId);
            if (concurrentHashMap != null) {
                if (!concurrentHashMap.containsKey(this.b) || n > Integer.valueOf(concurrentHashMap.get((Object)this.b))) {
                    concurrentHashMap.put(this.b, Integer.valueOf(n));
                }
            }
            else {
                final ConcurrentHashMap<Enums.HackType, Integer> value = new ConcurrentHashMap<Enums.HackType, Integer>();
                value.put(this.b, Integer.valueOf(n));
                HackPrevention.Q.put(uniqueId, value);
            }
        }
    }
    
    private void a(final SpartanPlayer spartanPlayer, final Enums.HackType b, final String j, final SpartanLocation a, final int f, final boolean n, final double damage, final int e, final boolean b2) {
        if (a(spartanPlayer, b, false)) {
            return;
        }
        this.b = b;
        this.j = j;
        this.E = e;
        this.a = a;
        this.F = f;
        this.n = n;
        this.damage = damage;
        if (b2) {
            this.D(spartanPlayer);
        }
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final SpartanLocation spartanLocation, final int n, final boolean b, final double n2, final boolean b2) {
        super();
        this.a(spartanPlayer, hackType, s, spartanLocation, n, b, Settings.canDo("fall_damage_on_teleport") ? n2 : 0.0, b2 ? 1 : 0, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final SpartanLocation spartanLocation, final int n, final boolean b, final double n2) {
        super();
        this.a(spartanPlayer, hackType, s, spartanLocation, n, b, Settings.canDo("fall_damage_on_teleport") ? n2 : 0.0, 1, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final SpartanLocation spartanLocation, final int n, final boolean b) {
        super();
        this.a(spartanPlayer, hackType, s, spartanLocation, n, b, 0.0, 1, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final SpartanLocation spartanLocation, final int n) {
        super();
        this.a(spartanPlayer, hackType, s, spartanLocation, n, false, 0.0, 1, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final SpartanLocation spartanLocation) {
        super();
        this.a(spartanPlayer, hackType, s, spartanLocation, 0, false, 0.0, 1, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final int n) {
        super();
        this.a(spartanPlayer, hackType, s, null, 0, false, 0.0, 1, true);
        this.f(spartanPlayer, n);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s) {
        super();
        this.a(spartanPlayer, hackType, s, null, 0, false, 0.0, 1, true);
    }
    
    public HackPrevention(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s, final boolean b) {
        super();
        this.a(spartanPlayer, hackType, s, null, 0, false, 0.0, b ? 2 : 1, true);
    }
    
    private HackPrevention(final Enums.HackType hackType) {
        super();
        this.a(null, hackType, null, null, 0, false, 0.0, -1, false);
    }
    
    public int h() {
        return this.E;
    }
    
    public Enums.HackType getHackType() {
        return this.b;
    }
    
    public String e() {
        return this.j;
    }
    
    public SpartanLocation a() {
        return this.a;
    }
    
    public int i() {
        return this.F;
    }
    
    public boolean i() {
        return this.n;
    }
    
    public double getDamage() {
        return this.damage;
    }
    
    public static HackPrevention a(final SpartanPlayer spartanPlayer, final Enums.HackType[] array, final boolean b) {
        if (spartanPlayer != null) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            final CopyOnWriteArrayList<HackPrevention> list = (CopyOnWriteArrayList<HackPrevention>)HackPrevention.q.get(uniqueId);
            HackPrevention hackPrevention = null;
            if (list != null) {
                for (final HackPrevention hackPrevention2 : list) {
                    for (final Enums.HackType hackType : array) {
                        if (hackType == hackPrevention2.b) {
                            if (hackPrevention == null) {
                                hackPrevention = hackPrevention2;
                                if (b) {
                                    list.remove(hackPrevention2);
                                }
                            }
                            else if (b) {
                                boolean b2 = false;
                                final Enums.HackType[] a = CombatUtils.a;
                                for (int length2 = a.length, j = 0; j < length2; ++j) {
                                    if (hackType == a[j]) {
                                        b2 = true;
                                        break;
                                    }
                                }
                                if (!b2) {
                                    list.remove(hackPrevention2);
                                }
                            }
                        }
                    }
                }
            }
            if (hackPrevention == null) {
                final ConcurrentHashMap<Enums.HackType, Integer> concurrentHashMap = (ConcurrentHashMap<Enums.HackType, Integer>)HackPrevention.Q.get(uniqueId);
                if (concurrentHashMap != null) {
                    for (final Enums.HackType hackType2 : array) {
                        if (concurrentHashMap.containsKey(hackType2) && Integer.valueOf(concurrentHashMap.get((Object)hackType2)) > 0 && Config.a(spartanPlayer, hackType2)) {
                            return new HackPrevention(hackType2);
                        }
                    }
                }
            }
            else if (b) {
                if (hackPrevention.E == 2) {
                    if (!VL.c(spartanPlayer, hackPrevention.b, hackPrevention.j)) {
                        return null;
                    }
                }
                else if (hackPrevention.E == 1) {
                    if (!VL.b(spartanPlayer, hackPrevention.b, hackPrevention.j)) {
                        return null;
                    }
                }
                else if (hackPrevention.E == 0) {
                    PunishUtils.a(spartanPlayer, hackPrevention.b, hackPrevention.j, false);
                }
            }
            return (hackPrevention == null) ? null : (Config.a(spartanPlayer, hackPrevention.b) ? hackPrevention : null);
        }
        return null;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Enums.HackType[] array, final boolean b) {
        return a(spartanPlayer, array, b) != null;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final boolean b) {
        return a(spartanPlayer, new Enums.HackType[] { hackType }, b) != null;
    }
    
    public static boolean G(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (HackPrevention.Q.containsKey(uniqueId)) {
            return true;
        }
        final CopyOnWriteArrayList<HackPrevention> list = (CopyOnWriteArrayList<HackPrevention>)HackPrevention.q.get(uniqueId);
        return list != null && !list.isEmpty();
    }
    
    static {
        q = new HashMap<UUID, CopyOnWriteArrayList<HackPrevention>>();
        Q = new HashMap<UUID, ConcurrentHashMap<Enums.HackType, Integer>>();
    }
}
