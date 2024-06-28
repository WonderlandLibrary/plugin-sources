package me.vagdedes.spartan.c;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import com.evill4mer.RealDualWield.Api.PlayerOffhandAnimationEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class RealDualWield implements Listener
{
    private static boolean enabled;
    
    public RealDualWield() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("RealDualWield");
        if (!RealDualWield.enabled && compatibility.isEnabled() && (PluginUtils.exists("realdualwield") || compatibility.c())) {
            Register.enable(new RealDualWield(), (Class)RealDualWield.class);
            RealDualWield.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerOffhandAnimationEvent playerOffhandAnimationEvent) {
        final SpartanPlayer a = SpartanBukkit.a(playerOffhandAnimationEvent.getPlayer().getUniqueId());
        VL.a(a, Enums.HackType.KillAura, 5);
        VL.a(a, Enums.HackType.HitReach, 5);
        VL.a(a, Enums.HackType.Criticals, 5);
    }
    
    static {
        RealDualWield.enabled = false;
    }
}
