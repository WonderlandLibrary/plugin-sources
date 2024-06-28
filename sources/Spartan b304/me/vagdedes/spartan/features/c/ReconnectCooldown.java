package me.vagdedes.spartan.features.c;

import java.util.Iterator;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class ReconnectCooldown
{
    private static final HashMap<UUID, Long> H;
    
    public ReconnectCooldown() {
        super();
    }
    
    public static void a(final Player player, final PlayerLoginEvent playerLoginEvent) {
        final int b = Settings.b("reconnect_cooldown");
        if (b <= 0) {
            return;
        }
        if (!PermissionUtils.a(player, Enums.Permission.reconnect)) {
            final UUID uniqueId = player.getUniqueId();
            if (ReconnectCooldown.H.containsKey(uniqueId) && System.currentTimeMillis() - Long.valueOf(ReconnectCooldown.H.get((Object)uniqueId)) <= b * 1000L) {
                playerLoginEvent.disallow(PlayerLoginEvent.Result.KICK_OTHER, ConfigUtils.a((OfflinePlayer)Bukkit.getPlayer(player.getUniqueId()), Language.getMessage("reconnect_kick_message"), (Enums.HackType)null));
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        ReconnectCooldown.H.put(spartanPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
    }
    
    public static void i() {
        if (VersionUtils.a() != VersionUtils.a.c) {
            final Iterator<Player> iterator = Bukkit.getOnlinePlayers().iterator();
            while (iterator.hasNext()) {
                ReconnectCooldown.H.put(((Player)iterator.next()).getUniqueId(), Long.valueOf(System.currentTimeMillis()));
            }
        }
    }
    
    static {
        H = new HashMap<UUID, Long>();
    }
}
