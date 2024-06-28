package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.h.WaterSoulSand;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Liquid
{
    public static final double value = 0.581;
    
    public Liquid() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2, final double n) {
        final boolean g = BlockUtils.g(spartanLocation2.a().getType());
        final boolean g2 = BlockUtils.g(spartanLocation.a().getType());
        if (MoveUtils.c(spartanPlayer, n) || g || g2) {
            MillisUtils.o(spartanPlayer, "liquid-protection=ms");
            if (g2) {
                return;
            }
        }
        boolean b = false;
        if (g) {
            CooldownUtils.d(spartanPlayer, "liquid-protection=from", 30);
        }
        if (g && !BouncingBlocks.R(spartanPlayer) && !PlayerData.az(spartanPlayer) && n > f(spartanPlayer)) {
            b = true;
        }
        else if (!CooldownUtils.g(spartanPlayer, "liquid-protection=from") && spartanPlayer.getFireTicks() <= 0 && (spartanPlayer.isOnGround() || PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -0.33, 0.0)) && BlockUtils.a(spartanPlayer, true, 0.3, 0.0, 0.3) && BlockUtils.a(spartanPlayer, true, 0.3, 1.0, 0.3) && AttemptUtils.b(spartanPlayer, "liquid-protection=attempts", 30) >= 5) {
            b = true;
        }
        if (b) {
            CooldownUtils.n(spartanPlayer, "=liquid");
        }
    }
    
    public static double f(final SpartanPlayer spartanPlayer) {
        return PlayerData.b(spartanPlayer, true) ? 1.2 : (WaterSoulSand.E(spartanPlayer) ? 0.8 : 0.581);
    }
    
    public static long e(final SpartanPlayer spartanPlayer) {
        return MillisUtils.a(spartanPlayer, "liquid-protection=ms");
    }
}
