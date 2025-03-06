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
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;

public class BadPacketsD extends PacketCheck {

    int streak;
    public BadPacketsD(PlayerData playerData) {
        super(playerData, "Bad Packets D", "Sent more than 20 flying packets in one tick", new ViolationHandler(10, 30000L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            final WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerFlying(event);

            if (playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_9)) return;

            if (wrapper.hasPositionChanged() || playerData.getPlayer().getPlayer().isInsideVehicle() || teleportTracker.isTeleport() || teleportTracker.getSinceTeleportTicks() <= 1) {
                streak = 0;
                return;
            }

            if (++streak > 23) {
                handleViolation(new DetailedPlayerViolation(this,""));
                staffAlert(new PlayerViolation(this));
            }
        } else if (event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE) {
            streak = 0;
        }
    }
}
