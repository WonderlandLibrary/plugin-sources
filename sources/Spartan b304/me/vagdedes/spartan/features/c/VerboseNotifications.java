package me.vagdedes.spartan.features.c;

import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.a.Language;
import java.util.Iterator;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class VerboseNotifications
{
    private static final HashMap<UUID, Integer> I;
    private static boolean k;
    
    public VerboseNotifications() {
        super();
    }
    
    public static boolean g() {
        return VerboseNotifications.k;
    }
    
    public static void j() {
        VerboseNotifications.k = true;
    }
    
    public static void k() {
        VerboseNotifications.k = false;
    }
    
    public static Player[] a() {
        final ArrayList<Player> list = new ArrayList<Player>();
        final Iterator<UUID> iterator = VerboseNotifications.I.keySet().iterator();
        while (iterator.hasNext()) {
            final Player player = Bukkit.getPlayer((UUID)iterator.next());
            if (d(player)) {
                list.add(player);
            }
        }
        return list.<Player>toArray(new Player[0]);
    }
    
    public static boolean c(final Player player) {
        return player != null && VerboseNotifications.I.containsKey(player.getUniqueId());
    }
    
    public static void a(final Player player, final int i) {
        if (i != 0 && i != 1) {
            return;
        }
        if (!c(player)) {
            VerboseNotifications.I.put(player.getUniqueId(), Integer.valueOf(i));
            player.sendMessage(Language.getMessage("verbose_enable"));
        }
        else {
            VerboseNotifications.I.remove(player.getUniqueId());
            player.sendMessage(Language.getMessage("verbose_disable"));
        }
    }
    
    public static void a(final Player player, final boolean b, final int i) {
        if (i != 0 && i != 1) {
            return;
        }
        if (b) {
            if (!c(player)) {
                VerboseNotifications.I.put(player.getUniqueId(), Integer.valueOf(i));
                player.sendMessage(Language.getMessage("verbose_enable"));
            }
        }
        else if (c(player)) {
            VerboseNotifications.I.remove(player.getUniqueId());
            player.sendMessage(Language.getMessage("verbose_disable"));
        }
    }
    
    public static boolean d(final Player player) {
        return player != null && VerboseNotifications.I.containsKey(player.getUniqueId()) && (Integer.valueOf(VerboseNotifications.I.get((Object)player.getUniqueId())) == 1 || (Integer.valueOf(VerboseNotifications.I.get((Object)player.getUniqueId())) == 0 && PermissionUtils.a(player, Enums.Permission.verbose))) && PermissionUtils.a(player, Enums.Permission.verbose);
    }
    
    static {
        I = new HashMap<UUID, Integer>();
        VerboseNotifications.k = false;
    }
}
