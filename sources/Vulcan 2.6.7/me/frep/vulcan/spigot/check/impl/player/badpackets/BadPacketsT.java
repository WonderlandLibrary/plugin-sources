package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.keepalive.WrappedPacketInKeepAlive;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'T', complexType = "Keep Alive", experimental = false, description = "Invalid KeepAlive packets.")
public class BadPacketsT extends AbstractCheck
{
    private long lastId;
    
    public BadPacketsT(final PlayerData data) {
        super(data);
        this.lastId = -1L;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isKeepAlive()) {
            final WrappedPacketInKeepAlive wrapper = new WrappedPacketInKeepAlive(packet.getRawPacket());
            final long id = wrapper.getId();
            if (id == this.lastId || id == 0L) {
                this.fail("id=" + id + " lastId=" + this.lastId);
            }
            this.lastId = id;
        }
    }
}
