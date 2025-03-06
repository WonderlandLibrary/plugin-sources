package me.frep.vulcan.spigot.check.impl.player.invalid;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Invalid", type = 'C', complexType = "Y", description = "Too large Y-Axis movement.")
public class InvalidC extends AbstractCheck
{
    public InvalidC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean bukkitTeleport = this.data.getActionProcessor().getSinceBukkitTeleportTicks() < 80;
            final boolean death = this.data.getActionProcessor().getSinceDeathTicks() < 40;
            final boolean joined = System.currentTimeMillis() - this.data.getJoinTime() < 10000L;
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.RIPTIDE, ExemptType.GLIDING, ExemptType.FISHING_ROD, ExemptType.VELOCITY, ExemptType.JOINED, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.VEHICLE, ExemptType.WORLD_CHANGE, ExemptType.DOLPHINS_GRACE, ExemptType.SOUL_SPEED, ExemptType.DEATH, ExemptType.RIPTIDE, ExemptType.SLEEPING, ExemptType.ENDER_PEARL, ExemptType.HIGH_JUMP_BOOST, ExemptType.HIGH_LEVITATION);
            final boolean invalid = Math.abs(deltaY) > 3.9210000038146973;
            if (invalid && !exempt && !bukkitTeleport && !death && !joined) {
                if (this.increaseBuffer() > this.MAX_BUFFER || (deltaY > 4.0 && this.isExempt(ExemptType.SERVER_POSITION))) {
                    this.fail("deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
