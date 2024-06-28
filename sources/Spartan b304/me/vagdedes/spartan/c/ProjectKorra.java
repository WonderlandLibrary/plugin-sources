package me.vagdedes.spartan.c;

import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import com.projectkorra.projectkorra.event.AbilityDamageEntityEvent;
import com.projectkorra.projectkorra.event.AbilityProgressEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.SpartanPlayer;
import com.projectkorra.projectkorra.event.AbilityStartEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class ProjectKorra implements Listener
{
    private static boolean enabled;
    
    public ProjectKorra() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("ProjectKorra");
        if (!ProjectKorra.enabled && compatibility.isEnabled() && (PluginUtils.exists("projectkorra") || compatibility.c())) {
            Register.enable(new ProjectKorra(), (Class)ProjectKorra.class);
            ProjectKorra.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final AbilityStartEvent abilityStartEvent) {
        CheckProtection.h(new SpartanPlayer(abilityStartEvent.getAbility().getPlayer()), 60);
    }
    
    @EventHandler
    private void a(final AbilityProgressEvent abilityProgressEvent) {
        CheckProtection.h(new SpartanPlayer(abilityProgressEvent.getAbility().getPlayer()), 40);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final AbilityDamageEntityEvent abilityDamageEntityEvent) {
        final Entity entity = abilityDamageEntityEvent.getEntity();
        if (entity instanceof Player) {
            CheckProtection.h(SpartanBukkit.a(entity.getUniqueId()), 60);
        }
    }
    
    static {
        ProjectKorra.enabled = false;
    }
}
