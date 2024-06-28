package me.vagdedes.spartan.checks.combat;

import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class Criticals
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, Double> n;
    
    public Criticals() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        Criticals.n.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        Criticals.n.clear();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (b(spartanPlayer)) {
            c(spartanPlayer);
            a(spartanPlayer);
            d(spartanPlayer);
        }
    }
    
    private static void c(final SpartanPlayer spartanPlayer) {
        if (spartanPlayer.isOnGround() || spartanPlayer.hasPotionEffect(PotionEffectType.BLINDNESS) || spartanPlayer.getFallDistance() == 0.0f || !Checks.getBoolean("Criticals.check_location")) {
            return;
        }
        if (spartanPlayer.a().getY() % 1.0 == 0.0 && AttemptUtils.b(spartanPlayer, Criticals.a.toString() + "=location=attempts", 150) >= 2) {
            new HackPrevention(spartanPlayer, Criticals.a, "t: location");
        }
    }
    
    private static void d(final SpartanPlayer spartanPlayer) {
        if (!a(spartanPlayer, 1.0) || !Checks.getBoolean("Criticals.check_mini_jump")) {
            return;
        }
        final float fallDistance = spartanPlayer.getFallDistance();
        if (fallDistance > 0.0f && fallDistance <= 0.07f && (PlayerData.i(spartanPlayer, 0.0, -fallDistance, 0.0) || PlayerData.i(spartanPlayer, 0.0, -(fallDistance + 0.1), 0.0))) {
            new HackPrevention(spartanPlayer, Criticals.a, "t: mini-jump, f: " + fallDistance);
        }
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer) {
        if ((spartanPlayer.isOnGround() && PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) || !a(spartanPlayer, 0.3) || PunishUtils.bg(spartanPlayer) || PlayerData.a(spartanPlayer, PotionEffectType.JUMP) > 250 || !Checks.getBoolean("Criticals.check_position")) {
            return false;
        }
        boolean b = false;
        final double n = spartanPlayer.a().getY() - spartanPlayer.a().getBlockY();
        if (PlayerData.q(spartanPlayer) >= 20 && n >= 0.15 && n <= 0.75 && Criticals.n.containsKey(spartanPlayer.getUniqueId()) && Double.valueOf(Criticals.n.get((Object)spartanPlayer.getUniqueId())) == n) {
            new HackPrevention(spartanPlayer, Criticals.a, "t: position, r: " + n);
            b = true;
        }
        Criticals.n.put(spartanPlayer.getUniqueId(), Double.valueOf(n));
        return b;
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final double n) {
        if (!CooldownUtils.g(spartanPlayer, Criticals.a.toString() + "=block_free=" + n)) {
            return false;
        }
        int n2 = 40;
        if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            n2 = 60;
        }
        for (int i = 0; i <= 2; ++i) {
            if (!BlockUtils.c(spartanPlayer, true, n, i, n)) {
                CooldownUtils.d(spartanPlayer, Criticals.a.toString() + "=block_free=" + n, n2);
                return false;
            }
        }
        return true;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        for (int i = -1; i <= 2; ++i) {
            if (!BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0)) {
                return false;
            }
        }
        return !VL.b(spartanPlayer, Criticals.a, true) && !PlayerData.b(spartanPlayer, true) && !PlayerData.aR(spartanPlayer) && !BouncingBlocks.R(spartanPlayer) && (BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0)) || (!BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0)) && BlockUtils.f(spartanPlayer, spartanPlayer.a().b(0.0, -2.0, 0.0)))) && !LatencyUtils.e(spartanPlayer, 200);
    }
    
    static {
        a = Enums.HackType.Criticals;
        n = new HashMap<UUID, Double>();
    }
}
