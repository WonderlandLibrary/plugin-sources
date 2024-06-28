package me.vagdedes.spartan.k.a.a;

import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.h.GameModeProtection;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.BowProtection;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Velocity;
import me.vagdedes.spartan.h.Damage;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.e.ScheduleHandlers;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.k.a.CombatUtils;
import java.util.UUID;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanPlayer;

public class UtilEvents
{
    private static final int amount = 40;
    private static final double radius = 0.7;
    
    public UtilEvents() {
        super();
    }
    
    public static boolean aQ(final SpartanPlayer spartanPlayer) {
        return PlayerData.b(spartanPlayer, 40, 0.7) || !CooldownUtils.g(spartanPlayer, "extremely=pushed");
    }
    
    public static boolean be(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "ground");
    }
    
    public static boolean a(final Player player, final EntityDamageEvent.DamageCause damageCause, final Entity entity) {
        boolean b = false;
        final UUID uniqueId = player.getUniqueId();
        if (!CooldownUtils.b(uniqueId, "teleport-cooldown")) {
            b = true;
        }
        else if (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            CooldownUtils.b(uniqueId, "hit_entity", 50);
        }
        if (entity instanceof Player && damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            int n = 1;
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (MillisUtils.a(uniqueId, "fight=delay=" + a.getUniqueId()) <= 5000L) {
                n += AttemptUtils.a(uniqueId, "fight=hits=" + a.getUniqueId());
            }
            AttemptUtils.a(uniqueId, "fight=hits=" + a.getUniqueId(), n);
            MillisUtils.b(uniqueId, "fight=delay=" + a.getUniqueId());
            MillisUtils.o(a, "fight=delay=" + player.getUniqueId());
        }
        return b;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final EntityDamageEvent.DamageCause damageCause) {
        if (CombatUtils.d(entity) && (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || damageCause == EntityDamageEvent.DamageCause.PROJECTILE)) {
            CooldownUtils.d(spartanPlayer, "combat", 100);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        if (n3 == Values.b(n3, 5)) {
            DoubleUtils.a(spartanPlayer, "move-utils=rem", n3);
        }
        if (PlayerData.b(spartanPlayer, 40, 0.7)) {
            if (PlayerData.aS(spartanPlayer)) {
                CooldownUtils.d(spartanPlayer, "extremely=pushed", 160);
            }
            else {
                CooldownUtils.d(spartanPlayer, "extremely=pushed", 40);
            }
        }
        if (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            CooldownUtils.d(spartanPlayer, "ground", 1);
            if (n < 0.29 && spartanPlayer.getVehicle() == null) {
                PlayerData.M(spartanPlayer);
            }
        }
        if (MoveUtils.ap(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, "chasing", 40);
        }
        if (MoveUtils.b(spartanPlayer)) {
            DoubleUtils.a(spartanPlayer, "move-utils=nms", n);
            DoubleUtils.a(spartanPlayer, "move-utils=nms-vertical", n2);
        }
        else {
            DoubleUtils.c(spartanPlayer, new String[] { "move-utils=nms", "move-utils=nms-vertical" });
        }
        ScheduleHandlers.r(spartanPlayer);
        if (n2 == 0.41999) {
            MillisUtils.o(spartanPlayer, "move-utils=last-jump");
        }
        if (spartanPlayer.isSneaking()) {
            CooldownUtils.d(spartanPlayer, "move-utils=sneaking-count", 10);
        }
        else {
            final int a = CooldownUtils.a(spartanPlayer, "move-utils=sneaking-count");
            if (a > 0 && a < 10) {
                CooldownUtils.j(spartanPlayer, "move-utils=sneaking-count");
                AttemptUtils.b(spartanPlayer, "move-utils=sneaking-count", 300);
            }
        }
    }
    
    public static void O(final SpartanPlayer spartanPlayer) {
        CooldownUtils.j(spartanPlayer, "walking");
        CooldownUtils.j(spartanPlayer, "sprint");
        DoubleUtils.c(spartanPlayer, new String[] { "move-utils=nms", "move-utils=nms-vertical", "move-utils=rem" });
        if (spartanPlayer.getVehicle() != null) {
            PlayerData.M(spartanPlayer);
        }
    }
    
    public static boolean bf(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "teleport-cooldown")) {
            PunishUtils.P(spartanPlayer);
            PlayerData.M(spartanPlayer);
            VL.a(spartanPlayer, Enums.HackType.NoFall, 3);
            VL.a(spartanPlayer, Enums.HackType.Sprint, 3);
            CooldownUtils.j(spartanPlayer, "walking");
            CooldownUtils.j(spartanPlayer, "sprint");
            DoubleUtils.j(spartanPlayer, "move-utils=nms");
            return true;
        }
        return false;
    }
    
    public static void f(final SpartanPlayer spartanPlayer, final boolean b) {
        if (!b) {
            PlayerData.N(spartanPlayer);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final PlayerTeleportEvent.TeleportCause teleportCause) {
        if (teleportCause != PlayerTeleportEvent.TeleportCause.UNKNOWN || AttemptUtils.b(spartanPlayer, "unknown-teleport-spam", 1) >= 2) {
            PlayerData.N(spartanPlayer);
        }
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final double n, final double n2) {
        if (Damage.E(spartanPlayer) || Velocity.E(spartanPlayer) || PlayerData.aw(spartanPlayer) || Explosion.E(spartanPlayer) || FloorProtection.E(spartanPlayer) || BowProtection.E(spartanPlayer) || PlayerData.b(spartanPlayer) > 0.0f || SelfHit.E(spartanPlayer) || GameModeProtection.E(spartanPlayer) || spartanPlayer.isSprinting() || PlayerData.aV(spartanPlayer) || PlayerData.aS(spartanPlayer) || PlayerData.az(spartanPlayer) || PlayerData.aF(spartanPlayer) || Liquid.e(spartanPlayer) <= 400L) {
            CooldownUtils.c(spartanPlayer, new String[] { "walking", "sprinting" });
            return;
        }
        final int n3 = 2;
        if (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.av(spartanPlayer) && !BlockUtils.f(spartanPlayer, spartanPlayer.a()) && !BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0))) {
            if (n >= 0.215 && n < 0.2203) {
                CooldownUtils.d(spartanPlayer, "walking", n3);
            }
            else {
                CooldownUtils.j(spartanPlayer, "walking");
            }
            if (n > 0.28 && n < 0.287) {
                CooldownUtils.d(spartanPlayer, "sprint", n3);
            }
            else {
                CooldownUtils.j(spartanPlayer, "sprint");
            }
        }
        else {
            CooldownUtils.j(spartanPlayer, "walking");
            CooldownUtils.j(spartanPlayer, "sprint");
        }
        if (MoveUtils.b(n2) && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.5, 0.0))) {
            if (n > 0.24 && n < 0.29 && !spartanPlayer.isSprinting()) {
                CooldownUtils.d(spartanPlayer, "walk-jumping", n3);
            }
            if ((n > 0.5 && n < 0.68) || spartanPlayer.isSprinting()) {
                CooldownUtils.d(spartanPlayer, "sprint-jumping", n3);
            }
        }
        else {
            CooldownUtils.j(spartanPlayer, "walk-jumping");
            CooldownUtils.j(spartanPlayer, "sprint-jumping");
        }
    }
    
    public static void f(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (hackType == Enums.HackType.BoatMove || hackType == Enums.HackType.EntityMove) {
            PlayerData.M(spartanPlayer);
        }
    }
}
