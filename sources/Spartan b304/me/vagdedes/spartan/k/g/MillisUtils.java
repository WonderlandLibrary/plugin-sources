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

public class MillisUtils
{
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, Long>> e;
    
    public MillisUtils() {
        super();
    }
    
    public static void clear() {
        MillisUtils.e.clear();
    }
    
    public static boolean hasTimer(final long n) {
        return n != Long.MAX_VALUE;
    }
    
    public static long a(final UUID key, final String s) {
        return (MillisUtils.e.containsKey(key) && ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(key)).containsKey(s)) ? (System.currentTimeMillis() - Long.valueOf(((ConcurrentHashMap<String, Long>)MillisUtils.e.get(key)).get((Object)s))) : Long.MAX_VALUE;
    }
    
    public static long a(final SpartanPlayer spartanPlayer, final String s) {
        if (ProtocolLib.F(spartanPlayer)) {
            return 0L;
        }
        return a(spartanPlayer.getUniqueId(), s);
    }
    
    public static void b(final UUID key, final String key2) {
        if (!MillisUtils.e.containsKey(key)) {
            MillisUtils.e.put(key, new ConcurrentHashMap<String, Long>());
        }
        ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(key)).put(key2, Long.valueOf(System.currentTimeMillis()));
    }
    
    public static void o(final SpartanPlayer spartanPlayer, final String s) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        b(spartanPlayer.getUniqueId(), s);
    }
    
    public static void j(final SpartanPlayer spartanPlayer, final String key) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (MillisUtils.e.containsKey(uniqueId)) {
            ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(uniqueId)).remove(key);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        MillisUtils.e.remove(spartanPlayer.getUniqueId());
    }
    
    public static void b(final ArrayList<String> list) {
        if (MillisUtils.e.size() == 0) {
            return;
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final Map.Entry<UUID, ConcurrentHashMap<String, Long>> entry : MillisUtils.e.entrySet()) {
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
                    MillisUtils.e.remove(uuid);
                }
                else {
                    final Iterator<String> iterator3 = list2.iterator();
                    while (iterator3.hasNext()) {
                        ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(uuid)).remove(iterator3.next());
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
        if (MillisUtils.e.size() == 0 || !MillisUtils.e.containsKey(uniqueId)) {
            return;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final ConcurrentHashMap.KeySetView<String, Long> keySet = ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(uniqueId)).keySet();
        for (final String e : keySet) {
            if (e.contains(s)) {
                list.add(e);
            }
        }
        if (keySet.size() == list.size()) {
            MillisUtils.e.remove(uniqueId);
        }
        else {
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((ConcurrentHashMap<String, Long>)MillisUtils.e.get(uniqueId)).remove(iterator2.next());
            }
        }
        list.clear();
    }
    
    static {
        e = new ConcurrentHashMap<UUID, ConcurrentHashMap<String, Long>>();
    }
}
