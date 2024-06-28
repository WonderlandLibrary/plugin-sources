package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.f.LatencyUtils;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Direction
{
    private static final Enums.HackType a;
    
    public Direction() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 12 || LatencyUtils.e(spartanPlayer, 300) || !Checks.getBoolean("KillAura.check_direction") || !(entity instanceof Player) || n < 1.5 || PlayerData.ax(spartanPlayer)) {
            return;
        }
        final double f = CombatUtils.f(spartanPlayer, entity);
        final String string = Direction.a.toString() + "=direction=" + entity.getUniqueId();
        final long a = MillisUtils.a(spartanPlayer, string);
        if ((!MillisUtils.hasTimer(a) || a >= 1000L) && f >= (NoHitDelay.E(spartanPlayer) ? 4.0 : 2.0)) {
            final String string2 = Direction.a.toString() + "=direction=violation";
            final int i = (int)(MillisUtils.a(spartanPlayer, string2) / 50L);
            final int j = (VL.a(spartanPlayer, Direction.a) >= 2) ? 200 : 100;
            if (i >= 11 && i <= j && AttemptUtils.b(spartanPlayer, Direction.a.toString() + "=direction", 1200) >= (PlayerData.aw(spartanPlayer) ? 5 : 2)) {
                new HackPrevention(spartanPlayer, Direction.a, "t: direction, d: " + f + ", t: " + i + ", l: " + j);
            }
            MillisUtils.o(spartanPlayer, string2);
        }
        MillisUtils.o(spartanPlayer, string);
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
