package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'Q', complexType = "GCD Modulo", description = "GCD bypass flaw detected.")
public class AimQ extends AbstractCheck
{
    public AimQ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.hitTicks() < 3) {
            final boolean validSensitivity = this.data.getRotationProcessor().hasValidSensitivity();
            if (validSensitivity) {
                final double mcpSensitivity = this.data.getRotationProcessor().getMcpSensitivity();
                if (mcpSensitivity < 0.01) {
                    return;
                }
                final float f = (float)mcpSensitivity * 0.6f + 0.2f;
                final float gcd = f * f * f * 1.2f;
                final float yaw = this.data.getRotationProcessor().getYaw();
                final float pitch = this.data.getRotationProcessor().getPitch();
                final float adjustedYaw = yaw - yaw % gcd;
                final float adjustedPitch = pitch - pitch % gcd;
                final float yawDifference = Math.abs(yaw - adjustedYaw);
                final float pitchDifference = Math.abs(pitch - adjustedPitch);
                final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
                final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
                final float combinedChange = deltaYaw + deltaPitch;
                final double distance = this.data.getCombatProcessor().getDistance();
                final boolean invalid = (yawDifference == 0.0f || pitchDifference == 0.0f) && combinedChange > 0.75f;
                if (invalid && distance > 1.0) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("yawDifference=" + yawDifference + " pitchDifference=" + pitchDifference + " combined=" + combinedChange);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
