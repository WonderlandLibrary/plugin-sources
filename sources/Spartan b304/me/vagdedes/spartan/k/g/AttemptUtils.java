package me.vagdedes.spartan.k.g;

import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.d.StringUtils;
import me.vagdedes.spartan.k.a.a.ElytraGlide;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.c.ProtocolLib;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AttemptUtils
{
    private int ac;
    private int ticks;
    private static final ConcurrentHashMap<UUID, ConcurrentHashMap<String, AttemptUtils>> a;
    
    public AttemptUtils(final int ac, final int ticks) {
        super();
        this.ac = ac;
        this.ticks = ticks;
    }
    
    private int o() {
        return this.ac;
    }
    
    private int getTicks() {
        return this.ticks;
    }
    
    private void d(final int ac) {
        this.ac = ac;
    }
    
    private void e(final int n) {
        this.ac += n;
    }
    
    private void f(final int n) {
        this.ac -= n;
    }
    
    private void setTicks(final int ticks) {
        this.ticks = ticks;
    }
    
    private void g(final int n) {
        this.ticks += n;
    }
    
    private void h(final int n) {
        this.ticks -= n;
    }
    
    public static void run() {
        for (final Map.Entry<UUID, ConcurrentHashMap<String, AttemptUtils>> entry : AttemptUtils.a.entrySet()) {
            final UUID key = (UUID)entry.getKey();
            for (final Map.Entry<String, AttemptUtils> entry2 : ((ConcurrentHashMap<String, AttemptUtils>)entry.getValue()).entrySet()) {
                final AttemptUtils value = (AttemptUtils)entry2.getValue();
                if (value.getTicks() > 0) {
                    value.h(1);
                    ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(key)).put(entry2.getKey(), value);
                }
            }
        }
    }
    
    public static void clear() {
        AttemptUtils.a.clear();
    }
    
    public static boolean a(final UUID uuid, final String key) {
        return AttemptUtils.a.containsKey(uuid) && ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).containsKey(key);
    }
    
    public static boolean f(final SpartanPlayer spartanPlayer, final String s) {
        return !ProtocolLib.F(spartanPlayer) && a(spartanPlayer.getUniqueId(), s);
    }
    
    public static int a(final UUID key, final String key2) {
        return a(key, key2) ? ((AttemptUtils)((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(key)).get(key2)).o() : 0;
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final String s) {
        return a(spartanPlayer.getUniqueId(), s);
    }
    
    public static void a(final UUID uuid, final String s, int n) {
        if (!AttemptUtils.a.containsKey(uuid)) {
            AttemptUtils.a.put(uuid, new ConcurrentHashMap<String, AttemptUtils>());
        }
        if (!MoveUtils.G) {
            n = -n;
        }
        if (((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).containsKey(s)) {
            final AttemptUtils value = (AttemptUtils)((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).get(s);
            value.d(n);
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).put(s, value);
        }
        else {
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).put(s, new AttemptUtils(n, 0));
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final String s, final int n) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        a(spartanPlayer.getUniqueId(), s, n);
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final String key, final int n) {
        if (ProtocolLib.F(spartanPlayer)) {
            return 0;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!AttemptUtils.a.containsKey(uniqueId)) {
            AttemptUtils.a.put(uniqueId, new ConcurrentHashMap<String, AttemptUtils>());
        }
        final AttemptUtils value = (AttemptUtils)((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).get(key);
        if (value != null) {
            value.e(n);
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).put(key, value);
            return value.o();
        }
        ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).put(key, new AttemptUtils(n, 0));
        return n;
    }
    
    public static int b(final SpartanPlayer spartanPlayer, final String key, final int ticks) {
        if (ProtocolLib.F(spartanPlayer)) {
            return 0;
        }
        if (ticks <= 0) {
            return 0;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!AttemptUtils.a.containsKey(uniqueId)) {
            AttemptUtils.a.put(uniqueId, new ConcurrentHashMap<String, AttemptUtils>());
        }
        final AttemptUtils value = ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).containsKey(key) ? ((AttemptUtils)((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).get(key)) : new AttemptUtils(0, 0);
        if (value.getTicks() != 0) {
            value.e(1);
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).put(key, value);
            return value.o();
        }
        final int o = value.o();
        value.setTicks(ticks);
        if (o >= 0) {
            value.d(1);
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).put(key, value);
            return 1;
        }
        ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).put(key, value);
        return o;
    }
    
    public static int b(final SpartanPlayer spartanPlayer, final String key) {
        return f(spartanPlayer, key) ? ((AttemptUtils)((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(spartanPlayer.getUniqueId())).get(key)).getTicks() : 0;
    }
    
    public static boolean g(final SpartanPlayer spartanPlayer, final String s) {
        return a(spartanPlayer, s) >= 0;
    }
    
    public static void m(final SpartanPlayer spartanPlayer, final String key) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (AttemptUtils.a.containsKey(uniqueId)) {
            ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).remove(key);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final String[] array) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (AttemptUtils.a.containsKey(uniqueId)) {
            for (int length = array.length, i = 0; i < length; ++i) {
                ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).remove(array[i]);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        AttemptUtils.a.remove(spartanPlayer.getUniqueId());
    }
    
    public static void b(final ArrayList<String> list) {
        if (AttemptUtils.a.size() == 0) {
            return;
        }
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final Map.Entry<UUID, ConcurrentHashMap<String, AttemptUtils>> entry : AttemptUtils.a.entrySet()) {
            final UUID uuid = (UUID)entry.getKey();
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null || !player.isOnline() || !ElytraGlide.bc(SpartanBukkit.a(player.getUniqueId()))) {
                final ConcurrentHashMap.KeySetView<String, AttemptUtils> keySet = ((ConcurrentHashMap<String, AttemptUtils>)entry.getValue()).keySet();
                for (final String e : keySet) {
                    if (!StringUtils.a(list, e)) {
                        list2.add(e);
                    }
                }
                if (keySet.size() == list2.size()) {
                    AttemptUtils.a.remove(uuid);
                }
                else {
                    final Iterator<String> iterator3 = list2.iterator();
                    while (iterator3.hasNext()) {
                        ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uuid)).remove(iterator3.next());
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
        if (AttemptUtils.a.size() == 0 || !AttemptUtils.a.containsKey(uniqueId)) {
            return;
        }
        final ArrayList<String> list = new ArrayList<String>();
        final ConcurrentHashMap.KeySetView<String, AttemptUtils> keySet = ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).keySet();
        for (final String e : keySet) {
            if (e.contains(s)) {
                list.add(e);
            }
        }
        if (keySet.size() == list.size()) {
            AttemptUtils.a.remove(uniqueId);
        }
        else {
            final Iterator<String> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((ConcurrentHashMap<String, AttemptUtils>)AttemptUtils.a.get(uniqueId)).remove(iterator2.next());
            }
        }
        list.clear();
    }
    
    static {
        a = new ConcurrentHashMap<UUID, ConcurrentHashMap<String, AttemptUtils>>();
    }
}
