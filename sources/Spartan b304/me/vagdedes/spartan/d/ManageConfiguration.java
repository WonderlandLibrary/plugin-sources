package me.vagdedes.spartan.d;

import org.bukkit.inventory.meta.ItemMeta;
import me.vagdedes.spartan.a.b.ChatProtection;
import me.vagdedes.spartan.a.b.SQLConfig;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.k.c.ConfigUtils;
import org.bukkit.event.inventory.ClickType;
import java.util.Iterator;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.List;
import java.io.File;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import org.bukkit.inventory.InventoryHolder;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ManageConfiguration
{
    private static final String i;
    public static final String[] a;
    
    public ManageConfiguration() {
        super();
    }
    
    public static void A(final SpartanPlayer spartanPlayer) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!PermissionUtils.a(player, Enums.Permission.manage)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 27, ManageConfiguration.i);
        final File[] listFiles = Register.plugin.getDataFolder().listFiles();
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 26);
        if (listFiles != null) {
            for (final File file : listFiles) {
                if (file.isFile()) {
                    for (final String anotherString : ManageConfiguration.a) {
                        final String name = file.getName();
                        if (name.equalsIgnoreCase(anotherString)) {
                            InventoryUtils.add(inventory, "§c" + name.replace((CharSequence)".yml", (CharSequence)""), null, new ItemStack(Register.v1_13 ? Material.WRITABLE_BOOK : Material.getMaterial("BOOK_AND_QUILL")), -1);
                        }
                    }
                }
            }
        }
        player.openInventory(inventory);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String str, final int n, final List<String> list) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (!PermissionUtils.a(player, Enums.Permission.manage)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        boolean b = false;
        final String[] a = ManageConfiguration.a;
        for (int length = a.length, i = 0; i < length; ++i) {
            if (a[i].equals(str + ".yml")) {
                b = true;
                break;
            }
        }
        if (!b) {
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 54, ManageConfiguration.i + ": " + str);
        final File file = new File(Register.plugin.getDataFolder() + "/" + str + ".yml");
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        int n2 = 0;
        InventoryUtils.add(inventory, "§4Back", null, new ItemStack(Material.ARROW), 53);
        if (file.exists()) {
            for (final String s : loadConfiguration.getKeys(true)) {
                final Object value = loadConfiguration.get(s);
                final String str2 = (value instanceof Boolean) ? "Logical" : ((value instanceof Double) ? "Decimal" : ((value instanceof Integer) ? "Number" : ((value instanceof String) ? "Text" : "Unknown")));
                if (value != null && !str2.equals("Unknown")) {
                    if (n2 == n) {
                        InventoryUtils.a(inventory, "§c" + s, list, new ItemStack(Material.PAPER), -1);
                    }
                    else {
                        final String string = value.toString();
                        final ArrayList<String> list2 = new ArrayList<String>();
                        list2.add("");
                        list2.add("§7Type§8:§c " + str2);
                        list2.add("§7Value§8:§c " + (string.equals("") ? "(Empty)" : string));
                        list2.add("");
                        if (str2.equals("Logical")) {
                            list2.add("§7Left click to set to §aTrue");
                            list2.add("§7Right click to set to §cFalse");
                        }
                        else if (str2.equals("Decimal")) {
                            list2.add("§7Left click to §aincrease §7by §a0.1");
                            list2.add("§7Right click to §cdecrease §7by §c0.1");
                        }
                        else if (str2.equals("Number")) {
                            list2.add("§7Left click to §aincrease §7by §a1");
                            list2.add("§7Right click to §cdecrease §7by §c1");
                        }
                        else if (str2.equals("Text")) {
                            list2.add("§7No modification available. Please use a file explorer.");
                        }
                        else {
                            list2.add("§7Unknown value type. Please use a file explorer.");
                        }
                        InventoryUtils.add(inventory, "§c" + s, list2, new ItemStack(Material.PAPER), -1);
                    }
                    if (++n2 == 53) {
                        break;
                    }
                    continue;
                }
            }
        }
        player.openInventory(inventory);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s, final ClickType clickType, final int n) {
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.startsWith(ManageConfiguration.i)) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        if (s2.equalsIgnoreCase("Back")) {
            if (s.equals(ManageConfiguration.i)) {
                MainMenu.b(spartanPlayer, false);
            }
            else {
                A(spartanPlayer);
            }
            return true;
        }
        if (s.equals(ManageConfiguration.i)) {
            a(spartanPlayer, s2, -1, null);
        }
        else {
            a(spartanPlayer, itemStack, s, clickType, n);
        }
        return true;
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s, final ClickType clickType, final int n) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final List lore = itemMeta.getLore();
        if (lore != null) {
            final String substring = s.substring(ManageConfiguration.i.length() + 2);
            final File file = new File(Register.plugin.getDataFolder() + "/" + substring + ".yml");
            if (file.exists()) {
                final String substring2 = itemMeta.getDisplayName().substring(2);
                final String substring3 = ((String)lore.get(1)).substring(12);
                final String substring4 = ((String)lore.get(2)).substring(13);
                boolean b = true;
                if (substring3.equals("Logical")) {
                    if (clickType.isLeftClick()) {
                        final Boolean value = Boolean.valueOf(true);
                        ConfigUtils.set(file, substring2, value);
                        lore.set(2, "§7Value§8:§c " + value);
                    }
                    else if (clickType.isRightClick()) {
                        final Boolean value2 = Boolean.valueOf(false);
                        ConfigUtils.set(file, substring2, value2);
                        lore.set(2, "§7Value§8:§c " + value2);
                    }
                    else {
                        b = false;
                    }
                }
                else if (substring3.equals("Decimal")) {
                    final double doubleValue = (double)Double.valueOf(substring4.toString());
                    if (clickType.isLeftClick()) {
                        final double n2 = doubleValue + 0.1;
                        ConfigUtils.set(file, substring2, Double.valueOf(n2));
                        lore.set(2, "§7Value§8:§c " + n2);
                    }
                    else if (clickType.isRightClick()) {
                        final double n3 = doubleValue - 0.1;
                        ConfigUtils.set(file, substring2, Double.valueOf(n3));
                        lore.set(2, "§7Value§8:§c " + n3);
                    }
                    else {
                        b = false;
                    }
                }
                else if (substring3.equals("Number")) {
                    int intValue = (int)Integer.valueOf(substring4.toString());
                    if (clickType.isLeftClick()) {
                        ++intValue;
                        ConfigUtils.set(file, substring2, Integer.valueOf(intValue));
                        lore.set(2, "§7Value§8:§c " + intValue);
                    }
                    else if (clickType.isRightClick()) {
                        --intValue;
                        ConfigUtils.set(file, substring2, Integer.valueOf(intValue));
                        lore.set(2, "§7Value§8:§c " + intValue);
                    }
                    else {
                        b = false;
                    }
                }
                else {
                    b = (substring3.equals("Text") && false);
                }
                if (b) {
                    Config.clear();
                    Settings.clear();
                    Checks.clear();
                    Compatibility.clear();
                    SQLConfig.clear();
                    ChatProtection.clear();
                    a(spartanPlayer, substring, n, lore);
                }
            }
        }
    }
    
    static {
        i = "§0Configurations".substring(Register.v1_13 ? 2 : 0);
        a = new String[] { "config.yml", "settings.yml", "language.yml", "checks.yml", "compatibility.yml", "mysql.yml", "chat_protection.yml" };
    }
}
