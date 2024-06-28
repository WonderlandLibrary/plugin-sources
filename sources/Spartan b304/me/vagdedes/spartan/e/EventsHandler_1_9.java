package me.vagdedes.spartan.e;

import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.checks.b.IllegalElytraPacket;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.Listener;

public class EventsHandler_1_9 implements Listener
{
    public EventsHandler_1_9() {
        super();
    }
    
    @EventHandler
    private void a(final EntityToggleGlideEvent entityToggleGlideEvent) {
        final Entity entity = entityToggleGlideEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
            final boolean gliding = entityToggleGlideEvent.isGliding();
            IllegalElytraPacket.a(a, gliding);
            a.setGliding(gliding);
            a.a(player.getEyeHeight());
        }
    }
}
