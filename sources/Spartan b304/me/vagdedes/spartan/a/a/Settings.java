package me.vagdedes.spartan.a.a;

import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import java.util.HashMap;
import java.io.File;

public class Settings
{
    private static File file;
    private static final HashMap<String, Boolean> d;
    private static final HashMap<String, Integer> k;
    private static final HashMap<String, String> l;
    
    public Settings() {
        super();
    }
    
    public static void clear() {
        Settings.d.clear();
        Settings.k.clear();
        Settings.l.clear();
    }
    
    public static void create() {
        Settings.file = new File(Register.plugin.getDataFolder() + "/settings.yml");
        clear();
        ConfigUtils.add(Settings.file, "Punishments.broadcast_on_kick", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Punishments.broadcast_on_ban", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Punishments.wave_broadcast_message", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Punishments.run_wave_if_its_full", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Punishments.max_wave_size", Integer.valueOf(100));
        ConfigUtils.add(Settings.file, "Logs.log_console", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Logs.log_mysql", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Logs.log_file", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Logs.log_file_limit", Integer.valueOf(5000));
        ConfigUtils.add(Settings.file, "Notifications.public_verbose", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Notifications.enable_verbose_notifications_on_login", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Notifications.output_silent_check_verbose", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Notifications.enable_mining_notifications_on_login", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Notifications.staff_chat", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Notifications.awareness_notifications", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Teleportation.fall_damage_on_teleport", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Teleportation.ground_teleport_on_detection", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Protections.explosion_detection_cooldown", Integer.valueOf(4));
        ConfigUtils.add(Settings.file, "Protections.chat_cooldown", Integer.valueOf(0));
        ConfigUtils.add(Settings.file, "Protections.command_cooldown", Integer.valueOf(0));
        ConfigUtils.add(Settings.file, "Protections.reconnect_cooldown", Integer.valueOf(5));
        ConfigUtils.add(Settings.file, "Protections.use_tps_protection", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Protections.use_ping_protection", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Protections.use_teleport_protection", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Protections.avoid_self_bow_damage", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Protections.player_limit_per_ip", Integer.valueOf(3));
        ConfigUtils.add(Settings.file, "Protections.interactions_per_tick", Integer.valueOf(20));
        ConfigUtils.add(Settings.file, "Protections.disallowed_building", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Protections.custom_knockback", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Important.op_bypass", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Important.enable_permissions", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Important.check_server_compatibility_on_load", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Important.server_version", "Configure this if the plugin is unable to detect your server's version.");
        ConfigUtils.add(Settings.file, "Important.vl_reset_time", Integer.valueOf(60));
        ConfigUtils.add(Settings.file, "Important.cloud_feature", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Detections.allow_cancelled_hit_checking", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Detections.update_blocks_upon_teleport", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Detections.run_asynchronously", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Detections.ignore_shields", Boolean.valueOf(false));
        ConfigUtils.add(Settings.file, "Performance.enable_performance_optimizer", Boolean.valueOf(true));
        ConfigUtils.add(Settings.file, "Performance.enable_false_positive_detection", Boolean.valueOf(true));
    }
    
    public static boolean canDo(final String key) {
        if (Settings.d.containsKey(key)) {
            return Boolean.valueOf(Settings.d.get((Object)key));
        }
        if (!Settings.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Settings.file);
        if (loadConfiguration == null) {
            return false;
        }
        for (final String s : loadConfiguration.getKeys(true)) {
            if (s.contains("." + key) || s.startsWith(key)) {
                final boolean boolean1 = loadConfiguration.getBoolean(s);
                Settings.d.put(key, Boolean.valueOf(boolean1));
                return boolean1;
            }
        }
        return false;
    }
    
    public static int b(final String key) {
        if (Settings.k.containsKey(key)) {
            return Integer.valueOf(Settings.k.get((Object)key));
        }
        if (!Settings.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Settings.file);
        if (loadConfiguration == null) {
            return 0;
        }
        for (final String s : loadConfiguration.getKeys(true)) {
            if (s.contains("." + key) || s.startsWith(key)) {
                final int int1 = loadConfiguration.getInt(s);
                Settings.k.put(key, Integer.valueOf(int1));
                return int1;
            }
        }
        return 0;
    }
    
    public static String getString(final String key) {
        if (Settings.l.containsKey(key)) {
            return Settings.l.get(key);
        }
        if (!Settings.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Settings.file);
        if (loadConfiguration == null) {
            return null;
        }
        for (final String s : loadConfiguration.getKeys(true)) {
            if (s.contains("." + key) || s.startsWith(key)) {
                final String string = loadConfiguration.getString(s);
                Settings.l.put(key, string);
                return string;
            }
        }
        return null;
    }
    
    public static int a() {
        final String key = "vl_reset_time";
        int b = b(key);
        if (b < 60) {
            b = 60;
        }
        else if (b > 3600) {
            b = 3600;
        }
        Settings.k.put(key, Integer.valueOf(b));
        return b;
    }
    
    public static File getFile() {
        return Settings.file;
    }
    
    private static void a(final Player player) {
        if (canDo("enable_verbose_notifications_on_login") && PermissionUtils.a(player, Enums.Permission.verbose)) {
            VerboseNotifications.a(player, true, 0);
        }
        if (canDo("enable_mining_notifications_on_login") && PermissionUtils.a(player, Enums.Permission.mining)) {
            MiningNotifications.b(player, true);
        }
    }
    
    public static void e() {
        if (VersionUtils.a() != VersionUtils.a.c) {
            final Iterator<Player> iterator = Bukkit.getOnlinePlayers().iterator();
            while (iterator.hasNext()) {
                a(iterator.next());
            }
        }
    }
    
    public static void b(final Player player) {
        a(player);
    }
    
    static {
        Settings.file = new File(Register.plugin.getDataFolder() + "/settings.yml");
        d = new HashMap<String, Boolean>(30);
        k = new HashMap<String, Integer>(10);
        l = new HashMap<String, String>(1);
    }
}
