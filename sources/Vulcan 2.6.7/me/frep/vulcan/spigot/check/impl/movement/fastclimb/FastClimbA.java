package me.frep.vulcan.spigot.check.impl.movement.fastclimb;

import org.bukkit.Material;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Fast Climb", type = 'A', complexType = "Limit", description = "Climbing too quickly")
public class FastClimbA extends AbstractCheck
{
    public FastClimbA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final int climbableTicks = this.data.getPositionProcessor().getClimbableTicks();
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean onClimbable = this.data.getPositionProcessor().isOnClimbable();
            if (this.data.getActionProcessor().getDistanceFromLastBreak() < 1.25 && this.data.getActionProcessor().getDistanceFromLastBreak() > 0.0) {
                return;
            }
            boolean valid = false;
            if (ServerUtil.isHigherThan1_13()) {
                if (this.data.getPositionProcessor().getBlockBelowModern() != null && this.data.getPositionProcessor().getBlockBelow2Modern() != null) {
                    final Material below = this.data.getPositionProcessor().getBlockBelowModern();
                    final Material below2 = this.data.getPositionProcessor().getBlockBelow2Modern();
                    if (BlockUtil.isClimbable(below) && BlockUtil.isClimbable(below2)) {
                        valid = true;
                    }
                }
            }
            else if (this.data.getPositionProcessor().getBlockBelow() != null && this.data.getPositionProcessor().getBlockBelow2() != null) {
                final Material below = this.data.getPositionProcessor().getBlockBelow();
                final Material below2 = this.data.getPositionProcessor().getBlockBelow2();
                if (BlockUtil.isClimbable(below) && BlockUtil.isClimbable(below2)) {
                    valid = true;
                }
            }
            if (!valid) {
                return;
            }
            final boolean invalid = deltaY > 0.118 && climbableTicks > 3 && onClimbable;
            final boolean exempt = this.isExempt(ExemptType.JUMP_BOOST, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.GLIDING, ExemptType.LEVITATION, ExemptType.SLEEPING, ExemptType.ENDER_PEARL, ExemptType.TELEPORT, ExemptType.WORLD_CHANGE, ExemptType.DEATH, ExemptType.SCAFFOLDING, ExemptType.LIQUID, ExemptType.WATERLOGGED, ExemptType.BLOCK_PLACE, ExemptType.RIPTIDE, ExemptType.LIQUID, ExemptType.BUBBLE_COLUMN, ExemptType.SLIME, ExemptType.PROJECTILE_DAMAGE, ExemptType.BUKKIT_VELOCITY, ExemptType.WALL);
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER || deltaY > 1.0) {
                    this.fail("deltaY=" + deltaY + " ticks=" + climbableTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
