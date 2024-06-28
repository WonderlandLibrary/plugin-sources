package me.vagdedes.spartan.i;

import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class CheckProtectionScheduler
{
    public CheckProtectionScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, CheckProtection::run, 0L, 0L);
    }
}
