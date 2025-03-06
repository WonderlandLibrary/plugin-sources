package me.frep.vulcan.spigot.check.impl.movement.jump;

import java.util.Iterator;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.value.Values;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Jump", type = 'A', complexType = "Motion", description = "Invalid jump motion.")
public class JumpA extends AbstractCheck
{
    public JumpA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (this.data.getActionProcessor().isCrawling() || this.data.getPositionProcessor().getSinceNearBedTicks() < 30) {
                return;
            }
            final int airTicks = this.data.getPositionProcessor().getClientAirTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            if ((airTicks == 6 && deltaY < 0.033) || (airTicks == 7 && deltaY < 0.033)) {
                return;
            }
            if (airTicks < 9 && airTicks > 0) {
                final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
                final boolean nearGround = this.data.getPositionProcessor().isNearGround();
                if (Config.GHOST_WATER_FIX && deltaXZ < 0.15 && nearGround) {
                    for (final double value : Values.WATER_VALUES) {
                        if (Math.abs(deltaY) > 0.0 && Math.abs(deltaY - value) < 0.01) {
                            return;
                        }
                    }
                }
                final double expected = Values.JUMP_MOTION.get(airTicks) + this.data.getActionProcessor().getJumpBoostAmplifier() * 0.1f;
                final double difference = Math.abs(deltaY - expected);
                if (System.currentTimeMillis() - this.data.getJoinTime() < 30000L) {
                    final double distance = MathUtil.magnitude(this.data.getPositionProcessor().getX() - this.data.getPositionProcessor().getFirstJoinX(), this.data.getPositionProcessor().getY() - this.data.getPositionProcessor().getFirstJoinY(), this.data.getPositionProcessor().getZ() - this.data.getPositionProcessor().getFirstJoinZ());
                    if (distance < 4.0) {
                        return;
                    }
                }
                final boolean explosion = this.data.getActionProcessor().getSinceExplosionDamageTicks() < 80;
                final boolean damage = this.data.getActionProcessor().getSinceDamageTicks() < 20;
                final boolean exempt = this.isExempt(ExemptType.VELOCITY, ExemptType.COLLIDING_VERTICALLY, ExemptType.HOPPER, ExemptType.SLIME, ExemptType.SCAFFOLDING, ExemptType.BOAT, ExemptType.TRAPDOOR, ExemptType.STAIRS, ExemptType.SHULKER, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.SLAB, ExemptType.ILLEGAL_BLOCK, ExemptType.FENCE, ExemptType.JUMP_BOOST, ExemptType.HONEY, ExemptType.FISHING_ROD, ExemptType.WEB, ExemptType.FULLY_STUCK, ExemptType.SHULKER_BOX, ExemptType.VEHICLE, ExemptType.BED, ExemptType.BLOCK_PLACE, ExemptType.SEA_PICKLE, ExemptType.SLEEPING, ExemptType.CANCELLED_BREAK, ExemptType.ANVIL, ExemptType.PISTON, ExemptType.TURTLE_EGG, ExemptType.DOOR, ExemptType.DEATH, ExemptType.PARTIALLY_STUCK, ExemptType.WALL, ExemptType.PLACED_WEB, ExemptType.BUKKIT_VELOCITY, ExemptType.BUBBLE_COLUMN, ExemptType.FROZEN, ExemptType.KELP, ExemptType.BLOCK_BREAK, ExemptType.SWEET_BERRIES, ExemptType.EMPTIED_BUCKET, ExemptType.SERVER_POSITION_FAST, ExemptType.FARMLAND, ExemptType.LENIENT_SCAFFOLDING, ExemptType.ATTACK_DAMAGE, ExemptType.SEAGRASS, ExemptType.DRIPSTONE, ExemptType.WATERLOGGED, ExemptType.CHEST, ExemptType.SOUL_SAND, ExemptType.GLITCHED_BLOCKS_ABOVE, ExemptType.POWDER_SNOW, ExemptType.SIGN, ExemptType.SEA_PICKLE) || this.data.getPositionProcessor().isExemptJoined() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptClimbable() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptEnderPearl() || this.data.getPositionProcessor().isExemptLevitation();
                final boolean invalid = deltaY > 0.0 && difference > 1.0E-10;
                if (invalid && !exempt && !explosion && !damage) {
                    if (this.increaseBuffer() > this.MAX_BUFFER || (difference > 0.5 && difference < 10.0)) {
                        this.fail("deltaY=" + deltaY + " ticks=" + airTicks + " difference=" + difference);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
