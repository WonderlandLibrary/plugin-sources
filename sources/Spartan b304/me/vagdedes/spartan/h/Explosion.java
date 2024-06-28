package me.vagdedes.spartan.h;

import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Explosion
{
    public Explosion() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final EntityDamageEvent.DamageCause damageCause) {
        if (damageCause == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || damageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            e(spartanPlayer, null);
        }
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity == null || (!(entity instanceof Firework) && !(entity instanceof Creeper))) {
            g(spartanPlayer, PlayerData.aT(spartanPlayer) ? 160 : 80);
            Damage.g(spartanPlayer, 0);
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final double n) {
        if (PlayerData.q(spartanPlayer) <= 12) {
            CooldownUtils.j(spartanPlayer, "explosion=protection=falling");
        }
        else if (n < 0.0 && E(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, "explosion=protection=falling", Settings.b("explosion_detection_cooldown") * 20);
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "explosion=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int a) {
        CooldownUtils.d(spartanPlayer, "explosion=protection", Math.min(a, Settings.b("explosion_detection_cooldown") * 20));
    }
    
    public static boolean X(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "explosion=protection=falling");
    }
}
