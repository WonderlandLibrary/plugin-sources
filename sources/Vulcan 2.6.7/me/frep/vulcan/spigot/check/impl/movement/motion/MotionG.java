package me.frep.vulcan.spigot.check.impl.movement.motion;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Motion", type = 'G', complexType = "Additional velocity", description = "Took additional velocity.")
public class MotionG extends AbstractCheck
{
    public MotionG(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (this.data.getPlayer().hasMetadata("ThrownByBoss")) {
                return;
            }
            if (this.data.getActionProcessor().getTicksExisted() < 30) {
                return;
            }
            final int sinceFireDamageTicks = this.data.getActionProcessor().getSinceFireDamageTicks();
            final int sinceAttackDamageTicks = this.data.getActionProcessor().getSinceAttackDamageTicks();
            final int sinceLavaDamageTicks = this.data.getActionProcessor().getSinceLavaDamageTicks();
            final int sinceFallDamageTicks = this.data.getActionProcessor().getSinceFallDamageTicks();
            final int sinceMagicDamageTicks = this.data.getActionProcessor().getSinceMagicDamageTicks();
            final int sinceProjectileDamageTicks = this.data.getActionProcessor().getSinceProjectileDamageTicks();
            final int sinceContactDamageTicks = this.data.getActionProcessor().getSinceContactDamageTicks();
            final int sinceIronGolemDamageTicks = this.data.getActionProcessor().getSinceIronGolemDamageTicks();
            final int sinceHoglinDamageTicks = this.data.getActionProcessor().getSinceHoglinDamageTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.RIPTIDE, ExemptType.VEHICLE, ExemptType.EXPLOSION, ExemptType.HONEY, ExemptType.GLIDING, ExemptType.ELYTRA, ExemptType.LEVITATION, ExemptType.SLAB, ExemptType.STAIRS, ExemptType.MYTHIC_MOB, ExemptType.RESPAWN, ExemptType.JUMP_BOOST_RAN_OUT, ExemptType.BOAT, ExemptType.BUBBLE_COLUMN, ExemptType.WALL, ExemptType.CREATIVE, ExemptType.SPECTATOR, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.ANVIL, ExemptType.SLIME, ExemptType.PISTON, ExemptType.TURTLE_EGG, ExemptType.DOOR, ExemptType.DEATH, ExemptType.FENCE_GATE, ExemptType.ENDER_PEARL, ExemptType.PLACED_SLIME, ExemptType.BUKKIT_VELOCITY, ExemptType.DRAGON_DAMAGE, ExemptType.BLOCK_BREAK, ExemptType.FISHING_ROD, ExemptType.SLEEPING, ExemptType.SERVER_POSITION, ExemptType.BED, ExemptType.FLIGHT);
            final boolean fireball = this.isExempt(ExemptType.FIREBALL);
            double max = 0.42f + this.data.getActionProcessor().getJumpBoostAmplifier() * 1.25f;
            if (fireball) {
                max += 0.4000000059604645;
            }
            final double diff = deltaY - max;
            if (sinceFireDamageTicks < 20) {
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Fire] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceFireDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceAttackDamageTicks < 20) {
                max += 0.3499999940395355;
                if (sinceIronGolemDamageTicks < 20) {
                    max += 0.20000000298023224;
                }
                if (sinceHoglinDamageTicks < 20) {
                    max += 0.25;
                }
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Attack] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceAttackDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceLavaDamageTicks < 20) {
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Lava] deltaY=" + deltaY + " max=" + max + " ticks" + sinceLavaDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceFallDamageTicks < 20) {
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Fall Damage] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceFallDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceMagicDamageTicks < 20) {
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Magic] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceMagicDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceProjectileDamageTicks < 20) {
                final boolean wither = this.data.getActionProcessor().getSinceWitherDamageTicks() < 100;
                max += 0.02;
                if (deltaY > max && !exempt && !wither) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Projectile] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceProjectileDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            else if (sinceContactDamageTicks < 20) {
                if (deltaY > max && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || diff > 5.0) {
                        this.fail("[Magic] deltaY=" + deltaY + " max=" + max + " ticks=" + sinceContactDamageTicks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
