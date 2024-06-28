package me.vagdedes.spartan.checks.combat.b;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;

public class SavedLocation
{
    private static final HashMap<UUID, SpartanLocation> s;
    
    public SavedLocation() {
        super();
    }
    
    public static void clear() {
        SavedLocation.s.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        SavedLocation.s.remove(spartanPlayer.getUniqueId());
    }
    
    public static SpartanLocation a(final SpartanPlayer spartanPlayer) {
        return SavedLocation.s.get(spartanPlayer.getUniqueId());
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        SavedLocation.s.put(spartanPlayer.getUniqueId(), spartanPlayer.a());
    }
    
    static {
        s = new HashMap<UUID, SpartanLocation>();
    }
}
