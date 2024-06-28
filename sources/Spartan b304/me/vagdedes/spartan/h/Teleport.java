package me.vagdedes.spartan.h;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Teleport
{
    public static final int ticks = 3;
    
    public Teleport() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerTeleportEvent.TeleportCause teleportCause, final double n) {
        final boolean b = spartanPlayer.a().getY() <= 0.0 && teleportCause != PlayerTeleportEvent.TeleportCause.UNKNOWN;
        if (!b && !Settings.canDo("use_teleport_protection")) {
            return;
        }
        if (!PunishUtils.bg(spartanPlayer)) {
            CooldownUtils.d(spartanPlayer, Enums.HackType.Clip.toString() + "=teleport-protection", 3);
        }
        if (!b && (teleportCause == PlayerTeleportEvent.TeleportCause.ENDER_PEARL || teleportCause == PlayerTeleportEvent.TeleportCause.COMMAND || (teleportCause == PlayerTeleportEvent.TeleportCause.PLUGIN && n >= 0.5))) {
            return;
        }
        final long a = MillisUtils.a(spartanPlayer, "teleport");
        final boolean b2 = MillisUtils.hasTimer(a) && ((a >= 200L && a <= 1000L) || a == 0L);
        final boolean b3 = !CooldownUtils.g(spartanPlayer, "teleport=frequent");
        if (b2) {
            CooldownUtils.d(spartanPlayer, "teleport=frequent", 2);
        }
        final boolean b4 = b2 || b3;
        if ((CooldownUtils.g(spartanPlayer, "teleport") && !PunishUtils.bh(spartanPlayer) && n >= 0.05) || (b4 && VL.o(spartanPlayer) <= 5)) {
            CooldownUtils.d(spartanPlayer, "teleport=protection", b4 ? 9 : 3);
        }
        MillisUtils.o(spartanPlayer, "teleport");
    }
    
    public static void E(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "teleport", 1);
    }
    
    public static void k(final SpartanPlayer spartanPlayer, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.contains("~") && (lowerCase.startsWith("/teleport ") || lowerCase.startsWith("/tp "))) {
            CooldownUtils.d(spartanPlayer, "teleport=protection=unknown-command", 2);
        }
    }
    
    public static boolean aa(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "teleport=protection=unknown-command");
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "teleport=protection");
    }
}
