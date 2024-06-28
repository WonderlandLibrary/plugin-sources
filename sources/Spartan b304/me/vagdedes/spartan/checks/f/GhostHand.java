package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.j.ClientSidedBlock;
import org.bukkit.GameMode;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.h.BlockBreak;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.block.Block;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.CooldownUtils;
import org.bukkit.Location;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class GhostHand
{
    private static final Enums.HackType a;
    
    public GhostHand() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (spartanBlock == null || !a(spartanPlayer, spartanBlock) || !Checks.getBoolean("GhostHand.check_player_interactions") || action != Action.RIGHT_CLICK_BLOCK || (!BlockUtils.F(spartanPlayer, spartanBlock.a()) && !BlockUtils.o(spartanPlayer.getItemInHand().getType()))) {
            return;
        }
        final Block a = BlockUtils.a(spartanPlayer.getPlayer(), new Location(spartanBlock.getWorld(), (double)spartanBlock.getX(), (double)spartanBlock.getY(), (double)spartanBlock.getZ()));
        if (a != null) {
            final String string = "t: interact, c: " + BlockUtils.a((SpartanPlayer)null, spartanBlock) + ", r: " + BlockUtils.a((SpartanPlayer)null, new SpartanBlock(a));
            if (CooldownUtils.g(spartanPlayer, GhostHand.a.toString() + "=break-protection")) {
                new HackPrevention(spartanPlayer, GhostHand.a, string);
            }
            else {
                PunishUtils.a(spartanPlayer, GhostHand.a, string, false);
            }
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final boolean b) {
        if (b) {
            CooldownUtils.d(spartanPlayer, GhostHand.a.toString() + "=break-protection", 2);
        }
        else if (a(spartanPlayer, spartanBlock) && Checks.getBoolean("GhostHand.check_block_breaking") && spartanPlayer.getItemInHand().getEnchantmentLevel(Enchantment.DIG_SPEED) <= 2 && !spartanPlayer.hasPotionEffect(PotionEffectType.FAST_DIGGING) && (!BlockBreak.E(spartanPlayer) || BlockUtils.F(spartanPlayer, spartanBlock.a()))) {
            final Block a = BlockUtils.a(spartanPlayer.getPlayer(), new Location(spartanBlock.getWorld(), (double)spartanBlock.getX(), (double)spartanBlock.getY(), (double)spartanBlock.getZ()));
            if (a != null) {
                new HackPrevention(spartanPlayer, GhostHand.a, "t: break, c: " + BlockUtils.a((SpartanPlayer)null, spartanBlock) + ", r: " + BlockUtils.a((SpartanPlayer)null, new SpartanBlock(a)), 10);
            }
        }
    }
    
    private static boolean a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        return !VL.b(spartanPlayer, GhostHand.a, true) && !spartanPlayer.isFlying() && spartanBlock.getWorld() == spartanPlayer.getWorld() && (spartanPlayer.getGameMode() == GameMode.SURVIVAL || spartanPlayer.getGameMode() == GameMode.ADVENTURE) && ClientSidedBlock.a(spartanPlayer, spartanBlock.a()) == null;
    }
    
    static {
        a = Enums.HackType.GhostHand;
    }
}
