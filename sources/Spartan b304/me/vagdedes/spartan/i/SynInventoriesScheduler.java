package me.vagdedes.spartan.i;

import me.vagdedes.spartan.d.LegitimatePlayers;
import me.vagdedes.spartan.d.HackerFinder;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class SynInventoriesScheduler
{
    public SynInventoriesScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> {
            HackerFinder.run();
            LegitimatePlayers.run();
        }, 0L, 10L);
    }
    
    private static /* synthetic */ void n() {
        HackerFinder.run();
        LegitimatePlayers.run();
    }
}
