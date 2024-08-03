package fr.body.ectasy.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.Map.Entry;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtil {
   static final boolean ifThisIsInsideTheFullDeobfDMmeBecauseIFuckedUpLOL = !PluginUtil.class.desiredAssertionStatus();

   /*
      ChatGPT my beloved <3
    */
   public static void unload(Plugin plugin) {
      String pluginName = plugin.getName();
      PluginManager pluginManager = Bukkit.getPluginManager();
      SimpleCommandMap commandMap = null;
      List<?> pluginsList = null;
      Map<?, ?> lookupNamesMap = null;
      Map<?, ?> listenersMap = null;
      Map<?, ?> knownCommandsMap = null;

      // Disable the plugin
      pluginManager.disablePlugin(plugin);

      try {
         // Get the plugins list
         Field pluginsField = pluginManager.getClass().getDeclaredField("plugins");
         pluginsField.setAccessible(true);
         pluginsList = (List<?>) pluginsField.get(pluginManager);

         // Get the lookup names map
         Field lookupNamesField = pluginManager.getClass().getDeclaredField("lookupNames");
         lookupNamesField.setAccessible(true);
         lookupNamesMap = (Map<?, ?>) lookupNamesField.get(pluginManager);

         // Get the listeners map (optional, as it may not exist in some implementations)
         try {
            Field listenersField = pluginManager.getClass().getDeclaredField("listeners");
            listenersField.setAccessible(true);
            listenersMap = (Map<?, ?>) listenersField.get(pluginManager);
         } catch (NoSuchFieldException e) {
            // Field does not exist, ignore
         }

         // Get the command map
         Field commandMapField = pluginManager.getClass().getDeclaredField("commandMap");
         commandMapField.setAccessible(true);
         commandMap = (SimpleCommandMap) commandMapField.get(pluginManager);

         // Get the known commands map
         Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
         knownCommandsField.setAccessible(true);
         knownCommandsMap = (Map<?, ?>) knownCommandsField.get(commandMap);
      } catch (NoSuchFieldException | IllegalAccessException e) {
         e.printStackTrace();
      }

      // Remove the plugin from the plugins list
      if (pluginsList != null) {
         pluginsList.remove(plugin);
      }

      // Remove the plugin from the lookup names map
      if (lookupNamesMap != null) {
         lookupNamesMap.remove(pluginName);
      }

      // Unregister listeners
      if (listenersMap != null) {
         for (Object value : listenersMap.values()) {
            if (value instanceof SortedSet) {
               SortedSet<?> sortedSet = (SortedSet<?>) value;
               Iterator<?> iterator = sortedSet.iterator();
               while (iterator.hasNext()) {
                  RegisteredListener registeredListener = (RegisteredListener) iterator.next();
                  if (registeredListener.getPlugin() == plugin) {
                     iterator.remove();
                  }
               }
            }
         }
      }

      // Unregister commands
      if (knownCommandsMap != null) {
         Iterator<?> iterator = knownCommandsMap.entrySet().iterator();
         while (iterator.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
            if (entry.getValue() instanceof PluginCommand) {
               PluginCommand pluginCommand = (PluginCommand) entry.getValue();
               if (pluginCommand.getPlugin() == plugin) {
                  pluginCommand.unregister(commandMap);
                  iterator.remove();
               }
            }
         }
      }

      // Close the class loader
      ClassLoader classLoader = plugin.getClass().getClassLoader();
      if (classLoader instanceof URLClassLoader) {
         try {
            Field pluginField = classLoader.getClass().getDeclaredField("plugin");
            pluginField.setAccessible(true);
            pluginField.set(classLoader, null);

            Field pluginInitField = classLoader.getClass().getDeclaredField("pluginInit");
            pluginInitField.setAccessible(true);
            pluginInitField.set(classLoader, null);
         } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
         }

         try {
            ((URLClassLoader) classLoader).close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      // Request garbage collection
      System.gc();
   }

   public static File getPluginFile(JavaPlugin var0) {
      try {
         Method var1 = JavaPlugin.class.getDeclaredMethod("getFile");
         var1.setAccessible(true);
         return (File)var1.invoke(var0);
      } catch (Exception var2) {
         return null;
      }
   }

   private static void loadUsingPluginInstance(Plugin var0) {
      loadUsingPath(var0.getName());
   }

   public static void loadUsingPath(String var0) {
      Plugin var1 = null;
      File var2 = new File("plugins");
      if (!var2.isDirectory()) {
      }

      File var3 = new File(var2, var0 + ".jar");
      if (!var3.isFile()) {
         for (File var7 : var2.listFiles()) {
            if (var7.getName().endsWith(".jar")) {
               try {
                  PluginDescriptionFile var8 = Ectasy.parentPlugin.getPluginLoader().getPluginDescription(var7);
                  if (var8.getName().equalsIgnoreCase(var0)) {
                     var3 = var7;
                     break;
                  }
               } catch (InvalidDescriptionException ignored) {
               }
            }
         }
      }

      try {
         var1 = Bukkit.getPluginManager().loadPlugin(var3);
      } catch (InvalidPluginException | InvalidDescriptionException var9) {
         var9.printStackTrace();
      }

      assert var1 != null;
      var1.onLoad();
      Bukkit.getPluginManager().enablePlugin(var1);
   }

   public static Plugin getPluginByName(String var0) {
      for(Plugin var4 : Bukkit.getPluginManager().getPlugins()) {
         if (var0.equalsIgnoreCase(var4.getName())) {
            return var4;
         }
      }

      return null;
   }

   public static void reload(Plugin var0) throws Exception {
      if (var0 != null) {
         unload(var0);
         loadUsingPluginInstance(var0);
      }
   }
}
