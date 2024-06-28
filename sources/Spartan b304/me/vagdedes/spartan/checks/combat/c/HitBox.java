package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.checks.combat.b.HitRatio;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class HitBox
{
    private static final Enums.HackType a;
    
    public HitBox() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double d, final int n) {
        if (!Checks.getBoolean("KillAura.check_hitbox") || n > 5 || d < 1.0 || LatencyUtils.e(spartanPlayer, 250) || (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -0.25, 0.0))) {
            return;
        }
        int i = 0;
        final double d2 = (double)Math.abs(spartanPlayer.a().getPitch());
        final boolean e = NoHitDelay.E(spartanPlayer);
        if (d >= 1.0 && d2 >= 80.0) {
            i = 1;
        }
        else if (d >= 1.5 && d2 >= 70.0) {
            i = 2;
        }
        else if (!PlayerData.aE(spartanPlayer)) {
            if (d >= 2.0 && d2 >= 60.0) {
                i = 3;
            }
            else if (d >= 2.5 && d2 >= 55.0) {
                i = 4;
            }
            else if (d >= 3.0 && d2 >= 45.0) {
                i = 5;
            }
            else if (d >= 3.5 && d2 >= 35.0) {
                i = 6;
            }
        }
        if (i != 0 && (!e || i <= 4)) {
            KillAuraOverall.o(spartanPlayer);
            final double n2 = (entity instanceof Player) ? HitRatio.c(spartanPlayer, SpartanBukkit.a(entity.getUniqueId())) : 0.0;
            if (AttemptUtils.b(spartanPlayer, HitBox.a.toString() + "=hitbox", 300) >= (e ? 5 : ((n2 >= 35.0) ? 1 : 2))) {
                new HackPrevention(spartanPlayer, HitBox.a, "t: hit-box, c: " + i + ", p: " + d2 + ", d: " + d);
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
