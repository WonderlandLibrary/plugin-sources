package me.vagdedes.spartan.i;

import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class ClientSidedBlockScheduler
{
    public ClientSidedBlockScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, ClientSidedBlock::run, 0L, 0L);
    }
}
