package com.elevatemc.anticheat.base.gui.impl.check.misc;

import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.base.gui.impl.check.misc.impl.*;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class MiscChecksGUI implements IGUI {
    @Override
    public void openToPlayer(Player player) {
        final SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create("&d&lMisc Checks", 4).setAutomaticPaginationEnabled(false);

        final SGButton badpacketsButton = new SGButton(
                new ItemBuilder(Material.REDSTONE).
                        name(CC.PRI + "Bad Packets Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(BadPacketsGUI.class).openToPlayer(player));

        final SGButton pingSpoofButton = new SGButton(
                new ItemBuilder(Material.PAINTING).
                        name(CC.PRI + "Ping Spoof Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(PingSpoofGUI.class).openToPlayer(player));


        final SGButton timerButton = new SGButton(
                new ItemBuilder(Material.CARROT_STICK).
                        name(CC.PRI + "Timer Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(TimerGUI.class).openToPlayer(player));

        final SGButton backTrack = new SGButton(
                new ItemBuilder(Material.MINECART).
                        name(CC.PRI + "Back Track Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(BackTrackGUI.class).openToPlayer(player));

        final SGButton refill = new SGButton(
                new ItemBuilder(Material.POTION).
                        name(CC.PRI + "Refill Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(RefillGUI.class).openToPlayer(player));

        final SGButton simulation = new SGButton(
                new ItemBuilder(Material.GOLD_BOOTS).
                        name(CC.PRI + "Prediction Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(Prediction.class).openToPlayer(player));

        menu.setButton(11, badpacketsButton);
        menu.setButton(13, pingSpoofButton);
        menu.setButton(15, timerButton);
        menu.setButton(21, backTrack);
        menu.setButton(23, refill);
        menu.setButton(31, simulation);
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
