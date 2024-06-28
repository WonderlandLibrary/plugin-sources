package me.vagdedes.spartan.checks.b;

import org.bukkit.Material;
import me.vagdedes.spartan.system.VL;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ReEnchant
{
    private static final Enums.HackType a;
    
    public ReEnchant() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack) {
        return !VL.b(spartanPlayer, ReEnchant.a, false) && itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants();
    }
    
    static {
        a = Enums.HackType.Exploits;
    }
}
