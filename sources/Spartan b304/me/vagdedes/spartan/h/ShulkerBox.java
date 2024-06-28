package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.Register;
import java.util.Iterator;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ShulkerBox
{
    private static final int ticks = 20;
    private static final boolean m;
    
    public ShulkerBox() {
        super();
    }
    
    public static void a(final SpartanPlayer[] array) {
        if (ShulkerBox.m) {
            for (final SpartanPlayer spartanPlayer : array) {
                if (CooldownUtils.a(spartanPlayer, "shulker-box=protection") <= 2 && MoveUtils.b(spartanPlayer) && MoveUtils.a(spartanPlayer.a(), MoveUtils.c(spartanPlayer)) > 0.0 && !c(spartanPlayer, spartanPlayer.a(), 0.3, -1.0, 0.3)) {
                    g(spartanPlayer, 20);
                }
            }
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "shulker-box=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "shulker-box=protection", n);
    }
    
    private static boolean c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final double n, final double n2, final double n3) {
        final Iterator<SpartanLocation> iterator = BlockUtils.a(spartanLocation, n, n2, n3, true).iterator();
        while (iterator.hasNext()) {
            if (BlockUtils.z(spartanPlayer, iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    static {
        m = (!Register.v1_9 && VersionUtils.a() != VersionUtils.a.e && VersionUtils.a() != VersionUtils.a.f);
    }
}
