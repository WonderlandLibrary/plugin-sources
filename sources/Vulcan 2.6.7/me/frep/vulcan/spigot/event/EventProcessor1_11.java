package me.frep.vulcan.spigot.event;

import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.Listener;

public class EventProcessor1_11 implements Listener
{
    @EventHandler(ignoreCancelled = true)
    public void onResurrect(final EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player)event.getEntity();
            final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
            if (data == null) {
                return;
            }
            data.getActionProcessor().handleResurrect();
        }
    }
}
