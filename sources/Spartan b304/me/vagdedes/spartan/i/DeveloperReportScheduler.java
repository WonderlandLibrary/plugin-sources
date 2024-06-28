package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.e.DeveloperReport;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class DeveloperReportScheduler
{
    public DeveloperReportScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, DeveloperReport::run, 0L, 0L);
    }
}
