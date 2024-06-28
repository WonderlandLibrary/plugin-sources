package me.vagdedes.spartan.checks.f;

import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.f.LatencyUtils;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.c.SuperPickaxe;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.c.MineBomb;
import me.vagdedes.spartan.c.TreeFeller;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Nuker
{
    private static final Enums.HackType a;
    
    public Nuker() {
        super();
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (!b(spartanPlayer) || ClientSidedBlock.a(spartanPlayer, spartanBlock.a()) != null || TreeFeller.b(spartanPlayer, spartanBlock) || MineBomb.B(spartanPlayer)) {
            return;
        }
        final int integer = Checks.getInteger("Nuker.cancel_seconds");
        final int n = (integer > 60) ? 60 : integer;
        a(spartanPlayer, spartanBlock, n * 20);
        b(spartanPlayer, spartanBlock, n * 20);
        c(spartanPlayer, spartanBlock, n * 20);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final int n) {
        if (BlockUtils.E(spartanPlayer, spartanBlock.a()) || z(spartanPlayer) || !Checks.getBoolean("Nuker.check_delay") || SuperPickaxe.G(spartanPlayer)) {
            return;
        }
        final ItemStack itemInHand = spartanPlayer.getItemInHand();
        final int enchantmentLevel = itemInHand.getEnchantmentLevel(Enchantment.DIG_SPEED);
        if (enchantmentLevel > 5) {
            return;
        }
        final boolean b = enchantmentLevel >= 3;
        if (b && !a(itemInHand.getType())) {
            return;
        }
        long lng = 50L;
        int n2 = 1;
        int n3 = 1;
        if (spartanPlayer.getGameMode() == GameMode.CREATIVE) {
            lng = 40L;
            n2 = 4;
            n3 = 3;
        }
        else if (b) {
            lng = 30L;
        }
        final long a = MillisUtils.a(spartanPlayer, Nuker.a.toString() + "=time");
        if (a < lng && AttemptUtils.b(spartanPlayer, Nuker.a.toString() + "=attempts", n2) >= n3) {
            new HackPrevention(spartanPlayer, Nuker.a, "t: delay, ms: " + a + ", l: " + lng + ", b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock), n);
        }
        MillisUtils.o(spartanPlayer, Nuker.a.toString() + "=time");
    }
    
    private static void b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final int n) {
        if (!Checks.getBoolean("Nuker.check_breaks_per_sec")) {
            return;
        }
        final int n2 = 20;
        final int b = AttemptUtils.b(spartanPlayer, Nuker.a.toString() + "=breaks-per-second", n2);
        final int integer = Checks.getInteger("Nuker.max_breaks_per_second");
        final int a = BlockUtils.E(spartanBlock.getType()) ? 75 : ((int)Math.round((double)((integer < 10) ? 10 : integer) * (z(spartanPlayer) ? 1.65 : 1.0)));
        if (a < n2 && BlockUtils.E(spartanBlock.getType())) {
            return;
        }
        if (b >= (LatencyUtils.e(spartanPlayer, 200) ? Math.max(a, 100) : a)) {
            new HackPrevention(spartanPlayer, Nuker.a, "t: breaks-per-sec, b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock) + ", a: " + b, n);
        }
    }
    
    private static void c(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final int n) {
        if (BlockUtils.E(spartanPlayer, spartanBlock.a()) || spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) >= 3 || z(spartanPlayer) || !Checks.getBoolean("Nuker.check_moves_in_between")) {
            return;
        }
        final String string = Nuker.a.toString() + "=moves-in-between=";
        final int a = AttemptUtils.a(spartanPlayer, string + "moves");
        AttemptUtils.m(spartanPlayer, string + "moves");
        if (a > 0) {
            final int a2 = AttemptUtils.a(spartanPlayer, string + "average", a);
            final int a3 = AttemptUtils.a(spartanPlayer, string + "count", 1);
            if (a3 >= 10) {
                AttemptUtils.b(spartanPlayer, new String[] { string + "average", string + "count" });
                final double d = a2 / (double)a3;
                if (d <= 1.5) {
                    new HackPrevention(spartanPlayer, Nuker.a, "t: moves-in-between, b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock) + ", a: " + d, n);
                }
            }
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation, final SpartanLocation spartanLocation2) {
        if (spartanLocation.getYaw() != spartanLocation2.getYaw() || spartanLocation.getPitch() != spartanLocation2.getPitch()) {
            AttemptUtils.a(spartanPlayer, Nuker.a.toString() + "=moves-in-between=moves", 1);
        }
    }
    
    private static boolean z(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.getItemInHand().getType() == Material.SHEARS || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("diamond_spade") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("iron_spade") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_spade") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("stone_spade") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_spade") || (spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) >= 4 && (spartanPlayer.getItemInHand().getType() == Material.DIAMOND_AXE || spartanPlayer.getItemInHand().getType() == Material.IRON_AXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_axe") || spartanPlayer.getItemInHand().getType() == Material.STONE_AXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_axe"))) || (mcMMO.isLoaded() && (spartanPlayer.getItemInHand().getType() == Material.DIAMOND_PICKAXE || spartanPlayer.getItemInHand().getType() == Material.IRON_PICKAXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_pickaxe") || spartanPlayer.getItemInHand().getType() == Material.STONE_PICKAXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_pickaxe")));
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, Nuker.a, true) && !spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) && (spartanPlayer.getGameMode() == GameMode.SURVIVAL || spartanPlayer.getGameMode() == GameMode.ADVENTURE || spartanPlayer.getGameMode() == GameMode.CREATIVE);
    }
    
    public static boolean a(final Material material) {
        return material == Material.DIAMOND_PICKAXE || material == Material.IRON_PICKAXE || material == Material.STONE_PICKAXE || material == MaterialUtils.a("gold_pickaxe") || material == MaterialUtils.a("wood_pickaxe");
    }
    
    static {
        a = Enums.HackType.Nuker;
    }
}
