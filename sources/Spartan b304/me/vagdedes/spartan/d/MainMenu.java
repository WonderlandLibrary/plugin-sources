package me.vagdedes.spartan.d;

import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.features.a.ConfigurationDiagnostics;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import me.vagdedes.spartan.features.syn.LegitimatePlayersFeature;
import me.vagdedes.spartan.features.syn.HackerFinderFeature;
import me.vagdedes.spartan.features.syn.SuspectedPlayersFeature;
import me.vagdedes.spartan.features.syn.DailyProgress;
import me.vagdedes.spartan.j.UltimateStatistics;
import me.vagdedes.spartan.features.b.SearchEngine;
import org.bukkit.Material;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.f.TPS;
import java.util.ArrayList;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanOpenInventory;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.e.ClickableMessage;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashSet;

public class MainMenu
{
    public static final long e = 50L;
    private static final String i;
    private static final boolean m;
    private static final int D = 37;
    private static final HashSet<UUID> n;
    
    public MainMenu() {
        super();
    }
    
    public static void clear() {
        MainMenu.n.clear();
    }
    
    public static void e(final Player player) {
        if (!SynManager.s() && PermissionUtils.a(player, Enums.Permission.manage)) {
            final UUID uniqueId = player.getUniqueId();
            if (!MainMenu.n.contains(uniqueId)) {
                MainMenu.n.add(uniqueId);
                final String d = ErrorUtils.d("You can have a better plugin experience by using the inventory menu. Click this message to open it or execute the command '/spartan menu'.");
                if (d != null) {
                    ClickableMessage.a(player, d, "Spartan Menu", "/spartan menu", true);
                }
            }
        }
    }
    
    public static void a(final SpartanPlayer[] array) {
        if (MainMenu.m) {
            for (final SpartanPlayer spartanPlayer : array) {
                final SpartanOpenInventory a = spartanPlayer.a();
                final String title = a.getTitle();
                if (title != null && title.equals(MainMenu.i)) {
                    b(spartanPlayer, a.getItem(37).getItemMeta().getLore().size() > 2);
                }
            }
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final boolean b) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!PermissionUtils.a(player, Enums.Permission.manage) && !PermissionUtils.a(player, Enums.Permission.info)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long lng = Runtime.getRuntime().freeMemory() / 1024L / 1024L;
        final long lng2 = Runtime.getRuntime().totalMemory() / 1024L / 1024L;
        final long lng3 = Runtime.getRuntime().maxMemory() / 1024L / 1024L;
        final long lng4 = lng3 - (lng2 - lng);
        final String string = VersionUtils.b().toString();
        final SpartanPlayer[] a = NPC.a();
        for (int length = a.length, l = 0; l < length; ++l) {
            i += VL.o(a[l]);
        }
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length2 = values.length, n = 0; n < length2; ++n) {
            if (Config.isEnabled(values[n])) {
                ++j;
            }
            else {
                ++k;
            }
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 54, MainMenu.i);
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        list.add("§7Server Statistics§8:");
        list.add("§7TPS§8: §5" + TPS.get());
        list.add("§7Bypassing§8: §6" + VL.ae(spartanPlayer));
        if (string.length() <= 5) {
            list.add("§7Server Version§8: §3" + string.substring(1).replaceAll("_", "."));
        }
        list.add("§7Available Processors§8: §d" + availableProcessors);
        list.add("§7Given Memory§8: §4" + lng3 + "MB");
        list.add("§7Allocated Memory§8: §c" + lng2 + "MB");
        list.add("§7Free Allocated Memory§8: §e" + lng + "MB");
        list.add("§7Total Free Memory§8: §a" + lng4 + "MB");
        list.add("§7ProtocolLib Packets§8: §1" + Register.arePlibPacketsEnabled());
        InventoryUtils.add(inventory, "§aReload Config", list, new ItemStack(MaterialUtils.a("redstone_comparator")), 11);
        list.clear();
        list.add("");
        if (VerboseNotifications.c(player)) {
            list.add("§7Left click to §cdisable §7verbose.");
        }
        else {
            list.add("§7Left Click to §aenable §7verbose.");
        }
        if (MiningNotifications.c(player)) {
            list.add("§7Right click to §cdisable §7mining notifications.");
        }
        else {
            list.add("§7Right Click to §aenable §7mining notifications.");
        }
        InventoryUtils.add(inventory, "§aNotifications", list, new ItemStack(Register.v1_13 ? Material.FIREWORK_ROCKET : Material.getMaterial("FIREWORK")), 12);
        list.clear();
        list.add("");
        list.add("§7Your Violations§8:§c " + VL.o(spartanPlayer));
        list.add("§7Global Violations§8:§c " + i);
        InventoryUtils.add(inventory, "§aReset Violations", list, new ItemStack(Material.BEDROCK), 13);
        list.clear();
        list.add("");
        list.add("§7Enabled Checks§8:§a " + j);
        list.add("§7Disabled Checks§8:§c " + k);
        InventoryUtils.add(inventory, "§aManage Checks", list, new ItemStack(Material.IRON_SWORD), 14);
        list.clear();
        list.add("");
        list.add("§7Available Configurations§8:§c " + ManageConfiguration.a.length);
        InventoryUtils.add(inventory, "§aConfigurations", list, new ItemStack(Material.PAPER), 15);
        list.clear();
        list.add("");
        list.add("§7Dispatch §c/spartan debug <player> §7for others.");
        InventoryUtils.add(inventory, "§aDebug Yourself", list, new ItemStack(Material.ENCHANTED_BOOK), 21);
        list.clear();
        list.add("");
        list.add("§7Enabled§8:§e " + SynManager.s());
        list.add("");
        list.add("§7Click to learn more information.");
        InventoryUtils.add(inventory, "§6Syn Membership", list, new ItemStack(Material.GOLD_BLOCK), 22);
        list.clear();
        list.add("");
        list.add("§7This feature is a semi-permanent solution to");
        list.add("§7solve unwanted low-violation false positives.");
        list.add("§7It will study your local or database logs, based");
        list.add("§7on your configuration preferences, and will");
        list.add("§7automatically adjust your config.yml configuration");
        list.add("§7for a better and more stable checking performance.");
        list.add("");
        list.add("§cPlease do not use this feature in a server that");
        list.add("§callows any sort of hacking module. It will possibly");
        list.add("§capply false changes to the configuration.");
        InventoryUtils.add(inventory, "§aConfiguration Diagnostics", list, new ItemStack(MaterialUtils.a("redstone_torch")), 23);
        list.clear();
        list.add("");
        list.add("§7Enabled§8:§c " + SearchEngine.d());
        list.add("§7Cache Size§8:§c " + SearchEngine.c() + "KB");
        list.add("§7Cached Rows§8:§c " + SearchEngine.size());
        if (UltimateStatistics.isEnabled()) {
            list.add("§7Ultimate Statistics§8:§c " + UltimateStatistics.l() + " Stats");
        }
        else {
            list.add("");
            list.add("§cUltimate Statistics§8:");
            list.add("§7The plugin's Search-Engine can be improved");
            list.add("§7over time with statistical data from another");
            list.add("§7plugin named 'Ultimate Statistics'. This will");
            list.add("§7help Spartan identify hackers from legitimate");
            list.add("§7players more precisely and safer. Please click");
            list.add("§7this item to receive the plugin's page.");
        }
        InventoryUtils.add(inventory, "§aDaily Progress", DailyProgress.a(player, b), DailyProgress.getItem(), 37);
        InventoryUtils.add(inventory, "§aSuspected Players", SuspectedPlayersFeature.a(player), SuspectedPlayersFeature.getItem(), 38);
        InventoryUtils.add(inventory, "§aSearch Engine", list, SearchEngine.a(), 40);
        InventoryUtils.add(inventory, "§aHacker Finder", HackerFinderFeature.a(player), HackerFinderFeature.getItem(), 42);
        InventoryUtils.add(inventory, "§aLegitimate Players", LegitimatePlayersFeature.a(player), LegitimatePlayersFeature.getItem(), 43);
        player.openInventory(inventory);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s, final ClickType clickType) {
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.equals(MainMenu.i)) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (s2.equalsIgnoreCase("Reload Config")) {
            if (!PermissionUtils.a(player, Enums.Permission.reload)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            Config.a(player, true);
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Notifications")) {
            if (clickType.isLeftClick()) {
                if (!PermissionUtils.a(player, Enums.Permission.verbose)) {
                    player.sendMessage(Language.getMessage("menu_gui_no_access"));
                    player.closeInventory();
                    return true;
                }
                VerboseNotifications.a(player, 0);
                player.closeInventory();
            }
            else if (clickType.isRightClick()) {
                if (!PermissionUtils.a(player, Enums.Permission.mining)) {
                    player.sendMessage(Language.getMessage("menu_gui_no_access"));
                    player.closeInventory();
                    return true;
                }
                MiningNotifications.d(player);
                player.closeInventory();
            }
        }
        else if (s2.equalsIgnoreCase("Reset Violations")) {
            if (!PermissionUtils.a(player, Enums.Permission.manage)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            VL.i(true);
            if (!VerboseNotifications.c(player)) {
                player.sendMessage(Language.getMessage("violations_reset"));
            }
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Manage Checks")) {
            ManageChecks.g(player);
        }
        else if (s2.equalsIgnoreCase("Debug Yourself")) {
            DebugMenu.a(player, player);
        }
        else if (s2.equalsIgnoreCase("Daily Progress")) {
            if (!SynManager.s()) {
                SynMenu.A(spartanPlayer);
            }
            else {
                b(spartanPlayer, true);
            }
        }
        else if (s2.equalsIgnoreCase("Suspected Players")) {
            SuspectedPlayers.A(spartanPlayer);
        }
        else if (s2.equalsIgnoreCase("Hacker Finder")) {
            HackerFinder.A(spartanPlayer);
        }
        else if (s2.equalsIgnoreCase("Legitimate Players")) {
            LegitimatePlayers.A(spartanPlayer);
        }
        else if (s2.equalsIgnoreCase("Syn Membership")) {
            SynMenu.A(spartanPlayer);
        }
        else if (s2.equalsIgnoreCase("Configuration Diagnostics")) {
            if (!PermissionUtils.a(player, Enums.Permission.manage)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            ConfigurationDiagnostics.b((CommandSender)player);
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Search Engine")) {
            if (!UltimateStatistics.isEnabled()) {
                player.sendMessage("§6Ultimate Statistics §7Page§8:§e https://www.spigotmc.org/resources/60868/");
            }
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Configurations")) {
            if (!PermissionUtils.a(player, Enums.Permission.manage)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            ManageConfiguration.A(spartanPlayer);
        }
        return true;
    }
    
    static {
        i = "§0Spartan Menu".substring(Register.v1_13 ? 2 : 0);
        m = (VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.c);
        n = new HashSet<UUID>();
    }
}
