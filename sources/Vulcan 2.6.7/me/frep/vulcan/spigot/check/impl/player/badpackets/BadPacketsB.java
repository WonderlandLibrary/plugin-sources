package me.frep.vulcan.spigot.check.impl.player.badpackets;

import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'B', complexType = "Sequence", description = "More than 20 flying packets in a row.")
public class BadPacketsB extends AbstractCheck
{
    private int streak;
    
    public BadPacketsB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final WrappedPacketInFlying wrapper = this.data.getFlyingWrapper();
            if (wrapper.isPosition() || this.data.getPlayer().isInsideVehicle()) {
                this.streak = 0;
                return;
            }
            final boolean exempt = this.isExempt(ExemptType.SERVER_VERSION, ExemptType.CLIENT_VERSION);
            if (++this.streak > 20 && !exempt) {
                this.fail("streak=" + this.streak);
            }
        }
        else if (packet.isSteerVehicle()) {
            this.streak = 0;
        }
    }
}
