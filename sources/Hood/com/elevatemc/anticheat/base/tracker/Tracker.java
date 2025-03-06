package com.elevatemc.anticheat.base.tracker;

import com.elevatemc.anticheat.base.PlayerData;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.atteo.classindex.IndexSubclasses;

@Getter
@IndexSubclasses
@RequiredArgsConstructor
public abstract class Tracker {
    protected final PlayerData playerData;

    public void registerIncomingPreHandler(PacketReceiveEvent event) {
    }

    public void registerIncomingPostHandler(PacketReceiveEvent event) {
    }

    public void registerOutgoingPreHandler(PacketSendEvent event) {
    }

    public void registerOutgoingPostHandler(PacketSendEvent event) {
    }
}