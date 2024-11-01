package com.elevatemc.anticheat.base.gui;


import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.manager.GuiManager;
import org.bukkit.entity.Player;

public interface IGUI {
    void openToPlayer(final Player player);

    default GuiManager getGuiService() {
        return HoodPlugin.INSTANCE.getGuiManager();
    }
}