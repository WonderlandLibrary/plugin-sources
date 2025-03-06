package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import lombok.Getter;

@Getter
public class PositionTracker extends Tracker {
    public double x, lastX,
            y, lastY,
            z, lastZ;

    private double deltaX, lastDeltaX,
            deltaY, lastDeltaY,
            deltaZ, lastDeltaZ,
            deltaXZ, lastDeltaXZ,
            acceleration, jumpMotion;

    private boolean moved, lastMoved, lastLastMoved, jumped;

    private int delayedFlyingTicks;

    private boolean sentMotion, inLoadedChunk, lastInLoadedChunk;
    private long lastMoveTimestamp;

    public PositionTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying playerFlying = new WrapperPlayClientPlayerFlying(event);
            lastMoveTimestamp = System.currentTimeMillis();
            playerData.setTicksExisted(playerData.getTicksExisted() + 1);

            ++delayedFlyingTicks;

            lastLastMoved = lastMoved;
            lastMoved = moved;
            moved = playerFlying.hasPositionChanged();

            lastX = x;
            lastY = y;
            lastZ = z;

            lastDeltaX = deltaX;
            lastDeltaY = deltaY;
            lastDeltaZ = deltaZ;

            lastDeltaXZ = deltaXZ;

            lastInLoadedChunk = inLoadedChunk;

            if (playerFlying.hasPositionChanged() ) {
                x = playerFlying.getLocation().getX();
                y = playerFlying.getLocation().getY();
                z = playerFlying.getLocation().getZ();

                deltaX = x - lastX;
                deltaY = y - lastY;
                deltaZ = z - lastZ;

                deltaXZ = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);

                acceleration = Math.abs(deltaXZ - lastDeltaXZ);

                jumpMotion = 0.42F + playerData.getAttributeTracker().getJumpBoost() * 0.1F;
                jumped = Math.abs(deltaY - jumpMotion) < 0.001D;

                inLoadedChunk = playerData.getWorldTracker().isChunkLoaded(x, z);
                // Run in pos because 0.03 is impossible in unloaded chunk
                if (!inLoadedChunk) {
                    sentMotion = false;
                }
            } else {
                // If the player didn't send position, and they're above 0 + 0.03 leniency they have a loaded chunk
                if (y > 0.03) {
                    inLoadedChunk = sentMotion = true;
                }
            }
        }
    }

    @Override
    public void registerIncomingPostHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            WrapperPlayClientPlayerFlying playerFlying = new WrapperPlayClientPlayerFlying(event);

            if (playerFlying.hasPositionChanged()) {
                delayedFlyingTicks = 0;
            }
        }
    }

    public boolean isPossiblyZeroThree() {
        return (!lastMoved || !lastLastMoved);
    }

}