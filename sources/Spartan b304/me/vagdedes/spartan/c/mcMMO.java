package me.vagdedes.spartan.c;

import java.util.Iterator;
import java.util.UUID;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.c.a.mcMMO_background;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class mcMMO
{
    public mcMMO() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("mcMMO_background");
        return compatibility.isEnabled() && (PluginUtils.exists("mcmmo") || compatibility.c());
    }
    
    public static boolean H(final SpartanPlayer spartanPlayer) {
        return isLoaded() && mcMMO_background.H(spartanPlayer);
    }
    
    public static boolean J(final SpartanPlayer spartanPlayer) {
        if (!isLoaded()) {
            return false;
        }
        final Material type = spartanPlayer.getItemInHand().getType();
        return PlayerData.c(spartanPlayer, 6.0) > 0 && (PlayerData.au(spartanPlayer) || PlayerData.av(spartanPlayer)) && (type == Material.DIAMOND_AXE || type == MaterialUtils.a("gold_axe") || type == Material.IRON_AXE || type == Material.STONE_AXE || type == MaterialUtils.a("wood_axe"));
    }
    
    public static boolean K(final SpartanPlayer spartanPlayer) {
        return isLoaded() && spartanPlayer.getItemInHand() == null;
    }
    
    public static boolean I(final SpartanPlayer spartanPlayer) {
        return isLoaded() && mcMMO_background.I(spartanPlayer);
    }
    
    public static boolean L(final SpartanPlayer spartanPlayer) {
        if (isLoaded()) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            for (final Entity entity : spartanPlayer.getNearbyEntities(12.0, 12.0, 12.0)) {
                if (entity instanceof Wolf && ((Wolf)entity).getOwner() != null && ((Wolf)entity).getOwner().getUniqueId().equals(uniqueId)) {
                    return true;
                }
            }
        }
        return false;
    }
}
