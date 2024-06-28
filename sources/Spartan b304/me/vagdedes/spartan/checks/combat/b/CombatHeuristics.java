package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.a.CombatUtils;
import org.bukkit.entity.LivingEntity;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Monster;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.checks.combat.HitReach;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CombatHeuristics
{
    private static final int ticks = 15;
    
    public CombatHeuristics() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Entity entity, final double n) {
        if ((spartanPlayer.getGameMode() != GameMode.ADVENTURE && spartanPlayer.getGameMode() != GameMode.SURVIVAL) || MoveUtils.ar(spartanPlayer)) {
            return;
        }
        final double f = LagManagement.f(spartanPlayer, HitReach.a(spartanPlayer, entity) + 0.4);
        if (n >= ((f > 6.0) ? 6.0 : f) && AttemptUtils.b(spartanPlayer, "combat=distance", 88) >= ((MoveUtils.i(spartanPlayer) > 0.1 && (VersionUtils.a() == VersionUtils.a.l || VersionUtils.a() == VersionUtils.a.c)) ? 3 : 2)) {
            CooldownUtils.d(spartanPlayer, "combat=distance", 15);
        }
        AttemptUtils.b(spartanPlayer, "combat=last-hits", 200);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (hackType == Enums.HackType.Speed) {
            CooldownUtils.d(spartanPlayer, "combat=movement", 15);
        }
        else if (hackType == Enums.HackType.HitReach) {
            CooldownUtils.d(spartanPlayer, "combat=distance", 15);
        }
        else if (hackType == Enums.HackType.NoSwing && VL.a(spartanPlayer, hackType) >= 1) {
            CooldownUtils.d(spartanPlayer, "combat=animation", 15);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final double n, final double n2) {
        if (!MoveUtils.b(spartanPlayer) || MoveUtils.ar(spartanPlayer)) {
            return;
        }
        if (YawRate.a(spartanPlayer) >= 50.0f && AttemptUtils.b(spartanPlayer, "combat=yaw", 10) >= 5) {
            CooldownUtils.d(spartanPlayer, "combat=yaw", 15);
        }
        if (PitchRate.a(spartanPlayer) >= 40.0f && AttemptUtils.b(spartanPlayer, "combat=pitch", 10) >= 5) {
            CooldownUtils.d(spartanPlayer, "combat=pitch", 15);
        }
        if (PlayerData.aM(spartanPlayer) || PlayerData.aN(spartanPlayer) || (n > 0.34 && n2 > 0.164)) {
            CooldownUtils.d(spartanPlayer, "combat=movement", 15);
        }
    }
    
    public static int h(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "combat=last-hits");
    }
    
    public static void m(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "combat=animation=cancel", 20);
    }
    
    public static boolean i(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "combat=movement");
    }
    
    public static boolean j(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "combat=yaw");
    }
    
    public static boolean k(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "combat=pitch");
    }
    
    public static boolean l(final SpartanPlayer spartanPlayer) {
        return CooldownUtils.g(spartanPlayer, "combat=animation=cancel") && !CooldownUtils.g(spartanPlayer, "combat=animation");
    }
    
    public static boolean m(final SpartanPlayer spartanPlayer) {
        return !MoveUtils.ar(spartanPlayer) && (!CooldownUtils.g(spartanPlayer, "combat=distance") || VL.a(spartanPlayer, Enums.HackType.HitReach) > 0);
    }
    
    public static boolean a(final Entity entity) {
        return !PlayerData.c(null, new SpartanLocation(entity.getLocation().add(0.0, -0.25, 0.0)), -0.25);
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final double n) {
        int n2 = 0;
        for (final Entity entity : spartanPlayer.getNearbyEntities(n, n, n)) {
            if (entity instanceof Monster) {
                ++n2;
            }
            else {
                if (!(entity instanceof Player) || (!PlayerData.au(SpartanBukkit.a(entity.getUniqueId())) && !PlayerData.av(spartanPlayer))) {
                    continue;
                }
                ++n2;
            }
        }
        return n2;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final Entity obj) {
        if (obj != null && obj instanceof LivingEntity) {
            if (obj.getVehicle() != null || !CombatUtils.d(obj) || spartanPlayer.equals(obj)) {
                return false;
            }
            final boolean b = obj instanceof Player;
            if ((b && !Checks.getBoolean("KillAura.detection.players")) || (!b && !Checks.getBoolean("KillAura.detection.entities"))) {
                return false;
            }
        }
        return !VL.b(spartanPlayer, hackType, true) && !CombatUtils.ah(spartanPlayer) && !spartanPlayer.isFlying();
    }
}
