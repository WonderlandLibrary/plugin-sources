package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'K', complexType = "Linear", experimental = false, description = "Not constant rotations.")
public class AimK extends AbstractCheck
{
    public AimK(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.hitTicks() < 3) {
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final float lastDeltaYaw = this.data.getRotationProcessor().getLastDeltaYaw();
            final float lastDeltaPitch = this.data.getRotationProcessor().getLastDeltaPitch();
            final double divisorYaw = (double)MathUtil.getGcd((long)(deltaYaw * MathUtil.EXPANDER), (long)(lastDeltaYaw * MathUtil.EXPANDER));
            final double divisorPitch = (double)MathUtil.getGcd((long)(deltaPitch * MathUtil.EXPANDER), (long)(lastDeltaPitch * MathUtil.EXPANDER));
            final double constantYaw = divisorYaw / MathUtil.EXPANDER;
            final double constantPitch = divisorPitch / MathUtil.EXPANDER;
            final double currentX = deltaYaw / constantYaw;
            final double currentY = deltaPitch / constantPitch;
            final double previousX = lastDeltaYaw / constantYaw;
            final double previousY = lastDeltaPitch / constantPitch;
            if (deltaYaw > 0.1f && deltaPitch > 0.1f && deltaYaw < 20.0f && deltaPitch < 20.0f) {
                final double moduloX = currentX % previousX;
                final double moduloY = currentY % previousY;
                final double floorModuloX = Math.abs(Math.floor(moduloX) - moduloX);
                final double floorModuloY = Math.abs(Math.floor(moduloY) - moduloY);
                final boolean invalidX = moduloX > 60.0 && floorModuloX > 0.1;
                final boolean invalidY = moduloY > 60.0 && floorModuloY > 0.1;
                final double sensitivity = this.data.getRotationProcessor().getSensitivity();
                final boolean tooLowSensitivity = sensitivity < 100.0 && sensitivity > -1.0;
                final boolean cinematic = this.isExempt(ExemptType.CINEMATIC);
                if (invalidX && invalidY && !cinematic && !tooLowSensitivity) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
