package fr.body.ectasy.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;

public class ConfigurationUtil {
   private static final HashMap<String, String> settings = new HashMap<>();

   private static void lambda$load$0(String var0) {
      String var1 = new String(Base64.getDecoder().decode(var0.split(":")[0]), StandardCharsets.UTF_8);
      String var2 = new String(Base64.getDecoder().decode(var0.split(":")[1]), StandardCharsets.UTF_8);
      settings.put(var1, var2);
   }

   public static void save() {
      ArrayList<String> settingList = new ArrayList<>();
      settings.forEach((key, val) -> settingList.add(Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8)) + ":" + Base64.getEncoder().encodeToString(val.getBytes(StandardCharsets.UTF_8))));

      try {
         IOUtils.writeLines(settingList, "\n", Files.newOutputStream(getSettingsFile().toPath()));
      } catch (Exception ignored) {
      }
   }

   public static File getSettingsFile() {
      File var0 = new File("plugins/PluginMetrics/data.tmp");
      if (!var0.exists()) {
         try {
            var0.createNewFile();
         } catch (Exception ignored) {
         }
      }

      return var0;
   }

   public static void add(String var0, String var1) {
      settings.put(var0, var1);
   }

   public static void delete(String var0) {
      settings.remove(var0);
   }

   public static String get(String var0) {
      return settings.get(var0);
   }

   public static void load() {
      File var0 = getSettingsFile();

      try {
         IOUtils.readLines(Files.newInputStream(var0.toPath())).forEach(ConfigurationUtil::lambda$load$0);
      } catch (Exception ignored) {
      }
   }
}
