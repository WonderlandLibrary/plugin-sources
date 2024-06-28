package me.vagdedes.spartan.j;

import org.bukkit.entity.Player;
import us.myles.ViaVersion.api.Via;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ViaVersion
{
    public static final int[] b;
    public static final int[] c;
    public static final int[] d;
    public static final int[] e;
    public static final int[] f;
    public static final int[] g;
    public static final int[] h;
    public static final int[] i;
    
    public ViaVersion() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final int[] array) {
        if (spartanPlayer != null) {
            final Compatibility compatibility = new Compatibility("ViaVersion");
            if (compatibility.isEnabled() && (PluginUtils.exists("ViaVersion") || compatibility.c())) {
                final Player player = spartanPlayer.getPlayer();
                if (player != null) {
                    final int playerVersion = Via.getAPI().getPlayerVersion((Object)player);
                    for (int length = array.length, i = 0; i < length; ++i) {
                        if (playerVersion == array[i]) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    static {
        b = new int[] { 490, 485, 480, 477 };
        c = new int[] { 404, 401, 393 };
        d = new int[] { 340, 338, 335 };
        e = new int[] { 316, 315 };
        f = new int[] { 210 };
        g = new int[] { 110, 109, 108, 107 };
        h = new int[] { 47 };
        i = new int[] { 5, 4 };
    }
}
