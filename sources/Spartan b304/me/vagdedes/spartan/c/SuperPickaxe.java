package me.vagdedes.spartan.c;

import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class SuperPickaxe
{
    public SuperPickaxe() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("SuperPickaxe");
        return compatibility.isEnabled() && (PluginUtils.exists("SuperPickaxe") || PluginUtils.exists("SuperpickaxeReloaded") || compatibility.c());
    }
    
    public static boolean G(final SpartanPlayer spartanPlayer) {
        final Material type = spartanPlayer.getItemInHand().getType();
        return (type == Material.DIAMOND_PICKAXE || type == Material.IRON_PICKAXE || type == Material.STONE_PICKAXE || type == MaterialUtils.a("gold_pickaxe") || type == MaterialUtils.a("wood_pickaxe")) && isLoaded();
    }
}
