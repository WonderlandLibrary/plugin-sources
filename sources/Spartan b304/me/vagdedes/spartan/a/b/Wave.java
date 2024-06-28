package me.vagdedes.spartan.a.b;

import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.ArrayList;
import me.vagdedes.spartan.k.c.ConfigUtils;
import java.util.UUID;
import me.vagdedes.spartan.Register;
import java.io.File;

public class Wave
{
    private static File file;
    
    public Wave() {
        super();
    }
    
    public static void create() {
        Wave.file = new File(Register.plugin.getDataFolder() + "/wave.yml");
        if (!Wave.file.exists()) {
            ConfigUtils.add(Wave.file, UUID.randomUUID() + ".command", "ban {player} wave punishment example");
        }
    }
    
    public static UUID[] getWaveList() {
        final ArrayList<UUID> list = new ArrayList<UUID>();
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Wave.file);
        if (loadConfiguration != null) {
            for (final String name : loadConfiguration.getKeys(true)) {
                if (name.length() == 36 && StringUtils.countMatches(name, "-") == 4) {
                    list.add(UUID.fromString(name));
                }
            }
        }
        return list.<UUID>toArray(new UUID[0]);
    }
    
    public static String b() {
        String str = "";
        final UUID[] waveList = getWaveList();
        for (int length = waveList.length, i = 0; i < length; ++i) {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(waveList[i]);
            if (offlinePlayer.hasPlayedBefore()) {
                str = str + ChatColor.RED + offlinePlayer.getName() + ChatColor.GRAY + ", ";
            }
        }
        if (str.length() >= 2) {
            str = str.substring(0, str.length() - 2);
        }
        else if (str.length() == 0) {
            str = Language.getMessage("empty_wave_list");
        }
        return str;
    }
    
    public static void a(final UUID uuid, final String s) {
        final int b = Settings.b("max_wave_size");
        if (getSize() >= ((b <= 0) ? 1 : ((b > 100) ? 100 : b))) {
            if (Settings.canDo("run_wave_if_its_full")) {
                run();
            }
            return;
        }
        create();
        ConfigUtils.set(Wave.file, uuid.toString() + ".command", s);
    }
    
    public static void b(final UUID uuid) {
        final String string = uuid.toString();
        ConfigUtils.set(Wave.file, string + ".command", null);
        ConfigUtils.set(Wave.file, string, null);
    }
    
    public static void clear() {
        int n = 0;
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Wave.file);
        if (loadConfiguration != null) {
            for (final String name : loadConfiguration.getKeys(true)) {
                if (name.length() == 36 && StringUtils.countMatches(name, "-") == 4) {
                    ++n;
                    b(UUID.fromString(name));
                    if (n >= 100) {
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    public static boolean b(final UUID uuid) {
        final String string = uuid.toString();
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Wave.file);
        return loadConfiguration != null && loadConfiguration.getString(string) != null && loadConfiguration.getString(string + ".command") != null;
    }
    
    public static void run() {
        final boolean canDo = Settings.canDo("wave_broadcast_message");
        if (canDo) {
            Bukkit.broadcastMessage(Language.getMessage("wave_start_message"));
        }
        int i = 0;
        final int b = Settings.b("max_wave_size");
        final int n = (b <= 0) ? 1 : ((b > 100) ? 100 : b);
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Wave.file);
        if (loadConfiguration != null) {
            for (final String s : loadConfiguration.getKeys(true)) {
                if (s.length() == 36 && StringUtils.countMatches(s, "-") == 4) {
                    ++i;
                    final UUID fromString = UUID.fromString(s);
                    try {
                        final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(fromString);
                        final String string = loadConfiguration.getString(s + ".command");
                        if (string != null) {
                            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), ConfigUtils.a(offlinePlayer, string, (Enums.HackType)null));
                        }
                    }
                    catch (Exception ex) {}
                    b(fromString);
                    if (i >= n) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (canDo) {
            Bukkit.broadcastMessage(Language.getMessage("wave_end_message").replace((CharSequence)"{total}", (CharSequence)String.valueOf(i)));
        }
    }
    
    public static int getSize() {
        int n = 0;
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Wave.file);
        if (loadConfiguration != null) {
            for (final String s : loadConfiguration.getKeys(true)) {
                if (s.length() == 36 && StringUtils.countMatches(s, "-") == 4) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    static {
        Wave.file = new File(Register.plugin.getDataFolder() + "/wave.yml");
    }
}
