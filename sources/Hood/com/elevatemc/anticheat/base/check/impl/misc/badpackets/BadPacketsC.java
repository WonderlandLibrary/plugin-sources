package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPluginMessage;

public class BadPacketsC extends PacketCheck {
    public BadPacketsC(PlayerData playerData) {
        super(playerData, "Bad Packets C", "Illegal Payload Size", new ViolationHandler(2, 60L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLUGIN_MESSAGE) {
            WrapperPlayClientPluginMessage payload = new WrapperPlayClientPluginMessage(event);

            if (payload.getChannelName().length() > 1500) {
                handleViolation(new DetailedPlayerViolation(this,""));
                staffAlert(new PlayerViolation(this));
            }
        }
    }
}