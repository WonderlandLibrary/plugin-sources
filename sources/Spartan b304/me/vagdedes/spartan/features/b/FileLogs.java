package me.vagdedes.spartan.features.b;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.Register;
import java.util.Random;
import me.vagdedes.spartan.k.d.TimeUtils;
import java.sql.Timestamp;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class FileLogs
{
    private static int id;
    private static File a;
    private static YamlConfiguration a;
    
    public FileLogs() {
        super();
    }
    
    public static void disable() {
        FileLogs.id = 0;
        if (FileLogs.a != null && FileLogs.a != null) {
            try {
                FileLogs.a.save(FileLogs.a);
            }
            catch (Exception ex) {}
        }
    }
    
    public static void a(final String s, final boolean b) {
        a(null, s, b, null, null, false, null);
    }
    
    public static void a(final Player player, final String s, final boolean b, final Material material, final Enums.HackType hackType, final boolean b2, final String s2) {
        if (Settings.canDo("log_console")) {
            Bukkit.getConsoleSender().sendMessage(s);
        }
        if (b) {
            final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            final String string = TimeUtils.d(timestamp) + " " + TimeUtils.a(timestamp);
            final boolean canDo = Settings.canDo("log_mysql");
            final boolean canDo2 = Settings.canDo("log_file");
            if (canDo || canDo2) {
                SearchEngine.a("(" + new Random(99L) + ")[" + string + "]", s);
            }
            if (canDo) {
                SQLLogs.a(player, s, material, hackType, b2, s2);
            }
            if (canDo2) {
                if (FileLogs.a == null) {
                    int n;
                    File a;
                    for (n = 1, a = new File(Register.plugin.getDataFolder() + "/logs/log" + n + ".yml"); a.exists(); a = new File(Register.plugin.getDataFolder() + "/logs/log" + n + ".yml")) {
                        ++n;
                    }
                    a.getParentFile().mkdirs();
                    FileLogs.a = a;
                    FileLogs.a = YamlConfiguration.loadConfiguration(FileLogs.a);
                    FileLogs.id = 0;
                }
                else {
                    int b3 = Settings.b("log_file_limit");
                    final int m = VL.m();
                    if (b3 < m) {
                        b3 = m;
                    }
                    ++FileLogs.id;
                    FileLogs.a.set("(" + FileLogs.id + ")[" + string + "]", (Object)s);
                    final boolean b4 = FileLogs.id >= b3;
                    if (FileLogs.id % 10 != 0) {
                        if (!b4) {
                            return;
                        }
                    }
                    try {
                        FileLogs.a.save(FileLogs.a);
                    }
                    catch (Exception ex) {}
                    if (b4) {
                        FileLogs.a = null;
                        FileLogs.a = null;
                        FileLogs.id = 0;
                    }
                }
            }
        }
    }
    
    static {
        FileLogs.id = 0;
        FileLogs.a = null;
        FileLogs.a = null;
    }
}
