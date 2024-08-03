package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "repair",
   description = "Repair the item you are holding",
   blatant = false,
   rank = Rank.LITE
)
public class RepairItemCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var1.getPlayer().getItemInHand() == null) {
         ChatUtil.sendChat(var1.getPlayer(), "You arent holding anything.");
      } else if (var1.getPlayer().getItemInHand().getType() == Material.AIR) {
         ChatUtil.sendChat(var1.getPlayer(), "You arent holding anything.");
      } else {
         var1.getPlayer().getItemInHand().setDurability((short)0);
         ChatUtil.sendChat(var1.getPlayer(), "Repaired the item");
      }
   }

   public RepairItemCommand() {
   }
}
