package me.frep.vulcan.spigot.util.reach;

import org.bukkit.block.BlockFace;
import me.frep.vulcan.spigot.util.nms.MathHelper;
import org.bukkit.util.Vector;

public class Point
{
    private final double x;
    private final double y;
    private final double z;
    
    public Point(double x, double y, double z) {
        if (x == -0.0) {
            x = 0.0;
        }
        if (y == -0.0) {
            y = 0.0;
        }
        if (z == -0.0) {
            z = 0.0;
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Point(final Point pos) {
        this(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public Point(final Vector vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }
    
    public Point subtractReverse(final Point vec) {
        return new Point(vec.x - this.x, vec.y - this.y, vec.z - this.z);
    }
    
    public Point normalize() {
        final double d0 = MathHelper.sqrt_double(this.x * this.x + this.y * this.y + this.z * this.z);
        return (d0 < 1.0E-4) ? new Point(0.0, 0.0, 0.0) : new Point(this.x / d0, this.y / d0, this.z / d0);
    }
    
    public double dotProduct(final Point vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }
    
    public Point crossProduct(final Point vec) {
        return new Point(this.y * vec.z - this.z * vec.y, this.z * vec.x - this.x * vec.z, this.x * vec.y - this.y * vec.x);
    }
    
    public Point subtract(final Point vec) {
        return this.subtract(vec.x, vec.y, vec.z);
    }
    
    public Point subtract(final double x, final double y, final double z) {
        return this.addVector(-x, -y, -z);
    }
    
    public Point add(final Point vec) {
        return this.addVector(vec.x, vec.y, vec.z);
    }
    
    public Point addVector(final double x, final double y, final double z) {
        return new Point(this.x + x, this.y + y, this.z + z);
    }
    
    public double distanceTo(final Point vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        final double d3 = vec.z - this.z;
        return MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d3 * d3);
    }
    
    public double distanceXZTo(final Point vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.z - this.z;
        return MathHelper.sqrt_double(d0 * d0 + d2 * d2);
    }
    
    public double squareDistanceTo(final Point vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        final double d3 = vec.z - this.z;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public double squareDistanceXZTo(final Point vec) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.z - this.z;
        return d0 * d0 + d2 * d2;
    }
    
    public double squareDistanceYTo(final Point vec) {
        return vec.y - this.y;
    }
    
    public double lengthVector() {
        return MathHelper.sqrt_double(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    public double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }
    
    public double lengthXZSquared() {
        return this.x * this.x + this.z * this.z;
    }
    
    public Point getRelative(final BlockFace blockFace) {
        return new Point(this.x + blockFace.getModX(), this.y + blockFace.getModY(), this.z + blockFace.getModZ());
    }
    
    public Point getIntermediateWithXValue(final Point vec, final double x) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        final double d3 = vec.z - this.z;
        if (d0 * d0 < 1.0000000116860974E-7) {
            return null;
        }
        final double d4 = (x - this.x) / d0;
        return (d4 >= 0.0 && d4 <= 1.0) ? new Point(this.x + d0 * d4, this.y + d2 * d4, this.z + d3 * d4) : null;
    }
    
    public Point getIntermediateWithYValue(final Point vec, final double y) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        final double d3 = vec.z - this.z;
        if (d2 * d2 < 1.0000000116860974E-7) {
            return null;
        }
        final double d4 = (y - this.y) / d2;
        return (d4 >= 0.0 && d4 <= 1.0) ? new Point(this.x + d0 * d4, this.y + d2 * d4, this.z + d3 * d4) : null;
    }
    
    public Point getIntermediateWithZValue(final Point vec, final double z) {
        final double d0 = vec.x - this.x;
        final double d2 = vec.y - this.y;
        final double d3 = vec.z - this.z;
        if (d3 * d3 < 1.0000000116860974E-7) {
            return null;
        }
        final double d4 = (z - this.z) / d3;
        return (d4 >= 0.0 && d4 <= 1.0) ? new Point(this.x + d0 * d4, this.y + d2 * d4, this.z + d3 * d4) : null;
    }
    
    public Point rotatePitch(final float pitch) {
        final float f = MathHelper.cos(pitch);
        final float f2 = MathHelper.sin(pitch);
        final double d0 = this.x;
        final double d2 = this.y * f + this.z * f2;
        final double d3 = this.z * f - this.y * f2;
        return new Point(d0, d2, d3);
    }
    
    public Point rotateYaw(final float yaw) {
        final float f = MathHelper.cos(yaw);
        final float f2 = MathHelper.sin(yaw);
        final double d0 = this.x * f + this.z * f2;
        final double d2 = this.y;
        final double d3 = this.z * f - this.x * f2;
        return new Point(d0, d2, d3);
    }
    
    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }
    
    public int getBlockX() {
        return MathHelper.floor_double(this.x);
    }
    
    public int getBlockY() {
        return MathHelper.floor_double(this.y);
    }
    
    public int getBlockZ() {
        return MathHelper.floor_double(this.z);
    }
    
    public Point scale(final double factor) {
        return this.mul(factor, factor, factor);
    }
    
    public Point mul(final double factorX, final double factorY, final double factorZ) {
        return new Point(this.x * factorX, this.y * factorY, this.z * factorZ);
    }
    
    public double dot(final Point other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    
    public float angle(final Point other) {
        final double dot = this.dot(other) / (this.lengthVector() * other.lengthVector());
        return (float)Math.acos(dot);
    }
    
    @Override
    public String toString() {
        return "Point{x=" + this.x + ", y=" + this.y + ", z=" + this.z + '}';
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
}
