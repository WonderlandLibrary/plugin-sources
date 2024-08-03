package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "xp",
   description = "Gives you xp",
   blatant = false,
   rank = Rank.LITE
)
public class XPCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      var1.getPlayer().sendRawMessage("Soon (TM)");
   }
}
