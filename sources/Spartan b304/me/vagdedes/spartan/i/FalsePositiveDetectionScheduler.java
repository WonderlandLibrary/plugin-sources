package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class FalsePositiveDetectionScheduler
{
    public FalsePositiveDetectionScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, FalsePositiveDetection::run, 0L, 0L);
    }
}
