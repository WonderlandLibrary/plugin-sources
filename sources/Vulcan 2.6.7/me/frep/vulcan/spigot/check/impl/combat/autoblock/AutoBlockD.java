package me.frep.vulcan.spigot.check.impl.combat.autoblock;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Block", type = 'D', complexType = "Order", description = "Invalid attack order.")
public class AutoBlockD extends AbstractCheck
{
    private boolean interacting;
    private boolean attacking;
    
    public AutoBlockD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            this.attacking = false;
            this.interacting = false;
        }
        else if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK) {
                this.attacking = true;
            }
            if (wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.INTERACT || wrapper.getAction() == WrappedPacketInUseEntity.EntityUseAction.INTERACT_AT) {
                this.interacting = true;
            }
        }
        else if (packet.isBlockPlace()) {
            final boolean exempt = this.isExempt(ExemptType.CLIENT_VERSION);
            if (!exempt && this.attacking && !this.interacting) {
                this.fail("a=" + this.attacking + " i=" + this.interacting);
            }
        }
    }
}
