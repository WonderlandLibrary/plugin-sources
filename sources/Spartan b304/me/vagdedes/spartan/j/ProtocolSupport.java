package me.vagdedes.spartan.j;

import me.vagdedes.spartan.k.b.ReflectionUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import protocolsupport.api.Connection;
import protocolsupport.api.ProtocolSupportAPI;
import me.vagdedes.spartan.k.b.PluginUtils;
import org.bukkit.entity.Player;

public class ProtocolSupport
{
    private static final boolean h;
    
    public ProtocolSupport() {
        super();
    }
    
    public static boolean f(final Player player) {
        if (PluginUtils.exists("ProtocolSupport") && ProtocolSupport.h) {
            final Connection connection = ProtocolSupportAPI.getConnection(player);
            return connection != null && connection.getVersion().toString().contains("_PE_");
        }
        return false;
    }
    
    public static boolean ad(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.l();
    }
    
    static {
        h = (ReflectionUtils.e("protocolsupport.api.Connection") && ReflectionUtils.e("protocolsupport.api.ProtocolSupportAPI"));
    }
}
