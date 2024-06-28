package me.vagdedes.spartan.checks.movement;

import org.bukkit.inventory.ItemStack;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.h.LowViolation;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.f.SpartanBlock;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.h.BlockBreak;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Damage;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Pig;
import me.vagdedes.spartan.e.EventsHandler1;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Jesus
{
    private static final Enums.HackType a;
    private static final int k = 18;
    private static final int l = 15;
    private static final double B = 0.19;
    private static final double C = 0.225;
    private static final double D = 0.15;
    private static final double E = 0.13;
    private static final double F = 0.3;
    private static final double G = 0.45;
    private static final double H = 0.35;
    private static final double I = 0.6;
    private static final double J = 0.68;
    private static final double K = 0.5;
    private static final double L = 0.17;
    private static final long c = 1000L;
    
    public Jesus() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double d) {
        if (!BlockUtils.c(spartanPlayer, true, 1.0, 2.0, 1.0) || !BlockUtils.c(spartanPlayer, true, 1.0, 2.0 - n2, 1.0)) {
            for (int i = -1; i <= 0; ++i) {
                if (BlockUtils.j(spartanPlayer, spartanLocation.b().b(0.0, (double)i, 0.0))) {
                    CooldownUtils.d(spartanPlayer, Jesus.a.toString() + "=ice", 20);
                    break;
                }
            }
        }
        if (PlayerData.q(spartanPlayer) > 95) {
            CooldownUtils.d(spartanPlayer, Jesus.a.toString() + "=air_ticks", 40);
        }
        if (PlayerData.q(spartanPlayer) > 0 && n <= -2.0) {
            CooldownUtils.d(spartanPlayer, Jesus.a.toString() + "=y_distance", 20);
        }
        final SpartanLocation b = spartanLocation.b().b(0.0, 1.0, 0.0);
        if (!a(spartanPlayer, spartanLocation, b)) {
            v(spartanPlayer);
            w(spartanPlayer);
            return;
        }
        if ((BlockUtils.f(spartanPlayer, spartanLocation) || !BlockUtils.c(spartanPlayer, true, 0.3, 0.0, 0.3)) && !spartanLocation.a().isLiquid()) {
            CooldownUtils.d(spartanPlayer, Jesus.a.toString() + "=ground", 15);
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=ground")) {
            return;
        }
        final SpartanLocation b2 = spartanLocation.b().b(0.0, -1.0, 0.0);
        final SpartanLocation b3 = spartanLocation.b().b(0.0, -2.0, 0.0);
        final boolean r = BouncingBlocks.R(spartanPlayer);
        final boolean b4 = PlayerData.b(spartanPlayer) != 0.0;
        final boolean b5 = n == 0.0 || MoveUtils.b(n) || VL.a(spartanPlayer, Jesus.a) >= 2 || (BlockUtils.d(b2) && BlockUtils.d(spartanLocation) && BlockUtils.d(spartanLocation.b().b(0.0, 1.0, 0.0)) && BlockUtils.d(spartanLocation.b().b(0.0, 2.0, 0.0)));
        int j = -1;
        double d2 = 0.0;
        if (a(spartanPlayer, MaterialUtils.a("lava"), 0.3, b, b2, b3)) {
            if (b4) {
                d2 = 0.6;
                j = 0;
            }
            else if (r) {
                d2 = 0.35;
                j = 1;
            }
            else if (b(spartanPlayer, spartanPlayer.a(), false) && n == 0.0 && l(spartanPlayer) >= 10) {
                d2 = 0.17 + n(spartanPlayer) * 0.17;
                j = 2;
            }
            else if (spartanLocation.a().getType() == MaterialUtils.a("lava") || (b.a().getType() == MaterialUtils.a("lava") && spartanLocation.a().getType() == MaterialUtils.a("lava"))) {
                if (b5) {
                    if (spartanPlayer.getFireTicks() <= 1) {
                        d2 = 0.13;
                        j = 3;
                    }
                    else {
                        d2 = 0.3;
                        j = 4;
                    }
                }
                else {
                    d2 = 0.45;
                    j = 5;
                }
            }
            else if ((b2.a().getType() == MaterialUtils.a("lava") && e(spartanPlayer, 1.0, 0.0, 1.0)) || (b3.a().getType() == MaterialUtils.a("lava") && e(spartanPlayer, 1.0, 0.0, 1.0) && e(spartanPlayer, 1.0, -1.0, 1.0))) {
                d2 = 0.15;
                j = 6;
            }
            else {
                d2 = 0.45;
                j = 7;
            }
        }
        else if (a(spartanPlayer, MaterialUtils.a("water"), 0.3, b, b2, b3)) {
            if (b4) {
                d2 = 0.6;
                j = 8;
            }
            else if (r) {
                d2 = 0.35;
                j = 9;
            }
            else if (b(spartanPlayer, spartanLocation, false) && n == 0.0 && l(spartanPlayer) >= 10) {
                d2 = 0.17 + n(spartanPlayer) * 0.17;
                j = 10;
            }
            else if (spartanLocation.a().getType() == MaterialUtils.a("water") || (b.a().getType() == MaterialUtils.a("water") && spartanLocation.a().getType() == MaterialUtils.a("water"))) {
                if (b5) {
                    d2 = 0.225 + n(spartanPlayer) * 3.5;
                    j = 11;
                }
                else {
                    d2 = 0.45 + n(spartanPlayer) * 1.75;
                    j = 12;
                }
            }
            else if ((b2.a().getType() == MaterialUtils.a("water") && e(spartanPlayer, 1.0, 0.0, 1.0)) || (b3.a().getType() == MaterialUtils.a("water") && e(spartanPlayer, 1.0, 0.0, 1.0) && e(spartanPlayer, 1.0, -1.0, 1.0))) {
                d2 = 0.19;
                j = 13;
            }
            else {
                d2 = 0.45;
                j = 14;
            }
        }
        else if (AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=cannot-check-block", 20) >= 3) {
            v(spartanPlayer);
        }
        if (!PlayerData.b(spartanPlayer, false) && Checks.getBoolean("Jesus.check_y_position") && !GroundUtils.ak(spartanPlayer) && ((spartanLocation.a().isLiquid() && b.a().isLiquid()) || (spartanLocation.a().isLiquid() && b.a().isLiquid()) || (BlockUtils.a(spartanPlayer, spartanLocation, MaterialUtils.a("water"), 0.298) && BlockUtils.a(spartanPlayer, b, MaterialUtils.a("water"), 0.3)) || (BlockUtils.a(spartanPlayer, spartanLocation, MaterialUtils.a("water"), 0.298) && BlockUtils.a(spartanPlayer, b, MaterialUtils.a("water"), 0.3)))) {
            final double abs = Math.abs(n);
            final boolean z = SelfHit.Z(spartanPlayer);
            final boolean e = WaterSoulSand.E(spartanPlayer);
            if (n >= 0.12 || n <= -0.2) {
                final double b6 = Values.b(abs, 10);
                if (n > 0.0 && m(spartanPlayer) >= 0 && !PlayerData.az(spartanPlayer)) {
                    final double d3 = e ? 0.8 : (Register.v1_13 ? 0.6 : ((z || spartanPlayer.isSprinting()) ? 0.4 : 0.175));
                    int n3 = 30;
                    int n4 = 3;
                    if (z) {
                        n3 = 1;
                        n4 = 1;
                    }
                    if (n >= d3 && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=ypos=attempts", n3) >= n4 && !LatencyUtils.e(spartanPlayer, 220)) {
                        a(spartanPlayer, spartanLocation2, "t: ypos(attempts), dy: " + n + ", l: " + d3);
                    }
                }
                if (!Register.v1_13) {
                    final long a = MillisUtils.a(spartanPlayer, Jesus.a.toString() + "=repeated=ypos=" + b6);
                    if (a <= 500L) {
                        a(spartanPlayer, spartanLocation2, "t: ypos(repeated), dy: " + n + ", ms: " + a);
                    }
                }
                MillisUtils.o(spartanPlayer, Jesus.a.toString() + "=repeated=ypos=" + b6);
            }
            final double d4 = Register.v1_13 ? 0.56 : ((spartanPlayer.getFireTicks() > 0) ? 0.5 : (z ? 0.4 : 0.38));
            if (!e && ((n <= -1.0 && m(spartanPlayer) >= 0) || (n >= d4 && n != 0.5 && !spartanPlayer.hasPotionEffect(PotionEffectType.JUMP) && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0) && CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=air_ticks")))) {
                a(spartanPlayer, spartanLocation2, "t: ypos(normal), dy: " + n + ", l: " + d4);
            }
        }
        else {
            w(spartanPlayer);
        }
        c(spartanPlayer, d, n);
        final long a2 = a(spartanPlayer, spartanLocation.b().b(0.0, 2.0, 0.0).a().getType());
        a(spartanPlayer, spartanLocation, spartanLocation2, n);
        if (d2 > 0.0 && j > -1) {
            a(spartanPlayer, d, n, n2, spartanLocation2);
            a(spartanPlayer, d, spartanLocation2, b);
            a(spartanPlayer, d, n, n2, spartanLocation2, a2, b5, b, b2);
            a(spartanPlayer, n, spartanLocation2, a2, b5, b, b2, b3);
            a(spartanPlayer, d, n, n2, spartanLocation2, a2, b5);
            if (!PlayerData.b(spartanPlayer, false)) {
                if (GroundUtils.ak(spartanPlayer)) {
                    d2 = 0.68;
                }
                else if (SelfHit.E(spartanPlayer) && 0.5 > d2) {
                    d2 = 0.5;
                }
                final double a3 = a(spartanPlayer, d2);
                if (Checks.getBoolean("Jesus.check_speed") && a3 > 0.0 && d >= a3 && CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=y_distance") && CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=air_ticks")) {
                    boolean b7 = false;
                    if (d2 == 0.19) {
                        if (d - d2 >= 0.3 || AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=safety", 20) >= 5) {
                            b7 = true;
                        }
                    }
                    else {
                        b7 = true;
                    }
                    if (b7) {
                        a(spartanPlayer, spartanLocation2, "t: speed(" + j + "), d: " + d + ", dm: " + d2 + ", dm_s: " + a3);
                    }
                }
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s) {
        final SpartanLocation a = spartanPlayer.a();
        EventsHandler1.a(spartanPlayer, Jesus.a, spartanLocation.b().b(0.0, -(a.getY() - a.getBlockY()), 0.0), s, true, true);
    }
    
    private static boolean e(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        return BlockUtils.c(spartanPlayer, true, n, n2, n3) && BlockUtils.a(spartanPlayer, true, n, n2, n3);
    }
    
    private static double a(final SpartanPlayer spartanPlayer, final double n) {
        double n2 = n;
        if (l(spartanPlayer) < 0) {
            double n3;
            if (n != 0.45) {
                if (n == 0.19 || n == 0.15) {
                    n3 = n2 + 0.48;
                }
                else {
                    n3 = n2 + 0.35;
                }
            }
            else {
                n3 = n2 + 0.2;
            }
            n2 = n3 + PlayerData.b(spartanPlayer);
        }
        if (!CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=ice")) {
            n2 += 0.6;
        }
        if (Register.v1_13) {
            final boolean b = n != 0.17;
            if (PlayerData.aC(spartanPlayer)) {
                final double n4 = (double)n(spartanPlayer);
                n2 += 0.35 * ((n4 == 3.0) ? 2.55 : ((n4 == 2.0) ? 2.3 : ((n4 == 1.0) ? 2.05 : 1.0)));
            }
            else if (PlayerData.aV(spartanPlayer)) {
                if (b) {
                    n2 += 0.25;
                }
                else {
                    n2 += 0.05;
                }
            }
            else {
                n2 += ((n < 0.2 && b) ? 0.1 : 0.05);
            }
        }
        if (l(spartanPlayer) < 0 && PlayerData.aL(spartanPlayer) && (n == 0.45 || n == 0.15 || n == 0.19)) {
            n2 += 0.2;
        }
        else if (PlayerData.aD(spartanPlayer)) {
            n2 += 0.1;
        }
        return MoveUtils.a(spartanPlayer, n2, 8.0, PotionEffectType.SPEED);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (VL.b(spartanPlayer, Jesus.a, true)) {
            return false;
        }
        if (!b(spartanPlayer, spartanLocation, true) || !b(spartanPlayer, spartanLocation2, true)) {
            CooldownUtils.d(spartanPlayer, Jesus.a.toString() + "=invalid=data", 10);
        }
        final Entity vehicle = spartanPlayer.getVehicle();
        return (vehicle == null || vehicle instanceof Pig || vehicle instanceof Horse) && (!Damage.E(spartanPlayer) && !PlayerData.b(spartanPlayer, 1, 0.5) && !PlayerData.aS(spartanPlayer) && !Explosion.E(spartanPlayer) && !PlayerData.aF(spartanPlayer) && !FishingHook.E(spartanPlayer)) && CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=invalid=data") && !BlockBreak.E(spartanPlayer) && (!BlockUtils.a(spartanPlayer, true, 0.298, 1.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, 0.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, -1.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, -2.0, 0.298));
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final boolean b) {
        final SpartanBlock a = spartanLocation.a();
        final byte data = a.getData();
        return !a.isLiquid() || data == 0 || data == 8 || (b && spartanPlayer.a().a() != a);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final SpartanLocation spartanLocation, final long n, final boolean b, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3, final SpartanLocation spartanLocation4) {
        if (!Checks.getBoolean("Jesus.check_upwards_movement") || !b || GroundUtils.ak(spartanPlayer) || n(spartanPlayer) > 0 || PlayerData.b(spartanPlayer, false) || PlayerData.az(spartanPlayer) || FloorProtection.E(spartanPlayer) || BouncingBlocks.R(spartanPlayer) || l(spartanPlayer) <= -10) {
            return;
        }
        if (!BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) || !BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0) || !BlockUtils.c(spartanPlayer, true, 1.0, -1.0, 1.0)) {
            final SpartanLocation a = spartanPlayer.a();
            if (!a.b().b(0.0, 1.0 - (a.getY() - a.getBlockY()), 0.0).a().isLiquid() && !spartanLocation2.a().isLiquid()) {
                return;
            }
        }
        int i = 0;
        final double b2 = Values.b(d, 2);
        final double n2 = WaterSoulSand.E(spartanPlayer) ? 0.8 : ((spartanPlayer.getFireTicks() > 0) ? 0.55 : ((SelfHit.E(spartanPlayer) || Register.v1_13 || n <= 1000L) ? 0.45 : (PlayerData.aG(spartanPlayer) ? 0.248 : (Register.v1_13 ? 0.2 : 0.17))));
        if (BlockUtils.c(spartanPlayer, true, 1.0, -1.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0)) {
            i = 1;
        }
        else if (BlockUtils.c(spartanPlayer, true, 0.298, -1.0, 0.298) && BlockUtils.c(spartanPlayer, true, 0.298, 0.0, 0.298) && BlockUtils.c(spartanPlayer, true, 0.298, 1.0, 0.298)) {
            i = 2;
        }
        if (i != 2) {
            for (int j = -1; j <= 1; ++j) {
                if (!BlockUtils.g(spartanPlayer, true, 1.0, j, 1.0) && !BlockUtils.h(spartanPlayer, true, 1.0, j, 1.0)) {
                    i = 3;
                    break;
                }
            }
        }
        final SpartanLocation a2 = spartanPlayer.a();
        if (i != 0 && d >= n2 && !spartanLocation2.a().isLiquid() && (a2.a().isLiquid() || spartanLocation3.a().isLiquid() || spartanLocation4.a().isLiquid())) {
            final boolean b3 = Math.round(d * 100.0) == 34.0 || Math.round(d * 10.0) == 3.0;
            if (i == 2 && (b3 || b2 == 0.5)) {
                if (AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=upwards=irregular", 5) >= 3) {
                    a(spartanPlayer, spartanLocation, "t: upwards(irregular), dy: " + d);
                }
            }
            else if (i == 3 || i == 2) {
                if (d >= 0.48) {
                    a(spartanPlayer, spartanLocation, "t: upwards(half-solid), dy: " + d + ", case: " + i);
                }
                else if (d >= 0.24 && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=upwards=half-solid", 5) >= 3) {
                    a(spartanPlayer, spartanLocation, "t: upwards[half-solid(repeated)], dy: " + d);
                }
            }
            else if (l(spartanPlayer) >= 0 && !MoveUtils.b(d) && !b3) {
                a(spartanPlayer, spartanLocation, "t: upwards(after), dy: " + d + ", l: " + n2);
            }
            else if (((d >= (SelfHit.E(spartanPlayer) ? 0.42 : 0.24) && !BouncingBlocks.R(spartanPlayer)) || d >= 3.0) && !PlayerData.az(spartanPlayer) && (!b3 || new LowViolation(spartanPlayer, Jesus.a, "upwards(before)").q())) {
                a(spartanPlayer, spartanLocation, "t: upwards(before), dy: " + d + ", l: " + n2);
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double d) {
        if (!Checks.getBoolean("Jesus.check_liquid_exit") || PlayerData.az(spartanPlayer) || GroundUtils.ak(spartanPlayer) || PlayerData.aL(spartanPlayer) || spartanLocation.a().isLiquid() || !spartanLocation2.a().isLiquid() || BouncingBlocks.R(spartanPlayer) || d <= Liquid.f(spartanPlayer)) {
            return;
        }
        a(spartanPlayer, spartanLocation2, "t: exit, dy: " + d);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final double d, final double n2, final SpartanLocation spartanLocation, final long n3, final boolean b, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3) {
        final SpartanLocation a = spartanPlayer.a();
        if (PlayerData.aV(spartanPlayer) || !Checks.getBoolean("Jesus.check_walking") || !b || GroundUtils.ak(spartanPlayer) || PlayerData.b(spartanPlayer, false) || FloorProtection.E(spartanPlayer) || l(spartanPlayer) < 0 || n(spartanPlayer) > 0 || BouncingBlocks.R(spartanPlayer) || LatencyUtils.e(spartanPlayer, 220) || WaterSoulSand.E(spartanPlayer) || TPS.u() || n3 <= 1000L || !BlockUtils.d(a)) {
            return;
        }
        int i = 0;
        final boolean b2 = a.a().isLiquid() && BlockUtils.c(spartanPlayer, true, 0.298, -0.6, 0.298);
        final boolean b3 = n >= 0.14;
        if (b2 && b3 && !f(spartanPlayer, spartanLocation2) && (n2 >= 0.5 || (n2 > 0.0 && n >= 0.16))) {
            i = 1;
        }
        else if (!f(spartanPlayer, a) && !f(spartanPlayer, spartanLocation2) && spartanLocation3.a().isLiquid()) {
            i = 2;
        }
        if (i != 0 && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=walking=normal", 20) >= 16 && (!Register.v1_13 || new LowViolation(spartanPlayer, Jesus.a, "walking-normal").q())) {
            a(spartanPlayer, spartanLocation, "t: walking(normal), d: " + n + ", r: " + n2 + ", c: " + i);
        }
        final double j = MoveUtils.i(spartanPlayer);
        final double abs = Math.abs(j - n);
        if (b2 && j >= 0.1 && abs >= 0.1 && d >= -0.1 && d <= 0.075 && b3 && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=walking=abnormal", 20) >= 4 && (!Register.v1_13 || new LowViolation(spartanPlayer, Jesus.a, "walking-abnormal").q())) {
            a(spartanPlayer, spartanLocation, "t: walking(abnormal), d: " + n + ", cd: " + j + ", diff: " + abs + ", dy: " + d + ", r: " + n2);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final double d2, final double d3, final SpartanLocation spartanLocation, final long n, final boolean b) {
        final SpartanLocation a = spartanPlayer.a();
        if (PlayerData.aV(spartanPlayer) || !Checks.getBoolean("Jesus.check_swimming") || GroundUtils.ak(spartanPlayer) || l(spartanPlayer) < 0 || d < 0.1 || d2 >= 0.15 || FloorProtection.E(spartanPlayer) || BouncingBlocks.R(spartanPlayer) || BlockBreak.E(spartanPlayer) || !b || LatencyUtils.e(spartanPlayer, 220) || WaterSoulSand.E(spartanPlayer) || TPS.u() || n(spartanPlayer) > 0 || n <= 1000L || !BlockUtils.d(a)) {
            return;
        }
        final boolean b2 = !Register.v1_13 || (d3 < 0.5 && d3 > 0.8) || d >= 0.14;
        final boolean liquid = a.a().isLiquid();
        final SpartanLocation b3 = a.b(0.0, 0.3, 0.0);
        final boolean b4 = !BlockUtils.a(spartanPlayer, b3, MaterialUtils.a("water"), 0.298) && !BlockUtils.a(spartanPlayer, b3, MaterialUtils.a("lava"), 0.3);
        final SpartanLocation b5 = b3.b(0.0, 0.7, 0.0);
        final boolean b6 = !BlockUtils.a(spartanPlayer, b5, MaterialUtils.a("water"), 0.298) && !BlockUtils.a(spartanPlayer, b5, MaterialUtils.a("lava"), 0.3);
        if (liquid && b4 && b6 && b2 && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=swimming", 20) >= 5 && (!Register.v1_13 || new LowViolation(spartanPlayer, Jesus.a, "swimming").q())) {
            a(spartanPlayer, spartanLocation, "t: swimming, d: " + d + ", dy: " + d2 + ", r: " + d3);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3, final SpartanLocation spartanLocation) {
        if (PlayerData.aV(spartanPlayer) || PlayerData.aB(spartanPlayer)) {
            return;
        }
        final double a = DoubleUtils.a(spartanPlayer, Jesus.a.toString() + "=horizontal=dy");
        if (!Checks.getBoolean("Jesus.check_horizontal_movement") || !DoubleUtils.h(a) || FloorProtection.E(spartanPlayer) || BouncingBlocks.R(spartanPlayer) || GroundUtils.ak(spartanPlayer)) {
            DoubleUtils.a(spartanPlayer, Jesus.a.toString() + "=horizontal=dy", n2);
            return;
        }
        final SpartanLocation a2 = spartanPlayer.a();
        if (BlockUtils.c(spartanPlayer, true, 0.298, -((n3 == 0.5) ? 0.6 : 0.01), 0.298) && BlockUtils.c(spartanPlayer, true, 0.298, 0.0, 0.298) && BlockUtils.c(spartanPlayer, true, 0.298, 1.0, 0.298) && BlockUtils.c(spartanPlayer, true, 0.298, 2.0, 0.298) && (BlockUtils.b(spartanPlayer, a2, MaterialUtils.a("water"), 0.298) || BlockUtils.b(spartanPlayer, a2, MaterialUtils.a("lava"), 0.298))) {
            final double abs = Math.abs(n2 - a);
            if (n2 == 0.0 && !PlayerData.aY(spartanPlayer) && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=horizontal=normal", 6) == 5) {
                AttemptUtils.m(spartanPlayer, Jesus.a.toString() + "=horizontal=normal");
                if (!Register.v1_13 || new LowViolation(spartanPlayer, Jesus.a, "horizontal").q()) {
                    a(spartanPlayer, spartanLocation, "t: horizontal(normal), d: " + n);
                }
            }
            if (abs <= 0.07 && n >= 0.14 && n(spartanPlayer) == 0 && !SelfHit.E(spartanPlayer) && l(spartanPlayer) >= 0 && PlayerData.az(spartanPlayer) && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=horizontal=cached", 20) == 9) {
                AttemptUtils.m(spartanPlayer, Jesus.a.toString() + "=horizontal=cached");
                a(spartanPlayer, spartanLocation, "t: horizontal(cached), d: " + n + ", ds: " + abs);
            }
        }
        DoubleUtils.a(spartanPlayer, Jesus.a.toString() + "=horizontal=dy", n2);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (!Checks.getBoolean("Jesus.check_repeated_movement") || n(spartanPlayer) > 3 || FloorProtection.E(spartanPlayer) || BouncingBlocks.R(spartanPlayer)) {
            return;
        }
        double d = 0.1;
        if (n(spartanPlayer) == 1) {
            d = 0.175;
        }
        else if (n(spartanPlayer) == 2) {
            d = 0.225;
        }
        else if (n(spartanPlayer) == 3) {
            d = 0.275;
        }
        if (Register.v1_13) {
            d += 0.1;
        }
        if (n >= d && !BlockUtils.f(spartanPlayer, spartanLocation2) && !spartanLocation2.a().isLiquid()) {
            final double b = Values.b(n, 12);
            final long a = MillisUtils.a(spartanPlayer, Jesus.a.toString() + "=repeated=" + b);
            if (a <= 50L && !String.valueOf(b).contains("E-") && AttemptUtils.b(spartanPlayer, Jesus.a.toString() + "=repeated", 20) == 4) {
                a(spartanPlayer, spartanLocation, "t: repeated, ms: " + a + ", d: " + b + ", l: " + d);
            }
            MillisUtils.o(spartanPlayer, Jesus.a.toString() + "=repeated=" + b);
        }
    }
    
    private static void v(final SpartanPlayer spartanPlayer) {
        if (PlayerData.b(spartanPlayer) > 0.0f) {
            AttemptUtils.c(spartanPlayer, Jesus.a.toString() + "=normal", -36);
        }
        else {
            AttemptUtils.c(spartanPlayer, Jesus.a.toString() + "=normal", -18);
        }
    }
    
    private static void w(final SpartanPlayer spartanPlayer) {
        if (CooldownUtils.g(spartanPlayer, Jesus.a.toString() + "=y_distance")) {
            AttemptUtils.c(spartanPlayer, Jesus.a.toString() + "=ypos", -15);
        }
        else {
            AttemptUtils.c(spartanPlayer, Jesus.a.toString() + "=ypos", -30);
        }
    }
    
    private static int l(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, Jesus.a.toString() + "=normal");
    }
    
    private static int m(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, Jesus.a.toString() + "=ypos");
    }
    
    private static void c(final SpartanPlayer spartanPlayer, final double n, final double n2) {
        if (n > 0.0) {
            AttemptUtils.a(spartanPlayer, Jesus.a.toString() + "=normal", 1);
        }
        if (n2 != 0.0) {
            AttemptUtils.a(spartanPlayer, Jesus.a.toString() + "=ypos", 1);
        }
    }
    
    private static long a(final SpartanPlayer spartanPlayer, final Material material) {
        if (BlockUtils.g(material)) {
            MillisUtils.o(spartanPlayer, Jesus.a.toString() + "=in-and-out=in");
            return MillisUtils.a(spartanPlayer, Jesus.a.toString() + "=in-and-out=out");
        }
        MillisUtils.o(spartanPlayer, Jesus.a.toString() + "=in-and-out=out");
        return MillisUtils.a(spartanPlayer, Jesus.a.toString() + "=in-and-out=in");
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final Material material, final double n, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3) {
        final SpartanLocation a = spartanPlayer.a();
        final double n2 = a.getY() - a.getBlockY();
        return spartanLocation.a().getType() == material || BlockUtils.b(spartanPlayer, a, material, n) || BlockUtils.b(spartanPlayer, a.b().b(0.0, -n2, 0.0), material, n) || BlockUtils.b(spartanPlayer, spartanLocation2, material, n) || (BlockUtils.c(spartanPlayer, true, 0.298, -1.0, 0.298) && BlockUtils.b(spartanPlayer, spartanLocation3, material, n));
    }
    
    private static boolean f(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return BlockUtils.f(spartanPlayer, spartanLocation) || spartanLocation.a().isLiquid();
    }
    
    private static int n(final SpartanPlayer spartanPlayer) {
        final VersionUtils.a a = VersionUtils.a();
        final ItemStack boots = spartanPlayer.a().getBoots();
        return (a != VersionUtils.a.c && a != VersionUtils.a.l && boots != null) ? boots.getEnchantmentLevel(Enchantment.DEPTH_STRIDER) : 0;
    }
    
    static {
        a = Enums.HackType.Jesus;
    }
}
