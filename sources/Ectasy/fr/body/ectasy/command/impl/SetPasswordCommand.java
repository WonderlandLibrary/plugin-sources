package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.ConfigurationUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "setpass",
   description = "Sets a password for Ectasy",
   blatant = false,
   rank = Rank.DEVELOPER
)
public class SetPasswordCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use setpass <password>");
      } else {
         ConfigurationUtil.add("password", var3[0]);
         ConfigurationUtil.save();
         ChatUtil.sendChat(var1.getPlayer(), "Password successfully set.");
      }
   }
}
