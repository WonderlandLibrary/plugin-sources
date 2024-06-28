package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.c.MineBomb;
import me.vagdedes.spartan.k.f.TPS;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.GameMode;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.Register;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.f.SpartanBlock;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class FastBreak
{
    private static final Enums.HackType a;
    
    public FastBreak() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action, final SpartanBlock obj) {
        if (action == Action.LEFT_CLICK_BLOCK && obj != null) {
            MillisUtils.o(spartanPlayer, FastBreak.a.toString() + "=" + obj);
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final SpartanBlock obj) {
        if (!b(spartanPlayer) || ClientSidedBlock.a(spartanPlayer, obj.a()) != null || BlockUtils.E(spartanPlayer, obj.a())) {
            return;
        }
        final long a = MillisUtils.a(spartanPlayer, FastBreak.a.toString() + "=" + obj);
        if (MillisUtils.hasTimer(a)) {
            final Material type = obj.getType();
            long lng = Checks.getBoolean("FastBreak.check_surface_blocks") ? ((BlockUtils.x(null, obj.a()) || type == MaterialUtils.a("grass_block") || type == MaterialUtils.a("soil") || type == Material.DIRT || (Register.v1_9 && type == Material.GRASS_PATH)) ? 150L : 300L) : 0L;
            if (spartanPlayer.getItemInHand() != null) {
                if (spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_axe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_pickaxe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_hoe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("wood_shovel")) {
                    lng /= ((type == Material.SNOW || type == Material.SNOW_BLOCK) ? 4L : 2L);
                }
                else if (spartanPlayer.getItemInHand().getType() == Material.STONE_AXE || spartanPlayer.getItemInHand().getType() == Material.STONE_PICKAXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("stone_hoe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("stone_shovel")) {
                    lng /= 4L;
                }
                else if (spartanPlayer.getItemInHand().getType() == Material.IRON_AXE || spartanPlayer.getItemInHand().getType() == Material.IRON_PICKAXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("iron_hoe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("iron_shovel")) {
                    lng /= 6L;
                }
                else if (spartanPlayer.getItemInHand().getType() == Material.DIAMOND_AXE || spartanPlayer.getItemInHand().getType() == Material.DIAMOND_PICKAXE || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("diamond_hoe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("diamond_shovel")) {
                    lng /= 8L;
                }
                else if (spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_axe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_pickaxe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_hoe") || spartanPlayer.getItemInHand().getType() == MaterialUtils.a("gold_shovel")) {
                    lng /= 12L;
                }
                else if (spartanPlayer.getItemInHand().getType() == Material.SHEARS || PlayerData.aB(spartanPlayer)) {
                    lng /= 15L;
                }
            }
            if (a > 5L && lng > 5L && a < lng && lng - a >= 20L) {
                boolean b = false;
                if (spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) > 0 || spartanPlayer.getItemInHand().getType() == Material.SHEARS) {
                    if (AttemptUtils.b(spartanPlayer, FastBreak.a.toString() + "=attempts", 20) >= 2) {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
                if (b) {
                    final int integer = Checks.getInteger("FastBreak.cancel_seconds");
                    new HackPrevention(spartanPlayer, FastBreak.a, "ms: " + a + ", l: " + lng + ", b: " + BlockUtils.a((SpartanPlayer)null, obj), ((integer > 60) ? 60 : integer) * 20);
                }
            }
        }
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, FastBreak.a, true) && (spartanPlayer.getGameMode() == GameMode.SURVIVAL || spartanPlayer.getGameMode() == GameMode.ADVENTURE) && !spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) && spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) <= 4 && !TPS.u() && !MineBomb.B(spartanPlayer);
    }
    
    static {
        a = Enums.HackType.FastBreak;
    }
}
