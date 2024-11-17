package me.frep.vulcan.spigot.check.impl.player.invalid;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Invalid", type = 'G', complexType = "Invalid X/Z movement", description = "Impossible X/Z change.")
public class InvalidG extends AbstractCheck
{
    public InvalidG(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            double max = PlayerUtil.getBaseSpeed(this.data, 1.0f);
            if (this.isExempt(ExemptType.VELOCITY)) {
                max += this.data.getVelocityProcessor().getVelocityXZ();
            }
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.HIGH_FLY_SPEED, ExemptType.DOLPHINS_GRACE, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.RIPTIDE, ExemptType.VEHICLE, ExemptType.ENDER_PEARL, ExemptType.CHORUS_FRUIT, ExemptType.SLIME, ExemptType.AROUND_SLIME);
            final boolean exploit = this.isExempt(ExemptType.FLIGHT) && !this.data.getPositionProcessor().isAllowFlight();
            final boolean walkSpeed = this.data.getPositionProcessor().getFlySpeed() > 0.11f;
            if (exploit && !exempt && deltaXZ > max && !walkSpeed) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaXZ=" + deltaXZ + " ticks=" + this.data.getPositionProcessor().getSinceFlyingTicks());
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
