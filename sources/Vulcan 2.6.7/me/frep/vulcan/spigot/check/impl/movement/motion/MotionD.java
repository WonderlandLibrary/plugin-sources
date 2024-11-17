package me.frep.vulcan.spigot.check.impl.movement.motion;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Motion", type = 'D', complexType = "Exponent", description = "Too small Y-axis change.")
public class MotionD extends AbstractCheck
{
    public MotionD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            final double deltaY = Math.abs(this.data.getPositionProcessor().getDeltaY());
            final boolean exempt = this.isExempt(ExemptType.LIQUID, ExemptType.GLIDING, ExemptType.RIPTIDE, ExemptType.BOAT, ExemptType.TELEPORT, ExemptType.SWIMMING, ExemptType.WEB, ExemptType.VELOCITY, ExemptType.PLACED_WEB, ExemptType.ELYTRA, ExemptType.FARMLAND, ExemptType.ENDER_PEARL, ExemptType.SLIME, ExemptType.PISTON, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.SLOW_FALLING, ExemptType.VEHICLE) || this.data.getPositionProcessor().isNearRail();
            final boolean invalid = deltaY < 0.003 && deltaY > 0.0;
            if (invalid && !exempt) {
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
