package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.f.SpartanBlock;
import java.util.Iterator;
import org.bukkit.util.Vector;
import me.vagdedes.spartan.k.c.LagManagement;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.BlockBreak;
import me.vagdedes.spartan.h.LowViolation;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.h.CheckProtection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Clip
{
    private static final Enums.HackType a;
    
    public Clip() {
        super();
    }
    
    public static void t(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, Clip.a.toString() + "=suffocation", 20);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double d, final double n, final double n2, final double n3, final double n4, final int i, final int n5, final boolean b) {
        if (!b(spartanPlayer)) {
            return;
        }
        final float n6 = PlayerData.b(spartanPlayer) / 2.0f;
        double max = 0.4 + n6;
        final double a = MoveUtils.a(spartanPlayer, max, 4.0, PotionEffectType.SPEED);
        if (b) {
            if (Checks.getBoolean("Clip.check_instant_movements")) {
                final boolean e = Piston.E(spartanPlayer);
                final double n7 = e ? 1.65 : 1.35;
                final double n8 = 0.47;
                final boolean b2 = BouncingBlocks.R(spartanPlayer) && !BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, 2.0, 0.0));
                double d2 = e ? n7 : ((MoveUtils.b(n4) || (n4 == 0.0 && d > 0.0)) ? 1.01 : ((BlockUtils.c(null, spartanPlayer.a().b(0.0, -Math.abs(d), 0.0), true) && PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && n4 == 0.0 && d < 0.0) ? n8 : n7));
                final double n9 = d2 - 0.0784000015258;
                if (n4 != 0.0 && n4 <= -n9) {
                    for (int j = -1; j <= 1; ++j) {
                        if (!BlockUtils.g(spartanPlayer, true, 0.298, j, 0.298) && BlockUtils.j(spartanPlayer, true, 0.298, j, 0.298)) {
                            CooldownUtils.d(spartanPlayer, Clip.a.toString() + "=climb", 40);
                            break;
                        }
                    }
                }
                if (Explosion.E(spartanPlayer)) {
                    d2 = 15.0;
                }
                else if (PlayerData.aF(spartanPlayer)) {
                    d2 = 6.0;
                }
                else if (PlayerData.aS(spartanPlayer) || !CooldownUtils.g(spartanPlayer, Clip.a.toString() + "=climb")) {
                    d2 = 5.0;
                }
                else if (r(spartanPlayer) && d < 0.0) {
                    d2 = 4.0;
                }
                else if (FishingHook.E(spartanPlayer)) {
                    d2 = 3.25;
                }
                else if (Damage.E(spartanPlayer) || EnderPearl.E(spartanPlayer) || SelfHit.E(spartanPlayer)) {
                    d2 = 2.0;
                }
                else if (FishingHook.Y(spartanPlayer)) {
                    d2 = 1.5;
                }
                else if (d2 < 0.8 && WaterSoulSand.E(spartanPlayer)) {
                    d2 = 0.8;
                }
                if ((spartanLocation.getBlockY() != spartanLocation2.getBlockY() || d2 == n8) && (!b2 || d <= 0.0 || MoveUtils.b(d))) {
                    final double abs = Math.abs(d);
                    final boolean b3 = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0);
                    if ((((i <= 1 && n5 <= 1) || (n4 > -n9 && b3)) && spartanLocation2.getBlockY() >= 0) || (abs >= 4.0 && d2 <= 4.0 && !Explosion.E(spartanPlayer)) || abs >= 15.0) {
                        final String str = (d >= 0.0) ? "up" : "down";
                        if (abs >= d2 && BlockUtils.a(spartanLocation, spartanLocation2, 5, true)) {
                            EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: instant, d: " + abs + ", l: " + d2 + ", r: " + str + ", a: " + i, false, true);
                            return;
                        }
                    }
                }
                final double n10 = 1.1;
                final double n11 = (n2 <= Speed.m) ? n10 : MoveUtils.a(spartanPlayer, n10, 4.0, PotionEffectType.SPEED);
                if (n >= n11 && n <= 16.0 && (Math.abs(d) < 0.167 & i <= 12)) {
                    SpartanLocation spartanLocation3 = spartanLocation.b();
                    for (double a2 = 1.0; a2 < n; ++a2) {
                        final double min = Math.min(a2, n);
                        final Vector multiply = spartanLocation3.getDirection().multiply(a2);
                        spartanLocation3.setX(multiply.getX());
                        spartanLocation3.setY(multiply.getY());
                        spartanLocation3.setZ(multiply.getZ());
                        if (BlockUtils.f(spartanPlayer, spartanLocation3)) {
                            EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: vector, h: 0, s: " + n11 + ", p: " + min + ", ds: " + n + ", dy: " + d + ", a: " + i, false, true);
                        }
                        else {
                            spartanLocation3 = spartanLocation.b().b(0.0, 1.0, 0.0);
                            final Vector multiply2 = spartanLocation3.getDirection().multiply(a2);
                            spartanLocation3.setX(multiply2.getX());
                            spartanLocation3.setY(multiply2.getY());
                            spartanLocation3.setZ(multiply2.getZ());
                            if (BlockUtils.f(spartanPlayer, spartanLocation3)) {
                                EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: vector, h: 1, s: " + n11 + ", p: " + min + ", ds: " + n + ", dy: " + d + ", a: " + i, false, true);
                            }
                        }
                    }
                }
            }
        }
        else {
            final Iterator<Entity> iterator = spartanPlayer.getNearbyEntities(1.0, 3.0, 1.0).iterator();
            while (iterator.hasNext()) {
                if (((Entity)iterator.next()) instanceof FallingBlock) {
                    CooldownUtils.d(spartanPlayer, Clip.a.toString() + "=falling=block", 30);
                }
            }
            if (!Damage.E(spartanPlayer) && !PlayerData.aS(spartanPlayer) && !CheckProtection.E(spartanPlayer) && !Piston.E(spartanPlayer) && CooldownUtils.g(spartanPlayer, Clip.a.toString() + "=falling=block") && !EnderPearl.E(spartanPlayer) && CooldownUtils.g(spartanPlayer, Clip.a.toString() + "=teleport-protection")) {
                int k = 0;
                final double n12 = 0.299;
                String str2 = "none";
                final SpartanLocation a3 = spartanPlayer.a();
                final SpartanLocation b4 = a3.b().b(0.0, 1.0, 0.0);
                final SpartanBlock a4 = a3.a();
                final SpartanBlock a5 = b4.a();
                final boolean b5 = VL.o(spartanPlayer) >= 3;
                if (Checks.getBoolean("Clip.check_inside_movements") && !Building.E(spartanPlayer)) {
                    if (c(spartanPlayer, n12, 0.0, n12) && c(spartanPlayer, n12, 1.0, n12)) {
                        k = 1;
                    }
                    else if (c(spartanPlayer, n12, 0.0, n12)) {
                        k = 2;
                    }
                    else if (c(spartanPlayer, n12, 1.0, n12) || a(b4)) {
                        k = 3;
                    }
                    else if (a(a3) && a(b4)) {
                        k = 4;
                    }
                }
                if (k != 0) {
                    final double b6 = Values.b(d, 5);
                    final boolean b7 = b6 == 0.41999 || b6 == 0.33319;
                    final boolean b8 = b(spartanPlayer, 0.1, 0.0, 0.1) && !BlockUtils.m(spartanPlayer, true, 0.1, 0.0, 0.1);
                    final boolean b9 = b7 && b8;
                    final boolean b10 = k == 4 || (d < 0.0 && a(b4));
                    final boolean b11 = !BlockUtils.i(spartanPlayer, true, 0.3, 0.0, 0.3) || !BlockUtils.i(spartanPlayer, true, 0.3, 1.0, 0.3);
                    final LowViolation lowViolation = new LowViolation(spartanPlayer, Clip.a, "inside-movements");
                    if (b11) {
                        max = Math.max(max, 2.5);
                    }
                    if (k == 1 || k == 4) {
                        str2 = "(" + BlockUtils.a((SpartanPlayer)null, a4) + ", " + BlockUtils.a((SpartanPlayer)null, a5) + ")";
                        if (!BlockUtils.F(a4.getType()) && !BlockUtils.F(a5.getType())) {
                            k = 0;
                        }
                    }
                    else if (k == 2) {
                        str2 = BlockUtils.a(null, a4);
                        if (!BlockUtils.F(a4.getType())) {
                            k = 0;
                        }
                    }
                    else if (k == 3) {
                        str2 = BlockUtils.a(null, a5);
                        if (!BlockUtils.F(a5.getType())) {
                            k = 0;
                        }
                    }
                    if (k != 0) {
                        if (a > 0.0 && n >= a && !a(b4) && b5) {
                            if (lowViolation.q()) {
                                EventsHandler1.a(spartanPlayer, Clip.a, spartanPlayer.a(), "t: speed(normal), c: " + k + ", b: " + str2 + ", ds: " + n + ", dm: " + max + ", dm_s: " + a, false, true);
                            }
                        }
                        else if (!PlayerData.aF(spartanPlayer) && (d <= -(str2.contains("cocoa") ? 1.2 : 0.8) || d > 0.5) && b5) {
                            if (lowViolation.q()) {
                                EventsHandler1.a(spartanPlayer, Clip.a, a3, "t: ypos(normal), c: " + k + ", b: " + str2 + ", dy: " + d, false, true);
                            }
                        }
                        else if (k != 3 && ((k == 1 && c(spartanPlayer, n12, 2.0 - n3, n12)) || (b10 && a(spartanPlayer.a().b(0.0, 2.0 - n3, 0.0))) || b9)) {
                            if (!PlayerData.aF(spartanPlayer) && ((d >= 0.25 && (k == 1 || b9)) || d <= -0.25) && lowViolation.q()) {
                                if (b7) {
                                    if (b8) {
                                        EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: ypos(full-block), c: " + k + ", b: " + str2 + ", dy: " + d, false, false);
                                    }
                                }
                                else {
                                    EventsHandler1.a(spartanPlayer, Clip.a, a3, "t: ypos(full-block), c: " + k + ", b: " + str2 + ", dy: " + d, false, true);
                                }
                            }
                            if (d != 0.0 && !BlockBreak.E(spartanPlayer) && !b9) {
                                final int b12 = AttemptUtils.b(spartanPlayer, Clip.a.toString() + "=repeated=dy=" + d, 5);
                                if (!PlayerData.aF(spartanPlayer) && b12 >= 4) {
                                    EventsHandler1.a(spartanPlayer, Clip.a, spartanPlayer.a(), "t: ypos(repeated), c: " + k + ", block(s): " + str2 + ", dy: " + d, false, true);
                                }
                                else {
                                    if (PlayerData.aF(spartanPlayer)) {
                                        AttemptUtils.m(spartanPlayer, Clip.a.toString() + "=repeated=dy=" + d);
                                    }
                                    int n13 = 0;
                                    final double b13 = Values.b(d, 4);
                                    final double b14 = Values.b(n4, 4);
                                    if (b14 == 0.2 && b13 != -0.0784) {
                                        n13 = 1;
                                    }
                                    else if (b14 == -0.0784 && b13 != -0.1216) {
                                        n13 = 2;
                                    }
                                    else if (b14 == -0.1216 && b13 != 0.2) {
                                        n13 = 3;
                                    }
                                    else if (b14 == b13) {
                                        n13 = 4;
                                    }
                                    if (n13 != 0 && lowViolation.q()) {
                                        if (!CooldownUtils.g(spartanPlayer, Clip.a.toString() + "=suffocation") || !PlayerData.c(spartanPlayer, a3, 0.0)) {
                                            EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: ypos(illegal), c: " + n13 + ", b: " + str2 + ", cr: " + b13 + ", pr: " + b14, false, false);
                                        }
                                        else {
                                            EventsHandler1.a(spartanPlayer, Clip.a, a3, "t: ypos(illegal), c: " + n13 + ", b: " + str2 + ", cr: " + b13 + ", pr: " + b14, false, true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                int n14 = 0;
                if (Checks.getBoolean("Clip.check_block_change")) {
                    if (b(a3) && b(b4)) {
                        n14 = 1;
                    }
                    else if (b(a3)) {
                        n14 = 2;
                    }
                    else if (b(b4) || a(b4)) {
                        n14 = 3;
                    }
                    else if (a(a3) && a(b4)) {
                        n14 = 4;
                    }
                }
                if (n14 != 0 && (spartanLocation.getBlockX() != spartanLocation2.getBlockX() || spartanLocation.getBlockZ() != spartanLocation2.getBlockZ())) {
                    final double n15 = (BlockUtils.a(null, spartanPlayer.a(), MaterialUtils.a("soil"), 0.298) || (Register.v1_9 && BlockUtils.a(null, spartanPlayer.a(), Material.GRASS_PATH, 0.298))) ? 0.1 : 0.0;
                    if (n14 == 1 || n14 == 4) {
                        str2 = "(" + BlockUtils.a((SpartanPlayer)null, a4) + ", " + BlockUtils.a((SpartanPlayer)null, a5) + ")";
                    }
                    else if (n14 == 2) {
                        str2 = BlockUtils.a(null, a4);
                    }
                    else if (n14 == 3) {
                        str2 = BlockUtils.a(null, a5);
                    }
                    final SpartanLocation b15 = spartanPlayer.a().b(0.0, n15, 0.0);
                    b15.setPitch(0.0f);
                    final SpartanLocation b16 = b15.b().b(b15.b().getDirection().multiply(1.0));
                    final SpartanLocation b17 = b15.b().b(b15.b().getDirection().multiply(-1.0));
                    if (b(b15) && (b(b16) || b(b17))) {
                        double d3 = 0.25 + n6;
                        if (BlockUtils.x(null, a4.a()) || BlockUtils.x(null, a5.a())) {
                            d3 += 0.35;
                        }
                        final double f = LagManagement.f(spartanPlayer, MoveUtils.a(spartanPlayer, d3, 4.0, PotionEffectType.SPEED));
                        if (n >= f && !BouncingBlocks.R(spartanPlayer)) {
                            EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: speed(block-change), block(s): " + str2 + ", ds: " + n + ", dm: " + d3 + ", dm_s: " + f, false, true);
                        }
                        else if (!a(spartanPlayer, 0.298, 0.0, 0.298) && CooldownUtils.g(spartanPlayer, Clip.a.toString() + "=block-change")) {
                            CooldownUtils.d(spartanPlayer, Clip.a.toString() + "=block-change", 5);
                            EventsHandler1.a(spartanPlayer, Clip.a, spartanLocation2, "t: speed(block-change), b: " + str2 + ", ds: " + n, false, false);
                        }
                    }
                }
            }
        }
    }
    
    private static boolean r(final SpartanPlayer spartanPlayer) {
        return Liquid.e(spartanPlayer) <= 1000L;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, Clip.a, true) && !PlayerData.c(spartanPlayer, true) && !PlayerData.az(spartanPlayer) && (Checks.getBoolean("Clip.check_when_flying") || !PlayerData.aR(spartanPlayer));
    }
    
    private static boolean a(final SpartanLocation spartanLocation) {
        final VersionUtils.a a = VersionUtils.a();
        final Material type = spartanLocation.a().getType();
        final boolean b = a != VersionUtils.a.l && a != VersionUtils.a.c && a != VersionUtils.a.d && type == Material.END_ROD;
        return !BlockUtils.u(null, spartanLocation) && !BlockUtils.v(null, spartanLocation) && !BlockUtils.w(null, spartanLocation) && type != MaterialUtils.a("cake") && type != Material.FLOWER_POT && !BlockUtils.e(type) && !b && !BlockUtils.o(null, spartanLocation) && !BlockUtils.m(null, spartanLocation) && type != MaterialUtils.a("iron_bars") && (BlockUtils.J(null, spartanLocation) || BlockUtils.n(null, spartanLocation) || BlockUtils.t(null, spartanLocation) || BlockUtils.c(null, spartanLocation, true));
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        for (final SpartanLocation spartanLocation : BlockUtils.a(spartanPlayer.a(), n, n2, n3)) {
            if (!b(spartanLocation) && BlockUtils.x(null, spartanLocation)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!b(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean c(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        return BlockUtils.g(spartanPlayer, false, n, n2, n3) && !BlockUtils.c(spartanPlayer, false, n, n2, n3);
    }
    
    private static boolean b(final SpartanLocation spartanLocation) {
        return BlockUtils.c(null, spartanLocation, true) || (Checks.getBoolean("Clip.check_pistons") && (spartanLocation.a().getType() == MaterialUtils.a("piston_extension") || spartanLocation.a().getType() == MaterialUtils.a("piston_moving")));
    }
    
    static {
        a = Enums.HackType.Clip;
    }
}
