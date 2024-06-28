package me.vagdedes.spartan.checks.b;

import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.HackPrevention;
import org.bukkit.Material;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class IllegalElytraPacket
{
    private static final Enums.HackType a;
    
    public IllegalElytraPacket() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final boolean b) {
        final ItemStack chestplate = spartanPlayer.a().getChestplate();
        if (b && (chestplate == null || chestplate.getType() != Material.ELYTRA || chestplate.getDurability() >= 432)) {
            new HackPrevention(spartanPlayer, IllegalElytraPacket.a, "t: illegal elytra packet");
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
    }
}
