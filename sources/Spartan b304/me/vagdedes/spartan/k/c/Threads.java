package me.vagdedes.spartan.k.c;

import java.util.concurrent.Executors;
import me.vagdedes.spartan.system.Enums;
import java.util.concurrent.ExecutorService;

public class Threads
{
    private static final ExecutorService executor;
    public static boolean enabled;
    
    public Threads() {
        super();
    }
    
    public static void destroy() {
        Threads.enabled = false;
        Threads.executor.shutdown();
    }
    
    public static void a(final Object o, final Runnable runnable) {
        if (o != null) {
            if (Threads.enabled && ChunkManager.f()) {
                Threads.executor.submit(runnable);
            }
            else {
                runnable.run();
            }
        }
    }
    
    static {
        executor = Executors.newFixedThreadPool(Enums.HackType.values().length);
        Threads.enabled = (Runtime.getRuntime().availableProcessors() > 1);
    }
}
