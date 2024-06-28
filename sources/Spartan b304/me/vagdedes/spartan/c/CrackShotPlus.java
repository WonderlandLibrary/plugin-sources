package me.vagdedes.spartan.c;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.DeeCaaD.CrackShotPlus.Events.WeaponSecondScopeEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class CrackShotPlus implements Listener
{
    private static boolean enabled;
    
    public CrackShotPlus() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("CrackShotPlus");
        if (!CrackShotPlus.enabled && compatibility.isEnabled() && (PluginUtils.exists("crackshotplus") || compatibility.c())) {
            Register.enable(new CrackShotPlus(), (Class)CrackShotPlus.class);
            CrackShotPlus.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final WeaponSecondScopeEvent weaponSecondScopeEvent) {
        final SpartanPlayer a = SpartanBukkit.a(weaponSecondScopeEvent.getPlayer().getUniqueId());
        if (!weaponSecondScopeEvent.isCancelled()) {
            CheckProtection.h(a, 20);
            if (weaponSecondScopeEvent.isZoomIn()) {
                AttemptUtils.c(a, "crackshotplus=compatibility=scope", 1);
            }
            else {
                AttemptUtils.m(a, "crackshotplus=compatibility=scope");
            }
        }
        else {
            AttemptUtils.m(a, "crackshotplus=compatibility=scope");
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final EntityDamageEvent entityDamageEvent) {
        final Entity entity = entityDamageEvent.getEntity();
        if (entity instanceof Player && A(SpartanBukkit.a(entity.getUniqueId()))) {
            CheckProtection.h(SpartanBukkit.a(entity.getUniqueId()), 60);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
        final Entity damager = entityDamageByEntityEvent.getDamager();
        if (damager instanceof Player && A(SpartanBukkit.a(damager.getUniqueId()))) {
            CheckProtection.h(SpartanBukkit.a(damager.getUniqueId()), 30);
        }
    }
    
    public static boolean A(final SpartanPlayer spartanPlayer) {
        return AttemptUtils.a(spartanPlayer, "crackshotplus=compatibility=scope") != 0;
    }
    
    static {
        CrackShotPlus.enabled = false;
    }
}
