package me.vagdedes.spartan.c;

import com.wasteofplastic.acidisland.events.AcidEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.f.SpartanPlayer;
import com.wasteofplastic.acidisland.events.AcidRainEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class AcidRain implements Listener
{
    private static boolean enabled;
    
    public AcidRain() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("AcidRain");
        if (!AcidRain.enabled && compatibility.isEnabled() && (PluginUtils.exists(new String[] { "acidrain", "acidisland", "askyblock" }) || compatibility.c())) {
            Register.enable(new AcidRain(), (Class)AcidRain.class);
            AcidRain.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final AcidRainEvent acidRainEvent) {
        FloorProtection.g(new SpartanPlayer(acidRainEvent.getPlayer()), 10);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final AcidEvent acidEvent) {
        FloorProtection.g(new SpartanPlayer(acidEvent.getPlayer()), 10);
    }
    
    static {
        AcidRain.enabled = false;
    }
}
