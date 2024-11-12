package dev.coldservices.command.impl;

import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import dev.coldservices.CAC;
import dev.coldservices.command.NiceCommand;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@CommandAlias("cac")
public class InfoCommand extends NiceCommand {

    @Subcommand("info")
    @Description("View information about a player")
    @CommandPermission("coldac.info")
    public void onCommand(final CommandSender sender, @Name("target") final OnlinePlayer onlinePlayer) {
        final Player player = onlinePlayer.getPlayer();
        final PlayerData data = CAC.get(PlayerDataManager.class).getPlayerData(player.getUniqueId());

        final String lastTransaction = new SimpleDateFormat("hh:mm:ss:ms aaa").format(data.getConnectionTracker().getLastTransaction());
        final String lastEntity = data.getActionTracker().getTarget() == null ? "None" : data.getActionTracker().getTarget().getName();

        final List<String> message = Arrays.asList(
                "&cInformation for &f" + player.getName() + "&c:",
                " &f- &cEnabled Checks: &7" + data.getChecks().size(),
                " &f- &cTracked Entities: &7" + data.getEntityTracker().getTrackerMap().size(),
                " &f- &cLast Transaction Received: &7" + lastTransaction,
                " &f- &cLast Target: &7" + lastEntity,
                " &f- &cSensitivity: &7" + data.getRotationTracker().getSensitivity() + "%",
                " &f- &cTransaction Ping: &7" + data.getConnectionTracker().getTransactionPing() + "ms",
                " &f- &cKeep Alive Ping: &7" + data.getConnectionTracker().getKeepAlivePing() + "ms"
        );

        sendLineBreak(sender);
        sendMessage(sender, String.join("\n", message));
        sendLineBreak(sender);
    }
}
