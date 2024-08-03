package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "lore",
   description = "Sets the lore of your held item (New line: {NL})",
   blatant = false,
   rank = Rank.LITE,
   aliases = {"setlore"}
)
public class ItemLoreCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use lore <lore>");
      } else if (var1.getPlayer().getItemInHand() == null) {
         ChatUtil.sendChat(var1.getPlayer(), "You are not holding an item!");
      } else {
         StringBuilder var4 = new StringBuilder();

         for(String var8 : var3) {
            var4.append(" ").append(ChatColor.translateAlternateColorCodes('&', var8));
         }

         String[] var9 = var4.substring(1).split("\\{NL}");
         ItemMeta var10 = var1.getPlayer().getItemInHand().getItemMeta();
         var10.setLore(Arrays.asList(var9));
         var1.getPlayer().getItemInHand().setItemMeta(var10);
         ChatUtil.sendChat(var1.getPlayer(), "Successfully set your items lore to " + var10.getLore().toString());
      }
   }
}
