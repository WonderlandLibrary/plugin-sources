package me.vagdedes.spartan.e;

import me.vagdedes.spartan.checks.b.ReEnchant;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.entity.Entity;
import org.bukkit.Location;
import java.util.UUID;
import me.vagdedes.spartan.checks.movement.MorePackets;
import me.vagdedes.spartan.checks.movement.Fly;
import me.vagdedes.spartan.checks.movement.NoSlowdown;
import me.vagdedes.spartan.checks.movement.EntityMove;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.movement.Speed;
import me.vagdedes.spartan.checks.movement.Clip;
import me.vagdedes.spartan.checks.movement.BoatMove;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.movement.Jesus;
import me.vagdedes.spartan.checks.b.ChunkUpdates;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.checks.combat.b.PitchRate;
import me.vagdedes.spartan.checks.combat.b.YawRate;
import me.vagdedes.spartan.checks.combat.c.Comparison;
import me.vagdedes.spartan.checks.f.Nuker;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerCommandEvent;
import me.vagdedes.spartan.a.b.ChatProtection;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.Listener;

public class EventsHandler7 implements Listener
{
    private static final String str = "movement-listeners=";
    
    public EventsHandler7() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerToggleFlightEvent playerToggleFlightEvent) {
        final Player player = playerToggleFlightEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        final boolean flying = playerToggleFlightEvent.isFlying();
        UtilEvents.f(a, flying);
        a.setFlying(flying);
        a.setAllowFlight(player.getAllowFlight());
    }
    
    @EventHandler
    public void a(final PlayerChatTabCompleteEvent playerChatTabCompleteEvent) {
        final Player player = playerChatTabCompleteEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (a == null) {
            return;
        }
        if (ChatProtection.a(a, playerChatTabCompleteEvent.getChatMessage(), true)) {
            playerChatTabCompleteEvent.getTabCompletions().clear();
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final ServerCommandEvent serverCommandEvent) {
        if (ChatProtection.a(serverCommandEvent.getSender(), serverCommandEvent.getCommand())) {
            serverCommandEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final PlayerMoveEvent playerMoveEvent) {
        final Player player = playerMoveEvent.getPlayer();
        final UUID uniqueId = player.getUniqueId();
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 1);
        a.setFallDistance(a.getFallDistance());
        a.setSprinting(player.isSprinting());
        a.setSneaking(player.isSneaking());
        a.setWalkSpeed(player.getWalkSpeed());
        a.setFlySpeed(player.getFlySpeed());
        a.a(player.getOpenInventory());
        if (!MoveUtils.b(a) || playerMoveEvent.isCancelled()) {
            DoubleUtils.c(a, new String[] { "movement-listeners=ver", "movement-listeners=hor", "movement-listeners=air", "movement-listeners=rem" });
            return;
        }
        final Location from = playerMoveEvent.getFrom();
        final SpartanLocation spartanLocation = new SpartanLocation(uniqueId, from, 1);
        if (UtilEvents.bf(a)) {
            MoveUtils.a(player, from);
            DoubleUtils.c(a, new String[] { "movement-listeners=ver", "movement-listeners=hor", "movement-listeners=air", "movement-listeners=rem" });
            return;
        }
        final Location to = playerMoveEvent.getTo();
        final SpartanLocation spartanLocation2 = new SpartanLocation(uniqueId, to, 1);
        if (spartanLocation2.getWorld() != spartanLocation.getWorld() || ScheduleHandlers.a(a, player, spartanLocation2, spartanLocation)) {
            return;
        }
        final double n = spartanLocation2.getY() - spartanLocation.getY();
        final double b = MathUtils.b(spartanLocation2, spartanLocation);
        final double n2 = spartanLocation2.getY() - spartanLocation2.getBlockY();
        final double a2 = spartanLocation2.a(spartanLocation);
        final int q = PlayerData.q(a);
        Nuker.c(a, spartanLocation2, spartanLocation);
        Comparison.a(a, false);
        YawRate.a(a, to);
        PitchRate.a(a, to);
        if (!MoveUtils.a(playerMoveEvent)) {
            return;
        }
        if (Settings.canDo("Detections.run_asynchronously")) {
            final SpartanPlayer spartanPlayer;
            final SpartanLocation spartanLocation3;
            final SpartanLocation spartanLocation4;
            final double n3;
            final double n4;
            final double n5;
            Threads.a(a, () -> {
                ChunkUpdates.b(spartanPlayer, spartanLocation3, spartanLocation4);
                Jesus.a(spartanPlayer, spartanLocation3, spartanLocation4, n3, n4, n5);
                return;
            });
        }
        else {
            ChunkUpdates.b(a, spartanLocation2, spartanLocation);
            Jesus.a(a, spartanLocation2, spartanLocation, n, n2, b);
        }
        UndetectedMovement.r(a);
        UtilEvents.a(a, b, n, n2);
        if (Config.a(new Enums.HackType[] { Enums.HackType.BoatMove, Enums.HackType.Clip, Enums.HackType.NoSlowdown, Enums.HackType.MorePackets, Enums.HackType.Speed, Enums.HackType.Fly, Enums.HackType.IrregularMovements, Enums.HackType.EntityMove })) {
            if (DoubleUtils.a(a, new String[] { "movement-listeners=ver", "movement-listeners=hor", "movement-listeners=air", "movement-listeners=rem" })) {
                final double a3 = DoubleUtils.a(a, "movement-listeners=ver");
                final double a4 = DoubleUtils.a(a, "movement-listeners=hor");
                final int n6 = (int)DoubleUtils.a(a, "movement-listeners=air");
                final double a5 = DoubleUtils.a(a, "movement-listeners=rem");
                final Entity vehicle = a.getVehicle();
                final SpartanLocation spartanLocation5 = (vehicle != null) ? new SpartanLocation(vehicle.getLocation()) : null;
                final SpartanLocation a6 = a.a();
                final SpartanLocation b2 = a6.b().b(0.0, -1.0, 0.0);
                final boolean b3 = GroundUtils.K(a, a6) || GroundUtils.K(a, b2);
                final boolean ak = GroundUtils.ak(a);
                if (Settings.canDo("Detections.run_asynchronously")) {
                    final SpartanPlayer spartanPlayer2;
                    final SpartanLocation spartanLocation6;
                    final SpartanLocation spartanLocation7;
                    final double n7;
                    final double n8;
                    final double n9;
                    final double n10;
                    final double n11;
                    final int n12;
                    final int n13;
                    final double n14;
                    final boolean b4;
                    final boolean b5;
                    final SpartanLocation spartanLocation8;
                    final double n15;
                    Threads.a(a, () -> {
                        BoatMove.a(spartanPlayer2, spartanLocation6, spartanLocation7, n7, n8, n9, n10);
                        Clip.a(spartanPlayer2, spartanLocation6, spartanLocation7, n7, n8, n9, n11, n10, n12, n13, true);
                        Speed.a(spartanPlayer2, spartanLocation7, n7, n8, n12, n11, n10, n9, n14);
                        IrregularMovements.a(true, spartanPlayer2, spartanLocation6, spartanLocation7, n7, n11, n14, n10, n12, b4, b5);
                        EntityMove.a(spartanPlayer2, spartanLocation6, spartanLocation7, spartanLocation8, n7, n10);
                        NoSlowdown.b(spartanPlayer2, spartanLocation7, n7, n8, n9);
                        Fly.a(spartanPlayer2, spartanLocation6, spartanLocation7, n7, n11, n12, n10, n13);
                        MorePackets.a(spartanPlayer2, spartanLocation6, spartanLocation7, n7, n10, n15);
                        return;
                    });
                }
                else {
                    BoatMove.a(a, spartanLocation2, spartanLocation, n, b, a4, a3);
                    Clip.a(a, spartanLocation2, spartanLocation, n, b, a4, n2, a3, q, n6, true);
                    Speed.a(a, spartanLocation, n, b, q, n2, a3, a4, a5);
                    IrregularMovements.a(true, a, spartanLocation2, spartanLocation, n, n2, a5, a3, q, b3, ak);
                    EntityMove.a(a, spartanLocation2, spartanLocation, spartanLocation5, n, a3);
                    NoSlowdown.b(a, spartanLocation, n, b, a4);
                    Fly.a(a, spartanLocation2, spartanLocation, n, n2, q, a3, n6);
                    MorePackets.a(a, spartanLocation2, spartanLocation, n, a3, a2);
                }
            }
            DoubleUtils.a(a, "movement-listeners=ver", n);
            DoubleUtils.a(a, "movement-listeners=hor", b);
            DoubleUtils.a(a, "movement-listeners=air", q);
            DoubleUtils.a(a, "movement-listeners=rem", n2);
        }
        else {
            DoubleUtils.c(a, new String[] { "movement-listeners=ver", "movement-listeners=hor", "movement-listeners=air", "movement-listeners=rem" });
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void a(final EnchantItemEvent enchantItemEvent) {
        final Player enchanter = enchantItemEvent.getEnchanter();
        if (NPC.is(enchanter)) {
            return;
        }
        if (ReEnchant.a(SpartanBukkit.a(enchanter.getUniqueId()), enchantItemEvent.getItem())) {
            enchantItemEvent.setCancelled(true);
        }
    }
    
    private static /* synthetic */ void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7, final double n8, final boolean b, final boolean b2, final SpartanLocation spartanLocation3, final double n9) {
        BoatMove.a(spartanPlayer, spartanLocation, spartanLocation2, n, n2, n3, n4);
        Clip.a(spartanPlayer, spartanLocation, spartanLocation2, n, n2, n3, n5, n4, n6, n7, true);
        Speed.a(spartanPlayer, spartanLocation2, n, n2, n6, n5, n4, n3, n8);
        IrregularMovements.a(true, spartanPlayer, spartanLocation, spartanLocation2, n, n5, n8, n4, n6, b, b2);
        EntityMove.a(spartanPlayer, spartanLocation, spartanLocation2, spartanLocation3, n, n4);
        NoSlowdown.b(spartanPlayer, spartanLocation2, n, n2, n3);
        Fly.a(spartanPlayer, spartanLocation, spartanLocation2, n, n5, n6, n4, n7);
        MorePackets.a(spartanPlayer, spartanLocation, spartanLocation2, n, n4, n9);
    }
    
    private static /* synthetic */ void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3) {
        ChunkUpdates.b(spartanPlayer, spartanLocation, spartanLocation2);
        Jesus.a(spartanPlayer, spartanLocation, spartanLocation2, n, n2, n3);
    }
}
