package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPositionAndRotation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import de.gerrygames.viarewind.utils.math.Vector3d;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;

@Getter
@Setter
public class TeleportTracker extends Tracker {
    private final ArrayDeque<Vector3d> teleports = new ArrayDeque<>();

    private int sinceTeleportTicks;

    @Getter
    public boolean teleport;

    public TeleportTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.PLAYER_POSITION_AND_LOOK) {
            WrapperPlayServerPlayerPositionAndLook playerPositionAndLook = new WrapperPlayServerPlayerPositionAndLook(event);

            Vector3d teleport = new Vector3d(playerPositionAndLook.getX(), playerPositionAndLook.getY(), playerPositionAndLook.getZ());

            playerData.getTransactionTracker().confirmPre(() -> teleports.add(teleport));
        }
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            teleport = false;
            sinceTeleportTicks++;

            Vector3d teleportPosition = teleports.peek();
            if (teleportPosition != null && event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {
                WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerPositionAndRotation(event);
                double x = wrapper.getLocation().getX();
                double y = wrapper.getLocation().getY();
                double z = wrapper.getLocation().getZ();

                if (Math.abs(x - teleportPosition.getX()) < 1E-8
                        && Math.abs(y - teleportPosition.getY()) < 1E-8
                        && Math.abs(z - teleportPosition.getZ()) < 1E-8) {
                    teleports.remove();

                    sinceTeleportTicks = 0;
                    teleport = true;
                }
            }
        }
    }

    public boolean isJoined() {
        return System.currentTimeMillis() - playerData.getLastJoin() < 5000L;
    }

    public boolean possiblyTeleporting() {
        return !playerData.getPositionTracker().isInLoadedChunk();
    }

    public boolean isTeleport() {
        return sinceTeleportTicks < 3;
    }
}