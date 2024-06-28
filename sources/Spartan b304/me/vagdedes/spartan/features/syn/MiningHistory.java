package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.Material;
import java.util.HashMap;

public class MiningHistory
{
    private HashMap<Material, Integer> N;
    private HashMap<Material, ArrayList<String>> O;
    
    public MiningHistory(final HashMap<Material, Integer> n, final HashMap<Material, ArrayList<String>> o) {
        super();
        this.N = n;
        this.O = o;
    }
    
    public HashMap<Material, Integer> a() {
        return this.N;
    }
    
    public HashMap<Material, ArrayList<String>> b() {
        return this.O;
    }
    
    public static ArrayList<String> a(final Player player, final MiningHistory miningHistory) {
        final ArrayList<String> list = new ArrayList<String>();
        if (SynManager.s()) {
            final HashMap<Material, Integer> a = miningHistory.a();
            final HashMap<Material, ArrayList<String>> b = miningHistory.b();
            list.add("");
            list.add("§7Mining History§8:");
            for (final Material material : MiningNotifications.a) {
                list.add("§c" + (a.containsKey(material) ? ((int)Integer.valueOf(a.get((Object)material))) : 0) + " §7" + material.toString().toLowerCase() + "(s) in §c" + (b.containsKey(material) ? ((ArrayList<String>)b.get(material)).size() : 0) + " §7day(s).");
            }
            return list;
        }
        list.add(Advertisement.a(player, "Mining History Information"));
        list.add(Advertisement.d());
        return list;
    }
}
