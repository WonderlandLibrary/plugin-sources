package com.elevatemc.anticheat.base.gui.impl.check.movement;

import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.base.gui.impl.check.movement.impl.*;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class MovementChecksGUI implements IGUI {
    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create("&3&lMovement Checks", 3).setAutomaticPaginationEnabled(false);

        SGButton flightButton = new SGButton(
                new ItemBuilder(Material.FEATHER).
                        name(CC.PRI + "Flight Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(FlyGUI.class).openToPlayer(player));

        SGButton groundButton = new SGButton(
                new ItemBuilder(Material.STONE).
                        name(CC.PRI + "Ground Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(GroundGUI.class).openToPlayer(player));

        SGButton motionButton = new SGButton(
                new ItemBuilder(Material.LADDER).
                        name(CC.PRI + "Motion Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(MotionGUI.class).openToPlayer(player));

        SGButton speedButton = new SGButton(
                new ItemBuilder(Material.RABBIT_FOOT).
                        name(CC.PRI + "Speed Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(SpeedGUI.class).openToPlayer(player));

        SGButton disablerButton = new SGButton(
                new ItemBuilder(Material.BARRIER).
                        name(CC.PRI + "Disabler Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(DisablerGUI.class).openToPlayer(player));

        menu.setButton(11, flightButton);
        menu.setButton(13, groundButton);
        menu.setButton(15, motionButton);
        menu.setButton(21, speedButton);
        menu.setButton(23, disablerButton);

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