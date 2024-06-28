package me.vagdedes.spartan.k.f;

import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.api.BackgroundAPI;
import org.bukkit.Bukkit;

public class ErrorUtils
{
    public ErrorUtils() {
        super();
    }
    
    public static void send(final String str) {
        Bukkit.getConsoleSender().sendMessage("[Spartan " + BackgroundAPI.getVersion() + "] " + str);
    }
    
    public static String d(final String str) {
        return Settings.canDo("Notifications.awareness_notifications") ? ("§8[§2Spartan Notification§8]§a " + str) : null;
    }
}
