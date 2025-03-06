package com.elevatemc.anticheat.base.listener.player;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.StandardCharsets;

public class ClientBrandListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(final String channel, final Player player, final byte[] msg) {
        final PlayerData data = Hood.get().getPlayerDataManager().getData(player.getUniqueId());

        if (data == null) return;

        final String clientBrand = StringUtils.capitalize(new String(msg, StandardCharsets.UTF_8).substring(1));

        data.setClientBrand(clientBrand);
    }
}

