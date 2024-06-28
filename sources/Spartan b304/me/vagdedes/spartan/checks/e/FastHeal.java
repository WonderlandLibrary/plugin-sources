package me.vagdedes.spartan.checks.e;

import org.bukkit.Difficulty;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.g.DoubleUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastHeal
{
    private static final Enums.HackType a;
    
    public FastHeal() {
        super();
    }
    
    private static long c(final SpartanPlayer spartanPlayer) {
        final boolean hasPotionEffect = spartanPlayer.hasPotionEffect(PotionEffectType.REGENERATION);
        long n = -1L;
        if (hasPotionEffect && spartanPlayer.getFireTicks() <= 0) {
            final int a = PlayerData.a(spartanPlayer, PotionEffectType.REGENERATION);
            if (a <= 9) {
                n = ((a == 0) ? 300L : 45L);
            }
        }
        else if (!hasPotionEffect) {
            if (VersionUtils.a() == VersionUtils.a.l || VersionUtils.a() == VersionUtils.a.c || VersionUtils.a() == VersionUtils.a.d) {
                n = 3700L;
            }
            else {
                n = 450L;
            }
        }
        return n;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final EntityRegainHealthEvent.RegainReason regainReason) {
        if (!b(spartanPlayer) || regainReason == EntityRegainHealthEvent.RegainReason.CUSTOM || regainReason == EntityRegainHealthEvent.RegainReason.MAGIC) {
            return;
        }
        if (Checks.getBoolean("FastHeal.check_illegal") && spartanPlayer.getFoodLevel() <= 17 && !spartanPlayer.hasPotionEffect(PotionEffectType.REGENERATION)) {
            new HackPrevention(spartanPlayer, FastHeal.a, "t: illegal, fl: " + spartanPlayer.getFoodLevel());
            return;
        }
        final double health = spartanPlayer.getHealth();
        if (Checks.getBoolean("FastHeal.check_unusual")) {
            final double a = DoubleUtils.a(spartanPlayer, FastHeal.a.toString() + "=hp");
            final long a2 = MillisUtils.a(spartanPlayer, FastHeal.a.toString() + "=time");
            if (MillisUtils.hasTimer(a2) && DoubleUtils.h(a) && a != health && !LatencyUtils.e(spartanPlayer, 100)) {
                final long c = c(spartanPlayer);
                if (a2 < c && AttemptUtils.b(spartanPlayer, FastHeal.a.toString() + "=attempts", 10) >= 3) {
                    new HackPrevention(spartanPlayer, FastHeal.a, "t: unusual, ms: " + a2 + ", l: " + c);
                }
            }
        }
        MillisUtils.o(spartanPlayer, FastHeal.a.toString() + "=time");
        DoubleUtils.a(spartanPlayer, FastHeal.a.toString() + "=hp", health);
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, FastHeal.a, true) && spartanPlayer.getWorld().getDifficulty() != Difficulty.PEACEFUL;
    }
    
    static {
        a = Enums.HackType.FastHeal;
    }
}
