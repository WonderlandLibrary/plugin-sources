package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanPlayer;

public class BouncingBlocks
{
    private static final int P = 60;
    private static final int Q = 120;
    private static final int n = 3;
    
    public BouncingBlocks() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final double n, final double n2) {
        if (!Piston.E(spartanPlayer) && PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            if (!BlockUtils.b(spartanPlayer, 1)) {
                j(spartanPlayer, "slime");
            }
            if (!BlockUtils.c(spartanPlayer, 1)) {
                j(spartanPlayer, "bed");
            }
        }
        if (n >= 0.0 && n2 >= 0.4 && !PlayerData.aw(spartanPlayer) && !PlayerData.az(spartanPlayer)) {
            j(spartanPlayer, "slime");
            j(spartanPlayer, "bed");
        }
        else if (CooldownUtils.g(spartanPlayer, "bouncing-blocks=cooldown")) {
            final int blockY = spartanPlayer.a().getBlockY();
            if (BlockUtils.b(spartanPlayer, (blockY >= 55) ? 55 : blockY)) {
                d(spartanPlayer, "slime");
            }
            if (BlockUtils.c(spartanPlayer, (blockY >= 25) ? 25 : blockY)) {
                d(spartanPlayer, "bed");
            }
            CooldownUtils.d(spartanPlayer, "bouncing-blocks=cooldown", 3);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        j(spartanPlayer, "slime");
        j(spartanPlayer, "bed");
    }
    
    private static void j(final SpartanPlayer spartanPlayer, final String s) {
        CooldownUtils.j(spartanPlayer, "bouncing-blocks=" + s + "=is");
        CooldownUtils.j(spartanPlayer, "bouncing-blocks=" + s + "=was");
    }
    
    private static void d(final SpartanPlayer spartanPlayer, final String s) {
        CooldownUtils.d(spartanPlayer, "bouncing-blocks=" + s + "=is", 60);
        CooldownUtils.d(spartanPlayer, "bouncing-blocks=" + s + "=was", 120);
    }
    
    public static boolean R(final SpartanPlayer spartanPlayer) {
        return T(spartanPlayer) || V(spartanPlayer);
    }
    
    public static boolean S(final SpartanPlayer spartanPlayer) {
        return U(spartanPlayer) || W(spartanPlayer);
    }
    
    public static boolean T(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "bouncing-blocks=slime=is") || BlockUtils.b(spartanPlayer, 2);
    }
    
    public static boolean U(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "bouncing-blocks=slime=was") || T(spartanPlayer);
    }
    
    public static boolean V(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "bouncing-blocks=bed=is") || BlockUtils.c(spartanPlayer, 2);
    }
    
    public static boolean W(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "bouncing-blocks=bed=was") || R(spartanPlayer);
    }
}
