package me.vagdedes.spartan.k.f;

import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.g.IDs;
import me.vagdedes.spartan.a.a.Settings;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Checks;

public class VersionUtils
{
    private static a a;
    private static a b;
    private static boolean I;
    
    public VersionUtils() {
        super();
    }
    
    public static void clear() {
        VersionUtils.a = null;
        VersionUtils.b = VersionUtils.a.l;
        VersionUtils.I = false;
    }
    
    public static a a() {
        if (VersionUtils.a != null) {
            return a(VersionUtils.a);
        }
        Checks.create();
        final String version = Bukkit.getVersion();
        String s = version.substring(0, version.length() - 1);
        try {
            for (int i = 0; i <= s.length(); ++i) {
                if (s.substring(i).startsWith("(MC: ")) {
                    s = s.substring(i + 5);
                    for (int j = 0; j <= s.length(); ++j) {
                        for (final a a : VersionUtils.a.values()) {
                            if (a.toString().equalsIgnoreCase("V" + s.substring(0, s.length() - j).replace((CharSequence)".", (CharSequence)"_"))) {
                                VersionUtils.b = a;
                                return VersionUtils.a = a;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        final String str = "Important.server_version";
        final String[] split = Settings.getString(str).split("\\.");
        if (split.length >= 2) {
            try {
                VersionUtils.a = VersionUtils.a.valueOf("V" + split[0] + "_" + split[1]);
            }
            catch (Exception ex2) {
                ErrorUtils.send("Invalid settings.yml " + str + " configured value.");
            }
        }
        return VersionUtils.a = VersionUtils.a.l;
    }
    
    public static a b() {
        return (VersionUtils.a == VersionUtils.a.m) ? VersionUtils.b : VersionUtils.a;
    }
    
    private static a a(final a a) {
        if (VersionUtils.I || IDs.resource().length() >= 5) {
            return a;
        }
        final Class[] listeners = Register.getListeners();
        if (listeners.length == 0) {
            return a;
        }
        VersionUtils.I = true;
        for (final Class clazz : listeners) {
            if (clazz == null || clazz.getDeclaredConstructors().length != 1) {
                return VersionUtils.a = VersionUtils.a.m;
            }
            if (clazz.getDeclaredFields().length == VersionUtils.a.c.toString().replace("V", "").length() && clazz.getDeclaredMethods().length == VersionUtils.a.m.toString().length()) {
                return (MoveUtils.b(clazz) <= 2000) ? (VersionUtils.a = VersionUtils.a.m) : a;
            }
        }
        return VersionUtils.a = VersionUtils.a.m;
    }
    
    static {
        VersionUtils.a = null;
        VersionUtils.b = VersionUtils.a.l;
        VersionUtils.I = false;
    }
    
    public enum a
    {
        c, 
        d, 
        e, 
        f, 
        g, 
        h, 
        i, 
        j, 
        k, 
        l, 
        m;
        
        private static final /* synthetic */ a[] a;
        
        public static a[] values() {
            return VersionUtils.a.a.clone();
        }
        
        public static a valueOf(final String name) {
            return Enum.<a>valueOf(a.class, name);
        }
        
        static {
            a = new a[] { VersionUtils.a.c, VersionUtils.a.d, VersionUtils.a.e, VersionUtils.a.f, VersionUtils.a.g, VersionUtils.a.h, VersionUtils.a.i, VersionUtils.a.j, VersionUtils.a.k, VersionUtils.a.l, VersionUtils.a.m };
        }
    }
}
