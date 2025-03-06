package com.elevatemc.anticheat.base.gui.impl;

import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.base.gui.impl.check.CheckGUI;
import com.elevatemc.anticheat.base.gui.impl.misc.HoodSuspectsGUI;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;


public class MainGUI implements IGUI {
    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create(CC.RED + CC.BOLD + "Hood Anticheat", 3).setAutomaticPaginationEnabled(false);

        SGButton suspectsGUI = new SGButton(
                new ItemBuilder(Material.REDSTONE).
                        name("&4&lSuspected Cheaters").
                        enchant(Enchantment.PROTECTION_FALL, 1).flag(ItemFlag.HIDE_ENCHANTS).build())
                .withListener((InventoryClickEvent event) ->
                        getGuiService().getByClass(HoodSuspectsGUI.class).openToPlayer(player)
                );

        SGButton checksGuiButton = new SGButton(
                new ItemBuilder(Material.ENCHANTED_BOOK).
                        name("&e&lAntiCheat Checks").
                        flag(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES)
                        .build())
                .withListener((InventoryClickEvent event) ->
                        getGuiService().getByClass(CheckGUI.class).openToPlayer(player)
                );
        menu.setButton(11, suspectsGUI);
        menu.setButton(15, checksGuiButton);

        for (int i = 0; i < menu.getRowsPerPage() * 9; i++) {
            if (menu.getButton(i) == null) {
                SGButton glassPane = new SGButton(new ItemStack(
                        Material.STAINED_GLASS_PANE,
                        1,
                        (byte) 0
                ));

                menu.setButton(i, glassPane);
            }
        }
        player.openInventory(menu.getInventory());
    }
}