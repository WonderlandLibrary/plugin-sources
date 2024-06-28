package me.vagdedes.spartan.i;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class VLScheduler
{
    public VLScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, VL::run, 0L, 0L);
    }
}
