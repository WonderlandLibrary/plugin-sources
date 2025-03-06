package me.frep.vulcan.spigot.check.impl.player.groundspoof;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Ground Spoof", type = 'C', complexType = "Spoof", description = "Spoofed OnGround value.")
public class GroundSpoofC extends AbstractCheck
{
    public GroundSpoofC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.fuckedPosition()) {
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean teleport = this.data.getActionProcessor().getSinceTeleportTicks() < 40;
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.ANVIL, ExemptType.LIQUID, ExemptType.WATERLOGGED, ExemptType.RIPTIDE, ExemptType.ELYTRA, ExemptType.GLIDING, ExemptType.JOINED, ExemptType.CLIMBABLE, ExemptType.LILY_PAD, ExemptType.SNOW, ExemptType.WORLD_CHANGE, ExemptType.VELOCITY, ExemptType.WEB, ExemptType.NEAR_GROUND, ExemptType.SLOW_FALLING, ExemptType.LEVITATION, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.HONEY, ExemptType.SLIME, ExemptType.FLIGHT, ExemptType.BUKKIT_VELOCITY, ExemptType.VOID, ExemptType.CANCELLED_PLACE, ExemptType.PLACED_SLIME, ExemptType.BLOCK_PLACE);
            final boolean invalid = deltaY < -0.5 && airTicks < 5;
            if (invalid && !exempt && !teleport) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " airTicks=" + airTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
