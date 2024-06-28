package me.vagdedes.spartan.k.b;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;

public class PluginUtils
{
    public PluginUtils() {
        super();
    }
    
    public static boolean exists(final String anotherString) {
        for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.isEnabled() && plugin.getName().equalsIgnoreCase(anotherString)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean exists(final String[] array) {
        for (final String anotherString : array) {
            for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                if (plugin.isEnabled() && plugin.getName().equalsIgnoreCase(anotherString)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Plugin a(final String anotherString) {
        for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.isEnabled() && plugin.getName().equalsIgnoreCase(anotherString)) {
                return plugin;
            }
        }
        return null;
    }
    
    public static boolean contains(final String s) {
        for (final Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (plugin.isEnabled() && plugin.getName().toLowerCase().contains(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
