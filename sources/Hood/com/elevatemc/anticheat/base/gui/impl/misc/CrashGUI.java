package com.elevatemc.anticheat.base.gui.impl.misc;


import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.util.chat.CC;
import com.elevatemc.anticheat.util.funny.Funny;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

/**
 * @author Moose1301
 * @date 5/4/2024
 */
public class CrashGUI implements IGUI {
    private final Player target;
    public CrashGUI(Player target) {
        this.target = target;
    }
    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create(CC.RED + CC.BOLD + "Crasher", 1).setAutomaticPaginationEnabled(false);
        for (Funny.CrashType value : Funny.CrashType.values()) {
            SGButton button = new SGButton(
                    new ItemBuilder(value.getIcon()).
                            name(value.getName()).
                            enchant(Enchantment.PROTECTION_FALL, 1).flag(ItemFlag.HIDE_ENCHANTS).build())
                    .withListener((InventoryClickEvent event) ->
                            value.apply(target)
                    );
            menu.addButton(button);
        }
        player.openInventory(menu.getInventory());
    }
}