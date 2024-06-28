package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.a.ConfigurationDiagnostics;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class ConfigurationDiagnosticsScheduler
{
    public ConfigurationDiagnosticsScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, ConfigurationDiagnostics::run, 0L, 0L);
    }
}
