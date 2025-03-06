package com.elevatemc.anticheat.base.gui.impl.misc;

import ai.rippedthisofsomeguy.gui.SGMenu;
import ai.rippedthisofsomeguy.gui.buttons.SGButton;
import ai.rippedthisofsomeguy.gui.item.ItemBuilder;
import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.base.gui.IGUI;
import com.elevatemc.anticheat.util.chat.CC;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

@Getter
public class HoodSuspectsGUI implements IGUI {

    @Override
    public void openToPlayer(Player player) {
        SGMenu menu = HoodPlugin.INSTANCE.getGuiManager2().create(CC.PRI + "Suspected Players", 10).setAutomaticPaginationEnabled(true);

        // this is the fix for the mem leak! (Too bad the command needs to be ran....)
        menu.clearStickiedSlots();

        for (Player suspect : Bukkit.getOnlinePlayers()) {
            PlayerData data = Hood.getPlayerDataManager().getData(suspect.getUniqueId());

            if (data == null) {
                continue; // Skip this player if no data is found
            }
            // This was the issue cause I never actually tried to make this gui properly, and I think this was the cause to the crashes...LOL!
            // not exactly the reason what I mean is that this has a mem leak, as it never cleared logged players.
            int totalViolations = 0; // Reset for each suspect

            for (Check check : data.getCheckData().getChecks()) {
                // Flag for non-experimental checks to actually make them a suspect.
                if (check.getVl() > 0 && check.getViolationHandler().getMaxViolations() != Integer.MAX_VALUE) {
                    totalViolations += check.getVl();
                }
            }

            if (totalViolations > 30) {
                SGButton button = new SGButton(new ItemBuilder(Material.MAP)
                        .name(CC.PRI + data.getPlayer().getName() + "&7: " + " VL: " + totalViolations)
                        .build())
                        .withListener((InventoryClickEvent event) -> player.performCommand("sf " + data.getPlayer().getName()));

                menu.addButton(button);
            }
        }

        player.openInventory(menu.getInventory());
    }
}
