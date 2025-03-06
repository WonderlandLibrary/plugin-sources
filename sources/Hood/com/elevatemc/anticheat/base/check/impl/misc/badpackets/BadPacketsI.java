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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;

public class BadPacketsI extends PacketCheck {
    boolean placing;
    public BadPacketsI(PlayerData playerData) {
        super(playerData, "Bad Packets I", "Invalid attack", new ViolationHandler(1, 3000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            final WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                final boolean invalid = actionTracker.isBlocking();
                final boolean sword = playerData.getPlayer().getItemInHand().getType().toString().contains("SWORD");

                if (invalid && sword) {
                    handleViolation(new DetailedPlayerViolation(this,""));
                    staffAlert(new PlayerViolation(this));
                }
            }
        }
    }
}
