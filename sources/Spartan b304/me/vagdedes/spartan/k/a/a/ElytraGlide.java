package me.vagdedes.spartan.k.a.a;

import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ElytraGlide
{
    public ElytraGlide() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (Register.v1_9) {
            final ItemStack chestplate = spartanPlayer.a().getChestplate();
            if (chestplate != null && chestplate.getDurability() < 432 && chestplate.getType() == Material.ELYTRA) {
                CooldownUtils.d(spartanPlayer, "elytra=has", 2);
            }
        }
    }
    
    public static boolean bc(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "elytra=has");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && spartanPlayer.getItemInHand().getType() == MaterialUtils.a("firework") && bc(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, "elytra=firework", 100);
        }
    }
    
    public static boolean bd(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "elytra=firework");
    }
}
