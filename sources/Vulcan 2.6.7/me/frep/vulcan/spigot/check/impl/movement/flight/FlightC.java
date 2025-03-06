package me.frep.vulcan.spigot.check.impl.movement.flight;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Flight", type = 'C', complexType = "Ascension", description = "Invalid Y-Axis movement.")
public class FlightC extends AbstractCheck
{
    public FlightC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (Config.FLIGHT_C_IGNORE_GHOST_BLOCKS && this.data.getPositionProcessor().isClientOnGround() && this.data.getPositionProcessor().isTouchingAir() && this.data.getPositionProcessor().isMathematicallyOnGround()) {
                return;
            }
            if (this.data.getActionProcessor().getSinceDragonDamageTicks() < 200) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getServerAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            int maxTicks = 7;
            if (this.isExempt(ExemptType.BLOCK_PLACE, ExemptType.CANCELLED_PLACE)) {
                maxTicks += 15;
            }
            if (Math.abs(deltaY) - 0.08307781780646728 < 0.001) {
                return;
            }
            final boolean invalid = airTicks > maxTicks && deltaY > 0.0;
            final boolean bed = this.data.getPositionProcessor().getSinceNearBedTicks() < 30;
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 100;
            final boolean slime = this.data.getPositionProcessor().getSinceNearSlimeTicks() < 80 || this.data.getActionProcessor().getSinceSlimePlaceTicks() < 80;
            final boolean exempt = this.isExempt(ExemptType.SHULKER_BOX, ExemptType.BOAT, ExemptType.WORLD_CHANGE, ExemptType.JUMP_BOOST, ExemptType.FENCE, ExemptType.LAGGED_NEAR_GROUND, ExemptType.ILLEGAL_BLOCK, ExemptType.SHULKER, ExemptType.FROZEN, ExemptType.FISHING_ROD, ExemptType.CANCELLED_PLACE, ExemptType.NOT_MOVING, ExemptType.SPECTATOR, ExemptType.CHORUS_FRUIT, ExemptType.PISTON, ExemptType.NEAR_GROUND, ExemptType.LAGGED_NEAR_GROUND_MODERN, ExemptType.EMPTIED_BUCKET, ExemptType.BOAT, ExemptType.PLACED_SLIME, ExemptType.DEAD) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptSlowFalling() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptLenientScaffolding() || this.data.getPositionProcessor().isExemptBukkitVelocity() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptChunk() || this.data.getPositionProcessor().isExemptComboMode() || this.data.getPositionProcessor().isExemptMythicMob() || this.data.getPositionProcessor().isExemptClimbable();
            final long delay = this.data.getConnectionProcessor().getFlyingDelay();
            final boolean velocity = this.data.getActionProcessor().getSinceNonFallDamageTicks() < 40 && airTicks < 30;
            final int sinceTeleportTicks = this.data.getActionProcessor().getSinceTeleportTicks();
            if (invalid && !exempt && !slime && !velocity && !bed && !explosion) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " ticks=" + airTicks + " delay=" + delay + " tp=" + sinceTeleportTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
