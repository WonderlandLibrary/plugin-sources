package me.frep.vulcan.spigot.check.impl.movement.sprint;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Sprint", type = 'A', complexType = "Angle", description = "Invalid sprint direction.")
public class SprintA extends AbstractCheck
{
    public SprintA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final float angle = this.data.getRotationProcessor().getMovementAngle();
            final int groundTicks = this.data.getPositionProcessor().getClientGroundTicks();
            final int sprintTicks = this.data.getActionProcessor().getSprintingTicks();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final boolean invalid = sprintTicks > 10 && angle > 1.5 && groundTicks > 6 && deltaXZ > 0.25;
            final boolean exempt = this.isExempt(ExemptType.RIPTIDE, ExemptType.TELEPORT, ExemptType.WORLD_CHANGE, ExemptType.JOINED, ExemptType.SERVER_POSITION, ExemptType.SWIMMING, ExemptType.LIQUID, ExemptType.ICE, ExemptType.SWIMMING, ExemptType.SERVER_POSITION);
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("angle=" + angle + " ticks=" + sprintTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
