package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClientStatus;

public class BadPacketsW extends PacketCheck {

    boolean attacking, swinging;
    public BadPacketsW(PlayerData playerData) {
        super(playerData, "Bad Packets W", "", new ViolationHandler(5, 3000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLIENT_STATUS) {
            WrapperPlayClientClientStatus wrapper = new WrapperPlayClientClientStatus(event);
            if (wrapper.getAction() != WrapperPlayClientClientStatus.Action.OPEN_INVENTORY_ACHIEVEMENT) {
                return;
            }
            if (attacking || swinging) {
                if (increaseBuffer() > 4.0) {
                    handleViolation(new DetailedPlayerViolation(this, ""));
                    staffAlert(new PlayerViolation(this));
                }
            } else {
                resetBuffer();
            }
        } else if (PacketUtil.isFlying(event.getPacketType())) {
            attacking = false;
            swinging = false;
        } else if (event.getPacketType() == PacketType.Play.Client.ANIMATION) {
            swinging = true;
        } else if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            attacking = true;
        }
    }
}
