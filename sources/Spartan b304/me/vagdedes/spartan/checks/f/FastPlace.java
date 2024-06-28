package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.Material;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastPlace
{
    private static final Enums.HackType a;
    
    public FastPlace() {
        super();
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (VL.b(spartanPlayer, FastPlace.a, true) || spartanBlock.getType() == Material.FIRE || PlayerData.aR(spartanPlayer)) {
            return;
        }
        final long a = MillisUtils.a(spartanPlayer, FastPlace.a.toString() + "=time");
        if (MillisUtils.hasTimer(a)) {
            final int integer = Checks.getInteger("FastPlace.cancel_seconds");
            final int n = (integer > 60) ? 60 : integer;
            if (a <= 50L) {
                if (AttemptUtils.b(spartanPlayer, FastPlace.a.toString() + "=fast", 20) >= 5) {
                    new HackPrevention(spartanPlayer, FastPlace.a, "t: fast, ms: " + a + ", b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock), n * 20);
                }
            }
            else if (a <= 100L) {
                if (AttemptUtils.b(spartanPlayer, FastPlace.a.toString() + "=medium", 20) >= 7) {
                    new HackPrevention(spartanPlayer, FastPlace.a, "t: medium, ms: " + a + ", b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock), n * 20);
                }
            }
            else if (a <= 150L && AttemptUtils.b(spartanPlayer, FastPlace.a.toString() + "=slow", 20) >= 8) {
                new HackPrevention(spartanPlayer, FastPlace.a, "t: slow, ms: " + a + ", b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock), n * 20);
            }
        }
        MillisUtils.o(spartanPlayer, FastPlace.a.toString() + "=time");
    }
    
    static {
        a = Enums.HackType.FastPlace;
    }
}
