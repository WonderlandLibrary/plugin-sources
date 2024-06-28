package me.vagdedes.spartan.k.c;

import me.vagdedes.spartan.j.NPC;
import java.util.ArrayList;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.system.Enums;
import java.util.UUID;
import java.util.HashMap;

public class PermissionUtils
{
    private static final HashMap<UUID, HashMap<Enums.Permission, Boolean>> ac;
    private static final HashMap<UUID, HashMap<Enums.HackType, Boolean>> ad;
    
    public PermissionUtils() {
        super();
    }
    
    public static void clear() {
        PermissionUtils.ac.clear();
        PermissionUtils.ad.clear();
    }
    
    public static void a(final Player player, final Enums.Permission key) {
        final UUID uniqueId = player.getUniqueId();
        if (!PermissionUtils.ac.containsKey(uniqueId)) {
            PermissionUtils.ac.put(uniqueId, new HashMap<Enums.Permission, Boolean>());
        }
        ((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).put(key, Boolean.valueOf(true));
    }
    
    public static boolean has(final Player player) {
        final Enums.Permission[] values = Enums.Permission.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (a(player, values[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean a(final Player player, final Enums.Permission key) {
        if (!Settings.canDo("enable_permissions")) {
            return true;
        }
        final UUID uniqueId = player.getUniqueId();
        if (PermissionUtils.ac.containsKey(uniqueId) && ((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).containsKey(key)) {
            return Boolean.valueOf(((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).get((Object)key));
        }
        final Enums.Permission admin = Enums.Permission.admin;
        final boolean b = key == admin;
        boolean booleanValue = player.hasPermission("spartan." + key.toString().toLowerCase()) || (b && player.hasPermission("spartan.*"));
        if (!PermissionUtils.ac.containsKey(uniqueId)) {
            PermissionUtils.ac.put(uniqueId, new HashMap<Enums.Permission, Boolean>());
        }
        if (!booleanValue && !b) {
            if (!((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).containsKey(admin)) {
                booleanValue = (player.hasPermission("spartan.admin") || player.hasPermission("spartan.*"));
                ((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).put(admin, Boolean.valueOf(booleanValue));
            }
            else {
                booleanValue = Boolean.valueOf(((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).get((Object)admin));
            }
        }
        ((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).put(key, Boolean.valueOf(booleanValue));
        return booleanValue;
    }
    
    public static boolean isBypassing(final Player player, final Enums.HackType key) {
        if (!Settings.canDo("enable_permissions")) {
            return true;
        }
        final UUID uniqueId = player.getUniqueId();
        if (PermissionUtils.ad.containsKey(uniqueId) && ((HashMap<Enums.HackType, Boolean>)PermissionUtils.ad.get(uniqueId)).containsKey(key)) {
            return Boolean.valueOf(((HashMap<Enums.HackType, Boolean>)PermissionUtils.ad.get(uniqueId)).get((Object)key));
        }
        final boolean hasPermission = player.hasPermission("spartan.bypass." + key.toString().toLowerCase());
        if (!PermissionUtils.ad.containsKey(uniqueId)) {
            PermissionUtils.ad.put(uniqueId, new HashMap<Enums.HackType, Boolean>());
        }
        ((HashMap<Enums.HackType, Boolean>)PermissionUtils.ad.get(uniqueId)).put(key, Boolean.valueOf(hasPermission));
        return hasPermission;
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        PermissionUtils.ac.remove(uniqueId);
        PermissionUtils.ad.remove(uniqueId);
    }
    
    public static ArrayList<SpartanPlayer> f() {
        final ArrayList<SpartanPlayer> list = new ArrayList<SpartanPlayer>();
        final Enums.Permission[] array = { Enums.Permission.report, Enums.Permission.staff_chat, Enums.Permission.wave, Enums.Permission.admin, Enums.Permission.kick, Enums.Permission.verbose, Enums.Permission.use_bypass, Enums.Permission.manage, Enums.Permission.info, Enums.Permission.warn, Enums.Permission.ban, Enums.Permission.unban, Enums.Permission.ban_info, Enums.Permission.mining, Enums.Permission.debug };
        for (final SpartanPlayer e : NPC.a()) {
            final UUID uniqueId = e.getUniqueId();
            if (PermissionUtils.ac.containsKey(uniqueId)) {
                for (final Enums.Permission permission : array) {
                    if (((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).containsKey(permission) && Boolean.valueOf(((HashMap<Enums.Permission, Boolean>)PermissionUtils.ac.get(uniqueId)).get((Object)permission))) {
                        list.add(e);
                        break;
                    }
                }
            }
        }
        return list;
    }
    
    static {
        ac = new HashMap<UUID, HashMap<Enums.Permission, Boolean>>();
        ad = new HashMap<UUID, HashMap<Enums.HackType, Boolean>>();
    }
}
