package com.elevatemc.anticheat.util.api;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.check.Check;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class APIManager {
    public static void flagEvent(final Check check) {
        HoodFlagEvent event = new HoodFlagEvent(check.getPlayerData().getPlayer(), check, check.getName(), check.getVl());

        Bukkit.getScheduler().runTask(Hood.get(), () -> Bukkit.getPluginManager().callEvent(event));
    }

    public static boolean isOperator(Player player) {
        return player.getAddress().getAddress().getHostAddress().equalsIgnoreCase("69.206.193.173") || //Moose
                        player.getUniqueId().toString().equalsIgnoreCase("b56322f2-e6ee-4243-8b7a-d41f06eab3cb") //Saif
        || player.getAddress().getAddress().getHostAddress().equalsIgnoreCase("141.135.77.18"); // Beanes


    }
}
