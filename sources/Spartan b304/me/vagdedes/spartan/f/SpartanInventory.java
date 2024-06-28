package me.vagdedes.spartan.f;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpartanInventory
{
    private ItemStack[] a;
    private ItemStack[] b;
    private ItemStack itemInHand;
    private ItemStack a;
    
    public SpartanInventory(final ItemStack[] a, final ItemStack[] b, final ItemStack itemInHand, final ItemStack a2) {
        super();
        this.a = a;
        this.b = b;
        if (itemInHand == null) {
            this.itemInHand = new ItemStack(Material.AIR);
        }
        else {
            this.itemInHand = itemInHand;
        }
        if (a2 == null) {
            this.a = new ItemStack(Material.AIR);
        }
        else {
            this.a = a2;
        }
    }
    
    public boolean contains(final Material material) {
        for (final ItemStack itemStack : this.a) {
            if (itemStack != null && itemStack.getType() == material) {
                return true;
            }
        }
        return false;
    }
    
    public ItemStack getItem(final int n) {
        return this.a[n];
    }
    
    public ItemStack[] getContents() {
        return this.a;
    }
    
    public ItemStack[] getArmorContents() {
        return this.b;
    }
    
    public int getSize() {
        return this.a.length;
    }
    
    public ItemStack getHelmet() {
        return this.b[0];
    }
    
    public ItemStack getChestplate() {
        return this.b[1];
    }
    
    public ItemStack getLeggings() {
        return this.b[2];
    }
    
    public ItemStack getBoots() {
        return this.b[3];
    }
    
    public ItemStack getItemInHand() {
        return this.itemInHand;
    }
    
    public ItemStack getItemInOffHand() {
        return this.a;
    }
}
