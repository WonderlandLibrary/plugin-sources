package me.vagdedes.spartan.e;

import me.vagdedes.spartan.h.a.CancelAfterViolation;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.event.EventPriority;
import me.vagdedes.spartan.h.Velocity;
import me.vagdedes.spartan.c.NoHitDelay;
import org.bukkit.event.player.PlayerVelocityEvent;
import me.vagdedes.spartan.checks.movement.Fly;
import org.bukkit.event.player.PlayerRespawnEvent;
import me.vagdedes.spartan.h.a.DeathAndRespawn;
import me.vagdedes.spartan.checks.movement.NoFall;
import me.vagdedes.spartan.checks.f.ImpossibleActions;
import me.vagdedes.spartan.checks.e.AutoRespawn;
import org.bukkit.event.entity.PlayerDeathEvent;
import me.vagdedes.spartan.d.LegitimatePlayers;
import me.vagdedes.spartan.d.HackerFinder;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.features.c.ReconnectCooldown;
import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.checks.combat.a.a.CATraining;
import me.vagdedes.spartan.checks.combat.b.SavedLocation;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.checks.combat.c.Comparison;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.checks.combat.c.HitTime;
import me.vagdedes.spartan.checks.b.ChunkUpdates;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.checks.combat.Criticals;
import me.vagdedes.spartan.checks.combat.VelocityCheck;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.checks.b.PingSpoof;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.movement.EntityMove;
import me.vagdedes.spartan.checks.movement.MorePackets;
import me.vagdedes.spartan.checks.movement.Sprint;
import me.vagdedes.spartan.checks.movement.BoatMove;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.d.MainMenu;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.features.e.DeveloperReport;
import me.vagdedes.spartan.d.SuspectedPlayers;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.c.IPUtils;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class EventsHandler1 implements Listener
{
    public EventsHandler1() {
        super();
    }
    
    @EventHandler
    private void a(final PlayerJoinEvent playerJoinEvent) {
        final Player player = playerJoinEvent.getPlayer();
        if (IPUtils.g(player)) {
            playerJoinEvent.setJoinMessage((String)null);
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player);
        NPC.I(a);
        final Player player2;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Register.plugin, () -> {
            if (player2 != null && player2.isOnline()) {
                Settings.b(player2);
                SuspectedPlayers.e(player2);
                DeveloperReport.e(player2);
                SearchEngine.c(player2);
                MainMenu.e(player2);
            }
            return;
        }, 5L);
        LatencyUtils.Q(a);
    }
    
    @EventHandler
    private void a(final PlayerQuitEvent playerQuitEvent) {
        final Player player = playerQuitEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer b = SpartanBukkit.b(player.getUniqueId());
        if (b == null) {
            return;
        }
        BoatMove.a(b);
        Sprint.a(b);
        MorePackets.a(b);
        EntityMove.a(b);
        IrregularMovements.a(b);
        PingSpoof.a(b);
        NoSwing.a(b);
        VelocityCheck.a(b);
        Criticals.a(b);
        CombatAnalysis.a(b);
        ChunkUpdates.a(b);
        HitTime.a(b);
        UndetectedMovement.a(b);
        Comparison.a(b);
        TimeBetweenClicks.a(b);
        SavedLocation.a(b);
        CATraining.a(b);
        NPC.a(b);
        PermissionUtils.a(b);
        DoubleUtils.a(b);
        MillisUtils.a(b);
        MoveUtils.a(b);
        IPUtils.a(b);
        ScheduleHandlers.a(b);
        CooldownUtils.a(b);
        AttemptUtils.a(b);
        GroundUtils.a(b);
        EnderPearl.a(b);
        ClientSidedBlock.a(b);
        PerformanceOptimizer.a(b);
        FalsePositiveDetection.a(b);
        ReconnectCooldown.a(b);
        HackPrevention.a(b);
        VL.e(b, false);
        HackerFinder.a(b);
        LegitimatePlayers.a(b);
    }
    
    @EventHandler
    private void a(final PlayerDeathEvent playerDeathEvent) {
        final Player entity = playerDeathEvent.getEntity();
        if (NPC.is(entity)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
        MorePackets.j(a);
        AutoRespawn.j(a);
        VelocityCheck.j(a);
        ImpossibleActions.j(a);
        NoFall.x(a);
        MoveUtils.L(a);
        DeathAndRespawn.b(a);
        a.e(true);
        a.setSleeping(false);
        a.setHealth(entity.getHealth());
    }
    
    @EventHandler
    private void a(final PlayerRespawnEvent playerRespawnEvent) {
        final Player player = playerRespawnEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        MorePackets.j(a);
        Fly.u(a);
        VelocityCheck.j(a);
        a.e(false);
        a.setSleeping(false);
        a.setHealth(player.getMaxHealth());
        MoveUtils.L(a);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final PlayerVelocityEvent playerVelocityEvent) {
        final Player player = playerVelocityEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        final boolean cancelled = playerVelocityEvent.isCancelled();
        if (cancelled) {
            VelocityCheck.j(a);
        }
        NoHitDelay.z(a);
        Velocity.a(a, cancelled);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final SpartanLocation spartanLocation, final String s, final boolean b, final boolean b2) {
        int n = 0;
        int n2 = 1;
        if (s != null) {
            for (final String s2 : s.split(" ")) {
                if (MathUtils.validDouble(s2)) {
                    final double doubleValue = (double)Double.valueOf(s2);
                    if (n == 0 && doubleValue > 1.0) {
                        n = 1;
                        CancelAfterViolation.F(spartanPlayer);
                    }
                    if (n2 != 0 && doubleValue > 16.0) {
                        n2 = 0;
                    }
                    if (n != 0 && n2 == 0) {
                        break;
                    }
                }
            }
        }
        new HackPrevention(spartanPlayer, hackType, s, spartanLocation, 0, b, 0.0, b2 && n2 != 0);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final String s) {
        a(spartanPlayer, hackType, null, s, false, true);
    }
    
    private static /* synthetic */ void h(final Player player) {
        if (player != null && player.isOnline()) {
            Settings.b(player);
            SuspectedPlayers.e(player);
            DeveloperReport.e(player);
            SearchEngine.c(player);
            MainMenu.e(player);
        }
    }
}
