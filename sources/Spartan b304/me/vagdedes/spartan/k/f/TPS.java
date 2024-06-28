package me.vagdedes.spartan.k.f;

import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.CheckTPS;
import java.util.HashMap;

public class TPS
{
    private static int cooldown;
    private static int aa;
    private static double aF;
    private static int ab;
    private static long[] a;
    private static final HashMap<String, CheckTPS> q;
    public static final double c = 0.33333;
    
    public TPS() {
        super();
    }
    
    public static void clear() {
        TPS.q.clear();
    }
    
    public static boolean i(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        return TPS.q.containsKey(spartanPlayer.getName() + "." + obj);
    }
    
    public static CheckTPS a(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        return TPS.q.get(spartanPlayer.getName() + "." + obj);
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final Enums.HackType obj) {
        TPS.q.put(spartanPlayer.getName() + "." + obj, new CheckTPS());
    }
    
    public static boolean useTPSProtection() {
        return Settings.canDo("use_tps_protection") && TPS.aa > 0;
    }
    
    public static boolean u() {
        return Settings.canDo("use_tps_protection") && get() < 18.0;
    }
    
    public static double get() {
        final double a = a(100);
        return (a > 20.0) ? 20.0 : ((a < 0.0) ? 0.0 : a);
    }
    
    private static double a(final int n) {
        if (TPS.ab < n) {
            return 20.0;
        }
        try {
            return n / ((System.currentTimeMillis() - TPS.a[(TPS.ab - 1 - n) % TPS.a.length]) / 1000.0);
        }
        catch (Exception ex) {
            return 0.0;
        }
    }
    
    public static void run() {
        TPS.a[TPS.ab % TPS.a.length] = System.currentTimeMillis();
        ++TPS.ab;
        if (TPS.aa > 0) {
            --TPS.aa;
        }
        if (TPS.cooldown > 0) {
            --TPS.cooldown;
        }
    }
    
    public static void t() {
        if (TPS.aa == 0 && TPS.cooldown == 0) {
            final double value = get();
            if (TPS.aF > 0.0 && TPS.aF - value >= 0.33333) {
                TPS.aa = 60;
                TPS.cooldown = 300;
            }
            TPS.aF = value;
        }
        else {
            TPS.aF = 0.0;
        }
    }
    
    static {
        TPS.cooldown = 0;
        TPS.aa = 0;
        TPS.aF = 0.0;
        TPS.ab = 0;
        TPS.a = new long[600];
        q = new HashMap<String, CheckTPS>(Enums.HackType.values().length);
    }
}
