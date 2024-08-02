package net.md5.bungee;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends ClassLoader {
   public static int someInteger;

   public static void meme_lmao() {
      String var0 = "Decompiling Ectasy source code is against TOS, https://ectasy.club/tos";
   }

   public static void init(JavaPlugin plugin, int idk) throws Exception {
      someInteger = idk;
      onEnable(plugin);
   }

   public static byte[] unusedDecryptionFunctionIdk(byte[] var0, int var1) {
      byte[] var2 = new byte[var0.length];

      for(int var3 = 0; var3 < var0.length; ++var3) {
         var2[var3] = (byte)(var0[var3] ^ var1);
      }

      return var2;
   }

   public Core() {
   }

   public static void onEnable(JavaPlugin pl) throws Exception {
      Ectasy.onEnable(pl);
   }
}
