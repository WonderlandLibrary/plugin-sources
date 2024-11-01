package com.elevatemc.anticheat.base.gui.impl.check.combat.impl;

import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.util.chat.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RangeGUI implements IGUI {
    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create(CC.PRI + "Range Checks", 5);
        
        for (String check : HoodPlugin.INSTANCE.getCheckManager().getAlphabeticallySortedChecks()) {
            if (!check.contains("Range")) {
                continue;
            }

            boolean enabled = HoodPlugin.INSTANCE.getConfigurationService().isEnabled(check);
            
            SGButton button = new SGButton(new ItemBuilder(enabled ? Material.MAP : Material.EMPTY_MAP).
                    name(CC.PRI + check + "&7: " + (enabled ? "&aEnabled" : "&cDisabled")).build())
                    .withListener((InventoryClickEvent event) -> {
                        HoodPlugin.INSTANCE.getConfigurationService().toggleCheckActivation(check);
                        openToPlayer(player);
                    });
            
            menu.addButton(button);
        }
        
        player.openInventory(menu.getInventory());
    }
}