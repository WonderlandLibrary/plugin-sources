package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Checks;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class EntityRaytrace
{
    private static final Enums.HackType a;
    
    public EntityRaytrace() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2) {
        if (n2 > 5 || !Checks.getBoolean("KillAura.check_entity_raytrace") || n < 1.0) {
            return;
        }
        for (double n3 = 0.0; n3 <= n; ++n3) {
            final SpartanBlock a = spartanPlayer.a().b(spartanPlayer.a().getDirection().multiply(n3)).a();
            for (final Entity entity2 : spartanPlayer.getNearbyEntities(n3, n3, n3)) {
                if (CombatUtils.d(entity2) && entity2 != entity) {
                    final SpartanLocation spartanLocation = new SpartanLocation(entity2.getLocation());
                    final double e = CombatUtils.e(spartanPlayer, entity2);
                    final double a2 = spartanLocation.a(a.a());
                    final double distance = entity2.getLocation().distance(entity.getLocation());
                    if (distance >= 1.0 && a2 <= 0.8 && e > 0.0 && e <= 7.5) {
                        KillAuraOverall.o(spartanPlayer);
                        new HackPrevention(spartanPlayer, EntityRaytrace.a, "t: entity raytrace, r: " + e + ", e_d_b: " + a2 + ", e_d_e: " + distance);
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
}
