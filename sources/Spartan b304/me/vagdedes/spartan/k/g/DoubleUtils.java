package me.vagdedes.spartan.k.g;

import java.util.Iterator;
import java.util.ArrayList;
import me.vagdedes.spartan.c.ProtocolLib;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DoubleUtils
{
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, Double>> f;
    
    public DoubleUtils() {
        super();
    }
    
    public static double a(final SpartanPlayer spartanPlayer, final String s) {
        if (ProtocolLib.F(spartanPlayer)) {
            return Double.MAX_VALUE;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return (DoubleUtils.f.containsKey(uniqueId) && ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(uniqueId)).containsKey(s)) ? ((double)Double.valueOf(((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(uniqueId)).get((Object)s))) : Double.MAX_VALUE;
    }
    
    public static boolean h(final double n) {
        return n != Double.MAX_VALUE;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final String[] array) {
        int n = 0;
        if (DoubleUtils.f.containsKey(spartanPlayer.getUniqueId())) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(spartanPlayer.getUniqueId())).containsKey(array[i])) {
                    ++n;
                }
            }
        }
        return n == array.length;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final String key, final double d) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        if (!DoubleUtils.f.containsKey(spartanPlayer.getUniqueId())) {
            DoubleUtils.f.put(spartanPlayer.getUniqueId(), new ConcurrentHashMap<String, Double>());
        }
        ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(spartanPlayer.getUniqueId())).put(key, Double.valueOf(d));
    }
    
    public static void j(final SpartanPlayer spartanPlayer, final String key) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        if (DoubleUtils.f.containsKey(spartanPlayer.getUniqueId())) {
            ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(spartanPlayer.getUniqueId())).remove(key);
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final String[] array) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        spartanPlayer.getUniqueId();
        if (DoubleUtils.f.containsKey(spartanPlayer.getUniqueId())) {
            for (int length = array.length, i = 0; i < length; ++i) {
                ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(spartanPlayer.getUniqueId())).remove(array[i]);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        DoubleUtils.f.remove(spartanPlayer.getUniqueId());
    }
    
    public static void n(final SpartanPlayer spartanPlayer, final String s) {
        if (ProtocolLib.F(spartanPlayer) || DoubleUtils.f.size() == 0 || !DoubleUtils.f.containsKey(spartanPlayer.getUniqueId())) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (DoubleUtils.f.size() == 0 || !DoubleUtils.f.containsKey(uniqueId)) {
            return;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final ConcurrentHashMap.KeySetView<String, Double> keySet = ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(uniqueId)).keySet();
        for (final String e : keySet) {
            if (e.contains(s)) {
                list.add(e);
            }
        }
        if (keySet.size() == list.size()) {
            DoubleUtils.f.remove(uniqueId);
        }
        else {
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((ConcurrentHashMap<String, Double>)DoubleUtils.f.get(uniqueId)).remove(iterator2.next());
            }
        }
        list.clear();
    }
    
    static {
        f = new ConcurrentHashMap<UUID, ConcurrentHashMap<String, Double>>();
    }
}
