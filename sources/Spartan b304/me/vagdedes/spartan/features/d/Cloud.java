package me.vagdedes.spartan.features.d;

import org.bukkit.entity.Player;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.d.RequestUtils;
import me.vagdedes.spartan.api.BackgroundAPI;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.a.a.Settings;
import java.util.HashSet;
import me.vagdedes.spartan.system.Enums;
import java.util.HashMap;

public class Cloud
{
    private static final HashMap<Enums.HackType, HashSet<String>> G;
    private static final int max = 1200;
    private static int time;
    
    public Cloud() {
        super();
    }
    
    public static void run() {
        if (Cloud.time > 0) {
            --Cloud.time;
        }
    }
    
    public static void clear() {
        if (Cloud.time == 0) {
            Cloud.time = 1200;
            Cloud.G.clear();
            if (Settings.canDo("Important.cloud_feature")) {
                final Enums.HackType[] array;
                int length;
                int i = 0;
                Enums.HackType key;
                HashSet<String> value;
                final String[] array2;
                int length2;
                int j = 0;
                String s;
                String s2;
                final String s3;
                Threads.a(new Object(), () -> {
                    if (Register.plugin != null && Register.plugin.isEnabled()) {
                        FalsePositiveDetection.b();
                        for (length = array.length; i < length; ++i) {
                            key = array[i];
                            value = new HashSet<String>();
                            try {
                                RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=get&data=falsePositives&version=" + BackgroundAPI.getVersion().replace((CharSequence)"Build ", (CharSequence)""), "Cloud Network [GET]");
                                for (length2 = array2.length; j < length2; ++j) {
                                    s = array2[j];
                                    if (s.contains("(False Positive) ")) {
                                        if (s.contains(" " + key.toString() + " ")) {
                                            s2 = s.split("\\), ")[7];
                                            FalsePositiveDetection.a(key, s2.substring(1, s2.length() - 2));
                                            if (!value.contains(s3)) {
                                                value.add(s3);
                                            }
                                        }
                                    }
                                }
                            }
                            catch (Exception ex) {
                                ErrorUtils.send("(GET) Failed to connect to the Spartan Cloud.");
                            }
                            Cloud.G.put(key, value);
                        }
                    }
                });
            }
        }
    }
    
    public static HashSet<String> a(final Enums.HackType hackType) {
        return Cloud.G.containsKey(hackType) ? ((HashSet<String>)Cloud.G.get(hackType)) : new HashSet<String>();
    }
    
    public static Enums.HackType[] a() {
        if (Settings.canDo("Important.cloud_feature")) {
            final HashMap<Enums.HackType, Integer> hashMap = new HashMap<Enums.HackType, Integer>();
            try {
                final String[] a = RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=get&data=developerReports&version=" + BackgroundAPI.getVersion().replace((CharSequence)"Build ", (CharSequence)""), "Cloud Network [GET]");
                for (int length = a.length, i = 0; i < length; ++i) {
                    final Enums.HackType value = Enums.HackType.valueOf(a[i]);
                    if (!hashMap.containsKey(value)) {
                        hashMap.put(value, Integer.valueOf(1));
                    }
                    else {
                        hashMap.put(value, Integer.valueOf((int)Integer.valueOf(hashMap.get((Object)value)) + 1));
                    }
                }
            }
            catch (Exception ex) {
                ErrorUtils.send("(GET) Failed to connect to the Spartan Cloud.");
            }
            final ArrayList<Enums.HackType> list = new ArrayList<Enums.HackType>();
            for (final Map.Entry<Enums.HackType, Integer> entry : hashMap.entrySet()) {
                final Enums.HackType hackType = (Enums.HackType)entry.getKey();
                if (!list.contains(hackType) && Integer.valueOf(entry.getValue()) >= 3) {
                    list.add(hackType);
                }
            }
            hashMap.clear();
            return list.<Enums.HackType>toArray(new Enums.HackType[0]);
        }
        return new Enums.HackType[0];
    }
    
    public static void sendMessage(final Player player, final String str) {
        player.sendMessage("§8[§2Spartan Cloud§8]§a " + str);
    }
    
    private static /* synthetic */ void l() {
        if (Register.plugin != null && Register.plugin.isEnabled()) {
            for (final Enums.HackType key : FalsePositiveDetection.b()) {
                final HashSet<String> value = new HashSet<String>();
                try {
                    for (final String s : RequestUtils.a("https://vagdedes.com/spartan/cloud/?action=get&data=falsePositives&version=" + BackgroundAPI.getVersion().replace((CharSequence)"Build ", (CharSequence)""), "Cloud Network [GET]")) {
                        if (s.contains("(False Positive) ") && s.contains(" " + key.toString() + " ")) {
                            final String s2 = s.split("\\), ")[7];
                            final String a2 = FalsePositiveDetection.a(key, s2.substring(1, s2.length() - 2));
                            if (!value.contains(a2)) {
                                value.add(a2);
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    ErrorUtils.send("(GET) Failed to connect to the Spartan Cloud.");
                }
                Cloud.G.put(key, value);
            }
        }
    }
    
    static {
        G = new HashMap<Enums.HackType, HashSet<String>>();
        Cloud.time = 1200;
    }
}
