package me.vagdedes.spartan.features.d;

import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.system.VL;
import org.bukkit.OfflinePlayer;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.Iterator;
import java.util.Map;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.a.a.Settings;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;
import java.util.HashSet;

public class FalsePositiveDetection
{
    private static final HashSet<Enums.HackType> m;
    private static final HashMap<Enums.HackType, String[]> J;
    private static final HashMap<UUID, HashMap<String, ArrayList<Double>>> q;
    private static final HashMap<UUID, HashMap<String, Long>> K;
    private static final HashMap<String, Integer> L;
    private static final long g = 5000L;
    public static final int r = 3600;
    private static int time;
    private static int s;
    
    public FalsePositiveDetection() {
        super();
    }
    
    public static Enums.HackType[] b() {
        return FalsePositiveDetection.m.<Enums.HackType>toArray(new Enums.HackType[0]);
    }
    
    public static boolean d() {
        return Settings.canDo("enable_false_positive_detection") && SearchEngine.d() && TPS.get() >= 18.0;
    }
    
    public static String a(final Enums.HackType hackType, final String s) {
        String str = hackType.toString();
        for (final String str2 : s.replaceAll(",", "").split(" ")) {
            if (!MathUtils.validNum(str2) && !MathUtils.validDouble(str2)) {
                str = str + " " + str2;
            }
        }
        return str;
    }
    
    public static void run() {
        if (d()) {
            if (FalsePositiveDetection.time == 0) {
                FalsePositiveDetection.time = 3600;
                FalsePositiveDetection.q.clear();
                FalsePositiveDetection.K.clear();
                final Iterator<Map.Entry<String, Integer>> iterator = FalsePositiveDetection.L.entrySet().iterator();
                while (iterator.hasNext()) {
                    if (Integer.valueOf(((Map.Entry<String, Integer>)iterator.next()).getValue()) < FalsePositiveDetection.s) {
                        iterator.remove();
                    }
                }
            }
            else {
                --FalsePositiveDetection.time;
            }
        }
        else {
            clear();
        }
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        final String s = "false-positive-detection=";
        final int n = 5;
        if (AttemptUtils.b(spartanPlayer, s + hackType.toString(), n * 20) >= n * 3) {
            CooldownUtils.d(spartanPlayer, s + "timeout", 3600);
        }
        return M(spartanPlayer);
    }
    
    public static boolean M(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "false-positive-detection=timeout");
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        return !M(spartanPlayer) && !CooldownUtils.g(spartanPlayer, "false-positive-detection=corrected=" + hackType.toString());
    }
    
    private static boolean N(final SpartanPlayer spartanPlayer) {
        return !PlayerData.aS(spartanPlayer) && PlayerData.r(spartanPlayer) >= 60 && MoveUtils.l(spartanPlayer) <= -0.0784000015258;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Enums.HackType key, final String s) {
        if (spartanPlayer == null || key == null || s == null || !Settings.canDo("enable_false_positive_detection") || (System.currentTimeMillis() - spartanPlayer.getLastPlayed()) / 1000L <= 1200L || M(spartanPlayer)) {
            return false;
        }
        boolean b = true;
        if (FalsePositiveDetection.m.contains(key)) {
            if (FalsePositiveDetection.J.containsKey(key)) {
                b = false;
                final String[] array = (String[])FalsePositiveDetection.J.get(key);
                for (int length = array.length, i = 0; i < length; ++i) {
                    if (s.contains(array[i])) {
                        b = true;
                        break;
                    }
                }
            }
            if (b) {
                final UUID uniqueId = spartanPlayer.getUniqueId();
                String key2 = key.toString();
                final ArrayList<Double> value = new ArrayList<Double>(10);
                final boolean b2 = PerformanceOptimizer.M(spartanPlayer) || SearchEngine.a((OfflinePlayer)Bukkit.getPlayer(spartanPlayer.getUniqueId()), false, true, true, false);
                for (final String str : s.replaceAll(",", "").split(" ")) {
                    if (MathUtils.validDouble(str)) {
                        if (str.substring(str.indexOf(".") + 1).length() > 5) {
                            final double double1 = Double.parseDouble(str.substring(0, 5));
                            if (double1 < 1.0) {
                                value.add(Double.valueOf(double1));
                            }
                        }
                    }
                    else if (!MathUtils.validNum(str)) {
                        key2 = key2 + " " + str;
                    }
                }
                if (value.size() > 0) {
                    if (SearchEngine.c(key).contains(key2) || Cloud.a(key).contains(key2)) {
                        value.clear();
                        return true;
                    }
                    if (FalsePositiveDetection.L.containsKey(key2) && !b2) {
                        final int intValue = (int)Integer.valueOf(FalsePositiveDetection.L.get((Object)key2));
                        if (intValue < FalsePositiveDetection.s) {
                            FalsePositiveDetection.L.put(key2, Integer.valueOf(intValue + 1));
                        }
                        value.clear();
                        CooldownUtils.d(spartanPlayer, "false-positive-detection=corrected=" + key.toString(), 2);
                        return true;
                    }
                    if (!FalsePositiveDetection.K.containsKey(uniqueId)) {
                        FalsePositiveDetection.K.put(uniqueId, new HashMap<String, Long>());
                    }
                    final long currentTimeMillis = System.currentTimeMillis();
                    final long n = ((HashMap<String, Long>)FalsePositiveDetection.K.get(uniqueId)).containsKey(key2) ? (currentTimeMillis - Long.valueOf(((HashMap<String, Long>)FalsePositiveDetection.K.get(uniqueId)).get((Object)key2))) : 0L;
                    ((HashMap<String, Long>)FalsePositiveDetection.K.get(uniqueId)).put(key2, Long.valueOf(currentTimeMillis));
                    if (!FalsePositiveDetection.q.containsKey(uniqueId)) {
                        FalsePositiveDetection.q.put(uniqueId, new HashMap<String, ArrayList<Double>>());
                    }
                    else if (((HashMap<String, ArrayList<Double>>)FalsePositiveDetection.q.get(uniqueId)).containsKey(key2)) {
                        final ArrayList<Double> list = (ArrayList<Double>)((HashMap<String, ArrayList<Double>>)FalsePositiveDetection.q.get(uniqueId)).get(key2);
                        if (list.size() == value.size()) {
                            int index = 0;
                            double n2 = 0.0;
                            final Iterator<Double> iterator = value.iterator();
                            while (iterator.hasNext()) {
                                final double doubleValue = (double)Double.valueOf(iterator.next());
                                final double doubleValue2 = (double)Double.valueOf(list.get(index));
                                ++index;
                                if (doubleValue == doubleValue2) {
                                    ++n2;
                                }
                                else {
                                    final double max = Math.max(doubleValue, doubleValue2);
                                    double a = 0.0;
                                    if (max != 0.0) {
                                        a += Math.abs(Math.min(doubleValue, doubleValue2)) / Math.abs(max);
                                    }
                                    if (Math.abs(doubleValue - doubleValue2) <= 0.1) {
                                        a += 0.5;
                                    }
                                    if (a <= 0.0) {
                                        continue;
                                    }
                                    n2 += Math.min(a, 1.0);
                                }
                            }
                            if (n2 / value.size() * 100.0 >= 65.0 && (!b2 || (n > 5000L && VL.f(spartanPlayer) > 5000L) || N(spartanPlayer))) {
                                FalsePositiveDetection.L.put(key2, Integer.valueOf(1));
                                ((HashMap<String, Long>)FalsePositiveDetection.K.get(uniqueId)).remove(key2);
                                ((HashMap<String, ArrayList<Double>>)FalsePositiveDetection.q.get(uniqueId)).remove(key2);
                                if (b2) {
                                    return false;
                                }
                                CooldownUtils.d(spartanPlayer, "false-positive-detection=corrected=" + key.toString(), 2);
                                return true;
                            }
                        }
                    }
                    ((HashMap<String, ArrayList<Double>>)FalsePositiveDetection.q.get(uniqueId)).put(key2, value);
                }
            }
        }
        if (b && (System.currentTimeMillis() - spartanPlayer.getLastPlayed()) / 1000L > 300L) {
            final Enums.HackType[] a2 = CombatUtils.a;
            for (int length3 = a2.length, k = 0; k < length3; ++k) {
                if (key == a2[k]) {
                    return false;
                }
            }
            final boolean b3 = VL.a(spartanPlayer, key) < 2 && VL.f(spartanPlayer) / 50L > 200L;
            if (b3) {
                for (final String s2 : s.split(" ")) {
                    if (MathUtils.validDouble(s2) && Double.valueOf(s2) > 1.0) {
                        return false;
                    }
                }
                VL.J(spartanPlayer);
                CooldownUtils.d(spartanPlayer, "false-positive-detection=corrected=" + key.toString(), 2);
            }
            return b3;
        }
        return false;
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        FalsePositiveDetection.q.remove(uniqueId);
        FalsePositiveDetection.K.remove(uniqueId);
    }
    
    public static void clear() {
        FalsePositiveDetection.q.clear();
        FalsePositiveDetection.K.clear();
        FalsePositiveDetection.L.clear();
        FalsePositiveDetection.time = 3600;
    }
    
    static {
        m = new HashSet<Enums.HackType>(11);
        J = new HashMap<Enums.HackType, String[]>();
        q = new HashMap<UUID, HashMap<String, ArrayList<Double>>>();
        K = new HashMap<UUID, HashMap<String, Long>>();
        L = new HashMap<String, Integer>();
        FalsePositiveDetection.time = 3600;
        FalsePositiveDetection.s = 12;
        FalsePositiveDetection.m.add(Enums.HackType.Fly);
        FalsePositiveDetection.m.add(Enums.HackType.IrregularMovements);
        FalsePositiveDetection.m.add(Enums.HackType.MorePackets);
        FalsePositiveDetection.m.add(Enums.HackType.Clip);
        FalsePositiveDetection.m.add(Enums.HackType.BoatMove);
        FalsePositiveDetection.m.add(Enums.HackType.ElytraMove);
        FalsePositiveDetection.m.add(Enums.HackType.EntityMove);
        FalsePositiveDetection.m.add(Enums.HackType.NoFall);
        FalsePositiveDetection.m.add(Enums.HackType.NoSlowdown);
        FalsePositiveDetection.m.add(Enums.HackType.Exploits);
        FalsePositiveDetection.m.add(Enums.HackType.Jesus);
        FalsePositiveDetection.m.add(Enums.HackType.KillAura);
        FalsePositiveDetection.J.put(Enums.HackType.Fly, new String[] { "t: stable", "t: up", "t: neutral", "t: change" });
        FalsePositiveDetection.J.put(Enums.HackType.Exploits, new String[] { "t: undetected movement" });
        FalsePositiveDetection.J.put(Enums.HackType.KillAura, new String[] { "t: block raytrace" });
        FalsePositiveDetection.J.put(Enums.HackType.Jesus, Register.v1_13 ? new String[] { "t: speed(" } : new String[] { "t: speed(", "t: walking(", "t: upwards(", "t: swimming" });
    }
}
