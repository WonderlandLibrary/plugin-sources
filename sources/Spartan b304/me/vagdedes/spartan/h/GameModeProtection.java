package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class GameModeProtection
{
    private static final int ticks = 60;
    
    public GameModeProtection() {
        super();
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "gamemode=protection", n);
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        g(spartanPlayer, 60);
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "gamemode=protection");
    }
}
