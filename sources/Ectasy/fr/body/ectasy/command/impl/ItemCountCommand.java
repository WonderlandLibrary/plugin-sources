package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "itemcount",
   description = "Sets the count of the item you are holding",
   blatant = true,
   rank = Rank.PREMIUM
)
public class ItemCountCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length != 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use itemcount <count>");
      } else if (var1.getPlayer().getItemInHand() == null) {
         ChatUtil.sendChat(var1.getPlayer(), "You are not holding an item!");
      } else {
         int var4;
         try {
            var4 = Integer.parseInt(var3[0]);
         } catch (NumberFormatException var6) {
            ChatUtil.sendChat(var1.getPlayer(), "That is not a valid number!");
            return;
         }

         var1.getPlayer().getItemInHand().setAmount(var4);
         ChatUtil.sendChat(var1.getPlayer(), "The count of your current item has been set to " + var4 + "!");
      }
   }
}
