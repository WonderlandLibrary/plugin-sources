package me.vagdedes.spartan.h;

import java.util.Iterator;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.List;
import org.bukkit.block.Block;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Piston
{
    private static final int ticks = 30;
    
    public Piston() {
        super();
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "piston=protection=has");
    }
    
    public static boolean Y(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "piston=protection=had");
    }
    
    public static void a(final Block block, final List<Block> list) {
        final SpartanLocation spartanLocation = new SpartanLocation(block.getLocation());
        for (final SpartanPlayer spartanPlayer : NPC.a()) {
            spartanPlayer.b(1);
            final SpartanLocation a2 = spartanPlayer.a();
            final double n = (BouncingBlocks.R(spartanPlayer) || BlockUtils.b(spartanPlayer, 2)) ? 3.5 : 2.5;
            if (MoveUtils.a(a2, spartanLocation) <= n || MoveUtils.a(a2.b().b(0.0, 1.0, 0.0), spartanLocation) <= n) {
                g(spartanPlayer, 30);
                return;
            }
            final Iterator<Block> iterator = list.iterator();
            while (iterator.hasNext()) {
                final SpartanLocation spartanLocation2 = new SpartanLocation(((Block)iterator.next()).getLocation());
                if (MoveUtils.a(a2, spartanLocation2) <= n || MoveUtils.a(a2.b().b(0.0, 1.0, 0.0), spartanLocation2) <= n) {
                    g(spartanPlayer, 30);
                }
            }
        }
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "piston=protection=has", n);
        CooldownUtils.d(spartanPlayer, "piston=protection=had", n * 2);
    }
}
