package me.vagdedes.spartan.features.d.a;

import java.util.UUID;

public class CacheObject
{
    private final UUID uuid;
    private UUID a;
    private String g;
    private boolean b;
    private float x;
    private float y;
    private float z;
    
    public CacheObject(final UUID uuid, final String g, final boolean b, final double n, final double n2, final double n3) {
        super();
        this.uuid = UUID.randomUUID();
        this.a = ((uuid == null) ? this.uuid : uuid);
        this.g = g;
        this.b = b;
        this.x = (float)n;
        this.y = (float)n2;
        this.z = (float)n3;
    }
    
    public UUID getUUID() {
        return this.a;
    }
    
    public String getString() {
        return this.g;
    }
    
    public boolean getBoolean() {
        return this.b;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getZ() {
        return this.z;
    }
}
