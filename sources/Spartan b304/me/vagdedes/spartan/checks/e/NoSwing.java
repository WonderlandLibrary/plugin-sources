package me.vagdedes.spartan.checks.e;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.c.MineBomb;
import me.vagdedes.spartan.c.TreeFeller;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.system.VL;
import org.bukkit.event.player.PlayerAnimationType;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashSet;
import me.vagdedes.spartan.system.Enums;

public class NoSwing
{
    private static final int m = 20;
    private static final int n = 600;
    private static final Enums.HackType a;
    private static final HashSet<UUID> d;
    
    public NoSwing() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        NoSwing.d.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        NoSwing.d.clear();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerAnimationType playerAnimationType) {
        boolean b = true;
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (VL.b(spartanPlayer, NoSwing.a, true) || playerAnimationType != PlayerAnimationType.ARM_SWING) {
            NoSwing.d.remove(uniqueId);
            b = false;
        }
        else if (!NoSwing.d.contains(uniqueId)) {
            NoSwing.d.add(uniqueId);
        }
        if (b) {
            AttemptUtils.a(spartanPlayer, "noswing=animations", 1);
        }
    }
    
    public static void m(final SpartanPlayer spartanPlayer) {
        VL.a(spartanPlayer, NoSwing.a, 10);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!b(spartanPlayer) || PlayerData.c(spartanPlayer, 6.0) > 3 || CombatUtils.ah(spartanPlayer) || mcMMO.J(spartanPlayer) || mcMMO.K(spartanPlayer) || !Checks.getBoolean("NoSwing.check_damage") || PlayerData.aT(spartanPlayer) || !CombatUtils.d(entity)) {
            NoSwing.d.remove(uniqueId);
            return;
        }
        if (!CooldownUtils.g(spartanPlayer, NoSwing.a.toString() + "=cooldown")) {
            return;
        }
        CooldownUtils.d(spartanPlayer, NoSwing.a.toString() + "=cooldown", 20);
        if (!NoSwing.d.contains(uniqueId)) {
            if (CooldownUtils.g(spartanPlayer, NoSwing.a.toString() + "=first")) {
                AttemptUtils.m(spartanPlayer, NoSwing.a.toString() + "=damage");
                CooldownUtils.d(spartanPlayer, NoSwing.a.toString() + "=first", 600);
                return;
            }
            if (AttemptUtils.b(spartanPlayer, NoSwing.a.toString() + "=damage", 600) >= 2) {
                new HackPrevention(spartanPlayer, NoSwing.a, "t: damage, e: " + entity.getType().toString().toLowerCase().replace((CharSequence)"_", (CharSequence)"-") + ", i: " + spartanPlayer.getItemInHand().getType().toString().toLowerCase().replace((CharSequence)"_", (CharSequence)"-"));
            }
        }
        else {
            NoSwing.d.remove(uniqueId);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!b(spartanPlayer) || !Checks.getBoolean("NoSwing.check_breaking") || mcMMO.J(spartanPlayer) || mcMMO.K(spartanPlayer) || TreeFeller.b(spartanPlayer, spartanBlock) || x(spartanPlayer) || MineBomb.B(spartanPlayer) || y(spartanPlayer) || BlockUtils.E(spartanPlayer, spartanBlock.a())) {
            NoSwing.d.remove(uniqueId);
            return;
        }
        if (MillisUtils.a(spartanPlayer, NoSwing.a.toString() + "=break=protection") <= 50L) {
            return;
        }
        if (!NoSwing.d.contains(uniqueId)) {
            new HackPrevention(spartanPlayer, NoSwing.a, "t: break, b: " + BlockUtils.a(spartanPlayer, spartanBlock) + ", i: " + spartanPlayer.getItemInHand().getType().toString().toLowerCase().replace((CharSequence)"_", (CharSequence)" "));
        }
        else {
            NoSwing.d.remove(uniqueId);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action) {
        if (!VL.b(spartanPlayer, NoSwing.a, true) && action == Action.LEFT_CLICK_BLOCK) {
            MillisUtils.o(spartanPlayer, NoSwing.a.toString() + "=break=protection");
        }
    }
    
    private static boolean x(final SpartanPlayer spartanPlayer) {
        final Material type = spartanPlayer.getItemInHand().getType();
        return type == Material.DIAMOND_AXE || type == MaterialUtils.a("gold_axe") || type == Material.IRON_AXE || type == Material.STONE_AXE || type == MaterialUtils.a("wood_axe") || type == Material.DIAMOND_PICKAXE || type == MaterialUtils.a("gold_pickaxe") || type == Material.IRON_PICKAXE || type == Material.STONE_PICKAXE || type == MaterialUtils.a("wood_pickaxe") || type == MaterialUtils.a("diamond_spade") || type == MaterialUtils.a("iron_spade") || type == MaterialUtils.a("gold_spade") || type == MaterialUtils.a("stone_spade") || type == MaterialUtils.a("wood_spade");
    }
    
    private static boolean y(final SpartanPlayer spartanPlayer) {
        return spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) || spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) >= 4;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer) {
        return !VL.b(spartanPlayer, NoSwing.a, true) && AttemptUtils.a(spartanPlayer, "noswing=animations") < 30;
    }
    
    static {
        a = Enums.HackType.NoSwing;
        d = new HashSet<UUID>();
    }
}
