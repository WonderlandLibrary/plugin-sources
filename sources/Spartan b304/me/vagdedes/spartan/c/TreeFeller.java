package me.vagdedes.spartan.c;

import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class TreeFeller
{
    public TreeFeller() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("TreeFeller");
        return compatibility.isEnabled() && (PluginUtils.exists("treefeller") || compatibility.c());
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        final Material type = spartanBlock.getType();
        if (isLoaded() || mcMMO.I(spartanPlayer)) {
            if (Register.v1_13) {
                if (type != Material.ACACIA_LOG && type != Material.BIRCH_LOG && type != Material.DARK_OAK_LOG && type != Material.JUNGLE_LOG && type != Material.OAK_LOG && type != Material.SPRUCE_LOG && type != Material.STRIPPED_ACACIA_LOG && type != Material.STRIPPED_BIRCH_LOG && type != Material.STRIPPED_DARK_OAK_LOG && type != Material.STRIPPED_JUNGLE_LOG && type != Material.STRIPPED_SPRUCE_LOG) {
                    if (type != Material.STRIPPED_OAK_LOG) {
                        return false;
                    }
                }
            }
            else if (type != Material.getMaterial("LOG") && type != Material.getMaterial("LOG_2")) {
                return false;
            }
            return true;
        }
        return false;
    }
}
