package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.util.PlayerUtil;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = '4', complexType = "Elytra", description = "Sending Elytra packets without an Elytra.")
public class BadPackets4 extends AbstractCheck
{
    public BadPackets4(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isEntityAction()) {
            final WrappedPacketInEntityAction wrapper = this.data.getEntityActionWrapper();
            final boolean exempt = this.isExempt(ExemptType.FLIGHT);
            if (!exempt) {
                if (wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.START_FALL_FLYING) {
                    final boolean elytra = this.data.getActionProcessor().isWearingElytra();
                    final boolean version = PlayerUtil.isHigherThan1_9(this.data.getPlayer());
                    if (!elytra && version) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("elytra=" + elytra + " action=" + wrapper.getAction());
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                }
            }
        }
        else if (packet.isFlying()) {
            this.decayBuffer();
        }
    }
}
