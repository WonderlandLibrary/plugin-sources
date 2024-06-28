package me.vagdedes.spartan.k.g;

public class PluginTicks
{
    private static int ticks;
    private static final int max = 3600;
    
    public PluginTicks() {
        super();
    }
    
    public static void run() {
        if (PluginTicks.ticks < 3600) {
            ++PluginTicks.ticks;
        }
    }
    
    public static int get() {
        return PluginTicks.ticks;
    }
    
    public static void reset() {
        PluginTicks.ticks = 0;
    }
    
    static {
        PluginTicks.ticks = 0;
    }
}
