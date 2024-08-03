package fr.body.ectasy.script;

import net.md5.bungee.Ectasy;

public class Logger {
   public Logger() {
   }

   public static void fatal(String var0) {
      Ectasy.parentPlugin.getLogger().severe("[script api] " + var0);
   }

   public static void warn(String var0) {
      Ectasy.parentPlugin.getLogger().warning("[script api] " + var0);
   }

   public static void info(String var0) {
      Ectasy.parentPlugin.getLogger().info("[script api] " + var0);
   }
}
