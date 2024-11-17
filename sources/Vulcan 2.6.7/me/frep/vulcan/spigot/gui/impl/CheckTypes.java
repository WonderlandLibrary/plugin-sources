package me.frep.vulcan.spigot.gui.impl;

import java.util.Objects;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.util.material.XMaterial;
import org.bukkit.inventory.Inventory;
import me.frep.vulcan.spigot.gui.VulcanGUI;

public class CheckTypes extends VulcanGUI
{
    public static final CheckTypes instance;
    private final String inventoryName = "Check Types";
    private Inventory inventory;
    
    private void initializeInventory() {
        this.inventory = this.createInventory(36, "Check Types");
        for (int i = 0; i < 36; ++i) {
            this.inventory.setItem(i, this.glassPane());
        }
        this.inventory.setItem(10, this.itemStack(XMaterial.DIAMOND_SWORD.parseItem(), "&cCombat Checks", this.noLore));
        this.inventory.setItem(13, this.itemStack(XMaterial.FEATHER.parseItem(), "&cMovement Checks", this.noLore));
        this.inventory.setItem(16, this.itemStack(XMaterial.APPLE.parseItem(), "&cPlayer Checks", this.noLore));
        this.inventory.setItem(31, this.itemStack(XMaterial.REDSTONE_BLOCK.parseItem(), "&cGo Back", this.noLore));
    }
    
    public void open(final Player player) {
        if (this.inventory == null) {
            this.initializeInventory();
        }
        player.openInventory(this.inventory);
    }
    
    public String getInventoryName() {
        Objects.requireNonNull(this);
        return "Check Types";
    }
    
    public Inventory getInventory() {
        return this.inventory;
    }
    
    public static CheckTypes getInstance() {
        return CheckTypes.instance;
    }
    
    static {
        instance = new CheckTypes();
    }
}
