package me.vagdedes.spartan.e;

import org.bukkit.event.player.PlayerAnimationEvent;
import me.vagdedes.spartan.checks.combat.VelocityCheck;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.c.SmashHit;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.vagdedes.spartan.checks.combat.c.BlockRaytrace;
import me.vagdedes.spartan.checks.combat.c.HitTime;
import me.vagdedes.spartan.checks.combat.c.TargetedArea;
import me.vagdedes.spartan.checks.combat.c.Precision;
import me.vagdedes.spartan.checks.combat.c.YawMovement;
import me.vagdedes.spartan.checks.combat.c.Stability;
import me.vagdedes.spartan.checks.combat.c.Rotations;
import me.vagdedes.spartan.checks.combat.c.RapidHits;
import me.vagdedes.spartan.checks.combat.c.Modulo;
import me.vagdedes.spartan.checks.combat.c.ImpossibleHits;
import me.vagdedes.spartan.checks.combat.c.HitsPerSec;
import me.vagdedes.spartan.checks.combat.c.HitConsistency;
import me.vagdedes.spartan.checks.combat.c.HitBox;
import me.vagdedes.spartan.checks.combat.c.Direction;
import me.vagdedes.spartan.checks.combat.c.Comparison;
import me.vagdedes.spartan.checks.combat.c.Combined;
import me.vagdedes.spartan.checks.combat.c.AimConsistency;
import me.vagdedes.spartan.checks.combat.c.AimAccuracy;
import me.vagdedes.spartan.checks.combat.c.Angle;
import me.vagdedes.spartan.checks.combat.c.PitchMovement;
import me.vagdedes.spartan.checks.combat.c.EntityRaytrace;
import me.vagdedes.spartan.checks.combat.c.Accuracy;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.checks.combat.HitReach;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.features.c.Debug;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.checks.combat.b.AccuracyData;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.checks.combat.b.HitRatio;
import me.vagdedes.spartan.checks.combat.b.SavedLocation;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.checks.combat.Criticals;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.c.MythicMobs;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.checks.c.ItemDrops;
import me.vagdedes.spartan.c.Essentials;
import me.vagdedes.spartan.a.b.ChatProtection;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.a.CommandHandlers;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;

public class EventsHandler6 implements Listener
{
    public EventsHandler6() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent) {
        final Player player = playerCommandPreprocessEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (a == null) {
            return;
        }
        final String message = playerCommandPreprocessEvent.getMessage();
        if (playerCommandPreprocessEvent.isCancelled()) {
            if (CommandHandlers.a(a, message)) {
                playerCommandPreprocessEvent.setCancelled(true);
            }
        }
        else {
            Teleport.k(a, message);
            if (ChatProtection.a(a, message, false)) {
                playerCommandPreprocessEvent.setCancelled(true);
            }
            else {
                Essentials.e(a, message);
                ItemDrops.a(message);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerKickEvent playerKickEvent) {
        final Player player = playerKickEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        CommandHandlers.a(SpartanBukkit.a(player.getUniqueId()), playerKickEvent);
    }
    
    public static void a(final Player player, final SpartanPlayer spartanPlayer, final Entity entity, final double n, final EntityDamageEvent.DamageCause damageCause, final boolean b) {
        if ((spartanPlayer != null && spartanPlayer.getPlayer().equals(entity)) || !CombatUtils.d(entity) || mcMMO.L(spartanPlayer) || (entity instanceof Player && SpartanBukkit.a(entity.getUniqueId()) == null)) {
            return;
        }
        if (MythicMobs.c(entity)) {
            Damage.g(spartanPlayer, 40);
            return;
        }
        if (!b) {
            UtilEvents.a(player, damageCause, entity);
        }
        if (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK && !NPC.D(spartanPlayer)) {
            final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
            final double b2 = MathUtils.b(spartanPlayer.a(), spartanLocation);
            if (b2 > 6.0) {
                return;
            }
            if (!b) {
                Criticals.b(spartanPlayer);
                NoSwing.a(spartanPlayer, entity);
                CAEventListeners.a(spartanPlayer, entity);
                SavedLocation.b(spartanPlayer);
                HitRatio.a(spartanPlayer, entity);
                CombatHeuristics.b(spartanPlayer, entity, b2);
                AccuracyData.b(spartanPlayer, entity);
                NoHitDelay.b(spartanPlayer, entity);
                if (Debug.size() > 0) {
                    Debug.g(spartanPlayer, "entity-type: " + entity + ", rotations: " + CombatUtils.e(spartanPlayer, entity) + ", direction: " + CombatUtils.f(spartanPlayer, entity) + ", angle: " + CombatUtils.h(spartanPlayer, entity) + ", distance: " + MoveUtils.a(spartanPlayer.a(), spartanLocation) + ", horizontal-distance: " + b2 + ", vertical-distance: " + MathUtils.c(spartanPlayer.a(), spartanLocation));
                }
            }
            if (!b || Settings.canDo("allow_cancelled_hit_checking") || PlayerData.au(spartanPlayer)) {
                HitReach.a(spartanPlayer, entity, n);
                if (CombatHeuristics.a(spartanPlayer, Enums.HackType.KillAura, entity)) {
                    final double a = HitReach.a(spartanPlayer, entity);
                    final int a2 = CombatHeuristics.a(spartanPlayer, a);
                    final int c = PlayerData.c(spartanPlayer, a);
                    Accuracy.a(spartanPlayer, entity);
                    EntityRaytrace.a(spartanPlayer, entity, b2, c);
                    PitchMovement.a(spartanPlayer, entity, b2, c);
                    Angle.a(spartanPlayer, entity, b2, c);
                    AimAccuracy.a(spartanPlayer, entity, b2, a2);
                    AimConsistency.a(spartanPlayer, entity, b2, c);
                    Combined.a(spartanPlayer, entity, b2);
                    Comparison.a(spartanPlayer, entity, b2);
                    Direction.a(spartanPlayer, entity, b2, a2);
                    HitBox.a(spartanPlayer, entity, b2, c);
                    HitConsistency.b(spartanPlayer);
                    HitsPerSec.b(spartanPlayer);
                    ImpossibleHits.a(spartanPlayer, entity);
                    Modulo.a(spartanPlayer, entity);
                    RapidHits.a(spartanPlayer, entity, n);
                    Rotations.a(spartanPlayer, entity, b2, c);
                    Stability.a(spartanPlayer, b2, c);
                    YawMovement.a(spartanPlayer, entity, b2, c);
                    Precision.a(spartanPlayer, entity, b2, a2);
                    TargetedArea.a(spartanPlayer, entity, b2, a2, n);
                    HitTime.b(spartanPlayer, entity);
                    BlockRaytrace.a(spartanPlayer, entity, c);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void b(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!SmashHit.isEnabled()) {
            final Entity damager = entityDamageByEntityEvent.getDamager();
            if (damager instanceof Player) {
                final Player player = (Player)damager;
                if (NPC.e(player)) {
                    return;
                }
                final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 2);
                if (NPC.D(a) || MoveUtils.k(a) <= 6.0 || MoveUtils.i(a) <= 6.0) {
                    a(player, a, entityDamageByEntityEvent.getEntity(), entityDamageByEntityEvent.getDamage(), entityDamageByEntityEvent.getCause(), entityDamageByEntityEvent.isCancelled());
                    if (HackPrevention.a(a, new Enums.HackType[] { Enums.HackType.KillAura, Enums.HackType.Criticals, Enums.HackType.NoSwing, Enums.HackType.CombatAnalysis, Enums.HackType.HitReach }, true)) {
                        entityDamageByEntityEvent.setCancelled(true);
                    }
                }
                else {
                    entityDamageByEntityEvent.setCancelled(true);
                }
            }
        }
    }
    
    public static void a(final Entity entity, final Entity obj, final EntityDamageEvent.DamageCause damageCause) {
        if (obj instanceof Player && !entity.equals(obj)) {
            final Player player = (Player)obj;
            if (NPC.e(player)) {
                return;
            }
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 2);
            if (!MythicMobs.c(entity)) {
                UtilEvents.a(a, entity, damageCause);
                if (!NPC.D(a)) {
                    if (damageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                        FloorProtection.e(a, entity);
                        Explosion.e(a, entity);
                    }
                    else if (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || (Register.v1_9 && damageCause == EntityDamageEvent.DamageCause.DRAGON_BREATH)) {
                        Damage.e(a, entity);
                    }
                    VelocityCheck.a(a, entity, damageCause);
                }
            }
            else if (!NPC.D(a)) {
                Damage.g(a, 40);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void c(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!SmashHit.isEnabled()) {
            final Entity damager = entityDamageByEntityEvent.getDamager();
            final Entity entity = entityDamageByEntityEvent.getEntity();
            final EntityDamageEvent.DamageCause cause = entityDamageByEntityEvent.getCause();
            if (Damage.a(damager, entity, cause)) {
                entityDamageByEntityEvent.setCancelled(true);
            }
            else {
                a(damager, entity, cause);
            }
        }
    }
    
    @EventHandler
    private void a(final PlayerAnimationEvent playerAnimationEvent) {
        final Player player = playerAnimationEvent.getPlayer();
        if (NPC.is(player)) {
            return;
        }
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 2);
        NoSwing.a(a, playerAnimationEvent.getAnimationType());
        Comparison.a(a, true);
    }
}
