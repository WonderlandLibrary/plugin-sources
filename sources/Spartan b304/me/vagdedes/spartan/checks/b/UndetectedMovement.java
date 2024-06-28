package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.k.c.PunishUtils;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.vagdedes.spartan.k.c.Threads;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.h.a.Liquid;
import org.bukkit.GameMode;
import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.h.Building;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.h.FloorProtection;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.f.SpartanLocation;
import java.util.UUID;
import java.util.HashMap;
import me.vagdedes.spartan.system.Enums;

public class UndetectedMovement
{
    private static final Enums.HackType a;
    private static final HashMap<UUID, SpartanLocation> x;
    
    public UndetectedMovement() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer) {
        UndetectedMovement.x.remove(spartanPlayer.getUniqueId());
    }
    
    public static void clear() {
        UndetectedMovement.x.clear();
    }
    
    public static void b(final SpartanPlayer spartanPlayer) {
        final long lng;
        SpartanLocation spartanLocation = null;
        final SpartanLocation spartanLocation2;
        final SpartanLocation spartanLocation3;
        final GameMode gameMode;
        boolean b;
        int i = 0;
        final int n;
        boolean q = false;
        final Object o;
        final Object o2;
        final SpartanLocation spartanLocation5;
        SpartanLocation spartanLocation4;
        SpartanLocation spartanLocation6;
        final SpartanLocation spartanLocation7;
        final double n2;
        final boolean b2;
        int n3 = 0;
        boolean b3;
        double d;
        final double n4;
        int n5 = 0;
        Threads.a(spartanPlayer, () -> {
            if (Register.v1_9 && Checks.getBoolean("Exploits.check_undetected_movement")) {
                MillisUtils.a(spartanPlayer, "undetected-movement");
                if (lng == 0L) {
                    MillisUtils.o(spartanPlayer, "undetected-movement");
                }
                else {
                    if (MillisUtils.hasTimer(lng) && lng > 150L) {
                        if (CooldownUtils.g(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement") && !Teleport.E(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !PlayerData.aS(spartanPlayer)) {
                            Label_0877_1: {
                                Label_0846_1: {
                                    if (UndetectedMovement.x.containsKey(spartanPlayer.getUniqueId())) {
                                        if (MoveUtils.i(spartanPlayer) <= 0.0) {
                                            spartanPlayer.a();
                                            spartanLocation = UndetectedMovement.x.get(spartanPlayer.getUniqueId());
                                            if (!MoveUtils.a(spartanLocation2, spartanLocation3)) {
                                                break Label_0846_1;
                                            }
                                        }
                                        if (!VL.b(spartanPlayer, UndetectedMovement.a, true) && !PlayerData.aF(spartanPlayer) && !PlayerData.aD(spartanPlayer) && MoveUtils.b(spartanPlayer) && !spartanPlayer.m() && !PlayerData.c(spartanPlayer, true) && BlockUtils.d(spartanPlayer, true, 0.3, 0.0, 0.3) && !Building.E(spartanPlayer) && !LatencyUtils.e(spartanPlayer, 250) && !EnderPearl.E(spartanPlayer) && !TPS.u() && !GroundUtils.ak(spartanPlayer)) {
                                            spartanPlayer.getGameMode();
                                            if (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE) {
                                                b = (Liquid.e(spartanPlayer) <= 1000L);
                                                if (b) {
                                                    while (i <= 2) {
                                                        Label_0379_1: {
                                                            if (BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0)) {
                                                                if (!BlockUtils.g(spartanPlayer, true, 0.298, i, 0.298)) {
                                                                    if (n != 0) {
                                                                        if (!q) {
                                                                            break Label_0379_1;
                                                                        }
                                                                    }
                                                                    else {
                                                                        q(spartanPlayer);
                                                                        if (!(o & o2)) {
                                                                            break Label_0379_1;
                                                                        }
                                                                    }
                                                                }
                                                                ++i;
                                                                continue;
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                                if (b) {
                                                    spartanLocation4 = ((spartanLocation5 == null) ? spartanPlayer.a() : spartanLocation5);
                                                    if (!BlockUtils.i(spartanPlayer, spartanLocation4) && !BlockUtils.s(spartanPlayer, spartanLocation4) && !BlockUtils.G(spartanPlayer, spartanLocation4) && !BlockUtils.z(spartanPlayer, spartanLocation4.b().b(0.0, -1.0, 0.0))) {
                                                        spartanLocation6 = ((spartanLocation == null) ? ((SpartanLocation)UndetectedMovement.x.get(spartanPlayer.getUniqueId())) : spartanLocation);
                                                        MoveUtils.c(spartanPlayer);
                                                        MoveUtils.a(spartanLocation4, spartanLocation7);
                                                        AttemptUtils.a(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still");
                                                        PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0);
                                                        if (n == 0) {
                                                            q = q(spartanPlayer);
                                                        }
                                                        if (n2 == 0.0 && (!b2 || q) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) {
                                                            ++n3;
                                                            AttemptUtils.c(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still", n3);
                                                        }
                                                        else {
                                                            AttemptUtils.m(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still");
                                                        }
                                                        b3 = (n3 >= (q ? 5 : 10));
                                                        d = (b3 ? spartanLocation4.a(spartanLocation6) : n2);
                                                        if (VL.o(spartanPlayer) >= 5 || d < 1.0) {
                                                            MathUtils.b(spartanLocation4, b3 ? spartanLocation6 : spartanLocation7);
                                                            if (d < 0.1 && n4 == 0.0) {
                                                                n5 = 1;
                                                            }
                                                            else if (d < 0.1 && b2) {
                                                                n5 = 9;
                                                            }
                                                            if (AttemptUtils.b(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement", 10) >= n5) {
                                                                new HackPrevention(spartanPlayer, UndetectedMovement.a, "t: undetected movement, ms: " + lng + ", d: " + d, spartanLocation7, 0, true);
                                                                r(spartanPlayer);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            break Label_0877_1;
                                        }
                                    }
                                }
                                CooldownUtils.d(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=cancelled", 10);
                            }
                            UndetectedMovement.x.put(spartanPlayer.getUniqueId(), (spartanLocation7 == null) ? MoveUtils.c(spartanPlayer) : spartanLocation7);
                            return;
                        }
                    }
                    UndetectedMovement.x.remove(spartanPlayer.getUniqueId());
                }
            }
        });
    }
    
    private static boolean q(final SpartanPlayer spartanPlayer) {
        return BlockUtils.h(spartanPlayer, true, 0.298, 0.0, 0.298) || BlockUtils.h(spartanPlayer, true, 0.298, 1.0, 0.298) || BlockUtils.m(spartanPlayer, true, 0.298, 0.0, 0.298);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final PlayerTeleportEvent.TeleportCause teleportCause) {
        if (Register.v1_9 && teleportCause != PlayerTeleportEvent.TeleportCause.UNKNOWN) {
            if (!PunishUtils.bg(spartanPlayer)) {
                a(spartanPlayer);
            }
            CooldownUtils.d(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement", 5);
        }
    }
    
    public static void p(final SpartanPlayer spartanPlayer) {
        if (Register.v1_9) {
            CooldownUtils.d(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement", 20);
        }
    }
    
    public static void q(final SpartanPlayer spartanPlayer) {
        if (Register.v1_9) {
            CooldownUtils.d(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement", 40);
        }
    }
    
    public static void r(final SpartanPlayer spartanPlayer) {
        if (Register.v1_9) {
            if (Building.E(spartanPlayer)) {
                MillisUtils.j(spartanPlayer, "undetected-movement");
            }
            else {
                MillisUtils.o(spartanPlayer, "undetected-movement");
            }
        }
    }
    
    private static /* synthetic */ void s(final SpartanPlayer spartanPlayer) {
        if (!Register.v1_9 || !Checks.getBoolean("Exploits.check_undetected_movement")) {
            return;
        }
        final long a = MillisUtils.a(spartanPlayer, "undetected-movement");
        if (a == 0L) {
            MillisUtils.o(spartanPlayer, "undetected-movement");
        }
        else if (MillisUtils.hasTimer(a) && a > 150L && CooldownUtils.g(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement") && !Teleport.E(spartanPlayer) && !FloorProtection.E(spartanPlayer) && !PlayerData.aS(spartanPlayer)) {
            SpartanLocation a2 = null;
            SpartanLocation spartanLocation = null;
            SpartanLocation c = null;
            if (UndetectedMovement.x.containsKey(spartanPlayer.getUniqueId()) && (MoveUtils.i(spartanPlayer) > 0.0 || MoveUtils.a(a2 = spartanPlayer.a(), spartanLocation = (SpartanLocation)UndetectedMovement.x.get(spartanPlayer.getUniqueId()))) && !VL.b(spartanPlayer, UndetectedMovement.a, true) && !PlayerData.aF(spartanPlayer) && !PlayerData.aD(spartanPlayer) && MoveUtils.b(spartanPlayer) && !spartanPlayer.m() && !PlayerData.c(spartanPlayer, true) && BlockUtils.d(spartanPlayer, true, 0.3, 0.0, 0.3) && !Building.E(spartanPlayer) && !LatencyUtils.e(spartanPlayer, 250) && !EnderPearl.E(spartanPlayer) && !TPS.u() && !GroundUtils.ak(spartanPlayer)) {
                final GameMode gameMode = spartanPlayer.getGameMode();
                if (gameMode == GameMode.SURVIVAL || gameMode == GameMode.ADVENTURE) {
                    int n = 0;
                    boolean b = false;
                    int n2 = (Liquid.e(spartanPlayer) <= 1000L) ? 1 : 0;
                    if (n2 != 0) {
                        int i = -1;
                        while (i <= 2) {
                            Label_0379: {
                                if (BlockUtils.a(spartanPlayer, true, 1.0, i, 1.0)) {
                                    if (!BlockUtils.g(spartanPlayer, true, 0.298, i, 0.298)) {
                                        if (n != 0) {
                                            if (!b) {
                                                break Label_0379;
                                            }
                                        }
                                        else if ((((b = q(spartanPlayer)) ? 1 : 0) & (n = 1)) == 0x0) {
                                            break Label_0379;
                                        }
                                    }
                                    ++i;
                                    continue;
                                }
                            }
                            n2 = 0;
                            break;
                        }
                    }
                    if (n2 != 0) {
                        final SpartanLocation spartanLocation2 = (a2 == null) ? spartanPlayer.a() : a2;
                        if (!BlockUtils.i(spartanPlayer, spartanLocation2) && !BlockUtils.s(spartanPlayer, spartanLocation2) && !BlockUtils.G(spartanPlayer, spartanLocation2) && !BlockUtils.z(spartanPlayer, spartanLocation2.b().b(0.0, -1.0, 0.0))) {
                            final SpartanLocation spartanLocation3 = (spartanLocation == null) ? ((SpartanLocation)UndetectedMovement.x.get(spartanPlayer.getUniqueId())) : spartanLocation;
                            c = MoveUtils.c(spartanPlayer);
                            final double a3 = MoveUtils.a(spartanLocation2, c);
                            int a4 = AttemptUtils.a(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still");
                            final boolean j = PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0);
                            if (n == 0) {
                                b = q(spartanPlayer);
                            }
                            if (a3 == 0.0 && (!j || b) && !PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0)) {
                                ++a4;
                                AttemptUtils.c(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still", a4);
                            }
                            else {
                                a4 = 0;
                                AttemptUtils.m(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=still");
                            }
                            final boolean b2 = a4 >= (b ? 5 : 10);
                            final double d = b2 ? spartanLocation2.a(spartanLocation3) : a3;
                            if (VL.o(spartanPlayer) >= 5 || d < 1.0) {
                                final double b3 = MathUtils.b(spartanLocation2, b2 ? spartanLocation3 : c);
                                int n3 = 3;
                                if (d < 0.1 && b3 == 0.0) {
                                    n3 = 1;
                                }
                                else if (d < 0.1 && j) {
                                    n3 = 9;
                                }
                                if (AttemptUtils.b(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement", 10) >= n3) {
                                    new HackPrevention(spartanPlayer, UndetectedMovement.a, "t: undetected movement, ms: " + a + ", d: " + d, c, 0, true);
                                    r(spartanPlayer);
                                }
                            }
                        }
                    }
                }
            }
            else {
                CooldownUtils.d(spartanPlayer, UndetectedMovement.a.toString() + "=undetected-movement=cancelled", 10);
            }
            UndetectedMovement.x.put(spartanPlayer.getUniqueId(), (c == null) ? MoveUtils.c(spartanPlayer) : c);
        }
        else {
            UndetectedMovement.x.remove(spartanPlayer.getUniqueId());
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
        x = new HashMap<UUID, SpartanLocation>();
    }
}
