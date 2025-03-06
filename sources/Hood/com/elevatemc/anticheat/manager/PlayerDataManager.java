package com.elevatemc.anticheat.manager;

import com.elevatemc.anticheat.base.PlayerData;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PlayerDataManager {

    private final Map<User, PlayerData> playerDataMap = new HashMap<>();

    public PlayerData getData(final User user) {
        return playerDataMap.get(user);
    }

    public PlayerData getData(final UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);

        User user = PacketEvents.getAPI().getPlayerManager().getUser(player);

        return playerDataMap.get(user);
    }

    public void registerData(final User user) {
        playerDataMap.put(user, new PlayerData(user));
    }

    public void removeData(final User user) {
        playerDataMap.remove(user);
    }

    public Collection<PlayerData> getAllData() {
        return playerDataMap.values();
    }

}