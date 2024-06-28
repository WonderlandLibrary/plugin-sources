package me.vagdedes.spartan.f;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SpartanOpenInventory
{
    private ItemStack b;
    private int slots;
    private String title;
    private ItemStack[] a;
    
    public SpartanOpenInventory(final ItemStack b, final int slots, final String title, final ItemStack[] a) {
        super();
        this.b = b;
        this.slots = slots;
        this.title = title;
        this.a = a;
    }
    
    public ItemStack getCursor() {
        return this.b;
    }
    
    public int countSlots() {
        return this.slots;
    }
    
    public String getTitle() {
        return this.title;
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
}
