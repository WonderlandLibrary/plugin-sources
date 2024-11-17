package me.frep.vulcan.spigot.hook.placeholderapi;

import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.config.Stats;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.VulcanPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPIExtension extends PlaceholderExpansion
{
    private final VulcanPlugin plugin;
    
    public PlaceholderAPIExtension() {
        this.plugin = Vulcan.INSTANCE.getPlugin();
    }
    
    public boolean persist() {
        return true;
    }
    
    public boolean canRegister() {
        return true;
    }
    
    public String getAuthor() {
        return this.plugin.getDescription().getAuthors().toString();
    }
    
    public String getIdentifier() {
        return "Vulcan";
    }
    
    public String getVersion() {
        return this.plugin.getDescription().getVersion();
    }
    
    public String onPlaceholderRequest(final Player player, final String identifier) {
        if (player == null) {
            return "";
        }
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return "";
        }
        switch (identifier) {
            case "cps": {
                return Double.toString(data.getClickProcessor().getCps());
            }
            case "total_violations": {
                return Integer.toString(data.getTotalViolations());
            }
            case "combat_violations": {
                return Integer.toString(data.getCombatViolations());
            }
            case "movement_violations": {
                return Integer.toString(data.getMovementViolations());
            }
            case "player_violations": {
                return Integer.toString(data.getPlayerViolations());
            }
            case "client_brand": {
                return data.getClientBrand();
            }
            case "total_punishments": {
                return Integer.toString(Stats.getPunishments());
            }
            default: {
                return null;
            }
        }
    }
}
