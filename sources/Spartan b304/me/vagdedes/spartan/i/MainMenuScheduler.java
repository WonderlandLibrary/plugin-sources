package me.vagdedes.spartan.i;

import me.vagdedes.spartan.d.MainMenu;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class MainMenuScheduler
{
    public MainMenuScheduler() {
        super();
    }
    
    public static void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> MainMenu.a(NPC.a()), 0L, 50L);
    }
    
    private static /* synthetic */ void n() {
        MainMenu.a(NPC.a());
    }
}
