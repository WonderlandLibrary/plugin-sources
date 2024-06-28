package me.vagdedes.spartan.g;

import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.b.IOUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import me.vagdedes.spartan.Register;
import org.apache.commons.lang.StringUtils;
import org.bukkit.event.Listener;

public class Piracy implements Listener
{
    public static final boolean enabled = true;
    private static boolean valid;
    private static final int length;
    private static final String name;
    
    public Piracy() {
        super();
    }
    
    private static boolean b(String substring) {
        if (substring == null) {
            return false;
        }
        final String substring2 = c(substring).substring(8).substring(0, 1);
        final boolean b = substring.length() == 43 + Piracy.name.length() && c(substring) != null && !StringUtils.isNumeric(substring) && c(substring).length() == 12 && c(substring).equals("vagdedes.com") && StringUtils.countMatches(c(substring), substring2) == Math.pow(2.0, 2.0) / 4.0;
        if (b) {
            substring = substring.substring(8);
            return substring.equals("vagdedes.com/" + Piracy.name.toLowerCase() + "/verify.php?id=&nonce=");
        }
        return b;
    }
    
    private static boolean c(final String s) {
        return s != null && StringUtils.isNumeric(s) && !s.contains("-") && !s.equalsIgnoreCase("0") && s.length() > 0;
    }
    
    private static boolean d(final String s) {
        return s != null && s.length() >= 8 && s.length() <= 12 && (StringUtils.isNumeric(s) || s.startsWith("-"));
    }
    
    private static boolean a(final String s, final String str, final String str2, final String str3) {
        final boolean b = Piracy.valid && b(s) && c(str) && c(str3) && d(str2) && str3.equalsIgnoreCase(String.valueOf(25638)) && Piracy.name.equalsIgnoreCase("Spartan") && Register.plugin.getDescription().getVersion().startsWith("Build " + Register.plugin.getDescription().getVersion().substring(6)) && Register.plugin.getDescription().getDescription().equalsIgnoreCase("An extraordinary cross-version cheat prevention you have always dreamed of.") && Register.plugin.getDescription().getWebsite().equalsIgnoreCase("https://www.spigotmc.org/resources/" + str3) && Register.plugin.getDescription().getAuthors().toString().equalsIgnoreCase("[Evangelos Dedes @Vagdedes]");
        if (b) {
            try {
                final int n = s.length() - 7;
                return !b(s.substring(0, n) + str + s.substring(n) + str2).equalsIgnoreCase(String.valueOf(false));
            }
            catch (Exception ex) {
                return b;
            }
        }
        return b;
    }
    
    private static String b(final String spec) {
        if (spec == null) {
            return "false";
        }
        final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL(spec).openConnection();
        httpsURLConnection.addRequestProperty("User-Agent", Piracy.name);
        httpsURLConnection.setRequestMethod("GET");
        httpsURLConnection.setReadTimeout(15000);
        httpsURLConnection.connect();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        final String line;
        if ((line = bufferedReader.readLine()) != null) {
            bufferedReader.close();
        }
        return line;
    }
    
    private static String c(String replace) {
        if (replace != null) {
            replace = replace.replace("https://", "");
            for (int i = 0; i <= replace.length(); ++i) {
                if (replace.substring(i).startsWith("/")) {
                    return replace.substring(0, i);
                }
            }
        }
        return null;
    }
    
    private static int a(final Class clazz) {
        if (clazz != null) {
            try {
                final byte[] fully = IOUtils.readFully(IOUtils.inputStream((Class)Piracy.class));
                final byte[] array = (byte[])((clazz.getPackage() == null || clazz.getPackage().getName() == null) ? null : clazz.getPackage().getName().getBytes());
                final byte[] array2 = (byte[])((clazz.getName() == null) ? null : clazz.getName().getBytes());
                return ((fully == null) ? 0 : fully.length) - ((array2 == null) ? 0 : array2.length) - ((array == null) ? 1 : array.length);
            }
            catch (Exception ex) {}
        }
        return 0;
    }
    
    static {
        Piracy.valid = true;
        length = a(Piracy.class);
        name = Register.plugin.getName();
        final boolean a = a(IDs.site(), IDs.spigot(), IDs.nonce(), IDs.resource());
        if (!a) {
            Piracy.valid = a;
            Bukkit.getPluginManager().disablePlugin(Register.plugin);
        }
    }
}
