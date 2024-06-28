package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.k.c.SynManager;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class LegitimatePlayersFeature
{
    public LegitimatePlayersFeature() {
        super();
    }
    
    public static ItemStack getItem() {
        return new ItemStack(Material.LEATHER_CHESTPLATE);
    }
    
    public static ArrayList<String> a(final Player player) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        if (SynManager.s()) {
            list.add("§7Click to view possibly legitimate players.");
            list.add("");
            list.add("§cWARNING! Upon loading inventory data, the server");
            list.add("§cmay freeze, and TPS may temporarily drop.");
            return list;
        }
        list.add(Advertisement.a(player, "Legitimate Players Menu"));
        list.add(Advertisement.d());
        return list;
    }
}
