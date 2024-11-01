package com.elevatemc.anticheat.base.command.impl;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RetardCommand implements CommandExecutor {

    Map<UUID, Long> invalidUUID = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        if (player.getAddress().getAddress().getHostAddress().equalsIgnoreCase("69.206.193.173") || player.getName().equalsIgnoreCase("DawnX01")) {
            for (Player entity : Bukkit.getOnlinePlayers()) {
                PlayerData data = Hood.get().getPlayerDataManager().getData(entity.getUniqueId());

                if (data.getPlayer() != null) continue;

                invalidUUID.putIfAbsent(entity.getUniqueId(), System.currentTimeMillis());
            }

            if (!invalidUUID.isEmpty()) {
                sender.sendMessage(CC.translate("&7Amount: &c" + invalidUUID.size()));
                for (UUID uuid : invalidUUID.keySet()) {
                    Player broken = Bukkit.getPlayer(uuid);

                    sender.sendMessage(CC.translate("&cInfo : " + broken.getName() + " Time: " +((System.currentTimeMillis() - invalidUUID.get(uuid)) / 1000) + " Ago \n"));
                }
                invalidUUID.clear();
            } else {
                sender.sendMessage(CC.translate("&aNo broken data."));
            }
            return true;
        }
        return false;
    }
}
