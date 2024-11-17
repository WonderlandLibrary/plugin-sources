package me.frep.vulcan.spigot.check.impl.player.badpackets;

import java.util.Optional;
import io.github.retrooper.packetevents.utils.vector.Vector3f;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockplace.WrappedPacketInBlockPlace;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = '5', complexType = "Elytra", description = "Invalid Block place.")
public class BadPackets5 extends AbstractCheck
{
    public BadPackets5(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlace()) {
            final WrappedPacketInBlockPlace wrapper = new WrappedPacketInBlockPlace(packet.getRawPacket());
            final Optional<Vector3f> optional = wrapper.getCursorPosition();
            if (optional.isPresent()) {
                final Vector3f cursorPosition = optional.get();
                final float x = cursorPosition.getX();
                final float y = cursorPosition.getY();
                final float z = cursorPosition.getZ();
                if (x > 1.0f || y > 1.0f || z > 1.0f || x < 0.0f || y < 0.0f || z < 0.0f) {
                    this.fail("x=" + x + " y=" + y + " z=" + z);
                }
            }
        }
    }
}
