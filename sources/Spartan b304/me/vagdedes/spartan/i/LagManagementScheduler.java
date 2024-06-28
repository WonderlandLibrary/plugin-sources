package me.vagdedes.spartan.i;

import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class LagManagementScheduler
{
    public LagManagementScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, LagManagement::run, 0L, 1200L);
    }
}
