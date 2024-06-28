package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.h.BowProtection;
import me.vagdedes.spartan.h.BlockBreak;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.h.LowViolation;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.c.ViaRewind;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Fly
{
    private static final Enums.HackType a;
    
    public Fly() {
        super();
    }
    
    public static void u(final SpartanPlayer spartanPlayer) {
        VL.a(spartanPlayer, Fly.a, 10);
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final double n) {
        if ((PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.5, 0.0)) && n >= spartanPlayer.getHealth()) {
            VL.a(spartanPlayer, Fly.a, 10);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double d, final double n, int i, final double d2, final int j) {
        if (!b(spartanPlayer)) {
            return;
        }
        for (int k = -1; k <= 2; ++k) {
            if (BlockUtils.a(spartanPlayer, spartanPlayer.a().b(0.0, (double)k, 0.0), MaterialUtils.a("web"), 0.7)) {
                CooldownUtils.d(spartanPlayer, Fly.a.toString() + "=web", 20);
                return;
            }
        }
        if (!CooldownUtils.g(spartanPlayer, Fly.a.toString() + "=web")) {
            return;
        }
        final boolean b = Liquid.e(spartanPlayer) <= 1000L || BlockUtils.I(spartanPlayer, spartanLocation.b().b(0.0, 1.0, 0.0));
        final double abs = Math.abs(d - d2);
        final int blockY = spartanPlayer.a().getBlockY();
        final double b2 = Values.b(d, 5);
        final boolean r = BouncingBlocks.R(spartanPlayer);
        final boolean e = Damage.E(spartanPlayer);
        final VersionUtils.a a = VersionUtils.a();
        final boolean b3 = !BlockUtils.e(spartanPlayer, true, 0.3, 0.0, 0.3) || !BlockUtils.e(spartanPlayer, true, 0.3, 1.0, 0.3);
        if (blockY >= 0 && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -3.0, 0.0) && !BlockUtils.G(spartanPlayer, spartanPlayer.a()) && !r && Checks.getBoolean("Fly.check_glide") && !NoHitDelay.E(spartanPlayer) && j + 3 <= i && d < 0.0 && i >= 20 && !TPS.u() && !b && AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=packets", 20) >= LagManagement.a(spartanPlayer, 4) && !b3) {
            a(spartanPlayer, spartanLocation2, "t: packets, ot: " + j + ", ct: " + i, true);
        }
        if (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            return;
        }
        final SpartanLocation a2 = spartanPlayer.a();
        final SpartanLocation b4 = a2.b().b(0.0, -1.0, 0.0);
        final int fireTicks = spartanPlayer.getFireTicks();
        final boolean ad = PlayerData.aD(spartanPlayer);
        if (!PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0) && !e && !PlayerData.aA(spartanPlayer) && !ad && !b3 && n != 0.0 && !EnderPearl.E(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !NoHitDelay.E(spartanPlayer) && !b && !PlayerData.aG(spartanPlayer)) {
            if (d < 0.0 && Checks.getBoolean("Fly.check_glide") && !FishingHook.Y(spartanPlayer) && blockY >= 0 && n != 0.5 && !PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0) && !Building.E(spartanPlayer)) {
                if (d2 <= 0.0 && !r) {
                    final boolean z = SelfHit.Z(spartanPlayer);
                    final int a3 = AttemptUtils.a(spartanPlayer, Fly.a.toString() + "=illegal=ticks", 1);
                    final int abs2 = Math.abs(i - a3);
                    if (d > d2 && i >= 15 && abs2 >= 2 && a3 >= 2 && !BlockUtils.f(spartanPlayer, b4) && !z) {
                        if (AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=illegal=attempts", 20) >= 2 || !PlayerData.i(spartanPlayer, 0.0, -3.0, 0.0) || d >= -1.0) {
                            a(spartanPlayer, spartanLocation2, "t: change, d: " + d + ", od: " + d2 + ", a: " + i + ", r: " + n, true);
                        }
                    }
                    else if (!Explosion.X(spartanPlayer) && d <= -0.0784) {
                        boolean b5 = false;
                        if (i >= 15 && !z && fireTicks <= 0) {
                            if (abs >= 0.08) {
                                a(spartanPlayer, spartanLocation2, "t: illegal, d: " + d + ", ds: " + abs + ", a: " + i + ", r: " + n, false);
                                b5 = true;
                            }
                            else if (d >= -3.0 && (i <= 40 || !ViaRewind.isLoaded()) && !a(spartanPlayer, 250, i)) {
                                final double abs3 = Math.abs(abs - MoveUtils.g(spartanPlayer));
                                final LowViolation lowViolation = new LowViolation(spartanPlayer, Fly.a, "calculated");
                                if (abs3 >= 0.034 && lowViolation.q()) {
                                    a(spartanPlayer, spartanLocation, "t: calculated, d: " + d + ", ds: " + abs + ", diff: " + abs3 + ", a: " + i + ", r: " + n, false);
                                    b5 = true;
                                }
                            }
                        }
                        else if (abs >= 0.4 && d <= -0.1) {
                            a(spartanPlayer, spartanLocation, "t: pre, d: " + d + ", ds: " + abs + ", a: " + i + ", r: " + n, false);
                            b5 = true;
                        }
                        if (b5) {
                            CooldownUtils.d(spartanPlayer, Fly.a.toString() + "=cancel=up", 5);
                            CooldownUtils.d(spartanPlayer, Fly.a.toString() + "=cancel=down", 5);
                        }
                    }
                }
                else {
                    AttemptUtils.m(spartanPlayer, Fly.a.toString() + "=illegal=ticks");
                    AttemptUtils.m(spartanPlayer, Fly.a.toString() + "=illegal=attempts");
                }
            }
            if (Checks.getBoolean("Fly.check_fly") && !Piston.E(spartanPlayer) && !BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, 2.0, 0.0)) && fireTicks <= 0 && a != VersionUtils.a.l && a != VersionUtils.a.c) {
                final boolean b6 = abs == 0.0;
                final boolean b7 = abs >= 0.09 && !BouncingBlocks.R(spartanPlayer);
                final boolean b8 = !b6 && Math.abs(d) == Math.abs(d2);
                final boolean b9 = MoveUtils.b(d) && (MoveUtils.b(d2) || d2 == 0.0 || d == n || d - 1.0 == n);
                int l = 0;
                if (d >= 0.0 && d2 < 0.0) {
                    l = 1;
                }
                else if (d > 0.0 && d2 <= 0.0) {
                    l = 2;
                }
                else if (d >= 0.0 && d2 >= 0.0 && (b7 || b6 || b8)) {
                    l = 3;
                }
                else if (b8) {
                    l = 4;
                }
                final boolean b10 = b8 || (b7 && d != 0.5 && n != 0.5 && i >= 12 && !r && !PlayerData.az(spartanPlayer) && !SelfHit.E(spartanPlayer) && !b9);
                final boolean b11 = d == 0.0 && n == 0.0 && d2 == 0.0 && abs == 0.0 && !b10;
                if (l != 0 && !b11 && (AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=neutral", 20) >= 8 || b10)) {
                    new HackPrevention(spartanPlayer, Fly.a, "t: neutral, d: " + d + ", r: " + n + ", od: " + d2 + ", ds: " + abs + ", a: " + i + ", i: " + b10 + ", c: " + l, spartanLocation2, 20);
                }
            }
        }
        if (a(spartanPlayer, d, b2, a)) {
            int n2 = 20;
            final int a4 = LagManagement.a(spartanPlayer, 2);
            int n3 = (a4 > 8) ? 8 : a4;
            final boolean am = MoveUtils.am(spartanPlayer);
            final LowViolation lowViolation2 = new LowViolation(spartanPlayer, Fly.a, "basic");
            final boolean b12 = BlockUtils.G(spartanPlayer, a2) || BlockUtils.G(spartanPlayer, a2.b().b(0.0, 1.0, 0.0));
            if (d >= 1.0) {
                n2 = 1;
                n3 = 1;
            }
            else if (NoHitDelay.E(spartanPlayer)) {
                n2 = 40;
                n3 = 20;
            }
            else if (BouncingBlocks.V(spartanPlayer) && FloorProtection.E(spartanPlayer)) {
                CooldownUtils.d(spartanPlayer, Fly.a.toString() + "=bouncing-blocks=damage", 10);
            }
            else if (e) {
                n2 = 40;
                n3 = 10;
            }
            else if (Building.E(spartanPlayer) && d >= 0.0) {
                n2 = 40;
                n3 = 6;
            }
            else if (BlockUtils.b(spartanPlayer, 2) || FloorProtection.E(spartanPlayer) || fireTicks > 0 || (r && d < 0.0) || SelfHit.E(spartanPlayer) || am || PlayerData.a(spartanPlayer, PotionEffectType.SPEED) >= 2) {
                n2 = 40;
                n3 = 4;
            }
            int a5 = LagManagement.a(spartanPlayer, n3);
            final boolean g = CooldownUtils.g(spartanPlayer, Fly.a.toString() + "=cancel=down");
            if (!CooldownUtils.g(spartanPlayer, Fly.a.toString() + "=bouncing-blocks=damage")) {
                n2 = 20;
                a5 = 8;
            }
            if (Checks.getBoolean("Fly.check_fly") && !MoveUtils.b(d) && !BlockBreak.E(spartanPlayer) && (b12 || (!PlayerData.i(spartanPlayer, 0.0, -0.25, 0.0) && d != 0.5) || !PlayerData.i(spartanPlayer, 0.0, -0.99999, 0.0) || (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && BlockUtils.g(spartanPlayer, true, 1.0, -1.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 1.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, 2.0, 1.0) && !e && !BowProtection.E(spartanPlayer) && !FloorProtection.E(spartanPlayer)))) {
                final boolean g2 = CooldownUtils.g(spartanPlayer, Fly.a.toString() + "=cancel=up");
                final double b13 = MathUtils.b(spartanLocation2, spartanLocation);
                final boolean b14 = d == n && d != 0.5;
                if (b12) {
                    i = Integer.MAX_VALUE;
                }
                if (abs <= 0.08 && g2 && !PlayerData.aG(spartanPlayer) && d > 0.0 && (d != 0.5 || !Piston.E(spartanPlayer)) && !b14 && !b && AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=up", n2) >= a5 && (i >= 12 || VL.a(spartanPlayer, Fly.a) >= DefaultConfiguration.f(Fly.a))) {
                    a(spartanPlayer, spartanLocation2, "t: up, d: " + d + ", ds: " + abs + ", r: " + n + ", a: " + i, true);
                }
                if ((i >= LagManagement.a(spartanPlayer, 12) || (i >= 3 && b13 >= 0.3) || (d < 0.0 && d2 < 0.0)) && !EnderPearl.E(spartanPlayer) && abs == 0.0 && !b && !PlayerData.aY(spartanPlayer) && !Piston.E(spartanPlayer) && lowViolation2.q() && !a(spartanPlayer, 400, i) && !b3) {
                    boolean b15 = false;
                    if (am || !BlockUtils.c(spartanPlayer, spartanPlayer.a(), false) || !BlockUtils.c(spartanPlayer, b4, false) || a(spartanPlayer, 100, i)) {
                        if (AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=stable", n2) >= a5) {
                            b15 = true;
                        }
                    }
                    else {
                        b15 = true;
                    }
                    if (b15) {
                        a(spartanPlayer, spartanLocation2, "t: stable, d: " + d + ", a: " + i + ", r: " + n, true);
                    }
                }
            }
            if (Checks.getBoolean("Fly.check_glide") && !e && d < 0.0 && d >= -0.2 && abs < 0.076 && g && i >= 5 && !b && !PlayerData.aG(spartanPlayer) && (!PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || i >= 40) && !s(spartanPlayer) && ((!am && i >= 40) || AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=down", n2) >= a5) && !ad && !Building.E(spartanPlayer) && !b3) {
                a(spartanPlayer, spartanLocation2, "t: down, d: " + d + ", ds: " + abs + ", r: " + n + ", a: " + i, true);
            }
        }
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final double n, final double n2, final VersionUtils.a a) {
        final boolean g = BlockUtils.G(spartanPlayer, spartanPlayer.a());
        final boolean b = n2 == 0.33319 || n2 == -0.098 || n2 == -0.15523 || n2 == -0.0784;
        final boolean b2 = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -2.0, 0.0) || a == VersionUtils.a.l || a == VersionUtils.a.c;
        return ((!b || !b2) && !g) || (!MoveUtils.f(n) && n > 0.0);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s, final boolean b) {
        if (Building.E(spartanPlayer) || PlayerData.aU(spartanPlayer) || !BlockUtils.g(spartanPlayer, true, 0.298, 0.0, 0.298) || !BlockUtils.g(spartanPlayer, true, 0.298, 1.0, 0.298)) {
            EventsHandler1.a(spartanPlayer, Fly.a, spartanLocation, s, b, false);
        }
        else if (EnderPearl.E(spartanPlayer) || FloorProtection.E(spartanPlayer) || BowProtection.E(spartanPlayer) || SelfHit.E(spartanPlayer) || NoHitDelay.E(spartanPlayer)) {
            if (AttemptUtils.b(spartanPlayer, Fly.a.toString() + "=attempts", 20) >= 2) {
                EventsHandler1.a(spartanPlayer, Fly.a, spartanLocation, s, b, true);
            }
            else {
                EventsHandler1.a(spartanPlayer, Fly.a, spartanLocation, s, b, false);
            }
        }
        else {
            EventsHandler1.a(spartanPlayer, Fly.a, spartanLocation, s, b, true);
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, Fly.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && BlockUtils.j(spartanPlayer, true, 1.0, 0.0, 1.0) && !Explosion.E(spartanPlayer) && !PlayerData.b(spartanPlayer, 1, 0.5) && spartanPlayer.a().getBlockY() > -60 && !PlayerData.aS(spartanPlayer) && !PlayerData.aF(spartanPlayer) && !WaterSoulSand.E(spartanPlayer) && !GroundUtils.ak(spartanPlayer) && BlockUtils.d(spartanPlayer, true, 0.3, 0.0, 0.3) && BlockUtils.f(spartanPlayer, true, 0.3, 0.0, 0.3) && !PlayerData.aV(spartanPlayer) && !BlockUtils.ag(spartanPlayer);
    }
    
    private static boolean s(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, Fly.a.toString() + "=unlegit_jump_effect")) {
            return true;
        }
        final boolean b = PlayerData.a(spartanPlayer, PotionEffectType.JUMP) >= 4;
        if (b) {
            CooldownUtils.d(spartanPlayer, Fly.a.toString() + "=unlegit_jump_effect", 100);
        }
        return b;
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final int n, final int n2) {
        return LatencyUtils.e(spartanPlayer, n) && n2 <= 60;
    }
    
    static {
        a = Enums.HackType.Fly;
    }
}
