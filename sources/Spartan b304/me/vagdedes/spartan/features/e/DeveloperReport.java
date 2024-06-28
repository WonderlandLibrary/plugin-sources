package me.vagdedes.spartan.features.e;

import me.vagdedes.spartan.features.d.Cloud;
import me.vagdedes.spartan.k.c.PermissionUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.d.StringUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.URLStreamHandler;
import java.net.URL;
import sun.net.www.protocol.https.Handler;
import javax.net.ssl.HttpsURLConnection;
import java.util.Iterator;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.d.RequestUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.api.BackgroundAPI;
import org.bukkit.ChatColor;
import java.net.URLEncoder;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.system.Enums;
import java.util.ArrayList;
import org.bukkit.command.CommandSender;

public class DeveloperReport
{
    private static final String line = "\n";
    private static final int max = 12000;
    private static int time;
    
    public DeveloperReport() {
        super();
    }
    
    public static void run() {
        if (DeveloperReport.time > 0) {
            --DeveloperReport.time;
        }
    }
    
    public static String a(final CommandSender commandSender, final ArrayList<String> list, final Enums.HackType hackType, final String s) {
        final int i = DeveloperReport.time / 20;
        if (i > 0) {
            return "Please wait " + i + " second(s) before doing this again.";
        }
        final int n = 1000000;
        String s2 = "(Check: " + ((hackType == null) ? "None" : hackType.toString()) + ", Player: " + ((s == null) ? "None" : s) + ")" + "\n" + "\n";
        int n2 = 0;
        for (final String str : list) {
            if (!str.contains("(TPS: 20.0)") && !str.contains("(TPS: 19.") && !str.contains("(TPS: 18.")) {
                ++n2;
            }
            if (s2.length() + str.length() + "\n".length() > n) {
                break;
            }
            s2 = s2 + str + "\n";
        }
        try {
            final String a = a("https://vagdedes.com/spartan/developer-report/", "u=" + IDs.spigot() + "&t=" + URLEncoder.encode(s2, "UTF-8"));
            if (a(a).contains("Developer-Report Created: ")) {
                final boolean b = n2 == 0 || n2 / (double)list.size() * 100.0 <= 10.0;
                if (n2 > 0) {
                    if (b) {
                        commandSender.sendMessage(ChatColor.RED + "Developer-Report Warning: Few cases of low TPS were detected in the reported logs.");
                    }
                    else {
                        commandSender.sendMessage(ChatColor.RED + "Developer-Report Warning: Many cases of low TPS were detected in the reported logs.");
                    }
                }
                if (hackType != null && b) {
                    final String str2;
                    Threads.a(new Object(), () -> {
                        BackgroundAPI.getVersion().replace("Build ", "");
                        if (Register.plugin != null && Register.plugin.isEnabled()) {
                            try {
                                RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=add&data=developerReports&value=" + hackType.toString() + "&version=" + str2, "Cloud Network [ADD]");
                            }
                            catch (Exception ex2) {
                                ErrorUtils.send("(ADD) Failed to connect to the Spartan Cloud.");
                            }
                        }
                        return;
                    });
                }
            }
            return a;
        }
        catch (Exception ex) {
            return ex.getMessage();
        }
    }
    
    private static String a(final String spec, final String s) {
        final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL(null, spec, new Handler()).openConnection();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setRequestProperty("User-Agent", "Spartan AntiCheat (Developer Report)");
        httpsURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpsURLConnection.setDoOutput(true);
        final DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
        dataOutputStream.writeBytes(s);
        dataOutputStream.flush();
        dataOutputStream.close();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        final String line;
        if ((line = bufferedReader.readLine()) != null) {
            bufferedReader.close();
        }
        DeveloperReport.time = 12000;
        return line;
    }
    
    public static String a(final String str) {
        if (!str.startsWith("https://pastebin.com")) {
            return ChatColor.RED + "Developer-Report Failed: " + StringUtils.substring(str, 0, 100);
        }
        return ChatColor.GREEN + "Developer-Report Created: " + str;
    }
    
    public static void e(final Player player) {
        if (PermissionUtils.a(player, Enums.Permission.manage)) {
            final Enums.HackType[] array;
            final Enums.HackType[] array2;
            int length;
            int i = 0;
            Threads.a(player, () -> {
                Cloud.a();
                if (array.length > 0) {
                    Cloud.sendMessage(player, "Customers have reported this version's following checks to be \"fairly\" malfunctioning:");
                    for (length = array2.length; i < length; ++i) {
                        player.sendMessage("§a" + array2[i]);
                    }
                }
            });
        }
    }
    
    private static /* synthetic */ void f(final Player player) {
        final Enums.HackType[] a = Cloud.a();
        if (a.length > 0) {
            Cloud.sendMessage(player, "Customers have reported this version's following checks to be \"fairly\" malfunctioning:");
            final Enums.HackType[] array = a;
            for (int length = array.length, i = 0; i < length; ++i) {
                player.sendMessage("§a" + array[i]);
            }
        }
    }
    
    private static /* synthetic */ void a(final Enums.HackType hackType, final String str) {
        if (Register.plugin != null && Register.plugin.isEnabled()) {
            try {
                RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=add&data=developerReports&value=" + hackType.toString() + "&version=" + str, "Cloud Network [ADD]");
            }
            catch (Exception ex) {
                ErrorUtils.send("(ADD) Failed to connect to the Spartan Cloud.");
            }
        }
    }
    
    static {
        DeveloperReport.time = 6000;
    }
}
