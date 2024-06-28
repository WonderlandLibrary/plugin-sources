package me.vagdedes.spartan.checks.combat;

import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.h.Teleport;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.Material;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Arrow;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.AreaEffectCloud;
import me.vagdedes.spartan.Register;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.d.MathUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.Location;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class VelocityCheck
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, SpartanLocation> o;
    private static final HashMap<UUID, SpartanLocation> p;
    private static final int b = 5;
    
    public VelocityCheck() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        VelocityCheck.o.remove(spartanPlayer.getUniqueId());
        VelocityCheck.p.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        VelocityCheck.o.clear();
        VelocityCheck.p.clear();
    }
    
    public static void i(final SpartanPlayer spartanPlayer) {
        a(spartanPlayer);
        DoubleUtils.j(spartanPlayer, VelocityCheck.a.toString() + "=ver");
    }
    
    public static void j(final SpartanPlayer spartanPlayer) {
        VL.a(spartanPlayer, VelocityCheck.a, 10);
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final int a = CooldownUtils.a(spartanPlayer, VelocityCheck.a.toString() + "=scheduler");
        if (a == 1 && VelocityCheck.o.containsKey(uniqueId) && b(spartanPlayer)) {
            final SpartanLocation a2 = spartanPlayer.a();
            final SpartanLocation spartanLocation = (SpartanLocation)VelocityCheck.o.get(uniqueId);
            if (a2.getWorld() == spartanLocation.getWorld()) {
                a(spartanPlayer, a2, spartanLocation, a2.getY() - spartanLocation.getY(), a);
                final HackPrevention a3 = HackPrevention.a(spartanPlayer, new Enums.HackType[] { Enums.HackType.Velocity }, true);
                if (a3 != null) {
                    final Player player = spartanPlayer.getPlayer();
                    if (a3.a() != null) {
                        MoveUtils.a(player, new Location(spartanLocation.getWorld(), spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ()));
                    }
                    if (a3.i()) {
                        MoveUtils.a(player, spartanPlayer);
                    }
                }
            }
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double d, final double d2) {
        final double a = spartanLocation.a(spartanLocation2);
        final double b = MathUtils.b(spartanLocation, spartanLocation2);
        final double n = d + b;
        final boolean boolean1 = Checks.getBoolean("Velocity.check_distance");
        final boolean b2 = !CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=knockback");
        final boolean b3 = !b2 && !CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=projectile");
        final int a2 = LagManagement.a(spartanPlayer, (VL.a(spartanPlayer, VelocityCheck.a) >= 2 || b3 || b2) ? 2 : 3);
        final int n2 = (a2 > 5) ? 5 : a2;
        final boolean b4 = d == 0.0;
        if (a >= 1.0 / d2 && !b2 && Checks.getBoolean("Velocity.check_extreme")) {
            final double abs = Math.abs(a - (b + Math.abs(d)));
            if (abs >= 1.5 / d2 && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=extreme=scheduler=" + d2, 77) >= n2) {
                a(spartanPlayer, "t: extreme(scheduler), diff: " + abs, spartanLocation2, d2);
            }
        }
        if (a <= 0.3 / d2 && d != 0.0 && boolean1 && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=distance=hard=" + d2, 77) >= n2) {
            a(spartanPlayer, "t: distance(hard), d: " + a, null, d2);
        }
        if (n == a && d != 0.0 && !b3 && Checks.getBoolean("Velocity.check_combined") && !BouncingBlocks.R(spartanPlayer) && PlayerData.av(spartanPlayer) && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=combined=" + d2, 55) >= n2) {
            a(spartanPlayer, "t: combined, d: " + a + ", v: " + d, null, d2);
        }
        if (a <= 0.15 / d2 && boolean1 && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=distance=sensitive=" + d2, 66) >= n2) {
            a(spartanPlayer, "t: distance(sensitive), d: " + a + ", v: " + d, null, d2);
        }
        if (d > 0.0 && d <= 0.5 / d2 && !b3 && Checks.getBoolean("Velocity.check_vertical")) {
            final double a3 = DoubleUtils.a(spartanPlayer, VelocityCheck.a.toString() + "=ver=" + d2);
            if (DoubleUtils.h(a3)) {
                final double abs2 = Math.abs(d - a3);
                if (abs2 <= 0.01 && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=vertical", 77) >= n2) {
                    a(spartanPlayer, "t: vertical, v: " + d + ", diff: " + abs2, null, d2);
                }
            }
            DoubleUtils.a(spartanPlayer, VelocityCheck.a.toString() + "=ver=" + d2, d);
        }
        else {
            DoubleUtils.j(spartanPlayer, VelocityCheck.a.toString() + "=ver=" + d2);
        }
        if (d >= 0.0 && (b < 0.1 || Math.abs(a - b) <= 0.05) && Checks.getBoolean("Velocity.check_horizontal") && AttemptUtils.b(spartanPlayer, VelocityCheck.a.toString() + "=horizontal=" + d2, 99) >= (b4 ? 6 : 2)) {
            a(spartanPlayer, "t: horizontal, ver: " + d + ", hor: " + b, null, d2);
        }
        if (a >= 2.5 / d2 && !b3 && CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=direction=" + d2) && Checks.getBoolean("Velocity.check_opposite")) {
            final SpartanLocation b5 = spartanLocation2.b();
            final double b6 = MathUtils.b(b5.b(b5.getDirection().multiply(a)), spartanLocation);
            final double d3 = (b6 - a) / a * 100.0;
            if (b6 <= 0.5 / d2) {
                a(spartanPlayer, "t: opposite, per: " + d3 + ", m: " + b6 + ", d: " + a, null, d2);
            }
        }
        if (VelocityCheck.p.containsKey(spartanPlayer.getUniqueId()) && a >= 3.0 / d2 && !b3 && Checks.getBoolean("Velocity.check_direction")) {
            final double b7 = MathUtils.b(spartanLocation2.b(((SpartanLocation)VelocityCheck.p.get(spartanPlayer.getUniqueId())).getDirection().multiply(a)), spartanLocation);
            final double d4 = b7 - a;
            if (b7 >= 2.5 / d2 && d4 >= 1.0 / d2) {
                a(spartanPlayer, "t: direction, m: " + b7 + ", d: " + a + ", diff: " + d4, spartanLocation2, d2);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final EntityDamageEvent.DamageCause damageCause) {
        if (!b(spartanPlayer) || !CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=scheduler") || entity instanceof EnderDragon || (Register.v1_9 && entity instanceof AreaEffectCloud) || (entity instanceof Player && CombatUtils.b(SpartanBukkit.a(entity.getUniqueId(), 2), spartanPlayer))) {
            return;
        }
        final boolean b = damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK;
        final boolean b2 = damageCause == EntityDamageEvent.DamageCause.PROJECTILE && entity instanceof Arrow;
        if (b || b2) {
            final double distance = spartanPlayer.a().getDirection().distance(entity.getLocation().getDirection());
            VelocityCheck.o.put(spartanPlayer.getUniqueId(), spartanPlayer.a());
            CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=damage", 2);
            CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=scheduler", 5);
            if (!(entity instanceof Player) || VL.a(SpartanBukkit.a(entity.getUniqueId()), Enums.HackType.KillAura) == 0) {
                VelocityCheck.p.put(spartanPlayer.getUniqueId(), new SpartanLocation(entity.getLocation()));
            }
            if (distance <= 1.0) {
                CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=direction", 5);
            }
            if (b2) {
                final ProjectileSource shooter = ((Projectile)entity).getShooter();
                if (shooter instanceof Player) {
                    final ItemStack itemInHand = ((Player)shooter).getItemInHand();
                    if (itemInHand.getType() == Material.BOW && itemInHand.containsEnchantment(Enchantment.ARROW_KNOCKBACK)) {
                        CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=knockback", 5);
                    }
                }
                CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=projectile", 5);
            }
            else if (entity instanceof Player && !spartanPlayer.getUniqueId().equals(entity.getUniqueId())) {
                final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
                if (a != null && (a.getItemInHand().containsEnchantment(Enchantment.KNOCKBACK) || a.isFlying())) {
                    CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=knockback", 5);
                }
            }
            if (b && entity.getLocation().getPitch() <= 0.0) {
                CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=pitch=up", 5);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double d, final double d2) {
        final boolean g = CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=scheduler");
        if (g || MoveUtils.b(n)) {
            CooldownUtils.d(spartanPlayer, VelocityCheck.a.toString() + "=jumping", 5);
            if (g) {
                return;
            }
        }
        if (!CooldownUtils.g(spartanPlayer, VelocityCheck.a.toString() + "=knockback") || PlayerData.b(spartanPlayer) != 0.0f || PlayerData.aA(spartanPlayer) || !Checks.getBoolean("Velocity.check_extreme") || (!PlayerData.au(spartanPlayer) && !PlayerData.av(spartanPlayer)) || !b(spartanPlayer)) {
            return;
        }
        final double f = LagManagement.f(spartanPlayer, MoveUtils.a(spartanPlayer, 1.25, 4.0, PotionEffectType.SPEED));
        final double abs = Math.abs(d - d2);
        if (abs >= f) {
            a(spartanPlayer, "t: extreme(event), d: " + d + ", od: " + d2 + ", diff: " + abs + ", l:" + f, spartanLocation, 0.0);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String str, final SpartanLocation spartanLocation, final double n) {
        new HackPrevention(spartanPlayer, VelocityCheck.a, "tick: " + (int)n + ", " + str, spartanLocation, 0, true);
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        if (!VL.b(spartanPlayer, VelocityCheck.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && !PlayerData.aR(spartanPlayer) && !MoveUtils.c(spartanPlayer, -1.0) && !CombatUtils.ai(spartanPlayer) && !PlayerData.aY(spartanPlayer) && !PlayerData.ba(spartanPlayer) && CombatHeuristics.a(spartanPlayer, 6.0) <= 3 && !NoHitDelay.E(spartanPlayer)) {
            final SpartanLocation a = spartanPlayer.a();
            for (int i = -1; i <= 2; ++i) {
                if ((((i >= 0 && !BlockUtils.c(spartanPlayer, true, 1.0, i, 1.0)) || !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) && !PlayerData.i(spartanPlayer, 0.0, -0.5, 0.0) && !BlockUtils.c(spartanPlayer, true, 1.0, i, 1.0)) || BlockUtils.a(spartanPlayer, (a == null) ? a.b().b(0.0, i, 0.0) : a.b().b(0.0, i, 0.0), MaterialUtils.a("web"), 0.3) || BlockUtils.G(spartanPlayer, a.b().b(0.0, (double)i, 0.0))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    static {
        a = Enums.HackType.Velocity;
        o = new HashMap<UUID, SpartanLocation>();
        p = new HashMap<UUID, SpartanLocation>();
    }
}
