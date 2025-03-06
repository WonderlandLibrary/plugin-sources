package me.frep.vulcan.spigot.check.impl.player.groundspoof;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Ground Spoof", type = 'A', complexType = "Spoof", description = "Spoofed onGround value.")
public class GroundSpoofA extends AbstractCheck
{
    public GroundSpoofA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying() && !this.teleporting() && !this.fuckedPosition()) {
            final boolean onGround = this.data.getPositionProcessor().isClientOnGround();
            final boolean touchingAir = this.data.getPositionProcessor().isTouchingAir();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            if (Math.abs(deltaY) - 0.3116 <= 0.005) {
                return;
            }
            final boolean teleport = this.data.getActionProcessor().getSinceTeleportTicks() < 20;
            final int groundTicks = this.data.getPositionProcessor().getClientGroundTicks();
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.STAIRS, ExemptType.CHUNK, ExemptType.SHULKER, ExemptType.STAIRS, ExemptType.RIPTIDE, ExemptType.LIQUID, ExemptType.TELEPORT, ExemptType.VOID, ExemptType.WORLD_CHANGE, ExemptType.SCAFFOLDING, ExemptType.RIPTIDE, ExemptType.SWIMMING, ExemptType.GLIDING, ExemptType.WATERLOGGED, ExemptType.HONEY, ExemptType.SLOW_FALLING, ExemptType.BLOCK_PLACE, ExemptType.SNOW, ExemptType.WEB, ExemptType.FLIGHT, ExemptType.SLIME, ExemptType.PLACED_SLIME, ExemptType.SHULKER_BOX, ExemptType.NEAR_GROUND, ExemptType.CANCELLED_PLACE, ExemptType.LEVITATION);
            final boolean invalid = touchingAir && onGround && deltaY < -0.20000000298023224;
            if (invalid && !exempt && !teleport) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " ticks=" + groundTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
