package me.frep.vulcan.spigot.util.reach;

import org.bukkit.entity.Entity;
import me.frep.vulcan.spigot.util.nms.EnumFacing;
import me.frep.vulcan.spigot.util.nms.BlockPos;

public class MovingPoint
{
    private BlockPos blockPos;
    public MovingObjectType typeOfHit;
    public EnumFacing sideHit;
    public Point hitVec;
    public Entity entityHit;
    
    public MovingPoint(final Point hitVecIn, final EnumFacing facing, final BlockPos blockPosIn) {
        this(MovingObjectType.BLOCK, hitVecIn, facing, blockPosIn);
    }
    
    public MovingPoint(final Point p_i45552_1_, final EnumFacing facing) {
        this(MovingObjectType.BLOCK, p_i45552_1_, facing, BlockPos.ORIGIN);
    }
    
    public MovingPoint(final Entity p_i2304_1_) {
        this(p_i2304_1_, new Point(p_i2304_1_.getLocation().getX(), p_i2304_1_.getLocation().getY(), p_i2304_1_.getLocation().getZ()));
    }
    
    public MovingPoint(final MovingObjectType typeOfHitIn, final Point hitVecIn, final EnumFacing sideHitIn, final BlockPos blockPosIn) {
        this.typeOfHit = typeOfHitIn;
        this.blockPos = blockPosIn;
        this.sideHit = sideHitIn;
        this.hitVec = new Point(hitVecIn.getX(), hitVecIn.getY(), hitVecIn.getZ());
    }
    
    public MovingPoint(final Entity entityHitIn, final Point hitVecIn) {
        this.typeOfHit = MovingObjectType.ENTITY;
        this.entityHit = entityHitIn;
        this.hitVec = hitVecIn;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    @Override
    public String toString() {
        return "HitResult{type=" + this.typeOfHit + ", blockpos=" + this.blockPos + ", f=" + this.sideHit + ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
    }
    
    public enum MovingObjectType
    {
        MISS, 
        BLOCK, 
        ENTITY;
    }
}
