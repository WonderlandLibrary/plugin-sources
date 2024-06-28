package me.vagdedes.spartan.k.a.a;

import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class PlayerSwim
{
    public PlayerSwim() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final boolean b) {
        if (b) {
            AttemptUtils.c(spartanPlayer, "swimming", 1);
        }
        else {
            AttemptUtils.m(spartanPlayer, "swimming");
        }
        CooldownUtils.d(spartanPlayer, "swimming", 10);
    }
}
