package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'P', complexType = "Yaw Acceleration", experimental = false, description = "Large yaw acceleration.")
public class AimP extends AbstractCheck
{
    public AimP(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.attacking(3)) {
            final float pitch = this.data.getRotationProcessor().getPitch();
            final float yawAccel = this.data.getRotationProcessor().getYawAccel();
            final float pitchAccel = this.data.getRotationProcessor().getPitchAccel();
            final double distance = this.data.getCombatProcessor().getComplexDistance();
            final boolean invalid = yawAccel > 20.0f && pitchAccel < 0.05 && Math.abs(pitch) < 60.0f && distance > 1.0;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("yawAccel=" + yawAccel + " pitchAccel=" + pitchAccel);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
