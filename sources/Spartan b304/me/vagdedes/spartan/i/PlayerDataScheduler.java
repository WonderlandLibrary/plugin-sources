package me.vagdedes.spartan.i;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class PlayerDataScheduler
{
    public PlayerDataScheduler() {
        super();
    }
    
    public static void run() {
        final SpartanPlayer[] array;
        int length;
        int i = 0;
        SpartanPlayer spartanPlayer;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> {
            NPC.a();
            for (length = array.length; i < length; ++i) {
                spartanPlayer = array[i];
                spartanPlayer.b(1);
                PlayerData.b(spartanPlayer);
            }
        }, 0L, 0L);
    }
    
    private static /* synthetic */ void n() {
        for (final SpartanPlayer spartanPlayer : NPC.a()) {
            spartanPlayer.b(1);
            PlayerData.b(spartanPlayer);
        }
    }
}
