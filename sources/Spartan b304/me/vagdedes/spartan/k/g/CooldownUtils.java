package me.vagdedes.spartan.k.g;

import org.bukkit.entity.Player;
import java.util.Iterator;
import me.vagdedes.spartan.k.d.StringUtils;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.Bukkit;
import java.util.Map;
import java.util.ArrayList;
import me.vagdedes.spartan.c.ProtocolLib;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownUtils
{
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, Long>> e;
    
    public CooldownUtils() {
        super();
    }
    
    public static void clear() {
        CooldownUtils.e.clear();
    }
    
    public static boolean a(final UUID uuid, final String key) {
        return CooldownUtils.e.containsKey(uuid) && ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(uuid)).containsKey(key);
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer, final String s) {
        return !ProtocolLib.F(spartanPlayer) && a(spartanPlayer.getUniqueId(), s);
    }
    
    public static int a(final UUID key, final String key2) {
        return a(key, key2) ? ((int)Math.max(0L, (long)(int)(Long.valueOf(((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(key)).get((Object)key2)) - System.currentTimeMillis()) / 50L)) : 0;
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final String s) {
        return a(spartanPlayer.getUniqueId(), s);
    }
    
    public static boolean g(final SpartanPlayer spartanPlayer, final String s) {
        return a(spartanPlayer, s) == 0;
    }
    
    public static boolean b(final UUID uuid, final String s) {
        return a(uuid, s) == 0;
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final String s, final int n) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        b(spartanPlayer.getUniqueId(), s, n);
    }
    
    public static void b(final UUID key, final String key2, final int n) {
        if (n <= 0) {
            return;
        }
        if (!CooldownUtils.e.containsKey(key)) {
            CooldownUtils.e.put(key, new ConcurrentHashMap<String, Long>());
        }
        ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(key)).put(key2, Long.valueOf(System.currentTimeMillis() + n * 50L));
    }
    
    public static void j(final SpartanPlayer spartanPlayer, final String key) {
        if (ProtocolLib.F(spartanPlayer) || !f(spartanPlayer, key)) {
            return;
        }
        ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(spartanPlayer.getUniqueId())).put(key, Long.valueOf(System.currentTimeMillis()));
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final String[] array) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (CooldownUtils.e.containsKey(uniqueId)) {
            for (int length = array.length, i = 0; i < length; ++i) {
                ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(uniqueId)).remove(array[i]);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        CooldownUtils.e.remove(spartanPlayer.getUniqueId());
    }
    
    public static void b(final ArrayList<String> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final Map.Entry<UUID, ConcurrentHashMap<String, Long>> entry : CooldownUtils.e.entrySet()) {
            final UUID uuid = (UUID)entry.getKey();
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline() || !ElytraGlide.bc(SpartanBukkit.a(player.getUniqueId()))) {
                final ConcurrentHashMap.KeySetView<String, Long> keySet = ((ConcurrentHashMap<String, Long>)entry.getValue()).keySet();
                for (final String e : keySet) {
                    if (!StringUtils.a(list, e)) {
                        list2.add(e);
                    }
                }
                if (keySet.size() == list2.size()) {
                    CooldownUtils.e.remove(uuid);
                }
                else {
                    final Iterator<String> iterator3 = list2.iterator();
                    while (iterator3.hasNext()) {
                        ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(uuid)).remove(iterator3.next());
                    }
                }
            }
        }
        list2.clear();
    }
    
    public static void n(final SpartanPlayer spartanPlayer, final String s) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (CooldownUtils.e.size() == 0 || !CooldownUtils.e.containsKey(uniqueId)) {
            return;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final ConcurrentHashMap.KeySetView<String, Long> keySet = ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(uniqueId)).keySet();
        for (final String e : keySet) {
            if (e.contains(s)) {
                list.add(e);
            }
        }
        if (keySet.size() == list.size()) {
            CooldownUtils.e.remove(uniqueId);
        }
        else {
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((ConcurrentHashMap<String, Long>)CooldownUtils.e.get(uniqueId)).remove(iterator2.next());
            }
        }
        list.clear();
    }
    
    static {
        e = new ConcurrentHashMap<UUID, ConcurrentHashMap<String, Long>>();
    }
}
