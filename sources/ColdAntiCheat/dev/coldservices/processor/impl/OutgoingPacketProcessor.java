package dev.coldservices.processor.impl;

import ac.artemis.packet.spigot.wrappers.GPacket;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.processor.PacketProcessor;

public final class OutgoingPacketProcessor extends PacketProcessor {

    public OutgoingPacketProcessor(final PlayerData data) {
        super(data);
    }

    @Override
    public void handle(final GPacket packet) {
        this.handleProcessors(packet, false);

        for (final PacketCheck packetCheck : data.getPacketChecks()) {
            packetCheck.handle(packet);
        }

        this.handleProcessors(packet, true);
    }
}
