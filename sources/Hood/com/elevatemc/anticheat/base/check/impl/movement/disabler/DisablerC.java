package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientKeepAlive;

public class DisablerC extends PacketCheck {
    public DisablerC(PlayerData playerData) {
        super(playerData, "Disabler C", "Spoofed KeepAlive packet", new ViolationHandler(1, 3000L), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            WrapperPlayClientKeepAlive keepAlive = new WrapperPlayClientKeepAlive(event);

            if (keepAlive.getId() == 0L) {
                handleViolation(new PlayerViolation(this));
                staffAlert(new PlayerViolation(this));
            }
        }
    }
}