package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "speed",
   description = "Makes you go zoom",
   blatant = false,
   rank = Rank.PREMIUM
)
public class SpeedCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         var1.getPlayer().setWalkSpeed(0.2F);
         var1.getPlayer().setFlySpeed(0.1F);
         ChatUtil.sendChat(var1.getPlayer(), "Your speed has been reset!");
      } else {
         float var4;
         try {
            var4 = Float.parseFloat(var3[0]);
         } catch (NumberFormatException var6) {
            ChatUtil.sendChat(var1.getPlayer(), "The number " + var3[0] + " is not a valid number!");
            return;
         }

         var1.getPlayer().setWalkSpeed(var4);
         var1.getPlayer().setFlySpeed(var4 - 0.1F);
         ChatUtil.sendChat(var1.getPlayer(), "Your speed has been set to " + var4 + "!");
      }
   }
}
