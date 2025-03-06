package dev.coldservices.listener.bukkit;

import dev.coldservices.CAC;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.PlayerDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class RegistrationListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        CAC.get(PlayerDataManager.class).add(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeave(final PlayerQuitEvent event) {
        final PlayerData data = CAC.get(PlayerDataManager.class).getPlayerData(event.getPlayer().getUniqueId());

        if (data == null) return;

        data.terminate();

        CAC.get(PlayerDataManager.class).remove(event.getPlayer());
    }
}
