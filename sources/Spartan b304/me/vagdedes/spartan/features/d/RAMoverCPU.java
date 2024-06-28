package me.vagdedes.spartan.features.d;

import me.vagdedes.spartan.k.c.LagManagement;
import java.util.UUID;
import me.vagdedes.spartan.features.d.a.CacheObject;
import java.util.concurrent.ConcurrentHashMap;

public class RAMoverCPU
{
    private static final ConcurrentHashMap<CacheObject, Boolean> a;
    private static final boolean l;
    private static final int u = 3200;
    private CacheObject a;
    private Boolean a;
    
    private static boolean d() {
        return RAMoverCPU.l && RAMoverCPU.a.size() < 3200;
    }
    
    public static void run() {
        clear();
    }
    
    public static void clear() {
        RAMoverCPU.a.clear();
    }
    
    public RAMoverCPU(final UUID uuid, final String s, final boolean b, final double n, final double n2, final double n3) {
        super();
        this.a = null;
        if (d()) {
            this.a = new CacheObject(uuid, s, b, n, n2, n3);
        }
    }
    
    public boolean exists() {
        return this.a != null && (this.a = Boolean.valueOf(RAMoverCPU.a.get((Object)this.a))) != null;
    }
    
    public boolean h() {
        return this.a;
    }
    
    public boolean a(final boolean b) {
        if (this.a != null) {
            final float y = this.a.getY();
            if (y == (int)y) {
                RAMoverCPU.a.put(this.a, Boolean.valueOf(b));
            }
        }
        return b;
    }
    
    static {
        a = new ConcurrentHashMap<CacheObject, Boolean>();
        l = LagManagement.a(2048);
    }
}
