package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "commandinfo",
   description = "Gives you detailed information on a command",
   blatant = false,
   rank = Rank.FREE
)
public class CommandInfoCommand extends AbstractCommand {
   public CommandInfoCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length != 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use commandinfo <command>");
      } else {
         String var4 = var3[0];

         for(AbstractCommand var6 : Ectasy.commandManager.getCommands()) {
            if (var6.getName().equalsIgnoreCase(var4) || var6.getAliases().contains(var4)) {
               if (Ectasy.users.get(var1.getPlayer().getName()).rank.index < var6.requiredRank.index) {
                  ChatUtil.sendChat(var1.getPlayer(), "You do not have permission to get info on the command " + var3[0] + ".");
                  return;
               } else {
                  ChatUtil.sendChat(var1.getPlayer(), "****** Command Info ******");
                  ChatUtil.sendChat(var1.getPlayer(), "Name: " + var6.getName());
                  ChatUtil.sendChat(var1.getPlayer(), "Description: " + var6.getDescription());
                  ChatUtil.sendChat(
                     var1.getPlayer(), "Blatant: " + String.valueOf(var6.isBlatant()).toUpperCase().replace("TRUE", "Yes §e⚠").replace("FALSE", "No")
                  );
                  ChatUtil.sendChat(var1.getPlayer(), "Rank: " + var6.getRequiredRank());
                  ChatUtil.sendChat(var1.getPlayer(), "Aliases: " + var6.getAliases().toString().replace("[", "").replace("]", ""));
                  return;
               }
            }
         }

         ChatUtil.sendChat(var1.getPlayer(), "Unknown command.");
      }
   }
}
