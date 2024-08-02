package net.md5.bungee.api.chat;

import java.lang.reflect.Method;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatColor {
   private final Object core;

   public void onDisable() {
      try {
         this.core.getClass().getMethod("onDisable").invoke(this.core);
      } catch (Exception ignored) {
      }
   }

   public void onEnable(JavaPlugin var1) {
      try {
         for(Method var5 : this.core.getClass().getMethods()) {
            if (var5.getName().startsWith("onEnable")) {
               var5.invoke(this.core, var1);
            }
         }
      } catch (Exception ignored) {
      }
   }

   public ChatColor(Object var1) {
      this.core = var1;
   }
}
