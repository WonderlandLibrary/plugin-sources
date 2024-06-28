package me.vagdedes.spartan.h;

import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanPlayer;

public class WaterSoulSand
{
    private static final int ticks = 60;
    
    public WaterSoulSand() {
        super();
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final double n) {
        if (Register.v1_13 && n > 0.0 && ab(spartanPlayer)) {
            final int blockY = spartanPlayer.a().getBlockY();
            if (a(spartanPlayer, (blockY >= 85) ? 85 : blockY)) {
                g(spartanPlayer, 60);
            }
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "water-soul-sand=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "water-soul-sand=protection", n);
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final int n) {
        for (int i = 0; i <= n; ++i) {
            final SpartanLocation b = spartanPlayer.a().b(0.0, -i, 0.0);
            if (BlockUtils.f(spartanPlayer, b)) {
                return b.a().getType() == Material.SOUL_SAND;
            }
        }
        return false;
    }
    
    public static boolean ab(final SpartanPlayer spartanPlayer) {
        for (int i = -1; i <= 2; ++i) {
            if (BlockUtils.a(spartanPlayer, spartanPlayer.a().b(0.0, (double)i, 0.0), Material.BUBBLE_COLUMN, 0.3)) {
                return true;
            }
        }
        return false;
    }
}
