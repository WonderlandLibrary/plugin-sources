package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.steervehicle.WrappedPacketInSteerVehicle;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'F', complexType = "Steer Vehicle", description = "Invalid Steer Vehicle packet.")
public class BadPacketsF extends AbstractCheck
{
    public BadPacketsF(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isSteerVehicle()) {
            final WrappedPacketInSteerVehicle wrapper = new WrappedPacketInSteerVehicle(packet.getRawPacket());
            final float forwardValue = wrapper.getForwardValue();
            final float sideValue = wrapper.getSideValue();
            final boolean invalid = Math.abs(forwardValue) > 0.98f || Math.abs(sideValue) > 0.98f;
            if (invalid) {
                this.fail("forward=" + forwardValue + " side=" + sideValue);
            }
        }
    }
}
