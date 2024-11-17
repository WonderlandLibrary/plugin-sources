package me.frep.vulcan.spigot.check.impl.movement.speed;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Speed", type = 'A', complexType = "Friction", description = "Invalid friction.")
public class SpeedA extends AbstractCheck
{
    public SpeedA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (this.data.getActionProcessor().getSinceCrystalDamageTicks() < 3) {
                return;
            }
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double lastDeltaXZ = this.data.getPositionProcessor().getLastDeltaXZ();
            final boolean sprinting = true;
            final double prediction = lastDeltaXZ * 0.9100000262260437 + 0.026;
            final double difference = deltaXZ - prediction;
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final boolean piston = this.data.getPositionProcessor().getSinceAroundPistonTicks() < 15;
            final boolean velocity = this.data.getVelocityProcessor().getTransactionFlyingTicks() < 3;
            final boolean exempt = this.isExempt(ExemptType.ILLEGAL_BLOCK, ExemptType.FULLY_STUCK, ExemptType.DOLPHINS_GRACE, ExemptType.WEB, ExemptType.SLEEPING, ExemptType.DEPTH_STRIDER, ExemptType.VOID, ExemptType.WORLD_CHANGE, ExemptType.CHUNK, ExemptType.JOINED, ExemptType.ELYTRA, ExemptType.ENTITY_COLLISION, ExemptType.ANVIL, ExemptType.TRAPDOOR, ExemptType.SPECTATOR, ExemptType.PARTIALLY_STUCK, ExemptType.FENCE_GATE, ExemptType.FROZEN, ExemptType.CHORUS_FRUIT, ExemptType.BLOCK_BREAK, ExemptType.CANCELLED_MOVE) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptVehicle();
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 10;
            if (airTicks > 2 && !velocity && !exempt && !piston && deltaXZ > 0.25 && !explosion) {
                if (difference > 1.0E-5) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("difference=" + difference + " ticks=" + airTicks + " deltaXZ=" + deltaXZ);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
