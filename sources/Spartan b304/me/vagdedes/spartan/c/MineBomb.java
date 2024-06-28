package me.vagdedes.spartan.c;

import java.util.Iterator;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.Material;
import java.util.HashSet;

public class MineBomb
{
    private static final HashSet<Material> e;
    
    public MineBomb() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("MineBomb");
        return compatibility.isEnabled() && (PluginUtils.exists("MineBomb") || compatibility.c());
    }
    
    public static void reload() {
        MineBomb.e.clear();
    }
    
    public static boolean B(final SpartanPlayer spartanPlayer) {
        if (isLoaded()) {
            if (C(spartanPlayer)) {
                return true;
            }
            final File file = new File("plugins/MineBomb/config.yml");
            if (file.exists()) {
                final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
                if (loadConfiguration != null) {
                    for (final String s : loadConfiguration.getKeys(false)) {
                        if (s.endsWith(".item")) {
                            final Material material = Material.getMaterial(loadConfiguration.getString(s));
                            if (material == null || MineBomb.e.contains(material)) {
                                continue;
                            }
                            MineBomb.e.add(material);
                        }
                    }
                    return C(spartanPlayer);
                }
            }
        }
        return false;
    }
    
    private static boolean C(final SpartanPlayer spartanPlayer) {
        if (MineBomb.e.size() > 0) {
            final Iterator<Material> iterator = MineBomb.e.iterator();
            while (iterator.hasNext()) {
                if (spartanPlayer.getItemInHand().getType() == iterator.next()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        e = new HashSet<Material>();
    }
}
