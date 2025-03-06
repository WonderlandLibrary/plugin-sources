package me.frep.vulcan.spigot.check.impl.movement.flight;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Flight", type = 'D', complexType = "Glide", description = "Invalid Y-Axis movement.")
public class FlightD extends AbstractCheck
{
    public FlightD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (Config.FLIGHT_D_IGNORE_GHOST_BLOCKS && this.data.getPositionProcessor().isClientOnGround() && this.data.getPositionProcessor().isTouchingAir() && this.data.getPositionProcessor().isMathematicallyOnGround()) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getServerAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            if (this.data.getActionProcessor().getSinceDragonDamageTicks() < 200) {
                return;
            }
            if (deltaY + 0.09800000190734881 <= 0.001) {
                return;
            }
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 100;
            final boolean velocity = this.data.getActionProcessor().getSinceNonFallDamageTicks() < 40 && airTicks < 30;
            final boolean bed = this.data.getPositionProcessor().getSinceNearBedTicks() < 30;
            final boolean exempt = this.isExempt(ExemptType.LAGGED_NEAR_GROUND, ExemptType.DEAD, ExemptType.WEB, ExemptType.JUMP_BOOST_RAN_OUT, ExemptType.EMPTIED_BUCKET, ExemptType.WORLD_CHANGE, ExemptType.ILLEGAL_BLOCK, ExemptType.BOAT, ExemptType.LIQUID, ExemptType.NOT_MOVING, ExemptType.FENCE, ExemptType.SHULKER, ExemptType.TRAPDOOR, ExemptType.SHULKER_BOX, ExemptType.NEAR_GROUND, ExemptType.FISHING_ROD, ExemptType.LAGGED_NEAR_GROUND_MODERN, ExemptType.VOID, ExemptType.CANCELLED_PLACE, ExemptType.SPECTATOR, ExemptType.SERVER_POSITION, ExemptType.PISTON, ExemptType.HIGH_JUMP_BOOST, ExemptType.NEAR_GROUND, ExemptType.SLIME, ExemptType.PARTIALLY_STUCK, ExemptType.FULLY_STUCK, ExemptType.RESPAWN, ExemptType.PLACED_SLIME) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptSlowFalling() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptLenientScaffolding() || this.data.getPositionProcessor().isExemptBukkitVelocity() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptChunk() || this.data.getPositionProcessor().isExemptComboMode() || this.data.getPositionProcessor().isExemptMythicMob() || this.data.getPositionProcessor().isExemptClimbable();
            int maxTicks = 18;
            if (this.isExempt(ExemptType.BLOCK_PLACE, ExemptType.CANCELLED_PLACE)) {
                maxTicks += 30;
            }
            final long delay = this.data.getConnectionProcessor().getFlyingDelay();
            final boolean invalid = airTicks > maxTicks && deltaY > -0.5;
            final int sinceTeleportTicks = this.data.getActionProcessor().getSinceTeleportTicks();
            if (invalid && !exempt && !velocity && !explosion && !bed) {
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
