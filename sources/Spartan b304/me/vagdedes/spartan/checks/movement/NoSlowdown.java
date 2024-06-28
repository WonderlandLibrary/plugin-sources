package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.h.Explosion;
import java.util.Iterator;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.h.LowViolation;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Difficulty;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class NoSlowdown
{
    private static final Enums.HackType a;
    
    public NoSlowdown() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2, final double n3) {
        c(spartanPlayer, spartanLocation, n2, n3, n);
        b(spartanPlayer, spartanLocation, n, n2);
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (!b(spartanPlayer) || !Checks.getBoolean("NoSlowdown.check_food_eating")) {
            return;
        }
        if (action == Action.RIGHT_CLICK_BLOCK && spartanBlock != null && spartanBlock.getType() == MaterialUtils.a("cake")) {
            CooldownUtils.d(spartanPlayer, NoSlowdown.a.toString() + "=food-eating", 10);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final int n) {
        if (!b(spartanPlayer) || spartanPlayer.getWorld().getDifficulty() == Difficulty.PEACEFUL || spartanPlayer.hasPotionEffect(PotionEffectType.SATURATION) || !Checks.getBoolean("NoSlowdown.check_food_eating") || !CooldownUtils.g(spartanPlayer, NoSlowdown.a.toString() + "=food-eating") || n <= spartanPlayer.getFoodLevel()) {
            return;
        }
        final GameMode gameMode = spartanPlayer.getGameMode();
        if (gameMode != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE) {
            return;
        }
        final int b = b(spartanPlayer, MoveUtils.k(spartanPlayer));
        if (b != 0) {
            new HackPrevention(spartanPlayer, NoSlowdown.a, "t: food eating, c: " + b, spartanPlayer.a(), 10, true);
        }
    }
    
    public static boolean t(final SpartanPlayer spartanPlayer) {
        return b(spartanPlayer) && Checks.getBoolean("NoSlowdown.check_bow_shots") && b(spartanPlayer, MoveUtils.k(spartanPlayer)) != 0 && !PlayerData.aw(spartanPlayer) && !PlayerData.az(spartanPlayer) && ((!PlayerData.aM(spartanPlayer) && !PlayerData.aN(spartanPlayer) && AttemptUtils.a(spartanPlayer, NoSlowdown.a.toString() + "=dynamic_sprint") < 4) || VL.a(spartanPlayer, NoSlowdown.a) >= 2 || new LowViolation(spartanPlayer, NoSlowdown.a, "bow").q());
    }
    
    private static void c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double d, final double n, final double d2) {
        if (LatencyUtils.e(spartanPlayer, 250)) {
            return;
        }
        final boolean b = PlayerData.aP(spartanPlayer) || spartanPlayer.getItemInHand().getType() == Material.POTION;
        final String a = a(spartanPlayer);
        if (MoveUtils.h(spartanPlayer, d, d2, 0.15)) {
            AttemptUtils.c(spartanPlayer, NoSlowdown.a.toString() + "=dynamic_sprint", AttemptUtils.a(spartanPlayer, NoSlowdown.a.toString() + "=dynamic_sprint") + 1);
        }
        else {
            AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=dynamic_sprint");
        }
        final int b2 = b(spartanPlayer, d);
        if (!b(spartanPlayer) || PlayerData.au(spartanPlayer) || VersionUtils.a() == VersionUtils.a.c || VersionUtils.a() == VersionUtils.a.l || b2 == 0 || !CooldownUtils.g(spartanPlayer, NoSlowdown.a.toString() + "=held") || !PlayerData.aH(spartanPlayer) || !Checks.getBoolean("NoSlowdown.check_item_usage") || (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && b && d < n) || a.equals("(air, air)") || a.contains(Material.FISHING_ROD.toString())) {
            return;
        }
        if (AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=use", 10) == 6) {
            AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=use");
            new HackPrevention(spartanPlayer, NoSlowdown.a, "t: item usage, holding: " + a + ", ds: " + d + ", dy: " + d2 + ", c: " + b2, spartanLocation, 20, true);
        }
    }
    
    private static String a(final SpartanPlayer spartanPlayer) {
        VersionUtils.a();
        String str = spartanPlayer.getItemInHand().getType().toString().toLowerCase().replaceAll("_", "-");
        if (Register.v1_9) {
            str = "(" + str + ", " + spartanPlayer.a().getItemInOffHand().getType().toString().toLowerCase().replaceAll("_", "-") + ")";
        }
        return str;
    }
    
    public static void m(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, NoSlowdown.a.toString() + "=held", 3);
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2) {
        if (!b(spartanPlayer) || PlayerData.aS(spartanPlayer) || PlayerData.b(spartanPlayer, 1, 0.5) || !Checks.getBoolean("NoSlowdown.check_cobweb_movement")) {
            return;
        }
        boolean b = true;
        boolean b2 = false;
        int a = AttemptUtils.a(spartanPlayer, NoSlowdown.a.toString() + "=web_ticks");
        final SpartanLocation b3 = spartanPlayer.a().b(0.0, -1.0, 0.0);
        if (BlockUtils.j(spartanPlayer, b3)) {
            CooldownUtils.d(spartanPlayer, NoSlowdown.a.toString() + "=ice=protection", 20);
        }
        final boolean b4 = !CooldownUtils.g(spartanPlayer, NoSlowdown.a.toString() + "=ice=protection") && (spartanPlayer.a().a().getType() == MaterialUtils.a("web") || !BlockUtils.f(spartanPlayer, spartanPlayer.a()));
        final double n3 = 0.13;
        final double n4 = (double)PlayerData.b(spartanPlayer);
        double d = Damage.E(spartanPlayer) ? 0.5 : ((n4 > 0.0) ? 0.4 : (b4 ? 0.275 : (SelfHit.E(spartanPlayer) ? 0.175 : n3)));
        if (spartanPlayer.a().a().getType() == Material.AIR && BlockUtils.b(spartanPlayer, b3, MaterialUtils.a("web"), 1.0)) {
            if (n == 0.0 && n2 >= 0.21 && AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=walking=web", 20) == 6) {
                AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=walking=web");
                b(spartanPlayer, spartanLocation, "t: walking(web), dy: " + n + ", ds: " + n2);
            }
            if (n > 0.0 && n2 > 0.0 && AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=jumping=web", 20) == 6) {
                AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=jumping=web");
                b(spartanPlayer, spartanLocation, "t: jumping(web), dy: " + n + ", ds: " + n2);
            }
        }
        for (int i = -1; i <= 1; ++i) {
            if (spartanPlayer.a().b(0.0, i, 0.0).a().getType() == MaterialUtils.a("web")) {
                b2 = true;
                boolean b5 = false;
                if (i == -1) {
                    b5 = true;
                }
                if (i >= 0) {
                    final double c = MoveUtils.c(spartanPlayer, spartanLocation);
                    ++a;
                    AttemptUtils.c(spartanPlayer, NoSlowdown.a.toString() + "=web_ticks", a);
                    if (a <= 10 && d == n3) {
                        d += 0.4;
                    }
                    if (c >= LagManagement.f(spartanPlayer, 0.16) && a > 10 && AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=speed=packets", 20) >= 2) {
                        b(spartanPlayer, spartanLocation, "t: web(packets), d: " + n2 + ", diff: " + c + ", dm: " + d);
                    }
                    if (d > 0.0) {
                        final double f = LagManagement.f(spartanPlayer, MoveUtils.a(spartanPlayer, d, 25.0, PotionEffectType.SPEED));
                        if (n2 >= f) {
                            b(spartanPlayer, spartanLocation, "type: web(speed), ds: " + n2 + ", dm: " + f);
                        }
                        if (n2 >= 0.1 && !PlayerData.aw(spartanPlayer) && !PlayerData.az(spartanPlayer) && !Damage.E(spartanPlayer) && n4 == 0.0 && a > 10) {
                            final double b6 = Values.b(n2, 10);
                            final long a2 = MillisUtils.a(spartanPlayer, NoSlowdown.a.toString() + "=repeated=speed=" + b6);
                            if (a2 >= 5L && a2 <= 250L && AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=repeated=speed", 10) == 3) {
                                AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=repeated=speed");
                                b(spartanPlayer, spartanLocation, "t: web(repeated), t: " + a2 + ", d: " + b6 + ", dm: " + f);
                            }
                            MillisUtils.o(spartanPlayer, NoSlowdown.a.toString() + "=repeated=speed=" + b6);
                        }
                    }
                    final double d2 = g(spartanPlayer, 0.7, 0.0, 0.7) ? 0.6 : 0.15;
                    if ((n <= -1.3 || (n >= d2 && (n != 0.5 || (n == 0.5 && b5)))) && !Damage.E(spartanPlayer) && !MoveUtils.b(n)) {
                        b(spartanPlayer, spartanLocation, "t: web(ypos), dy: " + n + ", l: " + d2);
                        break;
                    }
                    break;
                }
            }
            else if (i == -1) {
                b = false;
            }
        }
        if (b && !Damage.E(spartanPlayer) && (spartanPlayer.a().a().getType() == MaterialUtils.a("web") || !BlockUtils.f(spartanPlayer, spartanPlayer.a())) && ((f(spartanPlayer, 1.0, -1.0, 1.0) && f(spartanPlayer, 1.0, 0.0, 1.0) && n >= 0.0) || (f(spartanPlayer, 1.0, 0.0, 1.0) && c(spartanPlayer.a().b(0.0, 1.0, 0.0)) && n <= -0.1)) && n != 0.5 && !MoveUtils.b(n) && (!SelfHit.E(spartanPlayer) || n >= 0.6)) {
            int n5 = 1;
            int n6 = 1;
            if (n <= 0.125) {
                n5 = 20;
                n6 = 4;
            }
            if (AttemptUtils.b(spartanPlayer, NoSlowdown.a.toString() + "=full-block", n5) >= n6) {
                b(spartanPlayer, spartanLocation, "t: web(full-block), dy: " + n);
            }
        }
        if (!b2) {
            AttemptUtils.m(spartanPlayer, NoSlowdown.a.toString() + "=web_ticks");
        }
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final String s) {
        if (CooldownUtils.g(spartanPlayer, NoSlowdown.a.toString() + "=limit")) {
            EventsHandler1.a(spartanPlayer, NoSlowdown.a, spartanLocation, s, true, true);
        }
        else {
            EventsHandler1.a(spartanPlayer, NoSlowdown.a, null, s, false, false);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (spartanBlock.getType() == MaterialUtils.a("web")) {
            CooldownUtils.d(spartanPlayer, NoSlowdown.a.toString() + "=limit", 20);
        }
    }
    
    private static boolean f(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), n, n2, n3).iterator();
        while (iterator.hasNext()) {
            if (!g(spartanPlayer, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean g(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return !BlockUtils.f(spartanPlayer, spartanLocation) || spartanLocation.a().getType() == MaterialUtils.a("web");
    }
    
    private static boolean c(final SpartanLocation spartanLocation) {
        return spartanLocation.a().getType() == MaterialUtils.a("web");
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, NoSlowdown.a, true) && !PlayerData.b(spartanPlayer, true) && !Explosion.E(spartanPlayer) && !PlayerData.aV(spartanPlayer) && !MoveUtils.c(spartanPlayer, -1.0);
    }
    
    private static int b(final SpartanPlayer spartanPlayer, final double n) {
        return (spartanPlayer == null) ? 0 : (spartanPlayer.isSprinting() ? 1 : (PlayerData.aK(spartanPlayer) ? 2 : (PlayerData.aJ(spartanPlayer) ? 3 : (PlayerData.aM(spartanPlayer) ? 4 : (PlayerData.aN(spartanPlayer) ? 5 : ((AttemptUtils.a(spartanPlayer, NoSlowdown.a.toString() + "=dynamic_sprint") >= 4 & (n == -1.0 || n > 0.215)) ? 6 : 0))))));
    }
    
    private static boolean g(final SpartanPlayer spartanPlayer, final double n, final double n2, final double n3) {
        if (spartanPlayer != null) {
            final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanPlayer.a(), n, n2, n3).iterator();
            while (iterator.hasNext()) {
                if (!e(spartanPlayer, iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean e(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        return spartanLocation != null && (BlockUtils.c(spartanPlayer, spartanLocation, false) || spartanLocation.a().getType() == MaterialUtils.a("web"));
    }
    
    static {
        a = Enums.HackType.NoSlowdown;
    }
}
