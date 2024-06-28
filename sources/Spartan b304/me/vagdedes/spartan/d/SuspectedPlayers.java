package me.vagdedes.spartan.d;

import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.f.ErrorUtils;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.j.NPC;
import java.util.Iterator;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.k.e.InventoryUtils;
import java.util.ArrayList;
import me.vagdedes.spartan.features.syn.SuspectedPlayersFeature;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;

public class SuspectedPlayers
{
    private static final String i;
    
    public SuspectedPlayers() {
        super();
    }
    
    public static void A(final SpartanPlayer spartanPlayer) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!SynManager.s()) {
            SynMenu.A(spartanPlayer);
            return;
        }
        if (!PermissionUtils.a(player, Enums.Permission.info)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 54, SuspectedPlayers.i);
        for (final SuspectedPlayersFeature suspectedPlayersFeature : d()) {
            final ArrayList<String> list = new ArrayList<String>(2);
            list.add("");
            list.add("§7Suspected for§8:");
            final Iterator<Enums.HackType> iterator2 = suspectedPlayersFeature.c().iterator();
            while (iterator2.hasNext()) {
                list.add("§7-§c " + ((Enums.HackType)iterator2.next()).toString());
            }
            list.add("");
            list.add("§7Click to learn more info.");
            final Player player2 = suspectedPlayersFeature.getPlayer();
            InventoryUtils.add(inventory, "§c" + player2.getName(), list, InventoryUtils.a((OfflinePlayer)player2), -1);
        }
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 53);
        player.openInventory(inventory);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s) {
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.contains(SuspectedPlayers.i)) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        if (s2.equals("Back")) {
            MainMenu.b(spartanPlayer, false);
            return true;
        }
        final Player player = Bukkit.getPlayer(s2);
        if (player == null || !player.isOnline()) {
            final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            player2.sendMessage(Language.getMessage("player_not_found_message"));
            player2.closeInventory();
        }
        else {
            PlayerInfo.a(spartanPlayer, player);
        }
        return true;
    }
    
    public static ArrayList<SuspectedPlayersFeature> d() {
        final SpartanPlayer[] a = NPC.a();
        final ArrayList list = new ArrayList<SuspectedPlayersFeature>(a.length);
        final SpartanPlayer[] array = a;
        for (int length = array.length, i = 0; i < length; ++i) {
            final SuspectedPlayersFeature a2 = a(array[i]);
            if (a2.c().size() > 0) {
                list.add(a2);
            }
            if (list.size() == 53) {
                break;
            }
        }
        return (ArrayList<SuspectedPlayersFeature>)list;
    }
    
    private static SuspectedPlayersFeature a(final SpartanPlayer spartanPlayer) {
        final ArrayList<Enums.HackType> list = new ArrayList<Enums.HackType>(Enums.HackType.values().length);
        for (final Enums.HackType e : Enums.HackType.values()) {
            if (VL.a(spartanPlayer, e) >= (int)Math.round(DefaultConfiguration.e(e) / 3.0 * 2.0)) {
                list.add(e);
            }
        }
        return new SuspectedPlayersFeature(Bukkit.getPlayer(spartanPlayer.getUniqueId()), list);
    }
    
    public static void e(final Player player) {
        if (SynManager.s() && PermissionUtils.a(player, Enums.Permission.manage)) {
            final ArrayList<SuspectedPlayersFeature> d = d();
            final int size = d.size();
            if (size > 0) {
                String string = "";
                final Iterator<SuspectedPlayersFeature> iterator = d.iterator();
                while (iterator.hasNext()) {
                    string = string + ChatColor.GRAY + ((SuspectedPlayersFeature)iterator.next()).getPlayer().getName() + ChatColor.DARK_GRAY + ", ";
                }
                final String substring = string.substring(0, string.length() - 4);
                if (size == 1) {
                    final String d2 = ErrorUtils.d("The following player is suspected for using hack modules: " + substring);
                    if (d2 != null) {
                        player.sendMessage(d2);
                    }
                }
                else {
                    final String d3 = ErrorUtils.d("The following " + size + " players are suspected for using hack modules: " + substring);
                    if (d3 != null) {
                        player.sendMessage(d3);
                    }
                }
            }
        }
    }
    
    static {
        i = "§0Suspected Players".substring(Register.v1_13 ? 2 : 0);
    }
}
