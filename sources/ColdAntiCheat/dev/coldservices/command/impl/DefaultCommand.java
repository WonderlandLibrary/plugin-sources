package dev.coldservices.command.impl;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import dev.coldservices.command.NiceCommand;
import org.bukkit.command.CommandSender;

@CommandAlias("cac")
public class DefaultCommand extends NiceCommand {

    @HelpCommand
    @Description("ColdAC help command")
    @CommandPermission("coldac.command")
    @Syntax("")
    public void onHelp(final CommandSender sender, final CommandHelp commandHelp) {
        sendLineBreak(sender);

        sendMessage(sender, "&cCold Anticheat commands:");

        // whoever coded this fix this monstrosity. or ur fired
        commandHelp.getHelpEntries().forEach(command -> sendMessage(sender, " &f- &c" + command.getCommandPrefix() + command.getCommand() + " &7" + command.getParameterSyntax() + " &8- &c" + command.getDescription()));

        sendMessage(sender, String.format("&7Total of &c%s &7commands.", commandHelp.getHelpEntries().size()));
        sendLineBreak(sender);
    }
}
