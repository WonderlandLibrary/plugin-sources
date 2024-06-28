package me.vagdedes.spartan.c;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import com.snowgears.grapplinghook.api.HookAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;

public class GrapplingHook
{
    private static boolean enabled;
    
    public GrapplingHook() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("GrapplingHook");
        if (!GrapplingHook.enabled && compatibility.isEnabled() && (PluginUtils.exists("grapplinghook") || compatibility.c())) {
            Register.enable(new AcidRain(), (Class)GrapplingHook.class);
            GrapplingHook.enabled = true;
        }
    }
    
    public static boolean a(final ItemStack itemStack) {
        if (GrapplingHook.enabled) {
            try {
                return HookAPI.isGrapplingHook(itemStack);
            }
            catch (Exception ex) {}
        }
        return false;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerFishEvent playerFishEvent) {
        if (playerFishEvent.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            final Entity caught = playerFishEvent.getCaught();
            if (caught instanceof Player) {
                final SpartanPlayer a = SpartanBukkit.a(caught.getUniqueId());
                final Player player = playerFishEvent.getPlayer();
                if (!a.equals(player) && a(player.getItemInHand())) {
                    CheckProtection.h(a, 40);
                }
            }
        }
    }
    
    static {
        GrapplingHook.enabled = false;
    }
}
