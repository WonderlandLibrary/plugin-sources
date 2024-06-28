package me.vagdedes.spartan.k.c;

import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.h.EnderPearl;
import me.vagdedes.spartan.checks.combat.a.a.CATraining;
import me.vagdedes.spartan.checks.combat.b.SavedLocation;
import me.vagdedes.spartan.checks.combat.b.TimeBetweenClicks;
import me.vagdedes.spartan.checks.combat.c.Comparison;
import me.vagdedes.spartan.checks.b.UndetectedMovement;
import me.vagdedes.spartan.checks.combat.c.HitTime;
import me.vagdedes.spartan.checks.b.ChunkUpdates;
import me.vagdedes.spartan.checks.combat.a.CombatAnalysis;
import me.vagdedes.spartan.checks.combat.Criticals;
import me.vagdedes.spartan.checks.combat.VelocityCheck;
import me.vagdedes.spartan.checks.e.NoSwing;
import me.vagdedes.spartan.checks.b.PingSpoof;
import me.vagdedes.spartan.checks.movement.IrregularMovements;
import me.vagdedes.spartan.checks.movement.EntityMove;
import me.vagdedes.spartan.checks.movement.MorePackets;
import me.vagdedes.spartan.checks.movement.Sprint;
import me.vagdedes.spartan.checks.movement.BoatMove;
import java.util.ArrayList;

public class LagManagement
{
    private static final ArrayList<String> b;
    public static final long e = 1200L;
    
    public LagManagement() {
        super();
    }
    
    public static void s() {
        BoatMove.clear();
        Sprint.clear();
        MorePackets.clear();
        EntityMove.clear();
        IrregularMovements.clear();
        PingSpoof.clear();
        NoSwing.clear();
        VelocityCheck.clear();
        Criticals.clear();
        CombatAnalysis.a(false, false);
        ChunkUpdates.clear();
        HitTime.clear();
        UndetectedMovement.clear();
        Comparison.clear();
        TimeBetweenClicks.clear();
        SavedLocation.clear();
        CATraining.clear();
        EnderPearl.clear();
    }
    
    public static void k(final boolean b) {
        if (NPC.a().length <= 100) {
            ArrayList<String> b2 = new ArrayList<String>();
            if (!b) {
                b2 = LagManagement.b;
            }
            AttemptUtils.b(b2);
            CooldownUtils.b(b2);
            MillisUtils.b(b2);
        }
        else {
            AttemptUtils.clear();
            CooldownUtils.clear();
            MillisUtils.clear();
        }
        Comparison.clear();
    }
    
    public static void run() {
        k(false);
    }
    
    public static double m(final SpartanPlayer spartanPlayer) {
        final boolean bi = LatencyUtils.bi(spartanPlayer);
        final double n = Settings.canDo("use_ping_protection") ? Math.max(bi ? 300.0 : 0.0, spartanPlayer.getPing()) : 0.0;
        final double n2 = ((!bi && n <= 175.0) || n > 1000.0 || VL.a(spartanPlayer, Enums.HackType.Exploits) > 0 || !Register.arePlibPacketsEnabled() || PingSpoof.o(spartanPlayer)) ? 0.0 : (n / 50.0);
        final double n3 = Settings.canDo("use_tps_protection") ? TPS.get() : 20.0;
        final double n4 = (n3 >= 18.0) ? 0.0 : (20.0 - n3);
        return (n4 > n2) ? n4 : n2;
    }
    
    public static double f(final SpartanPlayer spartanPlayer, final double n) {
        final double m = m(spartanPlayer);
        return (m == 0.0) ? n : (m / 2.0 * n);
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final int n) {
        final double m = m(spartanPlayer);
        return (m == 0.0) ? n : ((int)Math.floor(m) / 2 * n);
    }
    
    public static long a(final SpartanPlayer spartanPlayer, final long n) {
        final double m = m(spartanPlayer);
        return (m == 0.0) ? n : ((long)Math.floor(m) / 2L * n);
    }
    
    public static boolean a(final int n) {
        return Runtime.getRuntime().maxMemory() / 1024L / 1024L >= n - 128;
    }
    
    public static int t(final SpartanPlayer spartanPlayer) {
        return (int)Math.ceil(m(spartanPlayer));
    }
    
    static {
        (b = new ArrayList<String>(30)).add("air_ticks");
        LagManagement.b.add("ground_ticks");
        LagManagement.b.add("inventory_use");
        LagManagement.b.add("move-utils=");
        LagManagement.b.add("_effect");
        LagManagement.b.add("walk_difference");
        LagManagement.b.add("flying");
        LagManagement.b.add("vehicle=");
        LagManagement.b.add("elytra");
        LagManagement.b.add("trident");
        LagManagement.b.add("sprint-cache");
        LagManagement.b.add("combat");
        LagManagement.b.add("fight=");
        LagManagement.b.add("extremely=pushed");
        LagManagement.b.add("chasing");
        LagManagement.b.add("ground=");
        LagManagement.b.add("teleport-cooldown");
        LagManagement.b.add("utils");
        LagManagement.b.add("utilities");
        LagManagement.b.add("=protection");
        LagManagement.b.add("ping");
        LagManagement.b.add("ping");
        LagManagement.b.add("false-positive-detection");
        LagManagement.b.add("combat-analysis=");
        LagManagement.b.add(Enums.HackType.KillAura.toString() + "=overall=");
        LagManagement.b.add(Enums.HackType.Exploits.toString() + "=ping-spoof(packets)");
        LagManagement.b.add("noswing=animations");
    }
}
