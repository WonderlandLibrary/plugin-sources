package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'X', complexType = "Analysis", description = "Generic rotation analysis heuristic.")
public class AimX extends AbstractCheck
{
    public AimX(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.hitTicks() < 3) {
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final int sensitivity = this.data.getRotationProcessor().getSensitivity();
            final boolean cinematic = this.isExempt(ExemptType.CINEMATIC, ExemptType.SPECTATOR);
            final boolean invalid = sensitivity > 205 && deltaYaw > 1.25f && deltaPitch > 1.25f;
            if (invalid && !cinematic) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("sens=" + sensitivity + " deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
