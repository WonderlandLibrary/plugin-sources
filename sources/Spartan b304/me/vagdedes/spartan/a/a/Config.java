package me.vagdedes.spartan.a.a;

import me.vagdedes.spartan.api.CheckSilentToggleEvent;
import me.vagdedes.spartan.features.b.FileLogs;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.api.CheckToggleEvent;
import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.api.PlayerViolationCommandEvent;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.h.a.CancelAfterViolation;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.api.CheckCancelEvent;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.e.ScheduleHandlers;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.checks.e.AutoRespawn;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.d.LegitimatePlayers;
import me.vagdedes.spartan.d.HackerFinder;
import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.features.syn.DailyProgress;
import me.vagdedes.spartan.features.d.Cloud;
import me.vagdedes.spartan.features.d.RAMoverCPU;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.k.c.ChunkManager;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.a.b.Wave;
import me.vagdedes.spartan.a.b.BanManagement;
import me.vagdedes.spartan.a.b.SQLConfig;
import me.vagdedes.spartan.a.b.ChatProtection;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.api.SpartanReloadEvent;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.Register;
import java.util.ArrayList;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;
import java.util.HashSet;

public class Config
{
    private static final HashSet<Enums.HackType> a;
    private static final HashSet<Enums.HackType> b;
    private static final HashMap<Enums.HackType, String> e;
    private static final HashMap<Enums.HackType, Integer> f;
    private static final HashMap<Enums.HackType, Integer> g;
    private static final HashMap<Enums.HackType, ArrayList<String>> h;
    private static final HashMap<Enums.HackType, ArrayList<String>> i;
    private static final HashMap<Enums.HackType, HashMap<Integer, ArrayList<String>>> j;
    
    public Config() {
        super();
    }
    
    public static String[] a(final Enums.HackType hackType, int i) {
        if (Config.j.containsKey(hackType) && ((HashMap<Integer, ArrayList<String>>)Config.j.get(hackType)).containsKey(Integer.valueOf(i))) {
            return ((ArrayList<String>)((HashMap<Integer, ArrayList<String>>)Config.j.get(hackType)).get(Integer.valueOf(i))).<String>toArray(new String[0]);
        }
        final FileConfiguration config = Register.plugin.getConfig();
        final ArrayList<String> list = new ArrayList<String>();
        final String string = hackType.toString() + ".punishments." + i;
        boolean b = false;
        for (int j = 1; j <= 10; ++j) {
            if ((j > 1 && config.contains(string + "*" + j)) || config.contains(string + "+" + j)) {
                b = true;
                break;
            }
        }
        if (b || config.contains(string)) {
            for (int k = 1; k <= VL.n(); ++k) {
                boolean b2 = false;
                String string2 = string;
                int n = 0;
                for (int l = 1; l <= 10; ++l) {
                    final String string3 = string2 + "*" + l + "." + k;
                    final String string4 = string2 + "+" + l + "." + k;
                    if (config.contains(string3)) {
                        b2 = true;
                        n = l;
                        string2 = string3;
                        break;
                    }
                    if (config.contains(string4)) {
                        n = l;
                        string2 = string4;
                        break;
                    }
                }
                if (n == 0) {
                    string2 = string2 + "." + k;
                }
                if (config.contains(string2)) {
                    final String string5 = config.getString(string2);
                    if (string5 != null && string5.length() > 0) {
                        list.add(string5);
                        if (!Config.j.containsKey(hackType)) {
                            Config.j.put(hackType, new HashMap<Integer, ArrayList<String>>());
                        }
                        ((HashMap<Integer, ArrayList<String>>)Config.j.get(hackType)).put(Integer.valueOf(i), list);
                        if (n > 0 && i > 0) {
                            while (i <= VL.m()) {
                                if (b2) {
                                    i *= n;
                                }
                                else {
                                    i += n;
                                }
                                if (!((HashMap<Integer, ArrayList<String>>)Config.j.get(hackType)).containsKey(Integer.valueOf(i))) {
                                    ((HashMap<Integer, ArrayList<String>>)Config.j.get(hackType)).put(Integer.valueOf(i), list);
                                }
                            }
                        }
                    }
                }
            }
        }
        return list.<String>toArray(new String[0]);
    }
    
    public static int a(final Enums.HackType hackType) {
        int n = 0;
        for (int i = 1; i <= VL.m(); ++i) {
            final String[] a = a(hackType, i);
            for (int length = a.length, j = 0; j < length; ++j) {
                if (a[j] != null) {
                    n = i;
                }
            }
        }
        return n;
    }
    
    public static int b(final Enums.HackType hackType) {
        for (int i = 1; i <= VL.m(); ++i) {
            final String[] a = a(hackType, i);
            for (int length = a.length, j = 0; j < length; ++j) {
                if (a[j] != null) {
                    return i;
                }
            }
        }
        return 0;
    }
    
    public static ArrayList<Integer> a(final Enums.HackType hackType) {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= VL.m(); ++i) {
            final String[] a = a(hackType, i);
            for (int length = a.length, j = 0; j < length; ++j) {
                if (a[j] != null) {
                    list.add(Integer.valueOf(i));
                }
            }
        }
        return list;
    }
    
    public static void a(final Player player, final boolean b) {
        final SpartanReloadEvent spartanReloadEvent = new SpartanReloadEvent();
        Bukkit.getPluginManager().callEvent((Event)spartanReloadEvent);
        if (spartanReloadEvent.isCancelled()) {
            return;
        }
        final String message = Language.getMessage("config_reload");
        if (player != null) {
            player.sendMessage(message);
        }
        else if (b) {
            Bukkit.getConsoleSender().sendMessage(message);
        }
        VL.r();
        create(true);
    }
    
    public static boolean a(final Enums.HackType key, final int i) {
        if (!a(key)) {
            return false;
        }
        final FileConfiguration config = Register.plugin.getConfig();
        if (config.getInt(key.toString() + ".cancel_after_violation") >= i) {
            return false;
        }
        ConfigUtils.set(key.toString() + ".cancel_after_violation", Integer.valueOf(i));
        final int int1 = config.getInt(key.toString() + ".cancel_after_violation");
        if (int1 < 0) {
            Config.f.put(key, Integer.valueOf(-1));
        }
        else if (int1 > VL.m()) {
            Config.f.put(key, Integer.valueOf(VL.m() + 1));
        }
        else {
            Config.f.put(key, Integer.valueOf(int1 + 1));
        }
        return true;
    }
    
    public static void a(final Enums.HackType key) {
        final FileConfiguration config = Register.plugin.getConfig();
        final int e = DefaultConfiguration.e(key);
        ConfigUtils.add(key.toString() + ".enabled", Boolean.valueOf(true));
        ConfigUtils.add(key.toString() + ".name", key.toString());
        ConfigUtils.add(key.toString() + ".violation_divisor", Integer.valueOf(DefaultConfiguration.d(key)));
        ConfigUtils.add(key.toString() + ".disabled_worlds", "");
        ConfigUtils.add(key.toString() + ".silent_worlds", "");
        if (a(key)) {
            ConfigUtils.add(key.toString() + ".silent", Boolean.valueOf(false));
            ConfigUtils.add(key.toString() + ".cancel_after_violation", Integer.valueOf(DefaultConfiguration.f(key)));
            final int int1 = config.getInt(key.toString() + ".cancel_after_violation");
            if (int1 < 0) {
                Config.f.put(key, Integer.valueOf(-1));
            }
            else if (int1 > VL.m()) {
                Config.f.put(key, Integer.valueOf(VL.m() + 1));
            }
            else {
                Config.f.put(key, Integer.valueOf(int1 + 1));
            }
        }
        final boolean boolean1 = config.getBoolean(key.toString() + ".enabled");
        final boolean boolean2 = config.getBoolean(key.toString() + ".silent");
        final int int2 = config.getInt(key.toString() + ".violation_divisor");
        final String string = config.getString(key.toString() + ".disabled_worlds");
        final String string2 = config.getString(key.toString() + ".silent_worlds");
        final String string3 = config.getString(key.toString() + ".name");
        if (string3 != null) {
            a(key, string3, false);
        }
        if (int2 > 100) {
            Config.g.put(key, Integer.valueOf(100));
        }
        else {
            Config.g.put(key, Integer.valueOf(int2));
        }
        if (string != null) {
            final String[] split = string.split(",");
            if (split.length > 0) {
                final ArrayList<String> value = new ArrayList<String>();
                final String[] array = split;
                for (int length = array.length, i = 0; i < length; ++i) {
                    final String replaceAll = array[i].replaceAll(" ", "");
                    if (replaceAll.length() > 0) {
                        value.add(replaceAll.toLowerCase());
                    }
                }
                if (value.size() > 0) {
                    Config.h.put(key, value);
                }
            }
        }
        if (string2 != null) {
            final String[] split2 = string2.split(",");
            if (split2.length > 0) {
                final ArrayList<String> value2 = new ArrayList<String>();
                final String[] array2 = split2;
                for (int length2 = array2.length, j = 0; j < length2; ++j) {
                    final String replaceAll2 = array2[j].replaceAll(" ", "");
                    if (replaceAll2.length() > 0) {
                        value2.add(replaceAll2.toLowerCase());
                    }
                }
                if (value2.size() > 0) {
                    Config.i.put(key, value2);
                }
            }
        }
        ConfigUtils.a(boolean1, Config.a, key);
        ConfigUtils.a(boolean2, Config.b, key);
        if (!config.contains(key.toString() + ".punishments")) {
            for (int k = 1; k <= VL.m(); ++k) {
                if (config.contains(key.toString() + ".punishments." + k)) {
                    return;
                }
            }
            ConfigUtils.add(key.toString() + ".punishments." + e + ".1", "spartan warn {player} You were detected for {detection}");
            ConfigUtils.add(key.toString() + ".punishments." + e + ".2", "spartan kick {player} {detection}");
        }
    }
    
    public static void a(final Enums.HackType key, final String anotherString, final boolean b) {
        for (final Enums.HackType hackType : Enums.HackType.values()) {
            if (hackType != key && Config.e.containsKey(hackType) && ((String)Config.e.get(hackType)).equalsIgnoreCase(anotherString)) {
                return;
            }
        }
        if (b) {
            ConfigUtils.set(key.toString() + ".name", anotherString);
        }
        Config.e.put(key, (anotherString.length() > 32) ? anotherString.substring(0, 32) : anotherString);
    }
    
    public static String a(final Enums.HackType hackType) {
        return Config.e.containsKey(hackType) ? ((String)Config.e.get(hackType)) : hackType.toString();
    }
    
    public static Enums.HackType a(final String anotherString) {
        for (final Enums.HackType hackType : Enums.HackType.values()) {
            if (Config.e.containsKey(hackType) && ((String)Config.e.get(hackType)).equalsIgnoreCase(anotherString)) {
                return hackType;
            }
        }
        try {
            return Enums.HackType.valueOf(anotherString);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static void clear() {
        Register.plugin.reloadConfig();
    }
    
    public static void create(final boolean b) {
        Config.g.clear();
        Config.a.clear();
        Config.b.clear();
        Config.j.clear();
        Config.f.clear();
        Config.e.clear();
        Config.h.clear();
        Config.i.clear();
        if (b && Register.plugin != null) {
            Register.plugin.reloadConfig();
            final Enums.HackType[] values = Enums.HackType.values();
            for (int length = values.length, i = 0; i < length; ++i) {
                a(values[i]);
            }
            Checks.create();
            Settings.create();
            Language.create();
            ChatProtection.create();
            SQLConfig.create();
            BanManagement.create();
            Compatibility.create();
            Wave.create();
        }
        else {
            Checks.clear();
            Settings.clear();
            Language.clear();
            ChatProtection.clear();
            SQLConfig.clear();
            BanManagement.clear();
            Compatibility.clear();
        }
        MoveUtils.clear();
        GroundUtils.clear();
        PermissionUtils.clear();
        LagManagement.s();
        HackPrevention.clear();
        ChunkManager.j(b);
        SearchEngine.d(b);
        RAMoverCPU.clear();
        Cloud.clear();
        DailyProgress.refresh();
        FalsePositiveDetection.clear();
        PerformanceOptimizer.clear();
        HackerFinder.clear();
        LegitimatePlayers.clear();
        VerboseNotifications.k();
        AutoRespawn.clear();
        CombatAnalysis.a(b, true);
        Compatibility.a(b);
        ScheduleHandlers.clear();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (b(spartanPlayer, hackType)) {
            final CheckCancelEvent checkCancelEvent = new CheckCancelEvent(Bukkit.getPlayer(spartanPlayer.getUniqueId()), hackType);
            Bukkit.getPluginManager().callEvent((Event)checkCancelEvent);
            return !checkCancelEvent.isCancelled();
        }
        return false;
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        final int a = VL.a(spartanPlayer, hackType);
        final int c = c(hackType);
        return !CheckProtection.E(spartanPlayer) && !isSilent(hackType, spartanPlayer.getWorld().getName()) && !VL.g(spartanPlayer, hackType) && !VL.af(spartanPlayer) && !CancelAfterViolation.E(spartanPlayer) && ((a == 0 && c <= 0) || a >= c) && !FalsePositiveDetection.d(spartanPlayer, hackType) && !VL.h(spartanPlayer, hackType);
    }
    
    public static int c(final Enums.HackType hackType) {
        return Config.f.containsKey(hackType) ? ((int)Integer.valueOf(Config.f.get((Object)hackType))) : 0;
    }
    
    public static int d(final Enums.HackType hackType) {
        return Config.g.containsKey(hackType) ? ((int)Integer.valueOf(Config.g.get((Object)hackType))) : 1;
    }
    
    public static boolean isEnabled(final Enums.HackType o) {
        return Config.a.contains(o);
    }
    
    public static boolean a(final Enums.HackType[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (Config.a.contains(array[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean b(final Enums.HackType[] array) {
        int n = 0;
        for (int length = array.length, i = 0; i < length; ++i) {
            if (Config.a.contains(array[i])) {
                ++n;
            }
        }
        return n == array.length;
    }
    
    public static boolean isSilent(final Enums.HackType key, final String o) {
        return !a(key) || Config.b.contains(key) || (o != null && Config.i.containsKey(key) && ((ArrayList<String>)Config.i.get(key)).contains(o));
    }
    
    public static boolean a(final Player player, final Enums.HackType hackType) {
        if (isEnabled(hackType)) {
            final SpartanPlayer spartanPlayer = new SpartanPlayer(player);
            boolean b = false;
            for (final String s : a(hackType, VL.a(spartanPlayer, hackType))) {
                if (s != null) {
                    b = true;
                    final String a2 = ConfigUtils.a((OfflinePlayer)player, s.replace((CharSequence)"{player}", (CharSequence)spartanPlayer.getName()), hackType);
                    final PlayerViolationCommandEvent playerViolationCommandEvent = new PlayerViolationCommandEvent(player, hackType, a2);
                    Bukkit.getPluginManager().callEvent((Event)playerViolationCommandEvent);
                    if (playerViolationCommandEvent.isCancelled()) {
                        return false;
                    }
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), a2);
                }
            }
            return b;
        }
        return false;
    }
    
    public static boolean a(final Enums.HackType hackType) {
        return hackType != Enums.HackType.AutoRespawn && hackType != Enums.HackType.CombatAnalysis && hackType != Enums.HackType.FastClicks && hackType != Enums.HackType.XRay;
    }
    
    public static void a(final Enums.HackType hackType, final boolean b) {
        if (!isEnabled(hackType)) {
            final CheckToggleEvent checkToggleEvent = new CheckToggleEvent(hackType, Enums.ToggleAction.Enable);
            Bukkit.getPluginManager().callEvent((Event)checkToggleEvent);
            if (checkToggleEvent.isCancelled()) {
                return;
            }
            final String version = Register.plugin.getDescription().getVersion();
            Register.plugin.getConfig().set(hackType.toString() + ".enabled", (Object)Boolean.valueOf(true));
            Register.plugin.saveConfig();
            a(hackType);
            if (b) {
                FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] " + hackType.toString() + " has been enabled.", true);
            }
        }
    }
    
    public static void b(final Enums.HackType hackType, final boolean b) {
        if (isEnabled(hackType)) {
            final CheckToggleEvent checkToggleEvent = new CheckToggleEvent(hackType, Enums.ToggleAction.Disable);
            Bukkit.getPluginManager().callEvent((Event)checkToggleEvent);
            if (checkToggleEvent.isCancelled()) {
                return;
            }
            final String version = Register.plugin.getDescription().getVersion();
            Register.plugin.getConfig().set(hackType.toString() + ".enabled", (Object)Boolean.valueOf(false));
            Register.plugin.saveConfig();
            a(hackType);
            if (b) {
                FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] " + hackType.toString() + " has been disabled.", true);
            }
        }
    }
    
    public static void c(final Enums.HackType o, final boolean b) {
        if (Config.b.contains(o) || !a(o)) {
            return;
        }
        final CheckSilentToggleEvent checkSilentToggleEvent = new CheckSilentToggleEvent(o, Enums.ToggleAction.Enable);
        Bukkit.getPluginManager().callEvent((Event)checkSilentToggleEvent);
        if (checkSilentToggleEvent.isCancelled()) {
            return;
        }
        final String version = Register.plugin.getDescription().getVersion();
        Register.plugin.getConfig().set(o.toString() + ".silent", (Object)Boolean.valueOf(true));
        Register.plugin.saveConfig();
        a(o);
        if (b) {
            FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] " + o.toString() + " is silent checking.", true);
        }
    }
    
    public static void d(final Enums.HackType o, final boolean b) {
        if (!Config.b.contains(o) || !a(o)) {
            return;
        }
        final CheckSilentToggleEvent checkSilentToggleEvent = new CheckSilentToggleEvent(o, Enums.ToggleAction.Disable);
        Bukkit.getPluginManager().callEvent((Event)checkSilentToggleEvent);
        if (checkSilentToggleEvent.isCancelled()) {
            return;
        }
        final String version = Register.plugin.getDescription().getVersion();
        Register.plugin.getConfig().set(o.toString() + ".silent", (Object)Boolean.valueOf(false));
        Register.plugin.saveConfig();
        a(o);
        if (b) {
            FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] " + o.toString() + " is no longer silent checking.", true);
        }
    }
    
    public static void a() {
        CheckProtection.c(20);
        final String version = Register.plugin.getDescription().getVersion();
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            a(values[i], false);
        }
        FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] All checks have been enabled.", true);
    }
    
    public static void b() {
        CheckProtection.c(20);
        final String version = Register.plugin.getDescription().getVersion();
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            b(values[i], false);
        }
        FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] All checks have been disabled.", true);
    }
    
    public static void c() {
        CheckProtection.c(20);
        final String version = Register.plugin.getDescription().getVersion();
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            c(values[i], false);
        }
        FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] All checks are now silent checking.", true);
    }
    
    public static void d() {
        CheckProtection.c(20);
        final String version = Register.plugin.getDescription().getVersion();
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            d(values[i], false);
        }
        FileLogs.a("[Spartan " + version + "-" + IDs.spigot() + "-" + IDs.nonce() + "] All checks are no longer silent checking.", true);
    }
    
    public static boolean a(final Enums.HackType hackType, final String s) {
        return !Config.h.containsKey(hackType) || !((ArrayList<String>)Config.h.get(hackType)).contains(s.toLowerCase());
    }
    
    static {
        a = new HashSet<Enums.HackType>(Enums.HackType.values().length);
        b = new HashSet<Enums.HackType>(Enums.HackType.values().length);
        e = new HashMap<Enums.HackType, String>(Enums.HackType.values().length);
        f = new HashMap<Enums.HackType, Integer>(Enums.HackType.values().length);
        g = new HashMap<Enums.HackType, Integer>(Enums.HackType.values().length);
        h = new HashMap<Enums.HackType, ArrayList<String>>(Enums.HackType.values().length);
        i = new HashMap<Enums.HackType, ArrayList<String>>(Enums.HackType.values().length);
        j = new HashMap<Enums.HackType, HashMap<Integer, ArrayList<String>>>();
    }
}
