package me.vagdedes.spartan.i;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class SpartanPlayerScheduler
{
    public SpartanPlayerScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> SpartanPlayer.a(1), 0L, 0L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> SpartanPlayer.a(10), 0L, 10L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> SpartanPlayer.a(20), 0L, 20L);
    }
    
    private static /* synthetic */ void o() {
        SpartanPlayer.a(20);
    }
    
    private static /* synthetic */ void p() {
        SpartanPlayer.a(10);
    }
    
    private static /* synthetic */ void n() {
        SpartanPlayer.a(1);
    }
}
