package me.vagdedes.spartan.h;

import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ItemTeleporter
{
    private static final int ticks = 20;
    
    public ItemTeleporter() {
        super();
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "item-teleporter=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "item-teleporter=protection", n);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if (spartanPlayer.getItemInHand() != null && (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
            CooldownUtils.d(spartanPlayer, "item-teleporter=interact", 1);
            if (!CooldownUtils.g(spartanPlayer, "item-teleporter=teleport")) {
                g(spartanPlayer, 20);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerTeleportEvent.TeleportCause teleportCause) {
        if (teleportCause == PlayerTeleportEvent.TeleportCause.PLUGIN && spartanPlayer.getItemInHand() != null) {
            CooldownUtils.d(spartanPlayer, "item-teleporter=teleport", 1);
            if (!CooldownUtils.g(spartanPlayer, "item-teleporter=interact")) {
                g(spartanPlayer, 20);
            }
        }
    }
}
