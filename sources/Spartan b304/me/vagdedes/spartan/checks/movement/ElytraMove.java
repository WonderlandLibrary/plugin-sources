package me.vagdedes.spartan.checks.movement;

import me.vagdedes.spartan.h.Explosion;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.a.Values;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.e.EventsHandler1;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.h.SelfHit;
import me.vagdedes.spartan.h.Damage;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ElytraMove
{
    private static final Enums.HackType a;
    
    public ElytraMove() {
        super();
    }
    
    public static void t(final SpartanPlayer spartanPlayer) {
        VL.a(spartanPlayer, ElytraMove.a, 40);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n, final double n2, final double n3, final int n4, final double d, final double n5) {
        if (!b(spartanPlayer, n)) {
            return;
        }
        final boolean aw = PlayerData.aW(spartanPlayer);
        final float pitch = spartanPlayer.a().getPitch();
        final boolean e = Damage.E(spartanPlayer);
        final boolean e2 = SelfHit.E(spartanPlayer);
        boolean b = false;
        final boolean aq = PlayerData.aQ(spartanPlayer);
        final double a = DoubleUtils.a(spartanPlayer, ElytraMove.a.toString() + "=pitch");
        if (DoubleUtils.h(a)) {
            final float n6 = (float)a;
            if (pitch < n6 && Math.abs(pitch - n6) >= 7.5f) {
                b = true;
            }
        }
        DoubleUtils.a(spartanPlayer, ElytraMove.a.toString() + "=pitch", pitch);
        if (Checks.getBoolean("ElytraMove.check_ratio") && pitch <= 45.0 && pitch >= -45.0 && n4 >= 30 && n >= -0.5 && n3 != 0.0 && !aw) {
            final double abs = Math.abs(n / n3);
            if (abs > 3.0) {
                if (AttemptUtils.b(spartanPlayer, ElytraMove.a.toString() + "=ratio=instant", 20) == 2) {
                    AttemptUtils.m(spartanPlayer, ElytraMove.a.toString() + "=ratio=instant");
                    EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: ratio(instant), ds: " + n3 + ", dy: " + n + ", r: " + abs + ", a: " + n4 + ", p: " + pitch, true, true);
                }
            }
            else if (abs > 1.5 && AttemptUtils.b(spartanPlayer, ElytraMove.a.toString() + "=ratio=normal", 20) == 8) {
                AttemptUtils.m(spartanPlayer, ElytraMove.a.toString() + "=ratio=normal");
                EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: ratio(normal), ds: " + n3 + ", dy: " + n + ", r: " + abs + ", a: " + n4 + ", p: " + pitch, true, true);
            }
        }
        if (Checks.getBoolean("ElytraMove.check_speed")) {
            final double d2 = aq ? 7.0 : 3.5;
            if (n3 >= MoveUtils.a(spartanPlayer, d2, 4.0, PotionEffectType.SPEED)) {
                EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: speed(normal), ds: " + n3 + ", dm: " + d2, true, true);
            }
            if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !BlockUtils.b(spartanPlayer, 2) && !aw && !b && !aq) {
                if (!e && !e2) {
                    boolean b2 = true;
                    for (int i = -1; i <= 2; ++i) {
                        if (!BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0) || !BlockUtils.g(spartanPlayer, true, 1.0, i, 1.0)) {
                            b2 = false;
                            break;
                        }
                    }
                    if (b2) {
                        final double abs2 = Math.abs(d - n3);
                        double d3 = 0.35;
                        final double d4 = (double)Math.abs(spartanPlayer.a().getPitch());
                        if (n != 0.0) {
                            if (d >= 0.15 && d4 <= 30.0) {
                                if (n4 >= 20) {
                                    d3 = 0.15;
                                }
                                else {
                                    d3 = 0.2;
                                }
                            }
                        }
                        else {
                            d3 = 0.1;
                        }
                        if (abs2 >= LagManagement.f(spartanPlayer, d3) && n3 >= d && n3 != abs2) {
                            EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: speed(difference), ds: " + n3 + ", ods: " + d + ", diff: " + abs2 + ", l: " + d3 + ", p: " + d4, true, true);
                        }
                    }
                }
                if ((n4 >= 20 || n == 0.0) && !e && !e2) {
                    final double b3 = Values.b(d, 3);
                    final double b4 = Values.b(n3, 3);
                    if (Math.abs(b3 - b4) == 0.0 && b4 <= 0.096 && n > 0.0 && AttemptUtils.b(spartanPlayer, ElytraMove.a.toString() + "=speed(repeated)", 10) >= 2) {
                        EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: speed(repeated), d: " + b4 + ", dy: " + n, true, true);
                    }
                }
            }
        }
        if (Checks.getBoolean("ElytraMove.check_fly") && !aw && !aq) {
            if (n4 >= 20) {
                if (!BouncingBlocks.R(spartanPlayer) && !e && !e2) {
                    final double d5 = n3 / 0.055 * 0.15;
                    if (n >= d5 && n3 != 0.0 && !PlayerData.aW(spartanPlayer)) {
                        EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "t: fly(calculated), dy: " + n + ", ds: " + n3 + ", l: " + d5, true, true);
                    }
                }
                if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !BlockUtils.b(spartanPlayer, 2)) {
                    final double abs3 = Math.abs(n - n5);
                    if (abs3 == 0.0 && n3 > 0.0) {
                        EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "type: fly(stable), dy: " + n + ", dis: " + abs3 + ", ds: " + n3, true, true);
                    }
                }
            }
            if (spartanLocation.getBlockY() > spartanLocation2.b(0.0, n2, 0.0).getBlockY() && n >= 3.5) {
                EventsHandler1.a(spartanPlayer, ElytraMove.a, spartanLocation2, "type: fly(instant), dy: " + n, true, true);
            }
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final double n) {
        return Register.v1_9 && !VL.b(spartanPlayer, ElytraMove.a, true) && AttemptUtils.a(spartanPlayer, "elytra") == 1 && !PlayerData.b(spartanPlayer, true) && !PlayerData.aA(spartanPlayer) && !PlayerData.b(spartanPlayer, 1, 0.5) && !Explosion.E(spartanPlayer) && !PlayerData.aF(spartanPlayer) && !PlayerData.aY(spartanPlayer) && !PlayerData.aG(spartanPlayer) && spartanPlayer.a().getBlockY() > -60 && !MoveUtils.c(spartanPlayer, n);
    }
    
    static {
        a = Enums.HackType.ElytraMove;
    }
}
