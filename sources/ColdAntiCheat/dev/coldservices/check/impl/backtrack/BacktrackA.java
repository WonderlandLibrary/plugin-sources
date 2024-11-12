package dev.coldservices.check.impl.backtrack;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientKeepAlive;
import ac.artemis.packet.wrapper.client.PacketPlayClientPosition;
import ac.artemis.packet.wrapper.client.PacketPlayClientTransaction;
import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerKeepAlive;
import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerTransaction;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;

@CheckManifest(name = "Backtrack", type = "A", description = "The best backtrack check.")
public class BacktrackA extends Check implements PacketCheck {

    // MADE BY RAWRL
    // It's actually decent check...
    // p

    // THE IDEA ON IT IS
    // Basicly we send to the client a new transaction without breaking the order
    // Client normally sends the transaction back as received, or in the next tick (CPacketFlying, Position)
    // If the client sends position packets but delays Transactions, that def means there is something wrong.
    // So we handle the check here, might be falsing on connection freezes (i think). not gonna false high ping players.
    // But still a good check.

    private long lastTransactionTime, lastTransactionDiff;

    public BacktrackA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof PacketPlayClientFlying) {
            data.send(new GPacketPlayServerTransaction((byte) 0, data.getConnectionTracker().getTransactionId(), false));
        }

        if(packet instanceof PacketPlayClientPosition) {
            if(data.getPlayer().getDisplayName().equalsIgnoreCase("credit_")) return;

            boolean exempt = this.isExempt(ExemptType.TELEPORTED_RECENTLY, ExemptType.TELEPORT, ExemptType.BOAT,
                    ExemptType.JOIN, ExemptType.CHUNK);

            if(exempt) return;

            double diffBetweenTransaction = System.currentTimeMillis() - lastTransactionTime;

            if(diffBetweenTransaction >= 200) {
                if(this.buffer.increase() > 3) {
                    this.failNoBan( "time: " + diffBetweenTransaction);
                }
            } else {
                this.buffer.decreaseBy(0.1);
            }
        }

        if(packet instanceof PacketPlayClientTransaction) {
            lastTransactionTime = packet.getTimestamp();
        }
    }
}
