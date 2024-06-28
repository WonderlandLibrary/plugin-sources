package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class BlockPlace
{
    private static final long k = 205L;
    
    public BlockPlace() {
        super();
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final boolean b) {
        if (MillisUtils.a(spartanPlayer, "block-place=repeat") <= 205L) {
            CooldownUtils.d(spartanPlayer, "block-place=protection", b ? 20 : 40);
        }
        MillisUtils.o(spartanPlayer, "block-place=repeat");
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "block-place=protection");
    }
}
