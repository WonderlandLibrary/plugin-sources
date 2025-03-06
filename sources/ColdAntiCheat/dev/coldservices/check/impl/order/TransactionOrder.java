package dev.coldservices.check.impl.order;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientTransaction;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientTransaction;
import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerTransaction;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;

@CheckManifest(name = "TransactionOrder", type = "A", description = "Checks if the transactions is synced")
public class TransactionOrder extends Check implements PacketCheck {

    private boolean should;
    private int lastServerActionNumber, lastServerWindowID;

    public TransactionOrder(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayServerTransaction) {
            GPacketPlayServerTransaction wrapper = (GPacketPlayServerTransaction) packet;

            lastServerActionNumber = wrapper.getActionNumber();
            lastServerWindowID = wrapper.getWindowId();
            should = true;
        }

        if(packet instanceof GPacketPlayClientTransaction && should) {
            GPacketPlayClientTransaction wrapper = (GPacketPlayClientTransaction) packet;

            if(wrapper.getActionNumber() != lastServerActionNumber || wrapper.getWindowId() != lastServerWindowID) {
                if(this.buffer.increase() > 9) {
                    this.failNoBan("sent to client: " + lastServerWindowID + ":" + lastServerActionNumber +
                            " * received: " + wrapper.getWindowId() + ":" + wrapper.getActionNumber());
                }
            } else {
                this.buffer.decreaseBy(2);
            }

            should = false;
        }
    }
}
