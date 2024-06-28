package me.vagdedes.spartan.e;

import org.bukkit.inventory.Inventory;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.d.ManageConfiguration;
import me.vagdedes.spartan.d.LegitimatePlayers;
import me.vagdedes.spartan.d.HackerFinder;
import me.vagdedes.spartan.d.SuspectedPlayers;
import me.vagdedes.spartan.d.DebugMenu;
import me.vagdedes.spartan.d.PlayerInfo;
import me.vagdedes.spartan.d.SynMenu;
import me.vagdedes.spartan.d.ManageChecks;
import me.vagdedes.spartan.d.MainMenu;
import me.vagdedes.spartan.checks.c.InventoryClicks;
import me.vagdedes.spartan.checks.c.ImpossibleInventory;
import me.vagdedes.spartan.k.d.StringUtils;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import me.vagdedes.spartan.h.Piston;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.EventPriority;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.c.ItemDrops;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.Register;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.checks.combat.a.b.CARotations;
import me.vagdedes.spartan.checks.combat.a.b.CADirection;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.checks.combat.a.c.CACombatEvent;
import org.bukkit.event.Listener;

public class EventsHandler4 implements Listener
{
    public EventsHandler4() {
        super();
    }
    
    @EventHandler
    private void a(final CACombatEvent caCombatEvent) {
        final Player damager = caCombatEvent.getDamager();
        if (NPC.is(damager)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(damager.getUniqueId());
        final SpartanPlayer a2 = SpartanBukkit.a(caCombatEvent.a().getUniqueId());
        final CAEventListeners.b a3 = caCombatEvent.a();
        CADirection.a(a, a2, a3);
        CARotations.a(a, a2, a3);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerDropItemEvent playerDropItemEvent) {
        final Player player = playerDropItemEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 4);
        a.a(player.getInventory());
        a.a(player.getOpenInventory());
        a.setItemInHand(player.getItemInHand());
        if (Register.v1_13) {
            a.h(player.hasCooldown(player.getItemInHand().getType()));
        }
        if (Settings.canDo("Detections.run_asynchronously")) {
            Threads.a(a, () -> ItemDrops.b(a));
        }
        else {
            ItemDrops.b(a);
        }
        MiningNotifications.b(a, playerDropItemEvent.getItemDrop());
        if (HackPrevention.a(a, Enums.HackType.ItemDrops, true)) {
            playerDropItemEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public static void a(final PlayerPickupItemEvent playerPickupItemEvent) {
        final Player player = playerPickupItemEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        a.a(player.getInventory());
        a.a(player.getOpenInventory());
        a.setItemInHand(player.getItemInHand());
        if (Register.v1_13) {
            a.h(player.hasCooldown(player.getItemInHand().getType()));
        }
        MiningNotifications.a(a, playerPickupItemEvent.getItem());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final BlockPistonExtendEvent blockPistonExtendEvent) {
        Piston.a(blockPistonExtendEvent.getBlock(), blockPistonExtendEvent.getBlocks());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final InventoryClickEvent inventoryClickEvent) {
        final ItemStack currentItem = inventoryClickEvent.getCurrentItem();
        if (currentItem != null && currentItem.getType() != Material.AIR) {
            final Player player = (Player)inventoryClickEvent.getWhoClicked();
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 4);
            final ClickType click = inventoryClickEvent.getClick();
            final Inventory inventory = inventoryClickEvent.getInventory();
            final String s = Register.v1_13 ? StringUtils.getClearColorString(player.getOpenInventory().getTitle()) : player.getOpenInventory().getTitle();
            if (Register.v1_13) {
                a.h(player.hasCooldown(player.getItemInHand().getType()));
            }
            a.a(player.getInventory());
            a.a(player.getOpenInventory());
            a.setItemInHand(player.getItemInHand());
            ImpossibleInventory.a(a, currentItem, click);
            InventoryClicks.a(a, currentItem, click);
            if (MainMenu.a(a, currentItem, s, click) | ManageChecks.a(a, currentItem, s, click) | SynMenu.c(a, s) | PlayerInfo.a(a, currentItem, s, inventory.getContents()) | DebugMenu.a(a, currentItem, s) | SuspectedPlayers.a(a, currentItem, s) | HackerFinder.a(a, currentItem, s) | LegitimatePlayers.a(a, currentItem, s) | ManageConfiguration.a(a, currentItem, s, click, inventoryClickEvent.getSlot()) | HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.ImpossibleInventory, Enums.HackType.InventoryClicks }, true)) {
                inventoryClickEvent.setCancelled(true);
            }
        }
    }
    
    private static /* synthetic */ void B(final SpartanPlayer spartanPlayer) {
        ItemDrops.b(spartanPlayer);
    }
}
