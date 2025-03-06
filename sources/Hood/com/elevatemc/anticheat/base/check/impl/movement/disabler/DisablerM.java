package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class DisablerM extends PacketCheck {

    public DisablerM(PlayerData playerData) {
        super(playerData, "Disabler M", "", new ViolationHandler(10, 30000L), Category.MISC, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.ANIMATION
        || event.getPacketType() == PacketType.Play.Client.WINDOW_CONFIRMATION
        || event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT
        || event.getPacketType() == PacketType.Play.Client.PONG
        || event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            boolean joined = playerData.getTicksExisted() < 25;
            boolean teleport = teleportTracker.getSinceTeleportTicks() < 5 ;

            fuckambassator: {
                if (joined || teleport || !playerData.getWorldTracker().isChunkLoaded(positionTracker.x, positionTracker.z)) break fuckambassator;

                int ping = (int) keepAliveTracker.getKeepAlivePing();
                int idkwhyimusingthisbutitsjustincasetheseniggersaredoingsmthweird = PacketEvents.getAPI().getPlayerManager().getPing(playerData.getPlayer());

                if (ping == 0 || idkwhyimusingthisbutitsjustincasetheseniggersaredoingsmthweird == 0) {
                    increaseBuffer();
                } else {
                    decreaseBuffer();
                }

                if (getBuffer() > 10) {
                    handleViolation(new DetailedPlayerViolation(this, "0 ms."));
                    staffAlert(new PlayerViolation(this));
                }
            }
        }
    }
}
