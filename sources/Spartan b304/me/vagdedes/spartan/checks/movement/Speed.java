package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.c.ViaRewind;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.c.CrackShot;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.system.VL;
import java.util.Iterator;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.h.BowProtection;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.ItemTeleporter;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Boat;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.Register;
import org.bukkit.Material;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.HashSet;
import me.vagdedes.spartan.system.Enums;

public class Speed
{
    private static final Enums.HackType a;
    private static final double M = 0.292;
    private static final double l = 0.325;
    private static final double N = 0.46;
    private static final double O = 0.57;
    private static final double P = 0.31;
    private static final double Q = 0.47;
    private static final double R = 0.525;
    public static final double m;
    private static final double S = 0.363;
    private static final double T = 0.42;
    private static final double U = 0.7;
    private static final double V = 0.85;
    private static final double W = 1.2;
    private static final double X = 0.68;
    private static final double Y = 0.71;
    private static final double Z = 0.78;
    private static final double aa = 0.16;
    private static final double j = 0.59;
    private static final double K = 1.6;
    private static final double ab = 0.18;
    private static final double ac = 1.5;
    private static final double ad = 0.8;
    private static final double ae = 2.0;
    private static final double af = 0.62;
    private static final double ag = 2.15;
    private static final double ah = 3.5;
    private static final double ai = 3.0;
    private static final double aj = 1.6;
    private static final double ak = 6.0;
    private static final double al = 1.5;
    private static final double am = 0.5;
    private static final HashSet<Float> c;
    
    public Speed() {
        super();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, Speed.a.toString() + "=l");
        if (PlayerData.b(spartanPlayer, 5, 0.5)) {
            CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=teleport-contact", 2);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double d, final double n, final int i, final double d2, final double n2, final double d3, final double n3) {
        if (!b(spartanPlayer) || n < 0.16) {
            return;
        }
        final boolean as = PlayerData.aS(spartanPlayer);
        final double n4 = 0.0;
        boolean b = false;
        boolean b2 = false;
        final SpartanLocation b3 = spartanPlayer.a().b(0.0, 1.0, 0.0);
        final SpartanLocation b4 = spartanPlayer.a().b(0.0, -1.0, 0.0);
        String s;
        double n5;
        if (f(spartanPlayer, b3) && (PlayerData.i(spartanPlayer, 0.0, -d2, 0.0) || PlayerData.i(spartanPlayer, 0.0, -(d2 * 2.0), 0.0))) {
            s = "blocks";
            b = true;
            if (Piston.E(spartanPlayer)) {
                n5 = a(spartanPlayer, n4, 1.2, 15);
            }
            else if (BlockUtils.b(spartanPlayer, 2)) {
                n5 = a(spartanPlayer, n4, 0.85, 15);
            }
            else {
                n5 = a(spartanPlayer, n4, 0.7, 15);
            }
        }
        else if (BlockUtils.c(spartanPlayer, b3, true)) {
            s = "solid";
            n5 = a(spartanPlayer, n4, 1.5, 10);
        }
        else if (!BlockUtils.a(spartanPlayer, true, 0.298, 0.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, 1.0, 0.298)) {
            s = "liquid";
            if (PlayerData.aC(spartanPlayer)) {
                n5 = a(spartanPlayer, n4, 2.0, 10);
            }
            else {
                n5 = a(spartanPlayer, n4, 0.8, 10);
            }
        }
        else if ((n2 == 0.0 && d == 0.0) || (d - n2 == 0.0 && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0))) {
            if (spartanPlayer.isSneaking() && Checks.getBoolean("Speed.check_sneaking")) {
                s = "sneaking";
                n5 = a(spartanPlayer, n4, 0.16, 0);
            }
            else if (spartanPlayer.a().a().getType() == Material.SOUL_SAND) {
                s = "soul-sand";
                n5 = a(spartanPlayer, n4, 0.18, 5);
            }
            else {
                double n6;
                if ((Register.v1_8 && b4.a().getType() == Material.SLIME_BLOCK) || (Register.v1_15 && b4.a().getType() == Material.HONEY_BLOCK)) {
                    n6 = a(spartanPlayer, n4, 0.31, 0);
                }
                else if (spartanPlayer.isSprinting()) {
                    n6 = a(spartanPlayer, n4, 0.325, 0);
                }
                else {
                    n6 = a(spartanPlayer, n4, 0.292, 0);
                }
                if (n6 != 0.292 || !Checks.getBoolean("Speed.check_walking")) {
                    s = "ground";
                    n5 = a(spartanPlayer, n6, n6, 15);
                }
                else {
                    s = "walking";
                    n5 = a(spartanPlayer, n6, n6, 10);
                }
            }
        }
        else if (Values.b(d, 6) == 0.419999 || Values.b(d2, 6) == 0.419999) {
            s = "ypos";
            if (!CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=ice=reminder")) {
                n5 = a(spartanPlayer, n4, 0.71, 0);
            }
            else {
                n5 = a(spartanPlayer, n4, 0.68, 0);
            }
        }
        else if ((d2 == 0.0 || (d2 >= 0.084 && d2 <= 0.15) || d2 == 0.5 || d2 >= 0.75) && d == 0.0 && !BlockUtils.b(spartanPlayer, true, 0.2, 0.0, 0.2) && PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            s = "ground";
            if (n == d3) {
                n5 = a(spartanPlayer, n4, 0.57, 5);
            }
            else {
                n5 = a(spartanPlayer, n4, 0.46, 5);
            }
        }
        else {
            s = "air";
            if (DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=old") == 1.2 || !CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=ice=reminder")) {
                n5 = a(spartanPlayer, n4, 0.59, 10);
            }
            else if (BlockUtils.b(spartanPlayer, 3)) {
                if (!BlockUtils.c(spartanPlayer, true, 0.298, 2.0, 0.298)) {
                    n5 = a(spartanPlayer, n4, 0.85, 12);
                }
                else {
                    n5 = a(spartanPlayer, n4, 0.47, 12);
                }
            }
            else if (!BlockUtils.k(spartanPlayer, true, 0.298, 0.0, 0.298) || BlockUtils.a(spartanPlayer, spartanPlayer.a(), Material.SNOW, 0.298)) {
                n5 = a(spartanPlayer, n4, 0.42, 8);
            }
            else {
                if (!MoveUtils.b(d) && n >= 0.33 && d2 > 0.0 && d2 < 0.1 && PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) {
                    CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=skip-ground-jump-limit", 10);
                }
                n5 = a(spartanPlayer, n4, Speed.m, 8);
            }
        }
        for (int j = -1; j <= -1; ++j) {
            if (BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, (double)j, 0.0))) {
                CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=ice=reminder", 20);
                if (s != "blocks") {
                    b2 = true;
                    n5 = a(spartanPlayer, n5, 0.59, 0);
                    if (n5 == 0.59) {
                        s = "ice";
                        n5 = a(spartanPlayer, n5, n5, 15);
                        break;
                    }
                    break;
                }
                else if (j >= -1) {
                    n5 = a(spartanPlayer, n5, 1.2, 25);
                    break;
                }
            }
        }
        if ((PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) && spartanPlayer.isSprinting() && (d == 0.375 || (d >= 0.5 && d < 0.6) || Math.abs(d2 - n3) == 0.5)) {
            if (BlockUtils.t(spartanPlayer, b4) || (b4.getY() - d2 != b3.getBlockY() && BlockUtils.t(spartanPlayer, spartanPlayer.a().b(0.0, -d2, 0.0)))) {
                final SpartanLocation a = spartanPlayer.a();
                a.setPitch(0.0f);
                if (BlockUtils.t(spartanPlayer, a.b(a.getDirection().multiply(1.0)))) {
                    s = "stairs";
                    n5 = a(spartanPlayer, n5, 0.78, 15);
                }
            }
            else if (!BlockUtils.g(spartanPlayer, true, 1.0, -1.0, 1.0) || !BlockUtils.g(spartanPlayer, true, 1.0, -d2, 1.0) || !BlockUtils.g(spartanPlayer, true, 1.0, 0.0, 1.0)) {
                s = "upwards";
                n5 = a(spartanPlayer, n5, 0.78, 15);
                CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=upwards=reminder", 30);
            }
        }
        if (!CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=upwards=reminder")) {
            n5 = a(spartanPlayer, n5, 0.78, 15);
        }
        final Iterator<Entity> iterator = spartanPlayer.getNearbyEntities(3.0, 3.0, 3.0).iterator();
        while (iterator.hasNext()) {
            if (((Entity)iterator.next()) instanceof Boat) {
                s = "entity";
                if (n == d3) {
                    n5 = a(spartanPlayer, n5, 0.57, 5);
                }
                else {
                    n5 = a(spartanPlayer, n5, 0.46, 5);
                }
            }
        }
        if ((n5 == Speed.m || n5 == 0.46) && d2 == 0.0 && d == 0.0 && spartanPlayer.isSprinting() && n5 != 0.78 && CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=skip-ground-jump-limit")) {
            s = "ground";
            n5 = a(spartanPlayer, n5, 0.525, 10);
        }
        if (!BlockUtils.j(spartanPlayer, true, 1.0, -d2, 1.0) || !BlockUtils.j(spartanPlayer, true, 0.298, -d2, 0.298)) {
            CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=bed", 10);
        }
        if (!CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=bed")) {
            n5 = a(spartanPlayer, n5, 0.62, 10);
        }
        if (PlayerData.aQ(spartanPlayer)) {
            s = "push-extreme";
            n5 = a(spartanPlayer, n5, 6.0, 12);
        }
        else if (PlayerData.b(spartanPlayer, 1, 0.5)) {
            s = "push";
            n5 = a(spartanPlayer, n5, 1.5, 12);
        }
        else if (PlayerData.b(spartanPlayer, 10, 0.6) || !CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=teleport-contact")) {
            s = "contact";
            n5 = a(spartanPlayer, n5, 0.5, 6);
        }
        if (Piston.E(spartanPlayer) || ((n5 == 0.59 || n5 == Speed.m || n5 == 0.71) && VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.l && (BlockUtils.a(spartanPlayer, spartanPlayer.a(), Material.SLIME_BLOCK, 1.0) || BlockUtils.a(spartanPlayer, b3, Material.SLIME_BLOCK, 1.0)))) {
            n5 = a(spartanPlayer, n5, 1.6, 5);
        }
        if (SelfHit.E(spartanPlayer) || FishingHook.Y(spartanPlayer)) {
            n5 = a(spartanPlayer, n5, 1.6, 5);
        }
        if (BlockUtils.c(spartanPlayer, true, 0.298, 2.0, 0.298)) {
            CooldownUtils.d(spartanPlayer, Speed.a.toString() + "=above-block", 20);
        }
        final double a2 = DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=old");
        final double a3 = DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=number");
        if (DoubleUtils.h(a2) && DoubleUtils.h(a3)) {
            n5 = a(spartanPlayer, n5, a2, 0);
        }
        final float b5 = PlayerData.b(spartanPlayer);
        final double a4 = a(spartanPlayer, s, n5, b5, as);
        if (b5 == 0.0f && !Damage.E(spartanPlayer) && !Explosion.E(spartanPlayer) && !PlayerData.aQ(spartanPlayer) && !PlayerData.aw(spartanPlayer) && !as && !PlayerData.az(spartanPlayer) && !NoHitDelay.E(spartanPlayer) && !Piston.E(spartanPlayer) && !PlayerData.aF(spartanPlayer) && !ItemTeleporter.E(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !WaterSoulSand.E(spartanPlayer)) {
            if (!TPS.u() && a4 == Speed.m && n >= 0.363 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=value-decrease", 50) == 3) {
                AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=value-decrease");
                a(spartanPlayer, spartanLocation, "t: air(decreased), d: " + n, false);
            }
            if (!b && !f(spartanPlayer, b3) && a4 == 1.2 && !LatencyUtils.e(spartanPlayer, 250)) {
                int k = 0;
                if (n >= 0.8999999999999999) {
                    k = 1;
                }
                else if (d3 >= 0.59 && n >= d3 && n >= 0.9) {
                    k = 2;
                }
                if (k != 0) {
                    a(spartanPlayer, spartanLocation, "t: ice-speed(exit), d: " + n + ", c: " + k, true);
                }
            }
            if (i <= 12 && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && BlockUtils.c(spartanPlayer, true, 1.0, 2.0, 1.0) && !BlockUtils.b(spartanPlayer, 2) && !BouncingBlocks.R(spartanPlayer) && (n5 == Speed.m || n5 == 0.325 || n5 == 0.525 || n5 == 0.46 || n5 == 0.57 || n5 == 0.16 || n5 == 0.292)) {
                boolean b6 = true;
                for (int l = -1; l <= 1; ++l) {
                    if (!BlockUtils.g(spartanPlayer, true, 1.0, l, 1.0)) {
                        b6 = false;
                        break;
                    }
                }
                if (b6 && d > 0.0) {
                    if (!MoveUtils.b(d)) {
                        if (!TPS.u() && !BlockUtils.b(spartanPlayer, 3) && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=hop=illegal", 10) >= 4) {
                            a(spartanPlayer, spartanLocation, "t: hop(illegal), d: " + n + ", dm: " + n5, true);
                        }
                    }
                    else if (CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=above-block")) {
                        final double b7 = Values.b(d, 3);
                        boolean b8 = false;
                        if (b7 == 0.083 && n >= 0.35) {
                            b8 = true;
                        }
                        else if (b7 == 0.164 && n >= 0.355) {
                            b8 = true;
                        }
                        else if (b7 == 0.248 && n >= 0.36) {
                            b8 = true;
                        }
                        else if (b7 == 0.333 && n >= 0.365) {
                            b8 = true;
                        }
                        if (b8) {
                            a(spartanPlayer, spartanLocation, "t: hop(acceleration), v: " + b7 + ", d: " + n + ", dm: " + n5, true);
                        }
                    }
                }
            }
            if (n5 == 0.78) {
                final double abs = Math.abs(n - d3);
                if (n > d3 && abs >= 0.35 && d == 0.5) {
                    a(spartanPlayer, spartanLocation, "t: upwards(difference), d: " + n + ", od: " + d3 + ", diff: " + abs, true);
                }
            }
            if (d3 != 0.0) {
                final double abs2 = Math.abs(n - d3);
                int a5 = AttemptUtils.a(spartanPlayer, Speed.a.toString() + "=ice=ticks");
                if (b2 && (n5 == 0.59 || n5 == 0.71)) {
                    ++a5;
                    AttemptUtils.c(spartanPlayer, Speed.a.toString() + "=ice=ticks", a5);
                }
                else {
                    a5 = 0;
                    AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=ice=ticks");
                }
                if (n > d3) {
                    if (b && n5 == 1.2 && abs2 >= 0.3) {
                        a(spartanPlayer, spartanLocation, "t: ice-speed(blocks), diff: " + abs2 + ", d: " + n + ", od: " + d3, true);
                    }
                    else if (a5 >= 20 && n >= 0.5 && (abs2 >= 0.25 || abs2 <= 0.15)) {
                        a(spartanPlayer, spartanLocation, "t: ice-speed(normal), diff: " + abs2 + ", d: " + n + ", od: " + d3, true);
                    }
                }
            }
            if (n5 == 0.59 && !Piston.E(spartanPlayer)) {
                if (n >= 0.5 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=ice_speed=counter", 20) >= 7) {
                    a(spartanPlayer, spartanLocation, "t: ice-speed(counter), d: " + n + ", dm: " + n5, true);
                }
                if ((PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) & n >= 0.36) && AttemptUtils.g(spartanPlayer, Speed.a.toString() + "=" + s) && BlockUtils.g(spartanPlayer, true, 0.298, 0.0, 0.298) && !BlockUtils.i(spartanPlayer, true, 0.0, -1.0, 0.0) && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=ice_speed", 10) == 4) {
                    AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=ice_speed");
                    a(spartanPlayer, spartanLocation, "t: ice-speed(ground), d: " + n + ", dm: " + n5, true);
                }
            }
            final double a6 = DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=ov");
            if (n5 == Speed.m && i > 0 && i <= 11 && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0) && BlockUtils.g(spartanPlayer, true, 1.0, -1.0, 1.0) && !PlayerData.i(spartanPlayer, 0.0, -0.15, 0.0)) {
                DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=ov", DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=ov") + n);
            }
            else if (n5 != 0.68 && DoubleUtils.h(a6)) {
                if (a6 <= 1.65) {
                    final double b9 = Values.b(a6, 3);
                    final long a7 = MillisUtils.a(spartanPlayer, Speed.a.toString() + "=overall=" + b9);
                    if (a7 <= 700L && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=overall", 30) >= 2) {
                        a(spartanPlayer, spartanLocation, "t: overall, v: " + b9 + ", t: " + a7, true);
                    }
                    MillisUtils.o(spartanPlayer, Speed.a.toString() + "=overall=" + b9);
                }
                DoubleUtils.j(spartanPlayer, Speed.a.toString() + "=ov");
            }
            if (n < a4 && CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=bed") && !BlockUtils.b(spartanPlayer, 2) && (n5 == Speed.m || n5 == 0.292 || n5 == 0.325 || n5 == 0.16 || n5 == 0.525) && CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=above-block") && !b) {
                final float fallDistance = spartanPlayer.getFallDistance();
                boolean b10 = true;
                if (n5 == Speed.m || n5 == 0.525) {
                    for (int n7 = -1; n7 <= 1; ++n7) {
                        if (!BlockUtils.g(spartanPlayer, true, 1.0, n7, 1.0)) {
                            b10 = false;
                            break;
                        }
                    }
                }
                if (fallDistance <= 1.2f && b10) {
                    final boolean b11 = !Speed.c.contains(Float.valueOf(fallDistance));
                    if (n >= 0.215 && b11 && !PlayerData.b(spartanPlayer, 1, 0.5) && i <= 12 && spartanPlayer.getFireTicks() <= 0 && !BouncingBlocks.R(spartanPlayer) && !Building.E(spartanPlayer)) {
                        a(spartanPlayer, spartanLocation, "t: fall-distance(illegal), d: " + n + ", dm: " + n5 + ", fd: " + fallDistance + ", a: " + i + ", r: " + d2 + ", dy: " + d, true);
                    }
                    else if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && n > 0.335 && fallDistance > 0.0f && BlockUtils.c(spartanPlayer, b4, false)) {
                        a(spartanPlayer, spartanLocation, "t: fall-distance(normal), d: " + n + ", dm: " + n5 + ", r: " + d2 + ", dy: " + d, true);
                    }
                    else if (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && n >= 0.36 && fallDistance == 0.0f && d2 == 0.0 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=fall-distance", 10) == 2) {
                        AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=fall-distance");
                        a(spartanPlayer, spartanLocation, "t: fall-distance(ground), d: " + n + ", dm: " + n5 + ", r: " + d2 + ", dy: " + d, true);
                    }
                }
            }
            if (n5 == Speed.m && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && n >= 0.34 && n < a4 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=hop=motion", 20) == 12) {
                AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=hop=motion");
                a(spartanPlayer, spartanLocation, "t: hop(motion), d: " + n + ", dm: " + n5, true);
            }
            if (n5 == 0.525 && n >= 0.36 && n < a4 && d <= 0.0 && !b && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=ground_jump_speed", 10) == 3) {
                AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=ground_jump_speed");
                a(spartanPlayer, spartanLocation, "t: ground(jump-speed), d: " + n + ", dm: " + n5, true);
            }
            if (n >= 0.295 && n < a4 && !b) {
                int n8 = 0;
                final double b12 = Values.b(n, 8);
                if (n5 == Speed.m && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && PlayerData.i(spartanPlayer, 0.0, -0.25, 0.0)) {
                    n8 = 1;
                }
                else if (n5 == 0.325 || n5 == 0.292 || n5 == 0.16) {
                    n8 = 1;
                }
                if (n8 != 0) {
                    for (int n9 = -1; n9 <= 1; ++n9) {
                        if (!BlockUtils.g(spartanPlayer, true, 1.0, n9, 1.0)) {
                            n8 = 0;
                            break;
                        }
                    }
                }
                if (n8 != 0 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=high_normal_speed", 20) >= 18) {
                    a(spartanPlayer, spartanLocation, "t: ground(high), d: " + n + ", dm: " + n5, true);
                }
                final long a8 = MillisUtils.a(spartanPlayer, Speed.a.toString() + "=repeated=" + b12);
                if (a8 <= 250L && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=repeated", 10) == 3) {
                    AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=repeated");
                    a(spartanPlayer, spartanLocation, "t: repeated, t: " + a8 + ", d: " + b12 + ", dm: " + n5, true);
                }
                MillisUtils.o(spartanPlayer, Speed.a.toString() + "=repeated=" + b12);
            }
            if (n5 == 0.68 && s.length() == 4 && s == "ypos" && n >= 0.4 && n < a4 && AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=ypos=attempts", 20) == 4) {
                AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=ypos=attempts");
                a(spartanPlayer, spartanLocation, "t: ypos, d: " + n + ", dm: " + n5 + ", dm_s: " + a4, true);
            }
        }
        if (n5 > 0.0 && a4 > 0.0 && n >= a4) {
            int n10 = 1;
            int n11 = 1;
            boolean b13 = true;
            final boolean e = Damage.E(spartanPlayer);
            final boolean e2 = Explosion.E(spartanPlayer);
            final boolean e3 = BowProtection.E(spartanPlayer);
            if (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) {
                if ((a4 == 0.325 && n < 0.36) || ((a4 == Speed.m || a4 == 0.46 || a4 == 0.57) && n < 0.63) || (a4 == 0.16 && n < 0.2)) {
                    final long a9 = MillisUtils.a(spartanPlayer, Speed.a.toString() + "=limit=protection");
                    if (MillisUtils.hasTimer(a9)) {
                        if (a9 >= 4000L) {
                            b13 = false;
                        }
                    }
                    else {
                        b13 = false;
                    }
                }
                MillisUtils.o(spartanPlayer, Speed.a.toString() + "=limit=protection");
            }
            if ((e && Damage.d(spartanPlayer) <= 500L) || NoHitDelay.E(spartanPlayer)) {
                n10 = 10;
                n11 = 6;
            }
            else if (PlayerData.aE(spartanPlayer) || as) {
                n10 = 5;
                n11 = 3;
            }
            if (b13) {
                if (!AttemptUtils.g(spartanPlayer, Speed.a.toString() + "=" + s) && CooldownUtils.g(spartanPlayer, Speed.a.toString() + "=protection=cooldown")) {
                    AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=" + s);
                }
                if (AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=" + s, n10) == n11 || (e2 && n5 == 3.5) || (e3 && n5 == 3.0) || (!e2 && !e3 && e && n >= 2.15)) {
                    AttemptUtils.m(spartanPlayer, Speed.a.toString() + "=" + s);
                    final String string = "t: normal, reason: " + s + ", d: " + n + ", dm: " + n5 + ", dm_s: " + a4 + ", a: " + i;
                    if (n - a4 >= 0.4) {
                        a(spartanPlayer, spartanLocation, string, false);
                    }
                    else {
                        a(spartanPlayer, spartanLocation, string, true);
                    }
                }
                else if (!AttemptUtils.g(spartanPlayer, Speed.a.toString() + "=" + s) && !e && !e2 && !e3) {
                    n5 = 1.75 + b5;
                    final double a10 = MoveUtils.a(spartanPlayer, n5, 4.0, PotionEffectType.SPEED);
                    if (n >= a10) {
                        a(spartanPlayer, spartanLocation, "t: cancelled, r: " + s + ", d: " + n + ", dm: " + n5 + ", dm_s: " + a10 + ", a: " + i, true);
                    }
                }
            }
        }
        if (DoubleUtils.h(a3)) {
            final int n12 = (int)a3;
            DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=number", n12 - 1);
            if (n12 <= 0) {
                DoubleUtils.j(spartanPlayer, Speed.a.toString() + "=number");
                DoubleUtils.j(spartanPlayer, Speed.a.toString() + "=old");
            }
        }
        DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=l", n5);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s, final boolean b) {
        boolean b2 = true;
        boolean b3 = true;
        if (b) {
            if (AttemptUtils.b(spartanPlayer, Speed.a.toString() + "=" + s.split(", ", 2)[0].substring(3) + "=attempts", 40) >= (Building.E(spartanPlayer) ? 4 : 2)) {
                if (Building.E(spartanPlayer) && VL.a(spartanPlayer, Enums.HackType.ImpossibleActions) <= 1) {
                    b3 = false;
                }
            }
            else if (VL.a(spartanPlayer, Speed.a) == 0) {
                b2 = false;
            }
            else {
                b3 = false;
            }
        }
        EventsHandler1.a(spartanPlayer, Speed.a, b2 ? spartanLocation : null, s, b2, b3);
    }
    
    private static double a(final SpartanPlayer spartanPlayer, final String s, final double n, final float n2, final boolean b) {
        double n3 = n + n2 * 1.48;
        if (PlayerData.aA(spartanPlayer)) {
            if (s == "air") {
                n3 += 0.26;
            }
            else {
                n3 += 0.5;
            }
        }
        else if (PlayerData.aE(spartanPlayer) && n3 != 0.16 && n3 != 0.292) {
            n3 += ((n3 == 0.59 || n3 == 1.2) ? 0.4 : 0.275);
        }
        if ((Damage.E(spartanPlayer) && Damage.d(spartanPlayer) <= 1000L) || NoHitDelay.E(spartanPlayer)) {
            n3 += (NoHitDelay.E(spartanPlayer) ? 0.5 : 0.3);
        }
        if (CrackShot.A(spartanPlayer) || (SelfHit.E(spartanPlayer) && n > 1.6) || b) {
            n3 += 0.3;
        }
        if (n == 0.8 && PlayerData.aC(spartanPlayer)) {
            n3 += 0.8;
        }
        double a;
        if (Explosion.E(spartanPlayer)) {
            a = ((3.5 < n3) ? n3 : 3.5);
        }
        else if (BowProtection.E(spartanPlayer)) {
            a = ((3.0 < n3) ? n3 : 3.0);
        }
        else {
            a = MoveUtils.a(spartanPlayer, n3, 4.0, PotionEffectType.SPEED);
        }
        return a;
    }
    
    private static boolean f(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        boolean b = false;
        final SpartanLocation b2 = spartanLocation.b().b(0.0, 1.0, 0.0);
        final SpartanLocation a = spartanPlayer.a();
        final VersionUtils.a a2 = VersionUtils.a();
        final double n = a.getY() - a.getBlockY();
        if ((a2 != VersionUtils.a.l && a2 != VersionUtils.a.c && a2 != VersionUtils.a.d && spartanPlayer.a().b(0.0, 2.0, 0.0).a().getType() == Material.END_ROD) || BlockUtils.m(spartanLocation.a().getType()) || BlockUtils.o(spartanPlayer, spartanLocation) || BlockUtils.m(b2.a().getType()) || BlockUtils.o(spartanPlayer, b2)) {
            return true;
        }
        for (double n2 = 2.0 - n; n2 <= ((n >= 0.8) ? (3.0 - n) : 2.0); ++n2) {
            if (BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, n2, 0.0))) {
                return true;
            }
            final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), 1.0, n2, 1.0).iterator();
            while (iterator.hasNext()) {
                if (BlockUtils.f(spartanPlayer, iterator.next())) {
                    b = true;
                    break;
                }
            }
            if (b) {
                break;
            }
        }
        if (b) {
            for (int n3 = 0; n3 <= 1.0 - n; ++n3) {
                final Iterator<SpartanLocation> iterator2 = BlockUtils.a(spartanPlayer.a(), 1.0, n3, 1.0).iterator();
                while (iterator2.hasNext()) {
                    if (BlockUtils.c(spartanPlayer, iterator2.next(), true)) {
                        return false;
                    }
                }
            }
        }
        return b;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, Speed.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && u(spartanPlayer);
    }
    
    private static boolean u(final SpartanPlayer spartanPlayer) {
        return !PlayerData.aS(spartanPlayer) || (PlayerData.q(spartanPlayer) <= 1 && spartanPlayer.getItemInHand().getType() != MaterialUtils.a("firework") && !ElytraGlide.bd(spartanPlayer) && BlockUtils.a(spartanPlayer, true, 0.298, 0.0, 0.298) && BlockUtils.a(spartanPlayer, true, 0.298, 1.0, 0.298) && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0)));
    }
    
    private static double a(final SpartanPlayer spartanPlayer, double n, final double n2, final int n3) {
        if (n2 >= n) {
            n = n2;
        }
        boolean b = true;
        final double a = DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=old");
        if (DoubleUtils.h(a) && n < a) {
            b = false;
        }
        if (b && n3 > 0) {
            DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=old", n);
            DoubleUtils.a(spartanPlayer, Speed.a.toString() + "=number", n3);
        }
        return n;
    }
    
    static {
        a = Enums.HackType.Speed;
        m = (ViaRewind.isLoaded() ? 0.39 : 0.3675);
        (c = new HashSet<Float>(11)).add(Float.valueOf(0.0f));
        Speed.c.add(Float.valueOf(0.0784f));
        Speed.c.add(Float.valueOf(0.075444065f));
        Speed.c.add(Float.valueOf(0.22777925f));
        Speed.c.add(Float.valueOf(0.233632f));
        Speed.c.add(Float.valueOf(0.45546773f));
        Speed.c.add(Float.valueOf(0.46415937f));
        Speed.c.add(Float.valueOf(0.7570025f));
        Speed.c.add(Float.valueOf(0.7684762f));
        Speed.c.add(Float.valueOf(1.1309065f));
        Speed.c.add(Float.valueOf(1.1451067f));
    }
}
