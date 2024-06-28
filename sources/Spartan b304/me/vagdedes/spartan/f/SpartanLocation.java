package me.vagdedes.spartan.f;

import me.vagdedes.spartan.k.d.MathUtils;
import org.bukkit.util.Vector;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.c.ChunkManager;
import org.bukkit.Material;
import java.util.Iterator;
import org.bukkit.Location;
import java.util.Set;
import java.util.Collections;
import java.util.Objects;
import java.util.HashSet;
import java.util.HashMap;
import org.bukkit.Chunk;
import org.bukkit.World;
import java.util.UUID;

public class SpartanLocation
{
    private UUID uuid;
    private World world;
    private Chunk chunk;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private SpartanBlock a;
    private int G;
    private Position a;
    private boolean p;
    private static final HashMap<UUID, HashSet<Position>> R;
    private static final HashMap<UUID, SpartanLocation[]> S;
    private static final boolean q = false;
    private static final boolean r = false;
    private static final boolean s = false;
    public static final int H = -1;
    public static final int I = 0;
    public static final int J = 1;
    public static final int K = 2;
    public static final int L = 3;
    public static final int M = 4;
    public static final int N = 5;
    
    @Override
    public int hashCode() {
        return Objects.hash(this.world, Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.z), Float.valueOf(this.yaw), Float.valueOf(this.pitch), Boolean.valueOf(this.p));
    }
    
    public static void clear() {
        SpartanLocation.R.clear();
        SpartanLocation.S.clear();
    }
    
    private boolean a(final HashSet<Position> s, final HashSet<SpartanLocation> set) {
        boolean b = false;
        final Set<Position> synchronizedSet = Collections.<Position>synchronizedSet(s);
        synchronized (synchronizedSet) {
            for (final Position position : synchronizedSet) {
                if (position.getID() == this.G) {
                    b = true;
                    set.add(new SpartanLocation(this.uuid, new Location(this.world, this.a.getX() + position.getX(), this.a.getY() + position.getY(), this.a.getZ() + position.getZ(), this.yaw, this.pitch), 0));
                }
            }
        }
        return b;
    }
    
    private void cache() {
        if (this == null || this.uuid == null || this.G != 0) {}
    }
    
    private void a(int max, final UUID uuid, final World world, final Chunk chunk, final double x, final double y, final double z, final float yaw, final float pitch, final Material material, final byte b, final boolean b2, final Position a, final boolean p14) {
        max = Math.max(max, -1);
        final boolean b3 = max != this.G;
        this.G = max;
        this.uuid = uuid;
        this.world = world;
        this.chunk = chunk;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.p = p14;
        if (a != null) {
            this.a = a;
        }
        else {
            this.a = new Position(this.G, this.x, this.y, this.z);
        }
        this.a = new SpartanBlock(this.uuid, this.world, this.chunk, material, b, this.getBlockX(), this.getBlockY(), this.getBlockZ(), this.G, this.a, b2);
        if (!this.p || b3) {
            this.cache();
        }
    }
    
    public SpartanLocation(final int n, final UUID uuid, final World world, final Chunk chunk, final double n2, final double n3, final double n4, final float n5, final float n6, final SpartanBlock spartanBlock, final Position position, final boolean b) {
        super();
        this.a(n, uuid, world, chunk, n2, n3, n4, n5, n6, spartanBlock.getType(), spartanBlock.getData(), spartanBlock.isLiquid(), position, b);
    }
    
    public SpartanLocation(final UUID uuid, final Location location, final int n) {
        super();
        final World world = location.getWorld();
        final boolean a = ChunkManager.a(world, location.getBlockX(), location.getBlockZ());
        final Chunk chunk = a ? location.getChunk() : null;
        final Block block = a ? location.getBlock() : null;
        this.a(n, uuid, world, chunk, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), (block == null) ? Material.STONE : block.getType(), (block == null) ? 0 : block.getData(), block != null && (block.isLiquid() || (Register.v1_13 && block instanceof Waterlogged)), (Position)null, false);
    }
    
    public SpartanLocation(final Location location) {
        super();
        final World world = location.getWorld();
        final boolean a = ChunkManager.a(world, location.getBlockX(), location.getBlockZ());
        final Chunk chunk = a ? location.getChunk() : null;
        final Block block = a ? location.getBlock() : null;
        this.a(0, null, world, chunk, location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), (block == null) ? Material.STONE : block.getType(), (block == null) ? 0 : block.getData(), block != null && (block.isLiquid() || (Register.v1_13 && block instanceof Waterlogged)), (Position)null, false);
    }
    
    public SpartanLocation(final UUID uuid, final SpartanLocation spartanLocation, final int n) {
        super();
        final World world = spartanLocation.getWorld();
        final Chunk chunk = spartanLocation.getChunk();
        final SpartanBlock a = spartanLocation.a();
        if (world != null && chunk != null && a != null) {
            this.a(n, uuid, world, chunk, spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ(), spartanLocation.getYaw(), spartanLocation.getPitch(), a.getType(), a.getData(), a.isLiquid(), null, true);
        }
    }
    
    private SpartanLocation(final SpartanLocation spartanLocation) {
        super();
        final World world = spartanLocation.getWorld();
        final Chunk chunk = spartanLocation.getChunk();
        final SpartanBlock a = spartanLocation.a();
        if (world != null && chunk != null && a != null) {
            this.a(spartanLocation.G, spartanLocation.uuid, world, chunk, spartanLocation.getX(), spartanLocation.getY(), spartanLocation.getZ(), spartanLocation.getYaw(), spartanLocation.getPitch(), a.getType(), a.getData(), a.isLiquid(), null, true);
        }
    }
    
    public SpartanLocation b() {
        return new SpartanLocation(this);
    }
    
    public int j() {
        return this.G;
    }
    
    public boolean j() {
        return this.p;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public Chunk getChunk() {
        return this.chunk;
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
    
    public void setX(final double x) {
        if (!this.isNull()) {
            this.x = x;
            if (this.a != null) {
                this.a.setX((int)x);
            }
        }
    }
    
    public void setY(final double y) {
        if (!this.isNull()) {
            this.y = y;
            if (this.a != null) {
                this.a.setY((int)y);
            }
        }
    }
    
    public void setZ(final double z) {
        if (!this.isNull()) {
            this.z = z;
            if (this.a != null) {
                this.a.setZ((int)z);
            }
        }
    }
    
    public void setYaw(final float yaw) {
        if (!this.isNull()) {
            if (yaw > 360.0f) {
                this.yaw = 360.0f;
            }
            else if (yaw < 0.0f) {
                this.yaw = 0.0f;
            }
            else {
                this.yaw = yaw;
            }
        }
    }
    
    public void setPitch(final float pitch) {
        if (!this.isNull()) {
            if (pitch > 90.0f) {
                this.pitch = 90.0f;
            }
            else if (pitch < -90.0f) {
                this.pitch = -90.0f;
            }
            else {
                this.pitch = pitch;
            }
        }
    }
    
    public int getBlockX() {
        return (int)this.x;
    }
    
    public int getBlockY() {
        return (int)this.y;
    }
    
    public int getBlockZ() {
        return (int)this.z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public Vector getDirection() {
        final Vector vector = new Vector();
        final double n = (double)this.getYaw();
        final double n2 = (double)this.getPitch();
        vector.setY(-Math.sin(Math.toRadians(n2)));
        final double cos = Math.cos(Math.toRadians(n2));
        vector.setX(-cos * Math.sin(Math.toRadians(n)));
        vector.setZ(cos * Math.cos(Math.toRadians(n)));
        return vector;
    }
    
    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }
    
    public SpartanLocation a(final double n, final double n2, final double n3) {
        return this.b(-n, -n2, -n3);
    }
    
    public SpartanLocation a(final SpartanLocation spartanLocation) {
        return this.a(spartanLocation.x, spartanLocation.y, spartanLocation.z);
    }
    
    public SpartanLocation a(final Vector vector) {
        return this.a(vector.getX(), vector.getY(), vector.getZ());
    }
    
    public SpartanLocation b(final Vector vector) {
        return this.b(vector.getX(), vector.getY(), vector.getZ());
    }
    
    public SpartanLocation b(final SpartanLocation spartanLocation) {
        return this.b(spartanLocation.x, spartanLocation.y, spartanLocation.z);
    }
    
    public SpartanLocation b(final double n, final double n2, final double n3) {
        final int blockX = this.getBlockX();
        final int blockY = this.getBlockY();
        final int blockZ = this.getBlockZ();
        final Location add = new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch).add(n, n2, n3);
        this.setX(add.getX());
        this.setY(add.getY());
        this.setZ(add.getZ());
        if (this.getBlockX() != blockX || this.getBlockY() != blockY || this.getBlockZ() != blockZ) {
            return new SpartanLocation(this.uuid, new Location(this.world, this.x, this.y, this.z, this.yaw, this.pitch), this.G);
        }
        return this;
    }
    
    public SpartanBlock a() {
        final SpartanBlock a = this.a;
        return (a == null) ? new SpartanBlock(this.uuid, this.world, this.chunk, Material.AIR, (byte)0, (int)this.x, (int)this.y, (int)this.z, this.G, this.a, false) : a;
    }
    
    public double a(final SpartanLocation spartanLocation) {
        return (this.world == spartanLocation.world) ? MathUtils.a(this.x, spartanLocation.x, this.y, spartanLocation.y, this.z, spartanLocation.z) : -1.0;
    }
    
    public boolean isEmpty() {
        return this.x == 0.0 && this.y == 0.0 && this.z == 0.0 && this.yaw == 0.0f && this.pitch == 0.0f;
    }
    
    public boolean isNull() {
        return this.world == null && this.chunk == null;
    }
    
    public /* synthetic */ Object clone() {
        return this.b();
    }
    
    static {
        R = new HashMap<UUID, HashSet<Position>>();
        S = new HashMap<UUID, SpartanLocation[]>();
    }
}
