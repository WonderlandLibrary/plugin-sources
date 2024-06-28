package me.vagdedes.spartan.k.b;

import org.bukkit.Bukkit;
import java.lang.reflect.Field;
import org.bukkit.entity.Player;

public class ReflectionUtils
{
    public static final String packages;
    public static final String version;
    private static final Class c;
    
    public ReflectionUtils() {
        super();
    }
    
    public static Class getClass(final String str, final boolean b) {
        if (str != null) {
            try {
                return Class.forName((b ? ReflectionUtils.packages : "") + str);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static boolean e(final String className) {
        try {
            Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
        return true;
    }
    
    public static Object getCraftPlayer(final Player obj, final String name) {
        if (obj != null && name != null) {
            try {
                final Object invoke = ReflectionUtils.c.getMethod("getHandle", (Class[])new Class[0]).invoke(obj, new Object[0]);
                return invoke.getClass().getDeclaredField(name).get(invoke);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public static Object getObject(Class forName, final String name) {
        if (forName != null && name != null) {
            try {
                forName = Class.forName(ReflectionUtils.packages + forName.getName());
                final Field declaredField = forName.getDeclaredField(name);
                if (declaredField == null) {
                    return null;
                }
                declaredField.setAccessible(true);
                return declaredField.get(forName);
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    static {
        packages = ReflectionUtils.class.getPackage().getName().substring(0, 19);
        version = Bukkit.getServer().getClass().getPackage().getName().substring(23);
        c = getClass("org.bukkit.craftbukkit." + ReflectionUtils.version + ".entity.CraftPlayer", false);
    }
}
