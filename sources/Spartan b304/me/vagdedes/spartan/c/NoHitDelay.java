package me.vagdedes.spartan.c;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.a.a.Compatibility;

public class NoHitDelay
{
    private static final int time = 40;
    
    public NoHitDelay() {
        super();
    }
    
    public static boolean isLoaded() {
        return new Compatibility("NoHitDelay").isEnabled();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (isLoaded() && entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (a.getMaximumNoDamageTicks() < 20 || new Compatibility("NoHitDelay").c()) {
                CooldownUtils.d(spartanPlayer, "no-hit-delay=protection", 40);
                CooldownUtils.d(a, "no-hit-delay=protection", 40);
            }
        }
    }
    
    public static void z(final SpartanPlayer spartanPlayer) {
        if (isLoaded() && (spartanPlayer.getMaximumNoDamageTicks() < 20 || new Compatibility("NoHitDelay").c())) {
            CooldownUtils.d(spartanPlayer, "no-hit-delay=protection", 40);
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "no-hit-delay=protection");
    }
}
