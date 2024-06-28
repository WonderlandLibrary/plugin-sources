/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 */
package com.unknownmyname.gui;

import com.unknownmyname.gui.ClickData;
import com.unknownmyname.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Gui
implements Listener,
InventoryHolder {
    private final /* synthetic */ Integer size;
    private final /* synthetic */ String header;
    public /* synthetic */ Inventory inventory;

    public String getHeader() {
        return this.header;
    }

    public void openGui(Player player) {
        if (this.inventory != null) {
            player.openInventory(this.inventory);
        }
    }

    public Gui(String header, Integer size) {
        this.inventory = Bukkit.createInventory((InventoryHolder)this, (int)size, (String)header);
        this.header = header;
        this.size = size;
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)Main.getPlugin());
    }

    public void onClose() {
    }

    @EventHandler
    public void onGuiClose(InventoryCloseEvent event) {
        this.onClose();
    }

    public Integer getSize() {
        return this.size;
    }

    @EventHandler
    public void onGuiClick(InventoryClickEvent event) {
        if (event.getInventory() != null && event.getInventory().getHolder() == this) {
            this.onClick(new ClickData((Player)event.getWhoClicked(), event.getClickedInventory(), event.getCurrentItem(), event.getClick(), event.getSlot()));
            event.setCancelled(" ".length() != 0);
            "".length();
            if (-1 >= 0) {
                throw null;
            }
        } else if (event.getView() != null && event.getView().getTopInventory() != null && event.getView().getTopInventory().getHolder() == this) {
            event.setCancelled(" ".length() != 0);
        }
    }

    public void onClick(ClickData clickData) {
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}

