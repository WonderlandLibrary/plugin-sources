package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import lombok.Getter;
import org.bukkit.util.NumberConversions;

@Getter
public class WorldTracker extends Tracker {

    public WorldTracker(PlayerData playerData) {
        super(playerData);
    }

    public boolean isChunkLoaded(double x, double z) {
        return playerData.getPlayer().getWorld().isChunkLoaded(NumberConversions.floor(x) >> 4, NumberConversions.floor(z) >> 4);
    }
}