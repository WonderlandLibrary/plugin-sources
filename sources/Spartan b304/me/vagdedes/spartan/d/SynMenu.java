package me.vagdedes.spartan.d;

import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.features.syn.AutomatedConfigurationDiagnostics;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.features.syn.LegitimatePlayersFeature;
import me.vagdedes.spartan.features.syn.HackerFinderFeature;
import me.vagdedes.spartan.features.syn.SuspectedPlayersFeature;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.features.syn.DailyProgress;
import java.util.ArrayList;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.k.c.SynManager;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashSet;

public class SynMenu
{
    private static final String i;
    private static final HashSet<UUID> o;
    
    public SynMenu() {
        super();
    }
    
    public static void A(final SpartanPlayer spartanPlayer) {
        if (!SynManager.s()) {
            final UUID uniqueId = spartanPlayer.getUniqueId();
            final Player player = Bukkit.getPlayer(uniqueId);
            if (!SynMenu.o.contains(uniqueId) && PermissionUtils.a(player, Enums.Permission.manage)) {
                SynMenu.o.add(uniqueId);
                if (ErrorUtils.d("The Syn membership's features can be recreated by using the plugin's Developer API. If you are not an experienced developer and wish to become one, it is recommended you try MineAcademy's course. Click this message to learn more.") != null) {}
            }
        }
        final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        final Enums.Permission[] values = Enums.Permission.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (PermissionUtils.a(player2, values[i])) {
                final Inventory inventory = Bukkit.createInventory((InventoryHolder)player2, 18, SynMenu.i);
                final ArrayList<String> list = new ArrayList<String>();
                list.clear();
                list.add("");
                list.add("§7Study the daily and yesterday's progress of the Spartan");
                list.add("§7anti-cheat gathered from saved logs when opening the Main menu.");
                add(inventory, "§3Daily Progress Statistics", list, DailyProgress.getItem(), -1);
                list.clear();
                list.add("");
                list.add("§7Knowing a player's live violations is very useful, but");
                list.add("§7knowing their violation history is astronomically more");
                list.add("§7useful. Read a player's violation history upon opening");
                list.add("§7the Player-Info menu.");
                add(inventory, "§3Violation History Information", list, new ItemStack(Register.v1_13 ? Material.MUSIC_DISC_11 : Material.getMaterial("RECORD_11")), -1);
                list.clear();
                list.add("");
                list.add("§7Knowing a player's mining history will help you know");
                list.add("§7if they were using any illegal modules such as x-ray");
                list.add("§7or texture-less resource packs.");
                add(inventory, "§3Mining History Information", list, new ItemStack(Material.IRON_PICKAXE), -1);
                list.clear();
                list.add("");
                list.add("§7Open a menu that can list up to 54 suspected players");
                list.add("§7that are possibly using illegal modules based on their");
                list.add("§7live violations and limits set by the official developer.");
                add(inventory, "§3Suspected Players Menu", list, SuspectedPlayersFeature.getItem(), -1);
                list.clear();
                list.add("");
                list.add("§7Find and punish players that used illegal");
                list.add("§7modules and were lucky to get away from the");
                list.add("§7anti-cheat's violation important. Since logs");
                list.add("§7are always stored, they won't get away so easily.");
                add(inventory, "§3Hacker Finder Menu", list, HackerFinderFeature.getItem(), -1);
                list.clear();
                list.add("");
                list.add("§7Spartan comes with a feature named Performance-Optimizer");
                list.add("§7which analyzes player activity and after a long process");
                list.add("§7deems them as legitimate and exempts them from all checks.");
                list.add("§7With the Syn membership, you have access to a menu with a");
                list.add("§7progressive list of legitimate players.");
                add(inventory, "§3Legitimate Players Menu", list, LegitimatePlayersFeature.getItem(), -1);
                list.clear();
                list.add("");
                list.add("§7Spartan comes with a Search Engine that uses");
                list.add("§7statistical analysis to understand which players");
                list.add("§7are legitimate and which are hackers. The engine");
                list.add("§7will be refreshed upon reloading the plugin, and");
                list.add("§7it can take up several seconds. Syn members get");
                list.add("§7to enjoy this feature happen automatically, with");
                list.add("§7no apparent performance throwbacks.");
                add(inventory, "§3Live Search Engine", list, SearchEngine.a(), -1);
                list.clear();
                list.add("");
                list.add("§7The Search-Engine is the long-term memory provider");
                list.add("§7of the plugin. It is responsible for finding false");
                list.add("§7positives, legitimate players, statistics & many more.");
                list.add("§7By default, it can cache up to 5MB of information, but");
                list.add("§7with the Syn membership you get to bypass this limitation");
                list.add("§7for a total of 50MB.");
                add(inventory, "§3Upgraded Search Engine", list, new ItemStack(Material.ARROW), -1);
                list.clear();
                list.add("");
                list.add("§7What's better than Configuration Diagnostics?");
                list.add("§7Exactly, having them be automated. No longer");
                list.add("§7worry about low-violation false positives.");
                list.add("§7Spartan will pick the perfect timing and");
                list.add("§7configure any crucial variables for you.");
                add(inventory, "§3Automated Configuration Diagnostics", list, AutomatedConfigurationDiagnostics.getItem(), -1);
                list.clear();
                list.add("");
                list.add("§7With the Syn membership you are granted permission");
                list.add("§7to use all the locked inventory-menu items that");
                list.add("§7redirect you to an advertisement menu.");
                add(inventory, "§3Item Unlock", list, new ItemStack(Material.PAPER), -1);
                list.clear();
                list.add("");
                list.add("§7Join the official Discord server via the invite");
                list.add("§7https://vagdedes.com/discord and receive a unique");
                list.add("§7role that offers several cool features, such as");
                list.add("§7dedicated support, extra permissions, early product");
                list.add("§7trials, and many more!");
                add(inventory, "§3Discord Role", list, new ItemStack(Material.OBSIDIAN), -1);
                list.clear();
                list.add("");
                list.add("§7Syn members have the ability to completely hide the Spartan");
                list.add("§7command and replace it with a help message found in the");
                list.add("§7plugin's configuration.");
                add(inventory, "§3Hidden Command", list, new ItemStack(Material.FEATHER), -1);
                list.clear();
                list.add("");
                list.add("§7Syn members have the ability to receive notifications about");
                list.add("§7potential hackers. Notifications are sent automatically when");
                list.add("§7an advanced-permission member joins the server.");
                add(inventory, "§3Automated Notifications", list, new ItemStack(MaterialUtils.a("REDSTONE_TORCH")), -1);
                player2.openInventory(inventory);
                break;
            }
        }
    }
    
    public static void add(final Inventory inventory, final String s, final ArrayList<String> list, final ItemStack itemStack, final int n) {
        list.add("");
        list.add("§3Pricing§8: §b(All features are included in the purchase)");
        list.add("§74 Months§8: §e10 EUR");
        list.add("§71 Year§8: §e20 EUR §c(4 MONTHS FREE) §6[RECOMMENDED]");
        list.add("§7Lifetime§8: §e50 EUR §6[BEST DEAL]");
        list.add("");
        list.add("§7To become a §6Syn Member §7please send the money");
        list.add("§7via §2PayPal §7to §abilling@vagdedes.com §7with your");
        list.add("§8SpigotMC account name§7. A conversation will be created");
        list.add("§7with the simple steps to activate your membership.");
        list.add("");
        list.add("§cThe membership is a monthly subscription,");
        list.add("§cfollows the resource's terms, requires a");
        list.add("§ca server with internet connection, and its");
        list.add("§ccontents may be updated at any time.");
        list.add("§4Chargebacking without a proper approval");
        list.add("§4will disable your membership regardless of the");
        list.add("§4PayPal case's final resolution, unless closed.");
        InventoryUtils.add(inventory, s, list, itemStack, n);
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final String s) {
        if (s.equalsIgnoreCase(SynMenu.i)) {
            Bukkit.getPlayer(spartanPlayer.getUniqueId()).closeInventory();
            return true;
        }
        return false;
    }
    
    static {
        i = "§0Syn Membership".substring(Register.v1_13 ? 2 : 0);
        o = new HashSet<UUID>();
    }
}
