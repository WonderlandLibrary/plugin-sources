package me.vagdedes.spartan.e;

import org.bukkit.event.block.Action;
import org.bukkit.block.Block;
import me.vagdedes.spartan.h.BlockBreak;
import me.vagdedes.spartan.h.ItemTeleporter;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.h.a.InteractSpam;
import me.vagdedes.spartan.h.a.FenceClick;
import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.checks.combat.b.AccuracyData;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.k.a.a.TridentUse;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import me.vagdedes.spartan.checks.combat.c.HitTime;
import me.vagdedes.spartan.checks.combat.c.Comparison;
import me.vagdedes.spartan.checks.combat.FastBow;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.checks.combat.FastClicks;
import me.vagdedes.spartan.checks.f.GhostHand;
import me.vagdedes.spartan.checks.c.ItemDrops;
import me.vagdedes.spartan.checks.movement.NoSlowdown;
import me.vagdedes.spartan.checks.e.FastEat;
import me.vagdedes.spartan.checks.f.FastBreak;
import me.vagdedes.spartan.checks.f.ImpossibleActions;
import me.vagdedes.spartan.checks.f.BlockReach;
import me.vagdedes.spartan.f.SpartanBlock;
import org.bukkit.event.player.PlayerInteractEvent;
import me.vagdedes.spartan.checks.b.SignCrasher;
import org.bukkit.event.block.SignChangeEvent;
import java.util.UUID;
import me.vagdedes.spartan.features.c.ReconnectCooldown;
import me.vagdedes.spartan.a.b.BanManagement;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.h.a.InvisibleBlock;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.h.a.FalseFallDamage;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.h.Velocity;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.checks.movement.Clip;
import me.vagdedes.spartan.checks.movement.Fly;
import me.vagdedes.spartan.checks.movement.ElytraMove;
import me.vagdedes.spartan.checks.movement.NoFall;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.b.ChatProtection;
import me.vagdedes.spartan.features.c.StaffChat;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.b.Chat;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.Listener;

public class EventsHandler3 implements Listener
{
    public EventsHandler3() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final AsyncPlayerChatEvent asyncPlayerChatEvent) {
        final Player player = asyncPlayerChatEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (a == null) {
            return;
        }
        final String message = asyncPlayerChatEvent.getMessage();
        if (Settings.canDo("Detections.run_asynchronously")) {
            Threads.a(a, () -> Chat.e(a, message));
        }
        else {
            Chat.e(a, message);
        }
        if ((StaffChat.c(a, message) | ChatProtection.b(a, message)) || HackPrevention.a(a, Enums.HackType.Exploits, false)) {
            asyncPlayerChatEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void b(final EntityDamageEvent entityDamageEvent) {
        final Entity entity = entityDamageEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 2);
            final EntityDamageEvent.DamageCause cause = entityDamageEvent.getCause();
            final boolean cancelled = entityDamageEvent.isCancelled();
            a.setLastDamage(player.getLastDamage());
            if (cancelled) {
                NoFall.a(a, cause, entityDamageEvent.getDamage(), true);
            }
            else {
                if (cause == EntityDamageEvent.DamageCause.FALL) {
                    NoFall.t(a);
                    ElytraMove.t(a);
                    Fly.b(a, entityDamageEvent.getDamage());
                }
                else {
                    if (cause == EntityDamageEvent.DamageCause.SUFFOCATION) {
                        Clip.t(a);
                    }
                    UndetectedMovement.q(a);
                }
                if (cause != EntityDamageEvent.DamageCause.FIRE_TICK) {
                    IrregularMovements.t(a);
                }
                FloorProtection.b(a, cause);
                Velocity.q(a);
                Damage.a(a, cause);
                Explosion.b(a, cause);
                NoFall.a(a, cause, entityDamageEvent.getDamage(), false);
                if (FalseFallDamage.a(a, cause)) {
                    entityDamageEvent.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    private void a(final PlayerLoginEvent playerLoginEvent) {
        final Player player = playerLoginEvent.getPlayer();
        final UUID uniqueId = player.getUniqueId();
        InvisibleBlock.d(uniqueId);
        CheckProtection.a(uniqueId, 5);
        BanManagement.a(player, playerLoginEvent);
        ReconnectCooldown.a(player, playerLoginEvent);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final SignChangeEvent signChangeEvent) {
        final Player player = signChangeEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (Settings.canDo("Detections.run_asynchronously")) {
            Threads.a(a, () -> SignCrasher.a(a, signChangeEvent.getLines()));
        }
        else {
            SignCrasher.a(a, signChangeEvent.getLines());
        }
        if (HackPrevention.a(a, Enums.HackType.Exploits, true)) {
            signChangeEvent.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final PlayerInteractEvent playerInteractEvent) {
        final Player player = playerInteractEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 3);
        final Block clickedBlock = playerInteractEvent.getClickedBlock();
        final Action action = playerInteractEvent.getAction();
        final boolean cancelled = playerInteractEvent.isCancelled();
        final boolean b = clickedBlock != null;
        if (b) {
            final SpartanBlock spartanBlock = new SpartanBlock(clickedBlock);
            if (Settings.canDo("Detections.run_asynchronously")) {
                final SpartanPlayer spartanPlayer;
                final SpartanBlock spartanBlock2;
                final Action action2;
                Threads.a(a, () -> {
                    BlockReach.b(spartanPlayer, spartanBlock2);
                    ImpossibleActions.a(spartanPlayer, action2, spartanBlock2);
                    return;
                });
            }
            else {
                BlockReach.b(a, spartanBlock);
                ImpossibleActions.a(a, action, spartanBlock);
            }
            FastBreak.a(a, action, spartanBlock);
            FastEat.a(a, spartanBlock, action);
            NoSlowdown.b(a, spartanBlock, action);
            ItemDrops.a(a, spartanBlock, action);
            GhostHand.a(a, spartanBlock, action);
        }
        else {
            FastClicks.b(a, action);
            FastEat.a(a, null, action);
        }
        NoSwing.a(a, action);
        FastBow.a(a, action);
        if (Settings.canDo("Detections.run_asynchronously")) {
            final SpartanPlayer spartanPlayer2;
            final Action action3;
            Threads.a(a, () -> {
                Comparison.a(spartanPlayer2, true);
                HitTime.b(spartanPlayer2, action3);
                return;
            });
        }
        else {
            Comparison.a(a, true);
            HitTime.b(a, action);
        }
        ElytraGlide.a(a, action);
        TridentUse.a(a, action);
        TimeBetweenClicks.b(a, action);
        AccuracyData.a(a, action);
        CpsCounter.a(a, action);
        if (!cancelled) {
            if (b) {
                final SpartanBlock spartanBlock3 = new SpartanBlock(clickedBlock);
                if (FenceClick.a(a, spartanBlock3, action) | InteractSpam.a(a, spartanBlock3, action)) {
                    playerInteractEvent.setCancelled(true);
                }
                Building.a(a, spartanBlock3, action);
            }
            ItemTeleporter.a(a, action);
        }
        if (b) {
            BlockBreak.a(a, new SpartanBlock(clickedBlock), action);
        }
        if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.FastClicks, Enums.HackType.GhostHand }, true)) {
            playerInteractEvent.setCancelled(true);
        }
    }
    
    private static /* synthetic */ void c(final SpartanPlayer spartanPlayer, final Action action) {
        Comparison.a(spartanPlayer, true);
        HitTime.b(spartanPlayer, action);
    }
    
    private static /* synthetic */ void c(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        BlockReach.b(spartanPlayer, spartanBlock);
        ImpossibleActions.a(spartanPlayer, action, spartanBlock);
    }
    
    private static /* synthetic */ void a(final SpartanPlayer spartanPlayer, final SignChangeEvent signChangeEvent) {
        SignCrasher.a(spartanPlayer, signChangeEvent.getLines());
    }
    
    private static /* synthetic */ void i(final SpartanPlayer spartanPlayer, final String s) {
        Chat.e(spartanPlayer, s);
    }
}
