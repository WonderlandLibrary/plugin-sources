package me.vagdedes.spartan.d;

import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.h.CheckProtection;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.f.ErrorUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.Bukkit;
import java.util.Iterator;
import java.util.Map;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.OfflinePlayer;
import java.util.ArrayList;
import java.util.HashMap;

public class LegitimatePlayers
{
    private static final String i = "Legitimate Players ";
    private static final HashMap<Integer, ArrayList<OfflinePlayer>> q;
    private static final HashMap<SpartanPlayer, Integer> P;
    
    public LegitimatePlayers() {
        super();
    }
    
    public static void run() {
        for (final Map.Entry<SpartanPlayer, Integer> entry : LegitimatePlayers.P.entrySet()) {
            d(entry.getKey(), Integer.valueOf(entry.getValue()));
        }
        LegitimatePlayers.P.clear();
    }
    
    public static void A(final SpartanPlayer spartanPlayer) {
        d(spartanPlayer, 1);
    }
    
    public static void a(final SpartanPlayer key) {
        LegitimatePlayers.P.remove(key);
    }
    
    private static void d(final SpartanPlayer key, final int i) {
        final Player player = Bukkit.getPlayer(key.getUniqueId());
        if (!SynManager.s()) {
            SynMenu.A(key);
            return;
        }
        if (!PermissionUtils.a(player, Enums.Permission.info)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        if (!SearchEngine.d()) {
            player.sendMessage(Language.getMessage("disabled_log_saving"));
            player.closeInventory();
            return;
        }
        if (SearchEngine.e()) {
            player.sendMessage(Language.getMessage("not_enough_saved_logs").replace((CharSequence)"{amount}", (CharSequence)String.valueOf(100)));
            player.closeInventory();
            return;
        }
        Bukkit.createInventory((InventoryHolder)player, 54, "Legitimate Players (Page " + i + ")");
        final boolean containsKey = LegitimatePlayers.q.containsKey(Integer.valueOf(i));
        final ArrayList<OfflinePlayer> list;
        final Iterator<OfflinePlayer> iterator;
        OfflinePlayer offlinePlayer;
        final Inventory inventory;
        final ItemStack itemStack;
        final String s;
        final ArrayList<String> list2;
        final ItemStack itemStack2;
        final String s2;
        final ArrayList<String> list3;
        final boolean b;
        final Player player2;
        final Runnable runnable = () -> {
            a(i);
            list.iterator();
            while (iterator.hasNext()) {
                offlinePlayer = iterator.next();
                InventoryUtils.add(inventory, "§c" + offlinePlayer.getName(), null, InventoryUtils.a(offlinePlayer), -1);
            }
            if (i > 1) {
                new StringBuilder().append("§cPage ").append(i - 1).toString();
                new ItemStack(Register.v1_13 ? Material.GRAY_DYE : Material.getMaterial("INK_SACK"), 1, (short)8);
                InventoryUtils.add(inventory, s, list2, itemStack, 48);
            }
            InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 49);
            if (list.size() == 45) {
                new StringBuilder().append("§aPage ").append(i + 1).toString();
                new ItemStack(Register.v1_13 ? Material.LIME_DYE : Material.getMaterial("INK_SACK"), 1, (short)10);
                InventoryUtils.add(inventory, s2, list3, itemStack2, 50);
            }
            if (!b) {
                LegitimatePlayers.P.put(key, Integer.valueOf(i));
            }
            else {
                player2.openInventory(inventory);
            }
            return;
        };
        if (containsKey) {
            runnable.run();
        }
        else {
            player.closeInventory();
            final String d = ErrorUtils.d("The inventory data is being loaded. Please wait.");
            if (d != null) {
                player.sendMessage(d);
            }
            Bukkit.getScheduler().runTaskAsynchronously(Register.plugin, runnable);
        }
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s) {
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.startsWith("Legitimate Players ")) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!PermissionUtils.a(player, Enums.Permission.info)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
        }
        else if (s2.equals("Back")) {
            MainMenu.b(spartanPlayer, false);
        }
        else if (s2.startsWith("Page")) {
            d(spartanPlayer, Integer.valueOf(s2.substring(5)));
        }
        return true;
    }
    
    public static void clear() {
        LegitimatePlayers.q.clear();
        LegitimatePlayers.P.clear();
    }
    
    public static ArrayList<OfflinePlayer> a(final int i) {
        if (LegitimatePlayers.q.containsKey(Integer.valueOf(i))) {
            return LegitimatePlayers.q.get(Integer.valueOf(i));
        }
        CheckProtection.c(10000);
        int n = 0;
        final int initialCapacity = 45;
        final int n2 = (i - 1) * initialCapacity;
        final ArrayList value = new ArrayList<OfflinePlayer>(initialCapacity);
        for (final OfflinePlayer e : Bukkit.getOfflinePlayers()) {
            if (++n > n2 * initialCapacity && TimeUtils.a(e.getLastPlayed()) / 1000L / 60L <= 20160L && SearchEngine.c(e.getUniqueId())) {
                value.add(e);
                if (value.size() == initialCapacity) {
                    break;
                }
            }
        }
        LegitimatePlayers.q.put(Integer.valueOf(i), (ArrayList<OfflinePlayer>)value);
        return (ArrayList<OfflinePlayer>)value;
    }
    
    private static /* synthetic */ void a(final int i, final Inventory inventory, final boolean b, final SpartanPlayer key, final Player player) {
        final ArrayList<OfflinePlayer> a = a(i);
        for (final OfflinePlayer offlinePlayer : a) {
            InventoryUtils.add(inventory, "§c" + offlinePlayer.getName(), null, InventoryUtils.a(offlinePlayer), -1);
        }
        if (i > 1) {
            InventoryUtils.add(inventory, "§cPage " + (i - 1), null, new ItemStack(Register.v1_13 ? Material.GRAY_DYE : Material.getMaterial("INK_SACK"), 1, (short)8), 48);
        }
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 49);
        if (a.size() == 45) {
            InventoryUtils.add(inventory, "§aPage " + (i + 1), null, new ItemStack(Register.v1_13 ? Material.LIME_DYE : Material.getMaterial("INK_SACK"), 1, (short)10), 50);
        }
        if (!b) {
            LegitimatePlayers.P.put(key, Integer.valueOf(i));
        }
        else {
            player.openInventory(inventory);
        }
    }
    
    static {
        q = new HashMap<Integer, ArrayList<OfflinePlayer>>();
        P = new HashMap<SpartanPlayer, Integer>();
    }
}
