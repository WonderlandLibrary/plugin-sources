package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.system.VL;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.h.Piston;
import me.vagdedes.spartan.c.CrackShot;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.Location;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class Sprint
{
    private static final Enums.HackType a;
    private static final HashMap<String, Location> B;
    private static final boolean g;
    
    public Sprint() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final String name = spartanPlayer.getName();
        Sprint.B.remove(name + "=backwards");
        Sprint.B.remove(name + "=sideways=1");
        Sprint.B.remove(name + "=sideways=2");
        Sprint.B.remove(name + "=diagonal=1");
        Sprint.B.remove(name + "=diagonal=2");
    }
    
    public static void clear() {
        Sprint.B.clear();
    }
    
    public static void a(final Player player, final Location location, final SpartanLocation spartanLocation, final double n) {
        if (a(player)) {
            final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId(), 1);
            a(player, a, spartanLocation);
            a(player, a, location, spartanLocation, n);
        }
    }
    
    private static void a(final Player player, final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        if (!Checks.getBoolean("Sprint.check_food_sprinting") || TPS.u()) {
            return;
        }
        final boolean ak = PlayerData.aK(spartanPlayer);
        if (!player.isSprinting() && ak) {
            AttemptUtils.a(spartanPlayer, Sprint.a.toString() + "=sprint", 1);
        }
        else {
            AttemptUtils.m(spartanPlayer, Sprint.a.toString() + "=sprint");
        }
        int i = 0;
        if (player.isSprinting()) {
            i = 1;
        }
        else if (ak && AttemptUtils.a(spartanPlayer, Sprint.a.toString() + "=sprint") >= 10) {
            i = 2;
        }
        else if (PlayerData.aM(spartanPlayer)) {
            i = 3;
        }
        if (player.getFoodLevel() <= 6 && i != 0 && AttemptUtils.b(spartanPlayer, Sprint.a.toString() + "=food=level", 15) >= 10) {
            EventsHandler1.a(spartanPlayer, Sprint.a, spartanLocation, "t: food-level, c: " + i, false, true);
        }
    }
    
    private static void a(final Player player, final SpartanPlayer spartanPlayer, final Location location, final SpartanLocation spartanLocation, final double n) {
        if (Damage.E(spartanPlayer) || Explosion.E(spartanPlayer) || NoHitDelay.E(spartanPlayer) || !Checks.getBoolean("Sprint.check_omnidirectional_sprinting") || CrackShot.A(spartanPlayer) || PlayerData.b(spartanPlayer) != 0.0f || Piston.E(spartanPlayer)) {
            return;
        }
        final Location location2 = player.getLocation();
        location2.setPitch(0.0f);
        final float yaw = location2.getYaw();
        final String name = player.getName();
        final double n2 = 0.06;
        int i = 0;
        final String string = Sprint.a.toString() + "=omnidirectional=";
        final boolean b = !BlockUtils.i(spartanPlayer, true, 0.298, 0.0, 0.298);
        if (MoveUtils.b(n) && (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || !player.isOnGround())) {
            CooldownUtils.d(spartanPlayer, string + "jumping", 20);
            if (!Sprint.g && !Building.E(spartanPlayer)) {
                i = 1;
            }
        }
        else if (CooldownUtils.g(spartanPlayer, string + "jumping")) {
            i = 2;
        }
        final boolean b2 = i == 1;
        final double a = MoveUtils.a((LivingEntity)player, b ? 0.57 : (b2 ? 0.35 : 0.29), 4.0, PotionEffectType.SPEED);
        if (i != 0 && (Sprint.B.containsKey(name + "=backwards") || Sprint.B.containsKey(name + "=sideways=1") || Sprint.B.containsKey(name + "=sideways=2") || Sprint.B.containsKey(name + "=diagonal=1") || Sprint.B.containsKey(name + "=diagonal=2"))) {
            final double a2 = MoveUtils.a(Sprint.B.get(name + "=backwards"), location2);
            final double a3 = MoveUtils.a(Sprint.B.get(name + "=sideways=1"), location2);
            final double a4 = MoveUtils.a(Sprint.B.get(name + "=sideways=2"), location2);
            final double a5 = MoveUtils.a(Sprint.B.get(name + "=diagonal=1"), location2);
            final double a6 = MoveUtils.a(Sprint.B.get(name + "=diagonal=2"), location2);
            final String str = (a2 <= n2) ? "backwards" : ((a3 <= n2 || a4 <= n2) ? "sideways" : ((a5 <= n2 || a6 <= n2) ? "diagonal" : null));
            if (str != null && a > 0.0 && AttemptUtils.b(spartanPlayer, string + i, 20) == (b2 ? 1 : 12)) {
                EventsHandler1.a(spartanPlayer, Sprint.a, spartanLocation, "t: omnidirectional, d: " + str + ", l: " + a, false, true);
            }
        }
        Sprint.B.put(name + "=backwards", location2.clone().add(location2.getDirection().multiply(-a)));
        final Location clone = location2.clone();
        final float n3 = yaw + 90.0f;
        clone.setYaw((n3 <= 360.0f) ? n3 : (n3 - 360.0f));
        Sprint.B.put(name + "=sideways=1", clone.add(clone.getDirection().multiply(a)));
        final Location clone2 = location2.clone();
        final float n4 = yaw - 90.0f;
        clone2.setYaw((n4 >= 0.0f) ? n4 : (n4 + 360.0f));
        Sprint.B.put(name + "=sideways=2", clone2.add(clone2.getDirection().multiply(a)));
        final Location clone3 = location2.clone();
        final float n5 = yaw + 135.0f;
        clone3.setYaw((n5 <= 360.0f) ? n5 : (n5 - 360.0f));
        Sprint.B.put(name + "=diagonal=1", clone3.add(clone3.getDirection().multiply(a)));
        final Location clone4 = location2.clone();
        final float n6 = yaw - 135.0f;
        clone4.setYaw((n6 >= 0.0f) ? n6 : (n6 + 360.0f));
        Sprint.B.put(name + "=diagonal=2", clone4.add(clone4.getDirection().multiply(a)));
    }
    
    private static boolean a(final Player player) {
        final SpartanPlayer spartanPlayer = new SpartanPlayer(player);
        return !VL.b(spartanPlayer, Sprint.a, true) && !Teleport.E(spartanPlayer) && !PlayerData.b(spartanPlayer, true) && !spartanPlayer.getAllowFlight() && !LatencyUtils.e(spartanPlayer, 1000);
    }
    
    static {
        a = Enums.HackType.Sprint;
        B = new HashMap<String, Location>();
        g = (Register.v1_9 && VersionUtils.a() != VersionUtils.a.e);
    }
}
