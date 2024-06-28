package me.vagdedes.spartan.k.c;

import java.util.Iterator;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.j.NPC;
import java.net.InetSocketAddress;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class IPUtils
{
    private static final ArrayList<String> b;
    
    public IPUtils() {
        super();
    }
    
    public static String a(final Player player) {
        if (player == null || !player.isOnline()) {
            return null;
        }
        final InetSocketAddress address = player.getAddress();
        return (address == null || address.getAddress() == null) ? null : address.getAddress().toString().substring(1);
    }
    
    public static void cache() {
        final SpartanPlayer[] a = NPC.a();
        for (int length = a.length, i = 0; i < length; ++i) {
            final String a2 = a(Bukkit.getPlayer(a[i].getUniqueId()));
            if (a2 != null) {
                IPUtils.b.add(a2);
            }
        }
    }
    
    public static boolean g(final Player player) {
        final String a = a(player);
        if (a == null) {
            return false;
        }
        int n = 0;
        final Iterator<String> iterator = IPUtils.b.iterator();
        while (iterator.hasNext()) {
            if (a.equals(iterator.next())) {
                ++n;
            }
        }
        final int b = Settings.b("player_limit_per_ip");
        if (b > 0 && n > b && !PermissionUtils.a(player, Enums.Permission.ip_limit)) {
            final Player player2 = Bukkit.getPlayer(player.getUniqueId());
            player2.kickPlayer(ConfigUtils.a((OfflinePlayer)player2, Language.getMessage("player_ip_limit_kick_message"), (Enums.HackType)null));
            return true;
        }
        IPUtils.b.add(a);
        return false;
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        IPUtils.b.remove(a(Bukkit.getPlayer(spartanPlayer.getUniqueId())));
    }
    
    static {
        b = new ArrayList<String>();
    }
}
