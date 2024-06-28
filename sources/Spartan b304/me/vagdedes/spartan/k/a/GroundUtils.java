package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.k.g.DoubleUtils;
import org.bukkit.entity.Boat;
import me.vagdedes.spartan.Register;
import java.util.Collection;
import java.util.ArrayList;
import org.bukkit.Material;
import java.util.Iterator;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Entity;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;

public class GroundUtils
{
    private static final HashMap<UUID, List<Entity>> aa;
    private static final double aB = 2.0;
    private static final double aC = 0.7;
    
    public GroundUtils() {
        super();
    }
    
    public static void clear() {
        GroundUtils.aa.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        GroundUtils.aa.remove(spartanPlayer.getUniqueId());
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        spartanPlayer.a(2.0);
        GroundUtils.aa.put(spartanPlayer.getUniqueId(), a(spartanPlayer, spartanLocation.getY() - spartanLocation2.getY()));
        if (spartanLocation.getBlockX() == spartanLocation2.getBlockX() && spartanLocation.getBlockZ() == spartanLocation2.getBlockZ() && spartanLocation.getBlockY() + 0.5 >= spartanLocation2.getBlockY() && (!spartanPlayer.isOnGround() || !a(spartanPlayer, spartanLocation, 0.0, true, true)) && !BlockUtils.G(spartanPlayer, spartanLocation) && !BlockUtils.G(spartanPlayer, spartanLocation2) && MoveUtils.l(spartanPlayer) > 0.083 && !Building.E(spartanPlayer)) {
            if (!PunishUtils.bh(spartanPlayer)) {
                AttemptUtils.a(spartanPlayer, "ground-utils=utility-inaccuracy", 1);
            }
        }
        else {
            AttemptUtils.m(spartanPlayer, "ground-utils=utility-inaccuracy");
        }
    }
    
    public static void K(final SpartanPlayer spartanPlayer) {
        AttemptUtils.m(spartanPlayer, "ground-utils=utility-inaccuracy");
    }
    
    public static void j(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "ground-utils=set-on-ground", n);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final boolean b, final boolean b2) {
        final boolean b3 = spartanPlayer != null;
        final boolean b4 = b3 && n == 0.0;
        final double n2 = spartanLocation.getY() - spartanLocation.getBlockY();
        if (b3) {
            if (!CooldownUtils.g(spartanPlayer, "ground-utils=set-on-ground")) {
                if (b4) {
                    PlayerData.N(spartanPlayer);
                }
                return true;
            }
            if (n2 != 0.5 && al(spartanPlayer)) {
                return false;
            }
        }
        final int n3 = b3 ? PlayerData.q(spartanPlayer) : Integer.MAX_VALUE;
        if ((b3 && (ak(spartanPlayer) || a(spartanPlayer, n3, n))) || b(spartanPlayer, spartanLocation, n2)) {
            if (b4) {
                PlayerData.N(spartanPlayer);
            }
            return true;
        }
        final Iterator<SpartanLocation> iterator = a(spartanLocation, b3 ? Math.max(MoveUtils.h(spartanPlayer), 0.0784000015258) : 0.0784000015258, n2).iterator();
        while (iterator.hasNext()) {
            final Material b5 = ClientSidedBlock.b(spartanPlayer, iterator.next());
            if ((b && BlockUtils.g(b5)) || (b2 && BlockUtils.D(b5)) || b5 == MaterialUtils.a("web")) {
                if (b4) {
                    PlayerData.N(spartanPlayer);
                }
                return true;
            }
            if (!F(b5)) {
                continue;
            }
            if (a(n3, n)) {
                return true;
            }
            final double n4 = spartanLocation.getY() - spartanLocation.getBlockY();
            for (final double n5 : a(b5)) {
                if (n4 == n5 || n4 % n5 == 0.0 || n5 == Double.MAX_VALUE || (n5 == 0.0 && Values.b(n4, 5) == 0.41999) || n5 == -1.0) {
                    if (b4) {
                        PlayerData.N(spartanPlayer);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public static ArrayList<SpartanLocation> a(final SpartanLocation spartanLocation, double max, final double n) {
        max = Math.max(max, 1.0);
        final ArrayList<SpartanLocation> list = new ArrayList<SpartanLocation>();
        double n2 = 0.0;
        if (n == 0.0) {
            n2 = -max;
        }
        else if (n == 0.5) {
            list.add(spartanLocation.b().b(0.0, -(n + max), 0.0));
        }
        list.addAll(BlockUtils.a(spartanLocation, 0.3, n2, 0.3, true));
        return list;
    }
    
    private static double[] a(final Material material) {
        if (BlockUtils.k(material) || BlockUtils.i(material) || BlockUtils.j(material) || BlockUtils.q(material) || BlockUtils.r(material) || material == MaterialUtils.a("cake")) {
            return new double[] { 0.0, 0.5 };
        }
        if (BlockUtils.e(material)) {
            return new double[] { 0.0, 0.5, 0.75 };
        }
        if (BlockUtils.p(material)) {
            return new double[] { 0.0, 0.1875 };
        }
        if (material == MaterialUtils.a("end_portal_frame")) {
            return new double[] { 0.0, 0.8125 };
        }
        if (material == MaterialUtils.a("enchanting_table")) {
            return new double[] { 0.75 };
        }
        if (material == MaterialUtils.a("soil") || (Register.v1_9 && material == Material.GRASS_PATH) || (Register.v1_15 && material == Material.HONEY_BLOCK)) {
            return new double[] { 0.9375 };
        }
        if (BlockUtils.f(material)) {
            return new double[] { 0.0, 0.375 };
        }
        if (BlockUtils.m(material)) {
            return new double[] { 0.0625 };
        }
        if (material == MaterialUtils.a("repeater_on") || material == MaterialUtils.a("repeater_off") || material == MaterialUtils.a("comparator_on") || material == MaterialUtils.a("comparator_off")) {
            return new double[] { 0.125 };
        }
        if (BlockUtils.n(material)) {
            return new double[] { 0.5625 };
        }
        if (BlockUtils.s(material)) {
            return new double[] { 0.0, 0.25 };
        }
        if (Register.v1_9 && (material == Material.CHORUS_PLANT || material == Material.CHORUS_FLOWER)) {
            return new double[] { 0.0, 0.8125 };
        }
        if (material == MaterialUtils.a("daylight_detector")) {
            return new double[] { 0.375 };
        }
        if (BlockUtils.z(material)) {
            return new double[] { Double.MAX_VALUE };
        }
        if (Register.v1_14 && (material == Material.LANTERN || material == Material.STONECUTTER)) {
            return new double[] { 0.0, 0.5625 };
        }
        if (Register.v1_14 && material == Material.CAMPFIRE) {
            return new double[] { 0.4375 };
        }
        if (Register.v1_14 && material == Material.BELL) {
            return new double[] { 0.0, 0.9375 };
        }
        if (Register.v1_14 && material == Material.COMPOSTER) {
            return new double[] { 0.0, 0.125 };
        }
        if (Register.v1_14 && material == Material.LECTERN) {
            return new double[] { 0.0, 0.125, 0.875 };
        }
        if (Register.v1_14 && material == Material.GRINDSTONE) {
            return new double[] { 0.0, 0.8125 };
        }
        if (Register.v1_13 && material == Material.TURTLE_EGG) {
            return new double[] { 0.0, 0.4375 };
        }
        if (Register.v1_13 && material == Material.CONDUIT) {
            return new double[] { 0.0, 0.6875 };
        }
        if ((Register.v1_13 && material == Material.LILY_PAD) || (!Register.v1_13 && material == Material.getMaterial("WATER_LILY"))) {
            return new double[] { 0.09375 };
        }
        if (Register.v1_9 && material == Material.END_ROD) {
            return new double[] { -1.0 };
        }
        switch (GroundUtils$1.j[material.ordinal()]) {
            case 1: {
                return new double[] { 0.875 };
            }
            case 2: {
                return new double[] { 0.875 };
            }
            case 3: {
                return new double[] { 0.875 };
            }
            case 4: {
                return new double[] { 0.75 };
            }
            case 5: {
                return new double[] { 0.0, 0.25, 0.3125 };
            }
            case 6: {
                return new double[] { 0.125, 0.875 };
            }
            case 7: {
                return new double[] { 0.9375 };
            }
            case 8: {
                return new double[] { 0.0, 0.6875 };
            }
            case 9: {
                return new double[] { 0.0, 0.125 };
            }
            case 10: {
                return new double[] { 0.0, 0.875 };
            }
            default: {
                return new double[] { 0.0 };
            }
        }
    }
    
    public static double b(final Material material) {
        double max = 0.0;
        final double[] a = a(material);
        for (int length = a.length, i = 0; i < length; ++i) {
            max = Math.max(max, a[i]);
        }
        return max;
    }
    
    private static boolean F(final Material material) {
        return BlockUtils.F(material) || BlockUtils.r(material) || BlockUtils.p(material);
    }
    
    public static boolean ak(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (GroundUtils.aa.containsKey(uniqueId)) {
            final Iterator<Entity> iterator = ((List<Entity>)GroundUtils.aa.get(uniqueId)).iterator();
            while (iterator.hasNext()) {
                if (((Entity)iterator.next()) instanceof Boat) {
                    PlayerData.N(spartanPlayer);
                    return true;
                }
            }
        }
        return false;
    }
    
    private static List<Entity> a(final SpartanPlayer spartanPlayer, final double n) {
        final SpartanLocation a = spartanPlayer.a();
        final double n2 = (n < 0.0 || BlockUtils.I(spartanPlayer, a) || BlockUtils.I(spartanPlayer, a.b().b(0.0, -1.0, 0.0))) ? 2.0 : 0.7;
        return spartanPlayer.getNearbyEntities(n2, n2, n2);
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n) {
        if ((spartanPlayer == null || spartanPlayer.isOnGround()) && n == 0.5) {
            final double n2 = spartanLocation.getX() - spartanLocation.getBlockX();
            final double n3 = spartanLocation.getZ() - spartanLocation.getBlockZ();
            if (n2 <= 0.05 || n3 <= 0.05 || n2 >= 0.95 || n3 >= 0.95) {
                return !F(spartanLocation.a().getType()) && F(spartanLocation.b().b(0.0, -0.6, 0.0).a().getType());
            }
        }
        return false;
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final int n, final double n2) {
        final double a = DoubleUtils.a(spartanPlayer, "move-utils=nms-vertical");
        return (n2 == 0.0 || n2 == Double.MIN_VALUE) && ((n > 4 && n < 20) || (a < 0.0 && (Math.abs(a) % 0.0784000015258 <= 0.0784000015258 || a > -0.122))) && spartanPlayer.isOnGround() && DoubleUtils.a(spartanPlayer, "move-utils=vanilla-on-ground") == 0.0 && (!BlockUtils.c(spartanPlayer, true, 0.298, -1.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, -1.0, 0.298));
    }
    
    private static boolean al(final SpartanPlayer spartanPlayer) {
        return !PunishUtils.bh(spartanPlayer) && AttemptUtils.a(spartanPlayer, "ground-utils=utility-inaccuracy") >= 12;
    }
    
    private static boolean a(final int n, final double n2) {
        return n2 == Double.MIN_VALUE || (n2 != 0.0 && n <= 12);
    }
    
    static boolean d(final Material material) {
        return BlockUtils.v(material) || BlockUtils.u(material) || BlockUtils.p(material) || BlockUtils.t(material) || BlockUtils.q(material) || BlockUtils.r(material) || BlockUtils.j(material) || material == MaterialUtils.a("iron_bars") || BlockUtils.s(material) || material == Material.CACTUS || material == MaterialUtils.a("cake") || material == Material.COCOA || material == MaterialUtils.a("head") || BlockUtils.f(material) || material == Material.DRAGON_EGG || (Register.v1_15 && material == Material.HONEY_BLOCK) || (Register.v1_9 && (material == Material.CHORUS_PLANT || material == Material.END_ROD));
    }
    
    public static boolean K(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Material b = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        if (BlockUtils.g(b) || BlockUtils.D(b)) {
            return false;
        }
        final Iterator<SpartanLocation> iterator = a(spartanLocation, MoveUtils.h(spartanPlayer), spartanLocation.getY() - (double)spartanLocation.getBlockY()).iterator();
        while (iterator.hasNext()) {
            if (F(ClientSidedBlock.b(spartanPlayer, (SpartanLocation)iterator.next()))) {
                return true;
            }
        }
        return false;
    }
    
    static {
        aa = new HashMap<UUID, List<Entity>>();
    }
}
