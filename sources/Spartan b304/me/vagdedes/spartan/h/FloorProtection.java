package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.f.SpartanPlayer;

public class FloorProtection
{
    public FloorProtection() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final EntityDamageEvent.DamageCause damageCause) {
        final boolean b = VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.d && VersionUtils.a() != VersionUtils.a.e;
        final boolean b2 = b && VersionUtils.a() != VersionUtils.a.f;
        final boolean b3 = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.5, 0.0);
        if (((damageCause == EntityDamageEvent.DamageCause.FIRE_TICK || damageCause == EntityDamageEvent.DamageCause.FALL) && b3 && !PunishUtils.bh(spartanPlayer) && !MoveUtils.an(spartanPlayer)) || damageCause == EntityDamageEvent.DamageCause.STARVATION || damageCause == EntityDamageEvent.DamageCause.LIGHTNING || damageCause == EntityDamageEvent.DamageCause.THORNS || damageCause == EntityDamageEvent.DamageCause.VOID || damageCause == EntityDamageEvent.DamageCause.POISON || damageCause == EntityDamageEvent.DamageCause.WITHER || (PlayerData.aX(spartanPlayer) && damageCause == EntityDamageEvent.DamageCause.SUFFOCATION)) {
            g(spartanPlayer, 10);
        }
        else if ((b && damageCause == EntityDamageEvent.DamageCause.HOT_FLOOR) || (b2 && damageCause == EntityDamageEvent.DamageCause.MAGIC) || damageCause == EntityDamageEvent.DamageCause.CONTACT) {
            g(spartanPlayer, 30);
        }
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof Firework) {
            g(spartanPlayer, 10);
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "floor=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "floor=protection", n);
    }
}
