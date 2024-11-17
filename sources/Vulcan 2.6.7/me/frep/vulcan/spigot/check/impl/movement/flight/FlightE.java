package me.frep.vulcan.spigot.check.impl.movement.flight;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Flight", type = 'E', complexType = "Hover", description = "Invalid Y-Axis movement.")
public class FlightE extends AbstractCheck
{
    public FlightE(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (Config.FLIGHT_E_IGNORE_GHOST_BLOCKS && this.data.getPositionProcessor().isClientOnGround() && this.data.getPositionProcessor().isTouchingAir() && this.data.getPositionProcessor().isMathematicallyOnGround()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double lastDeltaY = this.data.getPositionProcessor().getLastDeltaY();
            final double acceleration = Math.abs(deltaY - lastDeltaY);
            final boolean touchingAir = this.data.getPositionProcessor().isTouchingAir();
            final int airTicks = this.data.getPositionProcessor().getServerAirTicks();
            final long delay = this.data.getConnectionProcessor().getFlyingDelay();
            final boolean invalid = airTicks > 3 && Math.abs(deltaY) < 0.5 && acceleration < 0.05 && touchingAir;
            final boolean exempt = this.isExempt(ExemptType.BED, ExemptType.EMPTIED_BUCKET, ExemptType.WEB, ExemptType.LAGGED_NEAR_GROUND, ExemptType.SLEEPING, ExemptType.JOINED_CHUNK_LOAD, ExemptType.ILLEGAL_BLOCK, ExemptType.BOAT, ExemptType.SHULKER, ExemptType.BLOCK_PLACE, ExemptType.LAGGED_NEAR_GROUND_MODERN, ExemptType.TRAPDOOR, ExemptType.WORLD_CHANGE, ExemptType.NOT_MOVING, ExemptType.LILY_PAD, ExemptType.SPECTATOR, ExemptType.SHULKER_BOX, ExemptType.HONEY, ExemptType.CANCELLED_PLACE, ExemptType.FENCE, ExemptType.SWEET_BERRIES, ExemptType.DEAD) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptSlowFalling() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptLenientScaffolding() || this.data.getPositionProcessor().isExemptBukkitVelocity() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptChunk() || this.data.getPositionProcessor().isExemptComboMode() || this.data.getPositionProcessor().isExemptMythicMob() || this.data.getPositionProcessor().isExemptClimbable();
            final int sinceTeleportTicks = this.data.getActionProcessor().getSinceTeleportTicks();
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("ticks=" + airTicks + " acceleration=" + acceleration + " deltaY=" + deltaY + " delay=" + delay + " tp=" + sinceTeleportTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
