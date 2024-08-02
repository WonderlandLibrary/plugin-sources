package fr.body.ectasy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RequestUtil {
   public static String post(String var0, String var1) {
      try {
         URL var2 = new URL(var0);
         HttpURLConnection var3 = (HttpURLConnection)var2.openConnection();
         var3.setRequestMethod("POST");
         var3.setRequestProperty("Content-Type", "application/json");
         var3.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
         var3.setDoOutput(true);
         OutputStream var4 = var3.getOutputStream();
         byte[] var5 = var1.getBytes(StandardCharsets.UTF_8);
         var4.write(var5, 0, var5.length);
         var4.close();
         var3.connect();
         BufferedReader var6 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
         return var6.readLine();
      } catch (Exception var7) {
         var7.printStackTrace();
         return "Unauthorized";
      }
   }

   public RequestUtil() {
   }

   public static String get(String var0) {
      try {
         URL var1 = new URL(var0);
         HttpURLConnection var2 = (HttpURLConnection)var1.openConnection();
         var2.setRequestMethod("GET");
         var2.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
         var2.setDoOutput(true);
         var2.connect();
         BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));
         return var3.lines().collect(Collectors.joining("\n"));
      } catch (Exception var4) {
         return "Unauthorized";
      }
   }
}
