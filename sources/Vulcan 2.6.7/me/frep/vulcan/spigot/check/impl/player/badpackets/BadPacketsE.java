package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'E', complexType = "Interact", description = "Interacted with themselves.")
public class BadPacketsE extends AbstractCheck
{
    public BadPacketsE(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            final boolean exempt = this.isExempt(ExemptType.SPECTATOR);
            if (wrapper.getEntityId() == this.data.getPlayer().getEntityId() && !exempt) {
                this.fail();
            }
        }
    }
}
