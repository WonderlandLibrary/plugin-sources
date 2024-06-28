package me.vagdedes.spartan.c;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class CraftBook
{
    private static double c;
    
    public CraftBook() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("CraftBook");
        return compatibility.isEnabled() && (PluginUtils.exists("craftbook") || compatibility.c());
    }
    
    public static void reload() {
        CraftBook.c = 0.0;
    }
    
    public static double d() {
        if (!isLoaded()) {
            return 0.0;
        }
        if (CraftBook.c > 0.0) {
            return CraftBook.c;
        }
        final File file = new File("plugins/CraftBook/mechanisms.yml");
        if (file.exists()) {
            final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
            if (loadConfiguration != null) {
                final double double1 = loadConfiguration.getDouble("mechanics.BoatSpeedModifiers.max-speed");
                double c;
                if (double1 < 0.0) {
                    c = 0.1;
                }
                else {
                    c = double1 + 0.1;
                }
                return CraftBook.c = c;
            }
        }
        return 0.0;
    }
    
    static {
        CraftBook.c = 0.0;
    }
}
