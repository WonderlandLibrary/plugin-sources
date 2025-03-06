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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;

public class BadPacketsK extends PacketCheck {

    public BadPacketsK(PlayerData playerData) {
        super(playerData, "Bad Packets K", "Liquid bounce client spammer.", new ViolationHandler(1, 3000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CHAT_MESSAGE) {
            WrapperPlayClientChatMessage wrapper = new WrapperPlayClientChatMessage(event);

            if (wrapper.getMessage().contains("liquidbounce(.net)") || wrapper.getMessage().contains("CCBlueX on yt")) {
                handleViolation(new DetailedPlayerViolation(this, ""));
                staffAlert(new PlayerViolation(this));
            }
        }
    }
}
