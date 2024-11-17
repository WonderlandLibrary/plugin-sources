package me.frep.vulcan.spigot.command;

import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.command.CommandSender;

public class CommandManager
{
    public void sendMessage(final CommandSender sender, final String string) {
        sender.sendMessage(ColorUtil.translate(string.replaceAll("%prefix%", ColorUtil.translate(Config.PREFIX))));
    }
    
    public void sendLine(final CommandSender sender) {
        this.sendMessage(sender, "&7&m---»--*-------------------------------------*--«---");
    }
}
