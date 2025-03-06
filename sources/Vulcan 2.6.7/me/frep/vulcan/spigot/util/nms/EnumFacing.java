package me.frep.vulcan.spigot.util.nms;

import com.google.common.collect.Iterators;
import java.util.Iterator;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import java.util.Random;
import java.util.Map;

public enum EnumFacing implements IStringSerializable
{
    DOWN(0, 1, -1, "down", AxisDirection.NEGATIVE, Axis.Y, new Vec3i(0, -1, 0)), 
    UP(1, 0, -1, "up", AxisDirection.POSITIVE, Axis.Y, new Vec3i(0, 1, 0)), 
    NORTH(2, 3, 2, "north", AxisDirection.NEGATIVE, Axis.Z, new Vec3i(0, 0, -1)), 
    SOUTH(3, 2, 0, "south", AxisDirection.POSITIVE, Axis.Z, new Vec3i(0, 0, 1)), 
    WEST(4, 5, 1, "west", AxisDirection.NEGATIVE, Axis.X, new Vec3i(-1, 0, 0)), 
    EAST(5, 4, 3, "east", AxisDirection.POSITIVE, Axis.X, new Vec3i(1, 0, 0));
    
    private final int index;
    private final int opposite;
    private final int horizontalIndex;
    private final String name;
    private final Axis axis;
    private final AxisDirection axisDirection;
    private final Vec3i directionVec;
    private static final EnumFacing[] VALUES;
    private static final EnumFacing[] HORIZONTALS;
    private static final Map NAME_LOOKUP;
    private static final String __OBFID = "CL_00001201";
    
    private EnumFacing(final int p_i46016_3_, final int p_i46016_4_, final int p_i46016_5_, final String p_i46016_6_, final AxisDirection p_i46016_7_, final Axis p_i46016_8_, final Vec3i p_i46016_9_) {
        this.index = p_i46016_3_;
        this.horizontalIndex = p_i46016_5_;
        this.opposite = p_i46016_4_;
        this.name = p_i46016_6_;
        this.axis = p_i46016_8_;
        this.axisDirection = p_i46016_7_;
        this.directionVec = p_i46016_9_;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int getHorizontalIndex() {
        return this.horizontalIndex;
    }
    
    public AxisDirection getAxisDirection() {
        return this.axisDirection;
    }
    
    public EnumFacing getOpposite() {
        return getFront(this.opposite);
    }
    
    public EnumFacing rotateAround(final Axis axis) {
        switch (SwitchPlane.AXIS_LOOKUP[axis.ordinal()]) {
            case 1: {
                if (this != EnumFacing.WEST && this != EnumFacing.EAST) {
                    return this.rotateX();
                }
                return this;
            }
            case 2: {
                if (this != EnumFacing.UP && this != EnumFacing.DOWN) {
                    return this.rotateY();
                }
                return this;
            }
            case 3: {
                if (this != EnumFacing.NORTH && this != EnumFacing.SOUTH) {
                    return this.rotateZ();
                }
                return this;
            }
            default: {
                throw new IllegalStateException("Unable to get CW facing for axis " + axis);
            }
        }
    }
    
    public EnumFacing rotateY() {
        switch (SwitchPlane.FACING_LOOKUP[this.ordinal()]) {
            case 1: {
                return EnumFacing.EAST;
            }
            case 2: {
                return EnumFacing.SOUTH;
            }
            case 3: {
                return EnumFacing.WEST;
            }
            case 4: {
                return EnumFacing.NORTH;
            }
            default: {
                throw new IllegalStateException("Unable to get Y-rotated facing of " + this);
            }
        }
    }
    
    private EnumFacing rotateX() {
        switch (SwitchPlane.FACING_LOOKUP[this.ordinal()]) {
            case 1: {
                return EnumFacing.DOWN;
            }
            default: {
                throw new IllegalStateException("Unable to get X-rotated facing of " + this);
            }
            case 3: {
                return EnumFacing.UP;
            }
            case 5: {
                return EnumFacing.NORTH;
            }
            case 6: {
                return EnumFacing.SOUTH;
            }
        }
    }
    
    private EnumFacing rotateZ() {
        switch (SwitchPlane.FACING_LOOKUP[this.ordinal()]) {
            case 2: {
                return EnumFacing.DOWN;
            }
            default: {
                throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
            }
            case 4: {
                return EnumFacing.UP;
            }
            case 5: {
                return EnumFacing.EAST;
            }
            case 6: {
                return EnumFacing.WEST;
            }
        }
    }
    
    public EnumFacing rotateYCCW() {
        switch (SwitchPlane.FACING_LOOKUP[this.ordinal()]) {
            case 1: {
                return EnumFacing.WEST;
            }
            case 2: {
                return EnumFacing.NORTH;
            }
            case 3: {
                return EnumFacing.EAST;
            }
            case 4: {
                return EnumFacing.SOUTH;
            }
            default: {
                throw new IllegalStateException("Unable to get CCW facing of " + this);
            }
        }
    }
    
    public int getFrontOffsetX() {
        return (this.axis == Axis.X) ? this.axisDirection.getOffset() : 0;
    }
    
    public int getFrontOffsetY() {
        return (this.axis == Axis.Y) ? this.axisDirection.getOffset() : 0;
    }
    
    public int getFrontOffsetZ() {
        return (this.axis == Axis.Z) ? this.axisDirection.getOffset() : 0;
    }
    
    public String getName2() {
        return this.name;
    }
    
    public Axis getAxis() {
        return this.axis;
    }
    
    public static EnumFacing byName(final String name) {
        return (name == null) ? null : (EnumFacing) EnumFacing.NAME_LOOKUP.get(name.toLowerCase());
    }
    
    public static EnumFacing getFront(final int index) {
        return EnumFacing.VALUES[MathHelper.abs_int(index % EnumFacing.VALUES.length)];
    }
    
    public static EnumFacing getHorizontal(final int p_176731_0_) {
        return EnumFacing.HORIZONTALS[MathHelper.abs_int(p_176731_0_ % EnumFacing.HORIZONTALS.length)];
    }
    
    public static EnumFacing fromAngle(final double angle) {
        return getHorizontal(MathHelper.floor_double(angle / 90.0 + 0.5) & 0x3);
    }
    
    public static EnumFacing random(final Random rand) {
        return values()[rand.nextInt(values().length)];
    }
    
    public static EnumFacing func_176737_a(final float p_176737_0_, final float p_176737_1_, final float p_176737_2_) {
        EnumFacing var3 = EnumFacing.NORTH;
        float var4 = Float.MIN_VALUE;
        for (final EnumFacing var8 : values()) {
            final float var9 = p_176737_0_ * var8.directionVec.getX() + p_176737_1_ * var8.directionVec.getY() + p_176737_2_ * var8.directionVec.getZ();
            if (var9 > var4) {
                var4 = var9;
                var3 = var8;
            }
        }
        return var3;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public Vec3i getDirectionVec() {
        return this.directionVec;
    }
    
    static {
        VALUES = new EnumFacing[6];
        HORIZONTALS = new EnumFacing[4];
        NAME_LOOKUP = Maps.newHashMap();
        for (final EnumFacing var4 : values()) {
            EnumFacing.VALUES[var4.index] = var4;
            if (var4.getAxis().isHorizontal()) {
                EnumFacing.HORIZONTALS[var4.horizontalIndex] = var4;
            }
            EnumFacing.NAME_LOOKUP.put(var4.getName2().toLowerCase(), var4);
        }
    }
    
    public enum Axis implements Predicate, IStringSerializable
    {
        X("X", 0, "x", Plane.HORIZONTAL), 
        Y("Y", 1, "y", Plane.VERTICAL), 
        Z("Z", 2, "z", Plane.HORIZONTAL);
        
        private static final Map NAME_LOOKUP;
        private final String name;
        private final Plane plane;
        private static final Axis[] $VALUES;
        private static final String __OBFID = "CL_00002321";
        
        private Axis(final String p_i46015_1_, final int p_i46015_2_, final String name, final Plane plane) {
            this.name = name;
            this.plane = plane;
        }
        
        public static Axis byName(final String name) {
            return (name == null) ? null : (Axis) Axis.NAME_LOOKUP.get(name.toLowerCase());
        }
        
        public String getName2() {
            return this.name;
        }
        
        public boolean isVertical() {
            return this.plane == Plane.VERTICAL;
        }
        
        public boolean isHorizontal() {
            return this.plane == Plane.HORIZONTAL;
        }
        
        @Override
        public String toString() {
            return this.name;
        }
        
        public boolean apply(final EnumFacing facing) {
            return facing != null && facing.getAxis() == this;
        }
        
        public Plane getPlane() {
            return this.plane;
        }
        
        @Override
        public String getName() {
            return this.name;
        }
        
        @Override
        public boolean apply(final Object p_apply_1_) {
            return this.apply((EnumFacing)p_apply_1_);
        }
        
        static {
            NAME_LOOKUP = Maps.newHashMap();
            $VALUES = new Axis[] { Axis.X, Axis.Y, Axis.Z };
            for (final Axis var4 : values()) {
                Axis.NAME_LOOKUP.put(var4.getName2().toLowerCase(), var4);
            }
        }
    }
    
    public enum AxisDirection
    {
        POSITIVE("POSITIVE", 0, 1, "Towards positive"), 
        NEGATIVE("NEGATIVE", 1, -1, "Towards negative");
        
        private final int offset;
        private final String description;
        private static final AxisDirection[] $VALUES;
        private static final String __OBFID = "CL_00002320";
        
        private AxisDirection(final String p_i46014_1_, final int p_i46014_2_, final int offset, final String description) {
            this.offset = offset;
            this.description = description;
        }
        
        public int getOffset() {
            return this.offset;
        }
        
        @Override
        public String toString() {
            return this.description;
        }
        
        static {
            $VALUES = new AxisDirection[] { AxisDirection.POSITIVE, AxisDirection.NEGATIVE };
        }
    }
    
    public enum Plane implements Predicate, Iterable
    {
        HORIZONTAL("HORIZONTAL", 0), 
        VERTICAL("VERTICAL", 1);
        
        private static final Plane[] $VALUES;
        private static final String __OBFID = "CL_00002319";
        
        private Plane(final String p_i46013_1_, final int p_i46013_2_) {
        }
        
        public EnumFacing[] facings() {
            switch (SwitchPlane.PLANE_LOOKUP[this.ordinal()]) {
                case 1: {
                    return new EnumFacing[] { EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST };
                }
                case 2: {
                    return new EnumFacing[] { EnumFacing.UP, EnumFacing.DOWN };
                }
                default: {
                    throw new Error("Someone's been tampering with the universe!");
                }
            }
        }
        
        public EnumFacing random(final Random rand) {
            final EnumFacing[] var2 = this.facings();
            return var2[rand.nextInt(var2.length)];
        }
        
        public boolean apply(final EnumFacing facing) {
            return facing != null && facing.getAxis().getPlane() == this;
        }
        
        @Override
        public Iterator iterator() {
            return Iterators.forArray(this.facings());
        }
        
        @Override
        public boolean apply(final Object p_apply_1_) {
            return this.apply((EnumFacing)p_apply_1_);
        }
        
        static {
            $VALUES = new Plane[] { Plane.HORIZONTAL, Plane.VERTICAL };
        }
    }
    
    static final class SwitchPlane
    {
        static final int[] AXIS_LOOKUP;
        static final int[] FACING_LOOKUP;
        static final int[] PLANE_LOOKUP;
        private static final String __OBFID = "CL_00002322";
        
        static {
            PLANE_LOOKUP = new int[Plane.values().length];
            try {
                SwitchPlane.PLANE_LOOKUP[Plane.HORIZONTAL.ordinal()] = 1;
            }
            catch (final NoSuchFieldError noSuchFieldError) {}
            try {
                SwitchPlane.PLANE_LOOKUP[Plane.VERTICAL.ordinal()] = 2;
            }
            catch (final NoSuchFieldError noSuchFieldError2) {}
            FACING_LOOKUP = new int[EnumFacing.values().length];
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 1;
            }
            catch (final NoSuchFieldError noSuchFieldError3) {}
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
            }
            catch (final NoSuchFieldError noSuchFieldError4) {}
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 3;
            }
            catch (final NoSuchFieldError noSuchFieldError5) {}
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 4;
            }
            catch (final NoSuchFieldError noSuchFieldError6) {}
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.UP.ordinal()] = 5;
            }
            catch (final NoSuchFieldError noSuchFieldError7) {}
            try {
                SwitchPlane.FACING_LOOKUP[EnumFacing.DOWN.ordinal()] = 6;
            }
            catch (final NoSuchFieldError noSuchFieldError8) {}
            AXIS_LOOKUP = new int[Axis.values().length];
            try {
                SwitchPlane.AXIS_LOOKUP[Axis.X.ordinal()] = 1;
            }
            catch (final NoSuchFieldError noSuchFieldError9) {}
            try {
                SwitchPlane.AXIS_LOOKUP[Axis.Y.ordinal()] = 2;
            }
            catch (final NoSuchFieldError noSuchFieldError10) {}
            try {
                SwitchPlane.AXIS_LOOKUP[Axis.Z.ordinal()] = 3;
            }
            catch (final NoSuchFieldError noSuchFieldError11) {}
        }
    }
}
