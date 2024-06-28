package me.vagdedes.spartan.checks.combat;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import org.bukkit.entity.Enderman;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.GameMode;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.system.VL;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class HitReach
{
    private static final Enums.HackType a;
    private static final boolean c;
    
    public HitReach() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n) {
        if (VL.b(spartanPlayer, HitReach.a, true) || CombatUtils.a(entity, n) || LatencyUtils.e(spartanPlayer, 300) || (Checks.getBoolean("HitReach.exempt_player_chasing") && MoveUtils.ar(spartanPlayer)) || mcMMO.L(spartanPlayer)) {
            return;
        }
        if (spartanPlayer.getGameMode() != GameMode.ADVENTURE && spartanPlayer.getGameMode() != GameMode.SURVIVAL) {
            return;
        }
        final SpartanLocation spartanLocation = new SpartanLocation(entity.getLocation());
        final double c = MathUtils.c(spartanPlayer.a(), spartanLocation);
        final double n2 = NoHitDelay.E(spartanPlayer) ? 5.5 : a(spartanPlayer, Checks.getDouble("HitReach.vertical_distance"), false);
        final double d = (n2 <= 4.5) ? 4.501 : n2;
        if (c >= d && c <= 6.0 && !BlockUtils.G((entity instanceof Player) ? SpartanBukkit.a(entity.getUniqueId()) : null, spartanLocation)) {
            new HackPrevention(spartanPlayer, HitReach.a, "t: vertical, d: " + c + ", l: " + d);
        }
        else {
            final boolean b = Checks.getBoolean("HitReach.account_speed_effect") && PlayerData.a(spartanPlayer, PotionEffectType.SPEED) > 0 && MoveUtils.k(spartanPlayer) >= 0.35;
            final double b2 = MathUtils.b(spartanPlayer.a(), spartanLocation);
            double d2 = a(spartanPlayer, Checks.getDouble("HitReach.horizontal_distance"), true) + (b ? 0.75 : 0.0);
            final double a = a(spartanPlayer, entity);
            if (d2 < a) {
                d2 = a;
            }
            if (b2 >= d2) {
                final boolean b3 = PlayerData.at(spartanPlayer) || PlayerData.as(spartanPlayer) || (entity instanceof Player && PlayerData.as(SpartanBukkit.a(entity.getUniqueId())));
                if (AttemptUtils.b(spartanPlayer, HitReach.a.toString() + "=horizontal", 100) >= (((VL.a(spartanPlayer, HitReach.a) >= 3 && !HitReach.c) || MoveUtils.i(spartanPlayer) <= 0.1 || b3) ? 2 : 4)) {
                    new HackPrevention(spartanPlayer, HitReach.a, "t: horizontal, d: " + b2 + ", l: " + d2);
                }
            }
        }
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final Entity entity) {
        return (PlayerData.aR(spartanPlayer) || NoHitDelay.E(spartanPlayer)) ? 5.5 : ((entity != null && entity instanceof Enderman) ? 5.0 : (HitReach.c ? 4.2 : 3.6));
    }
    
    private static double a(final SpartanPlayer spartanPlayer, final double n, final boolean b) {
        return n + 0.5 + ((b && LatencyUtils.e(spartanPlayer, 100)) ? LagManagement.t(spartanPlayer) : 0) * 0.1;
    }
    
    static {
        a = Enums.HackType.HitReach;
        c = (VersionUtils.a() == VersionUtils.a.l || VersionUtils.a() == VersionUtils.a.c);
    }
}
