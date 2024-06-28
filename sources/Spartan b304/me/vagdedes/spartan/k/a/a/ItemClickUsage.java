package me.vagdedes.spartan.k.a.a;

import me.vagdedes.spartan.k.b.ReflectionUtils;
import java.lang.reflect.Field;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.entity.Player;

public class ItemClickUsage
{
    private static final Class a;
    private static final Class b;
    private static final Class c;
    
    public ItemClickUsage() {
        super();
    }
    
    private static float a(final float n) {
        if (n == 0.0f) {
            return 0.0f;
        }
        final float n2 = (72000.0f - n) / 20.0f;
        float n3 = (n2 * n2 + n2 * 2.0f) / 3.0f;
        if (n3 < 0.1) {
            return n3;
        }
        if (n3 > 1.0f) {
            n3 = 1.0f;
        }
        return n3;
    }
    
    private static float a(final int n) {
        if (n == 0) {
            return 0.0f;
        }
        final float n2 = (72000 - n) / 20.0f;
        float n3 = (n2 * n2 + n2 * 2.0f) / 3.0f;
        if (n3 < 0.1) {
            return n3;
        }
        if (n3 > 1.0f) {
            n3 = 1.0f;
        }
        return n3;
    }
    
    private static Object a(final Player obj) {
        final VersionUtils.a a = VersionUtils.a();
        if (a == VersionUtils.a.l) {
            return Float.valueOf(0.0f);
        }
        try {
            final Field field = (a == VersionUtils.a.c || a == VersionUtils.a.d) ? ItemClickUsage.b.getDeclaredFields()[29] : ((a == VersionUtils.a.e || a == VersionUtils.a.f) ? ItemClickUsage.a.getDeclaredFields()[66] : ((a == VersionUtils.a.g) ? ItemClickUsage.a.getDeclaredFields()[67] : ((a == VersionUtils.a.h) ? ItemClickUsage.a.getDeclaredFields()[93] : ((a == VersionUtils.a.i) ? ItemClickUsage.a.getDeclaredFields()[91] : ((a == VersionUtils.a.j) ? ItemClickUsage.a.getDeclaredFields()[105] : ItemClickUsage.a.getDeclaredFields()[68])))));
            if (field == null) {
                return null;
            }
            field.setAccessible(true);
            return field.get(ItemClickUsage.c.getDeclaredMethod("getHandle", (Class[])new Class[0]).invoke((Object)obj, new Object[0]));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static float a(final Player player) {
        if (VersionUtils.a() == VersionUtils.a.l) {
            return 0.0f;
        }
        final Object a = a(player);
        float n = 0.0f;
        if (a instanceof Integer) {
            n = a((int)a);
        }
        else if (a instanceof Float) {
            n = a((float)a);
        }
        return n;
    }
    
    static {
        a = ReflectionUtils.getClass("net.minecraft.server." + ReflectionUtils.version + ".EntityLiving", false);
        b = ReflectionUtils.getClass("net.minecraft.server." + ReflectionUtils.version + ".EntityHuman", false);
        c = ReflectionUtils.getClass("org.bukkit.craftbukkit." + ReflectionUtils.version + ".entity.CraftPlayer", false);
    }
}
