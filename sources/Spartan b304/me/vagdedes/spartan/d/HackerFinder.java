package me.vagdedes.spartan.d;

import java.util.concurrent.ConcurrentHashMap;
import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.h.CheckProtection;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import me.vagdedes.spartan.features.syn.ViolationHistory;
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
import me.vagdedes.spartan.features.syn.HackerFinderFeature;
import java.util.ArrayList;
import java.util.HashMap;

public class HackerFinder
{
    private static final String i = "Hacker Finder ";
    private static final HashMap<Integer, ArrayList<HackerFinderFeature>> q;
    private static final HashMap<SpartanPlayer, Integer> P;
    
    public HackerFinder() {
        super();
    }
    
    public static void run() {
        for (final Map.Entry<SpartanPlayer, Integer> entry : HackerFinder.P.entrySet()) {
            d(entry.getKey(), Integer.valueOf(entry.getValue()));
        }
        HackerFinder.P.clear();
    }
    
    public static void A(final SpartanPlayer spartanPlayer) {
        d(spartanPlayer, 1);
    }
    
    public static void a(final SpartanPlayer key) {
        HackerFinder.P.remove(key);
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
        Bukkit.createInventory((InventoryHolder)player, 54, "Hacker Finder (Page " + i + ")");
        final boolean containsKey = HackerFinder.q.containsKey(Integer.valueOf(i));
        final ArrayList<HackerFinderFeature> list;
        final Iterator<HackerFinderFeature> iterator;
        HackerFinderFeature hackerFinderFeature;
        ArrayList<String> list2;
        final Iterator<Enums.HackType> iterator2;
        Enums.HackType hackType;
        final ViolationHistory violationHistory;
        final Inventory inventory;
        final OfflinePlayer offlinePlayer;
        final ItemStack itemStack;
        final Object o;
        final ArrayList<String> list3;
        final ItemStack itemStack2;
        final Object o2;
        final ArrayList<String> list4;
        final boolean b;
        final Player player2;
        final Runnable runnable = () -> {
            a(i);
            list.iterator();
            while (iterator.hasNext()) {
                hackerFinderFeature = iterator.next();
                hackerFinderFeature.b();
                list2 = new ArrayList<String>(2);
                list2.add("");
                list2.add("§7Possibly used§8:");
                hackerFinderFeature.b().iterator();
                while (iterator2.hasNext()) {
                    hackType = iterator2.next();
                    list2.add("§7-§c " + hackType.toString() + " §8(§c" + violationHistory.getViolations().get(hackType) + " §7violation(s) in §c" + ((ArrayList<String>)violationHistory.getDays().get(hackType)).size() + " §7day(s)§8)");
                }
                hackerFinderFeature.a();
                InventoryUtils.add(inventory, "§c" + offlinePlayer.getName(), list2, InventoryUtils.a(offlinePlayer), -1);
            }
            if (i > 1) {
                new StringBuilder().append("§cPage ").append(i - 1).toString();
                new ItemStack(Register.v1_13 ? Material.GRAY_DYE : Material.getMaterial("INK_SACK"), 1, (short)8);
                InventoryUtils.add(inventory, (String)o, list3, itemStack, 48);
            }
            InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 49);
            if (list.size() == 45) {
                new StringBuilder().append("§aPage ").append(i + 1).toString();
                new ItemStack(Register.v1_13 ? Material.LIME_DYE : Material.getMaterial("INK_SACK"), 1, (short)10);
                InventoryUtils.add(inventory, (String)o2, list4, itemStack2, 50);
            }
            if (!b) {
                HackerFinder.P.put(key, Integer.valueOf(i));
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
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.startsWith("Hacker Finder ")) {
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
        HackerFinder.q.clear();
        HackerFinder.P.clear();
    }
    
    public static ArrayList<HackerFinderFeature> a(final int i) {
        if (HackerFinder.q.containsKey(Integer.valueOf(i))) {
            return HackerFinder.q.get(Integer.valueOf(i));
        }
        CheckProtection.c(10000);
        int n = 0;
        final int initialCapacity = 45;
        final int n2 = (i - 1) * initialCapacity;
        final ArrayList value = new ArrayList<HackerFinderFeature>(initialCapacity);
        for (final OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (++n > n2 * initialCapacity && TimeUtils.a(offlinePlayer.getLastPlayed()) / 1000L / 60L <= 20160L) {
                final HackerFinderFeature a = a(offlinePlayer);
                if (a.b().size() > 0) {
                    value.add(a);
                    if (value.size() == initialCapacity) {
                        break;
                    }
                }
            }
        }
        HackerFinder.q.put(Integer.valueOf(i), (ArrayList<HackerFinderFeature>)value);
        return (ArrayList<HackerFinderFeature>)value;
    }
    
    private static HackerFinderFeature a(final OfflinePlayer offlinePlayer) {
        final ArrayList<Enums.HackType> list = new ArrayList<Enums.HackType>(Enums.HackType.values().length);
        final ViolationHistory violationHistory = SearchEngine.getViolationHistory(offlinePlayer.getName());
        final ConcurrentHashMap<Enums.HackType, Integer> violations = violationHistory.getViolations();
        final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days = violationHistory.getDays();
        for (final Enums.HackType hackType : violations.keySet()) {
            if (days.containsKey(hackType) && Integer.valueOf(violations.get((Object)hackType)) / (double)((ArrayList<String>)days.get(hackType)).size() >= SearchEngine.a(hackType, true) + 1.0) {
                list.add(hackType);
            }
        }
        return new HackerFinderFeature(offlinePlayer, list, violationHistory);
    }
    
    private static /* synthetic */ void a(final int i, final Inventory inventory, final boolean b, final SpartanPlayer key, final Player player) {
        final ArrayList<HackerFinderFeature> a = a(i);
        for (final HackerFinderFeature hackerFinderFeature : a) {
            final ViolationHistory b2 = hackerFinderFeature.b();
            final ArrayList<String> list = new ArrayList<String>(2);
            list.add("");
            list.add("§7Possibly used§8:");
            for (final Enums.HackType hackType : hackerFinderFeature.b()) {
                list.add("§7-§c " + hackType.toString() + " §8(§c" + b2.getViolations().get(hackType) + " §7violation(s) in §c" + ((ArrayList<String>)b2.getDays().get(hackType)).size() + " §7day(s)§8)");
            }
            final OfflinePlayer a2 = hackerFinderFeature.a();
            InventoryUtils.add(inventory, "§c" + a2.getName(), list, InventoryUtils.a(a2), -1);
        }
        if (i > 1) {
            InventoryUtils.add(inventory, "§cPage " + (i - 1), null, new ItemStack(Register.v1_13 ? Material.GRAY_DYE : Material.getMaterial("INK_SACK"), 1, (short)8), 48);
        }
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 49);
        if (a.size() == 45) {
            InventoryUtils.add(inventory, "§aPage " + (i + 1), null, new ItemStack(Register.v1_13 ? Material.LIME_DYE : Material.getMaterial("INK_SACK"), 1, (short)10), 50);
        }
        if (!b) {
            HackerFinder.P.put(key, Integer.valueOf(i));
        }
        else {
            player.openInventory(inventory);
        }
    }
    
    static {
        q = new HashMap<Integer, ArrayList<HackerFinderFeature>>();
        P = new HashMap<SpartanPlayer, Integer>();
    }
}
