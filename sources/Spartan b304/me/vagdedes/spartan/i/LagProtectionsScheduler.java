package me.vagdedes.spartan.i;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.Register;
import org.bukkit.Bukkit;

public class LagProtectionsScheduler
{
    public LagProtectionsScheduler() {
        super();
    }
    
    public static void run() {
        final SpartanPlayer[] array;
        int length;
        int i = 0;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Register.plugin, () -> {
            TPS.t();
            NPC.a();
            for (length = array.length; i < length; ++i) {
                LatencyUtils.b(array[i]);
            }
        }, 0L, 10L);
    }
    
    private static /* synthetic */ void n() {
        TPS.t();
        final SpartanPlayer[] a = NPC.a();
        for (int length = a.length, i = 0; i < length; ++i) {
            LatencyUtils.b(a[i]);
        }
    }
}
