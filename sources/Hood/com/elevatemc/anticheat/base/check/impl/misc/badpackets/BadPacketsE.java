package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientHeldItemChange;

public class BadPacketsE extends PacketCheck {

    int lastSlot = -1;
    public BadPacketsE(PlayerData playerData) {
        super(playerData, "Bad Packets E", "Invalid slot", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.HELD_ITEM_CHANGE) {

            if (teleportTracker.isTeleport()) {
                lastSlot = -1;
            }

            final WrapperPlayClientHeldItemChange wrapper = new WrapperPlayClientHeldItemChange(event);

            final int slot = wrapper.getSlot();

            if (slot == lastSlot && lastSlot != -1) {
                if (increaseBuffer() > 2) handleViolation(new DetailedPlayerViolation(this,""));

            }
            lastSlot = slot;
        }
    }
}
