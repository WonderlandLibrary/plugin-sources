package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.e.EventsHandler1;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import me.vagdedes.spartan.Register;
import org.bukkit.entity.Pig;
import me.vagdedes.spartan.h.LowViolation;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class EntityMove
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, UUID> y;
    private static final boolean f;
    private static final double s = 1.0;
    private static final double t = 0.25;
    private static final double u = 0.3;
    private static final double v = 0.15;
    private static final double w = 0.4;
    private static final double A = 0.2;
    private static final double k = 0.5;
    
    public EntityMove() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        EntityMove.y.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        EntityMove.y.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final SpartanLocation spartanLocation3, final double d, final double d2) {
        final Entity vehicle = spartanPlayer.getVehicle();
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final boolean b = VL.a(spartanPlayer, EntityMove.a) > 0;
        if (!a(spartanPlayer, vehicle)) {
            CooldownUtils.d(spartanPlayer, EntityMove.a.toString() + "=distance=protection", b ? 10 : 40);
            DoubleUtils.j(spartanPlayer, EntityMove.a.toString() + "=y");
            EntityMove.y.remove(uniqueId);
            return;
        }
        if (!EntityMove.y.containsKey(uniqueId) || (EntityMove.y.containsKey(uniqueId) && vehicle.getUniqueId() != EntityMove.y.get(uniqueId))) {
            CooldownUtils.d(spartanPlayer, EntityMove.a.toString() + "=distance=protection", b ? 10 : 40);
            EntityMove.y.put(uniqueId, vehicle.getUniqueId());
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, EntityMove.a.toString() + "=distance=protection")) {
            return;
        }
        double d3 = 0.0;
        final double b2 = MathUtils.b(spartanLocation, spartanLocation2);
        final double abs = Math.abs(d2 - d);
        final boolean boolean1 = Checks.getBoolean("EntityMove.check_vertical");
        final boolean b3 = PlayerData.d(null, spartanLocation3, true) || PlayerData.c(null, spartanLocation3.b().b(0.0, -1.0, 0.0), -1.0);
        if (!b3) {
            AttemptUtils.a(spartanPlayer, EntityMove.a.toString() + "=air-ticks", 1);
        }
        else {
            AttemptUtils.m(spartanPlayer, EntityMove.a.toString() + "=air-ticks");
        }
        final int a = AttemptUtils.a(spartanPlayer, EntityMove.a.toString() + "=air-ticks");
        if (d >= 2.0 && boolean1) {
            a(spartanPlayer, spartanLocation2, "t: vertical(instant), dy: " + d, vehicle);
            return;
        }
        if (b(vehicle)) {
            if (Liquid.e(spartanPlayer) <= 51L && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0)) {
                if (AttemptUtils.a(spartanPlayer, EntityMove.a.toString() + "=water-ticks", 1) > 5) {
                    d3 = 0.5;
                }
            }
            else {
                AttemptUtils.m(spartanPlayer, EntityMove.a.toString() + "=water-ticks");
                if ((vehicle instanceof Horse && ((Horse)vehicle).getInventory().contains(Material.SADDLE)) || (vehicle instanceof SkeletonHorse && ((SkeletonHorse)vehicle).getInventory().contains(Material.SADDLE)) || (vehicle instanceof ZombieHorse && ((ZombieHorse)vehicle).getInventory().contains(Material.SADDLE)) || (vehicle instanceof Donkey && ((Donkey)vehicle).getInventory().contains(Material.SADDLE))) {
                    d3 = 1.0;
                }
                else {
                    d3 = 0.25;
                }
            }
            if (boolean1 && !b3 && !PlayerData.c(null, spartanLocation3.b().b(0.0, -2.0, 0.0), -2.0)) {
                final LowViolation lowViolation = new LowViolation(spartanPlayer, EntityMove.a, "horse");
                if (d > 1.0 && lowViolation.q()) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(normal), dy: " + d, vehicle);
                }
                else if (d > 0.0 && a > 30 && lowViolation.q()) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(air), dy: " + d + ", air: " + a, vehicle);
                }
                else if (abs >= 1.1 && lowViolation.q()) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(diff), dy: " + d + ", diff: " + abs, vehicle);
                }
                else if (d >= d2 && d > 1.0 && d2 != 0.0 && lowViolation.q()) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(illegal), dy: " + d + ", ody: " + d2, vehicle);
                }
            }
        }
        else if (vehicle instanceof Pig) {
            if (Liquid.e(spartanPlayer) <= 51L && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0)) {
                if (AttemptUtils.a(spartanPlayer, EntityMove.a.toString() + "=water-ticks", 1) > 5) {
                    d3 = 0.5;
                }
            }
            else {
                AttemptUtils.m(spartanPlayer, EntityMove.a.toString() + "=water-ticks");
                if (spartanPlayer.getItemInHand().getType() == (Register.v1_13 ? Material.CARROT_ON_A_STICK : Material.getMaterial("CARROT_STICK"))) {
                    d3 = 0.3;
                }
                else {
                    d3 = 0.15;
                }
            }
            if (boolean1 && !b3) {
                if (d >= 0.1) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(normal), dy: " + d, vehicle);
                }
                else if (d > 0.0 && a >= 12) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(air), dy: " + d + ", air: " + a, vehicle);
                }
                else if (abs >= 0.1) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(diff), dy: " + d + ", diff: " + abs, vehicle);
                }
                else if (d >= d2 && d != 0.0 && d2 != 0.0) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(illegal), dy: " + d + ", ody: " + d2, vehicle);
                }
            }
        }
        else if (vehicle instanceof Llama) {
            if (Liquid.e(spartanPlayer) <= 51L && BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0)) {
                if (AttemptUtils.a(spartanPlayer, EntityMove.a.toString() + "=water-ticks", 1) > 5) {
                    d3 = 0.5;
                }
            }
            else {
                AttemptUtils.m(spartanPlayer, EntityMove.a.toString() + "=water-ticks");
                boolean b4 = false;
                final ItemStack[] contents = ((Llama)vehicle).getInventory().getContents();
                for (int length = contents.length, i = 0; i < length; ++i) {
                    final String string = contents[i].toString();
                    if (string.equals("CARPET") || string.endsWith("_CARPET")) {
                        b4 = true;
                        break;
                    }
                }
                if (b4) {
                    d3 = 0.4;
                }
                else {
                    d3 = 0.2;
                }
            }
            if (boolean1 && !b3) {
                if (d >= 0.1) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(normal), dy: " + d, vehicle);
                }
                else if (d > 0.0 && a >= 12) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(air), dy: " + d + ", air: " + a, vehicle);
                }
                else if (abs >= 0.1) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(diff), dy: " + d + ", diff: " + abs, vehicle);
                }
                else if (d >= d2 && d != 0.0 && d2 != 0.0) {
                    a(spartanPlayer, spartanLocation2, "t: vertical(illegal), dy: " + d + ", ody: " + d2, vehicle);
                }
            }
        }
        final double f = LagManagement.f(spartanPlayer, MoveUtils.a((LivingEntity)vehicle, d3, 3.0, PotionEffectType.SPEED));
        if (Checks.getBoolean("EntityMove.check_speed") && d3 > 0.0 && b2 >= f) {
            a(spartanPlayer, spartanLocation2, "t: speed, ds: " + b2 + ", dm: " + d3 + ", dm_s: " + f, vehicle);
        }
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String str, final Entity entity) {
        EventsHandler1.a(spartanPlayer, EntityMove.a, spartanLocation, "e: " + entity.getType().toString().toLowerCase().replace((CharSequence)"_", (CharSequence)"-") + ", " + str, true, true);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!Register.v1_9 || entity == null || (!(entity instanceof Pig) && !b(entity))) {
            return false;
        }
        if (((LivingEntity)entity).hasPotionEffect(PotionEffectType.LEVITATION)) {
            VL.a(spartanPlayer, EntityMove.a, 40);
            return false;
        }
        return !VL.b(spartanPlayer, EntityMove.a, true) && !Explosion.E(spartanPlayer) && !Piston.E(spartanPlayer) && !BouncingBlocks.T(spartanPlayer) && !((LivingEntity)entity).isLeashed();
    }
    
    private static boolean b(final Entity entity) {
        return entity instanceof Llama || entity instanceof Horse || (EntityMove.f && (entity instanceof SkeletonHorse || entity instanceof ZombieHorse || entity instanceof Donkey));
    }
    
    static {
        a = Enums.HackType.EntityMove;
        y = new HashMap<UUID, UUID>();
        f = (VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.d && VersionUtils.a() != VersionUtils.a.e && VersionUtils.a() != VersionUtils.a.f);
    }
}
