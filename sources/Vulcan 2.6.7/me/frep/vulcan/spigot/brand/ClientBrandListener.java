package me.frep.vulcan.spigot.brand;

import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class ClientBrandListener implements PluginMessageListener
{
    public void onPluginMessageReceived(final String channel, final Player player, final byte[] msg) {
        try {
            final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
            if (data == null || channel == null || msg == null || data.isHasSentClientBrand()) {
                return;
            }
            Vulcan.INSTANCE.getClientBrandManager().handle(data, msg);
        }
        catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
