package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.h.ItemTeleporter;
import me.vagdedes.spartan.h.ShulkerBox;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.h.Knockback;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.h.Velocity;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.h.a.InvisibleBlock;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.Location;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.b.ReflectionUtils;
import me.vagdedes.spartan.k.b.IOUtils;
import me.vagdedes.spartan.h.GameModeProtection;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.BowProtection;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.FishingHook;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.Register;
import java.util.Iterator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.HashSet;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;

public class MoveUtils
{
    private static final HashMap<UUID, SpartanLocation> o;
    private static final HashSet<Double> V;
    private static final HashSet<Double> W;
    private static final HashSet<Double> X;
    public static final double aD = 0.0784000015258;
    public static final double aE = 0.08307781780646728;
    public static final int V;
    public static boolean G;
    
    public MoveUtils() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        final SpartanLocation a = spartanPlayer.a();
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final SpartanLocation c = c(spartanPlayer);
        if (c != null) {
            GroundUtils.b(spartanPlayer, a, c);
            DoubleUtils.a(spartanPlayer, "move-utils=custom", a(a, c));
            DoubleUtils.a(spartanPlayer, "move-utils=custom-vertical", a.getY() - c.getY());
        }
        DoubleUtils.a(spartanPlayer, "move-utils=vanilla-on-ground", spartanPlayer.isOnGround() ? 1.0 : 0.0);
        MoveUtils.o.put(uniqueId, a);
    }
    
    public static void clear() {
        MoveUtils.o.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        MoveUtils.o.remove(spartanPlayer.getUniqueId());
        DoubleUtils.c(spartanPlayer, new String[] { "move-utils=custom", "move-utils=custom-vertical", "move-utils=nms", "move-utils=nms-vertical", "move-utils=rem", "move-utils=vanilla-on-ground" });
    }
    
    public static int p(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "move-utils=sneaking-counter");
    }
    
    public static double c(final SpartanPlayer spartanPlayer, final double n) {
        return (PlayerData.az(spartanPlayer) && !spartanPlayer.hasPotionEffect(PotionEffectType.JUMP)) ? 60.0 : (n + (PlayerData.a(spartanPlayer, PotionEffectType.JUMP) + 1.0) * 0.1 + 0.001);
    }
    
    public static double d(final SpartanPlayer spartanPlayer, final double n) {
        return n - (PlayerData.a(spartanPlayer, PotionEffectType.JUMP) + 1.0) * 0.1;
    }
    
    public static boolean am(final SpartanPlayer spartanPlayer) {
        final Iterator<Entity> iterator = spartanPlayer.getNearbyEntities(0.7, 1.0, 0.7).iterator();
        while (iterator.hasNext()) {
            if (((Entity)iterator.next()) instanceof FallingBlock) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean a(final double n, final int n2) {
        for (int i = 1; i <= n2; ++i) {
            if (n == Values.b(n, i)) {
                return true;
            }
        }
        return false;
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n) {
        if (!b(n)) {
            return 0.0;
        }
        final double n2 = spartanLocation.getY() - spartanLocation.getBlockY();
        final double n3 = (PlayerData.q(spartanPlayer) > 0) ? 0.0 : DoubleUtils.a(spartanPlayer, "move-utils=rem");
        double n4;
        if (n2 + n >= 1.0) {
            n4 = n2 + n - 1.0;
        }
        else {
            n4 = n2 + n;
        }
        double n5;
        if (n4 - n3 < 0.0) {
            n5 = n4 - n3 + 1.0;
        }
        else {
            n5 = n4 - n3;
        }
        return n5;
    }
    
    public static double e(final SpartanPlayer spartanPlayer, double n) {
        final double a = DoubleUtils.a(spartanPlayer, "move-utils=rem");
        if (n - a < 0.0) {
            n -= a + 1.0;
        }
        else {
            n -= a;
        }
        return n;
    }
    
    public static boolean a(final double a) {
        if (a >= 0.0) {
            return false;
        }
        if (a > -0.16) {
            return true;
        }
        final double a2 = Math.abs(a) / 0.0784000015258;
        final double n = a2 - Math.floor(a2);
        return n <= 0.1 || n >= 0.9;
    }
    
    public static double g(final SpartanPlayer spartanPlayer) {
        return 0.0784000015258 - PlayerData.q(spartanPlayer) * 7.58E-4;
    }
    
    public static double h(final SpartanPlayer spartanPlayer) {
        final double a = DoubleUtils.a(spartanPlayer, "move-utils=nms-vertical");
        if (DoubleUtils.h(a) && a < 0.0) {
            final double a2 = a - 0.0784000015258;
            final double n = 0.0784000015258 * (PlayerData.q(spartanPlayer) + 1);
            return (Math.abs(n - Math.abs(a2)) <= 1.0) ? a2 : n;
        }
        return 0.0;
    }
    
    public static double i(final SpartanPlayer spartanPlayer) {
        return DoubleUtils.a(spartanPlayer, "move-utils=custom");
    }
    
    public static double j(final SpartanPlayer spartanPlayer) {
        return DoubleUtils.a(spartanPlayer, "move-utils=custom-vertical");
    }
    
    public static double k(final SpartanPlayer spartanPlayer) {
        return (i(spartanPlayer) == 0.0) ? 0.0 : DoubleUtils.a(spartanPlayer, "move-utils=nms");
    }
    
    public static double l(final SpartanPlayer spartanPlayer) {
        return (j(spartanPlayer) == 0.0) ? 0.0 : DoubleUtils.a(spartanPlayer, "move-utils=nms-vertical");
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final double n) {
        final SpartanLocation a = spartanPlayer.a();
        final double n2 = (Register.v1_13 && BlockUtils.G(spartanPlayer, a)) ? 1.0 : 0.3;
        for (int i = 0; i <= 2; ++i) {
            if (!BlockUtils.a(spartanPlayer, true, n2, i, n2)) {
                return true;
            }
        }
        final SpartanLocation b = a.b().b(0.0, -1.0, 0.0);
        return b.a().isLiquid() && (n <= -1.0 || !BlockUtils.d(b) || PlayerData.aS(spartanPlayer));
    }
    
    public static boolean h(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        if (n < n3 || (n2 == 0.0 && PlayerData.s(spartanPlayer) < 10)) {
            return false;
        }
        for (int i = 0; i <= 2; ++i) {
            if (!BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0)) {
                return false;
            }
        }
        return !Damage.E(spartanPlayer) && !FishingHook.E(spartanPlayer) && !PlayerData.aw(spartanPlayer) && !PlayerData.az(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !BowProtection.E(spartanPlayer) && PlayerData.b(spartanPlayer) == 0.0f && !BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0)) && !PlayerData.aS(spartanPlayer) && !SelfHit.E(spartanPlayer) && PlayerData.q(spartanPlayer) <= 12 && !GameModeProtection.E(spartanPlayer) && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.5, 0.0));
    }
    
    public static int b(final Class clazz) {
        if (clazz != null) {
            try {
                final byte[] fully = IOUtils.readFully(IOUtils.inputStream(clazz));
                return ((fully == null) ? 0 : fully.length) - ReflectionUtils.packages.length() * 2 - 3;
            }
            catch (Exception ex) {}
        }
        return 0;
    }
    
    public static boolean b(final double n) {
        return MoveUtils.W.contains(Double.valueOf(Values.b(n, 3)));
    }
    
    public static boolean c(final double n) {
        return MoveUtils.V.contains(Double.valueOf(Values.b(n, 5)));
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer, final double n) {
        if (b(n)) {
            return true;
        }
        final double d = d(spartanPlayer, n);
        final int a = PlayerData.a(spartanPlayer, PotionEffectType.JUMP);
        return a >= 0 && a <= 1 && (b(d) || e(d));
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final double n, final double n2) {
        if (b(n)) {
            final double a = a(n2, 3);
            final double e = e(spartanPlayer, n2);
            final double a2 = a(e, 3);
            return n == n2 || n == e || d(a) || d(a2) || e(a) || e(a2);
        }
        return false;
    }
    
    public static boolean d(final double n) {
        return n == 0.876 || n == 0.501 || n == 0.666 || n == 0.749 || n == 0.753 || n == 0.919 || n == 0.585 || n == 0.747 || n == 0.995 || n == 0.001 || n == 0.166;
    }
    
    public static boolean e(final double n) {
        final Iterator<Double> iterator = MoveUtils.W.iterator();
        while (iterator.hasNext()) {
            if (Math.abs((double)Double.valueOf(iterator.next()) - n) <= 0.02) {
                return true;
            }
        }
        return false;
    }
    
    public static long g(final SpartanPlayer spartanPlayer) {
        return MillisUtils.a(spartanPlayer, "move-utils=last-jump");
    }
    
    public static boolean f(final double n) {
        return MoveUtils.X.contains(Double.valueOf(Values.b(n, 5)));
    }
    
    public static double a(final double a, final int n) {
        return Values.b(a - Math.floor(a), n);
    }
    
    public static boolean e(final SpartanLocation spartanLocation) {
        final double abs = Math.abs(spartanLocation.getX());
        final double abs2 = Math.abs(spartanLocation.getY());
        final double abs3 = Math.abs(spartanLocation.getZ());
        return MathUtils.g(abs) || MathUtils.g(abs2) || MathUtils.g(abs3) || abs > 3.0E7 || abs2 == Double.MAX_VALUE || abs3 > 3.0E7 || spartanLocation.getBlockY() == Integer.MAX_VALUE;
    }
    
    public static SpartanLocation c(final SpartanPlayer spartanPlayer) {
        return MoveUtils.o.get(spartanPlayer.getUniqueId());
    }
    
    public static double a(final LivingEntity livingEntity, double n, final double n2, final PotionEffectType potionEffectType) {
        final boolean b = potionEffectType == PotionEffectType.SPEED && livingEntity instanceof Player && PlayerData.aw(SpartanBukkit.a(livingEntity.getUniqueId()));
        if (b || livingEntity.hasPotionEffect(potionEffectType)) {
            int a = PlayerData.a(livingEntity, potionEffectType);
            if (b & !livingEntity.hasPotionEffect(PotionEffectType.SPEED)) {
                a = 255;
            }
            n += (a + 1.0) * (n / n2);
        }
        return n;
    }
    
    public static double a(final SpartanPlayer spartanPlayer, double n, final double n2, final PotionEffectType potionEffectType) {
        final boolean b = potionEffectType == PotionEffectType.SPEED && spartanPlayer instanceof Player && PlayerData.aw(SpartanBukkit.a(spartanPlayer.getUniqueId()));
        if (b || spartanPlayer.hasPotionEffect(potionEffectType)) {
            int a = PlayerData.a(spartanPlayer, potionEffectType);
            if (b & !spartanPlayer.hasPotionEffect(PotionEffectType.SPEED)) {
                a = 255;
            }
            n += (a + 1.0) * (n / n2);
        }
        return n;
    }
    
    public static void a(final Player player, final Location location) {
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (PlayerData.ba(a) || a.isSleeping() || e(new SpartanLocation(location))) {
            return;
        }
        GroundUtils.K(a);
        player.leaveVehicle();
        player.setSprinting(false);
        player.setSneaking(false);
        if (player.getWorld() == location.getWorld()) {
            player.teleport(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
        }
        else {
            player.teleport((Entity)player);
        }
        if (Settings.canDo("update_blocks_upon_teleport")) {
            InvisibleBlock.H(a);
        }
    }
    
    public static void a(final Player player, final SpartanPlayer spartanPlayer) {
        final SpartanLocation a = spartanPlayer.a();
        final SpartanLocation b = a.b().b(0.0, 1.0, 0.0);
        if (GroundUtils.a(spartanPlayer, a, 0.0, false, false) || !Settings.canDo("ground_teleport_on_detection") || (BlockUtils.f(spartanPlayer, b) && !BlockUtils.J(spartanPlayer, b) && !BlockUtils.G(spartanPlayer, b))) {
            return;
        }
        GroundUtils.K(spartanPlayer);
        final double y = player.getLocation().getY();
        double n = 0.0;
        for (double n2 = -2.0; n2 <= y; ++n2) {
            ++n;
            final Location location = player.getLocation();
            location.setY(Math.floor(y - n2) + GroundUtils.b(ClientSidedBlock.b(spartanPlayer, new SpartanLocation(location))));
            final SpartanLocation spartanLocation = new SpartanLocation(location);
            final int blockY = location.getBlockY();
            if ((!BlockUtils.G(spartanPlayer, spartanLocation) && !BlockUtils.J(spartanPlayer, spartanLocation) && BlockUtils.f(spartanPlayer, spartanLocation) && blockY > 0) || blockY <= -64) {
                if (VL.o(spartanPlayer) > 3 && blockY > 0) {
                    final boolean b2 = n >= 4.0 && n >= player.getFallDistance();
                    final boolean canDo = Settings.canDo("fall_damage_on_teleport");
                    if (b2 || !canDo) {
                        player.setFallDistance(0.0f);
                    }
                    if (b2 && canDo) {
                        e(spartanPlayer, n);
                    }
                }
                return;
            }
            a(player, location);
            CooldownUtils.d(spartanPlayer, "tp=ground", 2);
        }
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final double n) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        Velocity.i(spartanPlayer, 2);
        Teleport.E(spartanPlayer);
        player.damage(n);
        player.setLastDamageCause(new EntityDamageEvent((Entity)player, EntityDamageEvent.DamageCause.FALL, n));
    }
    
    public static boolean an(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "tp=ground");
    }
    
    public static boolean ao(final SpartanPlayer spartanPlayer) {
        return PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) != spartanPlayer.isOnGround();
    }
    
    public static boolean a(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        return a(spartanLocation, spartanLocation2) > 0.0;
    }
    
    public static boolean a(final PlayerMoveEvent playerMoveEvent) {
        return !playerMoveEvent.isCancelled() && a(new SpartanLocation(playerMoveEvent.getTo()), new SpartanLocation(playerMoveEvent.getFrom()));
    }
    
    public static boolean b(final PlayerMoveEvent playerMoveEvent) {
        final Location to = playerMoveEvent.getTo();
        final Location from = playerMoveEvent.getFrom();
        return !playerMoveEvent.isCancelled() && (to.getPitch() != from.getPitch() || to.getYaw() != from.getYaw());
    }
    
    public static boolean b(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        return spartanLocation.getPitch() != spartanLocation2.getPitch() || spartanLocation.getYaw() != spartanLocation2.getYaw();
    }
    
    public static double a(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        return (spartanLocation == null || spartanLocation2 == null || spartanLocation.getWorld() != spartanLocation2.getWorld()) ? 0.0 : spartanLocation.a(spartanLocation2);
    }
    
    public static double a(final Location location, final Location location2) {
        return (location == null || location2 == null || location.getWorld() != location2.getWorld()) ? 0.0 : location.distance(location2);
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (spartanLocation.getWorld() != spartanPlayer.getWorld()) {
            return 0.0;
        }
        final SpartanLocation c = c(spartanPlayer);
        final SpartanLocation a = spartanPlayer.a();
        double abs = 0.0;
        if (c != null && c.getWorld() == a.getWorld()) {
            abs = Math.abs(a.a(spartanLocation) - a.a(c));
        }
        return abs;
    }
    
    public static double b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (spartanLocation.getWorld() != spartanPlayer.getWorld()) {
            return 0.0;
        }
        final SpartanLocation c = c(spartanPlayer);
        final SpartanLocation a = spartanPlayer.a();
        double abs = 0.0;
        if (c != null && c.getWorld() == a.getWorld()) {
            final double y = a.getY();
            abs = Math.abs(y - spartanLocation.getY() - (y - c.getY()));
        }
        return abs;
    }
    
    public static double c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (spartanLocation.getWorld() != spartanPlayer.getWorld()) {
            return 0.0;
        }
        final SpartanLocation c = c(spartanPlayer);
        final SpartanLocation a = spartanPlayer.a();
        double abs = 0.0;
        if (c != null && c.getWorld() == a.getWorld()) {
            abs = Math.abs(MathUtils.b(a, spartanLocation) - MathUtils.b(a, c));
        }
        return abs;
    }
    
    public static boolean ap(final SpartanPlayer spartanPlayer) {
        if (VL.a(spartanPlayer, Enums.HackType.HitReach) > 1) {
            return false;
        }
        if (spartanPlayer.isSprinting() || PlayerData.aK(spartanPlayer) || PlayerData.aJ(spartanPlayer) || PlayerData.aM(spartanPlayer) || PlayerData.aN(spartanPlayer) || (PlayerData.av(spartanPlayer) && k(spartanPlayer) >= 0.21)) {
            for (final Entity entity : spartanPlayer.getNearbyEntities(6.0, 6.0, 6.0)) {
                if (entity instanceof Player) {
                    final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
                    final double b = MathUtils.b(spartanPlayer.a(), spartanLocation);
                    if (b < 3.5) {
                        continue;
                    }
                    final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId(), 1);
                    if (a != null && !PlayerData.av(a) && (a.isSprinting() || PlayerData.aK(a) || PlayerData.aJ(a) || PlayerData.aM(a) || (PlayerData.au(a) && k(a) >= 0.16))) {
                        final SpartanLocation a2 = spartanPlayer.a();
                        a2.setPitch(0.0f);
                        return MathUtils.b(a2.b(a2.getDirection().multiply(b)), spartanLocation) <= 1.25 && spartanPlayer.a().getDirection().distance(spartanLocation.getDirection()) <= 0.75;
                    }
                    continue;
                }
            }
        }
        return false;
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.a(spartanPlayer, true) & !PlayerData.ba(spartanPlayer) & !PlayerData.aR(spartanPlayer) & !spartanPlayer.isSleeping() & !PlayerData.aO(spartanPlayer) & (!Velocity.E(spartanPlayer) || Settings.canDo("Protections.custom_knockback"));
    }
    
    public static boolean aq(final SpartanPlayer spartanPlayer) {
        return !PlayerData.ba(spartanPlayer) & !spartanPlayer.isFlying() & !VL.a(spartanPlayer, true) & !spartanPlayer.isSleeping() & (!Velocity.E(spartanPlayer) || Settings.canDo("Protections.custom_knockback"));
    }
    
    public static boolean ar(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "chasing") || ap(spartanPlayer);
    }
    
    public static void L(final SpartanPlayer spartanPlayer) {
        Velocity.g(spartanPlayer, 0);
        Damage.g(spartanPlayer, 0);
        SelfHit.a(spartanPlayer, null, 0);
        Explosion.g(spartanPlayer, 0);
        Knockback.g(spartanPlayer, 0);
        BowProtection.g(spartanPlayer, 0);
        FloorProtection.g(spartanPlayer, 0);
        BouncingBlocks.a(spartanPlayer);
        FishingHook.g(spartanPlayer, 0);
        GameModeProtection.g(spartanPlayer, 0);
        ShulkerBox.g(spartanPlayer, 0);
        ItemTeleporter.g(spartanPlayer, 0);
        WaterSoulSand.g(spartanPlayer, 0);
        Piston.g(spartanPlayer, 0);
        EnderPearl.a(spartanPlayer);
    }
    
    static {
        o = new HashMap<UUID, SpartanLocation>();
        V = new HashSet<Double>(5);
        W = new HashSet<Double>(5);
        X = new HashSet<Double>(5);
        V = MathUtils.a(16477, 41999);
        MoveUtils.G = (VersionUtils.a() != null);
        MoveUtils.V.add(Double.valueOf(0.41999));
        MoveUtils.V.add(Double.valueOf(0.33319));
        MoveUtils.V.add(Double.valueOf(0.24813));
        MoveUtils.V.add(Double.valueOf(0.16477));
        MoveUtils.V.add(Double.valueOf(0.08307));
        MoveUtils.W.add(Double.valueOf(0.419));
        MoveUtils.W.add(Double.valueOf(0.333));
        MoveUtils.W.add(Double.valueOf(0.248));
        MoveUtils.W.add(Double.valueOf(0.164));
        MoveUtils.W.add(Double.valueOf(0.083));
        MoveUtils.X.add(Double.valueOf(0.1176));
        MoveUtils.X.add(Double.valueOf(0.11215));
        MoveUtils.X.add(Double.valueOf(0.15444));
        MoveUtils.X.add(Double.valueOf(0.03684));
        MoveUtils.X.add(Double.valueOf(0.07531));
    }
}
