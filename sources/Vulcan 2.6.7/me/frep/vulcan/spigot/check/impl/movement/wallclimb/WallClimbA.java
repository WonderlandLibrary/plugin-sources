package me.frep.vulcan.spigot.check.impl.movement.wallclimb;

import java.util.Iterator;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.Material;
import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Wall Climb", type = 'A', complexType = "Spider", description = "Climbing up a wall.")
public class WallClimbA extends AbstractCheck
{
    private int ticks;
    
    public WallClimbA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final double x = Math.abs(this.data.getPositionProcessor().getX() % 1.0);
            final double z = Math.abs(this.data.getPositionProcessor().getZ() % 1.0);
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double diff = Math.abs(deltaY - 0.20000004768371582);
            if (diff < 0.001) {
                return;
            }
            if (this.data.getActionProcessor().getDistanceFromLastBreak() < 1.25 && this.data.getActionProcessor().getDistanceFromLastBreak() > 0.0) {
                return;
            }
            final boolean collidingVertically = this.data.getPositionProcessor().isCollidingVertically();
            final boolean againstWall = (x > 0.67 && x < 0.73) || (z > 0.67 && z < 0.73) || (x > 0.27 && x < 0.33) || (z > 0.27 && z < 0.33);
            if (againstWall) {
                ++this.ticks;
            }
            else {
                this.ticks = 0;
            }
            Material below;
            if (ServerUtil.isHigherThan1_13()) {
                below = this.data.getPositionProcessor().getBlockBelowModern();
            }
            else {
                below = this.data.getPositionProcessor().getBlockBelow();
            }
            if (below != null && !BlockUtil.isAir(below)) {
                this.ticks = 0;
            }
            if (this.data.getPositionProcessor().getBlocksAbove() != null && this.data.getPositionProcessor().getBlocksBelow() != null) {
                int aboveAir = 0;
                int belowAir = 0;
                for (final Material material : this.data.getPositionProcessor().getBlocksAbove()) {
                    if (BlockUtil.isAir(material)) {
                        ++aboveAir;
                    }
                }
                for (final Material material : this.data.getPositionProcessor().getBlocksBelow()) {
                    if (BlockUtil.isAir(material)) {
                        ++belowAir;
                    }
                }
                final boolean invalid = this.ticks > 5 && deltaY > 0.1 && aboveAir == belowAir;
                final boolean exempt = this.isExempt(ExemptType.CLIMBABLE, ExemptType.SCAFFOLDING, ExemptType.JUMP_BOOST, ExemptType.BLOCK_PLACE, ExemptType.FLIGHT, ExemptType.CREATIVE, ExemptType.GLIDING, ExemptType.LEVITATION, ExemptType.VELOCITY, ExemptType.RIPTIDE, ExemptType.CANCELLED_PLACE, ExemptType.NEAR_GROUND, ExemptType.POWDER_SNOW, ExemptType.SLIME, ExemptType.LIQUID, ExemptType.END_ROD, ExemptType.SHULKER, ExemptType.SHULKER_BOX, ExemptType.BUBBLE_COLUMN, ExemptType.WATERLOGGED, ExemptType.EMPTIED_BUCKET, ExemptType.TRAPDOOR);
                if (invalid && !exempt && collidingVertically) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaY=" + deltaY + " ticks=" + this.ticks);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
