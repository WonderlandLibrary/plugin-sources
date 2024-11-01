package com.elevatemc.anticheat.base.command.impl;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.violation.log.BanwaveLog;
import com.elevatemc.anticheat.manager.BanwaveManager;
import com.elevatemc.anticheat.util.api.APIManager;
import com.elevatemc.anticheat.util.chat.CC;
import com.elevatemc.anticheat.util.http.HttpUtil;
import com.elevatemc.elib.eLib;
import com.elevatemc.elib.util.UUIDUtils;
import com.github.retrooper.packetevents.util.MojangAPIUtil;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.Bukkit.broadcastMessage;

public class BanwaveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player player = (Player) sender;
        if (!APIManager.isOperator(player)) {
            sender.sendMessage(CC.translate("&cGet lost fam."));
            return false;
        }
        Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> {
            if (args.length < 1) {
                sendMessage(player,"&7/&cbanwave &7- &fShows banwave help");
                sendMessage(player,"&7/&3banwave list &7- &fSends the list of players in the banwave.");
                sendMessage(player, ("&7/&3banwave add &f<player> &7- &fAdds a player to the banwave."));
                sendMessage(player, ("&7/&3banwave start &7- &fStarts the banwave."));
                return;
            }
            switch (args[0].toLowerCase()) {
                case "start": {
                    broadcastMessage(CC.translate("&r           "));
                    broadcastMessage(CC.translate("&4✘ &7The &cHood &7is getting Patrolled..."));
                    broadcastMessage(CC.translate("&r           "));
                    BanwaveManager banwaveManager = new BanwaveManager();

                    banwaveManager.load();
                    banwaveManager.runTaskTimerAsynchronously(Hood.get(), 20L, 20L);
                    break;
                }
                case "add": {
                        if (args.length < 2) {
                            return;
                        }
                         UUID uuid = eLib.getInstance().getUuidCache().uuid(args[0]);
                        if (uuid == null) {
                            sendMessage(player, "&cPlayer not found!");
                            return;
                        }
                        if (inBanwave(uuid)) {
                            sendMessage(player, "&cPlayer is already in the BanWave.");
                            return;
                        }
                        BanwaveLog log = new BanwaveLog(uuid, "Forcefully added by an Administrator.");
                        Hood.get().getLogManager().getQueuedCheaters().add(log);
                        player.sendMessage(CC.PINK + "Added " + CC.DARK_PURPLE + UUIDUtils.name(uuid) + CC.PINK + " to the ban wave.");
                    break;
                }
                case "list": {
                    StringBuilder list = new StringBuilder();
                    for (Document document : Hood.get().getLogHandler().getCollection("HoodBanwave").find()) {
                        UUID uuid = UUID.fromString(document.getString("uuid"));
                        String check = document.getString("check");

                        String playerName = Bukkit.getOfflinePlayer(uuid).getName();

                        list.append(playerName).append(" - ").append(check).append("\n");
                    }
                    String key = HttpUtil.getPastie(list.toString());
                    if (key == null) {
                        sendMessage(player, "&4ERROR! Could not upload Banwave List to a Pastie!");
                        return;
                    }
                    sendMessage(player,ChatColor.GRAY + "Banwave List: " + ChatColor.WHITE + "https://paste.md-5.net/" + key);
                    break;
                }
            }
        });
        return false;
    }

    public void sendMessage(final Player player, final String message) {
        player.sendMessage(CC.translate(message));
    }

    public boolean inBanwave(UUID uuid) {
        boolean found = false;
        String stringUUID = uuid.toString();
        Document query = new Document("uuid", stringUUID);
        Document result = Hood.get().getLogHandler().getCollection("HoodBanwave").find(query).first();
        if (result != null) {
            found = true;
        }
        return found;
    }
}