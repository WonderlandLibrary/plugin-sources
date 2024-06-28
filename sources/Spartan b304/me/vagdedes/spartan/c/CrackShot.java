package me.vagdedes.spartan.c;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import com.shampaggon.crackshot.events.WeaponShootEvent;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import com.shampaggon.crackshot.events.WeaponPreShootEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import com.shampaggon.crackshot.events.WeaponScopeEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import org.bukkit.event.Listener;

public class CrackShot implements Listener
{
    private static boolean enabled;
    
    public CrackShot() {
        super();
    }
    
    public static void reload() {
        final Compatibility compatibility = new Compatibility("CrackShot");
        if (!CrackShot.enabled && compatibility.isEnabled() && (PluginUtils.exists("crackshot") || compatibility.c())) {
            Register.enable(new CrackShot(), (Class)CrackShot.class);
            CrackShot.enabled = true;
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    private void a(final WeaponScopeEvent weaponScopeEvent) {
        final SpartanPlayer a = SpartanBukkit.a(weaponScopeEvent.getPlayer().getUniqueId());
        if (!weaponScopeEvent.isCancelled()) {
            CheckProtection.h(a, 20);
            if (weaponScopeEvent.isZoomIn()) {
                AttemptUtils.c(a, "crackshot=compatibility=scope", 1);
            }
            else {
                AttemptUtils.m(a, "crackshot=compatibility=scope");
            }
        }
        else {
            AttemptUtils.m(a, "crackshot=compatibility=scope");
        }
    }
    
    @EventHandler
    private void a(final WeaponPreShootEvent weaponPreShootEvent) {
        final SpartanPlayer a = SpartanBukkit.a(weaponPreShootEvent.getPlayer().getUniqueId());
        VL.a(a, Enums.HackType.KillAura, 40);
        CheckProtection.h(a, 40);
    }
    
    @EventHandler
    private void a(final WeaponShootEvent weaponShootEvent) {
        final SpartanPlayer a = SpartanBukkit.a(weaponShootEvent.getPlayer().getUniqueId());
        VL.a(a, Enums.HackType.KillAura, 40);
        CheckProtection.h(a, 40);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final WeaponDamageEntityEvent weaponDamageEntityEvent) {
        final Entity victim = weaponDamageEntityEvent.getVictim();
        if (victim instanceof Player) {
            CheckProtection.h(SpartanBukkit.a(victim.getUniqueId()), 60);
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
        return AttemptUtils.a(spartanPlayer, "crackshot=compatibility=scope") != 0 || CrackShotPlus.A(spartanPlayer);
    }
    
    static {
        CrackShot.enabled = false;
    }
}
