package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'O', complexType = "Small Yaw", description = "Too small pitch change.")
public class AimO extends AbstractCheck
{
    public AimO(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.attacking(2)) {
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final boolean invalid = deltaPitch < 0.05 && deltaPitch > 0.0f && deltaYaw == 0.0f;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaPitch=" + deltaPitch + " deltaYaw=" + deltaYaw);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
