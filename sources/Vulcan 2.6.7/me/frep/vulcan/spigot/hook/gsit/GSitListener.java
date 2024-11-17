package me.frep.vulcan.spigot.hook.gsit;

import dev.geco.gsit.api.event.PlayerGetUpCrawlEvent;
import dev.geco.gsit.api.event.PlayerCrawlEvent;
import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.event.Listener;

public class GSitListener implements Listener
{
    @EventHandler
    public void onCrawl(final PlayerCrawlEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        data.getActionProcessor().setCrawling(true);
    }
    
    @EventHandler
    public void onCrawl(final PlayerGetUpCrawlEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        data.getActionProcessor().setCrawling(false);
    }
}
