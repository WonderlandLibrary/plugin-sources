package me.vagdedes.spartan.checks.combat.a.a;

import java.util.ArrayList;
import java.util.Iterator;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.util.UUID;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.d.RequestUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import java.util.HashMap;
import java.io.File;

public class CAConfig
{
    private static final int limit = 10000;
    public static final int minimum = 100;
    private static File file;
    private static final String URL = "https://pastebin.com/raw/pLKeUFgr";
    private static final HashMap<CAEventListeners.b, HashMap<Double, CAEventListeners.a>> q;
    private static final HashMap<CAEventListeners.b, HashMap<CAEventListeners.a, Double>> r;
    private static boolean e;
    
    public CAConfig() {
        super();
    }
    
    public static boolean exists() {
        return CAConfig.e;
    }
    
    public static void clear() {
        CAConfig.q.clear();
        CAConfig.r.clear();
        CAConfig.e = false;
    }
    
    public static void load() {
        clear();
        CAConfig.file = new File("plugins/Spartan/combatAnalysis.yml");
        CAConfig.e = CAConfig.file.exists();
        if (CAConfig.file.exists()) {
            final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(CAConfig.file);
            if (loadConfiguration == null) {
                return;
            }
            final int size = loadConfiguration.getKeys(true).size();
            try {
                for (int i = 1; i <= 10000; ++i) {
                    for (final CAEventListeners.b b : CAEventListeners.b.values()) {
                        final CAEventListeners.a[] values2 = CAEventListeners.a.values();
                        for (int length2 = values2.length, k = 0; k < length2; ++k) {
                            a(loadConfiguration, b, values2[k], i);
                        }
                    }
                    if (i > size) {
                        return;
                    }
                }
            }
            catch (Exception ex) {}
        }
        else if (Settings.canDo("Important.cloud_feature")) {
            final String[] array;
            int length3;
            int l = 0;
            String s;
            CAEventListeners.b b2 = null;
            CAEventListeners.a a = null;
            final int n;
            final String s2;
            Threads.a(new Object(), () -> {
                if (Register.plugin != null && Register.plugin.isEnabled()) {
                    try {
                        RequestUtils.a("https://pastebin.com/raw/pLKeUFgr", "CombatAnalysis Check");
                        for (length3 = array.length; l < length3; ++l) {
                            s = array[l];
                            if (s.contains("Combat:")) {
                                b2 = CAEventListeners.b.c;
                            }
                            else if (s.contains("Contact:")) {
                                b2 = CAEventListeners.b.b;
                            }
                            else if (s.contains("Rotations:")) {
                                a = CAEventListeners.a.b;
                            }
                            else if (s.contains("Direction:")) {
                                a = CAEventListeners.a.c;
                            }
                            else if (b2 != null && a != null) {
                                s.indexOf(": ");
                                if (n > -1) {
                                    s.substring(n + 2);
                                    if (MathUtils.validDouble(s2)) {
                                        a(b2, a, Double.valueOf(s2), false);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ErrorUtils.send("Failed to load CombatAnalysis check's data.");
                    }
                }
            });
        }
    }
    
    private static void a(final YamlConfiguration yamlConfiguration, final CAEventListeners.b b, final CAEventListeners.a a, final int i) {
        final String string = b.toString() + "." + a.toString() + "." + i;
        if (yamlConfiguration.get(string) != null) {
            a(b, a, yamlConfiguration.getDouble(string), false);
        }
    }
    
    public static void a(final CAEventListeners.b b, final CAEventListeners.a a, final double d, final boolean b2) {
        final int dataSize = getDataSize();
        final boolean b3 = b2 && CAConfig.e;
        if (dataSize <= 10000) {
            if (!CAConfig.q.containsKey(b)) {
                CAConfig.q.put(b, new HashMap<Double, CAEventListeners.a>());
            }
            ((HashMap<Double, CAEventListeners.a>)CAConfig.q.get(b)).put(Double.valueOf(d), a);
            if (b3) {
                final String string = b.toString() + "." + a.toString() + "." + MathUtils.a(1, 10000);
                final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(CAConfig.file);
                try {
                    loadConfiguration.set(string, (Object)Double.valueOf(d));
                    loadConfiguration.save(CAConfig.file);
                    final Iterator<UUID> iterator = CATraining.a.iterator();
                    while (iterator.hasNext()) {
                        Bukkit.getPlayer((UUID)iterator.next()).sendMessage(ChatColor.GRAY + "[" + dataSize + "] " + ChatColor.GOLD + b + "." + a + " (" + d + ")");
                    }
                }
                catch (Exception ex) {}
            }
        }
        else if (b3) {
            final Iterator<UUID> iterator2 = CATraining.a.iterator();
            while (iterator2.hasNext()) {
                Bukkit.getPlayer((UUID)iterator2.next()).sendMessage(ChatColor.GRAY + "[" + dataSize + "] " + ChatColor.GOLD + "Data limit is reached!");
            }
        }
    }
    
    public static ArrayList<Double> a(final CAEventListeners.b b, final CAEventListeners.a a) {
        final ArrayList<Double> list = new ArrayList<Double>(CAConfig.q.size());
        if (CAConfig.q.containsKey(b)) {
            final HashMap<Double, CAEventListeners.a> hashMap = (HashMap<Double, CAEventListeners.a>)CAConfig.q.get(b);
            final Iterator<Double> iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                final double doubleValue = (double)Double.valueOf(iterator.next());
                if (hashMap.get(Double.valueOf(doubleValue)) == a) {
                    list.add(Double.valueOf(doubleValue));
                }
            }
        }
        return list;
    }
    
    public static int getDataSize() {
        int n = 0;
        for (final CAEventListeners.b b : CAEventListeners.b.values()) {
            if (CAConfig.q.containsKey(b)) {
                n += ((HashMap<Double, CAEventListeners.a>)CAConfig.q.get(b)).size();
            }
        }
        return n;
    }
    
    public static double a(final CAEventListeners.b key, final CAEventListeners.a key2) {
        if (!CAConfig.r.containsKey(key)) {
            CAConfig.r.put(key, new HashMap<CAEventListeners.a, Double>());
        }
        if (!((HashMap<CAEventListeners.a, Double>)CAConfig.r.get(key)).containsKey(key2)) {
            final ArrayList<Double> a = a(key, key2);
            double n = 0.0;
            final Iterator<Double> iterator = a.iterator();
            while (iterator.hasNext()) {
                n += Double.valueOf(iterator.next());
            }
            final double d = n / a.size();
            ((HashMap<CAEventListeners.a, Double>)CAConfig.r.get(key)).put(key2, Double.valueOf(d));
            return d;
        }
        return Double.valueOf(((HashMap<CAEventListeners.a, Double>)CAConfig.r.get(key)).get((Object)key2));
    }
    
    private static /* synthetic */ void f() {
        if (Register.plugin != null && Register.plugin.isEnabled()) {
            try {
                CAEventListeners.b b = null;
                CAEventListeners.a a = null;
                for (final String s : RequestUtils.a("https://pastebin.com/raw/pLKeUFgr", "CombatAnalysis Check")) {
                    if (s.contains("Combat:")) {
                        b = CAEventListeners.b.c;
                    }
                    else if (s.contains("Contact:")) {
                        b = CAEventListeners.b.b;
                    }
                    else if (s.contains("Rotations:")) {
                        a = CAEventListeners.a.b;
                    }
                    else if (s.contains("Direction:")) {
                        a = CAEventListeners.a.c;
                    }
                    else if (b != null && a != null) {
                        final int index = s.indexOf(": ");
                        if (index > -1) {
                            final String substring = s.substring(index + 2);
                            if (MathUtils.validDouble(substring)) {
                                a(b, a, Double.valueOf(substring), false);
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                ErrorUtils.send("Failed to load CombatAnalysis check's data.");
            }
        }
    }
    
    static {
        CAConfig.file = new File("plugins/Spartan/combatAnalysis.yml");
        q = new HashMap<CAEventListeners.b, HashMap<Double, CAEventListeners.a>>();
        r = new HashMap<CAEventListeners.b, HashMap<CAEventListeners.a, Double>>();
        CAConfig.e = CAConfig.file.exists();
    }
}
