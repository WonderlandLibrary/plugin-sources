package me.vagdedes.spartan.f.a;

import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;

public class SpartanBukkit
{
    public SpartanBukkit() {
        super();
    }
    
    public static SpartanPlayer a(final UUID key, final int n) {
        final SpartanPlayer spartanPlayer = (SpartanPlayer)SpartanPlayer.d.get(key);
        if (spartanPlayer == null) {
            return null;
        }
        spartanPlayer.b(n);
        if (n == 2) {
            spartanPlayer.a(12.0);
        }
        return spartanPlayer;
    }
    
    public static SpartanPlayer a(final UUID uuid) {
        return a(uuid, 0);
    }
    
    public static SpartanPlayer a(final Player player, final int n) {
        final SpartanPlayer spartanPlayer = new SpartanPlayer(player);
        spartanPlayer.b(n);
        if (n == 2) {
            spartanPlayer.a(12.0);
        }
        return spartanPlayer;
    }
    
    public static SpartanPlayer a(final Player player) {
        return a(player, 0);
    }
    
    public static SpartanPlayer b(final Player player, final int n) {
        final SpartanPlayer a = a(player.getUniqueId(), n);
        if (a != null) {
            return a;
        }
        return a(player, n);
    }
    
    public static SpartanPlayer b(final Player player) {
        return b(player, 0);
    }
    
    public static SpartanPlayer b(final UUID key) {
        final SpartanPlayer a = a(key);
        if (a != null) {
            a.f(false);
            SpartanPlayer.d.remove(key);
            return a;
        }
        return null;
    }
}
