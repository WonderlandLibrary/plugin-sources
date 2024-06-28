package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.d.MathUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Boat;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.c.CraftBook;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Entity;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class BoatMove
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, Entity> v;
    private static final double j = 3.5;
    private static final double k = 0.415;
    private static final double l = 0.35;
    private static final double m = 0.5;
    private static final double n = 0.001;
    private static final double o = 1.5;
    private static final double p = 2.1;
    private static final double q = 6.0;
    private static final double r = 6.0;
    
    public BoatMove() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        BoatMove.v.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        BoatMove.v.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, double d, final double n2, final double n3) {
        if (!b(spartanPlayer)) {
            BoatMove.v.remove(spartanPlayer.getUniqueId());
            a(spartanPlayer, "", -2);
            AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=ticks");
            return;
        }
        final int a = AttemptUtils.a(spartanPlayer, BoatMove.a.toString() + "=ticks", 1);
        if ((a <= 10 && VL.a(spartanPlayer, BoatMove.a) <= DefaultConfiguration.f(BoatMove.a)) || MoveUtils.b(n)) {
            return;
        }
        double n4 = 1.5;
        final Entity vehicle = spartanPlayer.getVehicle();
        final double a2 = MoveUtils.a(spartanPlayer, spartanLocation2);
        final boolean b = Register.v1_13 && Checks.getBoolean("BoatMove.compatibility_protection");
        d = ((VersionUtils.a() == VersionUtils.a.i && b) ? (d - n2) : d);
        double d2;
        String s;
        if (BlockUtils.j(spartanPlayer, spartanPlayer.a())) {
            d2 = (b ? 6.0 : 3.5);
            s = "ice";
            a(spartanPlayer, "ice", -20);
            AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=air-ticks");
        }
        else if (spartanPlayer.a().a().getType() == MaterialUtils.a("water")) {
            d2 = (b ? 6.0 : 0.415);
            s = "water";
            a(spartanPlayer, "water", -5);
            AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=air-ticks");
        }
        else if ((BlockUtils.f(spartanPlayer, spartanPlayer.a()) || BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0))) && BlockUtils.a(spartanPlayer, true, 1.0, 0.0, 1.0)) {
            d2 = (b ? 6.0 : 0.35);
            s = "ground";
            a(spartanPlayer, "ground", -1);
            AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=air-ticks");
        }
        else {
            if (AttemptUtils.a(spartanPlayer, BoatMove.a.toString() + "=air-ticks", 1) >= 40) {
                d2 = 0.001;
            }
            else {
                d2 = 0.5;
            }
            s = "air";
            a(spartanPlayer, "air", -1);
        }
        if (CraftBook.d() > d2) {
            d2 = CraftBook.d();
        }
        if (d2 == 3.5) {
            n4 = 6.0;
        }
        else if (a >= 20) {
            n4 = 2.1;
        }
        final double n5 = 1.25;
        final double d3 = (b ? Math.max(d2, 6.0) : d2) * Math.max(1.0, LagManagement.m(spartanPlayer) / n5);
        final double d4 = n4 * Math.max(1.0, LagManagement.m(spartanPlayer) / n5);
        if (Checks.getBoolean("BoatMove.check_packets") && a2 >= d4 && !TPS.u() && !LatencyUtils.e(spartanPlayer, 200) && BoatMove.v.containsKey(spartanPlayer.getUniqueId()) && BoatMove.v.get(spartanPlayer.getUniqueId()) == vehicle) {
            a(spartanPlayer, BoatMove.a, spartanLocation2, "t: " + s + "(packets), ds: " + a2 + ", dm: " + d4 + ", bt: " + a, false);
        }
        if (Checks.getBoolean("BoatMove.check_horizontal") && d3 > 0.0 && d >= d3 && AttemptUtils.b(spartanPlayer, BoatMove.a.toString() + "=horizontal", 20) == 3) {
            AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=horizontal");
            a(spartanPlayer, BoatMove.a, spartanLocation, "t: " + s + "(horizontal), ds: " + d + ", dm: " + d3 + ", bt: " + a, false);
        }
        Label_1244: {
            if (BlockUtils.c(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.c(spartanPlayer, true, 1.0, -1.0, 1.0) && BlockUtils.a(spartanPlayer, true, 1.0, 0.0, 1.0) && BlockUtils.a(spartanPlayer, true, 1.0, -1.0, 1.0) && !s.equals("water") && (a >= 40 || n != 0.0)) {
                final double d5 = n3 - n;
                if (Checks.getBoolean("BoatMove.check_stable") && Math.abs(d5) <= 0.01 && n >= -0.1 && AttemptUtils.b(spartanPlayer, BoatMove.a.toString() + "=stable", 20) == 3) {
                    AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=stable");
                    a(spartanPlayer, BoatMove.a, spartanLocation2, "t: " + s + "(stable), ds: " + d5 + ", dy: " + n + ", bt: " + a, false);
                }
                if (Checks.getBoolean("BoatMove.check_vertical")) {
                    if (VL.a(spartanPlayer, BoatMove.a) >= 2) {
                        if (n <= 0.0) {
                            break Label_1244;
                        }
                    }
                    else if (n < 0.001) {
                        break Label_1244;
                    }
                    if (AttemptUtils.b(spartanPlayer, BoatMove.a.toString() + "=vertical", 20) == 3) {
                        AttemptUtils.m(spartanPlayer, BoatMove.a.toString() + "=vertical");
                        a(spartanPlayer, BoatMove.a, spartanLocation2, "t: " + s + "(vertical), dy: " + n + ", distance: " + d5 + ", bt: " + a, false);
                    }
                }
            }
        }
        BoatMove.v.put(spartanPlayer.getUniqueId(), vehicle);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String s, final int n) {
        if (!s.equals("ice")) {
            AttemptUtils.c(spartanPlayer, BoatMove.a.toString() + "=ice", n);
        }
        if (!s.equals("water")) {
            AttemptUtils.c(spartanPlayer, BoatMove.a.toString() + "=water", n);
        }
        if (!s.equals("ground")) {
            AttemptUtils.c(spartanPlayer, BoatMove.a.toString() + "=ground", n);
        }
        if (!s.equals("air")) {
            AttemptUtils.c(spartanPlayer, BoatMove.a.toString() + "=air", n);
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        final Entity vehicle = spartanPlayer.getVehicle();
        if (vehicle == null || !(vehicle instanceof Boat)) {
            return false;
        }
        if (Register.v1_13 && vehicle.getPassengers().size() >= 2) {
            return false;
        }
        for (final Entity entity : spartanPlayer.getNearbyEntities(0.25, 0.0, 0.25)) {
            if (entity instanceof Player && !NPC.is((Player)entity) && !entity.getUniqueId().equals(spartanPlayer.getUniqueId()) && !entity.getUniqueId().equals(vehicle.getUniqueId())) {
                return false;
            }
        }
        final SpartanLocation b = spartanPlayer.a().b(0.0, 1.0, 0.0);
        return !VL.b(spartanPlayer, BoatMove.a, true) && !Teleport.E(spartanPlayer) && !Explosion.E(spartanPlayer) && !BlockUtils.a(spartanPlayer, b, Material.SLIME_BLOCK, 1.0) && !BlockUtils.a(spartanPlayer, b, MaterialUtils.a("piston"), 1.0) && !BlockUtils.a(spartanPlayer, b, MaterialUtils.a("sticky_piston"), 1.0) && !BlockUtils.a(spartanPlayer, b, MaterialUtils.a("piston_extension"), 1.0) && !BlockUtils.a(spartanPlayer, b, MaterialUtils.a("piston_moving"), 1.0);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final SpartanLocation spartanLocation, final String s, final boolean b) {
        boolean b2 = true;
        if (s != null) {
            for (final String s2 : s.split(" ")) {
                if (MathUtils.validDouble(s2) && Double.valueOf(s2) > 16.0) {
                    b2 = false;
                    break;
                }
            }
        }
        final boolean b3 = VL.a(spartanPlayer, BoatMove.a) > 3 || s.contains("(packets)");
        new HackPrevention(spartanPlayer, hackType, s, b3 ? spartanLocation : null, b3 ? 20 : 0, b3 && b, 0.0, b2);
    }
    
    static {
        a = Enums.HackType.BoatMove;
        v = new HashMap<UUID, Entity>();
    }
}
