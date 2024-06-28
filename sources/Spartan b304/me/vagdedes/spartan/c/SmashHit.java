package me.vagdedes.spartan.c;

import org.bukkit.entity.Damageable;
import me.vagdedes.spartan.h.Damage;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.e.EventsHandler6;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import com.frash23.smashhit.AsyncPreDamageEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class SmashHit implements Listener
{
    private static boolean enabled;
    
    public SmashHit() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("SmashHit");
        if (!SmashHit.enabled && compatibility.isEnabled() && (PluginUtils.exists("smashhit") || compatibility.c())) {
            Register.enable(new SmashHit(), (Class)SmashHit.class);
            SmashHit.enabled = true;
        }
    }
    
    public static boolean isEnabled() {
        return SmashHit.enabled;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final AsyncPreDamageEvent asyncPreDamageEvent) {
        final Player damager = asyncPreDamageEvent.getDamager();
        if (damager instanceof Player) {
            final Player player = (Player)damager;
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 2);
            EventsHandler6.a(player, a, (Entity)asyncPreDamageEvent.getEntity(), asyncPreDamageEvent.getDamage(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, asyncPreDamageEvent.isCancelled());
            if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.KillAura, Enums.HackType.Criticals, Enums.HackType.NoSwing, Enums.HackType.CombatAnalysis }, true)) {
                asyncPreDamageEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void b(final AsyncPreDamageEvent asyncPreDamageEvent) {
        final Player damager = asyncPreDamageEvent.getDamager();
        final Damageable entity = asyncPreDamageEvent.getEntity();
        final EntityDamageEvent.DamageCause entity_ATTACK = EntityDamageEvent.DamageCause.ENTITY_ATTACK;
        if (Damage.a((Entity)damager, (Entity)entity, entity_ATTACK)) {
            asyncPreDamageEvent.setCancelled(true);
        }
        else {
            EventsHandler6.a((Entity)damager, (Entity)entity, entity_ATTACK);
        }
    }
    
    static {
        SmashHit.enabled = false;
    }
}
