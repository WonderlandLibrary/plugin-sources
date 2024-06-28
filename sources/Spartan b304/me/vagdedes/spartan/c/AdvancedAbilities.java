package me.vagdedes.spartan.c;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.SpartanPlayer;
import be.anybody.api.AbilityEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class AdvancedAbilities implements Listener
{
    private static boolean enabled;
    
    public AdvancedAbilities() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("AdvancedAbilities");
        if (!AdvancedAbilities.enabled && compatibility.isEnabled() && (PluginUtils.exists("advancedabilities") || compatibility.c())) {
            Register.enable(new AdvancedAbilities(), (Class)AdvancedAbilities.class);
            AdvancedAbilities.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final AbilityEvent abilityEvent) {
        CheckProtection.h(new SpartanPlayer(abilityEvent.getPlayer()), 60);
    }
    
    static {
        AdvancedAbilities.enabled = false;
    }
}
