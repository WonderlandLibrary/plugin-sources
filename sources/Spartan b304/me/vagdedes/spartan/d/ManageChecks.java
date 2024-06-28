package me.vagdedes.spartan.d;

import org.bukkit.event.inventory.ClickType;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.Iterator;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.k.d.StringUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.a.a.Config;
import org.bukkit.inventory.Inventory;
import me.vagdedes.spartan.Register;
import java.util.ArrayList;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;

public class ManageChecks
{
    private static final String i;
    
    public ManageChecks() {
        super();
    }
    
    public static void g(final Player player) {
        if (!PermissionUtils.a(player, Enums.Permission.manage)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 54, "§0Manage Checks");
        final Enums.HackType[] values = Enums.HackType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            a(player, inventory, values[i]);
        }
        InventoryUtils.add(inventory, "§cDisable silent checking for all", null, new ItemStack(Material.GLASS_BOTTLE), 46);
        InventoryUtils.add(inventory, "§cDisable all checks", null, new ItemStack(Register.v1_13 ? Material.RED_TERRACOTTA : Material.getMaterial("STAINED_CLAY"), 1, (short)14), 47);
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 49);
        InventoryUtils.add(inventory, "§aEnable all checks", null, new ItemStack(Register.v1_13 ? Material.LIME_TERRACOTTA : Material.getMaterial("STAINED_CLAY"), 1, (short)5), 51);
        InventoryUtils.add(inventory, "§aEnable silent checking for all", null, new ItemStack(Material.POTION), 52);
        player.openInventory(inventory);
    }
    
    private static void a(final Player player, final Inventory inventory, final Enums.HackType hackType) {
        final boolean silent = Config.isSilent(hackType, player.getWorld().getName());
        final boolean enabled = Config.isEnabled(hackType);
        final String e = null;
        final String e2 = null;
        String e3;
        String e4;
        if (silent) {
            e3 = "§7Silent§8: §a" + silent;
            e4 = "§7Right click to §cdisable §7silent checking.";
        }
        else {
            e3 = "§7Silent§8: §c" + silent;
            e4 = "§7Right click to §aenable §7silent checking.";
        }
        ItemStack itemStack;
        String str;
        String e5;
        String e6;
        if (enabled) {
            itemStack = new ItemStack(Register.v1_13 ? Material.LIME_DYE : Material.getMaterial("INK_SACK"), 1, (short)10);
            str = "§2";
            e5 = "§7Enabled§8: §a" + enabled;
            e6 = "§7Left click to §cdisable §7check.";
        }
        else {
            itemStack = new ItemStack(Register.v1_13 ? Material.GRAY_DYE : Material.getMaterial("INK_SACK"), 1, (short)8);
            str = "§4";
            e5 = "§7Enabled§8: §c" + enabled;
            e6 = "§7Left click to §aenable §7check.";
        }
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        final String[] a = a(hackType);
        for (int length = a.length, i = 0; i < length; ++i) {
            list.add("§d" + a[i]);
        }
        list.add("");
        list.add("§7Bypassing§8: §e" + VL.e(SpartanBukkit.a(player.getUniqueId()), hackType));
        list.add(e5);
        if (Config.a(hackType)) {
            list.add(e3);
        }
        list.add("§7Violation Divisor§8: §4" + Config.d(hackType));
        if (Config.a(hackType)) {
            final int c = Config.c(hackType);
            list.add("§7Cancel After Violation§8: §4" + ((c >= 0) ? (c - 1) : c));
        }
        for (int j = 1; j <= VL.m(); ++j) {
            for (final String s : Config.a(hackType, j)) {
                if (s != null) {
                    final String substring = StringUtils.substring(s, 0, 57);
                    list.add("§7" + j + "§8:§f " + ((s.length() == substring.length()) ? s : (substring + "...")));
                }
            }
        }
        list.add("");
        list.add(e6);
        if (e != null) {
            list.add(e);
        }
        if (e2 != null) {
            list.add(e2);
        }
        if (Config.a(hackType)) {
            list.add(e4);
        }
        final Iterator<String> iterator = DefaultConfiguration.b(hackType).iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        InventoryUtils.add(inventory, str + Config.a(hackType), list, itemStack, -1);
        list.clear();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s, final ClickType clickType) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.equals(ManageChecks.i)) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        if (!PermissionUtils.a(player, Enums.Permission.manage)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return true;
        }
        if (s2.equalsIgnoreCase("Back")) {
            MainMenu.b(spartanPlayer, false);
        }
        else if (s2.equalsIgnoreCase("Disable all checks")) {
            Config.b();
            g(player);
        }
        else if (s2.equalsIgnoreCase("Enable all checks")) {
            Config.a();
            g(player);
        }
        else if (s2.equalsIgnoreCase("Disable silent checking for all")) {
            Config.d();
            g(player);
        }
        else if (s2.equalsIgnoreCase("Enable silent checking for all")) {
            Config.c();
            g(player);
        }
        else if (clickType.isLeftClick()) {
            b(player, s2);
        }
        else if (clickType.isRightClick()) {
            c(player, s2);
        }
        return true;
    }
    
    private static void b(final Player player, final String s) {
        try {
            final Enums.HackType a = Config.a(s);
            if (Config.isEnabled(a)) {
                Config.b(a, true);
            }
            else {
                Config.a(a, true);
            }
        }
        catch (Exception ex) {}
        g(player);
    }
    
    private static void c(final Player player, final String s) {
        try {
            final Enums.HackType a = Config.a(s);
            if (Config.isSilent(a, player.getWorld().getName())) {
                Config.d(a, true);
            }
            else {
                Config.c(a, true);
            }
        }
        catch (Exception ex) {}
        g(player);
    }
    
    private static String[] a(final Enums.HackType hackType) {
        switch (ManageChecks$1.a[hackType.ordinal()]) {
            case 1: {
                return new String[] { "This check will prevent client modules", "that allow a player to have an 'apparent'", "combat advantage against any entity." };
            }
            case 2: {
                return new String[] { "This check will prevent client", "modules that may potentially hurt", "a server's functional performance." };
            }
            case 3: {
                return new String[] { "This check will prevent client modules", "that allow a player to hit entities", "from an abnormally long distance" };
            }
            case 4: {
                return new String[] { "This check will prevent client modules", "that allow a player to receive abnormal", "amounts of knockback, or none at all." };
            }
            case 5: {
                return new String[] { "This check will prevent client modules", "that allow a player to float in the air", "or fall slower than physically expected." };
            }
            case 6: {
                return new String[] { "This check will prevent client modules", "that allow a player to travel faster", "than what is physically allowed." };
            }
            case 7: {
                return new String[] { "This check will prevent client modules", "that manipulate packets and prevent", "interaction animations from being shown." };
            }
            case 8: {
                return new String[] { "This check will prevent client modules", "that allow a player to float, fall slowly,", "or travel abnormally fast, while riding a boat." };
            }
            case 9: {
                return new String[] { "This check will prevent client modules", "that allow a player to move abnormally,", "such as stepping blocks or climbing walls." };
            }
            case 10: {
                return new String[] { "This check will prevent client modules", "that allow a player to move or travel", "through one or multiple blocks." };
            }
            case 11: {
                return new String[] { "This check will prevent client modules", "that allow a player to decrease or", "eliminate falling damage." };
            }
            case 12: {
                return new String[] { "This check will prevent client modules", "that allow a player to sprint irregularly,", "such as when not having enough food level." };
            }
            case 13: {
                return new String[] { "This check will prevent client modules", "that allow a player to interact or break", "blocks through walls of blocks." };
            }
            case 14: {
                return new String[] { "This check will prevent client modules", "that allow a player to build or break", "blocks within an abnormally long distance." };
            }
            case 15: {
                return new String[] { "This check will prevent client modules", "that allow a player to have a 'non apparent'", "combat advantage against any entity, over time." };
            }
            case 16: {
                return new String[] { "This check will prevent client modules", "that allow a player to walk, travel", "abnormally fast, or jump, while in water." };
            }
            case 17: {
                return new String[] { "This check will prevent client modules", "that allow a player to break a collection", "of blocks in a small amount of time." };
            }
            case 18: {
                return new String[] { "This check will prevent client modules", "that allow a player to break a single", "block in a tiny amount of time." };
            }
            case 19: {
                return new String[] { "This check will prevent client modules", "that allow a player to float, fall slowly,", "or travel abnormally fast, while riding an entity." };
            }
            case 20: {
                return new String[] { "This check will prevent client modules", "that allow a player to click abnormally fast", "or have an irregular clicking consistency." };
            }
            case 21: {
                return new String[] { "This check will prevent client modules", "that allow a player to critical damage", "an entity without properly moving." };
            }
            case 22: {
                return new String[] { "This check will prevent client modules", "that allow a player to fly irregularly, or", "travel abnormally fast, while using an elytra." };
            }
            case 23: {
                return new String[] { "This check will prevent client modules", "that allow a player to send abnormally", "high amounts of movement packets." };
            }
            case 24: {
                return new String[] { "This check will prevent client modules", "that allow a player to execute actions", "in abnormal cases, such as when sleeping." };
            }
            case 25: {
                return new String[] { "This check will prevent client modules", "that allow a player to place or break", "blocks against a liquid block." };
            }
            case 26: {
                return new String[] { "This check will prevent client modules", "that allow a player to place blocks", "in abnormally fast rates." };
            }
            case 27: {
                return new String[] { "This check will prevent client modules", "that allow a player to travel normally", "while executing eating animations, or", "passing through cobweb blocks." };
            }
            case 28: {
                return new String[] { "This check will prevent client modules", "that allow a player to respawn faster", "than what is physically expected." };
            }
            case 29: {
                return new String[] { "This check will prevent client modules", "that allow a player to achieve", "abnormal head/eye directions." };
            }
            case 30: {
                return new String[] { "This check will prevent client modules", "that allow a player to shoot arrows", "in abnormally fast rates." };
            }
            case 31: {
                return new String[] { "This check will prevent client modules", "that allow a player to consume an amount", "of food in an abnormal amount of time." };
            }
            case 32: {
                return new String[] { "This check will prevent client modules", "that allow a player to heal faster", "than what is physically allowed." };
            }
            case 33: {
                return new String[] { "This check will prevent client modules", "that allow a player to drop an amount", "of items in abnormally fast rates." };
            }
            case 34: {
                return new String[] { "This check will prevent client modules", "that allow a player to interact with an", "amount of items, in abnormally fast rates." };
            }
            case 35: {
                return new String[] { "This check will prevent client modules", "that allow a player to interact with", "an inventory in abnormal cases, such", "as when sprinting or walking." };
            }
            case 36: {
                return new String[] { "This check will prevent client modules", "that allow a player to see through blocks", "in order to find rare ores, such as diamonds,", "gold, and even emerald. (Logs must be enabled)" };
            }
            default: {
                return new String[] { "Unknown Check Description" };
            }
        }
    }
    
    static {
        i = "§0Manage Checks".substring(Register.v1_13 ? 2 : 0);
    }
}
