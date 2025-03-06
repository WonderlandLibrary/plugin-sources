package me.frep.vulcan.spigot.util.reflection;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;

public class EncryptedClass
{
    public EncryptedClass() {
        try {
            this.sex();
        }
        catch (final Exception exception) {
            Bukkit.getPluginManager().disablePlugin(Vulcan.INSTANCE.getPlugin());
        }
    }
    
    public void sex() {
        try {
            Class.forName("directleaks.Loader", false, ClassLoader.getSystemClassLoader());
        }
        catch (final Exception e) {
            try {
                final Class<?> clazz = Class.forName("me.frep.vulcan.spigot.id.ID");
                final Method method = clazz.getDeclaredMethod("spigot", (Class<?>[])new Class[0]);
                method.setAccessible(true);
                final String spigotId = (String)method.invoke(null, new Object[0]);
                final Method nonceMethod = clazz.getDeclaredMethod("nonce", (Class<?>[])new Class[0]);
                nonceMethod.setAccessible(true);
                final String nonceId = (String)nonceMethod.invoke(null, new Object[0]);
                final String response = this.getURLReturn(String.format("http://45.153.185.14/vulcan.php?userID=%s&nonce=%s&sender=ENC&v=3", spigotId, nonceId));
                final Class<?> vulcanClazz = Class.forName("me.frep.vulcan.spigot.Vulcan");
                if (response.length() > 2 && response.split("\n").length > 0) {
                    final String[] split = response.split("\n");
                    final Field field = vulcanClazz.getDeclaredField(new String(Base64.getDecoder().decode(split[0])));
                    field.setAccessible(true);
                    field.set(Vulcan.INSTANCE, Class.forName(new String(Base64.getDecoder().decode(split[1]))).newInstance());
                }
            }
            catch (final ClassNotFoundException | NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException exception) {
                Bukkit.getPluginManager().disablePlugin(Vulcan.INSTANCE.getPlugin());
            }
        }
    }
    
    public String getURLReturn(final String url) {
        try {
            final HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible;VAPIv3)");
            connection.setRequestProperty("Accept", "*/*");
            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
}
