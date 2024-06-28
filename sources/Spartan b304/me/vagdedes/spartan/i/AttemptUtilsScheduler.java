package me.vagdedes.spartan.i;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class AttemptUtilsScheduler
{
    public AttemptUtilsScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Register.plugin, AttemptUtils::run, 0L, 0L);
    }
}
