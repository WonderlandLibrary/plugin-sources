package me.vagdedes.spartan.checks.f;

import java.util.HashMap;
import me.vagdedes.spartan.features.syn.MiningHistory;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.HackPrevention;
import java.util.ArrayList;
import org.bukkit.WorldType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.features.b.SearchEngine;
import org.bukkit.GameMode;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class XRay
{
    private static final Enums.HackType a;
    
    public XRay() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Material material) {
        if (!MoveUtils.b(spartanPlayer) || spartanPlayer.getGameMode() != GameMode.SURVIVAL || !SearchEngine.d() || spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) || spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) > 5 || spartanPlayer.getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS) || spartanPlayer.a() == null || spartanPlayer.a().getBlockY() > 32 || spartanPlayer.getWorld().getWorldType() == WorldType.FLAT) {
            return;
        }
        if (SearchEngine.a(material) <= 0.0) {
            return;
        }
        if (((material == Material.GOLD_ORE) ? 3.0 : ((material == Material.DIAMOND) ? 2.0 : ((material == Material.EMERALD) ? 1.75 : Double.MAX_VALUE))) == Double.MAX_VALUE) {
            return;
        }
        final MiningHistory miningHistory;
        final HashMap<Material, Integer> hashMap;
        final HashMap<Material, ArrayList<String>> hashMap2;
        double d;
        final double d2;
        final double n;
        final Runnable runnable = () -> {
            SearchEngine.a(spartanPlayer.getName());
            miningHistory.a();
            miningHistory.b();
            if (hashMap.containsKey(material) && hashMap2.containsKey(material)) {
                d = Integer.valueOf(hashMap.get((Object)material)) / (double)((ArrayList<String>)hashMap2.get(material)).size();
                if (d >= 16.0 && d >= d2 * n) {
                    new HackPrevention(spartanPlayer, XRay.a, "m: " + BlockUtils.a(material) + ", a: " + d + ", ga: " + d2);
                }
            }
            return;
        };
        if (Settings.canDo("Detections.run_asynchronously")) {
            Threads.a(spartanPlayer, runnable);
        }
        else {
            runnable.run();
        }
    }
    
    private static /* synthetic */ void a(final SpartanPlayer spartanPlayer, final Material material, final double d, final double n) {
        final MiningHistory a = SearchEngine.a(spartanPlayer.getName());
        final HashMap<Material, Integer> a2 = a.a();
        final HashMap<Material, ArrayList<String>> b = a.b();
        if (a2.containsKey(material) && b.containsKey(material)) {
            final double d2 = Integer.valueOf(a2.get((Object)material)) / (double)((ArrayList<String>)b.get(material)).size();
            if (d2 >= 16.0 && d2 >= d * n) {
                new HackPrevention(spartanPlayer, XRay.a, "m: " + BlockUtils.a(material) + ", a: " + d2 + ", ga: " + d);
            }
        }
    }
    
    static {
        a = Enums.HackType.XRay;
    }
}
