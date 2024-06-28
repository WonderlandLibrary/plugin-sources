package me.vagdedes.spartan.i;

import me.vagdedes.spartan.checks.b.PingSpoof;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class PingSpoofScheduler
{
    public PingSpoofScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> PingSpoof.a(NPC.a()), 0L, 0L);
    }
    
    private static /* synthetic */ void n() {
        PingSpoof.a(NPC.a());
    }
}
