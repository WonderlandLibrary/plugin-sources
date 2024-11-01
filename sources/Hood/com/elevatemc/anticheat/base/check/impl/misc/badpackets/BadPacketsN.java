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
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.protocol.player.DiggingAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;

public class BadPacketsN extends PacketCheck {

    public BadPacketsN(PlayerData playerData) {
        super(playerData, "Bad Packets N", "Checks for NoSlowDown", new ViolationHandler(2, 300), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_DIGGING) {
            final WrapperPlayClientPlayerDigging wrapper = new WrapperPlayClientPlayerDigging(event);

            final boolean exempt = playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9);

            if (!exempt) {
                final boolean invalid = wrapper.getAction().equals(DiggingAction.RELEASE_USE_ITEM) && actionTracker.isBlocking();

                if (invalid) {
                    if (increaseBuffer() > 3.0) {
                        handleViolation(new DetailedPlayerViolation(this,"XZ " + positionTracker.getDeltaXZ()));
                        staffAlert(new PlayerViolation(this));
                    }
                } else {
                    decreaseBufferBy(.5);
                }
            }
        }
    }
}
