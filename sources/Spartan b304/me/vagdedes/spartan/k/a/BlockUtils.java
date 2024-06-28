package me.vagdedes.spartan.k.a;

import me.vagdedes.spartan.k.d.MathUtils;
import java.util.ArrayList;
import me.vagdedes.spartan.features.d.RAMoverCPU;
import java.util.Set;
import org.bukkit.block.Block;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanBlock;
import java.util.Iterator;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.Register;
import org.bukkit.Material;
import java.util.HashSet;

public class BlockUtils
{
    private static final HashSet<Material> r;
    private static final HashSet<Material> s;
    private static final HashSet<Material> t;
    private static final HashSet<Material> u;
    private static final HashSet<Material> v;
    private static final HashSet<Material> w;
    private static final HashSet<Material> x;
    private static final HashSet<Material> y;
    private static final HashSet<Material> z;
    private static final HashSet<Material> A;
    private static final HashSet<Material> B;
    private static final HashSet<Material> C;
    private static final HashSet<Material> D;
    private static final HashSet<Material> E;
    private static final HashSet<Material> F;
    private static final HashSet<Material> G;
    private static final HashSet<Material> H;
    private static final HashSet<Material> I;
    private static final HashSet<Material> J;
    private static final HashSet<Material> K;
    private static final HashSet<Material> L;
    private static final HashSet<Material> M;
    private static final HashSet<Material> N;
    private static final HashSet<Material> O;
    private static final HashSet<Material> P;
    private static final HashSet<Material> Q;
    private static final HashSet<Material> R;
    private static final HashSet<Material> S;
    private static final HashSet<Material> T;
    private static final HashSet<Material> U;
    public static final double ax = 0.298;
    public static final double ay = 0.3;
    public static final double az = 0.2;
    public static final double aA = 0.1;
    
    public BlockUtils() {
        super();
    }
    
    private static boolean endsWith(final String s, final String suffix) {
        return s.endsWith(suffix) && !s.contains("LEGACY_");
    }
    
    private static boolean startsWith(final String s, final String prefix) {
        return s.startsWith(prefix) && !s.contains("LEGACY_");
    }
    
    private static boolean contains(final String s, final String s2) {
        return s.contains(s2) && !s.contains("LEGACY_");
    }
    
    public static boolean b(final Material o) {
        return BlockUtils.U.contains(o);
    }
    
    public static boolean c(final Material material) {
        return Register.v1_14 && material == Material.SCAFFOLDING;
    }
    
    public static boolean d(final Material material) {
        return GroundUtils.d(material);
    }
    
    public static boolean e(final Material o) {
        return Register.v1_13 ? BlockUtils.M.contains(o) : (o == Material.getMaterial("SKULL"));
    }
    
    public static boolean f(final Material o) {
        return Register.v1_13 ? BlockUtils.Q.contains(o) : (o == Material.FLOWER_POT);
    }
    
    public static boolean g(final Material o) {
        return BlockUtils.E.contains(o);
    }
    
    public static boolean h(final Material material) {
        return material == MaterialUtils.a("water") || material == MaterialUtils.a("lava");
    }
    
    public static boolean i(final Material o) {
        return BlockUtils.J.contains(o);
    }
    
    public static boolean j(final Material o) {
        return BlockUtils.S.contains(o);
    }
    
    public static boolean k(final Material o) {
        return BlockUtils.z.contains(o);
    }
    
    public static boolean l(final Material o) {
        return BlockUtils.N.contains(o);
    }
    
    public static boolean m(final Material o) {
        return Register.v1_13 ? BlockUtils.G.contains(o) : (o == Material.getMaterial("CARPET"));
    }
    
    public static boolean n(final Material o) {
        return Register.v1_13 ? BlockUtils.H.contains(o) : (o == Material.getMaterial("BED_BLOCK"));
    }
    
    public static boolean o(final Material o) {
        return Register.v1_13 ? BlockUtils.O.contains(o) : (o == Material.getMaterial("MONSTER_EGG"));
    }
    
    public static boolean p(final Material o) {
        return BlockUtils.D.contains(o);
    }
    
    public static boolean q(final Material o) {
        return BlockUtils.K.contains(o);
    }
    
    public static boolean r(final Material o) {
        return BlockUtils.L.contains(o);
    }
    
    public static boolean s(final Material o) {
        return BlockUtils.R.contains(o);
    }
    
    public static boolean t(final Material o) {
        return BlockUtils.v.contains(o);
    }
    
    public static boolean u(final Material o) {
        return BlockUtils.B.contains(o);
    }
    
    public static boolean v(final Material o) {
        return BlockUtils.y.contains(o);
    }
    
    public static boolean w(final Material o) {
        return BlockUtils.w.contains(o);
    }
    
    public static boolean x(final Material o) {
        return BlockUtils.x.contains(o);
    }
    
    public static boolean y(final Material o) {
        return BlockUtils.C.contains(o);
    }
    
    public static boolean z(final Material o) {
        return BlockUtils.I.contains(o);
    }
    
    public static boolean A(final Material o) {
        return BlockUtils.P.contains(o);
    }
    
    public static boolean B(final Material o) {
        return BlockUtils.T.contains(o);
    }
    
    public static boolean C(final Material material) {
        return Register.v1_13 ? (material == Material.COBWEB) : (material == Material.getMaterial("WEB"));
    }
    
    public static boolean D(final Material o) {
        return BlockUtils.A.contains(o);
    }
    
    public static boolean E(final Material o) {
        final VersionUtils.a a = VersionUtils.a();
        if (a != VersionUtils.a.l && a != VersionUtils.a.c) {
            if (o == Material.SLIME_BLOCK || o == Material.RED_SANDSTONE || o == Material.RED_SANDSTONE_STAIRS) {
                return true;
            }
            if (a != VersionUtils.a.d && (o == Material.END_ROD || o == MaterialUtils.a("beetroot_block") || (a != VersionUtils.a.e && o == MaterialUtils.a("magma")))) {
                return true;
            }
        }
        return !F(o) || v(o) || BlockUtils.s.contains(o) || x(o) || A(o) || l(o) || e(o) || n(o) || m(o) || f(o) || g(o) || c(o);
    }
    
    public static boolean i(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return c(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean j(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return x(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean k(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return t(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean l(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return w(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean m(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return v(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean n(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return k(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean o(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return p(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean p(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return e(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean q(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return A(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean r(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return B(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean s(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return C(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean t(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return i(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean u(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return q(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean v(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return r(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean w(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return u(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean x(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return y(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean y(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return d(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean z(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return z(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean A(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return n(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean B(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return m(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean C(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return s(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean D(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return l(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return E(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final boolean b) {
        final Material b2 = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        final VersionUtils.a a = VersionUtils.a();
        return (a == VersionUtils.a.l || a == VersionUtils.a.c || (b2 != Material.SLIME_BLOCK && (a == VersionUtils.a.d || (b2 != Material.END_ROD && b2 != Material.GRASS_PATH && b2 != MaterialUtils.a("beetroot_block"))) && (!Register.v1_15 || b2 != Material.HONEY_BLOCK))) && (!b || (b && F(b2))) && !r(b2) && !q(b2) && !i(b2) && !k(b2) && !p(b2) && !u(b2) && !v(b2) && !t(b2) && !w(b2) && !z(b2) && !l(b2) && !e(b2) && !A(b2) && !s(b2) && !m(b2) && !e(b2) && !n(b2) && !f(b2) && !j(b2) && !BlockUtils.t.contains(b2);
    }
    
    public static boolean F(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Material b = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        return BlockUtils.u.contains(b) || r(b) || p(b) || t(b) || u(b) || r(b) || z(b) || n(b) || s(b);
    }
    
    public static boolean ag(final SpartanPlayer spartanPlayer) {
        final SpartanLocation a = spartanPlayer.a();
        final Iterator<Material> iterator = BlockUtils.R.iterator();
        while (iterator.hasNext()) {
            if (a(spartanPlayer, a, iterator.next(), 0.298)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean G(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return D(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean H(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return G(spartanPlayer, spartanLocation) && a(spartanPlayer, true, 1.0, 0.0, 1.0, false);
    }
    
    public static String a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        return a(ClientSidedBlock.b(spartanPlayer, spartanBlock.a()));
    }
    
    public static String a(final Material material) {
        return material.toString().toLowerCase().replaceAll("_", "-");
    }
    
    public static String a(final Player player, final Location location, final double n) {
        if (player.getWorld() != location.getWorld()) {
            return null;
        }
        final Block block = location.getBlock();
        final double distance = player.getLocation().distance(location);
        if (distance <= 6.0) {
            for (double n2 = 0.0; n2 <= distance; ++n2) {
                final Location add = player.getLocation().add(0.0, player.getEyeHeight(), 0.0).add(player.getLocation().getDirection().multiply(n2));
                final double distance2 = player.getLocation().distance(add);
                if (distance2 < n) {
                    return null;
                }
                final Block block2 = add.getBlock();
                final double d = player.getLocation().distance(block.getLocation()) - player.getLocation().distance(block2.getLocation());
                final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
                final SpartanLocation spartanLocation = new SpartanLocation(add);
                if (c(a, spartanLocation, true) && distance2 < distance && d >= 0.4 && block2.getLocation().getY() >= player.getLocation().getY()) {
                    return "r-d: " + distance + ", c-d: " + distance2 + ", b-a: " + a(a, spartanLocation.a()) + ", b-c: " + d;
                }
            }
        }
        return null;
    }
    
    public static Block a(final Player player, final Location location) {
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        final SpartanLocation spartanLocation = new SpartanLocation(location);
        if (player.getWorld() != location.getWorld() || (!f(a, spartanLocation) && !F(a, spartanLocation)) || VersionUtils.a() == VersionUtils.a.l || VersionUtils.a() == VersionUtils.a.c) {
            return null;
        }
        final Block block = location.getBlock();
        final double distance = player.getLocation().distance(location);
        if (distance >= 0.5) {
            final double n = (distance < 1.0) ? ((double)Math.round(distance)) : distance;
            Block targetBlock = null;
            try {
                targetBlock = player.getTargetBlock((Set)null, (int)n);
            }
            catch (Exception ex) {}
            if (targetBlock != null) {
                final SpartanLocation spartanLocation2 = new SpartanLocation(targetBlock.getLocation());
                final double distance2 = targetBlock.getLocation().distance(block.getLocation());
                double n2 = 1.0;
                if (PlayerData.aS(a)) {
                    n2 = 2.5;
                }
                else if (o(player.getItemInHand().getType())) {
                    n2 = 2.0;
                }
                if (distance2 >= n2 && (c(a, spartanLocation2, true) || F(a, spartanLocation2)) && (targetBlock.getLocation().getBlockX() != block.getLocation().getBlockX() || targetBlock.getLocation().getBlockY() != block.getLocation().getBlockY() || targetBlock.getLocation().getBlockZ() != block.getLocation().getBlockZ())) {
                    return targetBlock;
                }
            }
        }
        return null;
    }
    
    public static boolean I(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return BlockUtils.E.contains(ClientSidedBlock.b(spartanPlayer, spartanLocation)) || spartanLocation.a().isLiquid();
    }
    
    public static boolean a(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-l-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (I(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean b(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-g-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(spartanPlayer.a(), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (PlayerData.L(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean c(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-s-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (f(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean d(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-c-f-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (q(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean e(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        if (!Register.v1_15) {
            return true;
        }
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-h-b-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3, true).iterator();
        while (iterator.hasNext()) {
            if (ClientSidedBlock.b(spartanPlayer, iterator.next()) == Material.HONEY_BLOCK) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean f(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-i-b-f-1", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (r(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean a(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3, final boolean b2) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "h-s-s", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b3 = b2 && f(spartanPlayer, a.b().b(0.0, n2, 0.0));
        if (!b3) {
            final ArrayList<SpartanLocation> a2 = a(a, n, n2, n3, true);
            a2.remove(0);
            final Iterator<SpartanLocation> iterator = a2.iterator();
            while (iterator.hasNext()) {
                if (c(spartanPlayer, iterator.next(), true)) {
                    b3 = true;
                    break;
                }
            }
        }
        return raMoverCPU.a(b3);
    }
    
    public static boolean g(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-n-s-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!c(spartanPlayer, iterator.next(), false)) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean h(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "c-c-a", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = false;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (G(spartanPlayer, iterator.next())) {
                b2 = true;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean i(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-i-b-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (j(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean j(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-b-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (A(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean k(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-c-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (B(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean l(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "i-h-f", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (p(spartanPlayer, iterator.next())) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean m(SpartanPlayer spartanPlayer, final boolean b, final double n, final double n2, final double n3) {
        final RAMoverCPU raMoverCPU = new RAMoverCPU(spartanPlayer.getUniqueId(), "a-w-a", b, n, n2, n3);
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        final SpartanLocation a = spartanPlayer.a();
        spartanPlayer = (b ? spartanPlayer : null);
        boolean b2 = false;
        final Iterator<SpartanLocation> iterator = a(a, n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (J(spartanPlayer, iterator.next())) {
                b2 = true;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean J(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return d(ClientSidedBlock.b(spartanPlayer, spartanLocation));
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final Material b = ClientSidedBlock.b(spartanPlayer, spartanLocation);
        final VersionUtils.a a = VersionUtils.a();
        if (a != VersionUtils.a.l && a != VersionUtils.a.c) {
            if (b == Material.ARMOR_STAND || BlockUtils.F.contains(b)) {
                return false;
            }
            if (a != VersionUtils.a.d && (b == Material.BEETROOT_SEEDS || b == Material.END_GATEWAY || (a != VersionUtils.a.e && b == Material.STRUCTURE_VOID))) {
                return false;
            }
        }
        return !v(spartanPlayer, spartanLocation) && !I(spartanPlayer, spartanLocation) && !BlockUtils.r.contains(b);
    }
    
    public static boolean F(final Material material) {
        return (!Register.v1_8 || (material != Material.ARMOR_STAND && !BlockUtils.F.contains(material))) && (!Register.v1_9 || (material != Material.BEETROOT_SEEDS && material != Material.END_GATEWAY && (VersionUtils.a() == VersionUtils.a.e || material != Material.STRUCTURE_VOID))) && !r(material) && !p(material) && !w(material) && !g(material) && !BlockUtils.r.contains(material);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material) {
        return ClientSidedBlock.b(spartanPlayer, spartanLocation) == material;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material, final int n) {
        return a(spartanPlayer, spartanLocation, material) && spartanLocation.a().getData() == n;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material, final double d) {
        final boolean b = spartanPlayer != null;
        final RAMoverCPU raMoverCPU = new RAMoverCPU(b ? spartanPlayer.getUniqueId() : null, "i-b-" + material.toString().substring(0, 3) + "-" + d, b, spartanLocation.getBlockX(), spartanLocation.getBlockY(), spartanLocation.getBlockZ());
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        boolean b2 = false;
        final Iterator<SpartanLocation> iterator = a(spartanLocation, d, 0.0, d).iterator();
        while (iterator.hasNext()) {
            if (a(spartanPlayer, iterator.next(), material)) {
                b2 = true;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material, final int n, final double d) {
        final boolean b = spartanPlayer != null;
        final RAMoverCPU raMoverCPU = new RAMoverCPU(b ? spartanPlayer.getUniqueId() : null, "i-b-t-" + material.toString().substring(0, 3) + "-" + d, b, spartanLocation.getBlockX(), spartanLocation.getBlockY(), spartanLocation.getBlockZ());
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        boolean b2 = false;
        final Iterator<SpartanLocation> iterator = a(spartanLocation, d, 0.0, d).iterator();
        while (iterator.hasNext()) {
            if (a(spartanPlayer, iterator.next(), material, n)) {
                b2 = true;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material, final double d) {
        final boolean b = spartanPlayer != null;
        final RAMoverCPU raMoverCPU = new RAMoverCPU(b ? spartanPlayer.getUniqueId() : null, "a-b-" + material.toString().substring(0, 3) + "-" + d, b, spartanLocation.getBlockX(), spartanLocation.getBlockY(), spartanLocation.getBlockZ());
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(spartanLocation, d, 0.0, d).iterator();
        while (iterator.hasNext()) {
            if (!a(spartanPlayer, iterator.next(), material)) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final Material material, final int n, final double d) {
        final boolean b = spartanPlayer != null;
        final RAMoverCPU raMoverCPU = new RAMoverCPU(b ? spartanPlayer.getUniqueId() : null, "a-b-t-" + material.toString().substring(0, 3) + "-" + d, b, spartanLocation.getBlockX(), spartanLocation.getBlockY(), spartanLocation.getBlockZ());
        if (raMoverCPU.exists()) {
            return raMoverCPU.h();
        }
        boolean b2 = true;
        final Iterator<SpartanLocation> iterator = a(spartanLocation, d, 0.0, d).iterator();
        while (iterator.hasNext()) {
            if (!a(spartanPlayer, iterator.next(), material, n)) {
                b2 = false;
                break;
            }
        }
        return raMoverCPU.a(b2);
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final int n) {
        final VersionUtils.a a = VersionUtils.a();
        if (a == VersionUtils.a.l || a == VersionUtils.a.c) {
            return false;
        }
        for (int i = 0; i <= n; ++i) {
            final SpartanLocation b = spartanPlayer.a().b(0.0, -i, 0.0);
            if (b == null) {
                return false;
            }
            final Material b2 = ClientSidedBlock.b(spartanPlayer, b);
            final boolean b3 = b2 == Material.SLIME_BLOCK;
            if (F(b2) && !m(b2) && b2 != Material.SNOW && !k(b2) && !i(b2) && !d(b2) && !D(b2) && !b3) {
                return false;
            }
            if (b3) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final int n) {
        final VersionUtils.a a = VersionUtils.a();
        if (a == VersionUtils.a.l || a == VersionUtils.a.c || a == VersionUtils.a.d || a == VersionUtils.a.e || a == VersionUtils.a.f || a == VersionUtils.a.g) {
            return false;
        }
        for (int i = 0; i <= n; ++i) {
            final SpartanLocation b = spartanPlayer.a().b(0.0, -i, 0.0);
            if (b == null) {
                return false;
            }
            final boolean contains = BlockUtils.H.contains(ClientSidedBlock.b(spartanPlayer, b));
            if (f(spartanPlayer, b) && !contains) {
                return false;
            }
            if (contains) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean d(final SpartanLocation spartanLocation) {
        final SpartanBlock a = spartanLocation.a();
        final byte data = a.getData();
        return !a.isLiquid() || data == 0 || data == 8;
    }
    
    public static boolean a(final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final int n, final boolean b) {
        if (MathUtils.b(spartanLocation, spartanLocation2) <= 1.5) {
            final int blockY = spartanLocation.getBlockY();
            final int blockY2 = spartanLocation2.getBlockY();
            final double abs = Math.abs(spartanLocation.getY() - spartanLocation2.getY());
            if (abs <= 1.0 || abs > n) {
                return true;
            }
            for (int i = Math.min(blockY2, blockY) + 1; i < Math.max(blockY2, blockY); ++i) {
                if (F(spartanLocation.b().b(0.0, -i, 0.0).a().getType()) || F(spartanLocation2.b().b(0.0, i, 0.0).a().getType())) {
                    return true;
                }
            }
        }
        else if (b) {
            return true;
        }
        return false;
    }
    
    public static ArrayList<SpartanLocation> a(final SpartanLocation e, final double n, final double n2, final double n3, final boolean b) {
        if (e == null) {
            return new ArrayList<SpartanLocation>();
        }
        final ArrayList<SpartanLocation> list = new ArrayList<SpartanLocation>();
        final Location location = new Location(e.getWorld(), e.getX(), e.getY(), e.getZ());
        final double n4 = location.getX() - location.getBlockX();
        final double n5 = location.getZ() - location.getBlockZ();
        if (n2 == 0.0) {
            list.add(e);
        }
        else {
            list.add(e.b().b(0.0, n2, 0.0));
        }
        if (!b) {
            if (n4 >= 0.0 && n4 < 0.3 && n5 >= 0.0 && n5 < 0.3) {
                list.add(e.b().b(0.0, n2, -n3));
                list.add(e.b().b(-n, n2, 0.0));
                list.add(e.b().b(-n, n2, -n3));
            }
            else if (n4 > 0.7 && n4 < 1.0 && n5 >= 0.0 && n5 < 0.3) {
                list.add(e.b().b(n, n2, 0.0));
                list.add(e.b().b(0.0, n2, -n3));
                list.add(e.b().b(n, n2, -n3));
            }
            else if (n4 > 0.7 && n4 < 1.0 && n5 > 0.7 && n5 < 1.0) {
                list.add(e.b().b(n, n2, 0.0));
                list.add(e.b().b(0.0, n2, n3));
                list.add(e.b().b(n, n2, n3));
            }
            else if (n4 >= 0.0 && n4 < 0.3 && n5 > 0.7 && n5 < 1.0) {
                list.add(e.b().b(-n, n2, 0.0));
                list.add(e.b().b(0.0, n2, n3));
                list.add(e.b().b(-n, n2, n3));
            }
            else if (n4 >= 0.0 && n4 < 0.3 && n5 > 0.3 && n5 < 0.7) {
                list.add(e.b().b(n, n2, 0.0));
            }
            else if (n4 > 0.7 && n4 < 1.0 && n5 > 0.3 && n5 < 0.7) {
                list.add(e.b().b(-n, n2, 0.0));
            }
            else if (n4 > 0.3 && n4 < 0.7 && n5 >= 0.0 && n5 < 0.3) {
                list.add(e.b().b(0.0, n2, -n3));
            }
            else if (n4 > 0.3 && n4 < 0.7 && n5 > 0.7 && n5 < 1.0) {
                list.add(e.b().b(0.0, n2, n3));
            }
        }
        else {
            list.add(e.b().b(n, n2, 0.0));
            list.add(e.b().b(-n, n2, 0.0));
            list.add(e.b().b(0.0, n2, n3));
            list.add(e.b().b(0.0, n2, -n3));
            list.add(e.b().b(n, n2, n3));
            list.add(e.b().b(-n, n2, -n3));
            list.add(e.b().b(n, n2, -n3));
            list.add(e.b().b(-n, n2, n3));
        }
        return list;
    }
    
    public static ArrayList<SpartanLocation> a(final SpartanLocation spartanLocation, final double n, final double n2, final double n3) {
        return a(spartanLocation, n, n2, n3, false);
    }
    
    static {
        r = new HashSet<Material>();
        s = new HashSet<Material>();
        t = new HashSet<Material>();
        u = new HashSet<Material>();
        v = new HashSet<Material>();
        w = new HashSet<Material>();
        x = new HashSet<Material>();
        y = new HashSet<Material>();
        z = new HashSet<Material>();
        A = new HashSet<Material>();
        B = new HashSet<Material>();
        C = new HashSet<Material>();
        D = new HashSet<Material>();
        E = new HashSet<Material>();
        F = new HashSet<Material>();
        G = new HashSet<Material>();
        H = new HashSet<Material>();
        I = new HashSet<Material>();
        J = new HashSet<Material>();
        K = new HashSet<Material>();
        L = new HashSet<Material>();
        M = new HashSet<Material>();
        N = new HashSet<Material>();
        O = new HashSet<Material>();
        P = new HashSet<Material>();
        Q = new HashSet<Material>();
        R = new HashSet<Material>();
        S = new HashSet<Material>();
        T = new HashSet<Material>();
        U = new HashSet<Material>();
        final VersionUtils.a a = VersionUtils.a();
        final Material[] values = Material.values();
        if (Register.v1_13) {
            BlockUtils.E.add(Material.BUBBLE_COLUMN);
            BlockUtils.E.add(Material.KELP);
            BlockUtils.E.add(Material.KELP_PLANT);
            BlockUtils.E.add(Material.SEAGRASS);
            BlockUtils.E.add(Material.TALL_SEAGRASS);
            BlockUtils.E.add(Material.SEA_PICKLE);
        }
        else {
            BlockUtils.E.add(Material.getMaterial("STATIONARY_WATER"));
            BlockUtils.E.add(Material.getMaterial("STATIONARY_LAVA"));
        }
        BlockUtils.E.add(Material.WATER);
        BlockUtils.E.add(Material.LAVA);
        if (Register.v1_13) {
            if (Register.v1_14) {
                BlockUtils.t.add(Material.BELL);
                BlockUtils.t.add(Material.LANTERN);
                BlockUtils.t.add(Material.CAMPFIRE);
                BlockUtils.t.add(Material.COMPOSTER);
                BlockUtils.t.add(Material.LECTERN);
                BlockUtils.t.add(Material.GRINDSTONE);
                BlockUtils.t.add(Material.STONECUTTER);
                BlockUtils.t.add(Material.BAMBOO);
            }
            BlockUtils.t.add(Material.PISTON_HEAD);
            BlockUtils.t.add(Material.COBBLESTONE_WALL);
            BlockUtils.t.add(Material.MOSSY_COBBLESTONE_WALL);
            BlockUtils.t.add(Material.IRON_BARS);
            BlockUtils.t.add(Material.PISTON);
            BlockUtils.t.add(Material.STICKY_PISTON);
            BlockUtils.t.add(Material.COBWEB);
            BlockUtils.t.add(Material.FARMLAND);
            BlockUtils.t.add(Material.TURTLE_EGG);
            BlockUtils.t.add(Material.CONDUIT);
        }
        else {
            BlockUtils.t.add(Material.getMaterial("PISTON_EXTENSION"));
            BlockUtils.t.add(Material.getMaterial("COBBLE_WALL"));
            BlockUtils.t.add(Material.getMaterial("IRON_FENCE"));
            BlockUtils.t.add(Material.getMaterial("SOIL"));
            BlockUtils.t.add(Material.getMaterial("PISTON_BASE"));
            BlockUtils.t.add(Material.getMaterial("PISTON_STICKY_BASE"));
            BlockUtils.t.add(Material.getMaterial("WEB"));
        }
        BlockUtils.t.add(Material.COCOA);
        BlockUtils.t.add(Material.DRAGON_EGG);
        BlockUtils.t.add(Material.HOPPER);
        BlockUtils.t.add(Material.FLOWER_POT);
        BlockUtils.t.add(Material.BREWING_STAND);
        BlockUtils.t.add(Material.CAULDRON);
        BlockUtils.t.add(Material.LADDER);
        BlockUtils.t.add(Material.VINE);
        BlockUtils.t.add(Material.SNOW);
        BlockUtils.t.add(Material.SNOW_BLOCK);
        BlockUtils.t.add(Material.CACTUS);
        BlockUtils.t.add(Material.SOUL_SAND);
        BlockUtils.t.add(MaterialUtils.a("daylight_detector"));
        BlockUtils.t.add(Material.FIRE);
        BlockUtils.t.add(MaterialUtils.a("repeater_on"));
        BlockUtils.t.add(MaterialUtils.a("repeater_off"));
        BlockUtils.t.add(MaterialUtils.a("comparator_on"));
        BlockUtils.t.add(MaterialUtils.a("comparator_off"));
        BlockUtils.t.add(MaterialUtils.a("enchanting_table"));
        BlockUtils.t.add(MaterialUtils.a("lily_pad"));
        BlockUtils.t.add(MaterialUtils.a("end_portal_frame"));
        BlockUtils.t.add(MaterialUtils.a("cake"));
        if (Register.v1_13) {
            if (Register.v1_14) {
                BlockUtils.s.add(Material.BAMBOO);
            }
            BlockUtils.s.add(Material.TURTLE_EGG);
            BlockUtils.s.add(Material.BROWN_MUSHROOM);
            BlockUtils.s.add(Material.RED_MUSHROOM);
            BlockUtils.s.add(Material.BROWN_MUSHROOM_BLOCK);
            BlockUtils.s.add(Material.RED_MUSHROOM_BLOCK);
            BlockUtils.s.add(Material.CUT_SANDSTONE);
            BlockUtils.s.add(Material.CUT_RED_SANDSTONE);
        }
        else {
            BlockUtils.s.add(Material.getMaterial("HUGE_MUSHROOM_1"));
            BlockUtils.s.add(Material.getMaterial("HUGE_MUSHROOM_2"));
        }
        BlockUtils.s.add(Material.VINE);
        BlockUtils.s.add(Material.LADDER);
        BlockUtils.s.add(Material.TNT);
        BlockUtils.s.add(Material.COCOA);
        BlockUtils.s.add(Material.NETHERRACK);
        BlockUtils.s.add(Material.GLASS);
        BlockUtils.s.add(Material.SANDSTONE);
        BlockUtils.s.add(Material.SANDSTONE_STAIRS);
        BlockUtils.s.add(Material.QUARTZ_BLOCK);
        BlockUtils.s.add(Material.QUARTZ_STAIRS);
        BlockUtils.s.add(Material.SNOW);
        BlockUtils.s.add(Material.SNOW_BLOCK);
        BlockUtils.s.add(Material.SOUL_SAND);
        BlockUtils.s.add(MaterialUtils.a("lily_pad"));
        BlockUtils.s.add(MaterialUtils.a("daylight_detector"));
        BlockUtils.s.add(MaterialUtils.a("repeater_on"));
        BlockUtils.s.add(MaterialUtils.a("repeater_off"));
        BlockUtils.s.add(MaterialUtils.a("comparator_on"));
        BlockUtils.s.add(MaterialUtils.a("comparator_off"));
        BlockUtils.s.add(MaterialUtils.a("enchanting_table"));
        if (Register.v1_14) {
            BlockUtils.r.add(Material.SCAFFOLDING);
            BlockUtils.r.add(Material.WITHER_ROSE);
            BlockUtils.r.add(Material.SWEET_BERRY_BUSH);
            BlockUtils.r.add(Material.CORNFLOWER);
            BlockUtils.r.add(Material.LILY_OF_THE_VALLEY);
        }
        else {
            BlockUtils.r.add(Material.getMaterial("SIGN"));
        }
        if (Register.v1_13) {
            BlockUtils.r.add(Material.REDSTONE_TORCH);
            BlockUtils.r.add(Material.REDSTONE_WALL_TORCH);
            BlockUtils.r.add(Material.WALL_TORCH);
            BlockUtils.r.add(Material.RAIL);
            BlockUtils.r.add(Material.END_PORTAL);
            BlockUtils.r.add(Material.NETHER_PORTAL);
            BlockUtils.r.add(Material.MOVING_PISTON);
            BlockUtils.r.add(Material.DANDELION);
            BlockUtils.r.add(Material.POPPY);
            BlockUtils.r.add(Material.SUGAR_CANE);
            BlockUtils.r.add(Material.TALL_GRASS);
            BlockUtils.r.add(Material.POTATOES);
            BlockUtils.r.add(Material.CARROTS);
            BlockUtils.r.add(Material.NETHER_WART);
            BlockUtils.r.add(Material.ACTIVATOR_RAIL);
            BlockUtils.r.add(Material.DETECTOR_RAIL);
            BlockUtils.r.add(Material.POWERED_RAIL);
            BlockUtils.r.add(Material.BEETROOT_SEEDS);
            BlockUtils.r.add(Material.MELON_SEEDS);
            BlockUtils.r.add(Material.PUMPKIN_SEEDS);
            BlockUtils.r.add(Material.WHEAT);
            BlockUtils.r.add(Material.WHEAT_SEEDS);
            BlockUtils.r.add(Material.BUBBLE_COLUMN);
            BlockUtils.r.add(Material.CAVE_AIR);
            BlockUtils.r.add(Material.VOID_AIR);
            BlockUtils.r.add(Material.KELP);
            BlockUtils.r.add(Material.KELP_PLANT);
            BlockUtils.r.add(Material.BEETROOTS);
            BlockUtils.r.add(Material.GRASS);
            BlockUtils.r.add(Material.FERN);
            BlockUtils.r.add(Material.LARGE_FERN);
            BlockUtils.r.add(Material.SUNFLOWER);
            BlockUtils.r.add(Material.AZURE_BLUET);
            BlockUtils.r.add(Material.ATTACHED_MELON_STEM);
            BlockUtils.r.add(Material.ATTACHED_PUMPKIN_STEM);
            BlockUtils.r.add(Material.ROSE_BUSH);
            BlockUtils.r.add(Material.ALLIUM);
            BlockUtils.r.add(Material.OXEYE_DAISY);
            BlockUtils.r.add(Material.BLUE_ORCHID);
            BlockUtils.r.add(Material.LILAC);
            BlockUtils.r.add(Material.PEONY);
            BlockUtils.r.add(Material.COBWEB);
            for (final Material e : values) {
                final String string = e.toString();
                if (endsWith(string, "_BUTTON") || (!startsWith(string, "POTTED_") && (endsWith(string, "_SAPLING") || endsWith(string, "_TULIP")))) {
                    BlockUtils.r.add(e);
                }
            }
        }
        else {
            BlockUtils.r.add(Material.getMaterial("REDSTONE_TORCH_ON"));
            BlockUtils.r.add(Material.getMaterial("REDSTONE_TORCH_OFF"));
            BlockUtils.r.add(Material.getMaterial("SEEDS"));
            BlockUtils.r.add(Material.getMaterial("RAILS"));
            BlockUtils.r.add(Material.getMaterial("ENDER_PORTAL"));
            BlockUtils.r.add(Material.getMaterial("PORTAL"));
            BlockUtils.r.add(Material.getMaterial("PISTON_MOVING_PIECE"));
            BlockUtils.r.add(Material.getMaterial("CROPS"));
            BlockUtils.r.add(Material.getMaterial("SIGN_POST"));
            BlockUtils.r.add(Material.getMaterial("RED_ROSE"));
            BlockUtils.r.add(Material.getMaterial("FLOWER"));
            BlockUtils.r.add(Material.getMaterial("YELLOW_FLOWER"));
            BlockUtils.r.add(Material.getMaterial("SUGAR_CANE_BLOCK"));
            BlockUtils.r.add(Material.getMaterial("SAPLING"));
            BlockUtils.r.add(Material.getMaterial("LONG_GRASS"));
            BlockUtils.r.add(Material.getMaterial("NETHER_WARTS"));
            BlockUtils.r.add(Material.getMaterial("DOUBLE_PLANT"));
            BlockUtils.r.add(Material.getMaterial("NETHER_WARTS"));
            BlockUtils.r.add(Material.getMaterial("WOOD_BUTTON"));
            BlockUtils.r.add(Material.STONE_BUTTON);
            BlockUtils.r.add(Material.getMaterial("WEB"));
        }
        for (final Material e2 : values) {
            if (endsWith(e2.toString(), "_SIGN")) {
                BlockUtils.r.add(e2);
            }
        }
        BlockUtils.r.add(Material.SUGAR_CANE);
        BlockUtils.r.add(Material.AIR);
        BlockUtils.r.add(Material.BROWN_MUSHROOM);
        BlockUtils.r.add(Material.RED_MUSHROOM);
        BlockUtils.r.add(Material.TORCH);
        BlockUtils.r.add(Material.TRIPWIRE);
        BlockUtils.r.add(Material.TRIPWIRE_HOOK);
        BlockUtils.r.add(Material.REDSTONE_WIRE);
        BlockUtils.r.add(Material.ACTIVATOR_RAIL);
        BlockUtils.r.add(Material.DETECTOR_RAIL);
        BlockUtils.r.add(Material.POWERED_RAIL);
        BlockUtils.r.add(Material.MELON_SEEDS);
        BlockUtils.r.add(Material.PUMPKIN_SEEDS);
        BlockUtils.r.add(Material.PUMPKIN_STEM);
        BlockUtils.r.add(Material.MELON_STEM);
        BlockUtils.r.add(Material.CARROT);
        BlockUtils.r.add(Material.FIRE);
        BlockUtils.r.add(Material.POTATO);
        BlockUtils.r.add(Material.LEVER);
        BlockUtils.r.add(Material.DEAD_BUSH);
        BlockUtils.r.add(Material.VINE);
        if (Register.v1_13) {
            if (Register.v1_14) {
                BlockUtils.u.add(Material.SMITHING_TABLE);
                BlockUtils.u.add(Material.GRINDSTONE);
                BlockUtils.u.add(Material.FLETCHING_TABLE);
                BlockUtils.u.add(Material.STONECUTTER);
                BlockUtils.u.add(Material.CARTOGRAPHY_TABLE);
                BlockUtils.u.add(Material.BLAST_FURNACE);
                BlockUtils.u.add(Material.SMOKER);
                BlockUtils.u.add(Material.LOOM);
                BlockUtils.u.add(Material.BARREL);
                BlockUtils.u.add(Material.BELL);
            }
            for (final Material e3 : values) {
                if (endsWith(e3.toString(), "_BUTTON")) {
                    BlockUtils.u.add(e3);
                }
            }
        }
        else {
            BlockUtils.u.add(Material.getMaterial("WOOD_BUTTON"));
            BlockUtils.u.add(Material.STONE_BUTTON);
        }
        BlockUtils.u.add(Material.ITEM_FRAME);
        BlockUtils.u.add(Material.HOPPER);
        BlockUtils.u.add(Material.JUKEBOX);
        BlockUtils.u.add(Material.NOTE_BLOCK);
        BlockUtils.u.add(Material.DROPPER);
        BlockUtils.u.add(Material.BREWING_STAND);
        BlockUtils.u.add(Material.TRIPWIRE);
        BlockUtils.u.add(Material.TRIPWIRE_HOOK);
        BlockUtils.u.add(Material.LEVER);
        BlockUtils.u.add(Material.ANVIL);
        BlockUtils.u.add(MaterialUtils.a("crafting_table"));
        BlockUtils.u.add(MaterialUtils.a("repeater_on"));
        BlockUtils.u.add(MaterialUtils.a("repeater_off"));
        BlockUtils.u.add(MaterialUtils.a("comparator_on"));
        BlockUtils.u.add(MaterialUtils.a("comparator_off"));
        BlockUtils.u.add(MaterialUtils.a("enchanting_table"));
        BlockUtils.u.add(MaterialUtils.a("end_portal_frame"));
        BlockUtils.u.add(MaterialUtils.a("furnace"));
        BlockUtils.v.add(Material.CHEST);
        BlockUtils.v.add(Material.TRAPPED_CHEST);
        BlockUtils.v.add(Material.ENDER_CHEST);
        if (Register.v1_13) {
            for (final Material e4 : values) {
                if (e4.toString().contains("_PLATE")) {
                    BlockUtils.w.add(e4);
                }
            }
        }
        else {
            BlockUtils.w.add(Material.getMaterial("GOLD_PLATE"));
            BlockUtils.w.add(Material.getMaterial("IRON_PLATE"));
            BlockUtils.w.add(Material.getMaterial("STONE_PLATE"));
            BlockUtils.w.add(Material.getMaterial("WOOD_PLATE"));
        }
        BlockUtils.x.add(Material.ICE);
        BlockUtils.x.add(Material.PACKED_ICE);
        if (Register.v1_9) {
            BlockUtils.x.add(Material.FROSTED_ICE);
            if (Register.v1_13) {
                BlockUtils.x.add(Material.BLUE_ICE);
            }
        }
        if (Register.v1_13) {
            BlockUtils.y.add(Material.GLASS_PANE);
            for (final Material e5 : values) {
                if (e5.toString().contains("_STAINED_GLASS_PANE")) {
                    BlockUtils.y.add(e5);
                }
            }
        }
        else {
            BlockUtils.y.add(Material.getMaterial("THIN_GLASS"));
            BlockUtils.y.add(Material.getMaterial("STAINED_GLASS_PANE"));
        }
        if (!Register.v1_13) {
            BlockUtils.z.add(Material.getMaterial("STEP"));
            BlockUtils.z.add(Material.getMaterial("WOOD_STEP"));
        }
        for (final Material e6 : values) {
            if (contains(e6.toString(), "_SLAB")) {
                BlockUtils.z.add(e6);
            }
        }
        BlockUtils.A.add(Material.LADDER);
        BlockUtils.A.add(Material.VINE);
        if (Register.v1_14) {
            BlockUtils.A.add(Material.SCAFFOLDING);
        }
        BlockUtils.C.add(Material.SAND);
        BlockUtils.C.add(Material.GRAVEL);
        if (a != VersionUtils.a.l && a != VersionUtils.a.c && a != VersionUtils.a.d && a != VersionUtils.a.e && a != VersionUtils.a.f && a != VersionUtils.a.g) {
            if (Register.v1_13) {
                for (final Material e7 : values) {
                    if (endsWith(e7.toString(), "_CONCRETE_POWDER")) {
                        BlockUtils.C.add(e7);
                    }
                }
            }
            else {
                BlockUtils.C.add(Material.getMaterial("CONCRETE_POWDER"));
            }
        }
        if (Register.v1_13) {
            BlockUtils.B.add(Material.IRON_DOOR);
            BlockUtils.B.add(Material.OAK_DOOR);
        }
        else {
            BlockUtils.B.add(Material.getMaterial("IRON_DOOR_BLOCK"));
            BlockUtils.B.add(Material.getMaterial("WOODEN_DOOR"));
        }
        if (a != VersionUtils.a.l && a != VersionUtils.a.c) {
            BlockUtils.B.add(Material.ACACIA_DOOR);
            BlockUtils.B.add(Material.BIRCH_DOOR);
            BlockUtils.B.add(Material.DARK_OAK_DOOR);
            BlockUtils.B.add(Material.JUNGLE_DOOR);
            BlockUtils.B.add(Material.SPRUCE_DOOR);
        }
        if (Register.v1_13) {
            BlockUtils.D.add(Material.ACACIA_TRAPDOOR);
            BlockUtils.D.add(Material.BIRCH_TRAPDOOR);
            BlockUtils.D.add(Material.DARK_OAK_TRAPDOOR);
            BlockUtils.D.add(Material.IRON_TRAPDOOR);
            BlockUtils.D.add(Material.JUNGLE_TRAPDOOR);
            BlockUtils.D.add(Material.OAK_TRAPDOOR);
            BlockUtils.D.add(Material.SPRUCE_TRAPDOOR);
        }
        else {
            BlockUtils.D.add(Material.getMaterial("TRAP_DOOR"));
            if (a != VersionUtils.a.l && a != VersionUtils.a.c) {
                BlockUtils.D.add(Material.IRON_TRAPDOOR);
            }
        }
        if (Register.v1_13) {
            for (final Material e8 : values) {
                if (endsWith(e8.toString(), "_CARPET")) {
                    BlockUtils.G.add(e8);
                }
            }
        }
        else {
            BlockUtils.G.add(Material.getMaterial("CARPET"));
        }
        if (Register.v1_13) {
            for (final Material e9 : values) {
                if (endsWith(e9.toString(), "_BED")) {
                    BlockUtils.H.add(e9);
                }
            }
        }
        else {
            BlockUtils.H.add(Material.getMaterial("BED_BLOCK"));
        }
        if (Register.v1_13) {
            for (final Material e10 : values) {
                if (endsWith(e10.toString(), "_BANNER")) {
                    BlockUtils.F.add(e10);
                }
            }
        }
        else {
            BlockUtils.F.add(Material.getMaterial("STANDING_BANNER"));
            BlockUtils.F.add(Material.getMaterial("WALL_BANNER"));
        }
        if (Register.v1_9 && a != VersionUtils.a.e && a != VersionUtils.a.f) {
            for (final Material e11 : values) {
                final String string2 = e11.toString();
                if (Register.v1_13) {
                    BlockUtils.I.add(Material.SHULKER_BOX);
                }
                if (endsWith(string2, "_SHULKER_BOX")) {
                    BlockUtils.I.add(e11);
                }
            }
        }
        for (final Material e12 : values) {
            if (endsWith(e12.toString(), "_STAIRS")) {
                BlockUtils.J.add(e12);
            }
        }
        if (!Register.v1_13) {
            BlockUtils.K.add(Material.getMaterial("FENCE"));
        }
        for (final Material e13 : values) {
            if (endsWith(e13.toString(), "_FENCE")) {
                BlockUtils.K.add(e13);
            }
        }
        if (!Register.v1_13) {
            BlockUtils.K.add(Material.getMaterial("FENCE_GATE"));
        }
        for (final Material e14 : values) {
            if (endsWith(e14.toString(), "_FENCE_GATE")) {
                BlockUtils.L.add(e14);
            }
        }
        if (Register.v1_13) {
            BlockUtils.M.add(Material.SKELETON_SKULL);
            BlockUtils.M.add(Material.SKELETON_WALL_SKULL);
            BlockUtils.M.add(Material.WITHER_SKELETON_SKULL);
            BlockUtils.M.add(Material.WITHER_SKELETON_WALL_SKULL);
            BlockUtils.M.add(Material.CREEPER_HEAD);
            BlockUtils.M.add(Material.CREEPER_WALL_HEAD);
            BlockUtils.M.add(Material.DRAGON_HEAD);
            BlockUtils.M.add(Material.DRAGON_WALL_HEAD);
            BlockUtils.M.add(Material.PLAYER_HEAD);
            BlockUtils.M.add(Material.PLAYER_WALL_HEAD);
            BlockUtils.M.add(Material.ZOMBIE_HEAD);
            BlockUtils.M.add(Material.ZOMBIE_WALL_HEAD);
        }
        if (Register.v1_13) {
            BlockUtils.N.add(Material.ACACIA_LEAVES);
            BlockUtils.N.add(Material.BIRCH_LEAVES);
            BlockUtils.N.add(Material.DARK_OAK_LEAVES);
            BlockUtils.N.add(Material.JUNGLE_LEAVES);
            BlockUtils.N.add(Material.OAK_LEAVES);
            BlockUtils.N.add(Material.SPRUCE_LEAVES);
        }
        else {
            BlockUtils.N.add(Material.getMaterial("LEAVES"));
            BlockUtils.N.add(Material.getMaterial("LEAVES_2"));
        }
        if (Register.v1_13) {
            for (final Material e15 : values) {
                if (endsWith(e15.toString(), "_SPAWN_EGG")) {
                    BlockUtils.O.add(e15);
                }
            }
        }
        if (Register.v1_13) {
            BlockUtils.P.add(Material.BRAIN_CORAL_FAN);
            BlockUtils.P.add(Material.BRAIN_CORAL);
            BlockUtils.P.add(Material.DEAD_BRAIN_CORAL_FAN);
            BlockUtils.P.add(Material.DEAD_BRAIN_CORAL);
            BlockUtils.P.add(Material.BUBBLE_CORAL_FAN);
            BlockUtils.P.add(Material.BUBBLE_CORAL);
            BlockUtils.P.add(Material.DEAD_BUBBLE_CORAL_FAN);
            BlockUtils.P.add(Material.DEAD_BUBBLE_CORAL);
            BlockUtils.P.add(Material.FIRE_CORAL_FAN);
            BlockUtils.P.add(Material.FIRE_CORAL);
            BlockUtils.P.add(Material.DEAD_FIRE_CORAL_FAN);
            BlockUtils.P.add(Material.DEAD_FIRE_CORAL);
            BlockUtils.P.add(Material.HORN_CORAL_FAN);
            BlockUtils.P.add(Material.HORN_CORAL);
            BlockUtils.P.add(Material.DEAD_HORN_CORAL_FAN);
            BlockUtils.P.add(Material.DEAD_HORN_CORAL);
            BlockUtils.P.add(Material.TUBE_CORAL_FAN);
            BlockUtils.P.add(Material.TUBE_CORAL);
            BlockUtils.P.add(Material.DEAD_TUBE_CORAL_FAN);
            BlockUtils.P.add(Material.DEAD_TUBE_CORAL);
        }
        if (Register.v1_13) {
            BlockUtils.Q.add(Material.FLOWER_POT);
            for (final Material e16 : values) {
                if (startsWith(e16.toString(), "POTTED_")) {
                    BlockUtils.Q.add(e16);
                }
            }
        }
        if (Register.v1_13) {
            BlockUtils.R.add(Material.ANVIL);
            BlockUtils.R.add(Material.CHIPPED_ANVIL);
            BlockUtils.R.add(Material.DAMAGED_ANVIL);
        }
        else {
            BlockUtils.R.add(Material.ANVIL);
        }
        for (final Material e17 : values) {
            if (endsWith(e17.toString(), "_WALL")) {
                BlockUtils.S.add(e17);
            }
        }
        if (Register.v1_14) {
            BlockUtils.T.add(Material.SWEET_BERRY_BUSH);
        }
        if (Register.v1_13) {
            if (Register.v1_14) {
                BlockUtils.U.add(Material.SWEET_BERRIES);
            }
            BlockUtils.U.add(Material.PORKCHOP);
            BlockUtils.U.add(Material.COOKED_PORKCHOP);
            BlockUtils.U.add(Material.BEEF);
            BlockUtils.U.add(Material.MUSHROOM_STEW);
            BlockUtils.U.add(Material.DRIED_KELP);
            BlockUtils.U.add(Material.MELON_SLICE);
            BlockUtils.U.add(Material.SALMON);
            BlockUtils.U.add(Material.COOKED_SALMON);
            BlockUtils.U.add(Material.ENCHANTED_GOLDEN_APPLE);
            BlockUtils.U.add(Material.COD);
            BlockUtils.U.add(Material.COOKED_COD);
            BlockUtils.U.add(Material.TROPICAL_FISH);
            BlockUtils.U.add(Material.BEETROOT);
            BlockUtils.U.add(Material.BEETROOT_SOUP);
            BlockUtils.U.add(Material.CHICKEN);
            BlockUtils.U.add(Material.POTATO);
            BlockUtils.U.add(Material.CARROT);
            BlockUtils.U.add(Material.PUFFERFISH);
        }
        else {
            BlockUtils.U.add(Material.getMaterial("MELON"));
            BlockUtils.U.add(Material.getMaterial("MUSHROOM_SOUP"));
            BlockUtils.U.add(Material.getMaterial("PORK"));
            BlockUtils.U.add(Material.getMaterial("GRILLED_PORK"));
            BlockUtils.U.add(Material.getMaterial("RAW_BEEF"));
            BlockUtils.U.add(Material.getMaterial("RAW_CHICKEN"));
            BlockUtils.U.add(Material.getMaterial("POTATO_ITEM"));
            BlockUtils.U.add(Material.getMaterial("CARROT_ITEM"));
        }
        if (a != VersionUtils.a.l && a != VersionUtils.a.c) {
            BlockUtils.U.add(Material.RABBIT);
            BlockUtils.U.add(Material.COOKED_RABBIT);
            BlockUtils.U.add(Material.RABBIT_STEW);
            BlockUtils.U.add(Material.MUTTON);
            BlockUtils.U.add(Material.COOKED_MUTTON);
        }
        BlockUtils.U.add(Material.COOKIE);
        BlockUtils.U.add(Material.GOLDEN_APPLE);
        BlockUtils.U.add(Material.APPLE);
        BlockUtils.U.add(Material.BREAD);
        BlockUtils.U.add(Material.ROTTEN_FLESH);
        BlockUtils.U.add(Material.SPIDER_EYE);
        BlockUtils.U.add(Material.BAKED_POTATO);
        BlockUtils.U.add(Material.POISONOUS_POTATO);
        BlockUtils.U.add(Material.PUMPKIN_PIE);
        BlockUtils.U.add(Material.COOKED_CHICKEN);
        BlockUtils.U.add(Material.COOKED_BEEF);
    }
}
