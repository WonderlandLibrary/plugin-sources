package me.vagdedes.spartan.system;

import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import me.vagdedes.spartan.api.ViolationResetEvent;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.h.a.DeathAndRespawn;
import me.vagdedes.spartan.d.PlayerInfo;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.event.Event;
import me.vagdedes.spartan.api.PlayerViolationEvent;
import me.vagdedes.spartan.features.d.FalsePositiveDetection;
import me.vagdedes.spartan.k.a.CombatUtils;
import me.vagdedes.spartan.h.GameModeProtection;
import me.vagdedes.spartan.h.CheckProtection;
import me.vagdedes.spartan.f.CheckTPS;
import org.bukkit.GameMode;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.c.MythicMobs;
import me.vagdedes.spartan.features.d.PerformanceOptimizer;
import me.vagdedes.spartan.j.ProtocolSupport;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.g.Patreon;
import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.c.mcMMO;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.k.c.PunishUtils;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.c.PermissionUtils;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.c.ProtocolLib;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import java.util.HashMap;

public class VL
{
    private static int time;
    private static final int T = 10;
    private static final int U = 100;
    private static final HashMap<UUID, HashMap<Enums.HackType, Integer>> U;
    private static final HashMap<UUID, HashMap<Enums.HackType, String>> V;
    private static final HashMap<UUID, Long> W;
    private static final HashMap<UUID, HashSet<Enums.HackType>> X;
    private static final HashMap<UUID, HashMap<Enums.HackType, Integer>> Y;
    private static final HashMap<UUID, HashMap<String, Integer>> l;
    private static final HashMap<UUID, HashSet<Enums.HackType>> Z;
    private static final HashSet<UUID> q;
    
    public VL() {
        super();
    }
    
    public static void q() {
        VL.U.clear();
        VL.V.clear();
        VL.W.clear();
        VL.X.clear();
        VL.Y.clear();
        VL.l.clear();
        VL.Z.clear();
        VL.q.clear();
    }
    
    private static void b(final ArrayList<Enums.HackType> list) {
        if (list.size() == 0) {
            VL.U.clear();
        }
        else {
            final Iterator<Map.Entry<UUID, HashMap<Enums.HackType, Integer>>> iterator = VL.U.entrySet().iterator();
            while (iterator.hasNext()) {
                final Iterator<Map.Entry<Enums.HackType, Integer>> iterator2 = ((HashMap<Enums.HackType, Integer>)((Map.Entry<UUID, HashMap<Enums.HackType, Integer>>)iterator.next()).getValue()).entrySet().iterator();
                while (iterator2.hasNext()) {
                    if (!list.contains(((Map.Entry<Enums.HackType, Integer>)iterator2.next()).getKey())) {
                        iterator2.remove();
                    }
                }
            }
        }
    }
    
    public static int m() {
        return 100;
    }
    
    public static int n() {
        return 10;
    }
    
    public static boolean ae(final SpartanPlayer spartanPlayer) {
        return ProtocolLib.F(spartanPlayer) || ((!spartanPlayer.isOp() || Settings.canDo("op_bypass")) && PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.bypass));
    }
    
    public static boolean e(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer)) {
            return true;
        }
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        return (!spartanPlayer.isOp() || Settings.canDo("op_bypass")) && (PermissionUtils.a(player, Enums.Permission.bypass) || PermissionUtils.isBypassing(player, hackType));
    }
    
    public static long f(final SpartanPlayer spartanPlayer) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return VL.W.containsKey(uniqueId) ? (System.currentTimeMillis() - Long.valueOf(VL.W.get((Object)uniqueId))) : 0L;
    }
    
    public static void J(final SpartanPlayer spartanPlayer) {
        VL.W.put(spartanPlayer.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
    }
    
    public static int a(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer)) {
            return 0;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return (!VL.U.containsKey(uniqueId) || !((HashMap<Enums.HackType, Integer>)VL.U.get(uniqueId)).containsKey(hackType)) ? 0 : ((int)Integer.valueOf(((HashMap<Enums.HackType, Integer>)VL.U.get(uniqueId)).get((Object)hackType)));
    }
    
    public static int o(final SpartanPlayer spartanPlayer) {
        int n = 0;
        if (ProtocolLib.F(spartanPlayer)) {
            return n;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (VL.U.containsKey(uniqueId)) {
            final Iterator<Enums.HackType> iterator = ((HashMap<Enums.HackType, Integer>)VL.U.get(uniqueId)).keySet().iterator();
            while (iterator.hasNext()) {
                n += Integer.valueOf(((HashMap<Enums.HackType, Integer>)VL.U.get(uniqueId)).get((Object)iterator.next()));
            }
        }
        return n;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType key, final int i, final boolean b, final String s) {
        if (ProtocolLib.F(spartanPlayer) || e(spartanPlayer, s)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.U.containsKey(uniqueId)) {
            VL.U.put(uniqueId, new HashMap<Enums.HackType, Integer>());
        }
        final HashMap<Enums.HackType, Integer> value = (HashMap<Enums.HackType, Integer>)VL.U.get(uniqueId);
        final int j = 0;
        if (i > m()) {
            value.put(key, Integer.valueOf(m()));
        }
        else if (i < j) {
            value.put(key, Integer.valueOf(j));
        }
        else {
            value.put(key, Integer.valueOf(i));
        }
        VL.U.put(uniqueId, value);
        if (b) {
            PunishUtils.a(spartanPlayer, key, s, true);
        }
        Config.a(Bukkit.getPlayer(uniqueId), key);
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, final boolean b) {
        if (!Config.isEnabled(hackType) || a(spartanPlayer, b) || e(spartanPlayer, hackType) || g(spartanPlayer, hackType)) {
            return true;
        }
        final boolean b2 = hackType == Enums.HackType.KillAura;
        return (hackType == Enums.HackType.Nuker || hackType == Enums.HackType.GhostHand || hackType == Enums.HackType.FastBreak || hackType == Enums.HackType.BlockReach || hackType == Enums.HackType.NoSwing || hackType == Enums.HackType.HitReach || b2) && (mcMMO.H(spartanPlayer) || (mcMMO.J(spartanPlayer) && b2));
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final boolean b) {
        final VersionUtils.a a = VersionUtils.a();
        return !Patreon.o() || NPC.D(spartanPlayer) || ProtocolLib.F(spartanPlayer) || ProtocolSupport.ad(spartanPlayer) || PerformanceOptimizer.O(spartanPlayer) || MythicMobs.D(spartanPlayer) || (b && (TPS.t() || LatencyUtils.bi(spartanPlayer))) || (a != VersionUtils.a.c && a != VersionUtils.a.l && spartanPlayer.getGameMode() == GameMode.SPECTATOR);
    }
    
    private static boolean f(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        boolean b = false;
        if (TPS.i(spartanPlayer, hackType)) {
            final CheckTPS a = TPS.a(spartanPlayer, hackType);
            final double tps = a.getTPS();
            if (a.getTime() <= 15000L && tps > 0.0 && tps - TPS.get() >= 0.499995) {
                b = true;
            }
        }
        TPS.g(spartanPlayer, hackType);
        if (!b && LatencyUtils.i(spartanPlayer, hackType)) {
            final double n = (double)LatencyUtils.a(spartanPlayer, hackType).getPing();
            if (n > 0.0 && spartanPlayer.getPing() >= n + 37.5) {
                b = true;
            }
        }
        LatencyUtils.g(spartanPlayer, hackType);
        return b;
    }
    
    public static boolean b(final SpartanPlayer spartanPlayer, final Enums.HackType hackType, String s) {
        if (spartanPlayer == null || hackType == null || CheckProtection.d(spartanPlayer, s) || GameModeProtection.E(spartanPlayer)) {
            return false;
        }
        if (PunishUtils.bg(spartanPlayer)) {
            boolean b = false;
            final Enums.HackType[] a = CombatUtils.a;
            for (int length = a.length, i = 0; i < length; ++i) {
                if (a[i] == hackType) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
        }
        if (f(spartanPlayer, hackType)) {
            return false;
        }
        if (s == null) {
            s = "None";
        }
        boolean a2 = FalsePositiveDetection.a(spartanPlayer, hackType, s);
        if (a2 && FalsePositiveDetection.c(spartanPlayer, hackType)) {
            a2 = false;
        }
        final int j = a(spartanPlayer, hackType) + (a2 ? 0 : 1);
        if (Thread.currentThread().getId() <= 29L) {
            final PlayerViolationEvent playerViolationEvent = new PlayerViolationEvent(Bukkit.getPlayer(spartanPlayer.getUniqueId()), hackType, Integer.valueOf(j), s, a2);
            Bukkit.getPluginManager().callEvent((Event)playerViolationEvent);
            if (playerViolationEvent.isCancelled()) {
                PunishUtils.P(spartanPlayer);
                return false;
            }
        }
        final SpartanPlayer[] a3 = NPC.a();
        if (j > 0) {
            J(spartanPlayer);
            if (!SynManager.s() && j == 4) {
                for (final SpartanPlayer spartanPlayer2 : a3) {
                    final Player player = spartanPlayer2.getPlayer();
                    final UUID uniqueId = spartanPlayer2.getUniqueId();
                    if (PermissionUtils.a(player, Enums.Permission.manage) && !VL.q.contains(uniqueId)) {
                        VL.q.add(uniqueId);
                        if (ErrorUtils.d("Several players may have reached unsafe violation levels. Spartan offers moderation features to help find illegitimate players. Click this message to learn more.") != null) {}
                    }
                }
            }
        }
        a(spartanPlayer, hackType, j, true, s);
        final Player player2 = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        for (final SpartanPlayer spartanPlayer3 : a3) {
            if (PlayerData.d(spartanPlayer3, 0) && spartanPlayer3.a().getTitle().equalsIgnoreCase("§0Info:§2 " + spartanPlayer.getName())) {
                PlayerInfo.a(spartanPlayer3, player2);
            }
        }
        return true;
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Enums.HackType key, final int i) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.Y.containsKey(uniqueId) && i > 0) {
            VL.Y.put(uniqueId, new HashMap<Enums.HackType, Integer>());
        }
        final HashMap<Enums.HackType, Integer> value = (HashMap<Enums.HackType, Integer>)VL.Y.get(uniqueId);
        if (!value.containsKey(key) || i >= Integer.valueOf(value.get((Object)key))) {
            final int j = 3600;
            final int k = 0;
            if (i > j) {
                value.put(key, Integer.valueOf(j));
            }
            else if (i < k) {
                value.put(key, Integer.valueOf(k));
            }
            else {
                value.put(key, Integer.valueOf(i));
            }
            VL.Y.put(uniqueId, value);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final String key, final int i) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.l.containsKey(uniqueId) && i > 0) {
            VL.l.put(uniqueId, new HashMap<String, Integer>());
        }
        final HashMap<String, Integer> value = (HashMap<String, Integer>)VL.l.get(uniqueId);
        if (!value.containsKey(key) || i >= Integer.valueOf(value.get((Object)key))) {
            final int j = 3600;
            final int k = 0;
            if (i > j) {
                value.put(key, Integer.valueOf(j));
            }
            else if (i < k) {
                value.put(key, Integer.valueOf(k));
            }
            else {
                value.put(key, Integer.valueOf(i));
            }
            VL.l.put(uniqueId, value);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.Z.containsKey(uniqueId)) {
            VL.Z.put(uniqueId, new HashSet<Enums.HackType>());
        }
        final HashSet<Enums.HackType> value = (HashSet<Enums.HackType>)VL.Z.get(uniqueId);
        if (!value.contains(hackType)) {
            value.add(hackType);
            VL.Z.put(uniqueId, value);
        }
    }
    
    public static void c(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.X.containsKey(uniqueId)) {
            VL.X.put(uniqueId, new HashSet<Enums.HackType>());
        }
        final HashSet<Enums.HackType> value = (HashSet<Enums.HackType>)VL.X.get(uniqueId);
        if (!value.contains(hackType)) {
            value.add(hackType);
            VL.X.put(uniqueId, value);
        }
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, final Enums.HackType key, final String value) {
        if (ProtocolLib.F(spartanPlayer) || f(spartanPlayer, key)) {
            return false;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (!VL.V.containsKey(uniqueId)) {
            VL.V.put(uniqueId, new HashMap<Enums.HackType, String>());
        }
        ((HashMap<Enums.HackType, String>)VL.V.get(uniqueId)).put(key, value);
        return true;
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer) || !VL.Z.containsKey(spartanPlayer.getUniqueId())) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final HashSet<Enums.HackType> value = (HashSet<Enums.HackType>)VL.Z.get(uniqueId);
        if (value.contains(hackType)) {
            value.remove(hackType);
            VL.Z.put(uniqueId, value);
        }
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final Enums.HackType hackType) {
        if (ProtocolLib.F(spartanPlayer) || !VL.X.containsKey(spartanPlayer.getUniqueId())) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final HashSet<Enums.HackType> value = (HashSet<Enums.HackType>)VL.X.get(uniqueId);
        if (value.contains(hackType)) {
            value.remove(hackType);
            VL.X.put(uniqueId, value);
        }
    }
    
    public static void run() {
        for (final Map.Entry<UUID, HashMap<Enums.HackType, String>> entry : VL.V.entrySet()) {
            final UUID uuid = (UUID)entry.getKey();
            final SpartanPlayer spartanPlayer = new SpartanPlayer(Bukkit.getPlayer(uuid));
            for (final Enums.HackType hackType : ((HashMap<Enums.HackType, String>)entry.getValue()).keySet()) {
                if (a(spartanPlayer, hackType) < Config.a(hackType)) {
                    b(spartanPlayer, hackType, ((HashMap<Enums.HackType, String>)VL.V.get(uuid)).get(hackType));
                }
                else {
                    ((HashMap<Enums.HackType, String>)VL.V.get(uuid)).remove(hackType);
                }
            }
        }
        final Iterator<Map.Entry<UUID, HashMap<Enums.HackType, Integer>>> iterator3 = VL.Y.entrySet().iterator();
        while (iterator3.hasNext()) {
            for (final Map.Entry<Enums.HackType, Integer> entry2 : ((HashMap<Enums.HackType, Integer>)((Map.Entry<UUID, HashMap<Enums.HackType, Integer>>)iterator3.next()).getValue()).entrySet()) {
                final int intValue = (int)Integer.valueOf(entry2.getValue());
                if (intValue > 0) {
                    entry2.setValue(Integer.valueOf(intValue - 1));
                }
            }
        }
        final Iterator<Map.Entry<UUID, HashMap<String, Integer>>> iterator5 = VL.l.entrySet().iterator();
        while (iterator5.hasNext()) {
            for (final Map.Entry<String, Integer> entry3 : ((HashMap<String, Integer>)((Map.Entry<UUID, HashMap<String, Integer>>)iterator5.next()).getValue()).entrySet()) {
                final int intValue2 = (int)Integer.valueOf(entry3.getValue());
                if (intValue2 > 0) {
                    entry3.setValue(Integer.valueOf(intValue2 - 1));
                }
            }
        }
        if (VL.time == 0) {
            VL.time = Settings.a() * 20;
            i(false);
        }
        else {
            --VL.time;
        }
    }
    
    public static boolean g(final SpartanPlayer spartanPlayer, final Enums.HackType key) {
        if (ProtocolLib.F(spartanPlayer)) {
            return false;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (spartanPlayer.a() == null) {
            spartanPlayer.b(-1);
        }
        return DeathAndRespawn.E(spartanPlayer) || FalsePositiveDetection.d(spartanPlayer, key) || !Config.a(key, spartanPlayer.getWorld().getName()) || (VL.Z.containsKey(uniqueId) && ((HashSet<Enums.HackType>)VL.Z.get(uniqueId)).contains(key)) || (VL.Y.containsKey(uniqueId) && ((HashMap<Enums.HackType, Integer>)VL.Y.get(uniqueId)).containsKey(key) && Integer.valueOf(((HashMap<Enums.HackType, Integer>)VL.Y.get(uniqueId)).get((Object)key)) > 0);
    }
    
    public static boolean h(final SpartanPlayer spartanPlayer, final Enums.HackType o) {
        if (ProtocolLib.F(spartanPlayer)) {
            return false;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        return VL.X.containsKey(uniqueId) && ((HashSet<Enums.HackType>)VL.X.get(uniqueId)).contains(o);
    }
    
    private static boolean e(final SpartanPlayer spartanPlayer, final String s) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final boolean b = VL.l.containsKey(uniqueId) && ((HashMap<String, Integer>)VL.l.get(uniqueId)).containsKey(s) && Integer.valueOf(((HashMap<String, Integer>)VL.l.get(uniqueId)).get((Object)s)) > 0;
        if (b) {
            CooldownUtils.d(spartanPlayer, "information=cancelled", 1);
        }
        return b;
    }
    
    public static boolean af(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "information=cancelled");
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final boolean b) {
        if (ProtocolLib.F(spartanPlayer)) {
            return;
        }
        final UUID uniqueId = spartanPlayer.getUniqueId();
        if (b || o(spartanPlayer) == 0) {
            VL.U.remove(uniqueId);
        }
        final Player player = Bukkit.getPlayer(spartanPlayer.getUniqueId());
        VL.V.remove(uniqueId);
        VL.l.remove(uniqueId);
        VL.Y.remove(uniqueId);
        VL.Z.remove(uniqueId);
        VL.W.remove(uniqueId);
        VL.X.remove(uniqueId);
        for (final SpartanPlayer spartanPlayer2 : NPC.a()) {
            if (PlayerData.d(spartanPlayer2, 0) && spartanPlayer2.a().getTitle().equalsIgnoreCase("§0Info:§2 " + spartanPlayer.getName())) {
                if (spartanPlayer.isOnline()) {
                    PlayerInfo.a(spartanPlayer2, player);
                }
                else {
                    Bukkit.getPlayer(spartanPlayer2.getUniqueId()).closeInventory();
                }
            }
        }
    }
    
    public static void i(final boolean b) {
        final ViolationResetEvent violationResetEvent = new ViolationResetEvent();
        Bukkit.getPluginManager().callEvent((Event)violationResetEvent);
        if (violationResetEvent.isCancelled()) {
            return;
        }
        b(violationResetEvent.getIgnoredChecks());
        if (b) {
            final Player[] a = VerboseNotifications.a();
            for (int length = a.length, i = 0; i < length; ++i) {
                a[i].sendMessage(Language.getMessage("violations_reset"));
            }
        }
    }
    
    public static void r() {
        VL.time = 0;
    }
    
    static {
        VL.time = 0;
        U = new HashMap<UUID, HashMap<Enums.HackType, Integer>>();
        V = new HashMap<UUID, HashMap<Enums.HackType, String>>();
        W = new HashMap<UUID, Long>();
        X = new HashMap<UUID, HashSet<Enums.HackType>>();
        Y = new HashMap<UUID, HashMap<Enums.HackType, Integer>>();
        l = new HashMap<UUID, HashMap<String, Integer>>();
        Z = new HashMap<UUID, HashSet<Enums.HackType>>();
        q = new HashSet<UUID>();
    }
}
