package me.vagdedes.spartan.k.b;

import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.c.IPUtils;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.j.NPC;
import java.util.ArrayList;

public class Operators
{
    private static final ArrayList<String> b;
    
    public Operators() {
        super();
    }
    
    public static ArrayList<String> e() {
        return Operators.b;
    }
    
    public static void cache() {
        for (final SpartanPlayer spartanPlayer : NPC.a()) {
            final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            if (spartanPlayer.isOp() || PermissionUtils.a(player, Enums.Permission.admin) || PermissionUtils.a(player, Enums.Permission.verbose) || PermissionUtils.a(player, Enums.Permission.mining) || PermissionUtils.a(player, Enums.Permission.manage)) {
                final String a2 = IPUtils.a(player);
                if (a2 != null && !Operators.b.contains(a2)) {
                    Operators.b.add(a2);
                }
            }
        }
    }
    
    static {
        b = new ArrayList<String>(50);
    }
}
