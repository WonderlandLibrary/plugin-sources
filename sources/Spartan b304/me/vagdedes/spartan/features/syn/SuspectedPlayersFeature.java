package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.d.SuspectedPlayers;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.system.Enums;
import java.util.ArrayList;
import org.bukkit.entity.Player;

public class SuspectedPlayersFeature
{
    private Player player;
    private ArrayList<Enums.HackType> d;
    
    public SuspectedPlayersFeature(final Player player, final ArrayList<Enums.HackType> d) {
        super();
        this.player = player;
        this.d = d;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public ArrayList<Enums.HackType> c() {
        return this.d;
    }
    
    public static ItemStack getItem() {
        return new ItemStack(Material.COMPASS);
    }
    
    public static ArrayList<String> a(final Player player) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        if (SynManager.s()) {
            list.add("§7Suspicions Made§8:§c " + SuspectedPlayers.d().size());
            return list;
        }
        list.add(Advertisement.a(player, "Suspected Players Menu"));
        list.add(Advertisement.d());
        return list;
    }
}
