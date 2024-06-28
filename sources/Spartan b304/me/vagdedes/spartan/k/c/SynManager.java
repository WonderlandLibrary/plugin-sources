package me.vagdedes.spartan.k.c;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.features.syn.AutomatedConfigurationDiagnostics;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.g.Patreon;

public class SynManager
{
    private static boolean H;
    private static int cooldown;
    private static int a;
    public static final String o = "https://vagdedes.com/spartan/syn.php";
    
    public SynManager() {
        super();
    }
    
    public static boolean s() {
        return SynManager.H || (Patreon.isEnabled() && Patreon.o());
    }
    
    public static int getCooldown() {
        return SynManager.cooldown / 20;
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> {
            if (SynManager.cooldown > 0) {
                --SynManager.cooldown;
            }
            if (SynManager.a == 0) {
                SynManager.a = 864000;
                verify();
            }
            else {
                --SynManager.a;
            }
            AutomatedConfigurationDiagnostics.run();
        }, 0L, 0L);
    }
    
    public static void verify() {
        final String f = IDs.f();
        if (!f.equals("0")) {
            try {
                final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL("https://vagdedes.com/spartan/syn.php?sid=" + IDs.spigot() + "&pid=" + f).openConnection();
                httpURLConnection.addRequestProperty("User-Agent", "Spartan Syn");
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.connect();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                final String line;
                if ((line = bufferedReader.readLine()) != null) {
                    bufferedReader.close();
                }
                SynManager.H = line.equalsIgnoreCase("true");
            }
            catch (Exception ex) {
                SynManager.H = false;
                Bukkit.getConsoleSender().sendMessage("[Spartan] Could not connect to 'vagdedes.com' to verify the Syn membership.");
                Bukkit.getConsoleSender().sendMessage("Error: " + ex.getMessage());
                Bukkit.getConsoleSender().sendMessage("In Depth: " + ex.toString());
            }
            SynManager.cooldown = 300;
        }
        else {
            SynManager.H = false;
        }
    }
    
    private static /* synthetic */ void n() {
        if (SynManager.cooldown > 0) {
            --SynManager.cooldown;
        }
        if (SynManager.a == 0) {
            SynManager.a = 864000;
            verify();
        }
        else {
            --SynManager.a;
        }
        AutomatedConfigurationDiagnostics.run();
    }
    
    static {
        SynManager.H = false;
        SynManager.cooldown = 300;
        SynManager.a = 0;
    }
}
