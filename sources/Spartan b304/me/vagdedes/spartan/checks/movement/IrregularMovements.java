package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.h.a.Liquid;
import java.util.Iterator;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.system.VL;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.h.BowProtection;
import me.vagdedes.spartan.h.BlockBreak;
import me.vagdedes.spartan.c.ViaRewind;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.f.TPS;
import org.bukkit.Material;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.LowViolation;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.h.ShulkerBox;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class IrregularMovements
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, SpartanLocation> z;
    
    public IrregularMovements() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        IrregularMovements.z.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        IrregularMovements.z.clear();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        a(spartanPlayer);
        DoubleUtils.j(spartanPlayer, IrregularMovements.a.toString() + "=h");
        DoubleUtils.j(spartanPlayer, IrregularMovements.a.toString() + "=diff");
    }
    
    public static void t(final SpartanPlayer spartanPlayer) {
        if (!FloorProtection.E(spartanPlayer)) {
            AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=combat");
        }
    }
    
    public static void a(final boolean b, final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3, final double n4, final int n5, final boolean b2, final boolean b3) {
        if (b(spartanPlayer)) {
            final double abs = Math.abs(n - n4);
            final SpartanLocation b4 = spartanLocation.b().b(0.0, -1.0, 0.0);
            if (b) {
                a(spartanPlayer, n, n4, n2, abs, n5, spartanLocation, spartanLocation2, b4);
                a(spartanPlayer, n, n2, spartanLocation, spartanLocation2, b4);
                a(spartanPlayer, n, n4, n2, n3, b3, spartanLocation, spartanLocation2, b4);
                a(spartanPlayer, n, n4, n2, abs, n5, spartanLocation, spartanLocation2);
            }
            else {
                a(spartanPlayer, n, b2, spartanLocation2, b4);
            }
        }
        b(spartanPlayer, n, n4);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final double d2, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3) {
        if (!Checks.getBoolean("IrregularMovements.check_step_hacking")) {
            return;
        }
        double d3;
        final double n = d3 = 0.601;
        final double b = MoveUtils.b(spartanPlayer, spartanLocation2);
        final double b2 = Values.b(b, 10);
        final boolean e = Damage.E(spartanPlayer);
        final boolean r = r(spartanPlayer);
        final boolean b3 = MoveUtils.b(d);
        final boolean az = PlayerData.az(spartanPlayer);
        final boolean aa = PlayerData.aA(spartanPlayer);
        final boolean r2 = BouncingBlocks.R(spartanPlayer);
        final boolean g = BlockUtils.G(spartanPlayer, spartanLocation);
        final boolean e2 = ShulkerBox.E(spartanPlayer);
        final boolean e3 = Piston.E(spartanPlayer);
        final boolean e4 = SelfHit.E(spartanPlayer);
        final boolean e5 = WaterSoulSand.E(spartanPlayer);
        final boolean e6 = Building.E(spartanPlayer);
        final LowViolation lowViolation = new LowViolation(spartanPlayer, IrregularMovements.a, "step");
        if (Explosion.E(spartanPlayer)) {
            d3 = 5.0;
        }
        else if (r2 || (BlockUtils.b(spartanPlayer, 3) && d <= -1.0)) {
            d3 = 3.6;
        }
        else if (FishingHook.E(spartanPlayer)) {
            d3 = 3.1;
        }
        else if (r && MoveUtils.c(spartanPlayer, d)) {
            d3 = 2.0;
        }
        else if (e3) {
            d3 = 1.6;
        }
        else if (e2 || Damage.E(spartanPlayer)) {
            d3 = 1.3;
        }
        else if (BlockUtils.b(spartanPlayer, 1)) {
            d3 = 1.1;
        }
        else if (e6) {
            d3 = 1.0;
        }
        else if (WaterSoulSand.E(spartanPlayer) || Damage.E(spartanPlayer)) {
            d3 = 0.8;
        }
        else if (BlockUtils.c(spartanPlayer, 1)) {
            d3 = 0.7;
        }
        if (az) {
            final double c = MoveUtils.c(spartanPlayer, 0.41999);
            d3 = ((c > d3) ? c : d3);
        }
        if (IrregularMovements.z.containsKey(spartanPlayer.getUniqueId()) && (((SpartanLocation)IrregularMovements.z.get(spartanPlayer.getUniqueId())).getWorld() != spartanLocation2.getWorld() || ((SpartanLocation)IrregularMovements.z.get(spartanPlayer.getUniqueId())).a(spartanLocation2) >= 1.0)) {
            IrregularMovements.z.remove(spartanPlayer.getUniqueId());
        }
        if (d >= d3) {
            a(spartanPlayer, spartanLocation2, "t: step(high), d: " + d + ", dm: " + d3);
        }
        else if (d == 0.25 && d3 == n && !r && !e && !b3 && !r2 && !g && !EnderPearl.E(spartanPlayer) && !FloorProtection.E(spartanPlayer) && (BlockUtils.g(spartanPlayer, true, 1.0, 0.0, 1.0) || d != 0.25) && !BlockUtils.k(spartanPlayer, spartanLocation3) && !e5 && !BlockUtils.a(spartanPlayer, spartanLocation, Material.SNOW, 0.3)) {
            a(spartanPlayer, spartanLocation2, "t: step(low), d: " + d, lowViolation.q(), false);
        }
        else if (!e3 && !e5 && BlockUtils.l(spartanPlayer, true, 0.3, 0.0, 0.3) && BlockUtils.l(spartanPlayer, true, 0.3, -1.0, 0.3) && BlockUtils.l(spartanPlayer, true, 0.3, -d2, 0.3)) {
            final double d4 = spartanLocation2.getY() - spartanLocation2.getBlockY();
            if (d >= 0.08 && d % 0.5 != 0.0 && b >= 0.248 && !PlayerData.aw(spartanPlayer) && !TPS.u() && !b3 && !az && !e && !r2 && !g && !r && !e2) {
                AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=step=normal", 3);
                if (!MoveUtils.a(spartanPlayer, d, b) && b >= 1.25) {
                    final int a = LagManagement.a(spartanPlayer, 1);
                    if (AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=step=high", 5) >= a) {
                        if (a == 1) {
                            AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=step=high");
                        }
                        a(spartanPlayer, spartanLocation2, "t: step[packets(high)], d: " + d + ", c: " + b + ", l: " + a, lowViolation.q(), false);
                    }
                }
                else if (!b3) {
                    if (AttemptUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=step=normal") >= 2) {
                        a(spartanPlayer, spartanLocation2, "t: step[packets(normal)], d: " + d + ", c: " + b, lowViolation.q(), false);
                    }
                    else if (MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=step=packets=" + b2) <= 200L) {
                        a(spartanPlayer, spartanLocation2, "t: step[packets(repeated)], d: " + d + ", v: " + b2, lowViolation.q(), false);
                    }
                }
                MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=step=packets=" + b2);
            }
            if (d > 0.25 && !FishingHook.E(spartanPlayer) && !r2 && !g && !r && a(spartanPlayer, spartanLocation2) && a(spartanPlayer, spartanLocation) && !aa) {
                final long a2 = MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=repeated-step");
                if (MillisUtils.hasTimer(a2)) {
                    final boolean b4 = BlockUtils.c(spartanPlayer, spartanLocation, true) || BlockUtils.h(spartanPlayer, true, 0.298, 0.0, 0.298);
                    final boolean b5 = BlockUtils.c(spartanPlayer, true, 0.298, 2.0, 0.298) || BlockUtils.h(spartanPlayer, true, 0.298, 2.0, 0.298);
                    final double n2 = Damage.E(spartanPlayer) ? 0.6 : (e4 ? 0.401 : (r ? 0.33 : (GroundUtils.ak(spartanPlayer) ? 0.28 : 0.25)));
                    if (a2 > 55L && a2 <= 100L && (d != 0.5 || b4) && b5 && d >= n2) {
                        final int a3 = LagManagement.a(spartanPlayer, (e || (b3 && d < 0.33319) || ViaRewind.isLoaded()) ? 4 : ((b3 && DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=y") != d) ? 3 : 2));
                        if (AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=repeated-step", 6) >= a3) {
                            a(spartanPlayer, spartanLocation2, "t: step(repeated), d: " + d + ", ms: " + a2 + ", l: " + a3);
                        }
                    }
                }
                MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=repeated-step");
            }
            if (!e && d >= 0.34 && !r2 && d2 != 0.5 && !b3 && !e6 && !az && !BlockBreak.E(spartanPlayer) && !ShulkerBox.E(spartanPlayer) && !r) {
                for (int i = -1; i <= 1; ++i) {
                    if (!d(spartanPlayer, 1.0, i, 1.0)) {
                        return;
                    }
                }
                final double abs = Math.abs(d2 - d4);
                final double abs2 = Math.abs(d - abs);
                if (abs != d && d2 != d4 && abs2 >= 0.35) {
                    a(spartanPlayer, spartanLocation2, "t: step(difference), d: " + d + ", r: " + d2 + ", or: " + d4 + ", diff: " + abs2);
                }
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final double d, final double n2, final double n3, final int n4, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3) {
        if (!Checks.getBoolean("IrregularMovements.check_jump_movement") || Damage.E(spartanPlayer) || BlockUtils.G(spartanPlayer, spartanLocation) || FishingHook.Y(spartanPlayer) || (n2 == 0.5 && n != 0.5) || r(spartanPlayer) || Piston.E(spartanPlayer) || EnderPearl.E(spartanPlayer) || WaterSoulSand.E(spartanPlayer) || BlockUtils.ag(spartanPlayer)) {
            return;
        }
        final boolean e = SelfHit.E(spartanPlayer);
        final int fireTicks = spartanPlayer.getFireTicks();
        final boolean b = !BlockUtils.c(spartanPlayer, true, 0.298, 2.1, 0.298) && !MoveUtils.b(n) && n < 0.3 && n4 <= 20;
        final boolean b2 = !BlockUtils.c(spartanPlayer, true, 0.298, 0.0, 0.298);
        final int n5 = PlayerData.aG(spartanPlayer) ? 8 : ((fireTicks > 0 || FloorProtection.E(spartanPlayer) || (!BlockUtils.c(spartanPlayer, true, 0.298, 2.0, 0.298) && !b2)) ? 5 : 1);
        final int n6 = BowProtection.E(spartanPlayer) ? 20 : (e ? 8 : ((n5 <= 1) ? 2 : n5));
        if (n4 >= n5 && ((!SelfHit.Z(spartanPlayer) && MoveUtils.d(spartanPlayer, n)) || MoveUtils.c(n)) && (n != n2 || n < 0.419 || n4 > 1) && (!FloorProtection.E(spartanPlayer) || fireTicks > 0)) {
            String str = null;
            final boolean d2 = MoveUtils.d(spartanPlayer, d);
            final double abs = Math.abs(MoveUtils.g(spartanPlayer) - n3);
            final boolean b3 = Values.b(n2, 4) == 0.2874 && Values.b(n, 3) == 0.164 && n4 <= 20;
            final boolean b4 = spartanLocation.a().getType() == MaterialUtils.a("daylight_detector") || spartanLocation3.a().getType() == MaterialUtils.a("daylight_detector");
            if (n >= d && d2 && fireTicks <= 0) {
                str = "(new jump value must be smaller)";
            }
            else if (n > 0.0 && n != n2 && n != n2 - 0.5 && !MoveUtils.a(n2, 5) && ((Values.b(n, 3) < 0.419 && d <= -0.03) || d <= -0.3) && !PlayerData.az(spartanPlayer) && !ViaRewind.isLoaded()) {
                str = "(start cannot be bound to negative values)";
            }
            else if (n3 >= 0.09 && d2 && !PlayerData.az(spartanPlayer)) {
                str = "(value difference is too big)";
            }
            else if (n < d && d2 && n4 > 5 && !PlayerData.az(spartanPlayer) && (fireTicks <= 0 || n4 >= 8) && !b3 && !b4 && BlockUtils.g(spartanPlayer, true, 0.298, -1.0, 0.298) && BlockUtils.g(spartanPlayer, true, 0.298, 0.0, 0.298) && (Math.abs(abs - 0.0784000015258) > 1.0E-4 || !SelfHit.E(spartanPlayer)) && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) && (!MoveUtils.a(spartanPlayer, n, n2) || !BlockUtils.c(spartanPlayer, true, 0.298, 0.0, 0.298) || !BlockUtils.c(spartanPlayer, true, 0.298, 1.0, 0.298))) {
                str = "(jump movement must start on ground)";
            }
            else if (n4 > 12 && abs >= 0.08 && d >= 0.0 && !MoveUtils.d(n2) && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -0.25, 0.0)) {
                str = "(air-ticks must be analogous to the jump)";
            }
            if (n < d && n4 >= 6 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=jump=illegal=frequency", 11) >= 5 && str == null) {
                str = "(frequency cannot be too fast)";
            }
            if (str != null) {
                int n7 = 1;
                int n8 = 1;
                if (Damage.E(spartanPlayer)) {
                    n7 = 4;
                    n8 = 2;
                }
                if (AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=jump=illegal", n7) >= n8) {
                    final String string = "t: jump(illegal), d: " + n + ", od: " + d + ", a: " + n4 + ", r: " + n2 + ", c: " + str + ", diff: " + abs + ", f: " + fireTicks;
                    if (a(n, d) || (BlockUtils.g(spartanPlayer, true, 0.298, 0.0, 0.298) && BlockUtils.g(spartanPlayer, true, 0.298, 1.0, 0.298))) {
                        a(spartanPlayer, spartanLocation2, string);
                    }
                    else {
                        a(spartanPlayer, spartanLocation2, string, true, true);
                    }
                }
            }
        }
        else if (n >= 0.08 && !BouncingBlocks.R(spartanPlayer) && !Building.E(spartanPlayer) && !b && !MoveUtils.b(n) && !MoveUtils.b(n2) && !PlayerData.az(spartanPlayer) && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -0.6, 0.0) && n2 >= 0.1 && (n4 >= n6 || n >= 0.76 || (n >= 0.42 && n4 == 0)) && !BlockUtils.o(spartanPlayer, spartanLocation) && !TPS.u() && !NoHitDelay.E(spartanPlayer)) {
            int n9 = 1;
            int n10 = 1;
            if (Damage.E(spartanPlayer)) {
                n9 = 4;
                n10 = 2;
            }
            else if ((n < 0.13 && n4 <= 30) || e) {
                n9 = 8;
                n10 = 2;
            }
            if (AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=jump=unusual", n9) >= n10) {
                a(spartanPlayer, spartanLocation2, "t: jump(unusual), d: " + n + ", a: " + n4 + ", r: " + n2);
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final double d2, final double d3, final double d4, final int i, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (!Checks.getBoolean("IrregularMovements.check_bouncing_blocks") || d == d3 || !BlockUtils.c(spartanPlayer, true, 1.0, 2.0, 1.0) || !BlockUtils.c(spartanPlayer, true, 1.0, 3.0, 1.0) || r(spartanPlayer) || BlockBreak.E(spartanPlayer) || NoHitDelay.E(spartanPlayer) || (FloorProtection.E(spartanPlayer) && !BouncingBlocks.V(spartanPlayer)) || !BouncingBlocks.R(spartanPlayer) || BlockUtils.G(spartanPlayer, spartanLocation) || PlayerData.aA(spartanPlayer) || PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || WaterSoulSand.E(spartanPlayer) || PlayerData.aT(spartanPlayer) || BlockUtils.ag(spartanPlayer)) {
            return;
        }
        final String s = BouncingBlocks.T(spartanPlayer) ? "slime" : (BouncingBlocks.V(spartanPlayer) ? "bed" : "none");
        final boolean b = BlockUtils.b(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.b(spartanPlayer, true, 1.0, -1.0, 1.0) && BlockUtils.b(spartanPlayer, true, 1.0, -2.0, 1.0);
        final boolean e = Piston.E(spartanPlayer);
        if (d > 0.0 && i > 0) {
            final boolean e2 = SelfHit.E(spartanPlayer);
            final double a = DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=sd");
            final boolean h = DoubleUtils.h(a);
            final double n = (h ? a : 0.0) * 0.085;
            final double d5 = (Damage.E(spartanPlayer) || Piston.Y(spartanPlayer) || e2) ? 1.0 : (BouncingBlocks.V(spartanPlayer) ? (n / 1.5) : n);
            final double abs = Math.abs(d - d5);
            int j = 0;
            if (h && n > 0.0) {
                if (abs >= 0.75 && i > 1) {
                    j = 1;
                }
                else if (abs >= 0.51 && d >= 0.6) {
                    j = 2;
                }
                else if (d == abs && d >= 0.248 && i >= 6 && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !e2 && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) && (!MoveUtils.b(d) || !MoveUtils.a(spartanPlayer, d, d3))) {
                    j = 3;
                }
            }
            if (d >= 0.1 && d >= d5 && j != 0) {
                a(spartanPlayer, spartanLocation2, "t: bouncing-blocks[calculated(" + s + ")], d: " + d + ", l: " + d5 + ", diff: " + abs + ", a: " + i + ", c: " + j + ", r: " + d3);
            }
        }
        if (d2 <= 0.0 && d > 0.0 && b && !Damage.E(spartanPlayer) && (i >= 20 || i <= 6 || !MoveUtils.b(d))) {
            a(spartanPlayer, spartanLocation2, "type: bouncing-blocks[stable(" + s + ")], d: " + d + ", air: " + i);
        }
        else if (MoveUtils.b(d) && i >= 20 && !e) {
            a(spartanPlayer, spartanLocation2, "type: bouncing-blocks[jump(" + s + ")], d: " + d + ", air: " + i);
        }
        else if (spartanLocation.getBlockY() > spartanLocation2.b().b(0.0, d3, 0.0).getBlockY()) {
            if (d > d2 && d != 0.5 && s.equals("slime") && !e) {
                a(spartanPlayer, spartanLocation2, "type: bouncing-blocks[illegal(" + s + ")], d: " + d + ", od: " + d2);
            }
            if (d > 0.0) {
                final double b2 = Values.b(d, 10);
                if (MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=bouncing-blocks=repeated=" + b2) <= 250L) {
                    a(spartanPlayer, spartanLocation2, "type: bouncing-blocks[repeated(" + s + ")], d: " + d);
                }
                MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=bouncing-blocks=repeated=" + b2);
            }
            final double a2 = DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=diff");
            if (DoubleUtils.h(a2)) {
                final double d6 = a2 + 0.4;
                if (d4 >= d6 && d4 != 0.5 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=bouncing-blocks=difference", 20) >= 2) {
                    a(spartanPlayer, spartanLocation2, "t: bouncing-blocks[difference(" + s + ")], ds: " + d4 + ", ods: " + d6);
                }
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final boolean b, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (!Checks.getBoolean("IrregularMovements.check_block_climbing") || Damage.E(spartanPlayer) || r(spartanPlayer) || FloorProtection.E(spartanPlayer) || FishingHook.Y(spartanPlayer) || EnderPearl.E(spartanPlayer)) {
            return;
        }
        if (spartanLocation2.a().isLiquid()) {
            return;
        }
        final SpartanLocation a = spartanPlayer.a();
        if (BlockUtils.r(spartanPlayer, a) || BlockUtils.q(spartanPlayer, a)) {
            return;
        }
        final SpartanLocation b2 = a.b().b(0.0, 1.0, 0.0);
        final SpartanLocation b3 = a.b().b(0.0, 2.0, 0.0);
        final boolean a2 = a(spartanPlayer, spartanLocation2, 0.3);
        final boolean b4 = b || a2;
        final boolean b5 = (BlockUtils.H(spartanPlayer, spartanLocation2) && BlockUtils.H(spartanPlayer, a) && !b4) || (!BlockUtils.f(spartanPlayer, spartanLocation2) && BlockUtils.H(spartanPlayer, a) && !a2) || (BlockUtils.H(spartanPlayer, spartanLocation2) && BlockUtils.H(spartanPlayer, b2) && !BlockUtils.f(spartanPlayer, a) && !a2);
        final boolean b6 = (a.a().getType() == Material.VINE || b2.a().getType() == Material.VINE) && spartanLocation2.a().getType() == Material.LADDER;
        final boolean b7 = !BlockUtils.f(spartanPlayer, spartanLocation2) && BlockUtils.h(spartanPlayer, true, 0.298, -1.0, 0.298) && !BlockUtils.f(spartanPlayer, b3);
        int a3 = AttemptUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=climbing=in");
        if (b5 && !b6 && !b7) {
            ++a3;
            AttemptUtils.c(spartanPlayer, IrregularMovements.a.toString() + "=climbing=in", a3);
            AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=climbing=out");
            final int n = PlayerData.a(spartanPlayer, PotionEffectType.JUMP) * 3;
            if (d >= (WaterSoulSand.E(spartanPlayer) ? 0.8 : ((PlayerData.aA(spartanPlayer) && a3 <= n) ? MoveUtils.c(spartanPlayer, 0.41999) : (PlayerData.aD(spartanPlayer) ? 0.2 : 0.01))) && c(spartanPlayer, b3) && !BlockBreak.P(spartanPlayer) && (a3 >= 12 || (d >= 0.34 && a3 >= 3) || VL.a(spartanPlayer, IrregularMovements.a) >= DefaultConfiguration.f(IrregularMovements.a))) {
                if (!MoveUtils.f(d) && !MoveUtils.b(d) && (!SelfHit.E(spartanPlayer) || d >= 0.45)) {
                    if (BlockBreak.E(spartanPlayer)) {
                        a(spartanPlayer, spartanLocation, "t: climbing(ladder/vine), d: " + d + ", t: " + a3, true, true);
                    }
                    else {
                        a(spartanPlayer, spartanLocation, "t: climbing(ladder/vine), d: " + d + ", t: " + a3);
                    }
                }
                else if (!SelfHit.E(spartanPlayer)) {
                    final double b8 = Values.b(d, 5);
                    if ((b8 == 0.11215 || b8 == 0.15444 || b8 == 0.1176) && !BlockUtils.H(spartanPlayer, a)) {
                        a(spartanPlayer, spartanLocation, "t: climbing(illegal), d: " + d + ", case: 1");
                    }
                    else if ((b8 == 0.11215 || b8 == 0.15444 || b8 == 0.03684 || b8 == 0.07531) && BlockUtils.H(spartanPlayer, a)) {
                        if (AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=climbing=illegal", 5) >= 5) {
                            a(spartanPlayer, spartanLocation, "t: climbing(illegal), d: " + d + ", case: 2");
                        }
                    }
                    else if (MoveUtils.b(d) && !Building.E(spartanPlayer) && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=climbing=jumping", 5) >= 2) {
                        a(spartanPlayer, spartanLocation, "t: climbing(jumping), d: " + d + ", t: " + a3);
                    }
                }
            }
        }
        else if (a3 > 0 && AttemptUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=climbing=out", 1) >= 5) {
            AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=climbing=in");
            AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=climbing=out");
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double d, final double d2, final double d3, final double n, final boolean b, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3) {
        if (!Checks.getBoolean("IrregularMovements.check_hop_movement") || Damage.E(spartanPlayer) || PlayerData.aE(spartanPlayer) || d3 == 0.0 || d <= -2.0 || Building.E(spartanPlayer) || PlayerData.aw(spartanPlayer) || r(spartanPlayer) || SelfHit.E(spartanPlayer) || BouncingBlocks.R(spartanPlayer) || b || MoveUtils.b(d) || FloorProtection.E(spartanPlayer) || PlayerData.az(spartanPlayer) || PlayerData.aD(spartanPlayer) || WaterSoulSand.E(spartanPlayer) || !BlockUtils.d(spartanPlayer, true, 0.3, 0.0, 0.3) || BlockUtils.G(spartanPlayer, spartanLocation) || MoveUtils.f(d) || MoveUtils.f(d2) || !BlockUtils.f(spartanPlayer, true, 0.3, 0.0, 0.3) || BlockUtils.y(spartanPlayer, spartanLocation3) || FishingHook.E(spartanPlayer) || spartanLocation.getBlockY() <= 0 || d3 == n || NoHitDelay.E(spartanPlayer) || BlockUtils.ag(spartanPlayer) || !BlockUtils.e(spartanPlayer, true, 0.3, 0.0, 0.3) || !BlockUtils.e(spartanPlayer, true, 0.3, 1.0, 0.3)) {
            return;
        }
        boolean b2 = true;
        for (int i = -2; i <= 2; ++i) {
            if (!BlockUtils.g(spartanPlayer, true, 1.0, i, 1.0) || !BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0)) {
                return;
            }
            if (i >= 0 && !BlockUtils.c(spartanPlayer, true, 1.0, i, 1.0)) {
                b2 = false;
                break;
            }
        }
        if (BlockUtils.c(spartanPlayer, true, 1.0, 2.0, 1.0)) {
            boolean b3 = false;
            boolean b4 = false;
            final double n2 = (d > 0.0) ? 0.082 : 0.075;
            final double abs = Math.abs(n - d3);
            final LowViolation lowViolation = new LowViolation(spartanPlayer, IrregularMovements.a, "hop");
            String str;
            if (d >= 0.0) {
                if (d3 >= 0.05 && d3 <= 0.082) {
                    b3 = true;
                }
                if (d3 >= 0.05 && abs <= 0.082) {
                    b4 = true;
                }
                str = "up";
                if (d > 0.0) {
                    MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=hop-movement=delay");
                }
                if (MoveUtils.b(d) && !MoveUtils.b(d2) && b2 && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0))) {
                    final long a = MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=hop-movement=jump");
                    if (MillisUtils.hasTimer(a)) {
                        final int j = (a <= 350L) ? 1 : ((a <= 550L && d2 <= -0.07) ? 2 : 0);
                        if (j == 1 || (j == 2 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=hop-movement=jump", 20) >= 2)) {
                            a(spartanPlayer, spartanLocation2, "t: hop(jump), d: " + d + ", od: " + d2 + ", r: " + d3 + ", ms: " + a + ", c: " + j, lowViolation.q(), false);
                        }
                    }
                    MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=hop-movement=jump");
                }
            }
            else {
                if (d3 <= 0.075) {
                    b3 = true;
                }
                if (abs <= 0.075) {
                    b4 = true;
                }
                str = "down";
                final long a2 = MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=hop-movement=delay");
                if (a2 <= 150L && !Piston.E(spartanPlayer) && abs <= n2 && Values.b(d3, 4) != 0.7706) {
                    a(spartanPlayer, spartanLocation2, "t: hop(frequent), r: " + str + ", v: " + d3 + ", diff: " + abs + ", ms: " + a2, lowViolation.q(), false);
                }
            }
            if (b3 && d3 >= 0.003 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=hop=static", 10) == 8) {
                AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=hop=static");
                a(spartanPlayer, spartanLocation2, "t: hop(static), r: " + str + ", v: " + d3, lowViolation.q(), false);
            }
            if (b4 && abs != 0.0 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=hop=dynamic", 10) == 5) {
                AttemptUtils.m(spartanPlayer, IrregularMovements.a.toString() + "=hop=dynamic");
                a(spartanPlayer, spartanLocation2, "t: hop(dynamic), r: " + str + ", diff: " + abs, lowViolation.q(), false);
            }
            if (d3 <= n2) {
                if (d3 < 0.01) {
                    final int b5 = AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=hop=micro", 20);
                    if (b5 >= 2) {
                        a(spartanPlayer, spartanLocation2, "t: hop(micro), a: " + b5, lowViolation.q(), false);
                    }
                }
                final double b6 = Values.b(d3, 5);
                final long a3 = MillisUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=hop=repeated=" + b6);
                if (a3 <= 400L && b2 && AttemptUtils.b(spartanPlayer, IrregularMovements.a.toString() + "=hop=repeated", 20) >= 3) {
                    a(spartanPlayer, spartanLocation2, "t: hop(repeated), v: " + b6 + ", ms: " + a3, lowViolation.q(), false);
                }
                MillisUtils.o(spartanPlayer, IrregularMovements.a.toString() + "=hop=repeated=" + b6);
            }
        }
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final double a, final double n) {
        final boolean b = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -3.0, 0.0);
        final double a2 = DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=sd");
        if (a < 0.0 && (!b || a2 >= 0.47040000915479996)) {
            DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=sd", Math.abs(a) / 0.0784000015258 * 2.0);
        }
        else if (a > 0.0 && DoubleUtils.h(a2) && a2 > 0.0) {
            DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=sd", a2 - 0.5);
        }
        DoubleUtils.a(spartanPlayer, IrregularMovements.a.toString() + "=diff", Math.abs(a - n));
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, IrregularMovements.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && !PlayerData.aF(spartanPlayer) && !Explosion.E(spartanPlayer) && !PlayerData.aS(spartanPlayer);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s, final boolean b, final boolean b2) {
        if (b) {
            final boolean b3 = spartanLocation.getBlockY() >= 0;
            if (!Building.E(spartanPlayer) && !b2) {
                EventsHandler1.a(spartanPlayer, IrregularMovements.a, b3 ? spartanLocation : null, s, b3, true);
            }
            else {
                EventsHandler1.a(spartanPlayer, IrregularMovements.a, b3 ? spartanLocation : null, s, b3, false);
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s) {
        a(spartanPlayer, spartanLocation, s, true, false);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return BlockUtils.c(spartanPlayer, spartanLocation, false) || BlockUtils.G(spartanPlayer, spartanLocation);
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return BlockUtils.G(spartanPlayer, spartanLocation) || !BlockUtils.f(spartanPlayer, spartanLocation);
    }
    
    private static boolean c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanLocation, 0.298, 0.0, 0.298).iterator();
        while (iterator.hasNext()) {
            if (!b(spartanPlayer, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean d(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return !BlockUtils.G(spartanPlayer, spartanLocation) && BlockUtils.f(spartanPlayer, spartanLocation);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanLocation, n, 0.0, n).iterator();
        while (iterator.hasNext()) {
            if (d(spartanPlayer, iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean r(final SpartanPlayer spartanPlayer) {
        return Liquid.e(spartanPlayer) <= 1000L;
    }
    
    private static boolean d(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!e(spartanPlayer, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean e(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return BlockUtils.c(spartanPlayer, spartanLocation, false) || BlockUtils.u(spartanPlayer, spartanLocation) || BlockUtils.n(spartanPlayer, spartanLocation) || BlockUtils.m(spartanPlayer, spartanLocation) || BlockUtils.D(spartanPlayer, spartanLocation);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final boolean b) {
        final Material b2 = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        return BlockUtils.c(spartanPlayer, spartanLocation, b) || b2 == MaterialUtils.a("piston_extension") || b2 == MaterialUtils.a("piston_moving") || b2 == Material.DRAGON_EGG || BlockUtils.l(b2) || b2 == MaterialUtils.a("soil") || b2 == Material.SOUL_SAND || b2 == Material.CAULDRON || b2 == Material.ANVIL;
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanLocation, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!a(spartanPlayer, iterator.next(), false)) {
                return false;
            }
        }
        return true;
    }
    
    private static SpartanLocation a(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n) {
        spartanLocation2.setPitch(0.0f);
        spartanLocation2.setYaw(0.0f);
        final SpartanLocation b = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(n));
        final SpartanLocation b2 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(-n));
        spartanLocation2.setYaw(45.0f);
        final SpartanLocation b3 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(n));
        final SpartanLocation b4 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(-n));
        spartanLocation2.setYaw(90.0f);
        final SpartanLocation b5 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(n));
        final SpartanLocation b6 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(-n));
        spartanLocation2.setYaw(135.0f);
        final SpartanLocation b7 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(n));
        final SpartanLocation b8 = spartanLocation2.b().b(spartanLocation2.getDirection().multiply(-n));
        final double b9 = MathUtils.b(spartanLocation, b);
        final double b10 = MathUtils.b(spartanLocation, b2);
        final double b11 = MathUtils.b(spartanLocation, b3);
        final double b12 = MathUtils.b(spartanLocation, b4);
        final double b13 = MathUtils.b(spartanLocation, b5);
        final double b14 = MathUtils.b(spartanLocation, b6);
        final double b15 = MathUtils.b(spartanLocation, b7);
        final double min = Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(Math.min(b9, b10), b11), b12), b13), b14), b15), MathUtils.b(spartanLocation, b8));
        return (min == b9) ? b : ((min == b10) ? b2 : ((min == b11) ? b3 : ((min == b12) ? b4 : ((min == b13) ? b5 : ((min == b14) ? b6 : ((min == b15) ? b7 : b8))))));
    }
    
    private static boolean a(double b, double b2) {
        b = Values.b(b, 5);
        b2 = Values.b(b2, 5);
        return b == 0.41999 && (b2 == 0.24813 || b2 == 0.16477 || b2 == 0.08307);
    }
    
    static {
        a = Enums.HackType.IrregularMovements;
        z = new HashMap<UUID, SpartanLocation>();
    }
}
