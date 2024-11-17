package me.frep.vulcan.spigot.check.impl.movement.flight;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Flight", type = 'A', complexType = "Prediction [S]", description = "Invalid Y-Axis movement.")
public class FlightA extends AbstractCheck
{
    public FlightA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (Config.FLIGHT_A_IGNORE_GHOST_BLOCKS && this.data.getPositionProcessor().isClientOnGround() && this.data.getPositionProcessor().isTouchingAir() && this.data.getPositionProcessor().isMathematicallyOnGround()) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getServerAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean ladder = Math.abs(deltaY - 0.11760000228882461) <= 0.001;
            if (ladder && this.isExempt(ExemptType.FAST)) {
                return;
            }
            if (deltaY + 0.09800000190734881 <= 0.001) {
                return;
            }
            final double lastDeltaY = this.data.getPositionProcessor().getLastDeltaY();
            final double prediction = (lastDeltaY - 0.08) * 0.9800000190734863;
            final double difference = Math.abs(deltaY - prediction);
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 40;
            if (airTicks < 30 && this.isExempt(ExemptType.PISTON)) {
                return;
            }
            final long delay = this.data.getConnectionProcessor().getFlyingDelay();
            int maxTicks = 6;
            if (this.data.getActionProcessor().isHasJumpBoost()) {
                maxTicks += this.data.getActionProcessor().getJumpBoostAmplifier();
            }
            final boolean invalid = airTicks > maxTicks && difference > 1.0E-8 && airTicks > 6;
            final boolean exempt = this.isExempt(ExemptType.SHULKER_BOX, ExemptType.WEB, ExemptType.BOAT, ExemptType.ILLEGAL_BLOCK, ExemptType.SLEEPING, ExemptType.SHULKER, ExemptType.TRAPDOOR, ExemptType.FISHING_ROD, ExemptType.SERVER_POSITION_FAST, ExemptType.LILY_PAD, ExemptType.SPECTATOR, ExemptType.HONEY, ExemptType.CHORUS_FRUIT, ExemptType.BED, ExemptType.CHAIN, ExemptType.WORLD_CHANGE, ExemptType.CANCELLED_PLACE, ExemptType.LAGGED_NEAR_GROUND_MODERN, ExemptType.EMPTIED_BUCKET, ExemptType.DEAD, ExemptType.CANCELLED_BREAK, ExemptType.FENCE, ExemptType.LAGGED_NEAR_GROUND, ExemptType.BLOCK_PLACE_FAST, ExemptType.GLASS_PANE, ExemptType.NOT_MOVING) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptSlowFalling() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptLenientScaffolding() || this.data.getPositionProcessor().isExemptBukkitVelocity() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptChunk() || this.data.getPositionProcessor().isExemptComboMode() || this.data.getPositionProcessor().isExemptMythicMob() || this.data.getPositionProcessor().isExemptClimbable();
            final int sinceTeleportTicks = this.data.getActionProcessor().getSinceTeleportTicks();
            if (invalid && !exempt && !explosion) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " difference=" + difference + " ticks=" + airTicks + " delay=" + delay + " tp=" + sinceTeleportTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
