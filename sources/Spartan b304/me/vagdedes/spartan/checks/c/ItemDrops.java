package me.vagdedes.spartan.checks.c;

import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.Register;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.system.VL;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ItemDrops
{
    private static final Enums.HackType a;
    
    public ItemDrops() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        if (!b(spartanPlayer)) {
            return;
        }
        final long a = MillisUtils.a(spartanPlayer, ItemDrops.a.toString() + "=time");
        if (a <= LagManagement.a(spartanPlayer, 50L) && AttemptUtils.b(spartanPlayer, ItemDrops.a.toString() + "=attempts", 10) >= 8) {
            new HackPrevention(spartanPlayer, ItemDrops.a, "ms: " + a, 10);
        }
        MillisUtils.o(spartanPlayer, ItemDrops.a.toString() + "=time");
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (action == Action.RIGHT_CLICK_BLOCK && spartanBlock.getType() == MaterialUtils.a("water") && spartanPlayer.getItemInHand().getType() == Material.GLASS_BOTTLE) {
            VL.a(spartanPlayer, ItemDrops.a, 10);
        }
    }
    
    public static void a(final String s) {
        final String[] split = s.split(" ");
        for (int length = split.length, i = 0; i < length; ++i) {
            final Player player = Bukkit.getPlayer(split[i]);
            if (player != null && player.isOnline()) {
                VL.a(SpartanBukkit.a(player.getUniqueId()), ItemDrops.a, 5);
            }
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !Register.v1_13 && !VL.b(spartanPlayer, ItemDrops.a, true) && !PlayerData.ba(spartanPlayer);
    }
    
    static {
        a = Enums.HackType.ItemDrops;
    }
}
