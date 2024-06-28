package me.vagdedes.spartan.e;

import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.api.PlayerViolationEvent;
import me.vagdedes.spartan.checks.f.FastPlace;
import me.vagdedes.spartan.h.BlockPlace;
import me.vagdedes.spartan.h.Building;
import org.bukkit.event.block.BlockPlaceEvent;
import me.vagdedes.spartan.h.BlockBreak;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.features.c.Debug;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.checks.f.GhostHand;
import me.vagdedes.spartan.checks.f.FastBreak;
import me.vagdedes.spartan.checks.f.Nuker;
import me.vagdedes.spartan.checks.f.Liquids;
import me.vagdedes.spartan.checks.f.BlockReach;
import me.vagdedes.spartan.checks.f.ImpossibleActions;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.f.SpartanBlock;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.checks.e.FastHeal;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.HumanEntity;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.movement.NoSlowdown;
import me.vagdedes.spartan.checks.e.FastEat;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.Listener;

public class EventsHandler5 implements Listener
{
    public EventsHandler5() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final FoodLevelChangeEvent foodLevelChangeEvent) {
        final HumanEntity entity = foodLevelChangeEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 1);
            final int foodLevel = foodLevelChangeEvent.getFoodLevel();
            a.setFoodLevel(foodLevel);
            if (Settings.canDo("Detections.run_asynchronously")) {
                final SpartanPlayer spartanPlayer;
                final int n;
                Threads.a(a, () -> {
                    FastEat.b(spartanPlayer, n);
                    NoSlowdown.b(spartanPlayer, n);
                    return;
                });
            }
            else {
                FastEat.b(a, foodLevel);
                NoSlowdown.b(a, foodLevel);
            }
            if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.FastEat, Enums.HackType.NoSlowdown }, true)) {
                foodLevelChangeEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final EntityRegainHealthEvent entityRegainHealthEvent) {
        final Entity entity = entityRegainHealthEvent.getEntity();
        if (entity instanceof Player) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 5);
            if (Settings.canDo("Detections.run_asynchronously")) {
                Threads.a(a, () -> FastHeal.a(a, entityRegainHealthEvent.getRegainReason()));
            }
            else {
                FastHeal.a(a, entityRegainHealthEvent.getRegainReason());
            }
            if (HackPrevention.a(a, Enums.HackType.FastHeal, true)) {
                entityRegainHealthEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final BlockBreakEvent blockBreakEvent) {
        final Player player = blockBreakEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 3);
        final SpartanBlock spartanBlock = new SpartanBlock(blockBreakEvent.getBlock());
        final boolean cancelled = blockBreakEvent.isCancelled();
        if (!cancelled) {
            if (Settings.canDo("Detections.run_asynchronously")) {
                final SpartanPlayer spartanPlayer;
                final SpartanBlock spartanBlock2;
                Threads.a(a, () -> {
                    NoSwing.a(spartanPlayer, spartanBlock2);
                    ImpossibleActions.a(spartanPlayer, spartanBlock2);
                    BlockReach.a(spartanPlayer, spartanBlock2);
                    NoSlowdown.a(spartanPlayer, spartanBlock2);
                    Liquids.a(spartanPlayer, spartanBlock2);
                    Nuker.c(spartanPlayer, spartanBlock2);
                    FastBreak.c(spartanPlayer, spartanBlock2);
                    return;
                });
            }
            else {
                NoSwing.a(a, spartanBlock);
                ImpossibleActions.a(a, spartanBlock);
                BlockReach.a(a, spartanBlock);
                NoSlowdown.a(a, spartanBlock);
                Liquids.a(a, spartanBlock);
                Nuker.c(a, spartanBlock);
                FastBreak.c(a, spartanBlock);
            }
            GhostHand.a(a, spartanBlock, blockBreakEvent.isCancelled());
            MiningNotifications.a(a, spartanBlock);
            if (Debug.size() > 0) {
                Debug.h(a, "type: block-breaking, block-type: " + spartanBlock.getType() + ", block-distance: " + MoveUtils.a(a.a(), spartanBlock.a()));
            }
            if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.NoSwing, Enums.HackType.BlockReach, Enums.HackType.ImpossibleActions, Enums.HackType.Liquids, Enums.HackType.Nuker, Enums.HackType.FastBreak, Enums.HackType.GhostHand }, true)) {
                blockBreakEvent.setCancelled(true);
            }
        }
        BlockBreak.c(a, cancelled);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final BlockPlaceEvent blockPlaceEvent) {
        final Player player = blockPlaceEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanBlock spartanBlock = new SpartanBlock(blockPlaceEvent.getBlock());
        if (player.getWorld() != spartanBlock.getWorld()) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 3);
        final SpartanBlock spartanBlock2 = new SpartanBlock(blockPlaceEvent.getBlockAgainst());
        final boolean cancelled = blockPlaceEvent.isCancelled();
        Building.b(a, spartanBlock, cancelled);
        BlockPlace.d(a, cancelled);
        if (!cancelled) {
            if (Settings.canDo("Detections.run_asynchronously")) {
                final SpartanPlayer spartanPlayer;
                final SpartanBlock spartanBlock3;
                final SpartanBlock spartanBlock4;
                Threads.a(a, () -> {
                    ImpossibleActions.d(spartanPlayer, spartanBlock3);
                    FastPlace.c(spartanPlayer, spartanBlock3);
                    BlockReach.a(spartanPlayer, spartanBlock3, spartanBlock4);
                    Liquids.a(spartanPlayer, spartanBlock3, spartanBlock4);
                    return;
                });
            }
            else {
                ImpossibleActions.d(a, spartanBlock);
                FastPlace.c(a, spartanBlock);
                BlockReach.a(a, spartanBlock, spartanBlock2);
                Liquids.a(a, spartanBlock, spartanBlock2);
            }
            if (Debug.size() > 0) {
                Debug.h(a, "type: block-placing, block-type: " + spartanBlock.getType() + ", block-distance: " + a.a().a(spartanBlock.a()) + ", block-against-type: " + spartanBlock2.getType());
            }
            if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.FastPlace, Enums.HackType.BlockReach, Enums.HackType.Liquids, Enums.HackType.ImpossibleActions }, true)) {
                blockPlaceEvent.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    private void a(final PlayerViolationEvent playerViolationEvent) {
        final Player player = playerViolationEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        final int violation = playerViolationEvent.getViolation();
        final Enums.HackType hackType = playerViolationEvent.getHackType();
        CombatHeuristics.a(a, hackType);
        UtilEvents.f(a, hackType);
        PerformanceOptimizer.c(a, violation);
        SearchEngine.c(a, violation);
    }
    
    private static /* synthetic */ void b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final SpartanBlock spartanBlock2) {
        ImpossibleActions.d(spartanPlayer, spartanBlock);
        FastPlace.c(spartanPlayer, spartanBlock);
        BlockReach.a(spartanPlayer, spartanBlock, spartanBlock2);
        Liquids.a(spartanPlayer, spartanBlock, spartanBlock2);
    }
    
    private static /* synthetic */ void e(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        NoSwing.a(spartanPlayer, spartanBlock);
        ImpossibleActions.a(spartanPlayer, spartanBlock);
        BlockReach.a(spartanPlayer, spartanBlock);
        NoSlowdown.a(spartanPlayer, spartanBlock);
        Liquids.a(spartanPlayer, spartanBlock);
        Nuker.c(spartanPlayer, spartanBlock);
        FastBreak.c(spartanPlayer, spartanBlock);
    }
    
    private static /* synthetic */ void a(final SpartanPlayer spartanPlayer, final EntityRegainHealthEvent entityRegainHealthEvent) {
        FastHeal.a(spartanPlayer, entityRegainHealthEvent.getRegainReason());
    }
    
    private static /* synthetic */ void e(final SpartanPlayer spartanPlayer, final int n) {
        FastEat.b(spartanPlayer, n);
        NoSlowdown.b(spartanPlayer, n);
    }
}
