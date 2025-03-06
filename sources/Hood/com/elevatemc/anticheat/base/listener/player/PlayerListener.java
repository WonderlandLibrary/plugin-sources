package com.elevatemc.anticheat.base.listener.player;

import com.destroystokyo.paper.event.server.ServerTickStartEvent;
import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.spigot.event.PostEntityTrackerEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        HoodPlugin.INSTANCE.getPlayerDataManager().getData(event.getPlayer().getUniqueId()).initialize(event.getPlayer());
    }

    @EventHandler
    public void onPostEntityTracker(final PostEntityTrackerEvent event) {
        for (Player entity : event.getPlayers()) {
            PlayerData data = HoodPlugin.INSTANCE.getPlayerDataManager().getData(entity.getUniqueId());
            if (data == null) return;
            if (data.getTicksExisted() < 50) return;
            if (data.getPlayer() == null) {
                Bukkit.getScheduler().runTask(Hood.get(), () -> entity.kickPlayer("Failed to initialize your data."));
                return;
            }
           data.handlePreFlush();
        }
    }

    @EventHandler
    public void onTickStart(final ServerTickStartEvent event) {
        Hood.getPlayerDataManager().getPlayerDataMap().forEach((uuid, data) -> data.handleTickStart());
    }
}
