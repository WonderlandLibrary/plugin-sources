package me.vagdedes.spartan.i;

import me.vagdedes.spartan.k.b.Operators;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class OperatorsScheduler
{
    public OperatorsScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, Operators::cache, 1200L, 12000L);
    }
}
