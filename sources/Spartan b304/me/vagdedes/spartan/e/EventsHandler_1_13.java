package me.vagdedes.spartan.e;

import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Velocity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.a.PlayerSwim;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.Listener;

public class EventsHandler_1_13 implements Listener
{
    public EventsHandler_1_13() {
        super();
    }
    
    @EventHandler
    private void a(final EntityToggleSwimEvent entityToggleSwimEvent) {
        final Entity entity = entityToggleSwimEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
            final boolean swimming = entityToggleSwimEvent.isSwimming();
            PlayerSwim.a(a, swimming);
            a.setSwimming(swimming);
            a.a(player.getEyeHeight());
        }
    }
    
    @EventHandler
    private void a(final PlayerRiptideEvent playerRiptideEvent) {
        final Player player = playerRiptideEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (a.getItemInHand().getEnchantmentLevel(Enchantment.RIPTIDE) > 3) {
            Velocity.g(a, 100);
        }
        else {
            Explosion.g(a, 60);
        }
    }
}
