package me.vagdedes.spartan.d;

import me.vagdedes.spartan.k.d.StringUtils;
import java.util.Iterator;
import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.features.syn.MiningHistory;
import me.vagdedes.spartan.features.syn.ViolationHistory;
import org.bukkit.inventory.Inventory;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import me.vagdedes.spartan.features.c.CpsCounter;
import java.util.ArrayList;
import me.vagdedes.spartan.features.b.SearchEngine;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.f.SpartanPlayer;

public class PlayerInfo
{
    private static final String i;
    
    public PlayerInfo() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Player player) {
        final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!PermissionUtils.a(player2, Enums.Permission.info)) {
            player2.sendMessage(Language.getMessage("menu_gui_no_access"));
            player2.closeInventory();
            return;
        }
        final SpartanPlayer spartanPlayer2 = new SpartanPlayer(player);
        int o = VL.o(spartanPlayer2);
        if (o > 64) {
            o = 64;
        }
        else if (o == 0) {
            o = 1;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player2, 54, PlayerInfo.i + spartanPlayer2.getName());
        final String name = spartanPlayer2.getName();
        final ViolationHistory violationHistory = SearchEngine.getViolationHistory(name);
        final MiningHistory a = SearchEngine.a(name);
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            a(player2, inventory, spartanPlayer2, values[i], violationHistory, a);
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("§7CPS§8:§a " + CpsCounter.o(spartanPlayer2));
        list.add("§7Latency§8:§a " + spartanPlayer2.getPing());
        list.add("§7Violations§8:§a " + VL.o(spartanPlayer2));
        InventoryUtils.add(inventory, "§2Information", list, new ItemStack(Material.BOOK, o), 48);
        list.clear();
        InventoryUtils.add(inventory, "§4Close", null, new ItemStack(Material.ARROW), 49);
        InventoryUtils.add(inventory, "§cReset", null, new ItemStack(Material.REDSTONE), 50);
        player2.openInventory(inventory);
    }
    
    private static void a(final Player player, final Inventory inventory, final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final ViolationHistory violationHistory, final MiningHistory miningHistory) {
        final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        int a = VL.a(spartanPlayer, hackType);
        if (a > 64) {
            a = 64;
        }
        ItemStack itemStack = new ItemStack(Material.QUARTZ_BLOCK);
        if (a >= 1) {
            itemStack = new ItemStack(Register.v1_13 ? Material.RED_TERRACOTTA : Material.getMaterial("STAINED_CLAY"), a, (short)14);
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("§7Bypassing§8:§a " + (PerformanceOptimizer.O(spartanPlayer) || VL.e(spartanPlayer, hackType) || VL.g(spartanPlayer, hackType)));
        list.add("§7Violations§8:§a " + VL.a(spartanPlayer, hackType));
        final Iterator<String> iterator = ViolationHistory.getLore(player, player2, hackType, violationHistory).iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        final Iterator<String> iterator2 = MiningHistory.a(player, miningHistory).iterator();
        while (iterator2.hasNext()) {
            list.add(iterator2.next());
        }
        InventoryUtils.add(inventory, "§2" + hackType.toString(), list, itemStack, -1);
        list.clear();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s, final ItemStack[] array) {
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.contains(PlayerInfo.i)) {
            return false;
        }
        int n = 0;
        final Enums.HackType[] values = Enums.HackType.values();
        for (final ItemStack itemStack2 : array) {
            if (itemStack2 != null && itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName() != null) {
                final String displayName = itemStack2.getItemMeta().getDisplayName();
                final String substring = StringUtils.substring(displayName, 2, displayName.length());
                final Enums.HackType[] array2 = values;
                for (int length2 = array2.length, j = 0; j < length2; ++j) {
                    if (array2[j].toString().equals(substring)) {
                        ++n;
                    }
                }
            }
        }
        if (n != values.length) {
            return false;
        }
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final String displayName2 = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName2.startsWith("§") ? displayName2.substring(2) : displayName2;
        if (s2.equalsIgnoreCase("Close")) {
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Reset")) {
            final Player player2 = Bukkit.getPlayer(s.substring(Register.v1_13 ? 6 : 10));
            if (!PermissionUtils.a(player, Enums.Permission.manage)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            if (player2 == null || !player2.isOnline()) {
                player.sendMessage(Language.getMessage("player_not_found_message"));
                player.closeInventory();
                return true;
            }
            player.closeInventory();
            final String replace = Language.getMessage("player_violation_reset_message").replace("{player}", player2.getName());
            VL.e(spartanPlayer, true);
            player.sendMessage(replace);
        }
        return true;
    }
    
    static {
        i = "§0Info:§2 ".substring(Register.v1_13 ? 2 : 0).toString().replaceAll(Register.v1_13 ? "§2" : "", "");
    }
}
