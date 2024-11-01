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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerAbilities;

public class BadPacketsB extends PacketCheck {
    public BadPacketsB(PlayerData playerData) {
        super(playerData, "Bad Packets B", "Spoofed abilities packet", new ViolationHandler(2, 60L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_ABILITIES) {
            final WrapperPlayClientPlayerAbilities wrapper = new WrapperPlayClientPlayerAbilities(event);

            check: {
                if (playerData.getPlayer().getAllowFlight()) break check;

                if (wrapper.isFlightAllowed().orElse(false)) {
                    if (increaseBuffer() > 2.0) {
                        handleViolation(new DetailedPlayerViolation(this,""));
                        staffAlert(new PlayerViolation(this));
                    }
                } else {
                    resetBuffer();
                }
            }
        }
    }
}