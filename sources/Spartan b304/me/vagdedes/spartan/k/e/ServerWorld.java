package me.vagdedes.spartan.k.e;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class ServerWorld
{
    public ServerWorld() {
        super();
    }
    
    public static int a(final World world) {
        int n = -1;
        for (final World obj : Bukkit.getWorlds()) {
            ++n;
            if (world.equals(obj)) {
                break;
            }
        }
        return n;
    }
    
    public static World a(final int n) {
        int n2 = -1;
        for (final World world : Bukkit.getWorlds()) {
            if (++n2 == n) {
                return world;
            }
        }
        return null;
    }
}
