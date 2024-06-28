package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;

public class BlockBreak
{
    private static final long k = 205L;
    
    public BlockBreak() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (action == Action.LEFT_CLICK_BLOCK && spartanBlock != null) {
            MillisUtils.o(spartanPlayer, "block-break=click");
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final boolean b) {
        CooldownUtils.d(spartanPlayer, "block-break=action", 2);
        if (MillisUtils.a(spartanPlayer, "block-break=click") <= 205L || MillisUtils.a(spartanPlayer, "block-break=repeat") <= 205L) {
            CooldownUtils.d(spartanPlayer, "block-break=protection", b ? 20 : 40);
        }
        MillisUtils.o(spartanPlayer, "block-break=repeat");
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return P(spartanPlayer) | !CooldownUtils.g(spartanPlayer, "block-break=protection");
    }
    
    public static boolean P(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "block-break=action");
    }
    
    public static boolean Q(final SpartanPlayer spartanPlayer) {
        return MillisUtils.a(spartanPlayer, "block-break=click") <= 205L;
    }
}
