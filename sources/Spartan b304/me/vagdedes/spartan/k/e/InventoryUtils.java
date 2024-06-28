package me.vagdedes.spartan.k.e;

import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.SkullType;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import org.bukkit.inventory.Inventory;

public class InventoryUtils
{
    public InventoryUtils() {
        super();
    }
    
    public static void add(final Inventory inventory, final String displayName, final ArrayList<String> lore, final ItemStack itemStack, final int n) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (lore != null) {
            itemMeta.setLore((List)lore);
        }
        itemStack.setItemMeta(itemMeta);
        if (n != -1) {
            inventory.setItem(n, itemStack);
        }
        else {
            inventory.addItem(new ItemStack[] { itemStack });
        }
    }
    
    public static void a(final Inventory inventory, final String displayName, final List<String> lore, final ItemStack itemStack, final int n) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (lore != null) {
            itemMeta.setLore((List)lore);
        }
        itemStack.setItemMeta(itemMeta);
        if (n != -1) {
            inventory.setItem(n, itemStack);
        }
        else {
            inventory.addItem(new ItemStack[] { itemStack });
        }
    }
    
    public static ItemStack get(final String displayName, final ArrayList<String> lore, final ItemStack itemStack) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        if (lore != null) {
            itemMeta.setLore((List)lore);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack a(final OfflinePlayer offlinePlayer) {
        final ItemStack itemStack = new ItemStack(Register.v1_13 ? Material.PLAYER_HEAD : Material.getMaterial("SKULL_ITEM"), 1, (short)SkullType.PLAYER.ordinal());
        final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
        itemMeta.setOwner(offlinePlayer.getName());
        itemMeta.setDisplayName(offlinePlayer.getName());
        itemStack.setItemMeta((ItemMeta)itemMeta);
        return itemStack;
    }
}
