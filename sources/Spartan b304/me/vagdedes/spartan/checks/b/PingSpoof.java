package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class PingSpoof
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, Integer> w;
    private static final int max = 20;
    private static int time;
    
    public PingSpoof() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        PingSpoof.w.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        PingSpoof.w.clear();
    }
    
    public static boolean o(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, PingSpoof.a.toString() + "=ping-spoof(packets)");
    }
    
    private static boolean p(final SpartanPlayer spartanPlayer) {
        if (AttemptUtils.a(spartanPlayer, "plib=delay") != 0) {
            AttemptUtils.m(spartanPlayer, "plib=delay");
            return true;
        }
        return false;
    }
    
    public static void a(final SpartanPlayer[] array) {
        final boolean b;
        int length;
        int i = 0;
        SpartanPlayer spartanPlayer;
        final int j;
        final int n;
        final int k;
        int length2;
        int l = 0;
        SpartanPlayer spartanPlayer2;
        final int n2;
        Threads.a(array, () -> {
            Checks.getBoolean("Exploits.check_ping_spoof");
            if (PingSpoof.time == 0) {
                PingSpoof.time = 20;
                if (b) {
                    for (length = array.length; i < length; ++i) {
                        spartanPlayer = array[i];
                        if (!VL.b(spartanPlayer, PingSpoof.a, false) && MoveUtils.a(spartanPlayer.a(), MoveUtils.c(spartanPlayer))) {
                            spartanPlayer.getPing();
                            if (PingSpoof.w.containsKey(spartanPlayer.getUniqueId())) {
                                (int)Integer.valueOf(PingSpoof.w.get((Object)spartanPlayer.getUniqueId()));
                                Math.abs(j - n);
                                if (k <= 5 && j >= 400 && n >= 400) {
                                    if (AttemptUtils.b(spartanPlayer, PingSpoof.a.toString() + "=scheduler=attempts", 240) >= 12) {
                                        new HackPrevention(spartanPlayer, PingSpoof.a, "t: ping-spoof(scheduler), avg: " + k);
                                    }
                                }
                                else {
                                    AttemptUtils.m(spartanPlayer, PingSpoof.a.toString() + "=scheduler=attempts");
                                }
                            }
                            PingSpoof.w.put(spartanPlayer.getUniqueId(), Integer.valueOf(j));
                        }
                    }
                }
            }
            else {
                for (length2 = array.length; l < length2; ++l) {
                    spartanPlayer2 = array[l];
                    spartanPlayer2.getPing();
                    if (n2 >= 200 && (VL.o(spartanPlayer2) >= 1 || n2 >= 400) && p(spartanPlayer2)) {
                        CooldownUtils.d(spartanPlayer2, PingSpoof.a.toString() + "=ping-spoof(packets)", 1200);
                        if (b && !VL.b(spartanPlayer2, PingSpoof.a, true)) {
                            new HackPrevention(spartanPlayer2, PingSpoof.a, "t: ping-spoof(packets)");
                        }
                    }
                }
                --PingSpoof.time;
            }
        });
    }
    
    private static /* synthetic */ void b(final SpartanPlayer[] array) {
        final boolean boolean1 = Checks.getBoolean("Exploits.check_ping_spoof");
        if (PingSpoof.time == 0) {
            PingSpoof.time = 20;
            if (boolean1) {
                for (final SpartanPlayer spartanPlayer : array) {
                    if (!VL.b(spartanPlayer, PingSpoof.a, false) && MoveUtils.a(spartanPlayer.a(), MoveUtils.c(spartanPlayer))) {
                        final int ping = spartanPlayer.getPing();
                        if (PingSpoof.w.containsKey(spartanPlayer.getUniqueId())) {
                            final int intValue = (int)Integer.valueOf(PingSpoof.w.get((Object)spartanPlayer.getUniqueId()));
                            final int abs = Math.abs(ping - intValue);
                            if (abs <= 5 && ping >= 400 && intValue >= 400) {
                                if (AttemptUtils.b(spartanPlayer, PingSpoof.a.toString() + "=scheduler=attempts", 240) >= 12) {
                                    new HackPrevention(spartanPlayer, PingSpoof.a, "t: ping-spoof(scheduler), avg: " + abs);
                                }
                            }
                            else {
                                AttemptUtils.m(spartanPlayer, PingSpoof.a.toString() + "=scheduler=attempts");
                            }
                        }
                        PingSpoof.w.put(spartanPlayer.getUniqueId(), Integer.valueOf(ping));
                    }
                }
            }
        }
        else {
            for (final SpartanPlayer spartanPlayer2 : array) {
                final int ping2 = spartanPlayer2.getPing();
                if (ping2 >= 200 && (VL.o(spartanPlayer2) >= 1 || ping2 >= 400) && p(spartanPlayer2)) {
                    CooldownUtils.d(spartanPlayer2, PingSpoof.a.toString() + "=ping-spoof(packets)", 1200);
                    if (boolean1 && !VL.b(spartanPlayer2, PingSpoof.a, true)) {
                        new HackPrevention(spartanPlayer2, PingSpoof.a, "t: ping-spoof(packets)");
                    }
                }
            }
            --PingSpoof.time;
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
        w = new HashMap<UUID, Integer>();
        PingSpoof.time = 20;
    }
}
