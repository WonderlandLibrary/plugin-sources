package me.frep.vulcan.spigot.event;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.event.player.PlayerRiptideEvent;
import org.bukkit.event.Listener;

public class EventProcessor1_13 implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onRiptide(final PlayerRiptideEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        data.getPositionProcessor().setSinceRiptideTicks(0);
    }
}
