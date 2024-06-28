package me.vagdedes.spartan.i;

import me.vagdedes.spartan.checks.e.AutoRespawn;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class AutoRespawnScheduler
{
    public AutoRespawnScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, AutoRespawn::run, 0L, 0L);
    }
}
