package me.frep.vulcan.spigot.listener;

import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener
{
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        if (data.getPositionProcessor().isFrozen()) {
            Vulcan.INSTANCE.getAlertManager().sendMessage(Config.LOGGED_OUT_WHILE_FROZEN.replaceAll("%player%", player.getName()));
            Config.LOGGED_OUT_FROZEN_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(ColorUtil.translate(command.replaceAll("%player%", player.getName()))));
        }
        Vulcan.INSTANCE.getGuiManager().removeFromLists(player);
        Vulcan.INSTANCE.getAlertManager().getAlertsEnabled().remove(player);
        Vulcan.INSTANCE.getAlertManager().getVerboseEnabled().remove(player);
        Vulcan.INSTANCE.getPlayerDataManager().remove(player);
    }
}
