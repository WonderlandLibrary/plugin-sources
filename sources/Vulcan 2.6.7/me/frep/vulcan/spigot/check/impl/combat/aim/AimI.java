package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'I', complexType = "Constant", experimental = false, description = "Not constant rotations.")
public class AimI extends AbstractCheck
{
    public AimI(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.hitTicks() < 3) {
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final float lastDeltaPitch = this.data.getRotationProcessor().getLastDeltaPitch();
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final long expandedPitch = (long)(MathUtil.EXPANDER * deltaPitch);
            final long expandedLastPitch = (long)(MathUtil.EXPANDER * lastDeltaPitch);
            final boolean cinematic = this.isExempt(ExemptType.CINEMATIC);
            final long gcd = MathUtil.getGcd(expandedPitch, expandedLastPitch);
            final boolean tooLowSensitivity = this.data.getRotationProcessor().hasTooLowSensitivity();
            final boolean validAngles = deltaYaw > 0.25f && deltaPitch > 0.25f && deltaPitch < 20.0f && deltaYaw < 20.0f;
            final boolean invalid = !cinematic && gcd < 131072L;
            if (invalid && validAngles && !tooLowSensitivity) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("rotation=" + gcd / 1000L + " deltaPitch=" + deltaPitch + " deltaYaw=" + deltaYaw);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
