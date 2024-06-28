package me.vagdedes.spartan.e;

import org.bukkit.event.world.WorldUnloadEvent;
import me.vagdedes.spartan.k.c.ChunkManager;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.HumanEntity;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.Listener;

public class EventsHandler9 implements Listener
{
    public EventsHandler9() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final InventoryOpenEvent inventoryOpenEvent) {
        final HumanEntity player = inventoryOpenEvent.getPlayer();
        if (player instanceof Player) {
            final Player player2 = (Player)player;
            if (NPC.is(player2)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player2.getUniqueId());
            if (Register.v1_13) {
                a.h(player2.hasCooldown(player2.getItemInHand().getType()));
            }
            a.a(player2.getInventory());
            a.a(player2.getOpenInventory());
        }
    }
    
    @EventHandler
    private void a(final InventoryCloseEvent inventoryCloseEvent) {
        final HumanEntity player = inventoryCloseEvent.getPlayer();
        if (player instanceof Player) {
            final Player player2 = (Player)player;
            if (NPC.is(player2)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player2.getUniqueId());
            if (a == null) {
                return;
            }
            if (Register.v1_13) {
                a.h(player2.hasCooldown(player2.getItemInHand().getType()));
            }
            a.a(player2.getInventory());
            a.a(player2.getOpenInventory());
        }
    }
    
    @EventHandler
    private void a(final WorldLoadEvent worldLoadEvent) {
        ChunkManager.b(worldLoadEvent.getWorld());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final WorldUnloadEvent worldUnloadEvent) {
        ChunkManager.a(worldUnloadEvent.getWorld());
    }
}
