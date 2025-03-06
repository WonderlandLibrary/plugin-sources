package me.frep.vulcan.spigot.check.impl.player.invalid;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Invalid", type = 'F', complexType = "Spoofed Y", description = "Impossible Y-axis change.")
public class InvalidF extends AbstractCheck
{
    public InvalidF(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean exempt = this.isExempt(ExemptType.DEATH, ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.WORLD_CHANGE);
            if (Math.abs(deltaY) > 100.0 && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
