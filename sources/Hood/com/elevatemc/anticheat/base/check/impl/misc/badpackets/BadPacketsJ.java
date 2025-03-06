package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;

public class BadPacketsJ extends PacketCheck {

    boolean sendingAction;
    public BadPacketsJ(PlayerData playerData) {
        super(playerData, "Bad Packets J", "Invalid attack", new ViolationHandler(1, 3000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            final WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            check: {
                if (wrapper.getAction() != WrapperPlayClientInteractEntity.InteractAction.ATTACK) break check;

                final boolean invalid = actionTracker.isSendingAction();
                final boolean exempt = Hood.get().isLagging();

                if (invalid && !exempt) {
                    handleViolation(new DetailedPlayerViolation(this, ""));
                }
            }
        }
    }
}
