package me.vagdedes.spartan.i;

import me.vagdedes.spartan.k.g.PluginTicks;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class PluginTicksScheduler
{
    public PluginTicksScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, PluginTicks::run, 0L, 0L);
    }
}
