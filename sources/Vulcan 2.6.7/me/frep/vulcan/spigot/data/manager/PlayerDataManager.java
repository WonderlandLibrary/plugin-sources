package me.frep.vulcan.spigot.data.manager;

import java.util.Collection;
import org.bukkit.entity.Player;
import com.google.common.collect.Maps;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class PlayerDataManager
{
    private final Map<UUID, PlayerData> playerDataMap;
    
    public PlayerDataManager() {
        this.playerDataMap = new ConcurrentHashMap<>();
    }
    
    public PlayerData getPlayerData(final Player player) {
        return this.playerDataMap.get(player.getUniqueId());
    }
    
    public void add(final Player player) {
        this.playerDataMap.put(player.getUniqueId(), new PlayerData(player));
    }
    
    public void remove(final Player player) {
        this.playerDataMap.remove(player.getUniqueId());
    }
    
    public Collection<PlayerData> getAllData() {
        return this.playerDataMap.values();
    }
}
