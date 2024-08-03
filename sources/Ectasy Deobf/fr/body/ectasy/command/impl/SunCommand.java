package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "sun",
   description = "Sets the weather to sun",
   blatant = true,
   rank = Rank.LITE
)
public class SunCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var1.getPlayer().getWorld().setStorm(false);
         var1.getPlayer().getWorld().setThundering(false);
         ChatUtil.sendChat(var1.getPlayer(), "The weather has been set to sun.");
      });
   }
}
