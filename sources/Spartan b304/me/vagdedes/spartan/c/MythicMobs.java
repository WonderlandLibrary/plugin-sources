package me.vagdedes.spartan.c;

import me.vagdedes.spartan.f.SpartanInventory;
import me.vagdedes.spartan.f.SpartanPlayer;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Iterator;
import me.vagdedes.spartan.k.d.StringUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.inventory.ItemStack;
import java.util.HashSet;

public class MythicMobs
{
    private static boolean enabled;
    private static final HashSet<ItemStack> f;
    
    public MythicMobs() {
        super();
    }
    
    public static void a(final boolean b) {
        final Compatibility compatibility = new Compatibility("MythicMobs");
        if (!MythicMobs.enabled && compatibility.isEnabled() && (PluginUtils.exists("mythicmobs") || compatibility.c())) {
            MythicMobs.enabled = true;
        }
        if (MythicMobs.enabled) {
            MythicMobs.f.clear();
            if (b) {
                final File[] listFiles = new File("/plugins/MythicMobs/Items/").listFiles();
                if (listFiles != null) {
                    for (final File file : listFiles) {
                        if (file.isFile() && file.getName().endsWith(".yml")) {
                            final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
                            for (final String s : loadConfiguration.getKeys(false)) {
                                if (loadConfiguration.contains(s + ".Id") && loadConfiguration.contains(s + ".Display")) {
                                    final Material material = Material.getMaterial(loadConfiguration.getString(s + ".Id").toUpperCase());
                                    if (material == null) {
                                        continue;
                                    }
                                    final ItemStack e = new ItemStack(material);
                                    final ItemMeta itemMeta = e.getItemMeta();
                                    itemMeta.setDisplayName(StringUtils.getClearColorString(loadConfiguration.getString(s + ".Display")));
                                    e.setItemMeta(itemMeta);
                                    MythicMobs.f.add(e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public static boolean c(final Entity entity) {
        return MythicMobs.enabled && new BukkitAPIHelper().isMythicMob(entity);
    }
    
    public static boolean D(final SpartanPlayer spartanPlayer) {
        if (MythicMobs.enabled) {
            final SpartanInventory a = spartanPlayer.a();
            final ItemStack[] armorContents = a.getArmorContents();
            for (int length = armorContents.length, i = 0; i < length; ++i) {
                if (b(armorContents[i])) {
                    return true;
                }
            }
            return b(a.getItemInHand());
        }
        return false;
    }
    
    public static boolean b(final ItemStack itemStack) {
        if (itemStack != null) {
            for (final ItemStack itemStack2 : MythicMobs.f) {
                if (itemStack2.getType() == itemStack.getType() && itemStack.getItemMeta() != null && StringUtils.getClearColorString(itemStack.getItemMeta().getDisplayName()).equals(itemStack2.getItemMeta().getDisplayName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        MythicMobs.enabled = false;
        f = new HashSet<ItemStack>();
    }
}
