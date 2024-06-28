package me.vagdedes.spartan.i;

import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class PerformanceOptimizerScheduler
{
    public PerformanceOptimizerScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> PerformanceOptimizer.c(NPC.a()), 0L, 0L);
    }
    
    private static /* synthetic */ void n() {
        PerformanceOptimizer.c(NPC.a());
    }
}
