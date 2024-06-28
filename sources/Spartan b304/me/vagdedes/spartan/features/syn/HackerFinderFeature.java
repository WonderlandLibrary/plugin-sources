package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.system.Enums;
import java.util.ArrayList;
import org.bukkit.OfflinePlayer;

public class HackerFinderFeature
{
    private OfflinePlayer a;
    private ArrayList<Enums.HackType> c;
    private ViolationHistory a;
    
    public HackerFinderFeature(final OfflinePlayer a, final ArrayList<Enums.HackType> c, final ViolationHistory a2) {
        super();
        this.a = a;
        this.c = c;
        this.a = a2;
    }
    
    public OfflinePlayer a() {
        return this.a;
    }
    
    public ArrayList<Enums.HackType> b() {
        return this.c;
    }
    
    public ViolationHistory b() {
        return this.a;
    }
    
    public static ItemStack getItem() {
        return new ItemStack(MaterialUtils.a("watch"));
    }
    
    public static ArrayList<String> a(final Player player) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        if (SynManager.s()) {
            list.add("§7Click to view possible hackers.");
            list.add("");
            list.add("§cWARNING! Upon loading inventory data, the server");
            list.add("§cmay freeze, and TPS may temporarily drop.");
            return list;
        }
        list.add(Advertisement.a(player, "Hacker Finder Menu"));
        list.add(Advertisement.d());
        return list;
    }
}
