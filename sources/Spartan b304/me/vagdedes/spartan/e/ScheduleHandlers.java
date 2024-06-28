package me.vagdedes.spartan.e;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.combat.VelocityCheck;
import me.vagdedes.spartan.checks.movement.ElytraMove;
import me.vagdedes.spartan.checks.movement.Clip;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.checks.movement.Sprint;
import me.vagdedes.spartan.checks.movement.NoFall;
import me.vagdedes.spartan.features.c.Debug;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.checks.combat.c.Modulo;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.checks.f.ImpossibleActions;
import me.vagdedes.spartan.checks.c.ImpossibleInventory;
import me.vagdedes.spartan.checks.movement.IllegalPosition;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.d.MathUtils;
import org.bukkit.Location;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;

public class ScheduleHandlers
{
    private static final String str = "schedule-listeners=";
    private static final HashMap<UUID, SpartanLocation> q;
    
    public ScheduleHandlers() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        C(spartanPlayer);
    }
    
    public static void clear() {
        ScheduleHandlers.q.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        ScheduleHandlers.q.remove(spartanPlayer.getUniqueId());
        DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor" });
    }
    
    public static SpartanLocation b(final SpartanPlayer spartanPlayer) {
        return ScheduleHandlers.q.get(spartanPlayer.getUniqueId());
    }
    
    private static void C(final SpartanPlayer spartanPlayer) {
        if (!MoveUtils.b(spartanPlayer)) {
            DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air", "schedule-listeners=rem" });
            return;
        }
        final SpartanLocation b = b(spartanPlayer);
        if (b == null) {
            DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air", "schedule-listeners=rem" });
            return;
        }
        final Player player = spartanPlayer.getPlayer();
        if (UtilEvents.bf(spartanPlayer)) {
            DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air", "schedule-listeners=rem" });
            MoveUtils.a(player, new Location(b.getWorld(), b.getX(), b.getY(), b.getZ()));
            return;
        }
        final SpartanLocation spartanLocation = new SpartanLocation(spartanPlayer.getUniqueId(), spartanPlayer.a(), 1);
        if (spartanLocation.getWorld() != b.getWorld() || a(spartanPlayer, player, spartanLocation, b)) {
            return;
        }
        final double b2 = b(spartanPlayer, spartanLocation.getY() - b.getY());
        final double b3 = b(spartanPlayer, MathUtils.b(spartanLocation, b));
        final double b4 = b(spartanPlayer, spartanLocation.getY() - (double)spartanLocation.getBlockY());
        final double b5 = b(spartanPlayer, spartanLocation.a(b));
        final int q = PlayerData.q(spartanPlayer);
        if (Settings.canDo("Detections.run_asynchronously")) {
            final SpartanLocation spartanLocation2;
            Threads.a(spartanPlayer, () -> {
                IllegalPosition.b(spartanPlayer, spartanLocation2);
                ImpossibleInventory.a(spartanPlayer, spartanLocation2);
                ImpossibleActions.r(spartanPlayer);
                return;
            });
        }
        else {
            IllegalPosition.b(spartanPlayer, b);
            ImpossibleInventory.a(spartanPlayer, b);
            ImpossibleActions.r(spartanPlayer);
        }
        Modulo.a(spartanPlayer, spartanLocation, b);
        CombatHeuristics.a(spartanPlayer, b3, b2);
        Liquid.b(spartanPlayer, spartanLocation, b, b2);
        BouncingBlocks.a(spartanPlayer, b2, b3);
        WaterSoulSand.c(spartanPlayer, b2);
        EnderPearl.r(spartanPlayer);
        if (!MoveUtils.a(spartanLocation, b)) {
            UtilEvents.O(spartanPlayer);
            return;
        }
        if (Debug.size() > 0) {
            final Entity vehicle = spartanPlayer.getVehicle();
            Debug.f(spartanPlayer, "distance: " + b5 + ", vertical-distance: " + b2 + ", horizontal-distance: " + b3 + ", remaining-value: " + b4 + ", ticks-on-air: " + q + ", on-ground-vanilla: " + spartanPlayer.isOnGround() + ", on-ground-custom: " + PlayerData.L(spartanPlayer, spartanPlayer.a()) + ", vehicle: " + ((vehicle == null) ? "None" : vehicle.getType().toString()));
        }
        if (Settings.canDo("Detections.run_asynchronously")) {
            Threads.a(spartanPlayer, () -> NoFall.b(spartanPlayer, spartanLocation, b, b2));
        }
        else {
            NoFall.b(spartanPlayer, spartanLocation, b, b2);
        }
        Sprint.a(player, player.getLocation(), b, b2);
        Damage.r(spartanPlayer);
        Explosion.c(spartanPlayer, b5);
        UtilEvents.d(spartanPlayer, b3, b4);
        if (Config.a(new Enums.HackType[] { Enums.HackType.Clip, Enums.HackType.ElytraMove, Enums.HackType.Velocity, Enums.HackType.EntityMove, Enums.HackType.IrregularMovements })) {
            if (DoubleUtils.a(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air" })) {
                final double a = DoubleUtils.a(spartanPlayer, "schedule-listeners=ver");
                final double a2 = DoubleUtils.a(spartanPlayer, "schedule-listeners=hor");
                final int n = (int)DoubleUtils.a(spartanPlayer, "schedule-listeners=air");
                final double a3 = DoubleUtils.a(spartanPlayer, "schedule-listeners=rem");
                final SpartanLocation a4 = spartanPlayer.a();
                final SpartanLocation b6 = a4.b().b(0.0, -1.0, 0.0);
                final boolean b7 = GroundUtils.K(spartanPlayer, a4) || GroundUtils.K(spartanPlayer, b6);
                final boolean ak = GroundUtils.ak(spartanPlayer);
                if (Settings.canDo("Detections.run_asynchronously")) {
                    final SpartanLocation spartanLocation3;
                    final SpartanLocation spartanLocation4;
                    final double n2;
                    final double n3;
                    final double n4;
                    final double n5;
                    final double n6;
                    final int n7;
                    final int n8;
                    final double n9;
                    final boolean b8;
                    final boolean b9;
                    Threads.a(spartanPlayer, () -> {
                        Clip.a(spartanPlayer, spartanLocation3, spartanLocation4, n2, n3, n4, n5, n6, n7, n8, false);
                        ElytraMove.a(spartanPlayer, spartanLocation3, spartanLocation4, n2, n5, n3, n7, n4, n6);
                        VelocityCheck.a(spartanPlayer, spartanLocation4, n2, n3, n4);
                        IrregularMovements.a(false, spartanPlayer, spartanLocation3, spartanLocation4, n2, n5, n9, n6, n7, b8, b9);
                        return;
                    });
                }
                else {
                    Clip.a(spartanPlayer, spartanLocation, b, b2, b3, a2, b4, a, q, n, false);
                    ElytraMove.a(spartanPlayer, spartanLocation, b, b2, b4, b3, q, a2, a);
                    VelocityCheck.a(spartanPlayer, b, b2, b3, a2);
                    IrregularMovements.a(false, spartanPlayer, spartanLocation, b, b2, b4, a3, a, q, b7, ak);
                }
            }
            DoubleUtils.a(spartanPlayer, "schedule-listeners=ver", b2);
            DoubleUtils.a(spartanPlayer, "schedule-listeners=hor", b3);
            DoubleUtils.a(spartanPlayer, "schedule-listeners=air", q);
            DoubleUtils.a(spartanPlayer, "schedule-listeners=rem", b4);
        }
        else {
            DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air", "schedule-listeners=rem" });
        }
    }
    
    public static void r(final SpartanPlayer spartanPlayer) {
        if (AttemptUtils.b(spartanPlayer, "schedule-listeners=move-event", 1) == 1) {
            ScheduleHandlers.q.put(spartanPlayer.getUniqueId(), spartanPlayer.a());
        }
    }
    
    public static double b(final SpartanPlayer spartanPlayer, final double n) {
        final int a = AttemptUtils.a(spartanPlayer, "schedule-listeners=move-event");
        return n / ((a < 1) ? 1 : a);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Player player, final SpartanLocation spartanLocation, SpartanLocation spartanLocation2) {
        final HackPrevention a = HackPrevention.a(spartanPlayer, new Enums.HackType[] { Enums.HackType.BoatMove, Enums.HackType.Clip, Enums.HackType.NoSlowdown, Enums.HackType.Speed, Enums.HackType.Fly, Enums.HackType.IrregularMovements, Enums.HackType.EntityMove, Enums.HackType.Exploits, Enums.HackType.ElytraMove, Enums.HackType.IllegalPosition, Enums.HackType.ImpossibleInventory, Enums.HackType.Sprint, Enums.HackType.NoFall, Enums.HackType.MorePackets, Enums.HackType.Jesus }, true);
        if (a != null && Config.a(spartanPlayer, a.getHackType())) {
            final int i = a.i();
            final double damage = a.getDamage();
            final SpartanLocation a2 = a.a();
            if (a2 != null) {
                final double y = a2.getY();
                if (y > 0.0 && y < spartanLocation2.getY() && MoveUtils.a(spartanLocation, a2) >= MoveUtils.a(spartanLocation, spartanLocation2)) {
                    spartanLocation2 = a2;
                }
                MoveUtils.a(player, new Location(spartanLocation2.getWorld(), spartanLocation2.getX(), spartanLocation2.getY(), spartanLocation2.getZ()));
            }
            if (a.i()) {
                MoveUtils.a(player, spartanPlayer);
            }
            if (i > 0) {
                PlayerData.k(spartanPlayer, i);
            }
            if (damage > 0.0) {
                MoveUtils.e(spartanPlayer, damage);
            }
            a(spartanPlayer);
            DoubleUtils.c(spartanPlayer, new String[] { "schedule-listeners=ver", "schedule-listeners=hor", "schedule-listeners=air", "schedule-listeners=rem" });
            return true;
        }
        return false;
    }
    
    private static /* synthetic */ void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7, final double n8, final boolean b, final boolean b2) {
        Clip.a(spartanPlayer, spartanLocation, spartanLocation2, n, n2, n3, n4, n5, n6, n7, false);
        ElytraMove.a(spartanPlayer, spartanLocation, spartanLocation2, n, n4, n2, n6, n3, n5);
        VelocityCheck.a(spartanPlayer, spartanLocation2, n, n2, n3);
        IrregularMovements.a(false, spartanPlayer, spartanLocation, spartanLocation2, n, n4, n8, n5, n6, b, b2);
    }
    
    private static /* synthetic */ void c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n) {
        NoFall.b(spartanPlayer, spartanLocation, spartanLocation2, n);
    }
    
    private static /* synthetic */ void d(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        IllegalPosition.b(spartanPlayer, spartanLocation);
        ImpossibleInventory.a(spartanPlayer, spartanLocation);
        ImpossibleActions.r(spartanPlayer);
    }
    
    static {
        q = new HashMap<UUID, SpartanLocation>();
    }
}
