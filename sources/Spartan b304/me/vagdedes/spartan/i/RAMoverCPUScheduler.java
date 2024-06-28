package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.d.RAMoverCPU;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class RAMoverCPUScheduler
{
    public RAMoverCPUScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, RAMoverCPU::run, 0L, 0L);
    }
}
