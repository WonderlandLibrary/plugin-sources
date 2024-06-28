package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.h.GameModeProtection;
import me.vagdedes.spartan.h.BlockBreak;
import org.bukkit.entity.LivingEntity;
import me.vagdedes.spartan.h.Teleport;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.j.ClientSidedBlock;
import java.util.Iterator;
import me.vagdedes.spartan.f.HackPrevention;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class MorePackets
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, SpartanLocation> A;
    
    public MorePackets() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        MorePackets.A.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        MorePackets.A.clear();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        MorePackets.A.remove(spartanPlayer.getUniqueId());
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=diff");
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=od");
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=cus");
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=nor");
        AttemptUtils.m(spartanPlayer, MorePackets.a.toString() + "=difference=times");
    }
    
    public static void j(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=cus");
        DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=nor");
        AttemptUtils.m(spartanPlayer, MorePackets.a.toString() + "=difference=times");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3) {
        if (!b(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (MorePackets.A.containsKey(uniqueId) && MoveUtils.a(spartanPlayer.a(), (SpartanLocation)MorePackets.A.get(uniqueId)) >= 15.0) {
            MorePackets.A.remove(uniqueId);
        }
        a(spartanPlayer, spartanLocation2, n3, n);
        if (!TPS.u()) {
            final long a = MillisUtils.a(spartanPlayer, MorePackets.a.toString() + "=ms");
            a(spartanPlayer, spartanLocation, spartanLocation2, a, n3, n, n2);
            a(spartanPlayer, n3, spartanLocation);
            if (a <= 40L) {
                c(spartanPlayer, spartanLocation2);
                a(spartanPlayer, a);
            }
        }
        MillisUtils.o(spartanPlayer, MorePackets.a.toString() + "=ms");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final long n, final double n2, final double a, final double n3) {
        final boolean b = MoveUtils.b(a);
        boolean b2 = a > 0.0 && Math.abs(a - n3) <= 0.01;
        if (!Checks.getBoolean("MorePackets.check_instant") || (Math.abs(a) > 0.083 && !b && !b2) || PlayerData.b(spartanPlayer, true) || PlayerData.aS(spartanPlayer) || BouncingBlocks.R(spartanPlayer) || Liquid.e(spartanPlayer) <= 400L || BlockUtils.G(spartanPlayer, spartanLocation) || Explosion.E(spartanPlayer) || Building.E(spartanPlayer) || Piston.E(spartanPlayer) || Damage.E(spartanPlayer) || LatencyUtils.e(spartanPlayer, 200)) {
            return;
        }
        final String string = MorePackets.a.toString() + "=instant";
        int i = AttemptUtils.b(spartanPlayer, string, 5);
        if (b2) {
            b2 = (AttemptUtils.b(spartanPlayer, string) > 0);
        }
        if (n <= 35L && !b) {
            final boolean b3 = MoveUtils.a(spartanPlayer, spartanLocation2) > n2;
            i = AttemptUtils.a(spartanPlayer, string, (n <= 5L || b3) ? 2 : 1);
        }
        if (i >= (b2 ? 10 : 20)) {
            AttemptUtils.m(spartanPlayer, string);
            if (VL.a(spartanPlayer, MorePackets.a) >= 1 || b2) {
                a(spartanPlayer, "t: instant, a: " + i, 20, true);
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final long lng) {
        if (PlayerData.b(spartanPlayer, true) || !Checks.getBoolean("MorePackets.check_delay")) {
            return;
        }
        final int i = AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=delay=ticks", 20) + (AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=delay=ticks") - 1);
        if (i <= 22 && lng <= 40L && AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=delay=attempts", 20) >= 18) {
            a(spartanPlayer, "t: delay, ct: " + i + ", ms: " + lng, 20, true);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final SpartanLocation spartanLocation) {
        if (PlayerData.b(spartanPlayer, true) || !Checks.getBoolean("MorePackets.check_difference") || BouncingBlocks.R(spartanPlayer) || PlayerData.aS(spartanPlayer)) {
            return;
        }
        for (int i = 0; i <= 2; ++i) {
            if (!d(spartanPlayer, 0.298, i, 0.298)) {
                return;
            }
        }
        final int j = AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=difference=ticks", 20) + (AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=difference=ticks") - 1);
        final int a = LagManagement.a(spartanPlayer, 23);
        final double a2 = DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=cus");
        final double a3 = DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=nor");
        final boolean h = DoubleUtils.h(a2);
        final boolean h2 = DoubleUtils.h(a3);
        final boolean b = !LatencyUtils.bi(spartanPlayer) && spartanPlayer.getPing() < 100;
        if (AttemptUtils.a(spartanPlayer, MorePackets.a.toString() + "=difference=times") >= (b ? 10 : 20) && h && h2) {
            final double abs = Math.abs(a2 - a3);
            if (abs <= 16.0) {
                final double n2 = 9.0;
                DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=cus");
                DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=nor");
                AttemptUtils.m(spartanPlayer, MorePackets.a.toString() + "=difference=times");
                final double f = LagManagement.f(spartanPlayer, 1.5);
                final double d = (f > 1.5) ? Math.pow(f, 2.0) : f;
                if (abs >= d && j >= a && (a2 >= n2 || a3 >= n2) && a2 >= a3 * LagManagement.t(spartanPlayer)) {
                    final String string = "t: difference, c: " + a2 + ", n: " + a3 + ", d: " + abs + ", t: " + j + ", l: " + d + ", p: " + b;
                    if (AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=difference=attempts", 600) >= 2 || VL.a(spartanPlayer, MorePackets.a) > 0) {
                        a(spartanPlayer, string, 20, true);
                    }
                    else {
                        a(spartanPlayer, string, 20, false);
                    }
                }
            }
        }
        else if (n > 0.0 && (j >= a || VL.a(spartanPlayer, MorePackets.a) >= 5)) {
            final double a4 = MoveUtils.a(spartanLocation, MoveUtils.c(spartanPlayer));
            if (h) {
                DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=cus", DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=cus") + a4);
            }
            else {
                DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=cus", a4);
            }
            if (h2) {
                DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=nor", DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=nor") + n);
            }
            else {
                DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=nor", n);
            }
            AttemptUtils.a(spartanPlayer, MorePackets.a.toString() + "=difference=times", 1);
        }
    }
    
    private static void c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (!Checks.getBoolean("MorePackets.check_blink")) {
            return;
        }
        final double a = MoveUtils.a(spartanPlayer, spartanLocation);
        AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=blink=ticks", 20);
        final int a2 = AttemptUtils.a(spartanPlayer, MorePackets.a.toString() + "=blink=ticks");
        final int a3 = LagManagement.a(spartanPlayer, 22);
        final int i = (a3 > 35) ? 35 : a3;
        if (a >= 2.0) {
            CooldownUtils.d(spartanPlayer, MorePackets.a.toString() + "=blink=distance", 5);
        }
        if (a2 == i && !CooldownUtils.g(spartanPlayer, MorePackets.a.toString() + "=blink=distance")) {
            AttemptUtils.m(spartanPlayer, MorePackets.a.toString() + "=blink=ticks");
            CooldownUtils.j(spartanPlayer, MorePackets.a.toString() + "=blink=distance");
            a(spartanPlayer, "t: blink, l: " + i, 0, true);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2) {
        if (!Checks.getBoolean("MorePackets.check_overall") || PlayerData.b(spartanPlayer, true) || (n < 0.215 && !spartanPlayer.isSneaking()) || (PlayerData.aS(spartanPlayer) && PlayerData.q(spartanPlayer) >= 15) || (BouncingBlocks.R(spartanPlayer) && n2 >= 0.5) || spartanPlayer.a().getBlockY() < 0) {
            return;
        }
        final int n3 = 20;
        double abs = 0.0;
        double abs2 = 0.0;
        final double a = MoveUtils.a(spartanPlayer, spartanLocation);
        final double a2 = DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=od");
        if (DoubleUtils.h(a2)) {
            abs2 = Math.abs(a - a2);
            abs = Math.abs(a - abs2);
        }
        DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=od", a);
        if (abs2 > 0.0) {
            CooldownUtils.d(spartanPlayer, MorePackets.a.toString() + "=overall=boost", 10);
        }
        if (abs <= 0.01 && !CooldownUtils.g(spartanPlayer, MorePackets.a.toString() + "=overall=boost")) {
            final int n4 = AttemptUtils.a(spartanPlayer, MorePackets.a.toString() + "=overall=times") + 1;
            AttemptUtils.c(spartanPlayer, MorePackets.a.toString() + "=overall=times", n4);
            final SpartanLocation a3 = spartanPlayer.a();
            DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=diff", DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=diff") + a);
            final double a4 = DoubleUtils.a(spartanPlayer, MorePackets.a.toString() + "=diff");
            final double a5 = MoveUtils.a(a3, (SpartanLocation)MorePackets.A.get(spartanPlayer.getUniqueId()));
            if (n4 == 1) {
                MorePackets.A.put(spartanPlayer.getUniqueId(), a3);
            }
            else if (n4 == n3) {
                AttemptUtils.m(spartanPlayer, MorePackets.a.toString() + "=overall=times");
                DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=diff");
                DoubleUtils.j(spartanPlayer, MorePackets.a.toString() + "=od");
                if (a4 > 0.0 && a5 >= MoveUtils.a(spartanPlayer, 5.5, 4.0, PotionEffectType.SPEED) && AttemptUtils.b(spartanPlayer, MorePackets.a.toString() + "=overall=attempts", n3 + 1) >= 2) {
                    a(spartanPlayer, "t: overall, diff: " + a4 + ", d: " + a5, 40, true);
                }
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String s, final int n, final boolean b) {
        new HackPrevention(spartanPlayer, MorePackets.a, s, MorePackets.A.get(spartanPlayer.getUniqueId()), n, true, 0.0, b);
    }
    
    private static boolean d(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!c(spartanPlayer, iterator.next(), false)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final boolean b) {
        final Material b2 = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        return BlockUtils.c(spartanPlayer, spartanLocation, b) || BlockUtils.t(spartanPlayer, spartanLocation) || BlockUtils.n(spartanPlayer, spartanLocation) || BlockUtils.J(spartanPlayer, spartanLocation) || b2 == MaterialUtils.a("piston_extension") || b2 == MaterialUtils.a("piston_moving") || b2 == Material.DRAGON_EGG || b2 == MaterialUtils.a("enchanting_table") || BlockUtils.l(b2) || b2 == MaterialUtils.a("soil") || b2 == Material.SOUL_SAND || b2 == Material.CAULDRON || b2 == Material.ANVIL || b2 == MaterialUtils.a("END_PORTAL_FRAME");
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, MorePackets.a, true) && !Teleport.E(spartanPlayer) && !(spartanPlayer.getVehicle() instanceof LivingEntity) && !BlockBreak.E(spartanPlayer) && !GameModeProtection.E(spartanPlayer);
    }
    
    static {
        a = Enums.HackType.MorePackets;
        A = new HashMap<UUID, SpartanLocation>();
    }
}
