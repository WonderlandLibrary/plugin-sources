package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;

public class InteractSpam
{
    private static final String str = "interact-spam";
    
    public InteractSpam() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (action == Action.RIGHT_CLICK_BLOCK && spartanBlock != null && BlockUtils.F(null, spartanBlock.a())) {
            if (!CooldownUtils.g(spartanPlayer, "interact-spam")) {
                return true;
            }
            int b = Settings.b("Protections.interactions_per_tick");
            if (b < 5) {
                b = 5;
            }
            if (AttemptUtils.b(spartanPlayer, "interact-spam", 1) >= b) {
                CooldownUtils.d(spartanPlayer, "interact-spam", 20);
                return true;
            }
        }
        return false;
    }
}
