package me.vagdedes.spartan.k.a.a;

import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanPlayer;

public class TridentUse
{
    public static final int ticks = 160;
    
    public TridentUse() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if (Register.v1_13 && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && (spartanPlayer.getWorld().isThundering() || spartanPlayer.getWorld().hasStorm()) && spartanPlayer.getItemInHand().getType() == Material.TRIDENT) {
            CooldownUtils.d(spartanPlayer, "trident", 160);
        }
    }
}
