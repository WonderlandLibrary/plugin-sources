package me.vagdedes.spartan.k.c;

import me.vagdedes.spartan.k.d.StringUtils;
import java.util.Base64;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashSet;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.features.b.SearchEngine;
import java.sql.Timestamp;
import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.f.LatencyUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.OfflinePlayer;

public class ConfigUtils
{
    public ConfigUtils() {
        super();
    }
    
    public static String a(final OfflinePlayer offlinePlayer, String s, final Enums.HackType hackType) {
        final String value = String.valueOf(TPS.get());
        final String replacement = (value.length() > 5) ? value.substring(0, 5) : value;
        final String version = Register.plugin.getDescription().getVersion();
        final String replace = VersionUtils.a().toString().substring(1).replace("_", ".");
        s = s.replace("{async}", String.valueOf(Settings.canDo("Detections.run_asynchronously") && Threads.enabled));
        s = s.replace("{online}", String.valueOf(NPC.a().length));
        s = s.replace("{staff}", String.valueOf(PermissionUtils.f().size()));
        s = s.replace("{plib}", String.valueOf(Register.arePlibPacketsEnabled()));
        s = s.replace("{tps}", replacement);
        s = s.replace("{server:name}", Language.getMessage("server_name"));
        s = s.replace("{plugin:version}", version);
        s = s.replace("{server:version}", replace);
        s = s.replace("{line}", "\n");
        s = s.replace("{date:time}", TimeUtils.a(TimeUtils.a(0, 0, 0)));
        s = s.replace("{date:d-m-y}", TimeUtils.b(TimeUtils.a(0, 0, 0)));
        s = s.replace("{date:m-d-y}", TimeUtils.c(TimeUtils.a(0, 0, 0)));
        s = s.replace("{date:y-m-d}", TimeUtils.d(TimeUtils.a(0, 0, 0)));
        if (offlinePlayer != null) {
            s = s.replace("{player}", offlinePlayer.getName());
            s = s.replace("{uuid}", offlinePlayer.getUniqueId().toString());
            if (offlinePlayer.isOnline()) {
                final SpartanPlayer a = SpartanBukkit.a(offlinePlayer.getUniqueId());
                s = s.replace("{ping}", String.valueOf(LatencyUtils.c((Player)offlinePlayer)));
                s = s.replace("{vls}", String.valueOf(VL.o(a)));
                s = s.replace("{world}", ((Player)offlinePlayer).getWorld().getName());
                s = s.replace("{health}", String.valueOf(((Player)offlinePlayer).getHealth()));
                s = s.replace("{gamemode}", ((Player)offlinePlayer).getGameMode().toString().toLowerCase());
                s = s.replace("{x}", String.valueOf(((Player)offlinePlayer).getLocation().getX()));
                s = s.replace("{y}", String.valueOf(((Player)offlinePlayer).getLocation().getY()));
                s = s.replace("{z}", String.valueOf(((Player)offlinePlayer).getLocation().getZ()));
                s = s.replace("{yaw}", String.valueOf(((Player)offlinePlayer).getLocation().getYaw()));
                s = s.replace("{pitch}", String.valueOf(((Player)offlinePlayer).getLocation().getPitch()));
                s = s.replace("{cps}", String.valueOf(CpsCounter.o(a)));
                s = s.replace("{time-online}", String.valueOf(TimeUtils.a(new Timestamp(offlinePlayer.getLastPlayed()), 1000)));
                s = s.replace("{kicks}", String.valueOf(SearchEngine.a((Player)offlinePlayer)));
                s = s.replace("{bans}", String.valueOf(SearchEngine.b((Player)offlinePlayer)));
                s = s.replace("{moving}", String.valueOf(MoveUtils.k(a) > 0.0 || MoveUtils.i(a) > 0.0));
            }
        }
        if (hackType != null) {
            final boolean b = offlinePlayer != null && offlinePlayer.isOnline();
            s = s.replace("{detection}", Config.a(hackType));
            s = s.replace("{detection:real}", hackType.toString());
            s = s.replace("{silent:detection}", String.valueOf(Config.isSilent(hackType, b ? ((Player)offlinePlayer).getWorld().getName() : null)));
            if (b) {
                s = s.replace("{vls:detection}", String.valueOf(VL.a(SpartanBukkit.a(offlinePlayer.getUniqueId()), hackType)));
            }
        }
        return a(offlinePlayer, ChatColor.translateAlternateColorCodes('&', s));
    }
    
    public static String a(final OfflinePlayer offlinePlayer, String s) {
        if (s.contains("{") && s.contains("}") && s.contains(":")) {
            final boolean b = offlinePlayer != null && offlinePlayer.isOnline();
            try {
                final String s2 = "{command:";
                while (s.contains(s2) && s.contains("}")) {
                    final String substring = s.substring(s.indexOf(s2) + s2.length(), s.indexOf("}"));
                    final String[] split = substring.split(":");
                    if (split.length == 3) {
                        final String s3 = split[0];
                        final String s4 = split[1];
                        final String s5 = split[2];
                        try {
                            final Enums.HackType value = Enums.HackType.valueOf(s3);
                            final int intValue = (int)Integer.valueOf(s4);
                            final int intValue2 = (int)Integer.valueOf(s5);
                            final String[] a = Config.a(value, intValue);
                            if (a.length >= intValue2) {
                                s = s.replace(s2 + substring + "}", a[intValue2 - 1]);
                            }
                            else {
                                s = s.replace(s2 + substring + "}", "");
                            }
                        }
                        catch (Exception ex) {
                            s = s.replace(s2 + substring + "}", "");
                        }
                    }
                    else {
                        s = s.replace(s2 + substring + "}", "");
                    }
                }
                if (b) {
                    String substring2;
                    for (String s6 = "{vls:"; s.contains(s6) && s.contains("}"); s = s.replace(s6 + substring2 + "}", "")) {
                        substring2 = s.substring(s.indexOf(s6) + s6.length(), s.indexOf("}"));
                        boolean b2 = false;
                        for (final Enums.HackType hackType : Enums.HackType.values()) {
                            if (hackType.toString().toLowerCase().equalsIgnoreCase(substring2)) {
                                s = s.replace(s6 + substring2 + "}", String.valueOf(VL.a(SpartanBukkit.a(offlinePlayer.getUniqueId()), hackType)));
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2) {}
                    }
                }
            }
            catch (Exception ex2) {}
        }
        return s;
    }
    
    public static void a(final boolean b, final HashSet<Enums.HackType> set, final Enums.HackType hackType) {
        if (b) {
            if (!set.contains(hackType)) {
                set.add(hackType);
            }
        }
        else if (set.contains(hackType)) {
            set.remove(hackType);
        }
    }
    
    public static void add(final File file, final String s, final Object o) {
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception ex) {}
        }
        if (!loadConfiguration.contains(s)) {
            set(file, s, o);
        }
    }
    
    public static void set(final File file, final String s, final Object o) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception ex) {}
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        loadConfiguration.set(s, o);
        try {
            loadConfiguration.save(file);
        }
        catch (Exception ex2) {}
    }
    
    public static void add(final String s, final Object o) {
        if (!Register.plugin.getConfig().contains(s)) {
            set(s, o);
        }
    }
    
    public static void set(final String s, final Object o) {
        Register.plugin.getConfig().set(s, o);
        Register.plugin.saveConfig();
        Register.plugin.reloadConfig();
    }
    
    public static String decode(final String src) {
        return (src == null) ? "" : StringUtils.newStringUtf8(Base64.getDecoder().decode(src));
    }
    
    public static int c(final String pathname) {
        final File[] listFiles = new File(pathname).listFiles();
        int n = 0;
        if (listFiles != null) {
            final File[] array = listFiles;
            for (int length = array.length, i = 0; i < length; ++i) {
                if (array[i].isFile()) {
                    ++n;
                }
            }
        }
        return n;
    }
}
