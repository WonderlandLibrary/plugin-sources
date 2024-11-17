package me.frep.vulcan.spigot.check.impl.combat.autoblock;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Block", type = 'C', complexType = "Order", description = "Attacked while sending BlockDig packet.")
public class AutoBlockC extends AbstractCheck
{
    public AutoBlockC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                final boolean sword = this.data.getPlayer().getItemInHand().getType().toString().contains("SWORD");
                final boolean invalid = this.data.getActionProcessor().isSendingDig();
                final boolean exempt = this.isExempt(ExemptType.CLIENT_VERSION);
                if (invalid && sword && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail();
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
