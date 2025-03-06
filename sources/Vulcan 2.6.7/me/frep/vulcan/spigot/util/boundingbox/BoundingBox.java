package me.frep.vulcan.spigot.util.boundingbox;

import me.frep.vulcan.spigot.util.nms.EnumFacing;
import me.frep.vulcan.spigot.util.reach.MovingPoint;
import me.frep.vulcan.spigot.util.reach.Point;
import java.util.List;
import java.util.Collection;
import me.frep.vulcan.spigot.util.StreamUtil;
import me.frep.vulcan.spigot.util.BlockUtil;
import java.util.LinkedList;
import org.bukkit.Material;
import java.util.function.Predicate;
import org.bukkit.World;
import org.bukkit.Location;

public final class BoundingBox
{
    private double minX;
    private double minY;
    private double minZ;
    private double maxX;
    private double maxY;
    private double maxZ;
    private final long timestamp;
    
    public BoundingBox(final double x, final double y, final double z) {
        this(x, x, y, y, z, z);
    }
    
    public BoundingBox(final double minX, final double maxX, final double minY, final double maxY, final double minZ, final double maxZ) {
        this.timestamp = System.currentTimeMillis();
        if (minX < maxX) {
            this.minX = minX;
            this.maxX = maxX;
        }
        else {
            this.minX = maxX;
            this.maxX = minX;
        }
        if (minY < maxY) {
            this.minY = minY;
            this.maxY = maxY;
        }
        else {
            this.minY = maxY;
            this.maxY = minY;
        }
        if (minZ < maxZ) {
            this.minZ = minZ;
            this.maxZ = maxZ;
        }
        else {
            this.minZ = maxZ;
            this.maxZ = minZ;
        }
    }
    
    public double distance(final Location location) {
        return Math.sqrt(Math.min(Math.pow(location.getX() - this.minX, 2.0), Math.pow(location.getX() - this.maxX, 2.0)) + Math.min(Math.pow(location.getZ() - this.minZ, 2.0), Math.pow(location.getZ() - this.maxZ, 2.0)));
    }
    
    public double distance(final double x, final double z) {
        final double dx = Math.min(Math.pow(x - this.minX, 2.0), Math.pow(x - this.maxX, 2.0));
        final double dz = Math.min(Math.pow(z - this.minZ, 2.0), Math.pow(z - this.maxZ, 2.0));
        return Math.sqrt(dx + dz);
    }
    
    public double distance(final BoundingBox box) {
        final double dx = Math.min(Math.pow(box.minX - this.minX, 2.0), Math.pow(box.maxX - this.maxX, 2.0));
        final double dz = Math.min(Math.pow(box.minZ - this.minZ, 2.0), Math.pow(box.maxZ - this.maxZ, 2.0));
        return Math.sqrt(dx + dz);
    }
    
    public BoundingBox add(final BoundingBox box) {
        this.minX += box.minX;
        this.minY += box.minY;
        this.minZ += box.minZ;
        this.maxX += box.maxX;
        this.maxY += box.maxY;
        this.maxZ += box.maxZ;
        return this;
    }
    
    public BoundingBox move(final double x, final double y, final double z) {
        this.minX += x;
        this.minY += y;
        this.minZ += z;
        this.maxX += x;
        this.maxY += y;
        this.maxZ += z;
        return this;
    }
    
    public BoundingBox expand(final double x, final double y, final double z) {
        this.minX -= x;
        this.minY -= y;
        this.minZ -= z;
        this.maxX += x;
        this.maxY += y;
        this.maxZ += z;
        return this;
    }
    
    public boolean checkAllBlocks(final World world, final Predicate<Material> predicate) {
        final int first = (int)Math.floor(this.minX);
        final int second = (int)Math.ceil(this.maxX);
        final int third = (int)Math.floor(this.minY);
        final int forth = (int)Math.ceil(this.maxY);
        final int fifth = (int)Math.floor(this.minZ);
        final int sixth = (int)Math.ceil(this.maxZ);
        final List<Material> list = new LinkedList<Material>();
        list.add(BlockUtil.getBlockTypeASync(world, first, third, fifth));
        for (int i = first; i < second; ++i) {
            for (int j = third; j < forth; ++j) {
                for (int k = fifth; k < sixth; ++k) {
                    list.add(BlockUtil.getBlockTypeASync(world, i, j, k));
                }
            }
        }
        return StreamUtil.allMatch(list, predicate);
    }
    
    public double middleX() {
        return (this.minX + this.maxX) / 2.0;
    }
    
    public double middleY() {
        return (this.minY + this.maxY) / 2.0;
    }
    
    public double middleZ() {
        return (this.minZ + this.maxZ) / 2.0;
    }
    
    private boolean isVecInYZ(final Point vec) {
        return vec != null && vec.getY() >= this.minY && vec.getY() <= this.maxY && vec.getZ() >= this.minZ && vec.getZ() <= this.maxZ;
    }
    
    private boolean isVecInXZ(final Point vec) {
        return vec != null && vec.getX() >= this.minX && vec.getX() <= this.maxX && vec.getZ() >= this.minZ && vec.getZ() <= this.maxZ;
    }
    
    private boolean isVecInXY(final Point vec) {
        return vec != null && vec.getX() >= this.minX && vec.getX() <= this.maxX && vec.getY() >= this.minY && vec.getY() <= this.maxY;
    }
    
    public MovingPoint calculateIntercept(final Point vecA, final Point vecB) {
        Point vec3 = vecA.getIntermediateWithXValue(vecB, this.minX);
        Point vec4 = vecA.getIntermediateWithXValue(vecB, this.maxX);
        Point vec5 = vecA.getIntermediateWithYValue(vecB, this.minY);
        Point vec6 = vecA.getIntermediateWithYValue(vecB, this.maxY);
        Point vec7 = vecA.getIntermediateWithZValue(vecB, this.minZ);
        Point vec8 = vecA.getIntermediateWithZValue(vecB, this.maxZ);
        if (!this.isVecInYZ(vec3)) {
            vec3 = null;
        }
        if (!this.isVecInYZ(vec4)) {
            vec4 = null;
        }
        if (!this.isVecInXZ(vec5)) {
            vec5 = null;
        }
        if (!this.isVecInXZ(vec6)) {
            vec6 = null;
        }
        if (!this.isVecInXY(vec7)) {
            vec7 = null;
        }
        if (!this.isVecInXY(vec8)) {
            vec8 = null;
        }
        Point vec9 = null;
        if (vec3 != null) {
            vec9 = vec3;
        }
        if (vec4 != null && (vec9 == null || vecA.squareDistanceTo(vec4) < vecA.squareDistanceTo(vec9))) {
            vec9 = vec4;
        }
        if (vec5 != null && (vec9 == null || vecA.squareDistanceTo(vec5) < vecA.squareDistanceTo(vec9))) {
            vec9 = vec5;
        }
        if (vec6 != null && (vec9 == null || vecA.squareDistanceTo(vec6) < vecA.squareDistanceTo(vec9))) {
            vec9 = vec6;
        }
        if (vec7 != null && (vec9 == null || vecA.squareDistanceTo(vec7) < vecA.squareDistanceTo(vec9))) {
            vec9 = vec7;
        }
        if (vec8 != null && (vec9 == null || vecA.squareDistanceTo(vec8) < vecA.squareDistanceTo(vec9))) {
            vec9 = vec8;
        }
        if (vec9 == null) {
            return null;
        }
        EnumFacing enumfacing = null;
        if (vec9 == vec3) {
            enumfacing = EnumFacing.WEST;
        }
        else if (vec9 == vec4) {
            enumfacing = EnumFacing.EAST;
        }
        else if (vec9 == vec5) {
            enumfacing = EnumFacing.DOWN;
        }
        else if (vec9 == vec6) {
            enumfacing = EnumFacing.UP;
        }
        else if (vec9 == vec7) {
            enumfacing = EnumFacing.NORTH;
        }
        else {
            enumfacing = EnumFacing.SOUTH;
        }
        return new MovingPoint(vec9, enumfacing);
    }
    
    public BoundingBox cloneBB() {
        return new BoundingBox(this.minX, this.maxX, this.minY, this.maxY, this.minZ, this.maxZ);
    }
    
    public double getMinX() {
        return this.minX;
    }
    
    public double getMinY() {
        return this.minY;
    }
    
    public double getMinZ() {
        return this.minZ;
    }
    
    public double getMaxX() {
        return this.maxX;
    }
    
    public double getMaxY() {
        return this.maxY;
    }
    
    public double getMaxZ() {
        return this.maxZ;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void setMinX(final double minX) {
        this.minX = minX;
    }
    
    public void setMinY(final double minY) {
        this.minY = minY;
    }
    
    public void setMinZ(final double minZ) {
        this.minZ = minZ;
    }
    
    public void setMaxX(final double maxX) {
        this.maxX = maxX;
    }
    
    public void setMaxY(final double maxY) {
        this.maxY = maxY;
    }
    
    public void setMaxZ(final double maxZ) {
        this.maxZ = maxZ;
    }
}
