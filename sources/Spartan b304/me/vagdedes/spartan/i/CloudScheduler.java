package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.d.Cloud;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class CloudScheduler
{
    public CloudScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, Cloud::run, 0L, 0L);
    }
}
