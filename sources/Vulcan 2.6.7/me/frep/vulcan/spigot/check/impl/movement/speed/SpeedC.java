package me.frep.vulcan.spigot.check.impl.movement.speed;

import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Speed", type = 'C', complexType = "Air", description = "Moving too quickly in the air.")
public class SpeedC extends AbstractCheck
{
    public SpeedC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting() && !this.fuckedPosition()) {
            if (this.data.getActionProcessor().getJumpBoostAmplifier() > 50) {
                return;
            }
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final boolean ice = this.data.getPositionProcessor().getSinceIceTicks() < 40;
            final boolean collidingVertically = this.data.getPositionProcessor().getSinceCollidingVerticallyTicks() < 30;
            final boolean slime = this.data.getPositionProcessor().getSinceNearSlimeTicks() < 30;
            final boolean soulSpeed = this.data.getPositionProcessor().getSinceSoulSpeedTicks() < 30;
            double maxSpeed = PlayerUtil.getBaseSpeed(this.data);
            if (collidingVertically) {
                maxSpeed += 0.1;
            }
            if (this.isExempt(ExemptType.CANCELLED_PLACE)) {
                maxSpeed += 0.1;
            }
            if (this.data.getPositionProcessor().getSinceAroundSlimeTicks() < 15) {
                maxSpeed += 0.6;
            }
            if (airTicks == 2 && this.data.getPositionProcessor().getSinceCollidingVerticallyTicks() < 50 && deltaY == -0.07840000152587834) {
                maxSpeed += 0.165;
            }
            if (ice) {
                maxSpeed += 0.25;
            }
            if (collidingVertically && ice) {
                maxSpeed += 0.3;
            }
            if (this.isExempt(ExemptType.STAIRS)) {
                maxSpeed += 0.05;
            }
            if (slime) {
                maxSpeed += 0.1;
            }
            if (ServerUtil.isHigherThan1_16() && soulSpeed) {
                maxSpeed += 0.3;
            }
            final int sinceVelocityTicks = this.data.getVelocityProcessor().getTransactionFlyingTicks();
            final VelocityProcessor.VelocitySnapshot snapshot = this.data.getVelocityProcessor().getSnapshot();
            double velocityXZ = this.data.getVelocityProcessor().getVelocityXZ();
            if (snapshot != null) {
                final double velocityX = snapshot.getVelocity().getX();
                final double velocityZ = snapshot.getVelocity().getZ();
                velocityXZ = MathUtil.hypot(velocityX, velocityZ);
            }
            switch (sinceVelocityTicks) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    maxSpeed += velocityXZ + 0.05;
                    break;
                }
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15: {
                    maxSpeed += velocityXZ * 0.75;
                    break;
                }
                case 16:
                case 17:
                case 18:
                case 19:
                case 20: {
                    maxSpeed += velocityXZ * 0.6;
                    break;
                }
                case 21:
                case 22:
                case 23:
                case 24:
                case 25: {
                    maxSpeed += velocityXZ * 0.5;
                    break;
                }
                case 26:
                case 27:
                case 28:
                case 29:
                case 30: {
                    maxSpeed += velocityXZ * 0.35;
                    break;
                }
                case 31:
                case 32:
                case 33:
                case 34:
                case 35: {
                    maxSpeed += velocityXZ * 0.25;
                    break;
                }
                case 36:
                case 37:
                case 38:
                case 39:
                case 40: {
                    maxSpeed += velocityXZ * 0.1;
                    break;
                }
            }
            if (ServerUtil.isHigherThan1_13() && this.data.getPositionProcessor().getSinceHoneyTicks() < 20) {
                maxSpeed += 0.125;
            }
            if (this.data.getActionProcessor().getSinceIcePlaceTicks() < 40) {
                maxSpeed += 0.2;
            }
            if (this.isExempt(ExemptType.FARMLAND)) {
                maxSpeed += 0.025;
            }
            if (ServerUtil.isHigherThan1_9() && Config.ENTITY_COLLISION_FIX && this.data.getPositionProcessor().getSinceEntityCollisionTicks() < 20) {
                maxSpeed += 0.1;
            }
            if (this.isExempt(ExemptType.PISTON)) {
                maxSpeed += 0.325;
            }
            if (this.isExempt(ExemptType.BUKKIT_VELOCITY)) {
                maxSpeed += velocityXZ * 2.0;
            }
            if (this.isExempt(ExemptType.SPEED_RAN_OUT)) {
                ++maxSpeed;
            }
            if (this.isExempt(ExemptType.SNOW)) {
                maxSpeed += 0.05;
            }
            if (this.data.getActionProcessor().getSinceFireballDamageTicks() < 30) {
                maxSpeed += 0.75;
            }
            if (this.data.getActionProcessor().getSinceRavagerDamageTicks() < 30) {
                maxSpeed += 2.0;
            }
            if (this.data.getActionProcessor().getSinceCrystalDamageTicks() < 5) {
                ++maxSpeed;
            }
            else if (this.data.getActionProcessor().getSinceCrystalDamageTicks() > 5 && this.data.getActionProcessor().getSinceCrystalDamageTicks() < 25) {
                maxSpeed += 0.5;
            }
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.ILLEGAL_BLOCK, ExemptType.RIPTIDE, ExemptType.SHULKER, ExemptType.CHORUS_FRUIT, ExemptType.GLIDING, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.VEHICLE, ExemptType.SHULKER_BOX, ExemptType.CHUNK, ExemptType.FISHING_ROD, ExemptType.DOLPHINS_GRACE, ExemptType.ENDER_PEARL, ExemptType.TELEPORT, ExemptType.WORLD_CHANGE, ExemptType.ELYTRA, ExemptType.FROZEN, ExemptType.DEATH, ExemptType.SLEEPING, ExemptType.BOAT, ExemptType.SPECTATOR, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.ANVIL, ExemptType.FULLY_STUCK, ExemptType.PARTIALLY_STUCK, ExemptType.CANCELLED_MOVE, ExemptType.ENTITY_CRAM_FIX);
            final double difference = deltaXZ - maxSpeed;
            final boolean invalid = airTicks > 1 && difference > Config.SPEED_C_MIN_DIFFERENCE;
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER || (difference > 0.5 && difference < 100.0 && !this.isExempt(ExemptType.SERVER_POSITION_FAST))) {
                    this.fail("speed=" + deltaXZ + " max=" + maxSpeed + " diff=" + difference + " ticks=" + airTicks + " deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
