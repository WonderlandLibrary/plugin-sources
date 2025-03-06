package me.frep.vulcan.spigot.check.impl.movement.strafe;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Strafe", type = 'A', complexType = "Air", description = "Moving incorrectly in the air.")
public class StrafeA extends AbstractCheck
{
    private boolean wasSprinting;
    
    public StrafeA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            final boolean sprinting = this.data.getActionProcessor().isSprinting();
            final int sinceSprintingTicks = this.data.getActionProcessor().getSinceSprintingTicks();
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final double lastDeltaX = this.data.getPositionProcessor().getLastDeltaX();
            final double lastDeltaZ = this.data.getPositionProcessor().getLastDeltaZ();
            final double deltaX = this.data.getPositionProcessor().getDeltaX();
            final double deltaZ = this.data.getPositionProcessor().getDeltaZ();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double predictedX = lastDeltaX * 0.9100000262260437;
            final double predictedZ = lastDeltaZ * 0.9100000262260437;
            final double differenceX = deltaX - predictedX;
            final double differenceZ = deltaZ - predictedZ;
            double difference = MathUtil.hypot(differenceX, differenceZ);
            difference /= (this.wasSprinting ? 1.3 : 1.0);
            difference -= ((sprinting || sinceSprintingTicks < 2) ? 0.026 : 0.02);
            final boolean velocity = this.data.getVelocityProcessor().getTransactionFlyingTicks() < 3;
            final boolean piston = this.data.getPositionProcessor().getSinceNearPistonTicks() < 30;
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.PARTIALLY_STUCK, ExemptType.BUKKIT_VELOCITY, ExemptType.DEATH, ExemptType.JOINED, ExemptType.WORLD_CHANGE, ExemptType.FLIGHT, ExemptType.FULLY_STUCK, ExemptType.GLIDING, ExemptType.ELYTRA, ExemptType.CREATIVE, ExemptType.SPECTATOR, ExemptType.SOUL_SAND, ExemptType.VEHICLE, ExemptType.ENTITY_COLLISION, ExemptType.DEPTH_STRIDER, ExemptType.FROZEN, ExemptType.LIQUID, ExemptType.DOLPHINS_GRACE, ExemptType.RIPTIDE, ExemptType.SWIMMING, ExemptType.CLIMBABLE, ExemptType.ENDER_PEARL, ExemptType.FIREBALL, ExemptType.CHORUS_FRUIT, ExemptType.BLOCK_BREAK, ExemptType.CANCELLED_MOVE);
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 10;
            final boolean bowBoost = this.data.getActionProcessor().getSinceBowBoostTicks() < 30;
            final boolean invalid = difference > 0.0075 && deltaXZ > 0.25 && airTicks > 3;
            if (invalid && !exempt && !piston && !velocity && !explosion && !bowBoost) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("difference=" + difference + " deltaXZ=" + deltaXZ);
                }
            }
            else {
                this.decayBuffer();
            }
            this.wasSprinting = sprinting;
        }
    }
}
