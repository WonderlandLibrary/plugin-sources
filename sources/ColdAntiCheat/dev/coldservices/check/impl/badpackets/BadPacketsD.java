package dev.coldservices.check.impl.badpackets;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientKeepAlive;
import ac.artemis.packet.wrapper.client.PacketPlayClientTransaction;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientKeepAlive;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientTransaction;
import dev.coldservices.CAC;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import org.bukkit.Bukkit;

@CheckManifest(name = "BadPackets", type = "D", description = "Checks if the user is lost connection")
public class BadPacketsD extends Check implements PacketCheck {

    private long lastTransaction, lastKeepAlive;

    public BadPacketsD(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof PacketPlayClientTransaction) {
            lastTransaction = packet.getTimestamp();
        }

        if(packet instanceof PacketPlayClientKeepAlive) {
            lastKeepAlive = packet.getTimestamp();
        }

        if(packet instanceof PacketPlayClientFlying) {
            boolean exempt = this.isExempt(ExemptType.JOIN, ExemptType.TELEPORT, ExemptType.TELEPORTED_RECENTLY);

            if(exempt) return;

            boolean keepAliveTimeOut = packet.getTimestamp() - lastKeepAlive >= 15000;
            boolean transactionTimeOut = packet.getTimestamp() - lastTransaction >= 15000;

            if(transactionTimeOut || keepAliveTimeOut) {
                Bukkit.getScheduler().runTaskLater(CAC.get().getPlugin(), () -> {
                    data.getPlayer().kickPlayer("timed out. (Connection problems?)");
                }, 20L);
            }
        }
    }
}
