package me.vagdedes.spartan.checks.combat.a.a;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.checks.combat.a.d.CAHistory;
import me.vagdedes.spartan.checks.combat.c.KillAuraOverall;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.ArrayList;

public class CATraining
{
    public static final ArrayList<UUID> a;
    
    public CATraining() {
        super();
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer) {
        return f(spartanPlayer) && VL.o(spartanPlayer) == 0 && !LatencyUtils.e(spartanPlayer, 200) && !NoHitDelay.E(spartanPlayer);
    }
    
    public static boolean e(final SpartanPlayer spartanPlayer) {
        return VL.o(spartanPlayer) <= 3 && VL.a(spartanPlayer, Enums.HackType.KillAura) == 0 && KillAuraOverall.j(spartanPlayer) < 5 && CAHistory.c(spartanPlayer) >= 3 && !LatencyUtils.e(spartanPlayer, 200);
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer) {
        return CATraining.a.contains(spartanPlayer.getUniqueId());
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final UUID uniqueId = spartanPlayer2.getUniqueId();
        String s;
        if (CATraining.a.contains(uniqueId)) {
            s = ChatColor.RED + "Combat-Analysis (" + spartanPlayer2.getName() + ")";
            for (final UUID uuid : CATraining.a) {
                if (!uuid.equals(uniqueId)) {
                    Bukkit.getPlayer(uuid).sendMessage(s);
                }
            }
            CATraining.a.remove(uniqueId);
        }
        else {
            s = ChatColor.GREEN + "Combat-Analysis (" + spartanPlayer2.getName() + ")";
            CATraining.a.add(uniqueId);
            for (final UUID uuid2 : CATraining.a) {
                if (!uuid2.equals(uniqueId)) {
                    Bukkit.getPlayer(uuid2).sendMessage(s);
                }
            }
        }
        Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(s);
    }
    
    public static void a(final SpartanPlayer o) {
        CATraining.a.remove(o);
    }
    
    public static void clear() {
        CATraining.a.clear();
    }
    
    static {
        a = new ArrayList<UUID>();
    }
}
