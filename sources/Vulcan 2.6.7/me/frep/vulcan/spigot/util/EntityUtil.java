package me.frep.vulcan.spigot.util;

import org.bukkit.entity.Entity;

public final class EntityUtil
{
    public static boolean isShulker(final Entity entity) {
        return entity.getType().toString().equals("SHULKER");
    }
    
    public static boolean isBoat(final Entity entity) {
        return entity.getType().toString().contains("BOAT");
    }
    
    private EntityUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
