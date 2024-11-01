package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClientStatus;

public class DisablerF extends PacketCheck {

    public DisablerF(PlayerData playerData) {
        super(playerData, "Disabler F", "Disabler detected", new ViolationHandler(1, 300), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLIENT_STATUS) {
            WrapperPlayClientClientStatus action = new WrapperPlayClientClientStatus(event);

            if (!action.getAction().equals(WrapperPlayClientClientStatus.Action.PERFORM_RESPAWN)) return;

            if (!playerData.getPlayer().isDead()) {
                if (increaseBuffer() > 3) handleViolation(new DetailedPlayerViolation(this, ""));
            } else {
                decreaseBuffer();
            }
        }
    }
}
