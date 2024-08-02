package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "sc",
   description = "Lets you talk to other Ectasy users",
   blatant = false,
   rank = Rank.FREE
)
public class SecretChatCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for(User var5 : Ectasy.users.values()) {
         if (var5.rank != Rank.UNAUTHORIZED) {
            ChatUtil.sendChat(
               var5.player, ChatUtil.getColorByPlayer(var1.getPlayer()) + var1.getPlayer().getName() + "§f " + String.join(" ", var3).replace("&", "§")
            );
         }
      }
   }
}
