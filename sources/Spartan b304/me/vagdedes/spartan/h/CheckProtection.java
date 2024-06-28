package me.vagdedes.spartan.h;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.j.NPC;
import java.util.UUID;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class CheckProtection
{
    private static int cooldown;
    
    public CheckProtection() {
        super();
    }
    
    public static void run() {
        if (CheckProtection.cooldown > 0) {
            --CheckProtection.cooldown;
        }
    }
    
    public static void f(final SpartanPlayer spartanPlayer, final int n) {
        CooldownUtils.d(spartanPlayer, "check=protection", n);
    }
    
    public static void a(final UUID uuid, final int n) {
        CooldownUtils.b(uuid, "check=protection", n);
    }
    
    public static void c(final int cooldown) {
        if (cooldown > 0) {
            CheckProtection.cooldown = cooldown;
            final SpartanPlayer[] a = NPC.a();
            for (int length = a.length, i = 0; i < length; ++i) {
                f(a[i], 5);
            }
        }
    }
    
    public static boolean p() {
        return CheckProtection.cooldown > 0;
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return p() || !CooldownUtils.g(spartanPlayer, "check=protection");
    }
    
    public static boolean d(final SpartanPlayer spartanPlayer, final String s) {
        if (E(spartanPlayer)) {
            if (s != null) {
                for (final String s2 : s.split(" ")) {
                    if (MathUtils.validDouble(s2) && Double.valueOf(s2) >= 1.0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public static void h(final SpartanPlayer spartanPlayer, final int n) {
        VL.a(spartanPlayer, Enums.HackType.Speed, n);
        VL.a(spartanPlayer, Enums.HackType.Sprint, n);
        VL.a(spartanPlayer, Enums.HackType.Fly, n);
        VL.a(spartanPlayer, Enums.HackType.IrregularMovements, n);
        VL.a(spartanPlayer, Enums.HackType.HitReach, n);
        VL.a(spartanPlayer, Enums.HackType.Velocity, n);
        VL.a(spartanPlayer, Enums.HackType.KillAura, n);
    }
    
    static {
        CheckProtection.cooldown = 0;
    }
}
