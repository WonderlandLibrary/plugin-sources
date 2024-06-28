package me.vagdedes.spartan.i;

import me.vagdedes.spartan.h.ShulkerBox;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class ShulkerBoxScheduler
{
    public ShulkerBoxScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> ShulkerBox.a(NPC.a()), 0L, 0L);
    }
    
    private static /* synthetic */ void n() {
        ShulkerBox.a(NPC.a());
    }
}
