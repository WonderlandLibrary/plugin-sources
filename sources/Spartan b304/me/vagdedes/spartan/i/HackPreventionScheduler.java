package me.vagdedes.spartan.i;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class HackPreventionScheduler
{
    public HackPreventionScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, HackPrevention::run, 0L, 0L);
    }
}
