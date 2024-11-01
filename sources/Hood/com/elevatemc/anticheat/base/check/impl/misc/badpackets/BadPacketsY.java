package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class BadPacketsY extends PacketCheck {
    private int ticks;

    public BadPacketsY(PlayerData playerData) {
        super(playerData, "Bad Packets Y", "Auto Block type shit", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.BAD_PACKETS, 3);
    }
    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {
            if (actionTracker.getLastAttack() <= 1 && ticks < 2) {
                if (increaseBuffer() > 5) {
                    handleViolation(new DetailedPlayerViolation(this, "T " + ticks));
                }
            } else {
                resetBuffer();
            }
            ticks = 0;
        } else if (event.getPacketType() == PacketType.Play.Client.PLAYER_FLYING) {
            ticks++;
        }
    }
}
