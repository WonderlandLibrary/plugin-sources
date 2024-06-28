package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.a.BlockUtils;
import org.bukkit.enchantments.Enchantment;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;

public class SelfHit
{
    public static final int limit = 25;
    
    public SelfHit() {
        super();
    }
    
    public static void a(final Entity entity, final Entity entity2) {
        if (entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId(), -1);
            if (a.a().getPitch() <= -60.0f) {
                CooldownUtils.d(a, "self-hit=protection=" + entity2.getUniqueId().toString(), 200);
            }
        }
    }
    
    public static boolean Z(final SpartanPlayer spartanPlayer) {
        return CooldownUtils.a(spartanPlayer, "self-hit=protection") > 20;
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final Entity entity, final int n) {
        if (entity == null || b(spartanPlayer, entity)) {
            CooldownUtils.d(spartanPlayer, "self-hit=protection", n);
            return true;
        }
        return false;
    }
    
    private static boolean b(final SpartanPlayer spartanPlayer, final Entity entity) {
        return !PlayerData.aS(spartanPlayer) && ((!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) || BowProtection.E(spartanPlayer) || Damage.E(spartanPlayer) || MoveUtils.k(spartanPlayer) >= 0.18 || CooldownUtils.g(spartanPlayer, "self-hit=protection=" + entity.getUniqueId().toString()) || spartanPlayer.getItemInHand().containsEnchantment(Enchantment.ARROW_KNOCKBACK) || !BlockUtils.a(spartanPlayer, true, 0.298, 0.0, 0.298) || !BlockUtils.a(spartanPlayer, true, 0.298, 1.0, 0.298));
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "self-hit=protection");
    }
}
