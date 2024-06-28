package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.checks.combat.c.KillAuraOverall;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Turtle;
import me.vagdedes.spartan.Register;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Giant;
import org.bukkit.entity.EnderDragon;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.system.Enums;

public class CombatUtils
{
    public static final Enums.HackType[] a;
    
    public CombatUtils() {
        super();
    }
    
    public static boolean d(final Entity entity) {
        return (entity instanceof Monster || entity instanceof Animals || entity instanceof Villager || (entity instanceof Player && !NPC.is((Player)entity))) && !(entity instanceof EnderDragon) && !(entity instanceof Giant) && !(entity instanceof Wither) && !(entity instanceof Blaze) && !(entity instanceof Enderman) && (!(entity instanceof Slime) || ((Slime)entity).getSize() != 4) && (!(entity instanceof MagmaCube) || ((MagmaCube)entity).getSize() != 4) && !(entity instanceof EnderCrystal) && (!Register.v1_13 || !(entity instanceof Turtle)) && (!Register.v1_14 || !(entity instanceof Ravager));
    }
    
    public static boolean a(final Entity entity, final double n) {
        return entity instanceof LivingEntity && (entity.isDead() || (((Damageable)entity).getHealth() - n <= 0.0 && !((LivingEntity)entity).hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE) && !((LivingEntity)entity).hasPotionEffect(PotionEffectType.REGENERATION)));
    }
    
    public static float a(final float n, final float n2) {
        final float abs = Math.abs(n - n2);
        return (abs <= 360.0f) ? abs : (abs - 360.0f);
    }
    
    public static double a(final double n, final double n2) {
        final double abs = Math.abs(n - n2);
        return (abs <= 360.0) ? abs : (abs - 360.0);
    }
    
    public static double c(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        final double b = MathUtils.b(a, new SpartanLocation(entity.getLocation()));
        return Math.sqrt(Math.pow(b, 2.0) + Math.pow(Math.abs(Math.sin(Math.toRadians((double)a.getPitch())) * b), 2.0));
    }
    
    public static double d(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        return Math.sin(Math.toRadians((double)a.getPitch())) * MathUtils.b(a, new SpartanLocation(entity.getLocation()));
    }
    
    private static float[] a(final SpartanPlayer spartanPlayer, final LivingEntity livingEntity) {
        if (!d((Entity)livingEntity)) {
            return null;
        }
        final double x = livingEntity.getLocation().getX() - spartanPlayer.a().getX();
        final double y = livingEntity.getLocation().getY() + livingEntity.getEyeHeight() * 0.9 - (spartanPlayer.a().getY() + spartanPlayer.getEyeHeight());
        final double y2 = livingEntity.getLocation().getZ() - spartanPlayer.a().getZ();
        return new float[] { spartanPlayer.a().getYaw() + MathUtils.b((float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f - spartanPlayer.a().getYaw()), spartanPlayer.a().getPitch() + MathUtils.b((float)(-(Math.atan2(y, Math.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)) - spartanPlayer.a().getPitch()) };
    }
    
    private static float[] a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final double x = spartanPlayer2.a().getX() - spartanPlayer.a().getX();
        final double y = spartanPlayer2.a().getY() + spartanPlayer2.getEyeHeight() * 0.9 - (spartanPlayer.a().getY() + spartanPlayer.getEyeHeight());
        final double y2 = spartanPlayer2.a().getZ() - spartanPlayer.a().getZ();
        return new float[] { spartanPlayer.a().getYaw() + MathUtils.b((float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f - spartanPlayer.a().getYaw()), spartanPlayer.a().getPitch() + MathUtils.b((float)(-(Math.atan2(y, Math.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)) - spartanPlayer.a().getPitch()) };
    }
    
    public static double e(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!d(entity)) {
            return 0.0;
        }
        final float[] a = a(spartanPlayer, (LivingEntity)entity);
        if (a == null) {
            return 0.0;
        }
        final float n = a[0] - spartanPlayer.a().getYaw();
        final float n2 = a[1] - spartanPlayer.a().getPitch();
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static double d(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final float[] a = a(spartanPlayer, spartanPlayer2);
        final float n = a[0] - spartanPlayer.a().getYaw();
        final float n2 = a[1] - spartanPlayer.a().getPitch();
        return Math.sqrt(n * n + n2 * n2);
    }
    
    public static double f(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        return MathUtils.b(a.b(a.getDirection().multiply(MathUtils.b(a, spartanLocation))), spartanLocation);
    }
    
    public static double e(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation a2 = spartanPlayer2.a();
        return MathUtils.b(a.b(a.getDirection().multiply(MathUtils.b(a, a2))), a2);
    }
    
    public static double g(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        return MathUtils.b(a.b(a.getDirection().multiply(MoveUtils.a(a, spartanLocation))), spartanLocation);
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!d(entity)) {
            return false;
        }
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        a.setPitch(0.0f);
        final double a2 = MoveUtils.a(a, spartanLocation);
        final double eyeHeight = ((LivingEntity)entity).getEyeHeight();
        final SpartanLocation b = a.b().b(a.getDirection().multiply(a2));
        final SpartanLocation b2 = a.b().b(a.getDirection().multiply(a2 + 1.0));
        final SpartanLocation b3 = b.b().b(0.0, 1.0, 0.0);
        final SpartanLocation b4 = b2.b().b(0.0, 1.0, 0.0);
        return BlockUtils.f(spartanPlayer, b) || BlockUtils.f(spartanPlayer, b2) || (eyeHeight > 1.0 && (BlockUtils.f(spartanPlayer, b3) || BlockUtils.f(spartanPlayer, b4)));
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        if (spartanPlayer == null || spartanPlayer2 == null) {
            return false;
        }
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation a2 = spartanPlayer2.a();
        a.setPitch(0.0f);
        final double a3 = MoveUtils.a(a, a2);
        final double eyeHeight = spartanPlayer2.getEyeHeight();
        final SpartanLocation b = a.b().b(a.getDirection().multiply(a3));
        final SpartanLocation b2 = a.b().b(a.getDirection().multiply(a3 + 1.0));
        final SpartanLocation b3 = b.b().b(0.0, 1.0, 0.0);
        final SpartanLocation b4 = b2.b().b(0.0, 1.0, 0.0);
        return BlockUtils.f(spartanPlayer, b) || BlockUtils.f(spartanPlayer, b2) || (eyeHeight > 1.0 && (BlockUtils.f(spartanPlayer, b3) || BlockUtils.f(spartanPlayer, b4)));
    }
    
    public static double h(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        a.setY(1.0);
        spartanLocation.setY(1.0);
        final Vector subtract = spartanLocation.toVector().subtract(a.toVector());
        final SpartanLocation b = a.b();
        b.setPitch(0.0f);
        return subtract.normalize().dot(b.getDirection());
    }
    
    public static double f(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation a2 = spartanPlayer2.a();
        a.setY(1.0);
        a2.setY(1.0);
        final Vector subtract = a2.toVector().subtract(a.toVector());
        final SpartanLocation b = a.b();
        b.setPitch(0.0f);
        return subtract.normalize().dot(b.getDirection());
    }
    
    public static double i(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        a.setY(0.0);
        a.setPitch(0.0f);
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        spartanLocation.setPitch(0.0f);
        spartanLocation.setY(0.0);
        if (MoveUtils.a(a, spartanLocation) < 1.0) {
            return 0.0;
        }
        final Vector normalize = a.a(spartanLocation).toVector().normalize();
        final Vector normalize2 = a.getDirection().normalize();
        normalize2.setX(normalize2.getX() * -1.0);
        normalize2.setY(normalize2.getY() * -1.0);
        normalize2.setZ(normalize2.getZ() * -1.0);
        return normalize.angle(normalize2);
    }
    
    public static SpartanLocation a(final SpartanPlayer spartanPlayer, final Entity entity) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        final Vector subtract = new Vector(a.getX() - spartanLocation.getX(), a.getY() - spartanLocation.getY(), a.getZ() - spartanLocation.getZ()).subtract(a.getDirection().normalize().multiply(a.a(spartanLocation)));
        return new SpartanLocation(new Location(spartanPlayer.getWorld(), subtract.getX(), subtract.getY(), subtract.getZ(), spartanPlayer.a().getYaw(), spartanPlayer.a().getPitch()));
    }
    
    public static double j(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!d(entity)) {
            return 0.0;
        }
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation().add(0.0, ((LivingEntity)entity).getEyeHeight(), 0.0));
        final SpartanLocation b = spartanPlayer.a().b(0.0, spartanPlayer.getEyeHeight(), 0.0);
        final Vector vector = new Vector(b.getYaw(), b.getPitch(), 0.0f);
        final Vector a = a(b, spartanLocation);
        final double b2 = MathUtils.b(vector.getX() - a.getX());
        final double b3 = MathUtils.b(vector.getY() - a.getY());
        final double b4 = MathUtils.b(b, spartanLocation);
        final double a2 = b.a(spartanLocation);
        return Math.abs(b2 * b4 * a2) + Math.abs(b3 * Math.abs(spartanLocation.getY() - b.getY()) * a2);
    }
    
    public static double k(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!d(entity)) {
            return 0.0;
        }
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation().add(0.0, ((LivingEntity)entity).getEyeHeight(), 0.0));
        final SpartanLocation b = spartanPlayer.a().b(0.0, spartanPlayer.getEyeHeight(), 0.0);
        return MathUtils.b(new Vector(b.getYaw(), b.getPitch(), 0.0f).distance(a(b, spartanLocation)));
    }
    
    private static Vector a(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        final double x = spartanLocation2.getX() - spartanLocation.getX();
        final double y = spartanLocation2.getY() - spartanLocation.getY();
        final double y2 = spartanLocation2.getZ() - spartanLocation.getZ();
        return new Vector((float)(Math.atan2(y2, x) * 180.0 / 3.141592653589793) - 90.0f, (float)(-(Math.atan2(y, Math.sqrt(x * x + y2 * y2)) * 180.0 / 3.141592653589793)), 0.0f);
    }
    
    public static double l(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (!d(entity)) {
            return 0.0;
        }
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        final double n = a.getY() - a.getBlockY();
        a.setY(1.0);
        spartanLocation.setY(1.0);
        final double n2 = a.b().b(a.b().getDirection().multiply(MoveUtils.a(a, spartanLocation) / 4.0 * 3.0)).getY() - n;
        final double eyeHeight = ((LivingEntity)entity).getEyeHeight();
        return Math.min(eyeHeight, Math.abs(n2 - eyeHeight));
    }
    
    public static boolean ah(final SpartanPlayer spartanPlayer) {
        final Compatibility compatibility = new Compatibility("RecentPvPMechanics");
        if ((compatibility.isEnabled() && Register.v1_9) || compatibility.c()) {
            final ItemStack itemInHand = spartanPlayer.getItemInHand();
            if (itemInHand.getType() == Material.DIAMOND_SWORD || itemInHand.getType() == MaterialUtils.a("gold_sword") || itemInHand.getType() == Material.IRON_SWORD || itemInHand.getType() == Material.STONE_SWORD || itemInHand.getType() == MaterialUtils.a("wood_sword")) {
                return PlayerData.c(spartanPlayer, 6.0) > 1;
            }
        }
        return false;
    }
    
    public static boolean ai(final SpartanPlayer spartanPlayer) {
        return Register.v1_9 && !Settings.canDo("Detections.ignore_shields") && (PlayerData.a(spartanPlayer, Material.SHIELD) || spartanPlayer.isBlocking());
    }
    
    public static int j(final SpartanPlayer spartanPlayer) {
        final int a = VL.a(spartanPlayer, Enums.HackType.KillAura);
        return a + VL.a(spartanPlayer, Enums.HackType.HitReach) + VL.a(spartanPlayer, Enums.HackType.Criticals) + VL.a(spartanPlayer, Enums.HackType.FastClicks) + VL.a(spartanPlayer, Enums.HackType.CombatAnalysis) + (KillAuraOverall.j(spartanPlayer) - a) / 5;
    }
    
    public static boolean aj(final SpartanPlayer spartanPlayer) {
        return !PlayerData.av(spartanPlayer) && TimeBetweenClicks.a(spartanPlayer) > 3000L && PlayerData.q(spartanPlayer) <= 15;
    }
    
    static {
        a = new Enums.HackType[] { Enums.HackType.KillAura, Enums.HackType.Criticals, Enums.HackType.HitReach, Enums.HackType.CombatAnalysis, Enums.HackType.Velocity };
    }
}
