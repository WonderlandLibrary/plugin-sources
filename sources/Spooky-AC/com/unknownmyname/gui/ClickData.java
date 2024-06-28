/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 */
package com.unknownmyname.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickData {
    private final /* synthetic */ Integer slot;
    private final /* synthetic */ ItemStack itemStack;
    private final /* synthetic */ Inventory inventory;
    private final /* synthetic */ ClickType clickType;
    private final /* synthetic */ Player player;

    public ClickData(Player player, Inventory inventory, ItemStack itemStack, ClickType clickType, Integer slot) {
        this.player = player;
        this.inventory = inventory;
        this.itemStack = itemStack;
        this.clickType = clickType;
        this.slot = slot;
    }

    public Integer getSlot() {
        return this.slot;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public ClickType getClickType() {
        return this.clickType;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public Player getPlayer() {
        return this.player;
    }
}

