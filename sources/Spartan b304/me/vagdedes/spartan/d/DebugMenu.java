package me.vagdedes.spartan.d;

import me.vagdedes.spartan.Register;
import org.bukkit.inventory.Inventory;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.a.MaterialUtils;
import java.util.ArrayList;
import me.vagdedes.spartan.k.e.InventoryUtils;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.features.c.Debug;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;

public class DebugMenu
{
    private static final String i;
    
    public DebugMenu() {
        super();
    }
    
    public static void a(final Player player, final Player player2) {
        final SpartanPlayer a = SpartanBukkit.a(player.getUniqueId());
        if (!PermissionUtils.a(player, Enums.Permission.debug)) {
            player.sendMessage(Language.getMessage("menu_gui_no_access"));
            player.closeInventory();
            return;
        }
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)player, 27, DebugMenu.i + player2.getName());
        final SpartanPlayer a2 = SpartanBukkit.a(player2.getUniqueId());
        final ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        if (Debug.a(a, a2, "Combat")) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        InventoryUtils.add(inventory, "§7Combat", null, itemStack, 10);
        final ItemStack itemStack2 = new ItemStack(MaterialUtils.a("gold_boots"));
        if (Debug.a(a, a2, "Movement")) {
            itemStack2.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        InventoryUtils.add(inventory, "§7Movement", null, itemStack2, 12);
        final ItemStack itemStack3 = new ItemStack(Material.COMPASS);
        if (Debug.a(a, a2, "Misc")) {
            itemStack3.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        InventoryUtils.add(inventory, "§7Misc", null, itemStack3, 14);
        InventoryUtils.add(inventory, "§cDisable", null, new ItemStack(Material.ARROW), 16);
        player.openInventory(inventory);
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final String s) {
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        if (itemStack.getItemMeta() == null || itemStack.getItemMeta().getDisplayName() == null || !s.startsWith(DebugMenu.i)) {
            return false;
        }
        final String displayName = itemStack.getItemMeta().getDisplayName();
        final String s2 = displayName.startsWith("§") ? displayName.substring(2) : displayName;
        final Player player2 = Bukkit.getPlayer(s.substring(DebugMenu.i.length()));
        if (player2 == null || !player2.isOnline()) {
            player.sendMessage(Language.getMessage("player_not_found_message"));
            player.closeInventory();
        }
        else if (s2.equalsIgnoreCase("Disable")) {
            Debug.d(spartanPlayer, SpartanBukkit.a(player2.getUniqueId()));
            player.closeInventory();
        }
        else {
            if (!PermissionUtils.a(player, Enums.Permission.debug)) {
                player.sendMessage(Language.getMessage("menu_gui_no_access"));
                player.closeInventory();
                return true;
            }
            Debug.a(spartanPlayer, SpartanBukkit.a(player2.getUniqueId()), s2);
            player.closeInventory();
        }
        return true;
    }
    
    static {
        i = "§0Debug: ".substring(Register.v1_13 ? 2 : 0);
    }
}
