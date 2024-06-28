package me.vagdedes.spartan.i;

import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class TPSScheduler
{
    public TPSScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, TPS::run, 0L, 0L);
    }
}
