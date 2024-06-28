package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.c.PunishUtils;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.f.SpartanPlayer;

public class FalseFallDamage
{
    public FalseFallDamage() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final EntityDamageEvent.DamageCause damageCause) {
        return !PunishUtils.bh(spartanPlayer) && VL.o(spartanPlayer) > 0 && !EnderPearl.E(spartanPlayer) && damageCause == EntityDamageEvent.DamageCause.FALL && !Explosion.E(spartanPlayer) && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.5, 0.0);
    }
}
