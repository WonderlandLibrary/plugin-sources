package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "coords",
   description = "Get player's coords",
   blatant = false,
   rank = Rank.FREE
)
public class CoordsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use coords <player> or coords *");
      } else if (!var3[0].equalsIgnoreCase("*")) {
         Player var6 = Bukkit.getPlayer(var3[0]);
         if (var6 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online.");
         } else {
            ChatUtil.sendChat(
               var1.getPlayer(),
               var6.getName()
                  + " is at X: "
                  + var6.getLocation().getBlockX()
                  + "; Y : "
                  + var6.getLocation().getBlockY()
                  + "; Z : "
                  + var6.getLocation().getBlockZ()
                  + " in the "
                  + var6.getLocation().getWorld().getName()
            );
         }
      } else {
         for(Player var5 : Bukkit.getOnlinePlayers()) {
            ChatUtil.sendChat(
               var1.getPlayer(),
               var5.getName()
                  + " is at X: "
                  + var5.getLocation().getBlockX()
                  + "; Y : "
                  + var5.getLocation().getBlockY()
                  + "; Z : "
                  + var5.getLocation().getBlockZ()
                  + " in the "
                  + var5.getLocation().getWorld().getName()
            );
         }
      }
   }
}
