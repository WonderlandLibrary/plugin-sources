package me.frep.vulcan.spigot.check.impl.movement.motion;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Motion", type = 'A', complexType = "Constant", description = "Repeated vertical motions.")
public class MotionA extends AbstractCheck
{
    public MotionA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double lastDeltaY = this.data.getPositionProcessor().getLastDeltaY();
            final boolean ladder = Math.abs(deltaY - 0.11760000228882461) <= 0.001;
            if (ladder) {
                return;
            }
            final boolean climbable = this.data.getPositionProcessor().getSinceNearClimbableTicks() < 20;
            final boolean exempt = this.isExempt(ExemptType.HALF_BLOCK, ExemptType.CLIMBABLE, ExemptType.FLIGHT, ExemptType.BED, ExemptType.LIQUID, ExemptType.TELEPORT, ExemptType.VELOCITY, ExemptType.ILLEGAL_BLOCK, ExemptType.SLEEPING, ExemptType.SOUL_SAND, ExemptType.HONEY, ExemptType.SLIME, ExemptType.LEVITATION, ExemptType.SPECTATOR, ExemptType.SCAFFOLDING, ExemptType.FENCE, ExemptType.BUBBLE_COLUMN, ExemptType.CREATIVE, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.CANCELLED_PLACE, ExemptType.LENIENT_SCAFFOLDING, ExemptType.PLACED_CLIMBABLE, ExemptType.KELP, ExemptType.BOAT, ExemptType.CHUNK, ExemptType.TRAPDOOR, ExemptType.POWDER_SNOW, ExemptType.EMPTIED_BUCKET);
            final boolean invalid = deltaY > 0.0 && deltaY == lastDeltaY;
            if (invalid && !exempt && !climbable) {
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
