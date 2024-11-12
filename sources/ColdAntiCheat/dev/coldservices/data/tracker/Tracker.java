package dev.coldservices.data.tracker;

import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.RequiredArgsConstructor;
import dev.coldservices.data.PlayerData;

@RequiredArgsConstructor
public abstract class Tracker {

    protected final PlayerData data;

    public void handle(final GPacket packet) {
    }

    public void handlePost(final GPacket packet) {
    }
}
