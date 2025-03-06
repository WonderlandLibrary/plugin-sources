package com.elevatemc.anticheat.base.gui.impl.check.combat;


import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.base.gui.impl.check.combat.impl.*;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class CombatChecksGUI implements IGUI {
    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create("&b&lCombat Checks", 4).setAutomaticPaginationEnabled(false);

        SGButton aimAssistButton = new SGButton(
                new ItemBuilder(Material.FISHING_ROD).
                        name(CC.PRI + "Aim Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(AimAssistGUI.class).openToPlayer(player));

        SGButton killauraButton = new SGButton(
                new ItemBuilder(Material.BLAZE_ROD).
                        name(CC.PRI + "KillAura Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(KillAuraGUI.class).openToPlayer(player));

        SGButton reachButton = new SGButton(
                new ItemBuilder(Material.BOW).
                        name(CC.PRI + "Range Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(RangeGUI.class).openToPlayer(player));

        SGButton velocityButton = new SGButton(
                new ItemBuilder(Material.ANVIL).
                        name(CC.PRI + "Velocity Checks")
                        .build()
        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(VelocityGUI.class).openToPlayer(player));

        SGButton hitSelectButton = new SGButton(
                new ItemBuilder(Material.DIAMOND_SWORD).
                        name(CC.PRI + "Hit Select Checks")
                        .build()

        ).withListener((InventoryClickEvent event) ->
                getGuiService().getByClass(HitSelectGUI.class).openToPlayer(player));

        menu.setButton(11, aimAssistButton);
        menu.setButton(13, killauraButton);
        menu.setButton(15, velocityButton);
        menu.setButton(21, reachButton);
        menu.setButton(23, hitSelectButton);
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