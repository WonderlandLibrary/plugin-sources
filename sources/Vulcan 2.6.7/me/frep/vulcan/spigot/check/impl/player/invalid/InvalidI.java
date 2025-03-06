package me.frep.vulcan.spigot.check.impl.player.invalid;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Invalid", type = 'I', complexType = "Invalid Y movement", description = "Impossible Y change.")
public class InvalidI extends AbstractCheck
{
    public InvalidI(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (PlayerUtil.isHigherThan1_9(this.data.getPlayer())) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final boolean damage = this.data.getActionProcessor().getSinceNonFallDamageTicks() < 20;
            final boolean bed = this.data.getPositionProcessor().getSinceNearBedTicks() < 40;
            final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 100;
            final boolean golem = this.data.getActionProcessor().getSinceIronGolemDamageTicks() < 30;
            final boolean dragon = this.data.getActionProcessor().getSinceDragonDamageTicks() < 200;
            final boolean fireball = this.isExempt(ExemptType.FIREBALL);
            final boolean exploit = this.isExempt(ExemptType.VEHICLE);
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.RIPTIDE, ExemptType.HONEY, ExemptType.GLIDING, ExemptType.LEVITATION, ExemptType.SLAB, ExemptType.STAIRS, ExemptType.FLIGHT, ExemptType.JUMP_BOOST_RAN_OUT, ExemptType.ELYTRA, ExemptType.BUBBLE_COLUMN, ExemptType.WALL, ExemptType.CREATIVE, ExemptType.SHULKER_BOX, ExemptType.STAIRS, ExemptType.ANVIL, ExemptType.SLIME, ExemptType.PISTON, ExemptType.TURTLE_EGG, ExemptType.DOOR, ExemptType.DEATH, ExemptType.ENDER_PEARL, ExemptType.PLACED_SLIME, ExemptType.BUKKIT_VELOCITY, ExemptType.CHORUS_FRUIT, ExemptType.MYTHIC_MOB, ExemptType.BLOCK_BREAK, ExemptType.FISHING_ROD, ExemptType.SLEEPING, ExemptType.SHULKER);
            double limit = 1.25;
            if (golem) {
                limit += 0.4000000059604645;
            }
            if (fireball) {
                limit += 0.5;
            }
            if (this.isExempt(ExemptType.BOAT)) {
                limit += 0.125;
            }
            if (this.isExempt(ExemptType.SKULL)) {
                limit += 0.1;
            }
            if (this.data.getPositionProcessor().isNearPowderSnow()) {
                limit += 0.3;
            }
            if (this.data.getActionProcessor().getSinceHoglinDamageTicks() < 20) {
                limit += 0.15000000596046448;
            }
            if (this.data.getActionProcessor().isHasJumpBoost()) {
                limit += this.data.getActionProcessor().getJumpBoostAmplifier() * 0.125f;
            }
            if (this.isExempt(ExemptType.FENCE)) {
                limit += 0.25;
            }
            if (airTicks > 0 && deltaY > limit && !damage && !exempt && !bed && !explosion && !dragon && exploit) {
                if (this.increaseBuffer() > this.MAX_BUFFER || deltaY > 2.5) {
                    this.fail("deltaY=" + deltaY + " limit=" + limit);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
