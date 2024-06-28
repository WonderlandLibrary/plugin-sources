package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.Register;
import org.bukkit.event.player.PlayerLoginEvent;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class LocationCrasher
{
    private static final Enums.HackType a;
    
    public LocationCrasher() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerLoginEvent playerLoginEvent) {
        if (!Register.v1_9 && !VL.b(spartanPlayer, LocationCrasher.a, false) && MoveUtils.e(spartanPlayer.a())) {
            new HackPrevention(spartanPlayer, LocationCrasher.a, "t: illegal SpartanPlayer location, e: login");
            playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, "illegal SpartanPlayer location");
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerTeleportEvent.TeleportCause teleportCause, final SpartanLocation spartanLocation) {
        if (!Register.v1_9 && teleportCause != PlayerTeleportEvent.TeleportCause.UNKNOWN && teleportCause != PlayerTeleportEvent.TeleportCause.END_PORTAL && teleportCause != PlayerTeleportEvent.TeleportCause.ENDER_PEARL && teleportCause != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL && MoveUtils.e(spartanPlayer.a()) && !VL.b(spartanPlayer, LocationCrasher.a, false)) {
            new HackPrevention(spartanPlayer, LocationCrasher.a, "t: illegal player location, e: teleport", spartanLocation);
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
    }
}
