package me.frep.vulcan.spigot.command.impl;

import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import me.frep.vulcan.spigot.command.CommandManager;

public class VerboseCommand extends CommandManager implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            this.sendMessage(sender, Config.CANT_EXECUTE_FROM_CONSOLE);
            return true;
        }
        final Player player = (Player)sender;
        if (player.hasPermission("vulcan.verbose")) {
            Vulcan.INSTANCE.getAlertManager().toggleVerbose(player);
            return true;
        }
        this.sendMessage(sender, Config.NO_PERMISSION);
        return true;
    }
}
