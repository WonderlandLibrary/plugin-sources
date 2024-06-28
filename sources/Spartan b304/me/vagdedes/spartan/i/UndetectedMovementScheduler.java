package me.vagdedes.spartan.i;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class UndetectedMovementScheduler
{
    public UndetectedMovementScheduler() {
        super();
    }
    
    public static void run() {
        final SpartanPlayer[] array;
        int length;
        int i = 0;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> {
            NPC.a();
            for (length = array.length; i < length; ++i) {
                UndetectedMovement.b(array[i]);
            }
        }, 0L, 0L);
    }
    
    private static /* synthetic */ void n() {
        final SpartanPlayer[] a = NPC.a();
        for (int length = a.length, i = 0; i < length; ++i) {
            UndetectedMovement.b(a[i]);
        }
    }
}
