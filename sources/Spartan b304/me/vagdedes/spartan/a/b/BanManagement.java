package me.vagdedes.spartan.a.b;

import org.bukkit.event.player.PlayerLoginEvent;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.features.b.FileLogs;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.system.Enums;
import java.sql.Timestamp;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.UUID;
import java.util.HashMap;
import java.io.File;

public class BanManagement
{
    private static File file;
    private static final HashMap<String, String> d;
    
    public BanManagement() {
        super();
    }
    
    public static UUID[] getBanList() {
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(BanManagement.file);
        if (loadConfiguration != null) {
            final Set keys = loadConfiguration.getKeys(false);
            final ArrayList list = new ArrayList<UUID>(keys.size());
            for (final String s : keys) {
                if (s.length() == 36 && StringUtils.countMatches(s, "-") == 4 && !a(UUID.fromString(s))) {
                    list.add(UUID.fromString(s));
                }
            }
            return list.<UUID>toArray(new UUID[0]);
        }
        return new UUID[0];
    }
    
    public static String a() {
        String str = "";
        final UUID[] banList = getBanList();
        for (int length = banList.length, i = 0; i < length; ++i) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(banList[i]);
            if (offlinePlayer.hasPlayedBefore()) {
                str = str + ChatColor.RED + offlinePlayer.getName() + ChatColor.GRAY + ", ";
            }
        }
        if (str.length() >= 2) {
            str = str.substring(0, str.length() - 2);
        }
        else if (str.length() == 0) {
            str = Language.getMessage("empty_ban_list");
        }
        return str;
    }
    
    public static void clear() {
        BanManagement.d.clear();
    }
    
    public static void create() {
        BanManagement.file = new File(Register.plugin.getDataFolder() + "/bans.yml");
        if (!BanManagement.file.exists()) {
            clear();
            final String string = UUID.randomUUID().toString();
            ConfigUtils.add(BanManagement.file, string + ".reason", "Test");
            ConfigUtils.add(BanManagement.file, string + ".punisher", "Unknown");
        }
    }
    
    public static void a(final UUID uuid, final String replacement, final String s, final long time) {
        final boolean b = time != 0L;
        create();
        final String string = uuid.toString();
        ConfigUtils.set(BanManagement.file, string + ".reason", s);
        BanManagement.d.put(string + ".reason", s);
        ConfigUtils.set(BanManagement.file, string + ".punisher", replacement);
        BanManagement.d.put(string + ".punisher", replacement);
        if (b) {
            ConfigUtils.set(BanManagement.file, string + ".expiration", Long.valueOf(time));
            BanManagement.d.put(string + ".expiration", String.valueOf(time));
        }
        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        final String version = Register.plugin.getDescription().getVersion();
        final String replace = Language.getMessage("ban_reason").replace("{reason}", s).replace("{punisher}", replacement);
        String s2;
        if (b) {
            s2 = replace.replace("{expiration}", new Timestamp(time).toString().substring(0, 10));
        }
        else {
            s2 = replace.replace("{expiration}", "Never");
        }
        final String a = ConfigUtils.a(offlinePlayer, s2, null);
        final String a2 = ConfigUtils.a(offlinePlayer, Language.getMessage("ban_broadcast_message").replace("{reason}", s).replace("{punisher}", replacement), null);
        if (Settings.canDo("broadcast_on_ban")) {
            Bukkit.broadcastMessage(a2);
        }
        else {
            for (final SpartanPlayer spartanPlayer : NPC.a()) {
                if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.ban_message)) {
                    Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(a2);
                }
            }
        }
        FileLogs.a("[Spartan " + version + "] " + offlinePlayer.getName() + " was banned for " + s, true);
        if (offlinePlayer.isOnline()) {
            ((Player)offlinePlayer).kickPlayer(a);
        }
    }
    
    public static void a(final UUID uuid) {
        if (!isBanned(uuid)) {
            return;
        }
        create();
        final String string = uuid.toString();
        ConfigUtils.set(BanManagement.file, string + ".reason", null);
        BanManagement.d.remove(string + ".reason");
        ConfigUtils.set(BanManagement.file, string + ".punisher", null);
        BanManagement.d.remove(string + ".punisher");
        ConfigUtils.set(BanManagement.file, string + ".expiration", null);
        BanManagement.d.remove(string + ".expiration");
        ConfigUtils.set(BanManagement.file, string, null);
    }
    
    public static boolean isBanned(final UUID uuid) {
        return a(uuid, "reason") != null && a(uuid, "punisher") != null && !a(uuid);
    }
    
    private static boolean a(final UUID uuid) {
        final String a = a(uuid, "expiration");
        return a != null && System.currentTimeMillis() > Long.valueOf(a);
    }
    
    public static long a(final UUID uuid) {
        final String a = a(uuid, "expiration");
        if (a != null) {
            return Long.valueOf(a);
        }
        return 0L;
    }
    
    public static String a(final UUID uuid, final String str) {
        final String string = uuid.toString();
        if (BanManagement.d.containsKey(string + "." + str)) {
            return BanManagement.d.get(string + "." + str);
        }
        create();
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(BanManagement.file);
        if (loadConfiguration == null) {
            return str;
        }
        final String string2 = loadConfiguration.getString(string + "." + str);
        if (string2 == null) {
            BanManagement.d.remove(string + "." + str);
        }
        else {
            BanManagement.d.put(string + "." + str, string2);
        }
        return string2;
    }
    
    public static void a(final Player player, final PlayerLoginEvent playerLoginEvent) {
        final UUID uniqueId = player.getUniqueId();
        if (isBanned(uniqueId)) {
            final String replace = Language.getMessage("ban_reason").replace("{reason}", a(uniqueId, "reason")).replace("{punisher}", a(uniqueId, "punisher"));
            final String a = a(uniqueId, "expiration");
            String s;
            if (a != null) {
                s = replace.replace("{expiration}", new Timestamp(Long.valueOf(a)).toString().substring(0, 10));
            }
            else {
                s = replace.replace("{expiration}", "Never");
            }
            playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, ConfigUtils.a((OfflinePlayer)player, s, (Enums.HackType)null));
        }
    }
    
    static {
        BanManagement.file = new File(Register.plugin.getDataFolder() + "/bans.yml");
        d = new HashMap<String, String>(10);
    }
}
