package com.elevatemc.anticheat.base.check.impl.misc.pingspoof;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class PingSpoofA extends PacketCheck {

    int lastKeepAlive = -1;
    public PingSpoofA(PlayerData playerData) {
        super(playerData, "Ping Spoof A", "LOL WTF?", ViolationHandler.EXPERIMENTAL, Category.MISC, SubCategory.PING_SPOOF, 10);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PONG || event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION) {
            int keepAlivePing = (int) keepAliveTracker.getKeepAlivePing();
            int difference = keepAlivePing - lastKeepAlive;

            boolean exempt = teleportTracker.isTeleport() || System.currentTimeMillis() - playerData.getLastJoin() < 3000L;

            if (exempt) return;

            boolean differenceDistance = difference > 1000 && positionTracker.getLastX() > positionTracker.getX();
            boolean invalidDifference = difference > 5000;

            if (differenceDistance) {
                handleViolation(new DetailedPlayerViolation(this, "D " + difference));
            }
            if (invalidDifference) {
                handleViolation(new DetailedPlayerViolation(this, "I " + difference));
            }
            lastKeepAlive = keepAlivePing;
        }
    }
}
