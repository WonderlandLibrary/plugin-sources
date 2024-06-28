package me.vagdedes.spartan.e;

import me.vagdedes.spartan.h.GameModeProtection;
import org.bukkit.GameMode;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.Velocity;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.combat.FastBow;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.EventPriority;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.checks.movement.NoSlowdown;
import me.vagdedes.spartan.Register;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import me.vagdedes.spartan.h.ItemTeleporter;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.a.InvisibleBlock;
import me.vagdedes.spartan.checks.combat.b.PitchRate;
import me.vagdedes.spartan.checks.combat.b.YawRate;
import me.vagdedes.spartan.checks.combat.c.Stability;
import me.vagdedes.spartan.checks.combat.c.Modulo;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.checks.combat.c.Combined;
import me.vagdedes.spartan.checks.combat.c.AimConsistency;
import me.vagdedes.spartan.checks.combat.VelocityCheck;
import me.vagdedes.spartan.checks.combat.Criticals;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.movement.Speed;
import me.vagdedes.spartan.checks.movement.MorePackets;
import me.vagdedes.spartan.checks.movement.NoFall;
import me.vagdedes.spartan.checks.movement.Sprint;
import me.vagdedes.spartan.checks.movement.BoatMove;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.Listener;

public class EventsHandler2 implements Listener
{
    private static final String str = "movement-listeners=";
    
    public EventsHandler2() {
        super();
    }
    
    @EventHandler
    private void a(final PlayerTeleportEvent playerTeleportEvent) {
        final Player player = playerTeleportEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final UUID uniqueId = player.getUniqueId();
        final SpartanLocation spartanLocation = new SpartanLocation(uniqueId, playerTeleportEvent.getTo(), 1);
        final SpartanLocation spartanLocation2 = new SpartanLocation(uniqueId, playerTeleportEvent.getFrom(), 1);
        if (spartanLocation.getWorld() != spartanLocation2.getWorld()) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 1);
        if (a == null) {
            return;
        }
        final PlayerTeleportEvent.TeleportCause cause = playerTeleportEvent.getCause();
        final double a2 = spartanLocation.a(spartanLocation2);
        if ((playerTeleportEvent.getCause() != PlayerTeleportEvent.TeleportCause.UNKNOWN || Teleport.aa(a)) && !PunishUtils.bg(a)) {
            DoubleUtils.j(a, "movement-listeners=ver");
            DoubleUtils.j(a, "movement-listeners=hor");
            DoubleUtils.j(a, "movement-listeners=air");
            BoatMove.a(a);
            Sprint.a(a);
            NoFall.a(a);
            MorePackets.i(a);
            Speed.i(a);
            IrregularMovements.i(a);
            Criticals.a(a);
            VelocityCheck.i(a);
            AimConsistency.i(a);
            Combined.i(a);
        }
        UndetectedMovement.a(a, cause);
        Modulo.n(a);
        Stability.n(a);
        YawRate.n(a);
        PitchRate.n(a);
        InvisibleBlock.d(a, a2);
        Teleport.a(a, cause, a2);
        EnderPearl.a(a, spartanLocation, cause);
        ItemTeleporter.a(a, cause);
        UtilEvents.b(a, cause);
        if (HackPrevention.a(a, Enums.HackType.Exploits, true)) {
            playerTeleportEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerItemHeldEvent playerItemHeldEvent) {
        final Player player = playerItemHeldEvent.getPlayer();
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
        NoSlowdown.m(a);
        NoSwing.m(a);
        CombatHeuristics.m(a);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final EntityShootBowEvent entityShootBowEvent) {
        final LivingEntity entity = entityShootBowEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entityShootBowEvent.getEntity();
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), -1);
            final float force = entityShootBowEvent.getForce();
            final Entity projectile = entityShootBowEvent.getProjectile();
            if (NoSlowdown.t(a)) {
                entityShootBowEvent.setCancelled(true);
            }
            else {
                if (Settings.canDo("Detections.run_asynchronously")) {
                    Threads.a(a, () -> FastBow.a(a, force));
                }
                else {
                    FastBow.a(a, force);
                }
                SelfHit.a((Entity)entity, projectile);
                if (HackPrevention.a(a, Enums.HackType.FastBow, true)) {
                    entityShootBowEvent.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void b(final PlayerFishEvent playerFishEvent) {
        final Entity caught = playerFishEvent.getCaught();
        if (caught instanceof Player) {
            final Player player = (Player)caught;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
            final SpartanPlayer a2 = SpartanBukkit.a(playerFishEvent.getPlayer().getUniqueId());
            UndetectedMovement.p(a);
            Velocity.i(a, 5);
            FishingHook.e(a, a2);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerGameModeChangeEvent playerGameModeChangeEvent) {
        final Player player = playerGameModeChangeEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        final GameMode gameMode = a.getGameMode();
        final GameMode newGameMode = playerGameModeChangeEvent.getNewGameMode();
        if (gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE && (newGameMode == GameMode.CREATIVE || (Register.v1_8 && newGameMode == GameMode.SPECTATOR))) {
            GameModeProtection.b(a);
        }
        a.setGameMode(playerGameModeChangeEvent.getNewGameMode());
        a.setAllowFlight(player.getAllowFlight());
    }
    
    private static /* synthetic */ void c(final SpartanPlayer spartanPlayer, final float n) {
        FastBow.a(spartanPlayer, n);
    }
}
