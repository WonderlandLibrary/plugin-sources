package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;

public class FenceClick
{
    private static final boolean c;
    
    public FenceClick() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        return FenceClick.c && action == Action.RIGHT_CLICK_BLOCK && (spartanBlock.getType() == Material.getMaterial("FENCE") || spartanBlock.getType() == Material.getMaterial("NETHER_FENCE")) && spartanPlayer.getItemInHand() != null && !spartanPlayer.getItemInHand().getType().isBlock();
    }
    
    static {
        c = (VersionUtils.a() == VersionUtils.a.c || VersionUtils.a() == VersionUtils.a.l);
    }
}
