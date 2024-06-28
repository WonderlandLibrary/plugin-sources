package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.BlockUtils;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.c.NoHitDelay;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.h.BouncingBlocks;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class BlockRaytrace
{
    private static final Enums.HackType a;
    
    public BlockRaytrace() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final int n) {
        if (n > 5 || !Checks.getBoolean("KillAura.check_block_raytrace") || (entity instanceof Player && BouncingBlocks.R(SpartanBukkit.a(entity.getUniqueId(), 2))) || spartanPlayer.getItemInHand().containsEnchantment(Enchantment.KNOCKBACK) || NoHitDelay.E(spartanPlayer)) {
            return;
        }
        final String a = BlockUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), entity.getLocation(), 1.5);
        if (a != null) {
            KillAuraOverall.o(spartanPlayer);
            new HackPrevention(spartanPlayer, BlockRaytrace.a, "t: block raytrace, " + a);
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
