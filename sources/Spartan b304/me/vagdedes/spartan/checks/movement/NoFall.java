package me.vagdedes.spartan.checks.movement;

import org.bukkit.inventory.ItemStack;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.GameRule;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class NoFall
{
    private static final Enums.HackType a;
    
    public NoFall() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        DoubleUtils.j(spartanPlayer, NoFall.a.toString() + "=old");
    }
    
    public static void t(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, NoFall.a.toString() + "=land", 10);
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n) {
        if (b(spartanPlayer)) {
            boolean b = true;
            for (int i = -2; i <= 2; ++i) {
                if (PlayerData.i(spartanPlayer, 0.0, i, 0.0)) {
                    b = false;
                    break;
                }
            }
            final double a = DoubleUtils.a(spartanPlayer, NoFall.a.toString() + "=old");
            final int q = PlayerData.q(spartanPlayer);
            b(spartanPlayer, n, spartanLocation2);
            a(spartanPlayer, n, q, spartanLocation, spartanLocation2);
            if (b && !Building.E(spartanPlayer) && n <= -0.1 && DoubleUtils.h(a)) {
                final float n2 = spartanPlayer.getFallDistance() - (float)a;
                if (spartanLocation.getBlockY() < spartanLocation2.getBlockY()) {
                    final float fallDistance = spartanPlayer.getFallDistance();
                    a(spartanPlayer, n2, (double)fallDistance - Math.floor(fallDistance), n, q, spartanLocation2);
                    a(spartanPlayer, n2, n, q, spartanLocation2);
                }
            }
        }
        else {
            x(spartanPlayer);
        }
        DoubleUtils.a(spartanPlayer, NoFall.a.toString() + "=old", spartanPlayer.getFallDistance());
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String s, final SpartanLocation spartanLocation, final int n, final double n2) {
        new HackPrevention(spartanPlayer, NoFall.a, s, spartanLocation, n, true, n2);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final float f, final double n, final double d, final int i, final SpartanLocation spartanLocation) {
        if (!Checks.getBoolean("NoFall.check_air") || ElytraGlide.bc(spartanPlayer) || LatencyUtils.e(spartanPlayer, 200) || d <= -3.5 || i == 0 || BlockUtils.g(spartanPlayer, true, 0.3, 1.0, 0.3) || BlockUtils.g(spartanPlayer, true, 0.3, 0.0, 0.3) || BlockUtils.g(spartanPlayer, true, 0.3, -1.0, 0.3) || PlayerData.az(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, NoFall.a.toString() + "=air=cooldown", 10);
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, NoFall.a.toString() + "=air=cooldown")) {
            return;
        }
        if (f <= 0.0f && n == 0.0 && (i >= 8 || VL.a(spartanPlayer, NoFall.a) > 0 || d <= -0.5)) {
            a(spartanPlayer, "t: air, f: " + f + ", a: " + i + ", d: " + d, spartanLocation, 5, 2.0);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final float f, final double d, final int i, final SpartanLocation spartanLocation) {
        if (!Checks.getBoolean("NoFall.check_pattern") || PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || LatencyUtils.e(spartanPlayer, 200) || PlayerData.az(spartanPlayer)) {
            return;
        }
        final String string = NoFall.a.toString() + "=pattern";
        if (f > 0.0f) {
            AttemptUtils.a(spartanPlayer, string, 1);
        }
        else {
            final int a = AttemptUtils.a(spartanPlayer, string);
            if (a > 1) {
                if (AttemptUtils.f(spartanPlayer, string + "=cache") && AttemptUtils.a(spartanPlayer, string + "=cache") == a) {
                    a(spartanPlayer, "t: pattern, f: " + f + ", a: " + i + ", d: " + d, spartanLocation, 5, 2.0);
                }
                AttemptUtils.c(spartanPlayer, string + "=cache", a);
            }
            AttemptUtils.m(spartanPlayer, string);
        }
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final double n, final SpartanLocation spartanLocation) {
        if (!Checks.getBoolean("NoFall.check_landing") || PlayerData.aw(spartanPlayer) || PlayerData.az(spartanPlayer)) {
            return;
        }
        if (n < 0.0) {
            if (spartanPlayer.getFallDistance() >= 6.0f) {
                CooldownUtils.d(spartanPlayer, NoFall.a.toString() + "=spoofed(land)", 10);
            }
        }
        else if (!CooldownUtils.g(spartanPlayer, NoFall.a.toString() + "=spoofed(land)")) {
            CooldownUtils.j(spartanPlayer, NoFall.a.toString() + "=spoofed(land)");
            if (PlayerData.L(spartanPlayer, spartanPlayer.a()) && MoveUtils.ao(spartanPlayer) && BlockUtils.c(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0), true) && BlockUtils.c(spartanPlayer, true, 0.298, 0.0, 0.298) && !spartanPlayer.getAllowFlight() && CooldownUtils.g(spartanPlayer, NoFall.a.toString() + "=land")) {
                a(spartanPlayer, "t: land", spartanLocation, 10, 5.0);
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final double n, final int n2, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (Building.E(spartanPlayer)) {
            return;
        }
        final String string = NoFall.a.toString() + "=ratio=";
        if (PlayerData.az(spartanPlayer) || AttemptUtils.a(spartanPlayer, string + "cancelled") == 1) {
            x(spartanPlayer);
            return;
        }
        final int blockY = spartanLocation.getBlockY();
        if (blockY < spartanLocation2.getBlockY()) {
            DoubleUtils.j(spartanPlayer, string + "damage");
            if (AttemptUtils.f(spartanPlayer, string + "block-y") && AttemptUtils.a(spartanPlayer, string + "block-y") != blockY) {
                AttemptUtils.a(spartanPlayer, string + "blocks", 1);
            }
            AttemptUtils.c(spartanPlayer, string + "block-y", blockY);
            if (n <= -0.6272000122064 && n2 > 8) {
                AttemptUtils.c(spartanPlayer, string + "validated", 1);
            }
        }
        else if (AttemptUtils.a(spartanPlayer, string + "validated") == 1 && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || n >= 0.0 || n2 <= 5)) {
            final int a = AttemptUtils.a(spartanPlayer, string + "blocks");
            if (!MoveUtils.b(n)) {
                if (a > 3 && !DoubleUtils.h(DoubleUtils.a(spartanPlayer, string + "damage"))) {
                    a(spartanPlayer, "t: ratio, b: " + a, spartanLocation, 10, a);
                }
                x(spartanPlayer);
            }
        }
        else {
            DoubleUtils.j(spartanPlayer, string + "damage");
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final EntityDamageEvent.DamageCause damageCause, final double n, final boolean b) {
        final String string = NoFall.a.toString() + "=ratio=";
        if (PlayerData.az(spartanPlayer) || damageCause != EntityDamageEvent.DamageCause.FALL) {
            x(spartanPlayer);
        }
        else if (b) {
            x(spartanPlayer);
            AttemptUtils.c(spartanPlayer, string + "cancelled", 1);
        }
        else {
            DoubleUtils.a(spartanPlayer, string + "damage", n);
        }
    }
    
    public static void x(final SpartanPlayer spartanPlayer) {
        final String string = NoFall.a.toString() + "=ratio=";
        AttemptUtils.b(spartanPlayer, new String[] { string + "blocks", string + "block-y", string + "damage", string + "cancelled", string + "validated" });
        DoubleUtils.j(spartanPlayer, string + "damage");
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        for (int i = 0; i <= 2; ++i) {
            if (BlockUtils.a(spartanPlayer, spartanPlayer.a().b(0.0, (double)i, 0.0), MaterialUtils.a("web"), 1.0)) {
                return false;
            }
        }
        if (!VL.b(spartanPlayer, NoFall.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && !Explosion.E(spartanPlayer) && spartanPlayer.a().getBlockY() >= 0 && !PlayerData.aF(spartanPlayer) && !BouncingBlocks.R(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !BlockUtils.G(spartanPlayer, spartanPlayer.a()) && !EnderPearl.E(spartanPlayer) && Liquid.e(spartanPlayer) > 1000L && !PlayerData.aD(spartanPlayer) && !WaterSoulSand.E(spartanPlayer) && !PlayerData.aS(spartanPlayer) && !PlayerData.bb(spartanPlayer) && !NoHitDelay.E(spartanPlayer) && !GroundUtils.ak(spartanPlayer)) {
            if (Register.v1_15) {
                final World world = spartanPlayer.getWorld();
                if (world.getGameRuleValue(GameRule.FALL_DAMAGE) == world.getGameRuleDefault(GameRule.FALL_DAMAGE)) {
                    return false;
                }
            }
            final ItemStack boots;
            if ((boots = spartanPlayer.a().getBoots()) != null && boots.containsEnchantment(Enchantment.PROTECTION_FALL) && BlockUtils.e(spartanPlayer, true, 0.3, 0.0, 0.3) && BlockUtils.e(spartanPlayer, true, 0.3, 1.0, 0.3)) {
                return true;
            }
        }
        return false;
    }
    
    static {
        a = Enums.HackType.NoFall;
    }
}
