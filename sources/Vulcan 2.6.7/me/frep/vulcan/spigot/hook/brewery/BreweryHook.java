package me.frep.vulcan.spigot.hook.brewery;

import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.Vulcan;
import com.dre.brewery.api.events.PlayerPushEvent;
import org.bukkit.event.Listener;

public class BreweryHook implements Listener
{
    @EventHandler
    public void onPush(final PlayerPushEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        data.getActionProcessor().setSincePushTicks(0);
    }
}
