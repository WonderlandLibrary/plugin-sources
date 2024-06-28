package me.vagdedes.spartan.g;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.bukkit.ChatColor;
import org.apache.commons.lang.StringUtils;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.c.ConfigUtils;
import org.bukkit.command.CommandSender;
import java.util.HashSet;
import java.io.File;

public class Patreon
{
    private static File file;
    private static final String pluginName;
    private static final boolean enabled = false;
    private static final HashSet<Integer> p;
    private static int level;
    private static final long j = 720000L;
    private static long time;
    
    public Patreon() {
        super();
    }
    
    public static boolean isEnabled() {
        return false;
    }
    
    public static boolean o() {
        return true;
    }
    
    private static void a(final CommandSender commandSender, final String s) {
        ConfigUtils.set(Patreon.file, "patreon_name", s);
        Settings.create();
        final String s2;
        Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> {
            a(false);
            if (StringUtils.isNumeric(s2)) {
                Patreon.level = Integer.valueOf(s2);
                if (Patreon.p.contains(Integer.valueOf(Patreon.level))) {
                    b(commandSender, "Your " + Patreon.pluginName + " license has been successfully enabled.");
                }
                else {
                    b(commandSender, "You need a higher membership to enable this license.");
                }
            }
            else {
                Patreon.level = 0;
                if (s2.equals("not-configured")) {
                    b(commandSender, "The Patreon full-name has not been configured. Please review the settings.yml configuration.");
                }
                else if (s2.equals("connection-error")) {
                    b(commandSender, "Servers couldn't communicated with each other other. Please seek support at https://vagdedes.com.");
                }
                else if (s2.equals("data-error")) {
                    b(commandSender, "A data error occurred in the verification server. Please seek support at https://vagdedes.com.");
                }
                else if (s2.equals("no-tier")) {
                    b(commandSender, "The system has not detected a Patreon membership. Please seek support at https://vagdedes.com.");
                }
                else if (s2.equals("ip-limited")) {
                    b(commandSender, "Your Patreon membership is IP limited. Please seek support at https://vagdedes.com.");
                }
                else if (s2.equals("declined-payment")) {
                    b(commandSender, "Your Patreon membership payment was recently declined. Please seek support at https://vagdedes.com.");
                }
                else {
                    b(commandSender, "Unhandled Exception (" + s2 + "). Please seek support at https://vagdedes.com.");
                }
            }
        });
    }
    
    public static void initialize() {
        if (isEnabled()) {
            Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, () -> a(true));
        }
    }
    
    public static void a(final CommandSender commandSender, final String[] array) {
        if (isEnabled()) {
            if (array.length > 0) {
                final StringBuilder sb = new StringBuilder();
                for (int i = 0; i < array.length; ++i) {
                    sb.append(array[i] + " ");
                }
                a(commandSender, sb.toString().substring(0, Math.max(0, sb.length() - 1)));
            }
            else {
                b(commandSender, "This license is not verified. Please execute the command '/" + Patreon.pluginName.toLowerCase() + " <Patreon Full-Name>' to enable it. If problems arise, please seek support at https://vagdedes.com.");
            }
        }
    }
    
    private static void log(final String str) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[" + Patreon.pluginName + "] " + ChatColor.RED + "Patreon Verification Failure (" + str + "). Please seek support at https://vagdedes.com.");
    }
    
    private static void b(final CommandSender commandSender, final String str) {
        commandSender.sendMessage(ChatColor.DARK_RED + "[" + Patreon.pluginName + "] " + ChatColor.RED + str);
    }
    
    private static String a(final boolean b) {
        if (b && k() > 0) {
            return Patreon.p.contains(Integer.valueOf(Patreon.level)) ? null : "not-verified";
        }
        Patreon.time = System.currentTimeMillis() + 720000L;
        final String g = IDs.g();
        final String s = "not-configured";
        if (g == null) {
            if (b) {
                Patreon.level = 0;
                log(s);
            }
            return s;
        }
        String line = "connection-error";
        try {
            final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL("https://vagdedes.com/patreon/?user=" + g).openConnection();
            httpsURLConnection.addRequestProperty("User-Agent", "Patreon Verification (" + Patreon.pluginName + ")");
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setReadTimeout(15000);
            httpsURLConnection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            if ((line = bufferedReader.readLine()) != null) {
                bufferedReader.close();
            }
            if (b) {
                if (StringUtils.isNumeric(line)) {
                    Patreon.level = Integer.valueOf(line);
                }
                else {
                    Patreon.level = 0;
                    log(line);
                }
            }
            return line;
        }
        catch (Exception ex) {
            if (b) {
                Patreon.level = 0;
                log(line);
            }
            return line;
        }
    }
    
    private static int k() {
        return Math.max((int)(Patreon.time - System.currentTimeMillis()), 0);
    }
    
    private static /* synthetic */ void m() {
        a(true);
    }
    
    private static /* synthetic */ void c(final CommandSender commandSender) {
        final String a = a(false);
        if (StringUtils.isNumeric(a)) {
            Patreon.level = Integer.valueOf(a);
            if (Patreon.p.contains(Integer.valueOf(Patreon.level))) {
                b(commandSender, "Your " + Patreon.pluginName + " license has been successfully enabled.");
            }
            else {
                b(commandSender, "You need a higher membership to enable this license.");
            }
        }
        else {
            Patreon.level = 0;
            if (a.equals("not-configured")) {
                b(commandSender, "The Patreon full-name has not been configured. Please review the settings.yml configuration.");
            }
            else if (a.equals("connection-error")) {
                b(commandSender, "Servers couldn't communicated with each other other. Please seek support at https://vagdedes.com.");
            }
            else if (a.equals("data-error")) {
                b(commandSender, "A data error occurred in the verification server. Please seek support at https://vagdedes.com.");
            }
            else if (a.equals("no-tier")) {
                b(commandSender, "The system has not detected a Patreon membership. Please seek support at https://vagdedes.com.");
            }
            else if (a.equals("ip-limited")) {
                b(commandSender, "Your Patreon membership is IP limited. Please seek support at https://vagdedes.com.");
            }
            else if (a.equals("declined-payment")) {
                b(commandSender, "Your Patreon membership payment was recently declined. Please seek support at https://vagdedes.com.");
            }
            else {
                b(commandSender, "Unhandled Exception (" + a + "). Please seek support at https://vagdedes.com.");
            }
        }
    }
    
    static {
        Patreon.file = new File(Register.plugin.getDataFolder() + "/settings.yml");
        pluginName = Register.plugin.getName();
        p = new HashSet<Integer>(10);
        Patreon.level = 0;
        Patreon.time = System.currentTimeMillis();
        Patreon.p.add(Integer.valueOf(2));
        Patreon.p.add(Integer.valueOf(3));
    }
}
