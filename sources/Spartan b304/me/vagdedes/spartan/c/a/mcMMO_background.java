package me.vagdedes.spartan.c.a;

import com.gmail.nossr50.api.AbilityAPI;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;

public class mcMMO_background
{
    public mcMMO_background() {
        super();
    }
    
    public static boolean H(final SpartanPlayer spartanPlayer) {
        try {
            return AbilityAPI.isAnyAbilityEnabled(Bukkit.getPlayer(spartanPlayer.getUniqueId()));
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean I(final SpartanPlayer spartanPlayer) {
        try {
            return AbilityAPI.treeFellerEnabled(Bukkit.getPlayer(spartanPlayer.getUniqueId()));
        }
        catch (Exception ex) {
            return false;
        }
    }
}
