package me.frep.vulcan.spigot.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import me.frep.vulcan.spigot.util.material.XMaterial;
import org.bukkit.inventory.meta.ItemMeta;
import me.frep.vulcan.spigot.util.ColorUtil;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class VulcanGUI
{
    public static VulcanGUI instance;
    public final List<String> noLore;
    
    public VulcanGUI() {
        this.noLore = new ArrayList<String>();
    }
    
    public ItemStack itemStack(final ItemStack itemStack, final String displayName, final List<String> lore) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ColorUtil.translate(displayName));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public ItemStack glassPane() {
        return this.itemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseItem(), "&r", this.noLore);
    }
    
    public Inventory createInventory(final int size, final String title) {
        return Bukkit.createInventory(null, size, ColorUtil.translate(title));
    }
    
    public ItemStack arrow(final boolean next) {
        final ItemStack i = new ItemStack(Material.ARROW);
        final ItemMeta im = i.getItemMeta();
        if (next) {
            im.setDisplayName(ChatColor.GREEN + "Next Page");
        }
        else {
            im.setDisplayName(ChatColor.RED + "Previous Page");
        }
        i.setItemMeta(im);
        return i;
    }
    
    public static VulcanGUI getInstance() {
        return VulcanGUI.instance;
    }
    
    static {
        VulcanGUI.instance = new VulcanGUI();
    }
}
