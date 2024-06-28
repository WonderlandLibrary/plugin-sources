package me.vagdedes.spartan.c;

import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class ViaRewind
{
    public ViaRewind() {
        super();
    }
    
    public static boolean isLoaded() {
        final Compatibility compatibility = new Compatibility("ViaRewind");
        return compatibility.isEnabled() && (PluginUtils.exists("viarewind") || PluginUtils.exists("viarewind-legacy-compatibility") || compatibility.c());
    }
}
