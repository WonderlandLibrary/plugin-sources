package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.h.a.DeathAndRespawn;
import me.vagdedes.spartan.h.Damage;
import org.bukkit.WorldBorder;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.j.ViaVersion;
import me.vagdedes.spartan.h.a.Liquid;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import org.bukkit.potion.PotionEffect;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.g.IDs;
import org.bukkit.GameMode;
import me.vagdedes.spartan.k.a.a.UtilEvents;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.Boat;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanPlayer;

public class PlayerData
{
    public PlayerData() {
        super();
    }
    
    public static boolean as(final SpartanPlayer spartanPlayer) {
        return Register.v1_13 && spartanPlayer.n();
    }
    
    public static boolean at(final SpartanPlayer spartanPlayer) {
        return Register.v1_9 && TimeBetweenClicks.a(spartanPlayer, true) >= 295L;
    }
    
    public static float b(final SpartanPlayer spartanPlayer) {
        final float max = Math.max(spartanPlayer.getWalkSpeed() - 0.2f, spartanPlayer.getFlySpeed() - 0.1f);
        if (max > 0.0f) {
            CooldownUtils.d(spartanPlayer, "walk_difference", 40);
        }
        else if (!CooldownUtils.g(spartanPlayer, "walk_difference")) {
            return 1.0f;
        }
        return (max < 0.0f) ? 0.0f : max;
    }
    
    public static boolean au(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "combat");
    }
    
    public static boolean av(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "hit_entity");
    }
    
    public static void k(final SpartanPlayer spartanPlayer, final int n) {
        if (n > CooldownUtils.a(spartanPlayer, "teleport-cooldown") || n == 0) {
            CooldownUtils.d(spartanPlayer, "teleport-cooldown", n);
        }
    }
    
    public static boolean aw(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "speed_effect")) {
            return true;
        }
        final boolean hasPotionEffect = spartanPlayer.hasPotionEffect(PotionEffectType.SPEED);
        if (hasPotionEffect) {
            CooldownUtils.d(spartanPlayer, "speed_effect", 60);
        }
        return hasPotionEffect;
    }
    
    public static boolean ax(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "high_speed_effect")) {
            return true;
        }
        final boolean b = spartanPlayer.hasPotionEffect(PotionEffectType.SPEED) && a(spartanPlayer, PotionEffectType.SPEED) > 2;
        if (b) {
            CooldownUtils.d(spartanPlayer, "high_speed_effect", 60);
        }
        return b;
    }
    
    public static boolean ay(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "boat=has")) {
            return true;
        }
        final boolean b = spartanPlayer.getVehicle() instanceof Boat;
        if (b) {
            CooldownUtils.d(spartanPlayer, "boat=has", 40);
        }
        return b;
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final boolean b) {
        if (!CooldownUtils.g(spartanPlayer, "vehicle=has") || (b && ay(spartanPlayer))) {
            return true;
        }
        final Entity vehicle = spartanPlayer.getVehicle();
        final boolean b2 = vehicle != null;
        if (b2 && !(vehicle instanceof LeashHitch) && !(vehicle instanceof Boat)) {
            if (vehicle instanceof LivingEntity) {
                CooldownUtils.d(spartanPlayer, "vehicle=has", 60);
                CooldownUtils.d(spartanPlayer, "vehicle=had", 120);
            }
            else {
                CooldownUtils.d(spartanPlayer, "vehicle=has", 20);
            }
        }
        return b2;
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final boolean b) {
        return b(spartanPlayer, b) || !CooldownUtils.g(spartanPlayer, "vehicle=had");
    }
    
    public static void M(final SpartanPlayer spartanPlayer) {
        CooldownUtils.j(spartanPlayer, "vehicle=has");
        CooldownUtils.j(spartanPlayer, "vehicle=had");
    }
    
    public static boolean az(final SpartanPlayer spartanPlayer) {
        return aA(spartanPlayer) | aE(spartanPlayer);
    }
    
    public static boolean aA(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "low_jump_effect")) {
            return true;
        }
        final boolean b = spartanPlayer.hasPotionEffect(PotionEffectType.JUMP) && (a(spartanPlayer, PotionEffectType.JUMP) < 128 || a(spartanPlayer, PotionEffectType.JUMP) > 250);
        if (b) {
            CooldownUtils.d(spartanPlayer, "low_jump_effect", 100);
        }
        return b;
    }
    
    public static boolean aB(final SpartanPlayer spartanPlayer) {
        return Register.v1_13 && spartanPlayer.hasPotionEffect(PotionEffectType.CONDUIT_POWER) && (!BlockUtils.a(spartanPlayer, true, 0.3, 0.0, 0.3) || !BlockUtils.a(spartanPlayer, true, 0.3, 1.0, 0.3));
    }
    
    public static boolean aC(final SpartanPlayer spartanPlayer) {
        if (!Register.v1_13) {
            return false;
        }
        if (!CooldownUtils.g(spartanPlayer, "dolphins_grace_effect")) {
            return true;
        }
        final boolean hasPotionEffect = spartanPlayer.hasPotionEffect(PotionEffectType.DOLPHINS_GRACE);
        if (hasPotionEffect) {
            CooldownUtils.d(spartanPlayer, "dolphins_grace_effect", 20);
        }
        return hasPotionEffect;
    }
    
    public static boolean aD(final SpartanPlayer spartanPlayer) {
        if (!Register.v1_13) {
            return false;
        }
        if (!CooldownUtils.g(spartanPlayer, "slow_falling_effect")) {
            return true;
        }
        final boolean hasPotionEffect = spartanPlayer.hasPotionEffect(PotionEffectType.SLOW_FALLING);
        if (hasPotionEffect) {
            CooldownUtils.d(spartanPlayer, "slow_falling_effect", 20);
        }
        return hasPotionEffect;
    }
    
    public static boolean aE(final SpartanPlayer spartanPlayer) {
        return a(spartanPlayer, PotionEffectType.JUMP) >= 128 && a(spartanPlayer, PotionEffectType.JUMP) <= 250;
    }
    
    public static boolean aF(final SpartanPlayer spartanPlayer) {
        final boolean b = VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.d && spartanPlayer.hasPotionEffect(PotionEffectType.LEVITATION);
        final int n = 60;
        final int a = CooldownUtils.a(spartanPlayer, "levitation_effect");
        if (b) {
            CooldownUtils.d(spartanPlayer, "levitation_effect", n);
        }
        else if (a > 0) {
            if (a >= n - 5) {
                N(spartanPlayer);
            }
            return true;
        }
        return b;
    }
    
    public static boolean aG(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.hasPotionEffect(PotionEffectType.POISON) || spartanPlayer.hasPotionEffect(PotionEffectType.HARM);
    }
    
    public static boolean aH(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.m();
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer, final int n) {
        return AttemptUtils.a(spartanPlayer, "inventory_use") >= n && spartanPlayer.a().countSlots() > 46;
    }
    
    public static boolean aI(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.a().getCursor().getType() != Material.AIR;
    }
    
    public static boolean aJ(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "walking");
    }
    
    public static boolean aK(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "sprint");
    }
    
    public static boolean aL(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "sprint-cache");
    }
    
    public static boolean aM(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "sprint-jumping");
    }
    
    public static boolean aN(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "walk-jumping");
    }
    
    public static boolean aO(final SpartanPlayer spartanPlayer) {
        return !aS(spartanPlayer) && !aV(spartanPlayer) && spartanPlayer.getEyeHeight() <= 1.2;
    }
    
    public static boolean aP(final SpartanPlayer spartanPlayer) {
        final ItemStack itemInHand = spartanPlayer.getItemInHand();
        return itemInHand != null && BlockUtils.b(itemInHand.getType());
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final int n, final double n2) {
        return Register.v1_9 && c(spartanPlayer, n2) >= n;
    }
    
    public static boolean aQ(final SpartanPlayer spartanPlayer) {
        return UtilEvents.aQ(spartanPlayer);
    }
    
    public static boolean aR(final SpartanPlayer spartanPlayer) {
        final VersionUtils.a a = VersionUtils.a();
        boolean b = spartanPlayer.getVehicle() == null && (spartanPlayer.isFlying() || (spartanPlayer.getGameMode() == GameMode.CREATIVE && spartanPlayer.getAllowFlight()) || (a != VersionUtils.a.l && a != VersionUtils.a.c && spartanPlayer.getGameMode() == GameMode.SPECTATOR));
        if (b) {
            CooldownUtils.d(spartanPlayer, "flying", 60);
        }
        else {
            b = !CooldownUtils.g(spartanPlayer, "flying");
        }
        return b;
    }
    
    public static int q(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "air_ticks");
    }
    
    public static int r(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "old_air_ticks");
    }
    
    public static int s(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "ground_ticks");
    }
    
    public static void N(final SpartanPlayer spartanPlayer) {
        AttemptUtils.m(spartanPlayer, "air_ticks");
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (MoveUtils.aq(spartanPlayer) && IDs.spigot().length() >= 1 && IDs.nonce().length() >= 8) {
            final boolean ba = ba(spartanPlayer);
            final boolean a = GroundUtils.a(spartanPlayer, spartanPlayer.a(), 0.0, true, false);
            AttemptUtils.c(spartanPlayer, "old_air_ticks", AttemptUtils.a(spartanPlayer, "air_ticks"));
            if (!a && spartanPlayer.getVehicle() == null && !EnderPearl.E(spartanPlayer) && !ba) {
                AttemptUtils.a(spartanPlayer, "air_ticks", 1);
                AttemptUtils.m(spartanPlayer, "ground_ticks");
            }
            else {
                AttemptUtils.m(spartanPlayer, "air_ticks");
                if (a && !ba) {
                    AttemptUtils.a(spartanPlayer, "ground_ticks", 1);
                }
            }
            if (d(spartanPlayer, 0)) {
                AttemptUtils.a(spartanPlayer, "inventory_use", 1);
            }
            else {
                AttemptUtils.m(spartanPlayer, "inventory_use");
            }
            if (spartanPlayer.isSprinting() || aK(spartanPlayer) || aM(spartanPlayer)) {
                CooldownUtils.d(spartanPlayer, "sprint-cache", 20);
            }
        }
        else {
            AttemptUtils.b(spartanPlayer, new String[] { "air_ticks", "ground_ticks", "inventory_use" });
        }
    }
    
    public static int a(final LivingEntity livingEntity, final PotionEffectType potionEffectType) {
        if (livingEntity.hasPotionEffect(potionEffectType)) {
            for (final PotionEffect potionEffect : livingEntity.getActivePotionEffects()) {
                if (potionEffect.getType().equals((Object)potionEffectType)) {
                    return potionEffect.getAmplifier();
                }
            }
        }
        return -1;
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final PotionEffectType potionEffectType) {
        if (spartanPlayer.hasPotionEffect(potionEffectType)) {
            for (final PotionEffect potionEffect : spartanPlayer.a()) {
                if (potionEffect.getType().equals((Object)potionEffectType)) {
                    return potionEffect.getAmplifier();
                }
            }
        }
        return -1;
    }
    
    public static boolean aS(final SpartanPlayer spartanPlayer) {
        final boolean b = Register.v1_9 && (spartanPlayer.isGliding() || Values.b(spartanPlayer.getEyeHeight(), 1) == 0.4) && ElytraGlide.bc(spartanPlayer);
        if (b) {
            CooldownUtils.d(spartanPlayer, "elytra", Math.max(ElytraGlide.bd(spartanPlayer) ? 120 : 60, CooldownUtils.a(spartanPlayer, "elytra")));
        }
        return b || !CooldownUtils.g(spartanPlayer, "elytra");
    }
    
    public static boolean aT(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "trident");
    }
    
    public static boolean aU(final SpartanPlayer spartanPlayer) {
        return Register.v1_13 && (spartanPlayer.getWorld().isThundering() || spartanPlayer.getWorld().hasStorm()) && spartanPlayer.getItemInHand().getType() == Material.TRIDENT;
    }
    
    public static boolean aV(final SpartanPlayer spartanPlayer) {
        final double eyeHeight = spartanPlayer.getEyeHeight();
        final boolean b = Liquid.e(spartanPlayer) <= 400L && ((Register.v1_13 && spartanPlayer.isSwimming()) || Values.b(eyeHeight, 1) == 0.4 || Math.round(eyeHeight * 10.0) == 4.0 || (!Register.v1_13 && (ViaVersion.a(spartanPlayer, ViaVersion.c) || ViaVersion.a(spartanPlayer, ViaVersion.b))));
        if (b) {
            CooldownUtils.d(spartanPlayer, "swimming", 10);
        }
        return b || !CooldownUtils.g(spartanPlayer, "swimming");
    }
    
    public static boolean aW(final SpartanPlayer spartanPlayer) {
        return aS(spartanPlayer) && ElytraGlide.bd(spartanPlayer);
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final long n = 5000L;
        final String string = spartanPlayer.getUniqueId().toString();
        final String string2 = spartanPlayer2.getUniqueId().toString();
        return (MillisUtils.a(spartanPlayer, "fight=delay=" + string2) <= n || MillisUtils.a(spartanPlayer2, "fight=delay=" + string) <= n) && AttemptUtils.a(spartanPlayer, "fight=hits=" + string2) + AttemptUtils.a(spartanPlayer2, "fight=hits=" + string) >= 5;
    }
    
    public static int c(final SpartanPlayer spartanPlayer, final double n) {
        int n2 = 0;
        final Entity vehicle = spartanPlayer.getVehicle();
        for (final Entity entity : spartanPlayer.getNearbyEntities(n, n, n)) {
            if (entity != null && entity != vehicle && (entity instanceof LivingEntity || entity instanceof Boat)) {
                ++n2;
            }
        }
        return n2;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Material material) {
        final ItemStack itemInHand = spartanPlayer.getItemInHand();
        if (itemInHand != null && itemInHand.getType() != null && itemInHand.getType() == material) {
            return true;
        }
        if (Register.v1_9) {
            final ItemStack itemInOffHand = spartanPlayer.a().getItemInOffHand();
            if (itemInOffHand != null && itemInOffHand.getType() != null && itemInOffHand.getType() == material) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean aX(final SpartanPlayer spartanPlayer) {
        final VersionUtils.a a = VersionUtils.a();
        if (a != VersionUtils.a.c && a != VersionUtils.a.l) {
            final WorldBorder worldBorder = spartanPlayer.getWorld().getWorldBorder();
            final SpartanLocation a2 = spartanPlayer.a();
            a2.setY(1.0);
            final SpartanLocation spartanLocation = new SpartanLocation(worldBorder.getCenter());
            final double abs = Math.abs(spartanLocation.getX() - a2.getX());
            final double abs2 = Math.abs(spartanLocation.getZ() - a2.getZ());
            final double a3 = a2.a(spartanLocation.b().b(abs, 0.0, abs2));
            final double a4 = a2.a(spartanLocation.b().b(-abs, 0.0, -abs2));
            final double a5 = a2.a(spartanLocation.b().b(-abs, 0.0, abs2));
            final double a6 = a2.a(spartanLocation.b().b(abs, 0.0, -abs2));
            final double size = worldBorder.getSize();
            return (a3 >= size && a4 > 1.0) || (a4 >= size && a3 > 1.0) || (a5 >= size && a6 > 1.0) || (a6 >= size && a5 > 1.0);
        }
        return false;
    }
    
    public static boolean aY(final SpartanPlayer spartanPlayer) {
        final VersionUtils.a a = VersionUtils.a();
        if (a != VersionUtils.a.c && a != VersionUtils.a.l) {
            final WorldBorder worldBorder = spartanPlayer.getWorld().getWorldBorder();
            final SpartanLocation a2 = spartanPlayer.a();
            a2.setY(1.0);
            final SpartanLocation spartanLocation = new SpartanLocation(worldBorder.getCenter());
            final double abs = Math.abs(spartanLocation.getX() - a2.getX());
            final double abs2 = Math.abs(spartanLocation.getZ() - a2.getZ());
            final double a3 = a2.a(spartanLocation.b().b(abs, 0.0, abs2));
            final double a4 = a2.a(spartanLocation.b().b(-abs, 0.0, -abs2));
            final double a5 = a2.a(spartanLocation.b().b(-abs, 0.0, abs2));
            final double a6 = a2.a(spartanLocation.b().b(abs, 0.0, -abs2));
            final double size = worldBorder.getSize();
            return (a3 >= size - 1.0 && a3 <= size + 1.0 && a4 > 1.0) || (a4 >= size - 1.0 && a4 <= size + 1.0 && a3 > 1.0) || (a5 >= size - 1.0 && a5 <= size + 1.0 && a6 > 1.0) || (a6 >= size - 1.0 && a6 <= size + 1.0 && a5 > 1.0);
        }
        return false;
    }
    
    public static boolean aZ(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.isDead() || spartanPlayer.getHealth() <= 0.0;
    }
    
    public static boolean ba(final SpartanPlayer spartanPlayer) {
        final boolean b = aZ(spartanPlayer) || Damage.e(spartanPlayer) > spartanPlayer.getHealth();
        if (b) {
            DeathAndRespawn.g(spartanPlayer, 10);
        }
        return b;
    }
    
    public static boolean bb(final SpartanPlayer spartanPlayer) {
        return Register.v1_9 && spartanPlayer.isInvulnerable();
    }
    
    public static boolean i(final SpartanPlayer spartanPlayer, final double n, final double d, final double n2) {
        if (NPC.D(spartanPlayer)) {
            return false;
        }
        final String string = spartanPlayer.getName() + "=" + n + "-" + d + "-" + n2;
        if (!CooldownUtils.g(spartanPlayer, string)) {
            return !CooldownUtils.g(spartanPlayer, string + "=true");
        }
        CooldownUtils.d(spartanPlayer, string, 1);
        if ((Math.abs(n) <= 0.3 && Math.abs(n2) <= 0.3 && d == 0.0 && spartanPlayer.isOnGround() && q(spartanPlayer) == 0) || c(spartanPlayer, spartanPlayer.a().b(n, d, n2), d)) {
            CooldownUtils.d(spartanPlayer, string + "=true", 1);
            return true;
        }
        return UtilEvents.be(spartanPlayer);
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n) {
        return GroundUtils.a(spartanPlayer, spartanLocation, n, true, true);
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final boolean b) {
        return c(spartanPlayer, spartanLocation, b ? 0.1 : 0.0);
    }
    
    public static boolean L(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return c(spartanPlayer, spartanLocation, 0.0);
    }
}
