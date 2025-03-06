package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientKeepAlive;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerKeepAlive;
import it.unimi.dsi.fastutil.longs.Long2LongArrayMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class KeepAliveTracker extends Tracker {
    private final Long2LongMap expectedKeepAlives = new Long2LongArrayMap();

    private boolean acceptedKeepAlive;

    private long keepAlivePing, flyingDelay, lastFlying;

    private long id = -1, lastKeepAlive;
    private final AtomicInteger keepAliveSent = new AtomicInteger(0), keepAliveReceived = new AtomicInteger(0);
    public KeepAliveTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            WrapperPlayClientKeepAlive keepAlive = new WrapperPlayClientKeepAlive(event);

            id = keepAlive.getId();
            long timestamp = event.getTimestamp();

            if (expectedKeepAlives.containsKey(id)) {
                keepAlivePing = timestamp - expectedKeepAlives.remove(id);
                acceptedKeepAlive = true;
            }
            keepAliveReceived.getAndIncrement();
            lastKeepAlive = System.currentTimeMillis();
        }
        if (PacketUtil.isFlying(event.getPacketType())) {

            flyingDelay = System.currentTimeMillis() - lastFlying;
            lastFlying = System.currentTimeMillis();
        }
    }

    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.KEEP_ALIVE) {
            WrapperPlayServerKeepAlive serverKeepAlive = new WrapperPlayServerKeepAlive(event);

            keepAliveSent.getAndIncrement();
            expectedKeepAlives.put(serverKeepAlive.getId(), event.getTimestamp());
        }
    }

    public int getPingTicks() {
        return (int) (Math.ceil(keepAlivePing / 100.0) + 2);
    }

}