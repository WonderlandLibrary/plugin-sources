package me.vagdedes.spartan.features.a;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Config;
import java.util.ArrayList;
import me.vagdedes.spartan.system.Enums;
import java.util.HashMap;

public class DefaultConfiguration
{
    private static final HashMap<Enums.HackType, Integer> C;
    private static final HashMap<Enums.HackType, Integer> D;
    
    public DefaultConfiguration() {
        super();
    }
    
    public static int e(final Enums.HackType key) {
        return Integer.valueOf(DefaultConfiguration.D.get((Object)key));
    }
    
    public static int d(final Enums.HackType hackType) {
        return f(hackType) + 1;
    }
    
    public static int f(final Enums.HackType key) {
        return Integer.valueOf(DefaultConfiguration.C.get((Object)key));
    }
    
    public static ArrayList<String> b(final Enums.HackType hackType) {
        final ArrayList<String> list = new ArrayList<String>();
        boolean b = false;
        list.add("");
        list.add("§3Configuration Recommendations§8:");
        final int f = f(hackType);
        final int d = d(hackType);
        final int e = e(hackType);
        if (f < Config.c(hackType) - 1) {
            list.add("§7Set the cancel-after-violation §7to §b" + f + "§7.");
            b = true;
        }
        if (d < Config.d(hackType)) {
            list.add("§7Set the violation-divisor §7to §b" + d + "§7.");
            b = true;
        }
        if (e < Config.b(hackType)) {
            list.add("§7Set the first punishment §7to §b> or = " + e + "§7.");
            b = true;
        }
        if (!b) {
            list.add("§7Everything seems to be configured correctly. Well done!");
        }
        return list;
    }
    
    static {
        C = new HashMap<Enums.HackType, Integer>(Enums.HackType.values().length);
        D = new HashMap<Enums.HackType, Integer>(Enums.HackType.values().length);
        for (final Enums.HackType hackType : Enums.HackType.values()) {
            switch (DefaultConfiguration$1.a[hackType.ordinal()]) {
                case 1: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 2: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 3: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 4: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 5: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 6: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 7: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 8: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 9: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 10: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(3));
                    break;
                }
                case 11: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 12: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(3));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(10));
                    break;
                }
                case 13: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 14: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 15: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 16: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 17: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 18: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 19: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 20: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 21: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(VL.m()));
                    break;
                }
                case 22: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(3));
                    break;
                }
                case 23: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 24: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(3));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(10));
                    break;
                }
                case 25: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(8));
                    break;
                }
                case 26: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 27: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(2));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(6));
                    break;
                }
                case 28: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(1));
                    break;
                }
                case 29: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(3));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(10));
                    break;
                }
                case 30: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(3));
                    break;
                }
                case 31: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(4));
                    break;
                }
                case 32: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(1));
                    break;
                }
                case 33: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 34: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(3));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(9));
                    break;
                }
                case 35: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(1));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(5));
                    break;
                }
                case 36: {
                    DefaultConfiguration.C.put(hackType, Integer.valueOf(0));
                    DefaultConfiguration.D.put(hackType, Integer.valueOf(2));
                    break;
                }
            }
        }
    }
}
