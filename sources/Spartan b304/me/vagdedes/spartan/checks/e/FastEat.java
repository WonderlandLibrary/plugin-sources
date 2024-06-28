package me.vagdedes.spartan.checks.e;

import org.bukkit.Difficulty;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.HackPrevention;
import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastEat
{
    private static final Enums.HackType a;
    private static final long e = 1000L;
    private static final long f = 600L;
    private static final int ticks = 25;
    
    public FastEat() {
        super();
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final int n) {
        if (!b(spartanPlayer) || n <= spartanPlayer.getFoodLevel()) {
            return;
        }
        if (v(spartanPlayer) || w(spartanPlayer)) {
            final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
            player.setFoodLevel(spartanPlayer.getFoodLevel() - (n - spartanPlayer.getFoodLevel()));
            if (!CooldownUtils.g(spartanPlayer, FastEat.a.toString() + "=regeneration")) {
                player.removePotionEffect(PotionEffectType.REGENERATION);
            }
            if (!CooldownUtils.g(spartanPlayer, FastEat.a.toString() + "=absorption")) {
                player.removePotionEffect(PotionEffectType.ABSORPTION);
            }
            if (!CooldownUtils.g(spartanPlayer, FastEat.a.toString() + "=resistance")) {
                player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            }
        }
    }
    
    private static boolean v(final SpartanPlayer spartanPlayer) {
        final boolean b = false;
        final long a = MillisUtils.a(spartanPlayer, FastEat.a.toString() + "=time");
        if (a <= ((Register.v1_13 && spartanPlayer.getItemInHand().getType() == Material.DRIED_KELP) ? 600L : 1000L)) {
            new HackPrevention(spartanPlayer, FastEat.a, "t: eat, ms: " + a);
        }
        MillisUtils.o(spartanPlayer, FastEat.a.toString() + "=time");
        return b;
    }
    
    private static boolean w(final SpartanPlayer spartanPlayer) {
        final boolean b = false;
        final long a = MillisUtils.a(spartanPlayer, FastEat.a.toString() + "=food");
        final long lng = (Register.v1_13 && spartanPlayer.getItemInHand().getType() == Material.DRIED_KELP) ? 600L : 1000L;
        if (a <= lng) {
            new HackPrevention(spartanPlayer, FastEat.a, "t: interact, ms: " + a + ", d: " + lng);
        }
        return b;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (!b(spartanPlayer)) {
            return;
        }
        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (spartanBlock.getType() == MaterialUtils.a("cake")) {
                VL.a(spartanPlayer, FastEat.a, 10);
            }
        }
        else if (action == Action.RIGHT_CLICK_AIR && PlayerData.aP(spartanPlayer)) {
            y(spartanPlayer);
            MillisUtils.o(spartanPlayer, FastEat.a.toString() + "=food");
        }
    }
    
    private static void y(final SpartanPlayer spartanPlayer) {
        if (spartanPlayer.getItemInHand() != null && spartanPlayer.getItemInHand().getType() == Material.GOLDEN_APPLE) {
            if (!spartanPlayer.hasPotionEffect(PotionEffectType.REGENERATION)) {
                CooldownUtils.d(spartanPlayer, FastEat.a.toString() + "=regeneration", 25);
            }
            if (!spartanPlayer.hasPotionEffect(PotionEffectType.ABSORPTION)) {
                CooldownUtils.d(spartanPlayer, FastEat.a.toString() + "=absorption", 25);
            }
            if (!spartanPlayer.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
                CooldownUtils.d(spartanPlayer, FastEat.a.toString() + "=resistance", 25);
            }
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, FastEat.a, true) && spartanPlayer.getWorld().getDifficulty() != Difficulty.PEACEFUL && !spartanPlayer.hasPotionEffect(PotionEffectType.SATURATION);
    }
    
    static {
        a = Enums.HackType.FastEat;
    }
}
