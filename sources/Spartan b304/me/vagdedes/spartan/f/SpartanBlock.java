package me.vagdedes.spartan.f;

import me.vagdedes.spartan.Register;
import org.bukkit.block.Block;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.Material;
import java.util.UUID;

public class SpartanBlock
{
    private UUID uuid;
    private Material material;
    private World world;
    private Chunk chunk;
    private byte a;
    private int x;
    private int y;
    private int z;
    private int G;
    private Position a;
    private boolean o;
    
    public SpartanBlock(final UUID uuid, final World world, final Chunk chunk, final Material material, final byte a, final int x, final int y, final int z, final int g, final Position a2, final boolean o) {
        super();
        this.uuid = uuid;
        this.world = world;
        this.material = material;
        this.chunk = chunk;
        this.a = a;
        this.x = x;
        this.y = y;
        this.z = z;
        this.G = g;
        this.a = a2;
        this.o = o;
    }
    
    public SpartanBlock(final Block block) {
        super();
        this.uuid = null;
        this.world = block.getWorld();
        this.material = block.getType();
        this.a = block.getData();
        this.chunk = block.getChunk();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.G = 0;
        this.o = block.isLiquid();
    }
    
    public Chunk getChunk() {
        return this.chunk;
    }
    
    public World getWorld() {
        return this.world;
    }
    
    public Material getType() {
        return this.material;
    }
    
    public byte getData() {
        return this.a;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getZ() {
        return this.z;
    }
    
    void setX(final int x) {
        this.x = x;
    }
    
    void setY(final int y) {
        this.y = y;
    }
    
    void setZ(final int z) {
        this.z = z;
    }
    
    public void setType(final Material material) {
        this.material = material;
    }
    
    public void setData(final byte a) {
        this.a = a;
    }
    
    public boolean isLiquid() {
        if (!this.o) {
            if (Register.v1_13) {
                if (this.material == Material.WATER) {
                    return true;
                }
                if (this.material == Material.LAVA) {
                    return true;
                }
            }
            else if (this.material == Material.getMaterial("STATIONARY_WATER") || this.material == Material.getMaterial("STATIONARY_LAVA")) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    public SpartanLocation a() {
        return new SpartanLocation(this.G, this.uuid, this.world, this.chunk, this.x, this.y, this.z, 0.0f, 0.0f, this, this.a, true);
    }
}
