package me.vagdedes.spartan.c;

import me.vagdedes.spartan.k.b.ReflectionUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import com.comphenix.protocol.injector.server.TemporaryPlayer;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.b.PluginUtils;

public class ProtocolLib
{
    private static final boolean h;
    
    public ProtocolLib() {
        super();
    }
    
    public static boolean exists() {
        return PluginUtils.exists("protocollib");
    }
    
    public static boolean b(final Player player) {
        return exists() && ProtocolLib.h && player instanceof TemporaryPlayer;
    }
    
    public static boolean F(final SpartanPlayer spartanPlayer) {
        return spartanPlayer == null || spartanPlayer.getPlayer() == null || spartanPlayer.k();
    }
    
    static {
        h = ReflectionUtils.e("com.comphenix.protocol.injector.server.TemporaryPlayer");
    }
}
