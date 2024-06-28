package me.vagdedes.spartan.checks.combat.c;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.c.NoHitDelay;
import me.vagdedes.spartan.k.a.ArmorUtils;
import me.vagdedes.spartan.checks.combat.b.PitchRate;
import me.vagdedes.spartan.c.SmashHit;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.checks.combat.b.CombatHeuristics;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class TargetedArea
{
    private static final Enums.HackType a;
    private static final int h = 9;
    
    public TargetedArea() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Entity entity, final double n, final int n2, final double n3) {
        if (entity instanceof Player) {
            final SpartanPlayer a = SpartanBukkit.a(entity.getUniqueId(), 2);
            if (!CombatHeuristics.a(spartanPlayer, TargetedArea.a, entity) || !Checks.getBoolean("KillAura.check_targeted_area") || n < 1.0 || n2 > 2 || CombatUtils.c(spartanPlayer, entity) || !PlayerData.av(a) || LatencyUtils.e(spartanPlayer, 200)) {
                return;
            }
            final String string = TargetedArea.a.toString() + "=targeted-area=";
            final double l = CombatUtils.l(spartanPlayer, entity);
            a a2;
            if (l >= 1.2) {
                a2 = TargetedArea.a.c;
            }
            else if (l >= 0.78) {
                a2 = TargetedArea.a.b;
            }
            else {
                a2 = TargetedArea.a.a;
            }
            final boolean i = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0);
            final boolean j = PlayerData.i(a, 0.0, 0.0, 0.0);
            int n4 = 0;
            if (!i && j && a2 != TargetedArea.a.c) {
                n4 = 1;
            }
            else if (i && !j && a2 != TargetedArea.a.b) {
                n4 = 2;
            }
            else if (!i && !j && a2 != TargetedArea.a.c && !PlayerData.aw(spartanPlayer)) {
                n4 = 3;
            }
            else if (i && j) {
                final int h = CombatHeuristics.h(a);
                final int o = CpsCounter.o(spartanPlayer);
                if (h >= 3 && CombatHeuristics.h(spartanPlayer) >= h * 2) {
                    n4 = 4;
                }
                else if (PlayerData.au(a) && PlayerData.av(a) && o >= 8 && o > CpsCounter.o(a)) {
                    n4 = 5;
                }
                AttemptUtils.c(spartanPlayer, string + "attempts", AttemptUtils.a(spartanPlayer, string + "attempts") + 1);
                AttemptUtils.c(spartanPlayer, string + a2.toString(), AttemptUtils.a(spartanPlayer, string + a2.toString()) + 1);
                if (AttemptUtils.a(spartanPlayer, string + "attempts") >= 5) {
                    AttemptUtils.m(spartanPlayer, string + "attempts");
                    final int a3 = AttemptUtils.a(spartanPlayer, string + "HEAD");
                    AttemptUtils.m(spartanPlayer, string + "HEAD");
                    final int a4 = AttemptUtils.a(spartanPlayer, string + "BODY");
                    AttemptUtils.m(spartanPlayer, string + "BODY");
                    final int a5 = AttemptUtils.a(spartanPlayer, string + "LEGS");
                    AttemptUtils.m(spartanPlayer, string + "LEGS");
                    if (a5 >= 4) {
                        new HackPrevention(spartanPlayer, TargetedArea.a, "t: targeted-area, d: consistency, h: " + a3 + ", b: " + a4 + ", l: " + a5);
                    }
                }
            }
            if (n4 != 0 && !SmashHit.isEnabled() && (PitchRate.a(spartanPlayer) <= 5.0f || CombatHeuristics.m(spartanPlayer) || CpsCounter.o(spartanPlayer) >= 9)) {
                final String string2 = string + n4;
                ArmorUtils.a(a);
                final boolean b = n4 >= 3 && !NoHitDelay.E(spartanPlayer) && !NoHitDelay.E(a);
                if (AttemptUtils.b(spartanPlayer, string2, 100) >= (PlayerData.aw(spartanPlayer) ? (b ? 3 : 4) : ((VL.a(spartanPlayer, TargetedArea.a) >= 2) ? 2 : ((n4 == 2) ? 4 : (b ? 2 : 3))))) {
                    AttemptUtils.m(spartanPlayer, string2);
                    new HackPrevention(spartanPlayer, TargetedArea.a, "t: targeted-area, d: cases, c: " + n4);
                }
            }
        }
    }
    
    static {
        a = Enums.HackType.KillAura;
    }
    
    public enum a
    {
        a, 
        b, 
        c;
        
        private static final /* synthetic */ a[] a;
        
        public static a[] values() {
            return TargetedArea.a.a.clone();
        }
        
        public static a valueOf(final String name) {
            return Enum.<a>valueOf(a.class, name);
        }
        
        static {
            a = new a[] { TargetedArea.a.a, TargetedArea.a.b, TargetedArea.a.c };
        }
    }
}
