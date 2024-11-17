package me.frep.vulcan.spigot.check.impl.movement.motion;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Motion", type = 'H', complexType = "Velocity", description = "Took additional explosion.", experimental = true)
public class MotionH extends AbstractCheck
{
    public MotionH(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (this.data.getPlayer().hasMetadata("ThrownByBoss") || this.data.getActionProcessor().getSinceExplosionTicks() > 50) {
                return;
            }
            if (this.data.getActionProcessor().getExplosionY() < 0.01 || this.data.getActionProcessor().getJumpBoostAmplifier() > 10) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean exempt = this.isExempt(ExemptType.JOINED, ExemptType.TELEPORT, ExemptType.RIPTIDE, ExemptType.VEHICLE, ExemptType.HONEY, ExemptType.GLIDING, ExemptType.ELYTRA, ExemptType.LEVITATION, ExemptType.SLAB, ExemptType.STAIRS, ExemptType.MYTHIC_MOB, ExemptType.RESPAWN, ExemptType.JUMP_BOOST_RAN_OUT, ExemptType.BOAT, ExemptType.BUBBLE_COLUMN, ExemptType.WALL, ExemptType.CREATIVE, ExemptType.SPECTATOR, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.ANVIL, ExemptType.SLIME, ExemptType.PISTON, ExemptType.TURTLE_EGG, ExemptType.DOOR, ExemptType.DEATH, ExemptType.FENCE_GATE, ExemptType.ENDER_PEARL, ExemptType.PLACED_SLIME, ExemptType.BUKKIT_VELOCITY, ExemptType.DRAGON_DAMAGE, ExemptType.BLOCK_BREAK, ExemptType.FISHING_ROD, ExemptType.SLEEPING, ExemptType.SERVER_POSITION, ExemptType.BED, ExemptType.FLIGHT);
            final boolean fireball = this.isExempt(ExemptType.FIREBALL);
            double max = Math.abs(this.data.getActionProcessor().getExplosionY()) + this.data.getActionProcessor().getJumpBoostAmplifier() * 1.25f + 0.2;
            if (fireball) {
                max += 0.44999998807907104;
            }
            final double diff = deltaY - max;
            if (diff > 0.1 && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " velocity=" + this.data.getActionProcessor().getExplosionY());
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
