package me.frep.vulcan.spigot.check.impl.movement.flight;

import java.util.Iterator;
import me.frep.vulcan.spigot.util.value.Values;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Flight", type = 'B', complexType = "Prediction [C]", description = "Invalid Y-Axis movement.")
public class FlightB extends AbstractCheck
{
    public FlightB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (this.data.getActionProcessor().getJumpBoostAmplifier() > 50) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double lastDeltaY = this.data.getPositionProcessor().getLastDeltaY();
            final boolean ladder = Math.abs(deltaY - 0.11760000228882461) <= 0.001;
            if (ladder) {
                return;
            }
            final double prediction = (lastDeltaY - 0.08) * 0.9800000190734863;
            final double difference = Math.abs(deltaY - prediction);
            if (deltaY + 0.09800000190734881 <= 0.005) {
                return;
            }
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 40;
            final boolean exempt = this.isExempt(ExemptType.BOAT, ExemptType.PARTIALLY_STUCK, ExemptType.DEAD, ExemptType.WEB, ExemptType.PLACING, ExemptType.WATERLOGGED, ExemptType.SLIME, ExemptType.WORLD_CHANGE, ExemptType.HONEY, ExemptType.FISHING_ROD, ExemptType.EMPTIED_BUCKET, ExemptType.SWEET_BERRIES, ExemptType.BUBBLE_COLUMN, ExemptType.SCAFFOLDING, ExemptType.NOT_MOVING, ExemptType.SEAGRASS, ExemptType.SIGN, ExemptType.ANVIL, ExemptType.END_ROD, ExemptType.SPECTATOR, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.FENCE, ExemptType.KELP, ExemptType.BLOCK_BREAK, ExemptType.FENCE_GATE, ExemptType.JOINED_CHUNK_LOAD, ExemptType.DOLPHINS_GRACE, ExemptType.PLACED_WEB, ExemptType.SEAGRASS, ExemptType.WALL, ExemptType.SEA_PICKLE, ExemptType.CHEST, ExemptType.DRIPSTONE, ExemptType.POWDER_SNOW, ExemptType.FULLY_STUCK, ExemptType.BED, ExemptType.SLEEPING, ExemptType.CANCELLED_MOVE, ExemptType.SIGN) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptSlowFalling() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptLenientScaffolding() || this.data.getPositionProcessor().isExemptBukkitVelocity() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptChunk() || this.data.getPositionProcessor().isExemptComboMode() || this.data.getPositionProcessor().isExemptMythicMob() || this.data.getPositionProcessor().isExemptClimbable();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final boolean nearGround = this.data.getPositionProcessor().isNearGround();
            if (Config.GHOST_WATER_FIX && deltaXZ < 0.15 && nearGround) {
                for (final double value : Values.WATER_VALUES) {
                    if (Math.abs(deltaY) > 0.0 && Math.abs(deltaY - value) < 0.01) {
                        return;
                    }
                }
            }
            final boolean velocity = this.data.getVelocityProcessor().getTransactionFlyingTicks() < 20 && airTicks < 20;
            int maxTicks = 7;
            if (this.data.getActionProcessor().isHasJumpBoost()) {
                maxTicks += this.data.getActionProcessor().getJumpBoostAmplifier();
            }
            if (this.data.getActionProcessor().getSinceAttackDamageTicks() < 15) {
                maxTicks += 7;
            }
            final boolean invalid = airTicks > maxTicks && difference > 1.0E-8;
            final int teleportTicks = this.data.getActionProcessor().getSinceTeleportTicks();
            final long delay = this.data.getConnectionProcessor().getFlyingDelay();
            if (invalid && !exempt && !velocity && !explosion) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " difference=" + difference + " ticks=" + airTicks + " delay=" + delay + " tp=" + teleportTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
