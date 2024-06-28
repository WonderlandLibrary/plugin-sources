package me.vagdedes.spartan.features.b;

import org.bukkit.inventory.ItemStack;
import me.vagdedes.ultimatestatistics.api.UltimateStatisticsAPI;
import me.vagdedes.spartan.j.UltimateStatistics;
import me.vagdedes.spartan.k.g.PluginTicks;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.features.syn.DailyProgress;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.features.syn.MiningHistory;
import me.vagdedes.spartan.features.syn.ViolationHistory;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.k.d.RequestUtils;
import java.net.URLEncoder;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.api.BackgroundAPI;
import me.vagdedes.spartan.features.d.Cloud;
import me.vagdedes.spartan.k.b.SiteUtils;
import me.vagdedes.spartan.a.a.Config;
import org.apache.commons.lang.StringUtils;
import me.vagdedes.spartan.api.API;
import org.bukkit.OfflinePlayer;
import java.util.Iterator;
import org.bukkit.Bukkit;
import java.util.Map;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.d.MapUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.c.SynManager;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashSet;
import org.bukkit.Material;
import me.vagdedes.spartan.system.Enums;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SearchEngine
{
    private static ConcurrentHashMap<String, String> b;
    private static final HashMap<Enums.HackType, Double> E;
    private static final HashMap<Material, Double> F;
    private static final HashSet<UUID> g;
    private static final HashSet<UUID> h;
    private static final HashSet<UUID> i;
    private static final HashSet<UUID> j;
    private static final HashSet<UUID> k;
    private static final HashMap<Enums.HackType, ArrayList<String>> G;
    public static final int o = 100;
    public static final double ao = 16.0;
    public static final double ap = 5.0;
    public static final double aq = 10.0;
    public static final double ar = 1.0;
    public static final int p = 300;
    
    public SearchEngine() {
        super();
    }
    
    static void g() {
        if (!SynManager.s() && ErrorUtils.d("The Search-Engine has reached its maximum size of cached information. Spartan offers system upgrades to help improve its performance. Click this message to learn more.") != null) {
            final SpartanPlayer[] a = NPC.a();
            for (int length = a.length, i = 0; i < length; ++i) {
                final Player player = a[i].getPlayer();
                final UUID uniqueId = player.getUniqueId();
                if (!SearchEngine.j.contains(uniqueId) && PermissionUtils.a(player, Enums.Permission.manage)) {
                    SearchEngine.j.add(uniqueId);
                }
            }
        }
    }
    
    public static void c(final Player player) {
        if (!d() && PermissionUtils.a(player, Enums.Permission.manage)) {
            final UUID uniqueId = player.getUniqueId();
            if (!SearchEngine.k.contains(uniqueId)) {
                SearchEngine.k.add(uniqueId);
                if (ErrorUtils.d("The Search-Engine feature is responsible for a large part of the plugin's functionality. Please ensure you keep at least one log-storing option set to true to re-enable this feature. ") != null) {}
            }
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final int n) {
        if (n > 1 && (System.currentTimeMillis() - spartanPlayer.getLastPlayed()) / 1000L < 300L) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            if (!SearchEngine.i.contains(uniqueId)) {
                SearchEngine.i.add(uniqueId);
            }
        }
    }
    
    public static int b() {
        return SynManager.s() ? 51200 : 5120;
    }
    
    public static int c() {
        return MapUtils.a(SearchEngine.b);
    }
    
    public static int size() {
        return SearchEngine.b.size();
    }
    
    public static boolean d() {
        return Settings.canDo("log_file") || Settings.canDo("log_mysql");
    }
    
    public static boolean e() {
        return SearchEngine.b.size() < 100;
    }
    
    public static boolean f() {
        return size() > 0;
    }
    
    public static void c(final boolean b) {
        SearchEngine.b.clear();
        SearchEngine.g.clear();
        SearchEngine.E.clear();
        SearchEngine.F.clear();
        SearchEngine.h.clear();
        SearchEngine.G.clear();
        if (b) {
            SearchEngine.i.clear();
            SearchEngine.j.clear();
            SearchEngine.k.clear();
        }
    }
    
    public static String c() {
        if (!d()) {
            return "All log saving options are disabled.";
        }
        if (Settings.canDo("log_mysql")) {
            if (SQLLogs.isConnected()) {
                SearchEngine.b = SQLLogs.a(b());
            }
        }
        else {
            final File[] listFiles = new File(Register.plugin.getDataFolder() + "/logs/").listFiles();
            if (listFiles != null) {
                for (final File file : listFiles) {
                    if (file.isFile() && file.getName().replaceAll("[0-9]", "").equals("log.yml")) {
                        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
                        for (final String s : loadConfiguration.getKeys(false)) {
                            a(s, loadConfiguration.getString(s), SearchEngine.b);
                        }
                        if (MapUtils.a(SearchEngine.b) >= b()) {
                            g();
                            break;
                        }
                    }
                }
            }
        }
        if (e()) {
            return "Not enough logs. Must be at least 100 rows.";
        }
        final Iterator<Map.Entry<String, String>> iterator2 = SearchEngine.b.entrySet().iterator();
        while (iterator2.hasNext()) {
            final String s2 = (String)((Map.Entry<String, String>)iterator2.next()).getValue();
            if (s2.contains("has been considered a legitimate player and is exempted from all checks.")) {
                final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(s2.split(" ", 4)[2]);
                if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
                    continue;
                }
                final UUID uniqueId = offlinePlayer.getUniqueId();
                if (SearchEngine.h.contains(uniqueId)) {
                    continue;
                }
                SearchEngine.h.add(uniqueId);
            }
        }
        return "Done loading " + MapUtils.a((ConcurrentHashMap)SearchEngine.b) + "KB of data.";
    }
    
    public static boolean a(final String key, final String value, final ConcurrentHashMap<String, String> concurrentHashMap) {
        final String str = "[Spartan Build ";
        final int index = value.indexOf(str);
        if (index > -1) {
            final String substring = value.substring(index + str.length(), index + str.length() + 3);
            if (Integer.valueOf(API.getVersion().substring(6)) - (StringUtils.isNumeric(substring) ? ((int)Integer.valueOf(substring)) : 0) <= 5) {
                concurrentHashMap.put(key, value);
                return true;
            }
        }
        return false;
    }
    
    public static void a(final String key, final String value) {
        if ((SynManager.s() || Config.isEnabled(Enums.HackType.XRay)) && MapUtils.a(SearchEngine.b) < b()) {
            SearchEngine.b.put(key, value);
        }
    }
    
    public static void d(final boolean b) {
        if (SiteUtils.c("https://vagdedes.com/spartan/syn.php").equals("vagdedes.com")) {
            c(!b);
            if (b && Register.isPluginEnabled()) {
                Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> {
                    if (Register.plugin.isEnabled()) {
                        ErrorUtils.send("Updating the Search Engine's data.");
                        ErrorUtils.send(c());
                    }
                });
            }
        }
    }
    
    public static int a(final Player player) {
        int n = 0;
        if (d()) {
            final String string = "] " + player.getName() + " was kicked for ";
            final Iterator<Map.Entry<String, String>> iterator = SearchEngine.b.entrySet().iterator();
            while (iterator.hasNext()) {
                if (((String)((Map.Entry<String, String>)iterator.next()).getValue()).contains(string)) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public static int b(final Player player) {
        int n = 0;
        if (d()) {
            final String string = "] " + player.getName() + " was banned for ";
            final Iterator<Map.Entry<String, String>> iterator = SearchEngine.b.entrySet().iterator();
            while (iterator.hasNext()) {
                if (((String)((Map.Entry<String, String>)iterator.next()).getValue()).contains(string)) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public static ArrayList<String> a(final String str, final Enums.HackType hackType) {
        final ArrayList<String> list = new ArrayList<String>();
        if (d()) {
            for (final Map.Entry<String, String> entry : SearchEngine.b.entrySet()) {
                final String str2 = (String)entry.getValue();
                if (str2.contains("[Spartan " + API.getVersion()) && (str == null || str2.contains(str + " failed ")) && (hackType == null || str2.contains(" failed " + hackType.toString() + " "))) {
                    list.add(entry.getKey() + " " + str2);
                }
            }
        }
        return list;
    }
    
    public static ArrayList<String> c(final Enums.HackType key) {
        if (SearchEngine.G.containsKey(key)) {
            return SearchEngine.G.get(key);
        }
        final ArrayList<String> value = new ArrayList<String>();
        final HashSet<String> set = new HashSet<String>();
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        final HashSet<String> a = Cloud.a(key);
        BackgroundAPI.getVersion().replace("Build ", "");
        final Iterator<Map.Entry<String, String>> iterator = SearchEngine.b.entrySet().iterator();
        while (iterator.hasNext()) {
            final String s = (String)((Map.Entry<String, String>)iterator.next()).getValue();
            final int index = s.indexOf("(TPS: ");
            double doubleValue = 20.0;
            if (index > -1) {
                try {
                    doubleValue = Double.valueOf(s.substring(index + 6, index + 11));
                }
                catch (Exception ex) {}
            }
            if (doubleValue >= 18.0 && s.startsWith("(False Positive) ") && s.contains(" " + key.toString() + " ")) {
                final String s2 = s.split("\\), ")[7];
                final String substring = s2.substring(1, s2.length() - 2);
                final String a2 = FalsePositiveDetection.a(key, substring);
                if (value.contains(a2) || a.contains(a2)) {
                    continue;
                }
                if (!hashMap.containsKey(a2)) {
                    hashMap.put(a2, Integer.valueOf(1));
                }
                else {
                    final int i = Integer.valueOf(hashMap.get((Object)a2)) + 1;
                    if (i == 3) {
                        value.add(a2);
                        set.add(substring);
                        hashMap.remove(a2);
                    }
                    else {
                        hashMap.put(a2, Integer.valueOf(i));
                    }
                }
            }
        }
        if (Settings.canDo("Important.cloud_feature")) {
            final String spigot = IDs.spigot();
            if (NPC.a().length > 20 && spigot.length() >= 1 && IDs.nonce().length() >= 8 && StringUtils.isNumeric(spigot)) {
                int n = 0;
                while (set.iterator().hasNext()) {
                    final Iterator<String> iterator2;
                    final String s3;
                    final String str;
                    final ArrayList list;
                    final int index2;
                    Threads.a(value, () -> {
                        s3 = iterator2.next();
                        if (Register.plugin != null && Register.plugin.isEnabled()) {
                            try {
                                if (RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=add&data=falsePositives&value=" + URLEncoder.encode(s3, "UTF-8") + "&version=" + str, "Cloud Network [ADD]")[0].equals("true")) {
                                    list.remove(index2);
                                }
                            }
                            catch (Exception ex2) {
                                ErrorUtils.send("(ADD) Failed to connect to the Spartan Cloud.");
                            }
                        }
                        return;
                    });
                    ++n;
                }
            }
        }
        set.clear();
        SearchEngine.G.put(key, value);
        return value;
    }
    
    public static ViolationHistory a(String string, final Enums.HackType hackType) {
        final ConcurrentHashMap<Enums.HackType, Integer> concurrentHashMap = new ConcurrentHashMap<Enums.HackType, Integer>();
        final ConcurrentHashMap<Enums.HackType, ArrayList<String>> concurrentHashMap2 = new ConcurrentHashMap<Enums.HackType, ArrayList<String>>();
        if (string != null) {
            string += " ";
        }
        for (final Map.Entry<String, String> entry : SearchEngine.b.entrySet()) {
            final String s = (String)entry.getKey();
            final String s2 = (String)entry.getValue();
            if (string == null || s2.contains(string)) {
                final int beginIndex = s.indexOf("[") + 1;
                final String substring = s.substring(beginIndex, beginIndex + 10);
                final int index = s2.indexOf("(TPS: ");
                double doubleValue = 20.0;
                if (index > -1) {
                    try {
                        doubleValue = Double.valueOf(s2.substring(index + 6, index + 11));
                    }
                    catch (Exception ex) {}
                }
                if (doubleValue >= 18.0) {
                    if (hackType == null) {
                        for (final Enums.HackType hackType2 : Enums.HackType.values()) {
                            if (s2.contains(" " + hackType2.toString() + " ")) {
                                if (!concurrentHashMap.containsKey(hackType2)) {
                                    concurrentHashMap.put(hackType2, Integer.valueOf(1));
                                }
                                else {
                                    concurrentHashMap.put(hackType2, Integer.valueOf((int)Integer.valueOf(concurrentHashMap.get((Object)hackType2)) + 1));
                                }
                                if (!concurrentHashMap2.containsKey(hackType2)) {
                                    final ArrayList<String> value = new ArrayList<String>();
                                    value.add(substring);
                                    concurrentHashMap2.put(hackType2, value);
                                }
                                else if (!((ArrayList<String>)concurrentHashMap2.get(hackType2)).contains(substring)) {
                                    ((ArrayList<String>)concurrentHashMap2.get(hackType2)).add(substring);
                                }
                            }
                        }
                    }
                    else if (s2.contains(" " + hackType.toString() + " ")) {
                        if (!concurrentHashMap.containsKey(hackType)) {
                            concurrentHashMap.put(hackType, Integer.valueOf(1));
                        }
                        else {
                            concurrentHashMap.put(hackType, Integer.valueOf((int)Integer.valueOf(concurrentHashMap.get((Object)hackType)) + 1));
                        }
                        if (!concurrentHashMap2.containsKey(hackType)) {
                            final ArrayList<String> value2 = new ArrayList<String>();
                            value2.add(substring);
                            concurrentHashMap2.put(hackType, value2);
                        }
                        else if (!((ArrayList<String>)concurrentHashMap2.get(hackType)).contains(substring)) {
                            ((ArrayList<String>)concurrentHashMap2.get(hackType)).add(substring);
                        }
                    }
                }
            }
            if (concurrentHashMap.size() < 100) {
                concurrentHashMap.clear();
                concurrentHashMap2.clear();
            }
        }
        return new ViolationHistory(concurrentHashMap, concurrentHashMap2);
    }
    
    public static ViolationHistory a(final Enums.HackType hackType) {
        return a((String)null, hackType);
    }
    
    public static ViolationHistory getViolationHistory(final String s) {
        return a(s, (Enums.HackType)null);
    }
    
    public static ViolationHistory a() {
        return a((String)null, (Enums.HackType)null);
    }
    
    public static MiningHistory a(String string) {
        final HashMap<Material, Integer> hashMap = new HashMap<Material, Integer>();
        final HashMap<Material, ArrayList<String>> hashMap2 = new HashMap<Material, ArrayList<String>>();
        if (!SynManager.s()) {
            return new MiningHistory(hashMap, hashMap2);
        }
        if (string != null) {
            string += " ";
        }
        for (final Map.Entry<String, String> entry : SearchEngine.b.entrySet()) {
            final String s = (String)entry.getKey();
            final String s2 = (String)entry.getValue();
            if (string == null || s2.contains(string)) {
                final int beginIndex = s.indexOf("[") + 1;
                final String substring = s.substring(beginIndex, beginIndex + 10);
                for (final Material material : MiningNotifications.a) {
                    final String lowerCase = material.toString().toLowerCase();
                    if (s2.contains(" " + lowerCase + " ") || s2.contains(" " + lowerCase + "s ")) {
                        final String s3 = s2.split(" ", 6)[5];
                        if (StringUtils.isNumeric(s3)) {
                            final int intValue = (int)Integer.valueOf(s3);
                            if (!hashMap.containsKey(material)) {
                                hashMap.put(material, Integer.valueOf(intValue));
                            }
                            else {
                                hashMap.put(material, Integer.valueOf((int)Integer.valueOf(hashMap.get((Object)material)) + intValue));
                            }
                            if (!hashMap2.containsKey(material)) {
                                final ArrayList<String> value = new ArrayList<String>();
                                value.add(substring);
                                hashMap2.put(material, value);
                            }
                            else if (!((ArrayList<String>)hashMap2.get(material)).contains(substring)) {
                                ((ArrayList<String>)hashMap2.get(material)).add(substring);
                            }
                        }
                    }
                }
            }
            if (hashMap.size() < 100) {
                hashMap.clear();
                hashMap2.clear();
            }
        }
        return new MiningHistory(hashMap, hashMap2);
    }
    
    public static MiningHistory a() {
        return a((String)null);
    }
    
    public static double a(final Material key) {
        if (SearchEngine.F.containsKey(key)) {
            return Double.valueOf(SearchEngine.F.get((Object)key));
        }
        final MiningHistory a = a();
        final HashMap<Material, Integer> a2 = a.a();
        final HashMap<Material, ArrayList<String>> b = a.b();
        double d = 0.0;
        if (a2.containsKey(key) && b.containsKey(key)) {
            d = Integer.valueOf(a.a().get((Object)key)) / (double)((ArrayList<String>)a.b().get(key)).size();
        }
        SearchEngine.F.put(key, Double.valueOf(d));
        return d;
    }
    
    public static DailyProgress a(final int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        if (!SynManager.s()) {
            return new DailyProgress(n2, n3, n4, n5);
        }
        for (final Map.Entry<String, String> entry : SearchEngine.b.entrySet()) {
            final String s = (String)entry.getKey();
            final String s2 = (String)entry.getValue();
            final int beginIndex = s.indexOf("[") + 1;
            final String substring = s.substring(beginIndex, beginIndex + 10);
            if (substring.equals(TimeUtils.d(TimeUtils.a(n, 0, 0)).substring(0, substring.length()))) {
                ++n4;
                if (s2.startsWith("(False Positive) ")) {
                    ++n5;
                }
                else {
                    if (!s2.contains(" failed ")) {
                        continue;
                    }
                    ++n2;
                    final String[] split = s2.split(" ", 9);
                    if (split.length != 9) {
                        continue;
                    }
                    int intValue = 0;
                    try {
                        intValue = Integer.valueOf(split[7].substring(0, split[7].length() - 1));
                    }
                    catch (Exception ex) {}
                    if (intValue <= 0) {
                        continue;
                    }
                    final Iterator<Integer> iterator2 = Config.a(Enums.HackType.valueOf(split[5])).iterator();
                    while (iterator2.hasNext()) {
                        if (intValue == Integer.valueOf(iterator2.next())) {
                            ++n3;
                        }
                    }
                }
            }
        }
        return new DailyProgress(n2, n3, n4, n5);
    }
    
    public static boolean a(final OfflinePlayer offlinePlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (offlinePlayer != null && offlinePlayer.hasPlayedBefore()) {
            final boolean online = offlinePlayer.isOnline();
            if (b2 && (offlinePlayer.isOp() || (online && PermissionUtils.a((Player)offlinePlayer, Enums.Permission.admin)))) {
                return true;
            }
            if (b4 && online && FalsePositiveDetection.M(SpartanBukkit.a(offlinePlayer.getUniqueId()))) {
                return true;
            }
            final UUID uniqueId = offlinePlayer.getUniqueId();
            if (b) {
                final long currentTimeMillis = System.currentTimeMillis();
                final long n = (currentTimeMillis - offlinePlayer.getFirstPlayed()) / 1000L;
                final boolean b5 = (currentTimeMillis - offlinePlayer.getLastPlayed()) / 1000L < 300L || PluginTicks.get() < 300;
                if ((n < 604800L && (b5 || SearchEngine.i.contains(uniqueId))) || b5) {
                    return true;
                }
            }
            if ((b3 && UltimateStatistics.a(offlinePlayer)) || SearchEngine.g.contains(uniqueId)) {
                return true;
            }
            final ViolationHistory violationHistory = getViolationHistory(offlinePlayer.getName());
            final ConcurrentHashMap<Enums.HackType, Integer> violations = violationHistory.getViolations();
            final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days = violationHistory.getDays();
            for (final Enums.HackType key : violations.keySet()) {
                if (days.containsKey(key) && Integer.valueOf(violations.get((Object)key)) / (double)((ArrayList<String>)days.get(key)).size() >= a(key, true) + 1.0) {
                    SearchEngine.g.add(uniqueId);
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean a(final OfflinePlayer offlinePlayer) {
        return a(offlinePlayer, true, true, true, true);
    }
    
    public static boolean c(final UUID o) {
        if (SearchEngine.h.contains(o)) {
            return true;
        }
        if (UltimateStatistics.isEnabled()) {
            try {
                if (UltimateStatisticsAPI.hasData(o, me.vagdedes.ultimatestatistics.system.Enums.EventType.Chat_Messages_Sent)) {
                    final Object stats = UltimateStatisticsAPI.getStats(o, me.vagdedes.ultimatestatistics.system.Enums.EventType.Chat_Messages_Sent, "amount");
                    if (stats instanceof Integer && (int)stats >= 500) {
                        return true;
                    }
                }
                if (UltimateStatisticsAPI.hasData(o, me.vagdedes.ultimatestatistics.system.Enums.EventType.Commands_Executed)) {
                    final Object stats2 = UltimateStatisticsAPI.getStats(o, me.vagdedes.ultimatestatistics.system.Enums.EventType.Commands_Executed, "amount");
                    if (stats2 instanceof Integer && (int)stats2 >= 500) {
                        return true;
                    }
                }
            }
            catch (NoSuchMethodError noSuchMethodError) {}
        }
        return false;
    }
    
    public static void c(final UUID uuid) {
        if (!SearchEngine.h.contains(uuid)) {
            SearchEngine.h.add(uuid);
        }
    }
    
    public static double a(final Enums.HackType hackType, final boolean b) {
        if (SearchEngine.E.containsKey(hackType)) {
            final double doubleValue = (double)Double.valueOf(SearchEngine.E.get((Object)hackType));
            return (doubleValue == -1.0 && b) ? 5.0 : doubleValue;
        }
        final ViolationHistory a = a();
        final ConcurrentHashMap<Enums.HackType, Integer> violations = a.getViolations();
        final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days = a.getDays();
        if (days.containsKey(hackType)) {
            SearchEngine.E.put(hackType, Double.valueOf(Math.min(Math.max((double)Integer.valueOf(violations.get((Object)hackType)) / (double)((ArrayList<String>)days.get(hackType)).size(), 5.0), 10.0)));
        }
        else {
            SearchEngine.E.put(hackType, Double.valueOf(-1.0));
        }
        return Double.valueOf(SearchEngine.E.get((Object)hackType));
    }
    
    public static ArrayList<String> a() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = SearchEngine.b.keySet().iterator();
        while (iterator.hasNext()) {
            final String[] split = ((String)SearchEngine.b.get(iterator.next())).split(" ", 5);
            if (split[4].startsWith("failed ")) {
                final String s = split[3];
                if (list.contains(s)) {
                    continue;
                }
                list.add(s);
            }
        }
        return list;
    }
    
    public static ItemStack a() {
        return new ItemStack(Register.v1_13 ? Material.WRITABLE_BOOK : Material.getMaterial("BOOK_AND_QUILL"));
    }
    
    private static /* synthetic */ void a(final String s, final String str, final ArrayList list, final int index) {
        if (Register.plugin != null && Register.plugin.isEnabled()) {
            try {
                if (RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=add&data=falsePositives&value=" + URLEncoder.encode(s, "UTF-8") + "&version=" + str, "Cloud Network [ADD]")[0].equals("true")) {
                    list.remove(index);
                }
            }
            catch (Exception ex) {
                ErrorUtils.send("(ADD) Failed to connect to the Spartan Cloud.");
            }
        }
    }
    
    private static /* synthetic */ void h() {
        if (Register.plugin.isEnabled()) {
            ErrorUtils.send("Updating the Search Engine's data.");
            ErrorUtils.send(c());
        }
    }
    
    static {
        SearchEngine.b = new ConcurrentHashMap<String, String>();
        E = new HashMap<Enums.HackType, Double>();
        F = new HashMap<Material, Double>();
        g = new HashSet<UUID>();
        h = new HashSet<UUID>();
        i = new HashSet<UUID>();
        j = new HashSet<UUID>();
        k = new HashSet<UUID>();
        G = new HashMap<Enums.HackType, ArrayList<String>>();
    }
}
