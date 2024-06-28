package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class HitRatio
{
    public static final double f = 35.0;
    
    public HitRatio() {
        super();
    }
    
    private static double a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        return AttemptUtils.a(spartanPlayer, "hit-ratio=" + spartanPlayer2.getName() + "=dealt");
    }
    
    private static double b(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final int a = AttemptUtils.a(spartanPlayer, "hit-ratio=" + spartanPlayer2.getName() + "=dealt");
        return Double.valueOf(a) / Double.valueOf(a + AttemptUtils.a(spartanPlayer, "hit-ratio=" + spartanPlayer2.getName() + "=received")) * 100.0;
    }
    
    public static double c(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        return a(spartanPlayer, spartanPlayer2, false) ? (b(spartanPlayer, spartanPlayer2) - b(spartanPlayer2, spartanPlayer)) : 0.0;
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2, final boolean b) {
        return PlayerData.au(spartanPlayer) && PlayerData.au(spartanPlayer2) && (b || (PlayerData.av(spartanPlayer) && PlayerData.av(spartanPlayer2)) || (a(spartanPlayer, spartanPlayer2) >= 3.0 && a(spartanPlayer2, spartanPlayer) >= 3.0));
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (CombatUtils.ah(spartanPlayer) || !a(spartanPlayer, a, true)) {
                return;
            }
            AttemptUtils.c(spartanPlayer, "hit-ratio=" + a.getName() + "=dealt", AttemptUtils.a(spartanPlayer, "hit-ratio=" + a.getName() + "=dealt") + 1);
            AttemptUtils.c(a, "hit-ratio=" + spartanPlayer.getName() + "=received", AttemptUtils.a(a, "hit-ratio=" + spartanPlayer.getName() + "=received") + 1);
        }
    }
}
