package me.vagdedes.spartan.k.c;

import org.bukkit.Chunk;
import java.util.Iterator;
import org.bukkit.World;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Settings;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.HashMap;

public class ChunkManager
{
    private static final HashMap<String, CopyOnWriteArrayList<Double>> ab;
    private static final int W = 100000;
    private static final int X = 16;
    
    public ChunkManager() {
        super();
    }
    
    public static void j(final boolean b) {
        ChunkManager.ab.clear();
        if (b && Settings.canDo("Detections.run_asynchronously")) {
            final Iterator<World> iterator = Bukkit.getWorlds().iterator();
            while (iterator.hasNext()) {
                b(iterator.next());
            }
        }
    }
    
    public static void a(final World world) {
        ChunkManager.ab.remove(world.getName());
    }
    
    public static void b(final World world) {
        final CopyOnWriteArrayList<Double> value = new CopyOnWriteArrayList<Double>();
        for (final Chunk chunk : world.getLoadedChunks()) {
            value.add(Double.valueOf(a(chunk.getX(), chunk.getZ())));
        }
        ChunkManager.ab.put(world.getName(), value);
    }
    
    public static boolean f() {
        return !ChunkManager.ab.isEmpty();
    }
    
    public static boolean a(final World world) {
        final CopyOnWriteArrayList<Double> list = (CopyOnWriteArrayList<Double>)ChunkManager.ab.get(world.getName());
        return list != null && list.size() >= 16;
    }
    
    private static double a(final int n, final int n2) {
        final boolean b = n < 0;
        final boolean b2 = n2 < 0;
        return Double.valueOf(((b || b2) ? "-" : "") + (b ? Math.abs(n * 100000) : n) + "." + (b2 ? Math.abs(n2 * 100000) : n2));
    }
    
    public static synchronized boolean a(final World world, int n, int n2) {
        if (!Settings.canDo("Detections.run_asynchronously")) {
            return true;
        }
        if (world == null) {
            return false;
        }
        if (a(world)) {
            n >>= 4;
            n2 >>= 4;
            final CopyOnWriteArrayList<Double> list = (CopyOnWriteArrayList<Double>)ChunkManager.ab.get(world.getName());
            return list == null || list.contains(Double.valueOf(a(n, n2)));
        }
        b(world);
        return false;
    }
    
    public static synchronized void a(final World world, final Chunk chunk) {
        if (Settings.canDo("Detections.run_asynchronously") && world != null && chunk != null) {
            final CopyOnWriteArrayList<Double> list = (CopyOnWriteArrayList<Double>)ChunkManager.ab.get(world.getName());
            if (list != null) {
                list.add(Double.valueOf(a(chunk.getX(), chunk.getZ())));
            }
            else {
                b(world);
            }
        }
    }
    
    public static synchronized void b(final World world, final Chunk chunk) {
        if (Settings.canDo("Detections.run_asynchronously") && world != null && chunk != null) {
            final CopyOnWriteArrayList<Double> list = (CopyOnWriteArrayList<Double>)ChunkManager.ab.get(world.getName());
            if (list != null) {
                list.remove(Double.valueOf(a(chunk.getX(), chunk.getZ())));
            }
            else {
                b(world);
            }
        }
    }
    
    static {
        ab = new HashMap<String, CopyOnWriteArrayList<Double>>(50);
    }
}
