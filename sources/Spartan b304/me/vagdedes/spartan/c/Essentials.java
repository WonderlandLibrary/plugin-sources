package me.vagdedes.spartan.c;

import org.bukkit.Bukkit;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Essentials
{
    public Essentials() {
        super();
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final String s) {
        final Compatibility compatibility = new Compatibility("Essentials");
        if (compatibility.isEnabled() && (PluginUtils.exists("essentials") || compatibility.c())) {
            if (a(spartanPlayer, s, "break")) {
                VL.a(spartanPlayer, Enums.HackType.NoSwing, 10);
                VL.a(spartanPlayer, Enums.HackType.GhostHand, 10);
                VL.a(spartanPlayer, Enums.HackType.Liquids, 10);
            }
            else if (a(spartanPlayer, s, "feed")) {
                VL.a(spartanPlayer, Enums.HackType.FastEat, 10);
            }
        }
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final String s, final String str) {
        return ((Bukkit.getPlayer(spartanPlayer.getUniqueId()).hasPermission("essentials." + str) || spartanPlayer.isOp()) && s.toLowerCase().startsWith("/" + str + " ")) || s.equalsIgnoreCase("/" + str);
    }
}
