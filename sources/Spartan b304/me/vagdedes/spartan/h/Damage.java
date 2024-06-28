package me.vagdedes.spartan.h;

import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import org.bukkit.entity.Projectile;
import me.vagdedes.spartan.j.NPC;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Guardian;
import me.vagdedes.spartan.k.f.VersionUtils;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Golem;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.entity.DragonFireball;
import me.vagdedes.spartan.Register;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.event.entity.EntityDamageEvent;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Damage
{
    public Damage() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final EntityDamageEvent.DamageCause damageCause) {
        if (damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK || damageCause == EntityDamageEvent.DamageCause.PROJECTILE) {
            MillisUtils.o(spartanPlayer, "damage=protection=last-combat-interaction");
        }
        MillisUtils.o(spartanPlayer, "damage=protection=last-receive-damage");
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final Entity entity) {
        if (entity instanceof EnderDragon || (Register.v1_9 && entity instanceof DragonFireball)) {
            int n = 60;
            if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) {
                n *= 2;
            }
            Velocity.g(spartanPlayer, n);
            g(spartanPlayer, n);
        }
        else if (entity instanceof Golem || entity instanceof IronGolem) {
            g(spartanPlayer, 100);
        }
        else if (entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId());
            if (a != null) {
                if (a.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK) > 2) {
                    g(spartanPlayer, 60);
                    Velocity.g(spartanPlayer, 60);
                }
                else if (a.getItemInHand().containsEnchantment(Enchantment.KNOCKBACK)) {
                    g(spartanPlayer, 60);
                    Knockback.g(spartanPlayer, 60);
                }
                else {
                    g(spartanPlayer, 40);
                }
            }
        }
        else {
            g(spartanPlayer, 30);
        }
    }
    
    public static boolean a(final Entity entity, final Entity entity2, final EntityDamageEvent.DamageCause damageCause) {
        if (VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.d && VersionUtils.a() != VersionUtils.a.e && VersionUtils.a() != VersionUtils.a.f && entity instanceof Player && damageCause == EntityDamageEvent.DamageCause.ENTITY_ATTACK && (entity2 instanceof Guardian || entity2 instanceof ElderGuardian)) {
            final Player player = (Player)entity;
            if (NPC.is(player)) {
                return false;
            }
            g(SpartanBukkit.a(player.getUniqueId()), 40);
        }
        else if (entity instanceof Projectile && entity2 instanceof Player && damageCause == EntityDamageEvent.DamageCause.PROJECTILE) {
            final Player player2 = (Player)entity2;
            if (NPC.is(player2)) {
                return false;
            }
            final SpartanPlayer a = SpartanBukkit.a(player2.getUniqueId());
            final Projectile projectile = (Projectile)entity;
            if (projectile.getShooter() instanceof Player) {
                final SpartanPlayer a2 = SpartanBukkit.a(((Player)projectile.getShooter()).getUniqueId());
                if (a2.getItemInHand().containsEnchantment(Enchantment.ARROW_KNOCKBACK) || a2.getItemInHand().containsEnchantment(Enchantment.KNOCKBACK)) {
                    boolean b = true;
                    if (a2.equals(a)) {
                        if (PlayerData.i(a, 0.0, 0.0, 0.0) || PlayerData.i(a, 0.0, -1.0, 0.0) || PlayerData.i(a, 0.0, -2.0, 0.0)) {
                            AttemptUtils.m(a, "damage=protection=projectile");
                        }
                        else {
                            final int n = AttemptUtils.a(a, "damage=protection=projectile") + 1;
                            AttemptUtils.c(a, "damage=protection=projectile", n);
                            if (n >= 5 && !CooldownUtils.g(a, "damage=protection=projectile")) {
                                b = false;
                            }
                        }
                        CooldownUtils.d(a, "damage=protection=projectile", 40);
                    }
                    if (b) {
                        g(a, 60);
                        if (a2.getItemInHand().getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) > 2 || a2.getItemInHand().getEnchantmentLevel(Enchantment.KNOCKBACK) > 2) {
                            Velocity.g(a, 60);
                        }
                        else {
                            Knockback.g(a, 60);
                        }
                        BowProtection.g(a, 100);
                    }
                }
                else if (!a2.equals(a)) {
                    g(a, 40);
                }
                else if (!SelfHit.a(a, entity, 25)) {
                    return Settings.canDo("avoid_self_bow_damage");
                }
            }
            else if (projectile.getShooter() != null) {
                if (projectile.getShooter() instanceof EnderDragon) {
                    Velocity.g(a, 40);
                    g(a, 60);
                }
                else {
                    g(a, 30);
                }
            }
            else {
                PlayerData.N(a);
                SelfHit.a(a, null, 25);
            }
        }
        return false;
    }
    
    public static void r(final SpartanPlayer spartanPlayer) {
        if (!CooldownUtils.g(spartanPlayer, "damage=protection=projectile")) {
            for (int i = 0; i <= 2; ++i) {
                if (PlayerData.i(spartanPlayer, 0.0, -i, 0.0)) {
                    AttemptUtils.m(spartanPlayer, "damage=protection=projectile");
                    return;
                }
            }
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "damage=protection");
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "damage=protection", n);
    }
    
    public static long d(final SpartanPlayer spartanPlayer) {
        return MillisUtils.a(spartanPlayer, "damage=protection=last-combat-interaction");
    }
    
    public static double e(final SpartanPlayer spartanPlayer) {
        return (MillisUtils.a(spartanPlayer, "damage=protection=last-receive-damage") <= 105L) ? spartanPlayer.getLastDamage() : 0.0;
    }
}
