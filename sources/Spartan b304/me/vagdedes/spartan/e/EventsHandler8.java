package me.vagdedes.spartan.e;

import org.bukkit.event.world.ChunkUnloadEvent;
import me.vagdedes.spartan.k.c.ChunkManager;
import org.bukkit.event.world.ChunkLoadEvent;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.h.CheckProtection;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.Listener;

public class EventsHandler8 implements Listener
{
    public EventsHandler8() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final VehicleEnterEvent vehicleEnterEvent) {
        final Entity entered = vehicleEnterEvent.getEntered();
        if (entered instanceof Player) {
            final Player player = (Player)entered;
            if (NPC.is(player)) {
                return;
            }
            SpartanBukkit.a(player.getUniqueId()).a((Entity)vehicleEnterEvent.getVehicle());
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final VehicleExitEvent vehicleExitEvent) {
        final LivingEntity exited = vehicleExitEvent.getExited();
        if (exited instanceof Player) {
            final Player player = (Player)exited;
            if (NPC.is(player)) {
                return;
            }
            SpartanBukkit.a(player.getUniqueId()).a((Entity)null);
        }
    }
    
    @EventHandler
    private void a(final PlayerChangedWorldEvent playerChangedWorldEvent) {
        final Player player = playerChangedWorldEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        a.setWorld(player.getWorld());
        CheckProtection.f(a, 10);
    }
    
    @EventHandler
    private void a(final ChunkLoadEvent chunkLoadEvent) {
        ChunkManager.a(chunkLoadEvent.getWorld(), chunkLoadEvent.getChunk());
    }
    
    @EventHandler
    private void a(final ChunkUnloadEvent chunkUnloadEvent) {
        ChunkManager.b(chunkUnloadEvent.getWorld(), chunkUnloadEvent.getChunk());
    }
}
