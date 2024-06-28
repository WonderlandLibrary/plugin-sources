package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanPlayer;

public class DeathAndRespawn
{
    public static final int ticks = 10;
    
    public DeathAndRespawn() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        g(spartanPlayer, 10);
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        if (PlayerData.ba(spartanPlayer)) {
            b(spartanPlayer);
            return true;
        }
        if (spartanPlayer != null) {
            spartanPlayer.e(false);
            spartanPlayer.setHealth(spartanPlayer.getPlayer().getHealth());
        }
        return !CooldownUtils.g(spartanPlayer, "death=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "death=protection", n);
    }
}
