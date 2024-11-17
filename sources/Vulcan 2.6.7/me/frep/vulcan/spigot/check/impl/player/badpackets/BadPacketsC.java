package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'C', complexType = "Pitch", description = "Impossible pitch.")
public class BadPacketsC extends AbstractCheck
{
    public BadPacketsC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation()) {
            final float pitch = this.data.getRotationProcessor().getPitch();
            if (Math.abs(pitch) > 90.0f) {
                this.fail("pitch=" + pitch);
            }
        }
    }
}
