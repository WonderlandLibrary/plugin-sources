package dev.coldservices.command.impl;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import dev.coldservices.CAC;
import dev.coldservices.alert.AlertManager;
import dev.coldservices.command.NiceCommand;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.PlayerDataManager;
import dev.coldservices.util.string.ChatUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("cac")
public class AlertsCommand extends NiceCommand {

    @Subcommand("alerts")
    @Description("Toggles your anti-cheat alerts")
    @CommandPermission("coldac.alerts")
    public void onCommand(final CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players.");
            return;
        }

        final Player player = ((Player) sender).getPlayer();
        final PlayerData data = CAC.get(PlayerDataManager.class).getPlayerData(player.getUniqueId());

        if (CAC.get(AlertManager.class).toggleAlerts(data)) {
            player.sendMessage(ChatUtil.translate("&aYou are now viewing alerts!"));
        } else {
            player.sendMessage(ChatUtil.translate("&cYou are no longer viewing alerts!"));
        }
    }
}
